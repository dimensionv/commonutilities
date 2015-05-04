/*
 * Copyright (c) 2015, , Volkmar Seifert, DimensionV.de
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package de.dimensionv.java.libraries.common.utilities.strings;

import java.util.Comparator;

/**
 * <p>
 * This is a {@link Comparator} that explicitly uses plain {@link Object} as input and utilizes the
 * {@link Object#toString()} to create {@link String} objects for sorting.</p>
 *
 * <p>
 * The algorithm used here in this implementation is optimized to avoid unnecessary check-cycles.</p>
 *
 * @author Volkmar Seifert &lt;vs@DimensionV.de&gt;
 *
 * @version 1.0
 * @since API 2.0
 */
public final class NaturalSortComparator implements Comparator {

  private static NaturalSortComparator INSTANCE = null;

  /**
   * Meta-data for the actual {@link String} object that should be compared.
   */
  private final class SortingBean {

    int index = 0;
    int leadingZeros = 0;
    char character;
    final String string;

    /**
     * Creates a new instance of the {@code SortingBean} with the given {@link String} {@code string}.
     *
     * @param string The {@code String} that shall be compared.
     */
    SortingBean(String string) {
      this.string = string;
    }
  }

  /**
   * The result data of the {@link NaturalSortComparator#compareDigits(de.dimensionv.java.libraries.common.utilities.strings.NaturalSortComparator.SortingBean, de.dimensionv.java.libraries.common.utilities.strings.NaturalSortComparator.SortingBean)
   * } method.
   */
  private static final class DigitCompareResult {

    final int result;
    final int offset;

    /**
     * Creates a {@code DigitCompareResult} object.
     *
     * @param result The result of the digit-comparison
     * @param offset The length of consecutive digits in both strings.
     */
    DigitCompareResult(int result, int offset) {
      this.result = result;
      this.offset = offset;
    }
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * This implementation sorts arbitrary {@link Object}s in natural order. Only prerequisite is a properly implemented
   * {@link Object#toString()} method. Also helpful are proper implementations of
   * {@link Object#equals(java.lang.Object)} and {@link Object#hashCode()}, though neither of these two methods are used
   * directly by this {@code Comparator} implementation. However, they might be used indirectly.</p>
   *
   * <p>
   * This is an example of what a sorted list of {@link String}s would look like after being sorted with this {@link Comparator}:
   *
   * {@code
   *    {
   *      "1-04", "1-4", "1-40", "10-40", "Alice", "Bob", "Charly",
   *      "a6-b6", "h2-i7", "item01", "item02", "item02a", "item2", "item3",
   *      "item00004", "item4", "item 4 else", "item05", "item 5", "item 5",
   *      "item 5 something", "item 6", "item    8", "item128", "item128a",
   *      "item255", "item256", "item04096", "item04096 test 1",
   *      "item04096 test 2", "item04096 test 2a", "item04096 test 2b",
   *      "item04096 test 3", "item04096 test 3a", "item04096 test 3b",
   *      "x2-y08", "z3-f6"
   *    }
   * }
   *
   * @param o1 The first object to be compared.
   * @param o2 The second object to be compared.
   * @return -1, 0, or 1 as the first argument is less than, equal to, or greater than the second.
   * @throws NullPointerException if an argument (i.e. {@code o1} or {@code o1}) is null
   */
  @Override
  public final int compare(Object o1, Object o2) {
    SortingBean beanOne = new SortingBean(o1.toString());
    SortingBean beanTwo = new SortingBean(o2.toString());

    int result = 0;

    do {
      readCharacter(beanOne);
      readCharacter(beanTwo);

      countLeadingZeros(beanOne);
      countLeadingZeros(beanTwo);

      // check if both characters are digits
      if (Character.isDigit(beanOne.character) && Character.isDigit(beanTwo.character)) {
        SortingBean beanOneNumbers = new SortingBean(beanOne.string.substring(beanOne.index));
        SortingBean beanTwoNumbers = new SortingBean(beanTwo.string.substring(beanTwo.index));
        DigitCompareResult digitCompareResult = compareDigits(beanOneNumbers, beanTwoNumbers);

        if (digitCompareResult.result != 0) {
          result = digitCompareResult.result;
          break; // only exit loop if strings are not equal -> we have a decision :)
        }

        result = evaluateLeadingZeroDelta(beanOne, beanTwo);

        // numbers compare equal, so skip them from the loop, we can continue after them.
        if (digitCompareResult.offset - 1 > 0) {
          // subtract 1, because at the end of the loop, this will be added again.
          int offset = digitCompareResult.offset - 1;
          beanOne.index += offset;
          beanTwo.index += offset;
        }
      } else if ((beanOne.character == 0) && (beanTwo.character == 0)) {
        // return the evaluation of leading zeros, normalized to the values -1, 0 and 1
        result = evaluateLeadingZeroDelta(beanOne, beanTwo);
        break;
      }

      if (beanOne.character < beanTwo.character) {
        result = -1;
      } else if (beanOne.character > beanTwo.character) {
        result = 1;
      }

      beanOne.index++;
      beanTwo.index++;
    } while (result == 0);

    if (result < -1) {
      result = -1;
    } else if (result > 1) {
      result = 1;
    }

    return result;
  }

  /**
   * Evaluates the delta of the leading zero meta information stored in each given {@link SortingBean} object, and
   * returns either -1, 0 or 1 to indicate that beanOne is either less than, equal to or even greater than beanTwo.
   *
   * @param beanOne Meta-data for the first {@code String} object
   * @param beanTwo Meta-data for the first {@code String} object
   * @return either -1, 0 or 1 to indicate that beanOne is either less than, equal to or even greater than beanTwo.
   */
  private static final int evaluateLeadingZeroDelta(SortingBean beanOne, SortingBean beanTwo) {
    int result = (beanTwo.leadingZeros - beanOne.leadingZeros);

    if (result < -1) {
      result = -1;
    } else if (result > 1) {
      result = 1;
    }

    return result;
  }

  /**
   * <p>
   * Specialized helper method for comparing strings of digits within a {@link String}.</p>
   * <p>
   * It returns not just the comparison result, but also the length of the number represented by the consecutive string
   * of digits as an offset, so that the number can be skipped if both objects contain the same number.</p>
   *
   * @param beanOne Meta-data for the first {@code String} object
   * @param beanTwo Meta-data for the first {@code String} object
   * @return A {@link DigitCompareResult} object containing the result and offset information.
   */
  private static final DigitCompareResult compareDigits(SortingBean beanOne, SortingBean beanTwo) {
    int result = 0;

    do {
      readCharacter(beanOne);
      readCharacter(beanTwo);

      if (!Character.isDigit(beanOne.character) && !Character.isDigit(beanTwo.character)) {
        break;
      } else if (!Character.isDigit(beanOne.character)) {
        result = -1;
        break;
      } else if (!Character.isDigit(beanTwo.character)) {
        result = 1;
        break;
      } else if (beanOne.character < beanTwo.character) {
        if (result == 0) { // only change if strings were equal up to this point...
          result = -1;
        }
      } else if (beanOne.character > beanTwo.character) {
        if (result == 0) { // only change if strings were equal up to this point...
          result = 1;
        }
      } else if (beanOne.character == 0 && beanTwo.character == 0) {
        break;
      }

      beanOne.index++;
      beanTwo.index++;
    } while (true);

    // Estimate the lower number of digist, because that's the number of digits encountered
    // in both strings, and that at least can be skipped, no matter what the actual result is...
    // To enable skipping the numbers, hand back the index as offset
    int offset = Math.min(beanOne.index, beanTwo.index);

    return new DigitCompareResult(result, offset);
  }

  /**
   * Specialized helper method for counting the leading zeros, if there are any, in the {@link String}.
   *
   * @param bean The {@code SortingBean} that contains all the necessary runtime information for the String.
   */
  private static final void countLeadingZeros(SortingBean bean) {
    bean.leadingZeros = 0;
    // skip over leading spaces or zeros
    while (Character.isSpaceChar(bean.character) || (bean.character == '0')) {
      if (bean.character == '0') {
        bean.leadingZeros++;
      } else {
        // only count consecutive zeroes
        bean.leadingZeros = 0;
      }

      bean.index++;
      readCharacter(bean);
    }
  }

  /**
   * <p>
   * Specialized helper-method, which retrieves a specific requested {@code char} from the {@link String} in the given
   * {@link SortingBean}. The requested {@code char} is defined by the {@code index} member variable of the given
   * {@code SortBean}.</p>
   * <p>
   * If {@code SortBean.index} is within the bounds of the {@code SortBean.string}, this method will return
   * {@link String#charAt(int)}, otherwise it will return a {@literal 0}.<br />
   * Please note that this is an actual {@literal 0}, not a {@literal '0'}.</p>
   *
   * <p>
   * For memory footprint reasons, this method is static, which means no matter how many instances of the
   * {@code NaturalSortComparator} are active in memory, this method will be present only once.</p>
   *
   * @param sortBean The {@code SortBean} to process
   *
   * @return The {@code char} at position {@code index} within {@code string} or {@literal 0} if {@code index} is
   * outside of the {@code string}'s range.
   */
  private static final char readCharacter(SortingBean sortBean) {
    sortBean.character = (sortBean.index < sortBean.string.length())
        ? sortBean.string.charAt(sortBean.index)
        : (char) 0;

    return sortBean.character;
  }

  public static final NaturalSortComparator getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new NaturalSortComparator();
    }
    return INSTANCE;
  }
}

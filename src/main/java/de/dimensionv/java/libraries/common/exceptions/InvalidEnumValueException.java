/*
 * Copyright (c) 2014, Volkmar Seifert, DimensionV.de
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
package de.dimensionv.java.libraries.common.exceptions;

/**
 * <p>
 * Exception-class for int-to-enum conversions, when the given int-value exceeds the valid values of the enum.</p>
 *
 * <p>
 * <b>Example:</b></p>
 * <pre>
 * enum ExampleEnum {
 *
 *   ONE,
 *   TWO,
 *   THREE,
 *   FOUR;
 *
 *   // cache all the values defined above in static array for fast int-to-enum conversion
 *   private static final JavaType[] VALUES = values();
 *
 *  / **
 *    * This method takes an arbitrary int value and returns the appropriate enum-value In case the
 *    * int-value is not a valid value of the enum, an Exception will be thrown.
 *    *
 *    * @param value
 *    *     The integer value to convert into the enum (also called "ordinal")
 *    *
 *    * @return the enum-object associated with the given ordinal.
 *    *
 *    * @throws de.dimensionv.java.libraries.common.exceptions.InvalidEnumValueException
 *    *     if value is not a valid value for the enum.
 *    * /
 *    public static JavaType fromOrdinal(int value) {
 *      try {
 *        return VALUES[value];
 *      } catch(ArrayIndexOutOfBoundsException ex) {
 *        throw new InvalidEnumValueException(value, VALUES.length-1);
 *      }
 *    }
 * }
 * </pre>
 *
 * <p>
 * Since class version 1.1, API version 1.3.0, this class extends InvalidValueException. Therefore, the method
 * <code>getValue()</code> now returns an <code>Object</code> (which contains an <code>Integer</code>-instance). In
 * order to retrieve the <code>int</code>-value directly, please use the method <code>getIntValue()</code>.</p>
 *
 * @author Volkmar Seifert &lt;vs@DimensionV.de&gt;
 *
 * @version 1.1
 * @since API 1.0.0
 */
public class InvalidEnumValueException extends InvalidValueException {

  private final int maximum;

  /**
   * Constructor that accepts the invalid value and the allowed maximum for the appropriate <code>enum</code>.
   *
   * @param value The invalid value.
   * @param maximum The allowed maximum for the <code>enum</code>.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public InvalidEnumValueException(int value, int maximum) {
    super(value);
    this.maximum = maximum;
  }

  /**
   * Returns the value that is said to be invalid explicitly as an integer primitive.
   *
   * @return the value the value handed over to the constructor.
   *
   * @since Class 1.1
   * @since API 1.3.0
   */
  public int getIntValue() {
    return (Integer) getValue();
  }

  /**
   * Returns the allowed maximum value.
   *
   * @return the value the allowed maximum value handed over to the constructor.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public int getMaximum() {
    return maximum;
  }
}

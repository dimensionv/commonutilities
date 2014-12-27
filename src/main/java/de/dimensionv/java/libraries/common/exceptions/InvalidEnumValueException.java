/*
 * Copyright (c) 2014, vseifert
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
 * @author vseifert
 */
public class InvalidEnumValueException extends RuntimeException {

  private final int value;
  private final int maximum;

  public InvalidEnumValueException(int value, int maximum) {
    this.value = value;
    this.maximum = maximum;
  }

  public int getValue() {
    return value;
  }

  public int getMaximum() {
    return maximum;
  }
}

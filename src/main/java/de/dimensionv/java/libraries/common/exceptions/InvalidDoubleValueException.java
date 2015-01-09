/*
 * Copyright (c) 2014, DimensionV, Volkmar Seifert
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
 * A runtime exception class for indicating that a provided double value was invalid.
 *
 * @author Volkmar Seifert &lt;vs@DimensionV.de&gt;
 *
 * @version 1.0
 * @since API 1.3.0
 */
@SuppressWarnings("serial")
public class InvalidDoubleValueException extends InvalidValueException {

  /**
   * Constructor that accepts the invalid value.
   *
   * @param value the invalid value
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public InvalidDoubleValueException(double value) {
    super(value);
  }

  /**
   * This constructors takes the invalid value plus a possible throwable that should be wrapped by this exception.
   *
   * @param value the invalid value.
   * @param throwable the Throwable (e.g. a non-runtime Exception) that should be wrapped inside this exception.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public InvalidDoubleValueException(double value, Throwable throwable) {
    super(value, throwable);
  }

  /**
   * Returns the value that is said to be invalid explicitly as a double primitive.
   *
   * @return the value the value handed over to the constructor.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public double getDoubleValue() {
    return (Double) getValue();
  }
}

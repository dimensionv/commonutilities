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
 * Generic runtime exception class for indication that a provided value was invalid. Please see the specific
 * Invalid&lt;Type&gt;ValueException classes for more specific exceptions.
 *
 * @author Volkmar Seifert &lt;vs@DimensionV.de&gt;
 *
 * @version 1.0
 * @since API 1.3.0
 */
@SuppressWarnings("serial")
public class InvalidValueException extends RuntimeException {

  private final Object value;

  /**
   * Constructor that accepts the invalid <code>value</code>.
   *
   * @param value the invalid value
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public InvalidValueException(Object value) {
    super();
    this.value = value;
  }

  /**
   * This constructors takes the invalid <code>value</code> plus a possible <code>throwable</code> that should be
   * wrapped by this exception.
   *
   * @param value the invalid value.
   * @param throwable the <code>Throwable</code> (e.g. a non-runtime Exception) that should be wrapped inside this
   * exception.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public InvalidValueException(Object value, Throwable throwable) {
    super(throwable);
    this.value = value;
  }

  /**
   * Returns the value that is said to be invalid.
   *
   * @return the value the value handed over to the constructor.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public Object getValue() {
    return value;
  }
}

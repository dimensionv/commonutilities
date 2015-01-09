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
package de.dimensionv.java.libraries.common.utilities.file.comparators;

import java.io.File;
import java.util.Comparator;

/**
 * A <code>Comparator</code> implementation to compare to <code>File</code>-objects by name.
 *
 * <p>
 * Both filenames are converted to lower case using the system's default <code>Locale</code> before they are actually
 * compared with each other.</p>
 *
 * @author Volkmar Seifert &lt;vs@DimensionV.de&gt;
 *
 * @version 1.0
 * @since API 1.3.0
 */
public class FileSizeComparator implements Comparator<File> {

  @Override
  public int compare(File fileA, File fileB) {
    long lenA = fileA.length();
    long lenB = fileB.length();
    return (lenA == lenB) ? 0 : ((lenA > lenB) ? 1 : -1);
  }
}

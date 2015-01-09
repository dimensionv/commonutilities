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
package de.dimensionv.java.libraries.common.utilities.file.filefilters;

import java.io.File;

/**
 * A FileFilter that allows directories only.
 * <p>
 * Depending on how it's initialized, it can also show hidden directories. The default behavior is to not show hidden
 * directories.</p>
 *
 * @author Volkmar Seifert &lt;vs@DimensionV.de&gt;
 *
 * @version 1.0
 * @since API 1.3.0
 */
public class FileFileFilter extends AbstractFileFilter {

  /**
   * Default constructor that initializes the DirectoryFileFilter with it's default behavior, meaning hidden directories
   * will not be shown.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public FileFileFilter() {
    this(false);
  }

  /**
   * <p>
   * Constructor that initializes the DirectoryFileFilter with the behavior defined by the parameter
   * <code>showHidden</code>.</p>
   * <ul>
   * <li><code>true</code> will enable the display of hidden directories.</li>
   * <li><code>false</code> will enable the default behavior of not showing hidden directories.</li>
   * </ul>
   *
   * @param includeHiddenDirectories Defines whether to show hidden directories or not.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public FileFileFilter(boolean includeHiddenDirectories) {
    super(includeHiddenDirectories);
  }

  @Override
  public boolean accept(File file) {
    if (showHidden) {
      return file.isFile();
    } else {
      return file.isFile() && isFileHidden(file);
    }
  }
}

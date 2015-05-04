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
 * A FileFilter that allows files only.
 * <p>
 * Depending on how it's initialized, it can also show hidden files. The default behavior is to not show hidden
 * files.</p>
 *
 * @author Volkmar Seifert &lt;vs@DimensionV.de&gt;
 *
 * @version 1.0
 * @since API 1.3.0
 */
public class FileFileFilter extends AbstractFileFilter {

  /**
   * Creates a new {@code FileFileFilter} object with the default behavior, meaning hidden files will not be shown.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public FileFileFilter() {
    this(false);
  }

  /**
   * <p>
   * Creates a new {@code FileFileFilter} object with the behavior defined by the parameter {@code showHidden}.</p>
   * <ul>
   * <li>{@code true} will enable the display of hidden files.</li>
   * <li>{@code false} will enable the default behavior of not showing hidden files.</li>
   * </ul>
   *
   * @param showHidden Defines whether to show hidden files or not.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public FileFileFilter(boolean showHidden) {
    super(showHidden);
  }

  /**
   * {@inheritDoc}
   *
   * @param file The {@code File} object to check
   * @return {@literal true} if the file matches the criteria (at least "{@link File#isFile()}"), {@literal false}
   * otherwise.
   */
  @Override
  public boolean accept(File file) {
    if (showHidden) {
      return file.isFile();
    } else {
      return file.isFile() && isFileHidden(file);
    }
  }
}

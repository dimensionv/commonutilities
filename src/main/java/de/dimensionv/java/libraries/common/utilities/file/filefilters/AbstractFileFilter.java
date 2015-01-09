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
import java.io.FileFilter;

/**
 * An abstract <code>FileFilter</code> which provides common basic functionality for actual filter implementations.
 *
 * <p>
 * Basic functionality means the capability to control whether to accept hidden files or directories in addition to the
 * non-hidden ones, as well as the actual check whether a <code>File</code>-object is hidden or not.</p>
 *
 * @author Volkmar Seifert &lt;vs@DimensionV.de&gt;
 *
 * @version 1.0
 * @since API 1.3.0
 */
public abstract class AbstractFileFilter implements FileFilter {

  private static final String HIDDEN_PREFIX = ".";

  /**
   * Flag that defines whether to show hidden files and directories or not.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  protected boolean showHidden;

  public AbstractFileFilter(boolean showHidden) {
    this.showHidden = showHidden;
  }

  @Override
  public abstract boolean accept(File file);

  /**
   * Check whether the file / directory is hidden or not.
   *
   * @param file The <code>File</code>-object to check.
   * @return <code>true</code> if hidden, <code>false</code> otherwise.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  protected boolean isFileHidden(File file) {
    return !(file.isHidden() && file.getName().startsWith(HIDDEN_PREFIX));
  }

  /**
   * Returns the current state of the <code>showHidden</code>-flag.
   *
   * @return the current state of the <code>showHidden</code>-flag.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public boolean isShowHidden() {
    return showHidden;
  }

  /**
   * <p>
   * Enables to switch the behavior of the DirectoryFileFilter instance regarding whether to show hidden directories or
   * not.</p>
   * <ul>
   * <li><code>true</code> will enable the display of hidden directories.</li>
   * <li><code>false</code> will enable the default behavior of not showing hidden directories.</li>
   * </ul>
   *
   * @param showHidden Defines whether to show hidden directories or not.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public void setShowHidden(boolean showHidden) {
    this.showHidden = showHidden;
  }
}

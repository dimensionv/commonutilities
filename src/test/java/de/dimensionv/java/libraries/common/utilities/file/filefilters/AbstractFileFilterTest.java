/*
 * Copyright (c) 2015, vseifert
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
import java.io.IOException;
import java.util.Locale;
import org.junit.Assert;

/**
 *
 * @author vseifert
 */
public abstract class AbstractFileFilterTest {

  protected static final double JAVA_VERSION = getVersion();
  protected static final String OS_NAME = getOperatingSystem();
  protected static final String OS_WINDOWS = "WINDOWS";

  protected static double getVersion() {
    String version = System.getProperty("java.version");
    int pos = version.indexOf('.');
    pos = version.indexOf('.', pos + 1);
    return Double.parseDouble(version.substring(0, pos));
  }

  protected static String getOperatingSystem() {
    return System.getProperty("os.name").toUpperCase(Locale.US);
  }
  protected AbstractFileFilter instance;
  protected File file;
  protected File hiddenFile;
  protected File directory;
  protected File hiddenDirectory;

  public AbstractFileFilterTest() {
  }

  private File createHiddenDirectory(String name, String tempPath) throws IOException, InterruptedException {
    File result;
    if (JAVA_VERSION > 1.6) {
      result = hideFile(java.nio.file.Files.createTempDirectory(name, new java.nio.file.attribute.FileAttribute<?>[0]));
    } else {
      result = new File(tempPath + File.separator + name);
      if (!result.mkdir()) {
        throw new IOException("Cannot create hidden directory " + result.getCanonicalPath());
      }
      hideFile(result);
    }
    return result;
  }

  private File createDirectory(String name, String tempPath) throws IOException, InterruptedException {
    File result;
    if (JAVA_VERSION > 1.6) {
      result = java.nio.file.Files.createTempDirectory(name, new java.nio.file.attribute.FileAttribute<?>[0]).toFile();
    } else {
      result = new File(tempPath + File.separator + name);
      if (!result.mkdir()) {
        throw new IOException("Cannot create hidden directory " + result.getCanonicalPath());
      }
    }
    return result;
  }

  private File createHiddenFile(String name) throws IOException, InterruptedException {
    File result;
    if (JAVA_VERSION > 1.6) {
      result = hideFile(java.nio.file.Files.createTempFile(name, ".tmp", new java.nio.file.attribute.FileAttribute<?>[0]));
    } else {
      result = hideFile(File.createTempFile(name, ".tmp"));
    }
    return result;
  }

  private File hideFile(java.nio.file.Path path) throws IOException {
    if (OS_NAME.endsWith(OS_WINDOWS)) {
      path = java.nio.file.Files.setAttribute(path, "dos:hidden", true);
    }
    return path.toFile();
  }

  private File hideFile(File file) throws IOException, InterruptedException {
    if (OS_NAME.endsWith(OS_WINDOWS)) {
      Process p = Runtime.getRuntime().exec("attrib +h " + file.getPath());
      p.waitFor(); // p.waitFor() important, so that the file really appears as
      // hidden immediately after function exit.
    }
    return file;
  }

  public void setUp() throws IOException, InterruptedException {
    file = File.createTempFile("DirectoryFileFilterTest-File", ".tmp");
    String absolutePath = file.getAbsolutePath();
    String tempPath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
    hiddenFile = createHiddenFile(".DirectoryFileFilterTest-File");
    directory = createDirectory("DirectoryFileFilterTest-Directory", tempPath);
    hiddenDirectory = createHiddenDirectory(".DirectoryFileFilterTest-Directory", tempPath);
  }

  public void tearDown() {
    instance = null;
    file.deleteOnExit();
    hiddenFile.deleteOnExit();
    directory.deleteOnExit();
    hiddenDirectory.deleteOnExit();
  }

  protected void checkAcceptWithoutHiddenFile(boolean expResult) {
    System.out.println("accept for file without hidden directories");
    instance.setShowHidden(false);
    boolean result = instance.accept(file);
    Assert.assertEquals(expResult, result);
  }

  protected void checkAcceptWithoutHiddenHiddenFile(boolean expResult) {
    System.out.println("accept for file without hidden directories");
    instance.setShowHidden(false);
    boolean result = instance.accept(hiddenFile);
    Assert.assertEquals(expResult, result);
  }

  protected void checkAcceptWithoutHiddenDirectory(boolean expResult) {
    System.out.println("accept for directory without hidden directories");
    instance.setShowHidden(false);
    boolean result = instance.accept(directory);
    Assert.assertEquals(expResult, result);
  }

  protected void checkAcceptWithoutHiddenHiddenDirectory(boolean expResult) {
    System.out.println("accept for hidden directory without hidden directories");
    instance.setShowHidden(false);
    boolean result = instance.accept(hiddenDirectory);
    Assert.assertEquals(expResult, result);
  }

  protected void checkAcceptWithHiddenFile(boolean expResult) {
    System.out.println("accept for file with hidden directories");
    instance.setShowHidden(true);
    boolean result = instance.accept(file);
    Assert.assertEquals(expResult, result);
  }

  protected void checkAcceptWithHiddenHiddenFile(boolean expResult) {
    System.out.println("accept for file without hidden directories");
    instance.setShowHidden(true);
    boolean result = instance.accept(hiddenFile);
    Assert.assertEquals(expResult, result);
  }

  protected void checkAcceptWithHiddenDirectory(boolean expResult) {
    System.out.println("accept for directory with hidden directories");
    instance.setShowHidden(true);
    boolean result = instance.accept(directory);
    Assert.assertEquals(expResult, result);
  }

  protected void checkAcceptWithHiddenHiddenDirectory(boolean expResult) {
    System.out.println("accept for hidden directory with hidden directories");
    instance.setShowHidden(true);
    boolean result = instance.accept(hiddenDirectory);
    Assert.assertEquals(expResult, result);
  }

}

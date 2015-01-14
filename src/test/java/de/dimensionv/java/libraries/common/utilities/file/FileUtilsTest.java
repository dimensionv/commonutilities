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
package de.dimensionv.java.libraries.common.utilities.file;

import de.dimensionv.java.libraries.common.exceptions.InvalidValueException;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author vseifert
 */
public class FileUtilsTest {

  public static final String EXTENSION = ".png";
  public static final String FILE_NAME_BASE = "test";
  public static final String FILE_NAME = FILE_NAME_BASE + EXTENSION;
  public static final String PATH_NAME = "/tmp";
  public static final String FILE_PATH = PATH_NAME + "/" + FILE_NAME;
  public static final String URI_STRING_FILE = "file://" + FILE_PATH;
  public static final String URI_STRING_FILE_EMPTY = "file://";
  public static final String URI_STRING_HTTPS_BASE = "https://www.dimensionv.de";
  public static final String URI_STRING_HTTPS = URI_STRING_HTTPS_BASE + "/" + FILE_NAME;
  public static final File PATH = new File(PATH_NAME);
  public static final File FILE = new File(FILE_PATH);
  public static final List<File> FILE_LIST = new ArrayList<File>(
      Arrays.asList(new File[]{
        new File("./src"), new File("./target"), new File("./assembly.xml"),
        new File("./LICENSE.md"), new File("./manifest.mf"),
        new File("./nb-configuration.xml"), new File("./pom.xml"),
        new File("./README.md")
      })
  );
  public URI uriFile;
  public URI uriFileEmpty;
  public URI uriHttps;
  public URI uriEmpty;

  public FileUtilsTest() {
  }

  @Before
  public void setUp() throws URISyntaxException {
    uriFile = new URI(URI_STRING_FILE);
    uriHttps = new URI(URI_STRING_HTTPS);
    uriEmpty = new URI("");
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of isLocal method, of class FileUtils.
   */
  @Test
  public void testIsLocalPositive() {
    System.out.println("isLocal positive");
    boolean expResult = true;
    boolean result = FileUtils.isLocal(URI_STRING_FILE);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of isLocal method, of class FileUtils.
   */
  @Test
  public void testIsLocalNegative() {
    System.out.println("isLocal negative");
    boolean expResult = false;
    boolean result = FileUtils.isLocal(URI_STRING_HTTPS);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of getExtension method, of class FileUtils.
   */
  @Test
  public void testGetExtension() {
    System.out.println("getExtension");
    String result = FileUtils.getExtension(URI_STRING_HTTPS);
    Assert.assertEquals(EXTENSION, result);
  }

  /**
   * Test of getExtension method, of class FileUtils.
   */
  @Test
  public void testGetExtensionEmpty() {
    System.out.println("getExtension");
    String result = FileUtils.getExtension(FILE_NAME_BASE);
    Assert.assertEquals("", result);
  }

  /**
   * Test of getPathWithoutFilename method, of class FileUtils.
   */
  @Test
  public void testGetPathWithoutFilename() {
    System.out.println("getPathWithoutFilename");
    File expResult = new File(PATH_NAME);
    File result = FileUtils.getPathWithoutFilename(FILE);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of getPathWithoutFilename method, of class FileUtils.
   */
  @Test
  public void testGetPathWithoutFilenameWithDirectory() {
    System.out.println("getPathWithoutFilename with directory");
    File result = FileUtils.getPathWithoutFilename(PATH);
    Assert.assertEquals(PATH, result);
  }

  /**
   * Test of getFile method, of class FileUtils.
   */
  @Test
  public void testGetFile_URI() {
    System.out.println("getFile");
    File result = FileUtils.getFile(uriFile);
    Assert.assertEquals(FILE, result);
  }

  /**
   * Test of getFile method, of class FileUtils.
   */
  @Test(expected = InvalidValueException.class)
  public void testGetFile_URI_InvalidScheme() {
    System.out.println("getFile with invalid scheme");
    File result = FileUtils.getFile(uriHttps);
  }

  /**
   * Test of getFile method, of class FileUtils.
   */
  @Test(expected = InvalidValueException.class)
  public void testGetFile_URI_InvalidPath() {
    System.out.println("getFile with empty URI");
    File result = FileUtils.getFile(uriEmpty);
  }

  /**
   * Test of getFile method, of class FileUtils.
   */
  @Test
  public void testGetFile_String_String() {
    System.out.println("getFile");
    File result = FileUtils.getFile(PATH_NAME, FILE_NAME);
    Assert.assertEquals(FILE, result);
  }

  /**
   * Test of getFile method, of class FileUtils.
   */
  @Test
  public void testGetFile_File_String() {
    System.out.println("getFile");
    File result = FileUtils.getFile(PATH, FILE_NAME);
    Assert.assertEquals(FILE, result);
  }

  /**
   * Test of getPath method, of class FileUtils.
   */
  @Test
  public void testGetPath() {
    System.out.println("getPath");
    String result = FileUtils.getPath(uriFile);
    Assert.assertEquals(FILE_PATH, result);
  }

  /**
   * Test of getReadableFileSize method, of class FileUtils.
   */
  @Test
  public void testGetReadableFileSizeByte() {
    System.out.println("getReadableFileSize");
    long size = 256;
    String expResult = "256 B";
    String result = FileUtils.getReadableFileSize(size);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of getReadableFileSize method, of class FileUtils.
   */
  @Test
  public void testGetReadableFileSizeKiloByte() {
    System.out.println("getReadableFileSize");
    long size = ((long) (1024 * 1.5d));
    String expResult = "1.5 KB";
    String result = FileUtils.getReadableFileSize(size);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of getReadableFileSize method, of class FileUtils.
   */
  @Test
  public void testGetReadableFileSizeMegaByte() {
    System.out.println("getReadableFileSize");
    long size = ((long) ((1024 * 1024) * 1.5d));
    String expResult = "1.5 MB";
    String result = FileUtils.getReadableFileSize(size);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of getReadableFileSize method, of class FileUtils.
   */
  @Test
  public void testGetReadableFileSizeGigaByte() {
    System.out.println("getReadableFileSize");
    long size = ((long) ((1024 * 1024 * 1024) * 1.5d));
    String expResult = "1.5 GB";
    String result = FileUtils.getReadableFileSize(size);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of getFileList method, of class FileUtils.
   */
  @Test
  public void testGetFileList() {
    System.out.println("getFileList");
    String path = ".";
    boolean includeHidden = false;
    List<File> result = FileUtils.getFileList(path, includeHidden);
    Assert.assertEquals(FILE_LIST, result);
  }

}

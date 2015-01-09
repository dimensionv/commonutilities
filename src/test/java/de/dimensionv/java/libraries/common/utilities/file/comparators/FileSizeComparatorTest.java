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
package de.dimensionv.java.libraries.common.utilities.file.comparators;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author vseifert
 */
public class FileSizeComparatorTest {

  private static final String CONTENT = "The quick brown fox jumps over the lazy dog.";

  File testFileA;
  File testFileB;

  public FileSizeComparatorTest() {
  }

  @Before
  public void setUp() throws IOException {
    testFileA = File.createTempFile("FileSizeComparatorTest-File-A", ".tmp");
    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(testFileA)));
    dos.writeChars(CONTENT);
    dos.close();

    testFileB = File.createTempFile("FileSizeComparatorTest-File-B", ".tmp");
    dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(testFileA)));
    dos.writeChars(CONTENT.substring(0, 19));
    dos.close();
  }

  @After
  public void tearDown() {
    testFileA.deleteOnExit();
    testFileB.deleteOnExit();
  }

  /**
   * Test of compare method, of class FileSizeComparator.
   */
  @Test
  public void testCompareSmaller() {
    System.out.println("compare");
    FileSizeComparator instance = new FileSizeComparator();
    int expResult = -1;
    int result = instance.compare(testFileB, testFileA);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of compare method, of class FileSizeComparator.
   */
  @Test
  public void testCompareEqual() {
    System.out.println("compare");
    FileSizeComparator instance = new FileSizeComparator();
    int expResult = -1;
    int result = instance.compare(testFileB, testFileA);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of compare method, of class FileSizeComparator.
   */
  @Test
  public void testCompareBigger() {
    System.out.println("compare");
    FileSizeComparator instance = new FileSizeComparator();
    int expResult = 1;
    int result = instance.compare(testFileA, testFileB);
    Assert.assertEquals(expResult, result);
  }

}

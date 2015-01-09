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

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author vseifert
 */
public class FileNameComparatorTest {

  File testFileA;
  File testFileB;

  public FileNameComparatorTest() {
  }

  @Before
  public void setUp() throws IOException {
    testFileA = File.createTempFile("FileNameComparatorTest-File-A", ".tmp");
    testFileB = File.createTempFile("FileNameComparatorTest-File-B", ".tmp");
  }

  @After
  public void tearDown() {
    testFileA.deleteOnExit();
    testFileB.deleteOnExit();
  }

  /**
   * Test of compare method, of class FileNameComparator.
   */
  @Test
  public void testCompareLess() {
    System.out.println("compare");
    FileNameComparator instance = new FileNameComparator();
    int expResult = -1;
    int result = instance.compare(testFileA, testFileB);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of compare method, of class FileNameComparator.
   */
  @Test
  public void testCompareEqual() {
    System.out.println("compare");
    FileNameComparator instance = new FileNameComparator();
    int expResult = 0;
    int result = instance.compare(testFileA, testFileA);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of compare method, of class FileNameComparator.
   */
  @Test
  public void testCompareGreater() {
    System.out.println("compare");
    FileNameComparator instance = new FileNameComparator();
    int expResult = 1;
    int result = instance.compare(testFileB, testFileA);
    Assert.assertEquals(expResult, result);
  }
}

/*
 * Copyright (c) 2014, Volkmar Seifert, Dimension V
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
package de.dimensionv.java.common.utilities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author vseifert
 */
public class StringUtilsTest {

  private static final String TEST_STRING = "The quick brown fox jumps over the lazy dog";

  public StringUtilsTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Negative test of isEmpty method against non-empty String, of class StringUtils.
   */
  @org.junit.Test
  public void testIsEmptyNegative() {
    System.out.println("isEmpty");
    boolean expResult = false;
    boolean result = StringUtils.isEmpty(TEST_STRING);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Positive test of isEmpty method against null, of class StringUtils.
   */
  @org.junit.Test
  public void testIsEmptyPositiveNull() {
    System.out.println("isEmpty");
    boolean expResult = true;
    boolean result = StringUtils.isEmpty(null);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Positive test of isEmpty method against a String of spaces, of class StringUtils.
   */
  @org.junit.Test
  public void testIsEmptyPositiveSpaces() {
    System.out.println("isEmpty");
    String text = " \t\n\r";
    boolean expResult = true;
    boolean result = StringUtils.isEmpty(text);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of bytesToHex method, of class StringUtils.
   */
  @org.junit.Test
  public void testBytesToHex() {
    System.out.println("bytesToHex");
    byte[] bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 0x3A, 0x3D, 0x3F};
    String expResult = "0102030405060708090A0B0C0D0E0F103A3D3F";
    String result = StringUtils.bytesToHex(bytes);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of sha1Hash method, of class StringUtils.
   */
  @org.junit.Test
  public void testSha1Hash() throws Exception {
    System.out.println("sha1Hash");
    String expResult = "2fd4e1c67a2d28fced849ee1bb76e7391b93eb12";
    String result = StringUtils.sha1Hash(TEST_STRING);
    Assert.assertEquals(expResult.toUpperCase(), result.toUpperCase());
  }

  /**
   * Test of md5Hash method, of class StringUtils.
   */
  @org.junit.Test
  public void testMd5Hash() throws Exception {
    System.out.println("md5Hash");
    String expResult = "9e107d9d372bb6826bd81d3542a419d6";
    String result = StringUtils.md5Hash(TEST_STRING);
    Assert.assertEquals(expResult.toUpperCase(), result.toUpperCase());
  }

}

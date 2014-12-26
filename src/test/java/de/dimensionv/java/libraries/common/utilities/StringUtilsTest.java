////////////////////////////////////////////////////////////////////////////////
// $Id: StringUtilsTest.java,v 1.2 2014/12/26 23:29:26 mjoellnir Exp $
//
// Author: Volkmar Seifert
// Description:
// Unit-tests for de.dimensionv.java.libraries.common.utilities.StringUtils
//
// In order to keep the StringUtils class in a functional state as bug-free as
// possible, this class supports in testing the actual StringUtils class by
// unit-testing each function of the class.
////////////////////////////////////////////////////////////////////////////////

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
package de.dimensionv.java.libraries.common.utilities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author vseifert
 */
public class StringUtilsTest {

  private static final String TEST_STRING = "The quick brown fox jumps over the lazy dog";

  private static final String TEST_HASH_SHA1 = "2fd4e1c67a2d28fced849ee1bb76e7391b93eb12";
  private static final byte[] TEST_HASH_SHA1_BYTES = new byte[]{
    0x2f, (byte) 0xd4, (byte) 0xe1, (byte) 0xc6, 0x7a, 0x2d, 0x28, (byte) 0xfc, (byte) 0xed, (byte) 0x84,
    (byte) 0x9e, (byte) 0xe1, (byte) 0xbb, 0x76, (byte) 0xe7, 0x39, 0x1b, (byte) 0x93, (byte) 0xeb, 0x12
  };
  private static final int TEST_HASH_SHA1_INT = 2147418157;
  private static final long TEST_HASH_SHA1_LONG = 72057589742960892l;

  private static final String TEST_HASH_MD5 = "9e107d9d372bb6826bd81d3542a419d6";
  private static final byte[] TEST_HASH_MD5_BYTES = new byte[]{
    (byte) 0x9e, 0x10, 0x7d, (byte) 0x9d, 0x37, 0x2b, (byte) 0xb6, (byte) 0x82, 0x6b, (byte) 0xd8, 0x1d,
    0x35, 0x42, (byte) 0xa4, 0x19, (byte) 0xd6
  };
  private static final int TEST_HASH_MD5_INT = 2147418328;
  private static final long TEST_HASH_MD5_LONG = 281470698455093l;

  private static final byte[] TEST_BYTES = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 0x3A, 0x3D, 0x3F};
  private static final String TEST_HEX = "0102030405060708090A0B0C0D0E0F103A3D3F";

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
    String result = StringUtils.bytesToHex(TEST_BYTES);
    Assert.assertEquals(TEST_HEX, result);
  }

  /**
   * Test of hexToBytes method, of class StringUtils.
   */
  @Test
  public void testHexToBytes() {
    System.out.println("hexToBytes");
    byte[] result = StringUtils.hexToBytes(TEST_HEX);
    Assert.assertArrayEquals(TEST_BYTES, result);
  }

  /**
   * Test of sha1Hash method, of class StringUtils.
   */
  @org.junit.Test
  public void testSha1Hash() throws Exception {
    System.out.println("sha1Hash");
    String result = StringUtils.sha1Hash(TEST_STRING);
    Assert.assertEquals(TEST_HASH_SHA1.toUpperCase(), result.toUpperCase());
  }

  /**
   * Test of md5Hash method, of class StringUtils.
   */
  @org.junit.Test
  public void testMd5Hash() throws Exception {
    System.out.println("md5Hash");
    String result = StringUtils.md5Hash(TEST_STRING);
    Assert.assertEquals(TEST_HASH_MD5.toUpperCase(), result.toUpperCase());
  }

  /**
   * Test of sha1HashInt method, of class StringUtils.
   */
  @Test
  public void testSha1HashInt() throws Exception {
    System.out.println("sha1HashInt");
    int result = StringUtils.sha1HashInt(TEST_STRING);
    Assert.assertEquals(TEST_HASH_SHA1_INT, result);
  }

  /**
   * Test of sha1HashLong method, of class StringUtils.
   */
  @Test
  public void testSha1HashLong() throws Exception {
    System.out.println("sha1HashLong");
    long result = StringUtils.sha1HashLong(TEST_STRING);
    Assert.assertEquals(TEST_HASH_SHA1_LONG, result);
  }

  /**
   * Test of md5HashInt method, of class StringUtils.
   */
  @Test
  public void testMd5HashInt() throws Exception {
    System.out.println("md5HashInt");
    int result = StringUtils.md5HashInt(TEST_STRING);
    Assert.assertEquals(TEST_HASH_MD5_INT, result);
  }

  /**
   * Test of md5HashLong method, of class StringUtils.
   */
  @Test
  public void testMd5HashLong() throws Exception {
    System.out.println("md5HashLong");
    long result = StringUtils.md5HashLong(TEST_STRING);
    Assert.assertEquals(TEST_HASH_MD5_LONG, result);
  }

  /**
   * Test of truncateHashToInt method, of class StringUtils.
   */
  @Test
  public void testTruncateHashToInt_String_SHA1() {
    System.out.println("truncateHashToInt (SHA1/String)");
    int result = StringUtils.truncateHashToInt(TEST_HASH_SHA1);
    Assert.assertEquals(TEST_HASH_SHA1_INT, result);
  }

  /**
   * Test of truncateHashToInt method, of class StringUtils.
   */
  @Test
  public void testTruncateHashToInt_String_MD5() {
    System.out.println("truncateHashToInt (MD5/String)");
    int result = StringUtils.truncateHashToInt(TEST_HASH_MD5);
    Assert.assertEquals(TEST_HASH_MD5_INT, result);
  }

  /**
   * Test of truncateHashToInt method, of class StringUtils.
   */
  @Test
  public void testTruncateHashToInt_byteArr_SHA1() {
    System.out.println("truncateHashToInt (SHA1/bytes)");
    int result = StringUtils.truncateHashToInt(TEST_HASH_SHA1_BYTES);
    Assert.assertEquals(TEST_HASH_SHA1_INT, result);
  }

  /**
   * Test of truncateHashToInt method, of class StringUtils.
   */
  @Test
  public void testTruncateHashToInt_byteArr_MD5() {
    System.out.println("truncateHashToInt (MD5/bytes)");
    int result = StringUtils.truncateHashToInt(TEST_HASH_MD5_BYTES);
    Assert.assertEquals(TEST_HASH_MD5_INT, result);
  }

  /**
   * Test of truncateHashToLong method, of class StringUtils.
   */
  @Test
  public void testTruncateHashToLong_String_SHA1() {
    System.out.println("truncateHashToLong (SHA1/String)");
    long result = StringUtils.truncateHashToLong(TEST_HASH_SHA1);
    Assert.assertEquals(TEST_HASH_SHA1_LONG, result);
  }

  /**
   * Test of truncateHashToLong method, of class StringUtils.
   */
  @Test
  public void testTruncateHashToLong_String_MD5() {
    System.out.println("truncateHashToLong (MD5/String)");
    long result = StringUtils.truncateHashToLong(TEST_HASH_MD5);
    Assert.assertEquals(TEST_HASH_MD5_LONG, result);
  }

  /**
   * Test of truncateHashToLong method, of class StringUtils.
   */
  @Test
  public void testTruncateHashToLong_byteArr_SHA1() {
    System.out.println("truncateHashToLong (SHA1/bytes)");
    long result = StringUtils.truncateHashToLong(TEST_HASH_SHA1_BYTES);
    Assert.assertEquals(TEST_HASH_SHA1_LONG, result);
  }

  /**
   * Test of truncateHashToLong method, of class StringUtils.
   */
  @Test
  public void testTruncateHashToLong_byteArr_MD5() {
    System.out.println("truncateHashToLong (MD5/bytes)");
    long result = StringUtils.truncateHashToLong(TEST_HASH_MD5_BYTES);
    Assert.assertEquals(TEST_HASH_MD5_LONG, result);
  }

  /**
   * Test of shorten method, of class StringUtils.
   */
  @Test
  public void testShorten_Start() {
    System.out.println("shorten @start");
    String expResult = "...the lazy dog";
    int size = expResult.length();
    String result = StringUtils.shorten(TEST_STRING, size, StringUtils.SHORTEN_START);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of shorten method, of class StringUtils.
   */
  @Test
  public void testShorten_Middle() {
    System.out.println("shorten @middle");
    String expResult = "The quick...the lazy dog";
    int size = expResult.length();
    String result = StringUtils.shorten(TEST_STRING, size, StringUtils.SHORTEN_MIDDLE);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of shorten method, of class StringUtils.
   */
  @Test
  public void testShorten_End() {
    System.out.println("shorten @end");
    String expResult = "The quick brown fox...";
    int size = expResult.length();
    String result = StringUtils.shorten(TEST_STRING, size, StringUtils.SHORTEN_END);
    Assert.assertEquals(expResult, result);
  }
}

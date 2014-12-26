////////////////////////////////////////////////////////////////////////////////
// $Id: StringUtils.java,v 1.2 2013/03/17 09:37:26 mjoellnir Exp $
//
// Author: Volkmar Seifert
// Description:
// Utility class for common string-related operations.
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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a utility class for common string-related operations. Many of these methods are thus available statically.
 *
 * @author Mjoellnir <vs@dimensionv.de>
 */
public class StringUtils {

  private static final Pattern spacePattern = Pattern.compile("\\A\\s*\\z");

  private static final String HASH_SHA1 = "SHA-1";
  private static final String HASH_MD5 = "MD5";

  public static final int SHORTEN_START = 1;
  public static final int SHORTEN_MIDDLE = 2;
  public static final int SHORTEN_END = 3;

  private static final String SHORTEN_STRING = "...";

  /**
   * Universal check on String-objects that even works on non-instantiated variables (null-references), as it checks for
   * null first. If a string-reference is null, it is considered to be empty, the same goes for string-objects that only
   * contain blank characters like tabs, spaces, carriage returns and line-feeds - and any other character that falls
   * under the \s regular expression class.
   *
   * @param text The string-object to be checked
   * @return true if the given string-object is empty according to the rules described above.
   */
  public static boolean isEmpty(String text) {
    if (text == null) {
      return true;
    }

    text = text.trim();
    if (text.length() == 0) {
      return true;
    }

    Matcher m = spacePattern.matcher(text);
    return m.matches();
  }

  /**
   * Convert an array of arbitrary bytes into a String of hexadecimal number-pairs with each pair representing on byte
   * of the array.
   *
   * @param bytes the array to convert into hexadecimal string
   * @return the String containing the hexadecimal representation of the array
   */
  public static String bytesToHex(byte[] bytes) {
    final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
      'A', 'B', 'C', 'D', 'E', 'F'};
    char[] hexChars = new char[bytes.length << 1];
    for (int i = 0; i < bytes.length; i++) {
      int value = bytes[i] & 0xFF;
      int baseIndex = i << 1;
      hexChars[baseIndex] = hexArray[value >>> 4];
      hexChars[baseIndex + 1] = hexArray[value & 0x0F];
    }
    return new String(hexChars);
  }

  /**
   * Convert a string consisting of hex-numbers into an array of bytes, which contains the binary representation of
   * those hexadecimal-numbers (takes pairs of numbers, so make sure the number of characters is even).
   *
   * @param hex String of pairs of hexadecimal numbers
   * @return byte-array with the binary representation of the hex-string
   */
  public static byte[] hexToBytes(String hex) {
    byte[] bytes = new byte[hex.length() >> 1];
    for (int i = 0; i < bytes.length; i++) {
      int baseIndex = i << 1;
      // in order to be able to parse the full range of 0x00 to 0xFF, we need a Short or Integer
      // to do the parsing, as Byte will throw an exception for values above or equal to 0x80.
      bytes[i] = (byte) Integer.parseInt(hex.substring(baseIndex, baseIndex + 2), 16);
    }
    return bytes;
  }

  /**
   * Compute the SHA-1 hash of a string, and return it has a string of hexadecimal numbers. The incoming text is assumed
   * to be UTF-8 encoded. An exception will be thrown if this encoding is not supported.
   *
   * @param text Text to compute the SHA-1 hash of
   * @return the hexadecimal representation of the hash
   * @throws NoSuchAlgorithmException thrown if the SHA-1 algorithm cannot be found
   * @throws UnsupportedEncodingException thrown if the encoding is not supported
   */
  public static String sha1Hash(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    return bytesToHex(computeHash(text, HASH_SHA1));
  }

  /**
   * Compute the SHA-1 hash of a string, and return a truncated int-value of the hash. The incoming text is assumed to
   * be UTF-8 encoded. An exception will be thrown if this encoding is not supported.
   *
   * @param text Text to compute the SHA-1 hash of
   * @return a truncated int-value of the hash.
   * @throws NoSuchAlgorithmException thrown if the SHA-1 algorithm cannot be found
   * @throws UnsupportedEncodingException thrown if the encoding is not supported
   */
  public static int sha1HashInt(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    return truncateHashToInt(computeHash(text, HASH_SHA1));
  }

  /**
   * Compute the SHA-1 hash of a string, and return a truncated long-value of the hash. The incoming text is assumed to
   * be UTF-8 encoded. An exception will be thrown if this encoding is not supported.
   *
   * @param text Text to compute the SHA-1 hash of
   * @return a truncated long-value of the hash.
   * @throws NoSuchAlgorithmException thrown if the SHA-1 algorithm cannot be found
   * @throws UnsupportedEncodingException thrown if the encoding is not supported
   */
  public static long sha1HashLong(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    return truncateHashToLong(computeHash(text, HASH_SHA1));
  }

  /**
   * Compute the MD-5 hash of a string, and return it has a string of hexadecimal numbers. The incoming text is assumed
   * to be UTF-8 encoded. An exception will be thrown if this encoding is not supported.
   *
   * @param text Text to compute the MD-5 hash of
   * @return the hexadecimal representation of the hash
   * @throws NoSuchAlgorithmException thrown if the MD-5 algorithm cannot be found
   * @throws UnsupportedEncodingException thrown if the encoding is not supported
   */
  public static String md5Hash(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    return bytesToHex(computeHash(text, HASH_MD5));
  }

  /**
   * Compute the MD-5 hash of a string, and return a truncated int-value of the hash. The incoming text is assumed to be
   * UTF-8 encoded. An exception will be thrown if this encoding is not supported.
   *
   * @param text Text to compute the MD-5 hash of
   * @return a truncated int-value of the hash.
   * @throws NoSuchAlgorithmException thrown if the MD-5 algorithm cannot be found
   * @throws UnsupportedEncodingException thrown if the encoding is not supported
   */
  public static int md5HashInt(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    return truncateHashToInt(computeHash(text, HASH_MD5));
  }

  /**
   * Compute the MD-5 hash of a string, and return a truncated long-value of the hash. The incoming text is assumed to
   * be UTF-8 encoded. An exception will be thrown if this encoding is not supported.
   *
   * @param text Text to compute the MD-5 hash of
   * @return a truncated long-value of the hash.
   * @throws NoSuchAlgorithmException thrown if the MD-5 algorithm cannot be found
   * @throws UnsupportedEncodingException thrown if the encoding is not supported
   */
  public static long md5HashLong(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    return truncateHashToLong(computeHash(text, HASH_MD5));
  }

  /**
   * Computes a truncated code from the given hash-value and returns it as int-variable.
   *
   * @param hash the hash-string
   * @return truncated code as int
   */
  public static int truncateHashToInt(String hash) {
    return truncateHashToInt(hexToBytes(hash));
  }

  /**
   * Computes a truncated code from the given hash-value and returns it as int-variable.
   *
   * @param bytes hash-value as a byte-array
   * @return truncated code as int
   */
  public static int truncateHashToInt(byte[] bytes) {
    int offset = bytes[bytes.length - 1] & 0x0f;
    return (bytes[offset] & (0x7f << 24))
        | (bytes[offset + 1] & (0xff << 16))
        | (bytes[offset + 2] & (0xff << 8))
        | (bytes[offset + 3] & 0xff);
  }

  /**
   * Computes a truncated code from the given hash-value and returns it as long-variable.
   *
   * @param hash the hash-string
   * @return truncated code as long
   */
  public static long truncateHashToLong(String hash) {
    return truncateHashToLong(hexToBytes(hash));
  }

  /**
   * Computes a truncated code from the given hash-value and returns it as long-variable.
   *
   * @param bytes the hash-value as a byte-array
   * @return truncated code as long
   */
  public static long truncateHashToLong(byte[] bytes) {
    int offset = bytes[bytes.length - 1] & 0x0c;
    return (((long) bytes[offset]) & (0x7fl << 56))
        | (((long) bytes[offset + 1]) & (0xffl << 48))
        | (((long) bytes[offset + 2]) & (0xffl << 40))
        | (((long) bytes[offset + 3]) & (0xffl << 32))
        | (((long) bytes[offset + 4]) & (0xffl << 24))
        | (((long) bytes[offset + 5]) & (0xffl << 16))
        | (((long) bytes[offset + 6]) & (0xffl << 8))
        | (((long) bytes[offset + 7]) & 0xffl);
  }

  private static byte[] computeHash(String text, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest md = MessageDigest.getInstance(algorithm);
    md.update(text.getBytes("UTF-8"), 0, text.length());
    return md.digest();
  }

  /**
   * Shortens a given String "text" down to size length, indicating the shortening by three dots ("..."). Mode
   * determines the position of the dots and where the text will be cut off.
   *
   * @param text The string to shorten
   * @param size The size the text is to shortened to.
   * @param mode The mode in which the string shall be shortened
   * @return The shortened string.
   */
  public static String shorten(String text, int size, int mode) {
    StringBuilder temp = null;
    size = Math.min(text.length(), size);
    switch (mode) {
      case SHORTEN_START: {
        int length = size - 3;
        temp = new StringBuilder(SHORTEN_STRING);
        temp.append(text.substring(text.length() - length));
        break;
      }
      case SHORTEN_MIDDLE: {
        int length = size >> 1;
        temp = new StringBuilder(text.substring(0, length - 3));
        temp.append(SHORTEN_STRING);
        temp.append(text.substring(text.length() - length));
        break;
      }
      case SHORTEN_END: {
        int length = size - 3;
        temp = new StringBuilder(text.substring(0, length));
        temp.append(SHORTEN_STRING);
        break;
      }
    }
    return temp.toString();
  }
}

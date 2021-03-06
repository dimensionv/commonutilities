////////////////////////////////////////////////////////////////////////////////
// $Id: StringUtils.java,v 1.2 2013/03/17 09:37:26 mjoellnir Exp $
//
// Author: Volkmar Seifert
// Description:
// Utility class for common string-related operations.
////////////////////////////////////////////////////////////////////////////////

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
package de.dimensionv.java.libraries.common.utilities.strings;

import de.dimensionv.java.libraries.common.exceptions.InvalidIntegerValueException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a utility class for common string-related operations. Many of these methods are thus available statically.
 *
 * @author Volkmar Seifert &lt;vs@DimensionV.de&gt;
 *
 * @version 1.4
 * @since API 1.0.0
 */
public class StringUtils {

  private static final Pattern spacePattern = Pattern.compile("\\A\\s*\\z");

  private static final String HASH_SHA1 = "SHA-1";
  private static final String HASH_MD5 = "MD5";

  private static final String SHORTEN_STRING = "...";

  public static final int SHORTEN_START = 1;
  public static final int SHORTEN_MIDDLE = 2;
  public static final int SHORTEN_END = 3;

  public static final String EMPTY_STRING = "";
  public static final String[] EMPTY_STRING_ARRAY = new String[0];

  private StringUtils() {
    // this ensures that the class cannot be instantiated...
  }

  /**
   * Universal check on String-objects that even works on non-instantiated variables (null-references), as it checks for
   * null first. If a string-reference is null, it is considered to be empty, the same goes for string-objects that only
   * contain blank characters like tabs, spaces, carriage returns and line-feeds - and any other character that falls
   * under the \s regular expression class.
   *
   * @param text The string-object to be checked
   * @return true if the given string-object is empty according to the rules described above.
   *
   * @since Class 1.0
   * @since API 1.0.0
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
   *
   * @since Class 1.2
   * @since API 1.2.0
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
   *
   * @since Class 1.2
   * @since API 1.2.0
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
   *
   * @since Class 1.2
   * @since API 1.2.0
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
   *
   * @since Class 1.2
   * @since API 1.2.0
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
   *
   * @since Class 1.2
   * @since API 1.2.0
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
   *
   * @since Class 1.2
   * @since API 1.2.0
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
   *
   * @since Class 1.2
   * @since API 1.2.0
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
   *
   * @since Class 1.2
   * @since API 1.2.0
   */
  public static long md5HashLong(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    return truncateHashToLong(computeHash(text, HASH_MD5));
  }

  /**
   * Computes a truncated code from the given hash-value and returns it as int-variable.
   *
   * @param hash the hash-string
   * @return truncated code as int
   *
   * @since Class 1.2
   * @since API 1.2.0
   */
  public static int truncateHashToInt(String hash) {
    return truncateHashToInt(hexToBytes(hash));
  }

  /**
   * Computes a truncated code from the given hash-value and returns it as int-variable.
   *
   * @param bytes hash-value as a byte-array
   * @return truncated code as int
   *
   * @since Class 1.2
   * @since API 1.2.0
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
   *
   * @since Class 1.2
   * @since API 1.2.0
   */
  public static long truncateHashToLong(String hash) {
    return truncateHashToLong(hexToBytes(hash));
  }

  /**
   * Computes a truncated code from the given hash-value and returns it as long-variable.
   *
   * @param bytes the hash-value as a byte-array
   * @return truncated code as long
   *
   * @since Class 1.2
   * @since API 1.2.0
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
   *
   * @since Class 1.2
   * @since API 1.2.0
   */
  public static String shorten(final String text, final int size, final int mode) {
    StringBuilder temp = null;
    final int shortLength = SHORTEN_STRING.length();
    final int effectiveSize = Math.min(text.length(), size);
    switch (mode) {
      case SHORTEN_START: {
        int length = effectiveSize - shortLength;
        temp = new StringBuilder(SHORTEN_STRING);
        temp.append(text.substring(text.length() - length));
        break;
      }
      case SHORTEN_MIDDLE: {
        int length = effectiveSize >> 1;
        temp = new StringBuilder(text.substring(0, length - shortLength));
        temp.append(SHORTEN_STRING);
        temp.append(text.substring(text.length() - length));
        break;
      }
      case SHORTEN_END: {
        int length = effectiveSize - shortLength;
        temp = new StringBuilder(text.substring(0, length));
        temp.append(SHORTEN_STRING);
        break;
      }
      default: {
        throw new InvalidIntegerValueException(mode);
      }
    }
    return temp.toString();
  }

  /**
   * Counts the number of occurrences of the given character.
   *
   * @param haystack The string in which to search for the given character.
   * @param needle The character to search for.
   * @return The total number of occurrences of the given character.
   *
   * @since Class 1.3
   * @since API 1.4.0
   */
  public static int countOccurrences(String haystack, char needle) {
    int result = 0;
    int index = haystack.indexOf(needle, 0);
    int length = haystack.length();
    while (index > -1) {
      result++;
      int startFrom = index + 1;
      index = (startFrom < length) ? haystack.indexOf(needle, startFrom) : -1;
    }
    return result;
  }

  /**
   * Counts the number of occurrences of the given character.
   *
   * @param haystack The string in which to search for the given character.
   * @param needle The character to search for.
   * @return The total number of occurrences of the given character.
   *
   * @since Class 1.3
   * @since API 1.4.0
   */
  public static int countOccurrences(String haystack, String needle) {
    int result = 0;
    int index = haystack.indexOf(needle, 0);
    int haystackLength = haystack.length();
    int needleLength = needle.length();
    while (index > -1) {
      result++;
      int startFrom = index + needleLength;
      index = (startFrom < haystackLength) ? haystack.indexOf(needle, startFrom) : -1;
    }
    return result;
  }

  /**
   * <p>
   * Performs a simple splitting of the given {@link String} {@code string} at the given {@code delimiter}. Opposite to
   * the method {@link String#split(java.lang.String)}, this method does not use regular expressions, but a simple
   * character matching algorithm. That means, this method is much faster, though less flexible regarding the
   * delimiters.</p>
   * <p>
   * This {@link #simpleSplit(java.lang.String, char)} method always starts a the beginning of the given
   * {@code string}.</p>
   *
   * @param string The {@link String} to split.
   * @param delimiter The {@code char} at which the given {@code string} is split up.
   * @return Array of {@link String}s
   *
   * @since Class 1.4, API 2.1.0
   */
  public static String[] simpleSplit(String string, char delimiter) {
    return simpleSplit(string, delimiter, 0);
  }

  /**
   * <p>
   * Performs a simple splitting of the given {@link String} {@code string} at the given {@code delimiter}. Opposite to
   * the method {@link String#split(java.lang.String)}, this method does not use regular expressions, but a simple
   * character matching algorithm. That means, this method is much faster, though less flexible regarding the
   * delimiters.</p>
   * <p>
   * This {@link #simpleSplit(java.lang.String, char, int) } method starts at the given {@code startOffset} of the given
   * {@code string}, which means it can be any position within the string.</p>
   *
   * @param string The {@link String} to split.
   * @param delimiter The {@code char} at which the given {@code string} is split up.
   * @param startOffset The {@code offset} to start looking for the {@code delimiter} from within {@code string}.
   * @return Array of {@link String}s
   *
   * @since Class 1.4, API 2.1.0
   */
  public static String[] simpleSplit(String string, char delimiter, int startOffset) {
    int max = StringUtils.countOccurrences(string, delimiter) + 1;
    String[] strings = new String[max];

    if (max == 1) {
      // no delimiter found, fill strings array with given string and return
      strings[0] = string;
      return strings;
    }

    int count = 0;
    int offset = startOffset;
    int colonPos;
    int length = string.length();
    while (((colonPos = string.indexOf(delimiter, offset)) > -1) && (count < max) && (offset < length)) {
      String item = string.substring(offset, colonPos);
      if (!item.isEmpty()) {
        strings[count++] = item;
      }
      offset = colonPos + 1;
    }

    if (count < max) {
      String[] tmp = new String[count];
      System.arraycopy(strings, 0, tmp, 0, count);
      strings = tmp;
    }

    return strings;
  }
}

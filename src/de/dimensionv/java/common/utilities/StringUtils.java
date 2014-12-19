////////////////////////////////////////////////////////////////////////////////
// $Id: StringUtils.java,v 1.2 2013/03/17 09:37:26 mjoellnir Exp $
//
// Author: Volkmar Seifert
// Description:
// Utility class for common string-related operations.
//
////////////////////////////////////////////////////////////////////////////////
package de.dimensionv.java.common.utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * This is a utility class for common string-related operations. Many of these methods are thus available statically.
 *
 * @author Mjoellnir <vs@dimensionv.de>
 */
public class StringUtils {

  private static final Pattern spacePattern = Pattern.compile("\\A\\s*\\z");

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
    if (text.isEmpty()) {
      return true;
    }

    // trim only removes this ' ' whitespace, therefore tabs and other \s characters
    // might actually still be the only content of the string.
    return spacePattern.matcher(text).matches();
  }

  /**
   * Convert an array of arbitrary bytes into a String of hexadecimal number-pairs with each pair representing on byte
   * of the array.
   *
   * @param bytes the array to convert into hexadecimal string
   * @return the String containing the hexadecimal representation of the array
   */
  public static String bytesToHex(byte[] bytes) {
    final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    char[] hexChars = new char[bytes.length << 1];
    for (int i = 0; i < bytes.length; i++) {
      int value = bytes[i] & 0xFF;
      int hexIndex = i << 1;
      hexChars[hexIndex] = hexArray[value >>> 4];
      hexChars[hexIndex + 1] = hexArray[value & 0x0F];
    }
    return new String(hexChars);
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
    MessageDigest md = MessageDigest.getInstance("SHA-1");
    md.update(text.getBytes("UTF-8"), 0, text.length());
    return bytesToHex(md.digest());
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
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(text.getBytes("UTF-8"), 0, text.length());
    return bytesToHex(md.digest());
  }
}

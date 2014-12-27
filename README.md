# CommonUtilities

## Synopsis
CommonUtilities is a collection of utility classes and commonly useful/used
exceptions that make everyday programming in Java a lot easier.

## Summary of Utility-Functions provided
For real documentation about the methods, what they do and how they should
be used, please refer to the provided JavaDoc. This is just a brief overview
with no guarantees for completeness. The JavaDoc, however, is complete.

### The Utilities Package
#### StringUtils
The StringUtils class provides convenience methods for dealing with strings.
All methods are static, which has the advantage that the class does not need
to be instantiated and will always use a fixed, defined amount of memory. Due
to Java's lazy initialization mechanism, it will only start to use memory once
one of its methods is called for the first time, not before.

* **static String _bytesToHex_(byte[] bytes)** Convert an array of arbitrary bytes into a String of hexadecimal number-pairs with each pair representing on byte of the array.
* **static byte[] _hexToBytes_(String hex)** Convert a string consisting of hex-numbers into an array of bytes, which contains the binary representation of those hexadecimal-numbers (takes pairs of numbers, so make sure the number of characters is even).
* **static boolean  _isEmpty_(String text)** Universal check on String-objects that even works on non-instantiated variables (null-references), as it checks for null first.
* **static String _md5Hash_(String text)** Compute the MD-5 hash of a string, and return it has a string of hexadecimal numbers.
* **static int _md5HashInt_(String text)** Compute the MD-5 hash of a string, and return a truncated int-value of the hash.
* **static long _md5HashLong_(String text)** Compute the MD-5 hash of a string, and return a truncated long-value of the hash.
* **static String _sha1Hash_(String text)** Compute the SHA-1 hash of a string, and return it has a string of hexadecimal numbers.
* **static int _sha1HashInt_(String text)** Compute the SHA-1 hash of a string, and return a truncated int-value of the hash.
* **static long _sha1HashLong_(String text)** Compute the SHA-1 hash of a string, and return a truncated long-value of the hash.
* **static String _shorten_(String text, int size, int mode)** Shortens a given String "text" down to size length, indicating the shortening by three dots ("...").
* **static int _truncateHashToInt_(byte[] bytes)** Computes a truncated code from the given hash-value and returns it as int-variable.
* **static int _truncateHashToInt_(String hash)** Computes a truncated code from the given hash-value and returns it as int-variable.
* **static long _truncateHashToLong_(byte[] bytes)** Computes a truncated code from the given hash-value and returns it as long-variable.
* **static long _truncateHashToLong_(String hash)** Computes a truncated code from the given hash-value and returns it as long-variable.

### The Exceptions Package
#### InvalidEnumValueException
Exception-class for int-to-enum conversions, when the given int-value exceeds the valid values of the enum.

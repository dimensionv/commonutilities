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

* *static String bytesToHex(byte[] bytes)* Convert an array of arbitrary bytes into a String of hexadecimal number-pairs with each pair representing on byte of the array.
* *static byte[] hexToBytes(String hex)* Convert a string consisting of hex-numbers into an array of bytes, which contains the binary representation of those hexadecimal-numbers (takes pairs of numbers, so make sure the number of characters is even).
* *static boolean  isEmpty(String text)* Universal check on String-objects that even works on non-instantiated variables (null-references), as it checks for null first.
* *static String md5Hash(String text)* Compute the MD-5 hash of a string, and return it has a string of hexadecimal numbers.
* *static int md5HashInt(String text)* Compute the MD-5 hash of a string, and return a truncated int-value of the hash.
* *static long md5HashLong(String text)* Compute the MD-5 hash of a string, and return a truncated long-value of the hash.
* *static String sha1Hash(String text)* Compute the SHA-1 hash of a string, and return it has a string of hexadecimal numbers.
* *static int sha1HashInt(String text)* Compute the SHA-1 hash of a string, and return a truncated int-value of the hash.
* *static long sha1HashLong(String text)* Compute the SHA-1 hash of a string, and return a truncated long-value of the hash.
* *static String shorten(String text, int size, int mode)* Shortens a given String "text" down to size length, indicating the shortening by three dots ("...").
* *static int truncateHashToInt(byte[] bytes)* Computes a truncated code from the given hash-value and returns it as int-variable.
* *static int truncateHashToInt(String hash)* Computes a truncated code from the given hash-value and returns it as int-variable.
* *static long truncateHashToLong(byte[] bytes)* Computes a truncated code from the given hash-value and returns it as long-variable.
* *static long truncateHashToLong(String hash)* Computes a truncated code from the given hash-value and returns it as long-variable.

### The Exceptions Package
#### InvalidEnumValueException
Exception-class for int-to-enum conversions, when the given int-value exceeds the valid values of the enum.

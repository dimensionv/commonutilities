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
package de.dimensionv.java.libraries.common.utilities.file;

import de.dimensionv.java.libraries.common.exceptions.InvalidValueException;
import de.dimensionv.java.libraries.common.utilities.file.comparators.FileNameComparator;
import de.dimensionv.java.libraries.common.utilities.file.filefilters.AbstractFileFilter;
import de.dimensionv.java.libraries.common.utilities.file.filefilters.DirectoryFileFilter;
import de.dimensionv.java.libraries.common.utilities.file.filefilters.FileFileFilter;
import java.io.File;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * This class is a collection of useful routines regarding files, directories and URIs.
 *
 * <p>
 * Each method is available statically, which means there is no need to instantiate FileUtils. This is more memory
 * efficient and allows for faster runtime then a class that has to be instantiated, first.</p>
 *
 * @author Volkmar Seifert &lt;vs@DimensionV.de&gt;
 *
 * @version 1.0
 * @since API 1.3.0
 */
public class FileUtils {

  private static final int KILOBYTE = 1024;
  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###.#");
  private static final String UNIT_BYTE = " B";
  private static final String UNIT_KB = " KB";
  private static final String UNIT_MB = " MB";
  private static final String UNIT_GB = " GB";

  private static final String[] UNITS = new String[]{UNIT_KB, UNIT_MB, UNIT_GB};

  /**
   * File and folder comparator.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  private static final Comparator<File> comparator = new FileNameComparator();

  /**
   * File (not directories) filter.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  private static final AbstractFileFilter fileFilter = new FileFileFilter();

  /**
   * Folder (directories) filter.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  private static final AbstractFileFilter directoryFilter = new DirectoryFileFilter();

  /**
   * Checks whether the URI represented by the <code>String</code> <code>uri</code> is a local one.
   *
   * <p>
   * Currently, this method checks against the schemes "http(s)" and "ftp(s)". Everything else is considered as "local",
   * though this is of course not necessarily true.</p>
   *
   * @param uri The URI to check against.
   *
   * @return <code>true</code> if the URI is considered as local, <code>false</code> otherwise.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public static boolean isLocal(String uri) {
    uri = uri.toLowerCase(Locale.US); // assuem US ASCII, as this is a URI.
    return !(uri.startsWith("http") || uri.startsWith("ftp"));
  }

  /**
   * Gets the extension of a file name, like ".png" or ".jpg".
   *
   * @param path The path to get the file extension from. Can also be a <code>String</code>-representation of a URI
   *
   * @return Extension including the dot("."); "" if there is no extension.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public static String getExtension(String path) {
    int dot = path.lastIndexOf(".");
    if (dot >= 0) {
      return path.substring(dot);
    } else {
      // No extension.
      return "";
    }
  }

  /**
   * Returns the path only (without file name).
   *
   * @param file <code>File</code>-object to retrieve the path from.
   *
   * @return The path of the given <code>File</code>-object.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public static File getPathWithoutFilename(File file) {
    if (file.isDirectory()) {
      // not a file -> this is a "path" already.
      return file;
    } else {
      String fileName = file.getName();
      String filePath = file.getAbsolutePath();

      int nameLength = fileName.length();
      int pathLength = filePath.length();
      int removeCount = (pathLength > nameLength)
          ? pathLength - (nameLength + 1) // include the otherwise trailing File.separator char
          : pathLength - nameLength;

      // remove filename from path
      String pathWithoutName = filePath.substring(0, removeCount);
      return new File(pathWithoutName);
    }
  }

  /**
   * Transform a <code>URI</code> into a <code>File</code>.
   *
   * @param uri the <code>URI</code> to convert.
   *
   * @return file The new <code>File</code>-object.
   *
   * @throws InvalidValueException if the <code>uri</code> is somehow invalid.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public static File getFile(URI uri) {
    return new File(getPath(uri));
  }

  /**
   * Create a <code>File</code>-object from a path and file name.
   *
   * @param path The path of the file.
   * @param fileName The name of the file.
   *
   * @return The <code>File</code>-object corresponding to the given path and filename.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public static File getFile(String path, String fileName) {
    String separator = path.endsWith(File.separator) ? "" : File.separator;
    return new File(path + separator + fileName);
  }

  /**
   * Create a <code>File</code>-object from a path and file name.
   *
   * This method uses the absolute path of the given <code>path</code> <code>File</code>-object to construct the new
   * <code>File</code>-object representing the desired file.
   *
   * @param path A <code>File</code>-object corresponding to a directory, used for the path.
   * @param fileName The name of the file.
   * @return The <code>File</code>-object corresponding to the given path and filename.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public static File getFile(File path, String fileName) {
    return FileUtils.getFile(path.getAbsolutePath(), fileName);
  }

  /**
   * <p>
   * Get a file path from a URI.</p>
   *
   * <p>
   * The URI's scheme must be "file://" for this to work, otherwise this method will throw an
   * <code>InvalidValueException</code>.
   *
   * @param uri The <code>URI</code>-object to extract the path from.
   *
   * @return The path of the given <code>URI</code>-object
   *
   * @throws InvalidValueException Thrown when either the URI's scheme is not "file", or the returned path is null.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public static String getPath(URI uri) {
    String scheme = uri.getScheme().toLowerCase(Locale.US); // a scheme must be in US-ASCII
    if (!"file".equals(scheme)) {
      throw new InvalidValueException(uri);
    }
    String path = uri.getPath();
    if (path == null) {
      throw new InvalidValueException(uri);
    }
    return path;
  }

  /**
   * Get the file size in a human-readable string.
   *
   * @param size The file-size.
   *
   * @return The file-size in human-readable form.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public static String getReadableFileSize(long size) {
    String suffix = UNIT_BYTE;
    double fileSize = size;
    int unit = 0;
    while ((fileSize > KILOBYTE) && (unit < UNITS.length)) {
      fileSize /= KILOBYTE;
      suffix = UNITS[unit++];
    }
    return ((unit > 0) ? DECIMAL_FORMAT.format(fileSize) : Long.toString(size)) + suffix;
  }

  /**
   * Get a list of Files in the give path.
   *
   * @param path The path to list the files of.
   * @param includeHidden Flag whether hidden files should be included or not.
   *
   * @return List of files in the given directory.
   *
   * @since Class 1.0
   * @since API 1.3.0
   */
  public static List<File> getFileList(String path, boolean includeHidden) {
    List<File> list = new ArrayList<File>();

    // Current directory File instance
    final File pathDir = new File(path);

    // List file in this directory with the directory filter
    directoryFilter.setShowHidden(includeHidden);
    final File[] dirs = pathDir.listFiles(FileUtils.directoryFilter);
    if (dirs != null) {
      // Sort the folders alphabetically
      Arrays.sort(dirs, FileUtils.comparator);
      // Add each folder to the File list for the list adapter
      Collections.addAll(list, dirs);
    }

    // List file in this directory with the file filter
    directoryFilter.setShowHidden(includeHidden);
    final File[] files = pathDir.listFiles(FileUtils.fileFilter);
    if (files != null) {
      // Sort the files alphabetically
      Arrays.sort(files, FileUtils.comparator);
      // Add each file to the File list for the list adapter
      Collections.addAll(list, files);
    }

    return list;
  }
}

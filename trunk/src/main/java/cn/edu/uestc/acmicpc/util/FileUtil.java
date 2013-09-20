/*
 *
 *  * cdoj, UESTC ACMICPC Online Judge
 *  * Copyright (c) 2013 fish <@link lyhypacm@gmail.com>,
 *  * 	mzry1992 <@link muziriyun@gmail.com>
 *  *
 *  * This program is free software; you can redistribute it and/or
 *  * modify it under the terms of the GNU General Public License
 *  * as published by the Free Software Foundation; either version 2
 *  * of the License, or (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program; if not, write to the Free Software
 *  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package cn.edu.uestc.acmicpc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * File util methods.
 *
 * @author <a href="mailto:lyhypacm@gmail.com">fish</a>
 */
public class FileUtil {

  private static final int BUFFER_SIZE = 2048;

  /**
   * Save string into the specific file.
   *
   * @param content string content
   * @param filePath file's path
   */
  public static void saveToFile(String content, String filePath) {
    try {
      OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
      bufferedOutputStream.write(content.getBytes());
      bufferedOutputStream.close();
    } catch (IOException ignored) {
    }
  }

  /**
   * Save inputStream's content into outputStream.
   *
   * @param inputStream input stream to read
   * @param outputStream output stream to write
   * @throws IOException
   */
  public static void saveToFile(InputStream inputStream, OutputStream outputStream)
      throws IOException {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE);
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, BUFFER_SIZE);
    byte[] buffer = new byte[BUFFER_SIZE];
    int len;
    while ((len = bufferedInputStream.read(buffer)) != -1) {
      bufferedOutputStream.write(buffer, 0, len);
    }
    bufferedOutputStream.flush();
    bufferedOutputStream.close();
    bufferedInputStream.close();
  }

  /**
   * Recursively delete the contents of {@code targetFile}, but not the {@code targetFile} itself.
   * <p/>
   * If the {@code targetFile} does not exist or it is not a directory, return {@code 0}.
   *
   * @param targetFile file to delete.
   * @return the total number of files deleted
   */
  public static int deleteContents(File targetFile) {
    if (targetFile.exists() && targetFile.isDirectory())
      return org.aspectj.util.FileUtil.deleteContents(targetFile);
    else
      return 0;
  }

  /**
   * Clear all the files under the path and delete the directory.
   * <p/>
   * <strong>WARN</strong>: this operation cannot be reverted.
   *
   * @param path absolute path value
   */
  public static void clearDirectory(String path) {
    clearDirectory(new File(path));
  }

  /**
   * Move a directory into specific location.
   *
   * @param fromDir origin directory location
   * @param toDir destination location
   * @throws IOException
   */
  public static void moveDirectory(File fromDir, File toDir) throws IOException {
    org.aspectj.util.FileUtil.copyDir(fromDir, toDir);
    clearDirectory(fromDir);
  }

  /**
   * Count number of files in the folder.
   *
   * @param file file pointer
   * @return number of files in the folder
   */
  public static int countFiles(File file) {
    if (!file.exists())
      return 0;
    File[] files = file.listFiles();
    if (files == null)
      return 0;
    return files.length;
  }

  /**
   * Delete specific directory.
   *
   * @param file directory file pointer
   */
  private static void clearDirectory(File file) {
    if (file.exists()) {
      deleteContents(file);
      file.delete();
    }
  }

  /**
   * Get file's name without file extension.
   *
   * @param file file entity
   * @return file's name
   */
  public static String getFileName(File file) {
    String fileName = file.getName();
    int idx = fileName.lastIndexOf('.');
    if (idx == -1)
      return fileName;
    else
      return fileName.substring(0, idx);
  }
}

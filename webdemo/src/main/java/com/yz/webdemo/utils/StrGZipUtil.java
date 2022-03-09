package com.yz.webdemo.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

 

import org.apache.tomcat.util.codec.binary.Base64;


/**
 * Compress and unCompress string using GZIP.
 * 
 * @author SoftRoad
 */
public class StrGZipUtil {

  public static final String GZIP_ENCODE_UTF_8 = "UTF-8";

  /**
   * Compress string.
   * 
   * @param str
   *          String to be compressed
   * @return Compressed string
   */
  public static String compress(String str) {

    if (null == str || str.length() <= 0) {
      return str;
    }

    // Create a byte array output stream
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    OutputStream gzip;
    try {

      // Create a new output stream with the default buffer size
      gzip = new GZIPOutputStream(out);
      gzip.write(str.getBytes());
      gzip.close();
    } catch (Exception e) {

      e.printStackTrace();
    }

    // Use base64 to encode the byte array and get a string
    return Base64.encodeBase64String(out.toByteArray());
  }

  /**
   * UnCompress string.
   * 
   * @param str
   *          Compressed string
   * @return UnCompressed string
   */
  public static String unCompress(String str) {

    if (null == str || str.length() <= 0) {
      return str;
    }

    // Use base64 to encode the String and get a byte array
    byte[] bytes = Base64.decodeBase64(str);

    // Create a byte array output stream
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    InputStream gzip;
    String unCpStr = "";
    try {

      // Create a new output stream with the default buffer size
      gzip = new GZIPInputStream(new ByteArrayInputStream(bytes));
      byte[] buffer = new byte[8192];
      int n = 0;
      // Read uncompressed data into a byte array
      while ((n = gzip.read(buffer)) >= 0) {

        out.write(buffer, 0, n);
      }
      gzip.close();

      // Convert the contents of buffer to a string
      unCpStr = out.toString(GZIP_ENCODE_UTF_8);
    } catch (Exception e) {

      e.printStackTrace();
    }
    return unCpStr;
  }
}
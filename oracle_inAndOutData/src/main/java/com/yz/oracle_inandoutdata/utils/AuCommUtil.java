package com.yz.oracle_inandoutdata.utils;




import com.yz.oracle_inandoutdata.base.AuBase;
import com.yz.oracle_inandoutdata.entity.AuMinInOut;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class AuCommUtil extends AuBase {

    public static List<String> minOutFileList;

    public static String inStreamRead(Process process) throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(() -> {
            String line = null;
            try (BufferedReader bufferReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), AuConstUtil.SYSTEM_ENCODING));) {
                while ((line = bufferReader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            try (BufferedReader bufferErrorReader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream(), AuConstUtil.SYSTEM_ENCODING));) {
                while (bufferErrorReader.readLine() != null) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
        return sb.toString();
    }

    /**
     * @param fileName
     * @return
     */
    public static List<String> stepId(String fileName) {
        String[] strArr = null;
        List<String> resList = new ArrayList<>();
        Collections.reverse(minOutFileList);
        for (String line : minOutFileList) {
            if (line.contains(fileName)) {
                strArr = line.split(" ");
                if (strArr != null) {
                    resList.add(strArr[3].replaceAll("\\[", "").replaceAll("\\]", ""));
                    resList.add(strArr[4].replaceAll("\\[", "").replaceAll("\\]", ""));
                    break;
                }
            }
        }
        return resList;
    }

    public static String getFileNm(String fileName) {
        if (fileName.endsWith(".tsv")) {
            return fileName.substring(0, fileName.indexOf(".tsv"));
        } else if (fileName.contains("_")) {
            return fileName.substring(0, fileName.indexOf("_"));
        } else {
            return fileName;
        }
    }

    /**
     * @param context
     * @return
     * @throws Exception
     */
    public static String dbScript2Sh(String context) throws Exception {
        String userHome = System.getProperties().getProperty("user.home");
        userHome = userHome + "/linux";
        String tmpPath = userHome + File.separator + "test" + File.separator + "tpsh" + File.separator;
        creteFile(tmpPath);
        Path shPath = Paths.get(tmpPath).resolve("makeIompData.sh");
        if (shPath.toFile().exists()) {
            shPath.toFile().delete();
        }
        Files.write(shPath, context.getBytes(Charset.forName(AuConstUtil.SYSTEM_ENCODING)), StandardOpenOption.CREATE,
                StandardOpenOption.WRITE);
        return shPath.toString();
    }

    /**
     * @param filePath
     * @return
     */
    private static String creteFile(String filePath) {
        File f = new File(filePath);
        if (!f.isDirectory() && !f.exists())
            f.mkdirs();
        return filePath;
    }

    public static boolean isBlank(String... express) {
        if (express == null)
            return true;

        if (express.length == 1)
            return express[0] == null || "".equals(express[0].trim());

        for (String e : express) {
            if (e != null && !"".equals(e.trim()))
                return false;
        }

        return true;
    }

    public static String getJobId(String jobPath) {
        String jobId = "";
        if (jobPath != null && !jobPath.isEmpty()) {
            int idx = jobPath.indexOf("/");
            jobId = jobPath.substring(idx + 1);
        }
        return jobId;
    }

    public static String getRealFile(String fileName) {
        if (fileName.contains("_")) {
            return fileName.substring(fileName.indexOf("_") + 1);
        } else {
            return fileName;
        }
    }

    public static boolean isTable(AuMinInOut fileInfo) {
        return "DB".toString().equals(fileInfo.getFileType());
    }

    public static boolean isReport(AuMinInOut fileInfo) {
        return "REPORT".toString().equals(fileInfo.getFileType());
    }

    public static boolean isTempFile(AuMinInOut fileInfo) {
        return "TEMP".toString().equals(fileInfo.getFileType());
    }

    public static boolean isEqualsIn(String str, String... express) {
        if (str == null && express == null)
            return true;
        if (str != null && express == null)
            return false;

        return Arrays.asList(express).contains(str);
    }

    public static String getVol(String volName) {
        if (!AuCommUtil.isBlank(volName) && volName.contains(",")) {
            return volName.split(",")[0];
        }
        return volName;
    }

    /**
     * 文字列は数字かの判断
     *
     * @param num 文字列
     * @return 判断結果(true : 数字 ; false : 非数字)
     */
    public static boolean isNumber(String num) {
        try {
            return StringUtils.isNumeric(num.replaceAll("^[-]?(.*)$", "$1"));
        } catch (Exception e) {
            return false;
        }
    }

    public static List<String> getStringByRegex(String regex, String str) {
        Matcher m = Pattern.compile(regex).matcher(str);
        List<String> strArr = new ArrayList<String>();
        while (m.find()) {
            String s = m.group();
            strArr.add(s);
        }
        return strArr;
    }

    public static String getRealFileName(String fileName) {
        return fileName.replaceAll("(.*)\\(.*\\)", "$1");
    }

    /**
     * 半角を全角に変換
     *
     * @param input String.
     * @return 全角文字列
     */
    public static String half2full(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);

            }
        }
        return new String(c);
    }

    /**
     * 全角を半角に変換
     *
     * @param input String.
     * @return 半角文字列
     */
    public static String full2half(String input) {

        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);

            }
        }
        String returnString = new String(c);

        return returnString;
    }

    public static String getTableName(String tableFile) {
        if (tableFile.contains("-")) {
            return tableFile.replace("-", "_");
        } else if (tableFile.contains(".")) {
            return tableFile.replace(".", "_");
        } else {
            return tableFile;
        }
    }

    public static void dirOrFileDelete(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            // ファイル削除
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            // ディレクトリ削除
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static boolean isEmpty(String cs) {
        return cs == null || cs.length() == 0;
    }

    public static void fkdel(ScriptRunner runner, Path scritpPath) throws IOException {
        BufferedReader readdrop = new BufferedReader(
                new InputStreamReader(new FileInputStream(scritpPath.toFile()), AuConstUtil.ENCODING_SYSTEM));
        runner.setLogWriter(null);
        runner.runScript(readdrop);
        readdrop.close();
    }

    public static void fkadd(ScriptRunner runner, Path scritpPath) throws IOException {
        BufferedReader readadd = new BufferedReader(
                new InputStreamReader(new FileInputStream(scritpPath.toFile()), AuConstUtil.ENCODING_SYSTEM));
        runner.setLogWriter(null);
        runner.runScript(readadd);
        readadd.close();
    }
    
    public static void tableDataOutTxt(String userName, String password, String dbName, String tableName,
            String outPath, String tableColumns) {
        String expCommand = null;
        Process process = null;
        try {
            if (osName.startsWith("windows")) {
                expCommand = "sqluldr264.exe " + userName + "/" + password + "@" + dbName + " query=\"select " + "*"
                        + " from " + tableName + "\" field=\"&*&\" charset=JA16SJIS file="
                        + Path.of(outPath, tableName + ".csv").toString();
                logger.info("expCommand:" + expCommand);
                process = Runtime.getRuntime().exec(expCommand);
            } else {
                expCommand = "sqluldr2 " + userName + "/" + password + "@" + dbName + " query=\"select " + "*"
                        + " from " + tableName + "\" field=\"&*&\" charset=JA16SJIS file="
                        + Path.of(outPath, tableName + ".csv").toString();
                logger.info("expCommand:" + expCommand);
                String tpPath = dbScript2Sh(expCommand);
                String keigenSet = "chmod 755 " + tpPath;
                process = Runtime.getRuntime().exec(keigenSet);

                process.waitFor();
                process = Runtime.getRuntime().exec(tpPath);
            }
            InputStream is1 = process.getInputStream();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is1, "MS932"));
            String line1;
            while ((line1 = br1.readLine()) != null) {
                if (!line1.trim().equals("")) {
//                  System.out.println(line1);
                }
            }
            InputStream is2 = process.getErrorStream();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2, "MS932"));
            String line2;
            while ((line2 = br2.readLine()) != null) {
                if (!line2.trim().contains("error")) {
                    logger.error(line2);
                }
            }
            process.waitFor();
            process.destroy();
            logger.info("テーブル({})エクスポート", tableName);
        } catch (Exception e) {
            System.out.println("エクスポート失敗:" + e.getMessage());
            e.printStackTrace();
        }
    }
}

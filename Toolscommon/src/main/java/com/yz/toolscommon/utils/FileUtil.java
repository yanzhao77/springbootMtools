package com.yz.toolscommon.utils;//package mao.linlon.utils;
//
//
//import java.io.File;
//import java.nio.channels.FileChannel;
//import java.nio.channels.FileLock;
//import java.nio.file.StandardOpenOption;
//import java.util.List;
//import java.util.Optional;
//
//import org.apache.commons.io.FileUtils;
//
//import ch.qos.logback.classic.gaffer.PropertyUtil;
//import lombok.SneakyThrows;
//
//
//public class FileUtil {
//
//
//
//
//	/*
//	 * public static String getDataFilePath() { return
//	 * DirManagerUtil.getRealPath(DirManagerUtil.PATH_DATA); }
//	 */
//
//
//
//
//	public static String getTempFilePath() {
////		return DirManagerUtil.getRealPath(DirManagerUtil.PATH_TEMP);
//		return "";
//	}
//
//
//
//
//
//    public static String getSysinContext(FileInfo fileInfo) {
//        if (fileInfo == null) {
//            return "";
//        }
//
//        StringBuilder sysinContext = new StringBuilder();
//
//        boolean isReadStart = true;
//        /*
//        try (SysinReader sysin = new SysinReader(fileInfo)) {
//            while (sysin.hasNextLine()) {
//                sysinContext.append(isReadStart ? "" : "\n");
//                sysinContext.append(new String(sysin.nextLine(), ConstUtil.ENCODING_SYSTEM));
//                isReadStart = false;
//            }
//        }
//        */
//
//        return sysinContext.toString();
//    }
//
//
//
//
//    public static Optional<Integer> getRecSize(FileInfo fileInfo) {
//
//       // if (FileStorgeType.isTempFile(fileInfo)) {
//
//			/*
//			 * if (JobInfo.mapTempFiles.containsKey(fileInfo.getFileName())) { return
//			 * Optional.ofNullable(JobInfo.mapTempFiles.get(fileInfo.getFileName()).
//			 * getRecSize()); } } else {
//			 */
//            // Cn0680tz fileEntity = fileInfo.getFileEntity();
//            //
//            // if (fileEntity == null) {
//            // // DB����A���R�[�h�T�C�Y�擾
//            // Cn0680tzMapper mapper = SpringUtil.getBean(Cn0680tzMapper.class);
//            // return
//            // Optional.ofNullable(mapper.findByName(fileInfo.getFileName()).getRecLen());
//            // }
//
//            return Optional.ofNullable(fileInfo.getRecSize());
////        }
//
////        return Optional.empty();
//    }
//
//
//
//
//    public static FileInfo createTempFileInfo(String fileName) {
//        FileInfo tmpFile = new FileInfo();
////        tmpFile.setFileName(JobInfo.getCurrentStepInfo().getStepId() + "_" + fileName);
//        tmpFile.setFileStat("TEMP");
////        tmpFile.setDisp(DispType.FORCEDEL.toString());
//        return tmpFile;
//    }
//
//
//
//    @SneakyThrows
//    public static File createTempFile(String fileName) {
//
//        String path = FileUtil.getTempFilePath();
//
//        File tempFile = File.createTempFile(fileName + "_", "", new File(path));
//
//        tempFile.deleteOnExit();
//
//        return tempFile;
//    }
//
//    @SneakyThrows
//    public static FileChannel getFileChannel(File file) {
//        return FileChannel.open(file.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE);
//    }
//
//    @SneakyThrows
//	public static FileLock getFileLock(File file, FileChannel channel, boolean shared) {
//    	 FileLock lock = null;
//		/*
//		 *
//		 * int retryTime = PropertyUtil.getInt("RETRY_TIME_FOR_APPLY_LOCK");
//		 *
//		 * if (retryTime < 0) { throw new SystemException("EM008",
//		 * ConstUtil.RETRY_TIME_FOR_APPLY_LOCK); }
//		 *
//		 * int retryInterval =
//		 * PropertyUtil.getInt(ConstUtil.RETRY_INTERVAL_FOR_APPLY_LOCK);
//		 *
//		 * if (retryInterval < 0) { throw new SystemException("EM008",
//		 * ConstUtil.RETRY_INTERVAL_FOR_APPLY_LOCK); }
//		 *
//		 * while (true) { if (shared) { lock = channel.tryLock(0L, Long.MAX_VALUE,
//		 * true); } else { lock = channel.tryLock(); }
//		 *
//		 * if (lock != null) { break; }
//		 *
//		 * Thread.sleep(retryTime * 1000); retryTime = retryTime - retryInterval; if
//		 * (retryTime < 0) { break; } }
//		 *
//		 * if (lock == null) { throw new SystemException("EM009",
//		 * file.getAbsoluteFile()); }
//		 *
//		 */
//    	 return lock;
//
//    }
//
//    @SneakyThrows
//    public static void joinFiles(List<FileInfo> fileList, FileInfo out) {
//    	/*
//        try (
//                FileChannel writeChannel = FileChannel.open(FileFactory.Create(out).create().toPath(),
//                        StandardOpenOption.WRITE,
//                        StandardOpenOption.CREATE);) {
//
//            long offset = 0;
//            long size = 0;
//            for (FileInfo in : fileList) {
//                out.setRecForm(in.getRecForm());
//                out.setFileStat(in.getFileStat());
//                out.setRecSize(in.getRecSize());
//                if (in.getIoModel() == null) {
//                    in.setIoModel(FileModel.I);
//                }
//                try (FileChannel readChannel = FileChannel.open(FileFactory.Create(in).getFile().toPath(),
//                        StandardOpenOption.READ);) {
//                    size = readChannel.size();
//                    writeChannel.transferFrom(readChannel, offset, size);
//                    offset += size;
//                }
//            }
//        }
//        */
//    }
//
//    @SneakyThrows
//    public static void deleteDirectory(FileInfo fileInfo) {
////        FileUtils.deleteDirectory(FileFactory.Create(fileInfo).getFile());
//    }
//
//    @SneakyThrows
//    public static void cleanDirectory(FileInfo fileInfo) {
////        FileUtils.cleanDirectory(FileFactory.Create(fileInfo).getFile());
//    }
//
//    @SneakyThrows
//    public static void copyDirectory(FileInfo srcFileInfo, FileInfo destFileInfo) {
//    	//FileUtils.copyDirectory(FileFactory.Create(srcFileInfo).getFile(),FileFactory.Create(destFileInfo).getFile());
////        FileUtils.copyDirectoryToDirectory(FileFactory.Create(srcFileInfo).getFile(),FileFactory.Create(destFileInfo).getFile());
//    }
//
//    @SneakyThrows
//    public static void copyFileToDirectory(FileInfo srcFileInfo, FileInfo destFileInfo) {
//        /*FileUtils.copyFileToDirectory(FileFactory.Create(srcFileInfo).getFile(),
//                FileFactory.Create(destFileInfo).getFile());
//                */
//    }
//
//    public static String getRealFileName(String fileName) {
//        return fileName.replaceAll("(.*)\\(.*\\)", "$1");
//    }
//
//    public static FileInfo setVol(FileInfo fileInfo) {
///*
//        if (StringUtil.isBlank(fileInfo.getVol())) {
//            fileInfo.setCatalog(true);
//            String vol = CatalogUtil.getVolumNo(fileInfo.getFileName());
//            if (StringUtil.isBlank(vol)) {
//                vol = ConstUtil.VOLUME_WORK;
//            }
//            fileInfo.setVol(vol);
//        }
//        */
//        return fileInfo;
//    }
//
//    @SneakyThrows
//    public static void creatMultiVolFile(FileInfo fileInfo) {
//        String[] vols = fileInfo.getVol().split(",");
//
//        if (vols.length <= 1) {
//            return;
//        }
//
//        FileInfo multiVolFile = null;
//        for (int i = 0; i < vols.length; i++) {
//            if (i == 0) {
//                continue;
//            }
//
//            multiVolFile = fileInfo.getClone();
//            multiVolFile.setVol(vols[i]);
////            FileUtils.copyFile(FileFactory.Create(fileInfo).getFile(),FileFactory.Create(multiVolFile).getFile());
//        }
//    }
//}

package com.yz.toolscommon.FileUtil;//package mao.linlon.FileUtil;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//import org.springframework.batch.core.StepContribution;
//
//import itt.batch.base.AbsTasklet;
//import itt.common.file.FileInfo;
//import itt.common.file.utils.BytesUtil;
//import itt.common.file.utils.CatalogUtil;
//import itt.common.file.utils.ConstUtil;
//import itt.common.file.utils.FileUtil;
//import itt.common.file.utils.InputUtil;
//import itt.common.file.writer.AbsWriter;
//import itt.common.utils.enums.DispType;
//import itt.common.utils.enums.RcdFmt;
//import jp.co.srm.file.FileModel;
//
///**
// * CTFE�}�N���N���X<br>
// * �J�^���O�o�^��̕ҏW
// *
// * @author SoftRoad
// */
//public class CtfeTasklet extends AbsTasklet {
//
//	/**
//	 * �f�t�H���g�ݒ菈��
//	 */
//	@Override
//	public void defaultSetting() {
//		fileList.forEach(fileInfo -> {
//			switch (fileInfo.getLogicName()) {
//			// �o�̓t�@�C��
//			case ConstUtil.FILE_LOGIC_NAME_U03FILE:
//			case ConstUtil.FILE_LOGIC_NAME_SPDFILE:
//				fileInfo.defaultAppend(false);
//				fileInfo.setIoModel(FileModel.O);
//				break;
//			// �p�����[�^�t�@�C��
//			case ConstUtil.FILE_LOGIC_NAME_COINFILE:
//				fileInfo.setIoModel(FileModel.I);
//				break;
//			default:
//				break;
//			}
//
//		});
//	}
//
//	/**
//	 * �}�N���̃��C������
//	 *
//	 * @param contribution StepContribution
//	 * @throws Exception �ُ�
//	 */
//	@Override
//	public void execute(StepContribution contribution) throws Exception {
//
//		// ����t�@�C��
//		FileInfo coinFile = null;
//		FileInfo outFile = null;
//
//		// �t�@�C���Ώێ擾
//		for (FileInfo file : fileList) {
//			if (ConstUtil.FILE_LOGIC_NAME_COINFILE.equalsIgnoreCase(file.getLogicName())) {
//				coinFile = file;
//			} else if (ConstUtil.FILE_LOGIC_NAME_U03FILE.equalsIgnoreCase(file.getLogicName())
//					|| ConstUtil.FILE_LOGIC_NAME_SPDFILE.equalsIgnoreCase(file.getLogicName())) {
//				outFile = file;
//			}
//		}
//
//		// SYSIN�t�@�C������p�����[�^�̒l���擾����
//		Map<String, List<String>> coinMap = InputUtil.getCoinFileList(FileUtil.getSysinContext(coinFile));
//
//		// �J�^���O�o�^��ւ̓o�^(CATL) �̏ꍇ
//		if (coinMap.get(ConstUtil.SYSIN_CATL) != null) {
//
//			List<String> files = coinMap.get(ConstUtil.SYSIN_FILE);
//			String[] fileArr = null;
//			FileInfo fileInfo = null;
//			for (String file : files) {
//				fileArr = file.split(",");
//
//				// �J�^���O�o�^��֓o�^����
//				if (fileArr.length >= 2) {
//
//					fileInfo = new FileInfo();
//					fileInfo.setFileName(fileArr[0]);
//					fileInfo.setVol(fileArr[1]);
//					CatalogUtil.catlog(fileInfo);
//				}
//			}
//		} else if (coinMap.get(ConstUtil.SYSIN_TAKE) != null) {
//			// �J�^���O�o�^��̈��(TAKE) �̏ꍇ
//			takeExec(outFile, coinMap);
//		} else if (coinMap.get(ConstUtil.SYSIN_SAVE) != null) {
//			// �J�^���O�o�^��̑ޔ�(SAVE) �̏ꍇ
//			saveExec(outFile, coinMap);
//		}
//	}
//
//	/**
//	 * �J�^���O�o�^��̈��(TAKE) �̏ꍇ
//	 *
//	 * @param inFile
//	 * @param coinMap
//	 */
//	public void takeExec(FileInfo inFile, Map<String, List<String>> coinMap) {
//		List<String> valueList = coinMap.get(ConstUtil.SYSIN_TAKE);
//		if (null != valueList) {
//			// �J�^���O�o�^��̂��ׂĂ̈�ʃt�@�C��(����t�@�C���ȊO)�̏����������.
//			if (valueList.get(0).equals(ConstUtil.SYSIN_TAKE_GNR)) {
//				List<File> listFile = listFile(inFile.getFileObject().getFile(false), false);
//				StringBuffer sBuffer = new StringBuffer();
//				sBuffer.append("SPD CTFE    ").append(getDate(true));
//				List<byte[]> bList = new ArrayList<>();
//				bList.add(getByte(sBuffer.toString(), 36));
//				for (File file : listFile) {
//					String vol = CatalogUtil.getVolumNo(file.getName()) == null ? ConstUtil.VOLUME_WORK
//							: CatalogUtil.getVolumNo(file.getName());
//
//					bList.add(getByte("LST", 4));
//					bList.add(getByte(file.getName(), 26));
//
//					byte[] rightPadding = getByte(getDate(false) + vol, 14);
//					bList.add(rightPadding);
//				}
//				// write file
//				writeByte(inFile, bList);
//			}
//		}
//	}
//
//	/**
//	 * �J�^���O�o�^��̑ޔ�(SAVE) �̏ꍇ
//	 *
//	 * @param inFile
//	 * @param coinMap
//	 */
//	public void saveExec(FileInfo inFile, Map<String, List<String>> coinMap) {
//
//	}
//
//	/**
//	 *
//	 * @param fileInfo
//	 * @param bList
//	 */
//	public void writeByte(String fileName, List<byte[]> bList) {
//		// �ꎞ�t�@�C���̍쐬
//		FileInfo fileInfo = new FileInfo();
//		fileInfo.setFileName(fileName);
//		fileInfo.setRecForm(RcdFmt.FB);
//		fileInfo.setDisp(DispType.FORCEDEL.toString());
//		try (
//				// �o�̓t�@�C���ɏ���
//				AbsWriter writer = AbsWriter.create(fileInfo);) {
//			for (byte[] bytes : bList) {
//				writer.write(bytes);
//			}
//		}
//	}
//
//	public byte[] getByte(String value, int recSize) {
//		return BytesUtil.rightPadding(BytesUtil.string2Byte(value), recSize,
//				BytesUtil.byte2String(BytesUtil.hexString2Bytes("20")).charAt(0));
//	}
//
//	public String getDate(boolean flag) {
//		Calendar now = Calendar.getInstance();
//		String yearStr = Integer.toString(now.get(Calendar.YEAR)).substring(2);
//		String month = String.valueOf(now.get(Calendar.MONTH) + 1).length() == 1 ? "0" + (now.get(Calendar.MONTH) + 1)
//				: String.valueOf(now.get(Calendar.MONTH) + 1);
//		int day = now.get(Calendar.HOUR_OF_DAY);
//		String str = flag ? "" : ".";
//		return yearStr + str + month + str + day;
//	}
//
//	/**
//	 * �K�w�f�B���N�g���t�@�C�����擾
//	 *
//	 * @param file home directory
//	 * @param flag ����t�@�C�����ǂ���
//	 * @return
//	 */
//	public List<File> listFile(File file, boolean flag) {
//		List<File> fileList = new ArrayList<>();
//		if (null != file.listFiles()) {
//			for (File newFile : Objects.requireNonNull(file.listFiles())) {
//				if (newFile.isDirectory()) {
//					fileList.addAll(listFile(newFile, flag));
//				} else if (flag) {
//					fileList.add(newFile);
//				} else if ((!newFile.getName().contains("(")) || (!newFile.getName().contains(")"))) {
//					fileList.add(newFile);
//				}
//			}
//		} else {
//			fileList.add(file);
//		}
//
//		return fileList;
//	}
//
//}

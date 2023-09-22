package com.yz.toolscommon.config;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class FileInfo {

    
    private String logicName = "";

    
    private String fileName = "";

    private String media = "";

    
    private String vol = "";

    
    private String dirName = "";

    
    private String fileStat = "NORMAL";

    
    private String disp;

    
    private Integer recSize;

    
//    private RcdFmt recForm;

    
    private Boolean append;

    
//    private FileModel ioModel;

    
    private String psw = "";

    
    private Boolean catalog = false;

    
    private String prLogicName = "";

    
    private Boolean existedWhenInitialize;

    
//    private AbsFile fileObject;

    
    public FileInfo getClone() {
        FileInfo fileInfo = new FileInfo();
//        fileInfo.setLogicName(this.getLogicName());
//        fileInfo.setFileName(this.getFileName());
//        fileInfo.setMedia(this.getMedia());
//        fileInfo.setVol(this.getVol());
//        fileInfo.setDirName(this.getDirName());
//        fileInfo.setFileStat(this.getFileStat());
//        fileInfo.setDisp(this.getDisp());
//        fileInfo.setRecSize(this.getRecSize());
////        fileInfo.setRecForm(this.getRecForm());
//        fileInfo.setAppend(this.getAppend());
////        fileInfo.setIoModel(this.getIoModel());
//        fileInfo.setPsw(this.getPsw());
//        fileInfo.setCatalog(this.getCatalog());
//        fileInfo.setPrLogicName(this.getPrLogicName());
//
        return fileInfo;
    }

    
    public void defaultAppend(boolean value) {
        if (this.append == null) {
            this.append = value;
        }
    }
}

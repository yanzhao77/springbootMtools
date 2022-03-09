package com.yz.securityjwtdemo.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * Implementation of SysInfo DTO class.
 * 
 * @author SoftRoad
 */
@Getter
@Setter
public class SysInfoDto {

  /**
   * System information.
   */
  private String systemData;

  /**
   * User id.
   */
  private String userId;

  /**
   * Job date.
   */
  private String jobDate;

  /**
   * Main menu.
   */
  private String mainMenu;
  
  /**
   * this is object json
   */
  private String dtoJson; 

  /**
   * Get job date.
   * 
   * @param dateType
   *          Job date type
   * @return Job date
   */
  public int getJobDate(String dateType) {

    switch (dateType) {
      case "yyyymmdd":
        return Integer.valueOf(jobDate);
      case "yymmdd":
        return Integer.valueOf(jobDate.substring(2));
      case "yyyy":
        return Integer.valueOf(jobDate.substring(0, 4));
      case "yy":
        return Integer.valueOf(jobDate.substring(0, 2));
      case "mm":
        return Integer.valueOf(jobDate.substring(4, 6));
      case "dd":
        return Integer.valueOf(jobDate.substring(6, 8));
      default:
        return 0;
    }
  }
}

package com.softroad.filefortreedemo.config;


import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

import static cn.hutool.core.util.CharsetUtil.UTF_8;

/**
 * @author yanzhao
 * @version 1.0
 * @classname ConfigTemp
 * @date 2022/8/18 11:40
 * @description TODO
 */
@Data
@Configuration
public class ConfigTemp {
    //转换文件还是转换文件夹
    boolean file_flag;

    //配置文件路径
    String configPath;

    // 性能修正flag
    boolean performanceFixes_flag = true;

    // QA修正flag
    boolean qaUpdate_flag = true;

    // 注释代码保存flag
    boolean exegesisDelete_flag = true;

    //merge config
    //这里只需要配文件夹 或者 文件 一对就可以
    //绝对路径，两个目录的文件夹结构和文件名同名
    //待合并的代码文件夹
    String old_dir_ptah;

    //需要合并的代码文件夹
    String new_dir_ptah;


    //如果文件合并,需要文件顺序需要对应
    //待合并的代码文件
    String old_file_ptah;

    //需要合并的代码文件
    String new_file_ptah;

    //输出文件夹
    String out_dir_path;

    //文件编码格式(可不写)(默认是UTF-8)
    String encode = UTF_8;

    //代码本次修改日期(可为空)(默认值是当天时间)
    String updateDate = new Date().toString();
}

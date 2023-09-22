package com.softroad.filefortreedemo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author yanzhao
 * @version 1.0
 * @classname Attribute
 * @date 2022/8/18 17:47
 * @description TODO
 */

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TreeAttribute implements Cloneable, Serializable {

    String key;

    String value;

    @Override
    public TreeAttribute clone() {
        return new TreeAttribute(key, value);
    }
}

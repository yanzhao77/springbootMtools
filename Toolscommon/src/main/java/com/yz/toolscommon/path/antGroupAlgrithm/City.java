package com.yz.toolscommon.path.antGroupAlgrithm;

/**
 * 城市类
 *
 * @author yanzhao
 * @version 1.0
 * @classname City
 * @date 2023/03/30 15:40
 * @description TODO
 */
public class City
{
    //记录城市的坐标
    public int x, y;
    //记录城市的标志
    public int ID;

    City(int lx, int ly, int sign) {
        this.x = lx;
        this.y = ly;
        this.ID = sign;
    }
}



package com.yz.toolscommon.path.antGroupAlgrithm;

/**
 * @author yanzhao
 * @version 1.0
 * @classname DistancePath
 * @date 2023/03/30 15:40
 * @description TODO
 */
public class DistancePath
{
    public int path[];
    public double distance;

    DistancePath(int num) {
        this.path = new int[num + 1];
        this.distance = 0;
    }
}

package com.yz.toolscommon.path.antGroupAlgrithm;

/**
 * @author yanzhao
 * @version 1.0
 * @classname AntAlgrithm
 * @date 2023/03/30 15:41
 * @description TODO
 */
public class AntAlgrithm
{

    public int cityNum;
    public City city[];
    public Ants ants[];
    // 记录已经被选择的出发点
    public boolean start[];
    // 距离矩阵与信息素矩阵
    public double distanceMatrix[][];
    public double pheromones[][];

    //记录全局解结果
    public DistancePath dp[][];

    public static final double alpha = 1;
    public static final double beta = 2;
    public static final double p = 0.5;
    public static final int antnumber = 18;
    public static final int MAX = 100000;
    public static final int times = 500;

    //构造函数
    AntAlgrithm(int x[], int y[]) {
        this.cityNum = x.length;
        this.city = new City[cityNum];
        this.ants = new Ants[antnumber];
        this.start = new boolean[cityNum];
        for (int i = 0; i < this.cityNum; i++) {
            this.city[i] = new City(x[i], y[i], i);
            this.start[i] = false;
        }
        // 初始化每一只蚂蚁
        for (int i = 0; i < antnumber; i++) {
            this.ants[i] = new Ants(this.cityNum);
            this.ants[i].pro = new double[this.cityNum - 1];
            this.ants[i].accumulatePro = new double[this.cityNum - 1];
        }
        //记录解空间的初始化
        this.dp = new DistancePath[times][antnumber];
        for (int i = 0; i < this.dp.length; i++) {
            for (int j = 0; j < this.dp[i].length; j++)
                this.dp[i][j] = new DistancePath(this.cityNum);
        }
    }

    //初始化函数
    public void Initial() {
        this.distanceMatrix = new double[this.cityNum][this.cityNum];
        this.pheromones = new double[this.cityNum][this.cityNum];

        for (int i = 0; i < this.cityNum; i++) {
            for (int j = i; j < this.cityNum; j++) {
                if (j == i)
                    this.distanceMatrix[i][j] = MAX;
                else {
                    this.distanceMatrix[i][j] = Math.hypot(this.city[i].x - this.city[j].x,
                            this.city[i].y - this.city[j].y);
                    this.distanceMatrix[j][i] = this.distanceMatrix[i][j];
                }
            }
        }
        // 利用贪心算法更新信息素矩阵
        double cn = this.greedy();
        double tao;
        tao = antnumber / cn;
        for (int i = 0; i < this.cityNum; i++) {
            for (int j = i + 1; j < this.cityNum; j++) {
                this.pheromones[i][j] = tao;
                this.pheromones[j][i] = tao;
            }
        }
    }

    private double greedy() {
        return 0;
    }

//    public static void main(String[] args) {
//        //城市坐标
//        int xCoordinate[] = { 1304, 3639, 4177, 3712, 3488, 3326, 3238, 4196, 4312, 4386, 3007, 2562, 2788, 2381, 1332,
//                3715, 3918, 4061, 3780, 3676, 4029, 4263, 3429, 3507, 3394, 3439, 2935, 3140, 2545, 2778, 2370 };
//        int yCoordinate[] = { 2312, 1315, 2244, 1399, 1535, 1556, 1229, 1004, 790, 570, 1970, 1756, 1491, 1676, 695,
//                1678, 2179, 2370, 2212, 2578, 2838, 2931, 1908, 2367, 2643, 3201, 3240, 3550, 2357, 2826, 2975 };
//        int flag = 0;
//        //初始化
//        AntAlgrithm ata = new AntAlgrithm(xCoordinate, yCoordinate);
//        ata.Initial();
//        //演变
//        while (flag < times) {
//            ata.tsp();
//            ata.record(flag);
//            ata.remake();
//            flag += 1;
//        }
//        //选择最优解
//        ata.chooseBest();
//    }

}
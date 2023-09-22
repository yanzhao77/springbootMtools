package com.yz.toolscommon.soft.sort.spfa;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class SPFA {
    //h,e,ne模拟邻接图，w存储边的权重，dist存储起点到当前节点的距离，st表示当前这个点是不是在队列当中，千万不要和dijkstra的st搞混了
    //count存储的是从起点到当前节点一共走过了几条边
    public static int N = 1000010, index = 0, n, m;
    public static int[] h = new int[N], e = new int[N], ne = new int[N], w = new int[N];
    public static int[] dist = new int[N], count = new int[N];
    public static boolean[] st = new boolean[N];

    public static void main(String[] args) {
        //初始化，链表head全部为正无穷，n是点的个数，m是边的个数
        Scanner in = new Scanner(System.in);
        n = in.nextInt(); m = in.nextInt();
        Arrays.fill(h, -1);

        while (m-- > 0) {
            int x = in.nextInt(), y = in.nextInt(), c = in.nextInt();
            add(x, y, c);
        }

        if (SPFA()) System.out.println("Yes");
        else System.out.println("No");
    }

    //邻接表的插入操作，类似于单链表模拟
    public static void add (int x, int y, int c){
        e[index] = y;
        w[index] = c;
        ne[index] = h[x];
        h[x] = index++;
    }

    public static boolean SPFA() {
        //这里不需要对dist进行正无穷覆盖和初始化，因为根本不关心最短路的事
        //将每一个节点都推入到队列中，这是防止起点和负环之间不存在连接，干脆就全推一遍
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i<=n; i++) {
            st[i] = true;
            q.add(i);
        }

        //这里的整体思路还是SPFA的思路不变
        while (q.size() > 0){
            int t = q.poll();
            st[t] = false;

            for (int i = h[t]; i!=-1; i = ne[i]) {
                int j = e[i];

                //着重说一下这个地方，count[n]代表起点到n点需要经过几条边,而j是t的下一个点，所以起点到j的距离就是t+1
                //而因为有负环的存在，负环中的点到起点的count就会被无限次的更新
                //那么当起点到当前节点经过的边数大于等于n时，也就说明图中至少有n+1个点存在，这就不合理了，说明存在负环，返回True
                if (dist[j] > dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];

                    count[j] = count[t] + 1;
                    if(count[j] >= n) return true;

                    if (!st[j]) {
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }

        //如果没遇到负环，返回false
        return false;
    }
}

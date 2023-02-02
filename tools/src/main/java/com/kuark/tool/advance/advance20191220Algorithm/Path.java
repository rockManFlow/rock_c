package com.kuark.tool.advance.advance20191220Algorithm;

/**
 * @author rock
 * @detail
 * @date 2021/6/9 11:04
 */
public class Path {
    public static int [][] init(int size,int[][] path){
        int [][] a=new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(i!=j){
                    a[i][j]=Integer.MAX_VALUE;
                }else {
                    a[i][j]=0;
                }
                path[i][j]=j;
            }
        }

        //初始化已知值
        a[0][1] = 10;
        a[1][0] = 10;
        a[0][3] = 30;
        a[3][0] = 30;
        a[0][4] = 100;
        a[4][0] = 100;
        a[1][2] = 50;
        a[2][1] = 50;
        a[2][3] = 20;
        a[3][2] = 20;
        a[2][4] = 10;
        a[4][2] = 10;
        a[3][4] = 60;
        a[4][3] = 60;
        return a;
    }

    public static void main(String[] args) {
        int size=5;
        int[][] path=new int[size][size];
        int[][] a=init(size,path);

        floyd(a,size,path);
    }

    /**
     * 弗洛伊得最短路径算法：动态算法（最短路径的子路径依然是最短路径）
     * 比如一条从a到e的最短路a->b->c->d->e 那么 a->b->c 一定是a到c的最短路c->d->e一定是c到e的最短路
     */
    public static void floyd(int[][] a,int n,int[][] path) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][k] < Integer.MAX_VALUE && a[k][j] < Integer.MAX_VALUE) {
                        final int d = a[i][k] + a[k][j];
                        if (d < a[i][j]) { //经过k点时i到j的距离比不经过k点的距离更短
                            a[i][j] = d; //更新i到j的最短距离
                            path[i][j] = path[i][k]; //更新i到j经过的最后一个点为k点
                        }
                    }
                }
            }
        }

        int temp;
        for (int i = 0; i < n; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                builder.append(i).append("->").append(j)
                        .append(", weight: "). append(a[i][j]).append(":").append(i);
                temp = path[i][j];
                while(temp != j) {
                    builder.append("->").append(temp);
                    temp = path[temp][j];
                }
                builder.append("->").append(j).append("\n");
            }
            System.out.println(builder.toString());
        }
    }
}

package a2408.study.week27;

import java.io.*;
import java.util.*;

public class bj_g3_17182_우주_탐사선 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N][K];
        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++){
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}

/*
 * | 활용 알고리즘 | BFS
 *
 * | 주의 사항
 *  1. 문제를 똑바로 읽기...
 *     귀신은 만나면 그 즉시 수행이 끝난다
 * 
 */

package a2404.study.week10;

import java.io.*;
import java.util.*;

public class bj_g3_11451_팩맨 {
    static int N, M, MIN = Integer.MAX_VALUE;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static char[] directionChar = {'N', 'E', 'S', 'W'};
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            char[][] maze = new char[M][N];
            int[][] pacman = new int[][] {{-1, -1}, {-1, -1}};
            for(int m=0; m<M; m++){
                maze[m] = br.readLine().toCharArray();
                for(int n=0; n<N; n++){
                    if(maze[m][n] == 'P'){
                        if(pacman[0][0] == -1){ pacman[0] = new int[] {m, n}; }
                        else{ pacman[1] = new int[] {m, n}; }
                    }
                }
            }
            int min = Integer.MAX_VALUE;
            for(int d=0; d<4; d++){
                System.out.println(">> "+directionChar[d]);
            }
        }
    }
}

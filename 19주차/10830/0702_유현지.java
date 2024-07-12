/*
 * | 활용 알고리즘 | Divide-and-Conquer
 *
 * | 접근 방법 |
 *  1. 행렬을 N번 제곱할 때
 *     1-1. N이 짝수이면 N/2번 제곱한 것을 2번 곱하면 된다
 *     1-2. N이 홀수이면 N/2번 제곱한 것을 2번, 원본 행렬을 1번 곱하면 된다
 */

package a2407.study.week19;

import java.io.*;
import java.util.*;

public class bj_g4_10830_행렬_제곱 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int[][] matrix = new int[N][N];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            for(int nn=0; nn<N; nn++){ matrix[n][nn] = (Integer.parseInt(st.nextToken()))%1000; }
        }
        int[][] result = dc(matrix, B);
        StringBuilder sb = new StringBuilder();
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                sb.append(result[r][c]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[][] dc(int[][] matrix, long n){
        if(n==1){
            return matrix;
        }
        int[][] half = dc(matrix, n/2);
        if(n%2 == 0){
            return calc(half, half);
        }
        else{
            return calc(calc(half, half), matrix);
        }
    }

    static int[][] calc(int[][] m1, int[][] m2){
        int N = m1.length;
        int[][] result = new int[N][N];
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                for(int d=0; d<N; d++){
                    result[r][c] = (result[r][c] + m1[r][d] * m2[d][c]) % 1000;
                }
            }
        }
        return result;
    }
}

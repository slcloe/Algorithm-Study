/*
 * | 활용 알고리즘 | DP, DFS
 *
 * | 접근 방법 |
 *  1. n x m x k 사이즈의 배열을 -1로 초기화한다
 *    -1: 초기화(미방문), 0(불가), n(n행 m열의 글자가 목표 단어의 k번째 일 때 가능한 경우의 수)
 *  2. DFS로 탐색하며 배열을 갱신한다
 */

package a2405.study.week11;

import java.io.*;
import java.util.*;

public class bj_g3_2186_문자판 {
    static int N, M, K, C, answer = 0;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static int[][][] dp;
    static char[][] board;
    static String word;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N][M][K];
        board = new char[N][M];
        for(int n=0; n<N; n++){
            board[n] = br.readLine().toCharArray();
            for(int m=0; m<M; m++){ Arrays.fill(dp[n][m], -1); }
        }
        word = br.readLine();
        C = word.length();

        for(int n=0; n<N; n++){
            for(int m=0; m<M; m++){
                if(board[n][m] == word.charAt(0)){
                    dp[n][m][0] = 0;
                    DFS(n, m, 1);
                }
            }
        }
        System.out.println(answer);
    }

    static void DFS(int n, int m, int cnt){
        if(dp[n][m][cnt] != -1){ return; }
        if(cnt == C){
            answer++;
            return;
        }
        char next = word.charAt(cnt);
        for(int d=0; d<4; d++){
            int nr = n;
            int nc = m;
            for(int k=1; k<=K; k++){
                nr += dr[d];
                nc += dc[d];
                if(isOut(nr, nc)){ break; }
                if(board[nr][nc] == next){
                    dp[nr][nc][cnt] = cnt+1;
                    DFS(nr, nc, cnt+1);
                }
            }
        }
    }

    static boolean isOut(int r, int c){
        return r<0 || N<=r || c<0 || M<=c;
    }

}
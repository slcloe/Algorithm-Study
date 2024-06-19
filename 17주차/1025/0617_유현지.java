/*
 * | 활용 알고리즘 | brute-force
 *
 * | 접근 방법 |
 *  1. 좌표의 행, 열, 방향(8방), 행 증가 폭, 열 증가 폭, 현재 수를 파라미터로 받아서 완전 탐색 한다
 *  2. 탐색을 수행할 때마다 현재 수가 제곱수인지 판별하여 최대값을 갱신한다
 */

package a2406.study.week17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_g5_1025_제곱수_찾기 {
    static int N, M, answer = -1;
    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}, dc = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[][] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int [N][M];

        for(int n=0; n<N; n++){
            char[] line = br.readLine().toCharArray();
            for(int m=0; m<M; m++){
                arr[n][m] = line[m] - '0';
            }
        }

        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                for(int d=0; d<8; d++){
                    for(int rs=1; rs<=N; rs++){
                        for(int cs=1; cs<=M; cs++) {
                            DFS(r, c, d, rs, cs, arr[r][c]);
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static boolean isOut(int r, int c){
        return r<0 || N<=r || c<0 || M<=c;
    }

    static void DFS(int r, int c, int d, int rs, int cs, int now){
        if(Math.sqrt(now) % 1 == 0){
            answer = Math.max(answer, now);
        }
        int nr = r + dr[d] * rs;
        int nc = c + dc[d] * cs;
        if(isOut(nr, nc)){ return; }
        DFS(nr, nc, d, rs, cs, now * 10 + arr[nr][nc]);
    }
}
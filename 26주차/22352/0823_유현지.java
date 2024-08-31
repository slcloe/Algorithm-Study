/*
 * | 활용 알고리즘 | BFS
 *
 * | 접근 방법 |
 *  1. 배열 A와 B의 값이 다른 지점에서 BFS를 수행한다
 *  2. 탐색 지점마다 A의 해당 지점을 새 값으로 업데이트한다
 *  3. 탐색이 끝나면 A와 B를 비교한다
 */


package a2408.study.week26;

import java.io.*;
import java.util.*;

public class bj_g5_22352_항체_인식 {
    static int N, M;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static int[][] A, B;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        B = new int[N][M];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m=0; m<M; m++){
                A[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m=0; m<M; m++){
                B[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        for(int n=0; n<N; n++){
            for(int m=0; m<M; m++){
                if(A[n][m] != B[n][m]){
                    if(vaccine(n, m)){ System.out.println("YES"); }
                    else{ System.out.println("NO"); }
                    return;
                }
            }
        }
        System.out.println("YES");
    }

    static boolean isOut(int r, int c){
        return r<0 || N<=r || c<0 || M<=c;
    }

    static boolean isSame(){
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(A[r][c] != B[r][c]){ return false; }
            }
        }
        return true;
    }

    static boolean vaccine(int n, int m) {
        int oldValue = A[n][m];
        int newValue = B[n][m];
        boolean[][] visit = new boolean[N][M];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[] {n, m});
        visit[n][m] = true;
        A[n][m] = newValue;

        while(!queue.isEmpty()){
            int[] now = queue.pollFirst();
            int r = now[0];
            int c = now[1];
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(isOut(nr, nc) || visit[nr][nc] || A[nr][nc]!=oldValue){ continue; }
                queue.offerLast(new int[] {nr, nc});
                visit[nr][nc] = true;
                A[nr][nc] = newValue;
            }
        }
        return isSame();
    }
}

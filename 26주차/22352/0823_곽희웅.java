package com.example.algo;

import java.io.*;
import java.util.*;
public class 0823_곽희웅 {

    /*

    접근 방식 : 단순한 완전탐색 문제. 탐색 => 방문 처리 => 배열 생성 후 비교의 순서를 지켜서 풀이

    */


    static int N, M, before[][], after[][];
    static boolean[][] v;
    static int[] dx = {0, 1, 0 , -1};
    static int[] dy = {1, 0, -1 , 0};
    static boolean isValid;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        before = new int[N][M];
        after = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                after[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        v = new boolean[N][M];
        isValid = false;
        check(before);
        end: for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(isValid) break end;
                if(!v[i][j]) {
                    bfs(i, j);
                }
            }
        }
        if(isValid) System.out.print("YES");
        else System.out.print("NO");
    }

    static void bfs(int x, int y) {
        Deque<int[]> dq = new ArrayDeque<>();
        List<int[]> list = new ArrayList<>();
        dq.offer(new int[] {x, y});
        list.add(new int[] {x, y});
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !v[nx][ny] && before[nx][ny] == before[x][y]) {
                    dq.offer(new int[] {nx, ny});
                    list.add(new int[] {nx, ny});
                    v[nx][ny] = true;
                }
            }
        }

        int[][] newMap = makeMap(list);
        if(check(newMap)) isValid = true;
    }

    static int[][] makeMap(List<int[]> list) {
        int[][] temp = new int[N][M];
        int target = after[list.get(0)[0]][list.get(0)[1]];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                temp[i][j] = before[i][j];
            }
        }
        for(int[] cur : list) {
            temp[cur[0]][cur[1]] = target;
        }
        return temp;
    }

    static boolean check(int[][] temp) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(temp[i][j] != after[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}

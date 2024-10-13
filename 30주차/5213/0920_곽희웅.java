package com.example.algo;

import java.util.*;
import java.io.*;
public class Main_bj_5213 {

    /*

    접근 방식 : N, N-1개의 타일이 번갈아가며 배치되는 배열에서 탐색하는 문제. BFS를 활용하여 풀이했지만 반례를 찾지 못해 실패

     */
    static int N, map[][], minD, maxD, maxTile;
    static String minLoad, maxLoad;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] origin = new int[N][N*2];
        for(int i=0; i<N; i++) {
            int limit = i % 2 == 0 ? N : N-1;
            int d1 = i % 2 == 0 ? 0 : 1;
            int d2 = i % 2 == 0 ? 1 : 2;
            for(int j=0; j<limit; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                origin[i][j*2 + d1] = Integer.parseInt(st.nextToken());
                origin[i][j*2 + d2] = Integer.parseInt(st.nextToken());
            }
        }

        map = new int[N][2*(N-1)];
        for(int i=0; i<N; i++) System.arraycopy(origin[i], 1, map[i], 0, 2 * (N - 1));

        minD = Integer.MAX_VALUE;
        minLoad = "";
        maxTile = 0;
        maxD = Integer.MAX_VALUE;
        maxLoad = "";

        bfs();

        StringBuilder sb = new StringBuilder();
        if(minD == Integer.MAX_VALUE) {
            System.out.println(maxD+1);

            for(String cur : maxLoad.split("\\|")) {
                sb.append(cur).append(" ");
            }
        } else {
            System.out.println(minD+1);
            for(String cur : minLoad.split("\\|")) {
                sb.append(cur).append(" ");
            }
        }
        System.out.print(sb.toString().trim());
    }

    static void bfs() {
        PriorityQueue<String[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(Integer.parseInt(o1[2]), Integer.parseInt(o2[2])));
        pq.offer(new String[] {"0", "0", "0", "1"});

        boolean[][] v = new boolean[N][2*(N-1)];
        v[0][0] = true;

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};


        while(!pq.isEmpty()) {
            String[] cur = pq.poll();
            int nowX = Integer.parseInt(cur[0]);
            int nowY = Integer.parseInt(cur[1]);
            int nowCount = Integer.parseInt(cur[2]);

            if(nowX == N-1 && nowY == 2*(N-1)-1) {
                if(minD > nowCount) {
                    minD = nowCount;
                    minLoad = cur[3];
                }
                continue;
            }

            int curTile = calcTile(nowX, nowY);
            if(maxTile < curTile) {
                maxTile = curTile;
                maxD = nowCount;
                maxLoad = cur[3];
            }

            // 만약 방문하지 않은 같은 타일이 있다면 추가
            if(nowY > 0 && !v[nowX][nowY-1] && calcTile(nowX, nowY-1) == curTile) {
                v[nowX][nowY-1] = true;
                pq.offer(new String[] {cur[0], String.valueOf(nowY-1), cur[2], cur[3]});
            }
            if(nowY <= 2*(N-2) && !v[nowX][nowY+1] && calcTile(nowX, nowY+1) == curTile) {
                v[nowX][nowY+1] = true;
                pq.offer(new String[] {cur[0], String.valueOf(nowY+1), cur[2], cur[3]});
            }

            for(int i=0; i<4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];
                if(nx >= 0 && ny >= 0 && nx < N && ny < 2*(N-1) && map[nx][ny] == map[nowX][nowY] && !v[nx][ny]) {
                    v[nx][ny] = true;
                    int tileNum = calcTile(nx, ny);

                    pq.offer(new String[] {String.valueOf(nx), String.valueOf(ny), String.valueOf(nowCount+1), cur[3]+"|"+tileNum});
                }
            }
        }

    }

    static int calcTile(int x, int y) {
        if(x % 2 == 0) return (x/2 * (2*N-1)) + (y == 0 ? 1 : y == (N-1)*2-1 ? N : y % 2 == 1 ? y/2+2 : y/2+1);
        else return (x/2+1) * N + x/2 * (N-1)+ y/2+1;
    }
}


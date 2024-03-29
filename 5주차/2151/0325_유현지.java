/*
 * | 활용 자료구조 | Priority Queue, 3 dimension visited array
 *
 * | 접근 방법 |
 *  1. 가장 첫 번째 문의 좌표를 저장한다
 *  2. 해당 좌표에서 네 방향으로 시선을 향할 때의 모든 경우를 고려하여 탐색한다
 *  3. 이미 방문한 좌표이더라도, 해당 좌표를 방문했을 때 빛이 들어오는 방향에 따라 다른 경우이므로
 *     3차원 방문 배열을 사용해 1.행, 2.열, 3.방향을 모두 저장한다
 *
 * | 주의 사항 |
 *  1. 거울을 설치하지 않는 경우도 고려해야 한다
 *  2. 문에서 바라보는 방향에 대해 네 가지 모두 고려해야 한다
 */

package a2403.week5;

import java.io.*;
import java.util.*;

public class bj_g3_2151_거울_설치 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] house = new char[N][N];
        int startR = -1, startC = -1;
        for(int r=0; r<N; r++){
            house[r] = br.readLine().toCharArray();
            for(int c=0; c<N; c++){
                if(house[r][c] == '#' && startR == -1){
                    startR = r;
                    startC = c;
                }
            }
        }
        house[startR][startC] = '.';
        System.out.println(play(startR, startC, N, house));
    }

    static int play(int startR, int startC, int N, char[][] house){
        int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> { return o1[3] - o2[3]; });
        boolean[][][] v = new boolean[N][N][4];

        for(int d=0; d<4; d++){ pq.offer(new int[] {startR, startC, d, 0}); }
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int nowR = now[0];
            int nowC = now[1];
            int nowD = now[2];
            int nowM = now[3];
            v[nowR][nowC][nowD] = true;

            int nxtD, nxtR, nxtC;
            switch (house[nowR][nowC]){
                case '#':
                    return nowM;
                case '!':
                    nxtD = mirror1(nowD);
                    nxtR = nowR + dr[nxtD];
                    nxtC = nowC + dc[nxtD];
                    if(isIn(nxtR, nxtC, N) && house[nxtR][nxtC]!='*' && !v[nxtR][nxtC][nxtD]){
                        pq.offer(new int[] {nxtR, nxtC, nxtD, nowM+1});
                    }
                    nxtD = mirror2(nowD);
                    nxtR = nowR + dr[nxtD];
                    nxtC = nowC + dc[nxtD];
                    if(isIn(nxtR, nxtC, N) && house[nxtR][nxtC]!='*' && !v[nxtR][nxtC][nxtD]){
                        pq.offer(new int[] {nxtR, nxtC, nxtD, nowM+1});
                    }
                case '.':
                    nxtR = nowR + dr[nowD];
                    nxtC = nowC + dc[nowD];
                    if(isIn(nxtR, nxtC, N) && house[nxtR][nxtC]!='*' && !v[nxtR][nxtC][nowD]){
                        pq.offer(new int[] {nxtR, nxtC, nowD, nowM});
                    }
                    break;
            }
        }
        return -1;
    }

    static boolean isIn(int r, int c, int N){
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static int mirror1(int d){
        return d % 2 == 0? d + 1: d - 1;
    }

    static int mirror2(int d){
        return 3 - d;
    }
}
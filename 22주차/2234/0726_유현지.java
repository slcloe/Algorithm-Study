/*
 * | 활용 알고리즘 | BFS, BitMask
 *
 * | 접근 방법 |
 *  1. BFS를 돌며 각각의 영역을 방의 번호로 표시한다
 *     1-1. 벽이 있는지 여부는 비트마스킹으로 판별한다
 *     1-2. 한 구역의 BFS가 끝나면 {방 번호: 방 크기}로 Map에 저장한다
 *     1-3. 최대 방 크기를 갱신한다
 *  2. 모든 좌표를 돌며 벽 하나가 없을 때의 경우를 센다
 *     2-1. 사방 탐색을 하며 대상 방과 탐색 방의 번호가 다를 경우 두 방 크기를 합치고 최대값을 갱신한다
 */

package a2407.study.week22;

import java.io.*;
import java.util.*;

public class bj_g3_2234_성곽 {
    static int N, M, MAX = 0, CNT = 0, WALL_MAX = 0;
    static int[] dr = {0, -1, 0, 1}, dc = {-1, 0, 1, 0}, db = {1, 2, 4, 8};
    static int[][] castle;
    static boolean[][] visit;
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        castle = new int[N][M];
        visit = new boolean[N][M];
        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++){
                castle[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(!visit[r][c]){ BFS(r, c); }
            }
        }
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                NoWall(r, c);
            }
        }
        System.out.println(CNT);
        System.out.println(MAX);
        System.out.println(WALL_MAX);
    }

    static boolean isOut(int R, int C){
        return R<0 || N<=R || C<0 || M<=C;
    }

    static int BFS(int R, int C){
        int cnt = 0;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visit[R][C] = true;
        queue.offerLast(new int[] {R, C});
        while(!queue.isEmpty()){
            cnt++;
            int[] now = queue.pollFirst();
            int r = now[0];
            int c = now[1];
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(isOut(nr, nc) || visit[nr][nc]){ continue; }
                int bear = castle[r][c] & db[d];
                if(bear == 0){
                    visit[nr][nc] = true;
                    queue.offerLast(new int[] {nr, nc});
                }
            }
            castle[r][c] = CNT;
        }
        MAX = Math.max(MAX, cnt);
        map.put(CNT++, cnt);
        return cnt;
    }

    static void NoWall(int R, int C){
        int num1 = castle[R][C];
        int room = map.get(num1);
        for(int d=0; d<4; d++){
            int nr = R + dr[d];
            int nc = C + dc[d];
            if(isOut(nr, nc) || num1 == castle[nr][nc]){ continue; }
            int num2 = castle[nr][nc];
            WALL_MAX = Math.max(WALL_MAX, room + map.get(num2));
        }
    }
}

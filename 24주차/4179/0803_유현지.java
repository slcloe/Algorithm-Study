/*
 * | 활용 알고리즘 | BFS
 *
 * | 활용 자료구조 | Queue
 *
 * | 접근 방법 |
 *  1. 사방으로 불을 번지게 한다
 *     1-1. 불에 대해서도 visit 배열이 필요하다. 없으면 메모리 초과가 난다...
 *  2. 사방으로 방문 가능한 곳에 지훈이를 보낸다
 *  3. 지훈이가 맵의 가장자리에 위치하면 탈출 가능한 경우이므로 탐색을 끝낸다
 */

package a2408.study.week24;

import java.io.*;
import java.util.*;

public class bj_g3_4179_불 {
    static int R, C, answer = Integer.MAX_VALUE;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static char[][] map;
    static ArrayDeque<int[]> fires = new ArrayDeque<>();
    static boolean[][] fire_visit;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        fire_visit = new boolean[R][C];
        int[] jihoon = new int[2];
        for(int r=0; r<R; r++){
            char[] line = br.readLine().toCharArray();
            for(int c=0; c<C; c++){
                map[r][c] = line[c];
                if(map[r][c] == 'F'){
                    fires.offerLast(new int[] {r, c});
                    fire_visit[r][c] = true;
                }
                else if(map[r][c] == 'J'){ jihoon = new int[] {r, c}; }
            }
        }
        escape(jihoon);
        System.out.println(answer==Integer.MAX_VALUE? "IMPOSSIBLE": answer);
    }

    static boolean isOut(int r, int c){
        return r<0 || R<=r || c<0 || C<=c;
    }

    static boolean isEscaped(int r, int c){
        return r==0 || r==R-1 || c==0 || c==C-1;
    }

    static void moveFires(){
        int S = fires.size();
        for(int s=0; s<S; s++){
            int[] fire = fires.pollFirst();
            int r = fire[0];
            int c = fire[1];
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(isOut(nr, nc) || fire_visit[nr][nc] || map[nr][nc]=='#'){ continue; }
                map[nr][nc] = 'F';
                fires.offerLast(new int[] {nr, nc});
                fire_visit[nr][nc] = true;
            }
        }
    }

    static void escape(int[] jihoon){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[R][C];
        int jr = jihoon[0], jc = jihoon[1];
        int DEPTH = 0;
        queue.offerLast(new int[] {1, jr, jc});
        visit[jr][jc] = true;

        while (!queue.isEmpty()){
            int[] now = queue.pollFirst();
            int depth = now[0];
            int r = now[1];
            int c = now[2];

            if(isEscaped(r, c)){
                answer = depth;
                return;
            }
            if(DEPTH != depth){
                DEPTH = depth;
                moveFires();
            }
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(isOut(nr, nc) || visit[nr][nc] || map[nr][nc]!='.'){ continue; }
                queue.offerLast(new int[] {depth+1, nr, nc});
                visit[nr][nc] = true;
            }
        }
    }
}

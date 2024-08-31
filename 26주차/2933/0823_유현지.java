/*
 * | 활용 알고리즘 | BFS, Implementation
 *
 * | 접근 방법 |
 *  1. 번갈아가며 막대를 던진다
 *  2. 막대에 파괴된 미네랄이 있다면 해당 지점으로부터 사방에 있는 미네랄들이 바닥에 붙어 있는지 확인한다
 *  3. 붙어 있지 않다면 바닥을 만나거나 미네랄을 만날 때까지 끌어내린다
 *
 *  골드 1 힘들어요
 */

package a2408.study.week26;

import java.io.*;
import java.util.*;

public class bj_g1_2933_미네랄 {
    static int R, C;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static char[][] cave;
    static ArrayList<int[]> cluster = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cave = new char[R][C];
        for(int r=0; r<R; r++){
            cave[r] = br.readLine().toCharArray();
        }
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++){
            int length = R - Integer.parseInt(st.nextToken());
            play(length, n%2);
        }
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                sb.append(cave[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int throwStick(int length, int player) {
        for(int c = player == 0? 0: C-1, cnt = 0; cnt<C ;c = player == 0? c+1: c-1, cnt++){
            if(cave[length][c] == 'x'){
                cave[length][c] = '.';
                return c;
            }
        }
        return -1;
    }

    static boolean isOut(int r, int c){
        return r<0 || R<=r || c<0 || C<=c;
    }

    static boolean checkFloor(int pr, int pc){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[R][C];
        queue.offerLast(new int[] {pr, pc});
        visit[pr][pc] = true;
        cluster.clear();
        cluster.add(new int[] {pr, pc});
        while(!queue.isEmpty()){
            int[] now = queue.pollFirst();
            int r = now[0];
            int c = now[1];
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(isOut(nr, nc) || visit[nr][nc] || cave[nr][nc]=='.'){ continue; }
                if(nr == R-1){ return true; }
                queue.offerLast(new int[] {nr, nc});
                visit[nr][nc] = true;
                cluster.add(new int[] {nr, nc});
            }
        }
        return false;
    }

    static void copyArr(char[][] from, char[][] to){
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                to[r][c] = from[r][c];
            }
        }
    }

    static void down(){
        cluster.sort(((o1, o2) -> o2[0] - o1[0]));
        char[][] tmpCave = new char[R][C];
        copyArr(cave, tmpCave);
        boolean isGoDown = true;

        while(isGoDown){
            for(int[] now: cluster){
                int r = now[0] + 1;
                int c = now[1];
                if(r == R || tmpCave[r][c] == 'x'){
                    isGoDown = false;
                    break;
                }
                now[0]++;
                tmpCave[r-1][c] = '.';
                tmpCave[r][c] = 'x';
            }
            if(isGoDown){ copyArr(tmpCave, cave); }
        }
    }

    static void play(int length, int player) {
        int c = throwStick(length, player);
        if(c == -1){ return; }
        for(int d=0; d<4; d++){
            int nr = length + dr[d];
            int nc = c + dc[d];
            if(isOut(nr, nc) || cave[nr][nc] == '.'){ continue; }
            if(checkFloor(nr, nc)){ continue; }
            down();
        }
    }
}
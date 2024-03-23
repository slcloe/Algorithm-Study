import java.io.*;
import java.util.*;
public class 0323_곽희웅 {

    /*
    접근 방식 : 최대 50 * 50 크기의 방을 탐색하는 문제. bfs + dfs를 결합해서 풀어도 좋을 듯함.
    주안점
    1. 기본적으로 상하좌우로 나가는데, 만약 상 or 하로 받았으면 좌 or 우로 가야하는 것이다. 대각선이 아님.
    2. 거울을 설치하는 곳도 시선이 통과할 수 있기 때문에 벽이 있을 때만 탐색에서 제외해줘야 함.
     */

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, mirror;
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        int[] door = new int[2];
        for(int i=0; i<N; i++) {
            char[] S = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                map[i][j] = S[j];
                if(map[i][j] == '#') {
                    door[0] = i;
                    door[1] = j;
                }
            }
        }
        mirror = Integer.MAX_VALUE;
        setMirror(door);
        System.out.println(mirror);
    }
    static void setMirror(int[] door) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        boolean[][] v = new boolean[N][N];
        v[door[0]][door[1]] = true;
        int mag = 1;
        boolean[] ban = new boolean[4];
        boolean flag;
        while(true) {
            flag = false;
            for(int i=0; i<4; i++) {
                if(ban[i]) continue;
                int nx = door[0] + dx[i] * mag;
                int ny = door[1] + dy[i] * mag;
                if(nx >= 0 && ny >= 0 && nx < N && ny <N && !v[nx][ny]) {
                    flag = true;
                    if(map[nx][ny] == '*') {
                        ban[i] = true;
                        continue;
                    }
                    if(map[nx][ny] == '#') {
                        mirror = 0;
                        return;
                    }
                    if(map[nx][ny] == '!') {
                        pq.offer(new int[] {nx, ny, 1, i % 2 == 0 ? 1 : 0});
                        v[nx][ny] = true;
                    }
                }
            }
            if(flag) mag++;
            else break;
        }
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int[] dir = {cur[3], cur[3] + 2};
            mag = 1;
            ban = new boolean[4];
            while(true) {
                flag = false;
                for(int i : dir) {
                    if(ban[i]) continue;
                    int nx = cur[0] + dx[i] * mag;
                    int ny = cur[1] + dy[i] * mag;
                    if(nx >= 0 && ny >= 0 && nx < N && ny <N && !v[nx][ny]) {
                        flag = true;
                        if(map[nx][ny] == '*') {
                            ban[i] = true;
                            continue;
                        }
                        if(map[nx][ny] == '#') {
                            mirror = Math.min(mirror, cur[2]);
                            return;
                        }
                        if(map[nx][ny] == '!') {
                            pq.offer(new int[] {nx, ny, cur[2]+1, i % 2 == 0 ? 1 : 0});
                            v[nx][ny] = true;
                        }
                    }
                }
                if(flag) mag++;
                else break;
            }
        }
    }
}

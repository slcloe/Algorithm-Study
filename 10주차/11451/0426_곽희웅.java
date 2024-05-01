import java.io.*;
import java.util.*;

public class Main {

    /*
    접근 방식 : 경로를 기억해야하기 때문에 dfs를 먼저 시도했지만, 방문 처리가 잘 되지 않아 bfs로 변경해서 풀었음

    주안점
    1. 두 점의 위치를 방문 처리해야하기 때문에 4차원으로 각 P를 방문처리
    2. bfs이기 때문에 class를 만들어서 경로를 기억하게 만듦
     */
    static int N, M, answer;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[] dir = {'E', 'S', 'W', 'N'};
    static String answerLoad;
    static boolean[][][][] v;

    static class World {

        int x1, x2, y1, y2, time;
        StringBuilder sb;

        public World(int x1, int y1, int x2, int y2, int time) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.time = time;
            sb = new StringBuilder();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            int[] P1 = new int[2];
            int[] P2 = new int[2];
            boolean pFlag = false;
            for (int i = 0; i < N; i++) {
                String S = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = S.charAt(j);
                    if (map[i][j] == 'P') {
                        if (!pFlag) {
                            P1[0] = i;
                            P1[1] = j;
                            pFlag = true;
                        } else {
                            P2[0] = i;
                            P2[1] = j;
                        }
                    }
                }
            }
            answer = Integer.MAX_VALUE;
            answerLoad = "IMPOSSIBLE";
            v = new boolean[N][M][N][M];
            v[P1[0]][P1[1]][P2[0]][P2[1]] = true;
            bfs(P1, P2, 0);
            if (answerLoad.equals("IMPOSSIBLE")) sb.append(answerLoad).append("\n");
            else sb.append(answer + " " + answerLoad).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void bfs(int[] P1, int[] P2, int move) {
        Deque<World> dq = new ArrayDeque<>();
        dq.offer(new World(P1[0], P1[1], P2[0], P2[1], move));
        while (!dq.isEmpty()) {
            World now = dq.poll();
            if (now.time > answer) continue;
            if (checkSame(now)) {
                if (answer > now.time) {
                    answer = now.time;
                    answerLoad = now.sb.toString();
                }
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int[] NP1 = checkP(new int[]{now.x1, now.y1}, now.x1 + dx[i], now.y1 + dy[i]);
                if (NP1[0] == -1 && NP1[1] == -1) continue;
                
                int[] NP2 = checkP(new int[]{now.x2, now.y2}, now.x2 + dx[i], now.y2 + dy[i]);
                if (NP2[0] == -1 && NP2[1] == -1) continue;
                
                if (v[NP1[0]][NP1[1]][NP2[0]][NP2[1]]) continue;
                
                v[NP1[0]][NP1[1]][NP2[0]][NP2[1]] = true;
                World newWorld = new World(NP1[0], NP1[1], NP2[0], NP2[1], now.time + 1);
                newWorld.sb.append(now.sb);
                newWorld.sb.append(dir[i]);
                dq.offer(newWorld);
            }
        }
    }

    static boolean checkSame(World now) {
        return now.x1 == now.x2 && now.y1 == now.y2;
    }

    static int[] checkP(int[] P, int x, int y) {
        int[] arr = new int[2];
        if (x == N) x = 0;
        else if (x == -1) x = N - 1;
        // y 좌표가 경계일 경우
        if (y == M) y = 0;
        else if (y == -1) y = M - 1;
        
        // 만약 이동했는데 벽이라면 다시 원래 위치로
        if (map[x][y] == 'X') {
            arr[0] = P[0];
            arr[1] = P[1];
        } else if (map[x][y] == 'G') {
            // 만약 유령이라면 못 가기 때문에 continue;
            arr[0] = -1;
            arr[1] = -1;
        } else {
            arr[0] = x;
            arr[1] = y;
        }
        return arr;
    }
}

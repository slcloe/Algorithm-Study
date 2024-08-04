import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[문제 해설]
1. 1분을 계산할 때, F를 먼저 계산 후 J를 계산하기 위해서
    각각, offerFirst, offerLast로 큐에 넣는다.
2. bfs로 탐색한다.
    J를 탐색할 때, 다음 방문할 칸이 범위 밖이면 depth 를 출력하고 함수를 끝낸다.
3. 모두 탐색했음에도 결과가 나오지 않았다므녀 IMPOSSIBLE 을 출력하고 함수를 끝낸다.
*/

public class Main {

    static int N, M;
    static char[][] map;
    static ArrayDeque<int[]> queue = new ArrayDeque<>();

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean v[][];

    static void calEscape() {

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] >= 0) { // J
                for (int i = 0; i < 4; i++) {
                    int tx = dx[i] + cur[1];
                    int ty = dy[i] + cur[2];

                    if (tx < 0 || tx >= N || ty < 0 || ty >= M) {
                        System.out.println(cur[0] + 1);
                        return;
                    }
                    if (map[tx][ty] == 'F' || map[tx][ty] == '#') continue;
                    if (v[tx][ty]) continue;

                    v[tx][ty] = true;
                    queue.offer(new int[] {cur[0] + 1, tx, ty});
                }
            }
            else { // F
                for (int i = 0; i < 4; i++) {
                    int tx = dx[i] + cur[1];
                    int ty = dy[i] + cur[2];

                    if (tx < 0 || tx >= N || ty < 0 || ty >= M) continue;
                    if (map[tx][ty] == 'F' || map[tx][ty] == '#') continue;

                    map[tx][ty] = 'F';
                    queue.offer(new int[]{cur[0], tx, ty});
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine() ," " );
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        v = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'J') {
                    queue.offerLast(new int[] {0, i, j}); // depth, i, j, depth
                    v[i][j] = true;
                }
                else if (map[i][j] == 'F') {
                    queue.offerFirst(new int[] {-1, i, j});
                }
            }
        }
        calEscape();

    }

}



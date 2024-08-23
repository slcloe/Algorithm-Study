import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
    bfs 문제
    항체를 맞기 전과 항체를 맞은 후의 테이블을 비교하여 기존 범위와 변한 범위가 같으면 CPCU-1202 백신이다.

    단, 다음을 고려해서 구현하자.
    1. 항체는 한 번만 적용된다. 두 번 이상의 항체 적용 범위가 나타난다면 오답.
    2. 항체가 퍼졌던 칸들의 데이터 값이 테이블에 이미 존재하는 값으로 바뀔 수도 있다.
        따라서, 항체가 적용된 범위는 좌표 값이 이전과 달라진 범위이다. 탐색을 하면서 (과거 값) != (현재 값) 인지 확인이 추가로 필요하다.
 */

public class 항체인식_0823_전영빈 {

    static int N;
    static int M;
    static int[][] past;
    static int[][] forward;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int flag = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        past = new int[N][M];
        forward = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                past[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                forward[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String sol = "YES";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (past[i][j] != forward[i][j]) {
                    sol = bfs(i, j, past[i][j], forward[i][j]);
                }
            }
        }

        System.out.println(sol);
    }

    static String bfs(int y, int x, int alpha, int beta) {
        flag++;

        if (flag >= 2) {
            return "NO";
        }

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y, x, 0});
        queue.offer(new int[]{y, x, 1});
        past[y][x] = -1;
        forward[y][x] = -1;

        int legacy = 0;
        int progress = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[2] == 0) {
                legacy++;

                for (int i = 0; i < 4; i++) {
                    int ny = current[0] + dy[i];
                    int nx = current[1] + dx[i];

                    if (ny >= 0 && ny < N && nx >= 0 && nx < M && past[ny][nx] == alpha) {
                        queue.offer(new int[]{ny, nx, 0});
                        past[ny][nx] = -1;
                    }
                }

            } else {
                progress++;

                for (int i = 0; i < 4; i++) {
                    int ny = current[0] + dy[i];
                    int nx = current[1] + dx[i];

                    if (ny >= 0 && ny < N && nx >= 0 && nx < M && forward[ny][nx] == beta && past[ny][nx] != forward[ny][nx]) {
                        queue.offer(new int[]{ny, nx, 1});
                        forward[ny][nx] = -1;
                    }
                }
            }

        }

        if (legacy != progress) {
            return "NO";
        }

        return "YES";
    }
}

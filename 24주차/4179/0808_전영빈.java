import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
    그냥 bfs 문제.
    초기 지점이 사람은 딱 한 곳만 존재하지만 불은 여러 곳이 존재할 수 있다는 것에 주의.
*/

public class 불_0808_전영빈 {

    static int R;
    static int C;
    static char[][] board;
    static boolean[][] person;
    static boolean[][] fire;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static ArrayDeque<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        person = new boolean[R][C];
        fire = new boolean[R][C];

        int y = 0, x = 0;
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'J') {
                    y = i;
                    x = j;
                }

                if (board[i][j] == 'F') {
                    queue.offer(new int[]{0, i, j, 1});
                    fire[i][j] = true;
                }
            }
        }

        int sol = bfs(y, x);
        if (sol == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(sol);
        }
    }

    static int bfs(int y, int x) {

        queue.offer(new int[]{1, y, x, 1});
        person[y][x] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[0] == 0) {
                // fire
                for (int i = 0; i < 4; i++) {
                    int ny = current[1] + dy[i];
                    int nx = current[2] + dx[i];

                    if (ny >= 0 && ny < R && nx >= 0 && nx < C
                            && board[ny][nx] != '#' && !fire[ny][nx]) {
                        fire[ny][nx] = true;
                        queue.offer(new int[]{0, ny, nx, current[3] + 1});
                    }
                }
            } else {
                // person
                if (current[1] == 0 || current[1] == R - 1 || current[2] == 0 || current[2] == C - 1) {
                    return current[3];
                }

                for (int i = 0; i < 4; i++) {
                    int ny = current[1] + dy[i];
                    int nx = current[2] + dx[i];

                    if (ny >= 0 && ny < R && nx >= 0 && nx < C
                            && board[ny][nx] != '#' &&!person[ny][nx] && !fire[ny][nx]) {
                        person[ny][nx] = true;
                        queue.offer(new int[]{1, ny, nx, current[3] + 1});
                    }
                }
            }

        }

        return -1;
    }
}

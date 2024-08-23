import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    다음에 조건에 맞춰 구현하자.
    1. 파괴된 미네랄의 상하좌우에 미네랄이 있으면 해당 미네랄을 기준으로 클러스터를 탐색한다. => bfs
    2. 탐색된 클러스터가 바닥에 닿아 있지 않다면 추락하는 클러스터이다.
    3. 클러스터의 각 열 중 하나가 반드시 바닥 혹은 다른 클러스터 위로 떨어지게 된다.
        따라서, 탐색한 영역에서 열에 따른 최하 값을 관리해야 한다. => Map을 통해 관리.
    4. 떨어지는 클러스터를 구현할 때 y값 좌표가 큰 순서대로 처리해야 한다.
        y값 좌표가 작은 순서대로 처리하면 뒷 연산에 의해 이전 연산의 결과가 삭제될 수 있다.
 */

public class 미네랄_8023_전영빈 {

    static int R;
    static int C;
    static char[][] board;
    static boolean[][] visited;
    static Map<Integer, Integer> bottom;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            boolean side = (i % 2 == 0);
            int[] object = throwStick(side, Integer.parseInt(st.nextToken()));

            if (object != null) {
                for (int j = 0; j < 4; j++) {
                    effect(object[0] + dy[j], object[1] + dx[j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] throwStick(boolean side, int height) {
        int h = R - height;
        int w = side ? 0 : C - 1;
        int weight = side ? 1 : -1;

        while (w >= 0 && w < C) {
            if (board[h][w] == 'x') {
                board[h][w] = '.';
                return new int[]{h, w};
            }

            w += weight;
        }

        return null;
    }

    static void effect(int y, int x) {
        if (y < 0 || y >= R || x < 0 || x >= C || board[y][x] == '.') {
            return;
        }

        visited = new boolean[R][C];
        bottom = new HashMap<>();

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y, x});
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[0] == R - 1) {
                return;
            }

            bottom.put(current[1], Math.max(bottom.getOrDefault(current[1], 0), current[0]));

            for (int i = 0; i < 4; i++) {
                int ny = current[0] + dy[i];
                int nx = current[1] + dx[i];

                if (ny >= 0 && ny < R && nx >= 0 && nx < C && board[ny][nx] == 'x' &&!visited[ny][nx]) {
                    queue.offer(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }

        fallingDown();
    }

    static void fallingDown() {
        boolean flag = false;
        int length = 1;

        Set<Integer> keys = bottom.keySet();

        while (true) {
            for (Integer k : keys) {
                int height = bottom.get(k);
                if (height + length >= R || board[height + length][k] == 'x') {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                length--;
                break;
            }

            length++;
        }

        for (int i = R-1; i >= 0 ; i--) {
            for (int j = C-1; j >= 0 ; j--) {
                if (visited[i][j]) {
                    board[i][j] = '.';
                    board[i + length][j] = 'x';
                }
            }
        }
    }
}

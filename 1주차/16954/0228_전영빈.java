import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    /*
        배열의 높이를 8이 아닌 9로 주었다.
        배열의 제일 위 칸을 .으로 채워 넣고 이동이 8회 이상 진행되어
        벽이 모두 사라지게 될 경우에 대한 체크를 편하게 하기 위함.
     */
    static char[][] board = new char[9][8];

    /*
        움직일 수 있는 방향은 상하좌우, 대각선, 제자리. 총 아홉 가지 이다.
     */
    static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1, 0};
    static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 8; i++) {
            board[0][i] = '.';
        }

        for (int i = 1; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{8, 0, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[0] == 1 && current[1] == 7) {
                return 1;
            }

            for (int i = 0; i < 9; i++) {
                int ny = current[0] + dy[i];
                int nx = current[1] + dx[i];

                /*
                    내가 이번에 이동할 위치의 y 좌표와 내가 이동할 위치의 바로 위의 y 좌표.
                    벽이 이동할 때마다 한 칸씩 아래로 움직이므로 내가 이번에 이동할 위치에 벽이 있는지 확인하기 위해서는
                    board[이동할 y 좌표 - 지금까지 이동한 횟수][이동할 x 좌표] 에 벽이 존재하는지 확인해야 한다.
                 */
                int beforeWave = Math.max(0, ny - current[2]);
                int afterWave = Math.max(0, ny - (current[2] + 1));

                // 내가 이번에 이동할 위치와 그 위치의 바로 위까지 벽이 존재하지 않아야 이동할 수 있다.
                if (ny < 1 || ny >= 9 || nx < 0 || nx >= 8 || board[beforeWave][nx] != '.' || board[afterWave][nx] != '.') {
                    continue;
                }

                /*
                    일반적인 bfs에서는 이동한 뒤 해당 좌표에 대해 방문 처리를 하지만 해당 문제에서는 해서는 안된다.
                    방문 처리를 하게 되면 제자리 이동이 불가능하기 때문.
                 */
                queue.offer(new int[]{ny, nx, current[2] + 1});
            }
        }

        return 0;
    }
}

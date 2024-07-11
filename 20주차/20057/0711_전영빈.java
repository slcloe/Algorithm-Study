import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    빡구현 문제.
    모래를 옮기는 것에 주의해서 풀이하면 그렇게 어려운 문제는 아닌 것 같다.
    주의해야 할 점은 (알파 위치에 옮기는 모래 양) = (기존 모래 양) - (토네이도에 의해 옮겨진 모래의 양) 이라는 것.
    본인처럼 알파 위치에 옮기는 모래의 양을 55%로 두지 않길 바람.
 */

public class 마법사상어와토네이도_0711_전영빈 {

    static int N;
    static int[][] board;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    static int y;
    static int x;
    static int direction;
    static int range;

    static int solve;
    static int leave;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        y = N / 2;
        x = N / 2;
        direction = 0;
        range = 1;

        solve = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (y != 0 || x != 0) {
            for (int i = 0; i < range; i++) {
                move();
                if (y == 0 && x == 0) {
                    break;
                }
            }

            changeDirection();
        }

        System.out.println(solve);
    }

    static void move() {
        y += dy[direction];
        x += dx[direction];

        effect();
    }

    static void effect() {
        int left = (direction + 3) % 4;
        int right = (direction + 1) % 4;

        leave = 0;

        // 10%
        moveSand(y + dy[direction] + dy[left], x + dx[direction] + dx[left], board[y][x] * 10 / 100);
        moveSand(y + dy[direction] + dy[right], x + dx[direction] + dx[right], board[y][x] * 10 / 100);

        // 7%
        moveSand(y + dy[left], x + dx[left], board[y][x] * 7 / 100);
        moveSand(y + dy[right], x + dx[right], board[y][x] * 7 / 100);

        // 5%
        moveSand(y + dy[direction] * 2, x + dx[direction] * 2, board[y][x] * 5 / 100);

        // 2%
        moveSand(y + dy[left] * 2, x + dx[left] * 2, board[y][x] * 2 / 100);
        moveSand(y + dy[right] * 2, x + dx[right] * 2, board[y][x] * 2 / 100);

        // 1%
        moveSand(y - dy[direction] + dy[left], x - dx[direction] + dx[left], board[y][x] / 100);
        moveSand(y - dy[direction] + dy[right], x - dx[direction] + dx[right], board[y][x] / 100);

        // alpha
        moveSand(y + dy[direction], x + dx[direction], board[y][x] - leave);

        board[y][x] = 0;
    }

    static void moveSand(int y, int x, int amount) {
        if (y < 0 || y >= N || x < 0 || x >= N) {
            solve += amount;
        } else {
            board[y][x] += amount;
        }
        leave += amount;
    }

    static void changeDirection() {
        if (direction % 2 == 1) {
            range++;
        }

        direction = (direction + 1) % 4;
    }
}

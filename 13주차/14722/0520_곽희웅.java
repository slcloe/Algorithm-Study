import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_14722 {

    /*
    접근 방식 : 처음엔 그리디 + bfs로 해결할 수 있을 줄 알았는데, 선택하지 않는 경우의 수도 있어 dp로 풀어야 함
               이후에 3차원으로 해야한다는 것은 알았는데, 어떻게 해야할지 모르겠어서 답을 봤음
    
    주안점
    1. 0, 1, 2를 각각 3차원으로 나누어 계산
    2. 현재 우유를 먹을 수 있든 없든 같은 종류의 우유는 항상 업데이트할 수 있는 가장 큰 수로 업데이트돼야 함
    3. check의 else문을 milk로 넣으면, 이상한 우유 값이 갱신되기 때문에 주의
     */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = st.nextToken().charAt(0) - '0';
            }
        }

        int[][][] dp = new int[N + 1][N + 1][3];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                check(dp, i, j, map[i][j]);
            }
        }
        System.out.println(Math.max(Math.max(dp[N][N][0], dp[N][N][1]), dp[N][N][2]));
    }

    static void check(int[][][] dp, int x, int y, int milk) {
        int before = milk == 0 ? 2 : milk - 1;

        if (milk == 0) {
            dp[x][y][milk] = Math.max(dp[x - 1][y][before] + 1, dp[x][y - 1][before] + 1);
        } else {
            dp[x][y][0] = Math.max(dp[x - 1][y][0], dp[x][y - 1][0]);
        }

        if (milk == 1 && dp[x][y][before] > dp[x][y][milk]) {
            dp[x][y][milk] = Math.max(dp[x - 1][y][before] + 1, dp[x][y - 1][before] + 1);
        } else {
            dp[x][y][1] = Math.max(dp[x - 1][y][1], dp[x][y - 1][1]);
        }

        if (milk == 2 && dp[x][y][before] > dp[x][y][milk]) {
            dp[x][y][milk] = Math.max(dp[x - 1][y][before] + 1, dp[x][y - 1][before] + 1);
        } else {
            dp[x][y][2] = Math.max(dp[x - 1][y][2], dp[x][y - 1][2]);
        }
    }
}

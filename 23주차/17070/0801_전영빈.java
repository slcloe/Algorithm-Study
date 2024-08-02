import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
(N,N)에 파이프를 놓는 방법은
1. (N-1, N-1)까지 파이프를 놓는 방법의 개수
2. (N-1, N)까지 파이프를 놓는 방법의 개수
3. (N, N-1)까지 파이프를 놓는 방법의 개수
위의 세 가지의 경우의 수에 따라 정할 수 있다.

전형적인 dp 문제.
단, 파이프의 회전은 45도만 회전시킬 수 있으므로 이전 단계에서 가능한 회전 반경에 있는 경우만 가져올 수 있다.
2차원 배열이고 각 요소에서 가질 수 있는 경우의 수가 세 가지 (가로, 세로, 대각) 이므로 3차원 배열을 이용하여 dp를 진행햐자.
 */

public class 파이프옮기기1_0801_전영빈 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N+1][N+1];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][] dp = new long[3][N + 1][N + 1];
        dp[0][1][2] = 1L;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N ; j++) {
                if (board[i][j] == 1 || dp[0][i][j] != 0) {
                    continue;
                }

                dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1];
                dp[1][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j];
                if (board[i-1][j] == 0 && board[i][j-1] == 0){
                    dp[2][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
                }
            }
        }

        System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
    }
}

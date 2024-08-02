import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[문제풀이]

1. 3차원 dp 배열을 사용한다.
    [i][j][N]
    i, j : arr의 좌표
    N : 0 :가로, 1 :세로, 2: 대각선
2. dp 점화식
    0 : 가로일 경우
        대각선, 가로 파이프 다음으로 올 수 있다.
    1 : 세로일 경우
        대각선, 세로 파이프 다음으로 올 수 있다.
    2 : 대각선일 경우
        대각선, 가로, 세로 파이프 다음으로 올 수 있다.
        하지만, 3개의 지점 중 벽이 있으면 파이프를 놓을 수 없다.
3. 결과
    가로, 세로, 대각선일 경우 N,N에 도착한 수를 모두 합한다.
 */

public class Main {

    static int N;
    static int[][][] dp;
    static int[][] arr;

    static boolean isRange(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < N) return true;
        return false;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N][N][3];
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 0 :가로, 1 :세로, 2: 대각선
        dp[0][1][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (arr[i][j] == 1) continue;

                if (isRange(i + 1, j + 1)
                        && arr[i + 1][j] == 0 && arr[i][j + 1] == 0 && arr[i + 1][j + 1] == 0) {
                    dp[i + 1][j + 1][2] += dp[i][j][0] + dp[i][j][1] + dp[i][j][2];
                }

                if (isRange(i , j + 1) && arr[i][j + 1] == 0) {
                    dp[i][j + 1][0] += dp[i][j][0] + dp[i][j][2];
                }

                if (isRange(i + 1, j) && arr[i + 1][j] == 0) {
                    dp[i + 1][j][1] += dp[i][j][1] + dp[i][j][2];
                }
            }

//            for(int[][] a : dp){
//                for(int[] ar: a) {
//                    System.out.println(Arrays.toString(ar));
//                }
//                System.out.println();
//            }
        }

        N--;
        System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);


    }

}



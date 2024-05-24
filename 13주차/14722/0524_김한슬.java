import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
문제이해를 잘못했음.
무조건 연속적으로 우유를 마셔야한다고 생각해서  N^2 으로 풀수 있다고 생각함.

만약 제대로 푼다면 dfs 를 통해 이전에 먹었던 우유의 idx를 저장해서
지금 위치까지 먹은 우유의 최대개수를 dp 배열에 저장해놓으면 될것같음.

 */


public class Main {

    static int N;
    static int[][] arr;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][N];
        int result = 0;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 2; k++) {
                    int tx = dx[k] + i;
                    int ty = dy[k] + j;

                    if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
                    System.out.println(i + " " + j  + " " + (arr[i][j] + 1) % 3+ arr[tx][ty] );

                    if ((arr[i][j] + 1) % 3 == arr[tx][ty]) {
                        dp[tx][ty] = Math.max(dp[tx][ty], dp[i][j] + 1);
                        result = Math.max(dp[tx][ty], result);
                    }

                }
            }
        }

        for(int[] ar: dp){
            System.out.println(Arrays.toString(ar));
        }
        System.out.println(result);

    }
}

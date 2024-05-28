import java.io.*;
import java.util.*;


public class BOJ_14722 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int map[][] = new int[N][N];
        int dp[][][]= new int[N][N][3];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        if(map[0][0] == 0 ) dp[0][0][0] = 1;

        for(int i=1;i<N;i++){
            int milk = map[0][i];

            dp[0][i][0] = milk == 0 ? dp[0][i-1][2] + 1 : dp[0][i-1][0];
            dp[0][i][1] = milk == 1 && dp[0][i][2] < dp[0][i][0] ? dp[0][i-1][0] + 1 : dp[0][i-1][1];
            dp[0][i][2] = milk == 2 && dp[0][i][0] < dp[0][i][1] ? dp[0][i - 1][1] + 1 : dp[0][i - 1][2];
        }


        for (int i = 1; i < N; i++) {
            int milk = map[i][0];

            dp[i][0][0] = milk == 0 ? dp[i - 1][0][2] + 1 : dp[i - 1][0][0];
            dp[i][0][1] = milk == 1 && dp[i][0][2] < dp[i][0][0] ? dp[i - 1][0][0] + 1 : dp[i - 1][0][1];
            dp[i][0][2] = milk == 2 && dp[i][0][0] < dp[i][0][1] ? dp[i - 1][0][1] + 1 : dp[i - 1][0][2];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                int milk = map[i][j];
                dp[i][j][0] = milk == 0 ? Math.max(dp[i][j - 1][2] + 1, dp[i - 1][j][2] + 1) : Math.max(dp[i][j - 1][0], dp[i - 1][j][0]);
                dp[i][j][1] = milk == 1 && dp[i][j][2] < dp[i][j][0] ? Math.max(dp[i][j - 1][0] + 1, dp[i - 1][j][0] + 1) : Math.max(dp[i][j - 1][1], dp[i - 1][j][1]);
                dp[i][j][2] = milk == 2 && dp[i][j][0] < dp[i][j][1] ? Math.max(dp[i][j - 1][1] + 1, dp[i - 1][j][1] + 1) : Math.max(dp[i][j - 1][2], dp[i - 1][j][2]);
            }
        }
        System.out.println(Math.max(dp[N-1][N-1][0], Math.max(dp[N-1][N-1][1],dp[N-1][N-1][2])));

    }
}
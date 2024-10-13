import java.util.*;
import java.io.*;
public class Main_bj_7579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        int[] keys = new int[N];
        int[] values = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) keys[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int maxValue = 0;
        for(int i=0; i<N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
            maxValue += values[i];
        }

        for(int i=0; i<N; i++) pq.offer(new int[] {keys[i], values[i]});
        for(int i=0; i<N; i++) {
            int[] cur = pq.poll();
            keys[i] = cur[0];
            values[i] = cur[1];
        }

        int[][] dp = new int[N][maxValue+1];

        // 초기값 설정
        for(int i=1; i<N; i++) Arrays.fill(dp[i], 1_100_000_000);
        for(int j=values[0]; j<=maxValue; j++) dp[0][j] = keys[0];

        for(int i=1; i<N; i++) {
            for(int j=0; j<=maxValue; j++) {
                if(j - values[i] >= 0) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-values[i]] + keys[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        end: for(int i=0; i<=maxValue; i++) {
            for(int j=0; j<N; j++) {
                if(dp[j][i] < 1_100_000_000 && dp[j][i] >= M) {
                    System.out.print(i);
                    break end;
                }
            }
        }
    }
}

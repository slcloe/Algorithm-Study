import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
dp 배열을 현재 존재하는 벌레의 수라고 가정하자.
오늘을 N이라고 하면
생산 가능한 벌레의 수는 (태어난 지 a일된 벌레의 수 - 태어난 지 b일된 벌레의 수) = dp[N - a] + dp[N - b]
실제 벌레의 수는 (현재까지 생산한 벌레의 수 - d일 전까지 생산한 벌레의 수) = dp[N] - dp[N-d]
 */

public class 짚신벌레_0703_전영빈 {

    static int a;
    static int b;
    static int d;
    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        dp = new int[N+1];
        dp[0] = 1;
        for (int i = 1; i < N+1; i++) {
            dp[i] = dp[i - 1];
            if (i - a >= 0) {
                dp[i] = (dp[i] + dp[i - a]) % 1000;
            }
            if (i - b >= 0) {
                dp[i] = (dp[i] - dp[i - b] + 1000) % 1000;
            }
        }

        if (N >= d) {
            System.out.println((dp[N] - dp[N - d] + 1000) % 1000);
        } else {
            System.out.println(dp[N]);
        }
    }
}

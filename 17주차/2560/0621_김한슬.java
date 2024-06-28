import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



/*


1. a 이전까지는 생식이 일어나지 않음.
2. a ~ b 가지 생식을 진행할 수 있다. 이때는 죽지 않기 때문에 무조건 증가함. 
    => 전날 벌레 수 + 새로 태어난 벌레 수
    점화식 : dp[i] = dp[i-1] + dp[i-a]
3. b 이후는 생식 하지 않음. => 벌레 수가 증가하지 않음.
    => 전날 벌레 수 + 새로 테어난 벌레 수 - 생식 불가능 벌레 수 
    점화식 : dp[i] = dp[i-1] + dp[i-a] - dp[i-b]

유의 ) dp 결과가 음수가 될 수 있을 경우에 대비하여 +1000 해주기

*/
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int death = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[N+1];
        dp[0] = 1;

        for (int i = 1; i <= N; i++) {
            if (i < start)
                dp[i] = dp[i-1]%1000;
            else if(i < end)
                dp[i] = (dp[i-1] + dp[i-start])%1000;
            else
                dp[i] = (dp[i-1]+dp[i-start]-dp[i - end]+1000)%1000;
        }

        if(N - death >= 0)
            System.out.println((dp[N]-dp[N-death]+1000)%1000);
            
        else System.out.println(dp[N]%1000);

    }
}

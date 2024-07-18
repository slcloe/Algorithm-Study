import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
[문제 풀이]
dp
1자리수를 선택할때와 2자리수를 선택할 때를 나눠 dp 식을 계산한다.
예외는 0일 때이다. '0' 카드는 존재하지 않기 때문에 따로 식을 처리해줘야한다.

점화식
1자리 수 추가할 때, 
    dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
2자리 수 추가할 때, ( 0인데 두자리 수 추가할 때 )
    dp[i][1] = dp[i - 1][0]
 */

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] nums = br.readLine().toCharArray();
        int[][] dp = new int[nums.length + 1][2];

        dp[1][0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int num = (nums[i - 1] - '0') * 10 + (nums[i] - '0');
//            System.out.println(num + "nums: " + nums[i - 1] + " " + nums[i]);
            if (nums[i] != '0') {
                if (num <= 34) {
                    dp[i + 1][1] = dp[i][0];
                }
                dp[i + 1][0] = dp[i][0] + dp[i][1];
            }
            else {
                if (num <= 34) {
                    dp[i + 1][1] = dp[i][0];
                }
            }
        }

        System.out.println(dp[nums.length][0] + dp[nums.length][1]);
//        for(int[] d: dp){
//            System.out.println(Arrays.toString(d));
//        }

    }
}


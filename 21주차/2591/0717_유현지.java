/*
 * | 활용 알고리즘 | Dynamic Programming
 *
 * | 접근 방법 |
 *  1. 점화식
 *     1-1. 숫자가 0이 아니면 dp[i+1] += dp[i]
 *     1-2. 뒷 숫자와 함께 한 수(두자리 수)가 34 이하면 dp[i+2] += dp[i]
 *  2. 문자열 길이가 L일 때 dp[L]이 답
 */

package a2407.study.week21;

import java.io.*;
import java.util.Arrays;

public class bj_g5_2591_숫자카드 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int L = line.length();
        int[] arr = new int[L];
        int[] dp = new int[L+1];
        for(int i=0; i<L; i++){ arr[i] = line.charAt(i) - '0'; }
        dp[0] = 1;
        for(int i=0; i<L; i++){
            if(arr[i] != 0){
                dp[i+1] += dp[i];
                if(i+1 != L && 10*arr[i] + arr[i+1] <= 34){
                    dp[i+2] += dp[i];
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[L]);
    }
}

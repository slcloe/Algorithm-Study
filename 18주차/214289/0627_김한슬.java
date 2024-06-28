import java.util.*;

/*
문제 풀이 시 감이 전혀 안잡혀서 
https://school.programmers.co.kr/questions/52432
위 링크를 참고했음.

1. onboard 시점에서 가능한 온도의 범위를 미리 정해두고 해당 범위에서 for 문을 돌림.
2. dp 경우의 수 
  : i. 에어컨을 끄고 온도 낮추기
    ii. 에어컨을 끄고 온도 높이기
    iii. 에어컨을 키고 온도 낮추기
    iv. 에어컨을 키고 온도 높이기
    v. 에어컨을 키고 온도 그대로
    vi. 에어컨을 끄고 온도 그대로 ( 예외. 목표 온도 == temperature )
3. min 값 찾기
*/

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        
        t1 += 10;
        t2 += 10;
        temperature += 10;
        
        int N = onboard.length;
        int[][] dp = new int[N + 1][51];
        int low = Math.min(t1, temperature);
        int high = Math.max(t2, temperature);
        
        for (int i = 0 ;i <= N; i++) {
            Arrays.fill(dp[i], 1_000_000);
        }
        // Arrays.fill(dp[0], 0);
        dp[0][temperature] = 0;
        
        for(int i = 0; i < N; i++) {
            int start = 0;
            int end = 0;
            
            // i 시점에서 가능한 온도의 범위
            if (onboard[i] == 1){ // 승객 탑승
                start = t1;
                end = t2;
            } else { // 승객 미탑승
                start = low;
                end = high;
            }
            
            for(int j = start ; j <= end ; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + b); // 에어컨 가동시켜 온도 유지
                // System.out.println(dp[i + 1][j] + " " + (dp[i][j + 1] + a));
                if (j + 1 <= high) { // 에어컨 가동시켜서 온도 낮추기
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j + 1] + a);
                }
                if (j - 1 >= low) { // 에어컨 가동시켜서 온도 높이기
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j - 1] + a);
                }
                if (j + 1 > temperature && j + 1 <= high) { // 에어컨을 끄고 온도 낮추기
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
                if (j - 1 < temperature && j - 1 >= low) { // 에어컨을 끄고 온도 높이기
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
                if (j == temperature){ // 온도가 실외온도랑 같은 경우 **
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                }
            }
        }
        
        return Arrays.stream(dp[onboard.length]).min().getAsInt();
    }
}

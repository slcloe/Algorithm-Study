/*
 * | 활용 알고리즘 | dynamic programming
 *
 * | 접근 방법 |
 *  1. 2차원 dp 배열을 만든다
 *     dp[i][j] = i분에 온도 j가 되기 위해 필요한 최소 비용
 *  2. dp 배열을 채운다
 *     2-1. 에어컨을 켜서 온도를 유지하는 경우(b)
 *     2-2. 에어컨을 켜서 온도를 조절하는 경우(a)
 *     2-3. 에어컨을 꺼서 온도가 유지되는 경우(0)
 *     2-4. 에어컨을 꺼서 온도가 달라지는 경우(0)
 *  3. 마지막 시점에 배열의 최소값을 찾는다
 */

package a2406.study.week18;

import java.util.*;

public class pr_3_214289_에어컨 {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        if(t1<=temperature && temperature<=t2){ return 0; }

        int MIN_TEMP = -10, MAX_TEMP = 40, MAX = 1_000_001,
                RANGE = MAX_TEMP - MIN_TEMP,
                lenOnboard = onboard.length;

        temperature -= MIN_TEMP;
        t1 -= MIN_TEMP;
        t2 -= MIN_TEMP;

        int[][] dp = new int[lenOnboard][RANGE+1];
        for(int[] d: dp){ Arrays.fill(d, MAX); }
        dp[0][temperature] = 0;

        for(int i=0; i<lenOnboard-1; i++){
            for(int j=0; j<=RANGE; j++){
                if(onboard[i]==1 && (j<t1 || t2<j)){ continue; }

                int nextTemp = j;
                if(j<temperature && j<RANGE){ nextTemp += 1; }
                else if(temperature<j && 0<j){ nextTemp -= 1; }
                dp[i+1][nextTemp] = Math.min(dp[i][j], dp[i+1][nextTemp]);

                if(j<RANGE){ dp[i+1][j+1] = Math.min(dp[i][j]+a, dp[i+1][j+1]); }
                if(0<j){ dp[i+1][j-1] = Math.min(dp[i][j]+a, dp[i+1][j-1]); }

                dp[i+1][j] = Math.min(dp[i][j]+b, dp[i+1][j]);
            }
        }

        int answer = MAX;
        for(int i=0; i<=RANGE; i++){
            if(onboard[lenOnboard-1]==1 && (i<t1 || t2<i)){ continue; }
            answer = Math.min(answer, dp[lenOnboard-1][i]);
        }

        return answer;
    }
}

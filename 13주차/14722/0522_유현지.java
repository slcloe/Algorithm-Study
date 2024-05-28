/*
 * | 활용 알고리즘 | Dynamic Programming
 *
 * | 접근 방법 |
 *  1. 3차원 dp 배열을 만든다
 *     dp[행][열][다음에 마실 우유 번호 | 지금까지 마실 수 있는 최대 우유 개수]
 *  2. 각 좌표에서 위, 왼쪽에 있는 dp[r][c][1] 값을 비교한다
 *  3. 값이 더 큰 좌표에서 city[r][c] 값이 dp[r][c][0]와 같은지 비교해 dp 배열을 갱신한다
 *     3-1. 같다면 우유 번호는 (+1)%3, 최대 우유 개수는 +1
 *     3-1. 다르다면 우유 번호와 최대 우유 개수 그대로
 *  4. dp[N][N][1]이 답이 된다
 */

package a2405.study.week13;

import java.io.*;
import java.util.*;

public class bj_g4_14722_우유_도시 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] city = new int[N+1][N+1];
        int[][][] dp = new int[N+1][N+1][2];
        for(int r=1; r<=N; r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c=1; c<=N; c++){
                city[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                int R, C;
                if(dp[r-1][c][1] < dp[r][c-1][1]){
                    R = r;
                    C = c-1;
                }
                else{
                    R = r-1;
                    C = c;
                }
                int nextMilk = dp[R][C][0];
                int nextResult = dp[R][C][1];
                if(city[r][c] == nextMilk){
                    nextResult++;
                    nextMilk = (nextMilk+1)%3;
                }
                dp[r][c] = new int[]{nextMilk, nextResult};
            }
        }
        System.out.println(dp[N][N][1]);
    }
}
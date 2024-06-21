// 새로운 짚신벌레를 찾았다
// a일 이후 성체가 된다
// 성체가 된 첫날부터 매일 한마리씩 개체 생성
// b일 이후에는 개체 만들기 중지
// -> b-a개의 개체 생성
// d일째 사망 ㅠㅠ
// N일째 될떄 살아있는 짚신벌레의 수 % 1000 =??

import java.io.*;
import java.util.*;

public class BOJ_2560_짚신벌레 {
    static int a,b,d,N;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        a= Integer.parseInt(st.nextToken());
        b= Integer.parseInt(st.nextToken());
        d= Integer.parseInt(st.nextToken());
        N= Integer.parseInt(st.nextToken());

        int[] dp= new int[N+1];
        dp[0]=1;

        for (int day = 1; day <= N; day++) {
            // 개체 만들기 전
            if(day<a){
                dp[day]= dp[day-1] % 1000;
            }else if(day<b){
                // 오늘 태어난 짚신벌레 추가
                dp[day]= dp[day-1] + dp[day-a] % 1000;
            }else{
                // 오늘 태어난 짚신벌레(생식 가능한 짚신벌레) - 생식 불가능한 짚신벌레 만큼 추가
                dp[day]= (dp[day-1] + dp[day-a] - dp[day-b] + 1000) % 1000;
            }
        }
        if(N-d >= 0){
            System.out.println((dp[N]- dp[N-d] + 1000)%1000);
        }else{
            System.out.println(dp[N] %1000);
        }
    }
}

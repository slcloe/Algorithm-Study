import java.util.*;

class Solution {
    int sub_max = 0;
    int vol_max = 0;
    int sale[] = {10,20,30,40};
    public int[] solution(int[][] users, int[] emoticons) {
        solve(users,new int[users.length],emoticons,0);
        int[] answer = {sub_max,vol_max};
        return answer;
    }

    void solve(int[][] users,int[] saled, int[] emoticons, int idx) {
        if(idx == users.length) {
            // 할인율이 다 적용 됐을 때
//    		System.out.println(Arrays.toString(saled));
            int subscribe = 0;
            int purchaseTotal = 0;
            // 1. 모든 유저에 대해 탐색
            for (int i = 0; i < users.length; i++) {
                int user_purchase = 0;
                // 2. 이모티콘과 할인율에 대해 팀섹
                for (int j = 0; j < emoticons.length; j++) {
                    // user[i][0]   : 할인율 한도 user[i][1] : 구매 한도
                    // saled[j] : 이모티콘의 할인율
                    // emoticons[j] : 이모티콘의 정가

                    // 유저가 바로 구매할 할인율을 비교
                    if(saled[j] >= users[i][0]) {
                        // 원하는 할인율일때 바로 구매하고 결제할 금액에 누적
                        user_purchase += emoticons[j] * (100-saled[j]) / 100;
                    }
                    // 누적 금액이 한도 이상일 경우에는 구독수 추가 및 유저에 대한 탐색 중단
                    if(user_purchase >= users[i][1]) {
                        subscribe ++ ;
                        user_purchase = 0;
                        break;
                    }

                }
                purchaseTotal += user_purchase;
            }
            // 모든 유저에 대한 탐색 이후
            if(subscribe > sub_max) {
                sub_max = subscribe;
                vol_max = purchaseTotal;
            }
            if(subscribe == sub_max) {
                if(purchaseTotal > vol_max) {
                    vol_max = purchaseTotal;
                }
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            saled[idx]= sale[i];
            solve(users,saled,emoticons,idx+1);
        }
    }
}
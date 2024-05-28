import java.io.*;
import java.util.*;


/*

문제 해설
1. permutation 으로 각 이모티콘의 할인율에 대한 경우의 수를 모두 탐색한다.
2. 할인율에 대한 이모티콘 값을 계산하여 해당 유저가 이모티콘 플러스 서비스를 가입하는지에 대해 계산한다.
3. 문제에 주어진 목표 2가지를 충족하는 값을 userNum, maxPrice 에 저장하여 값을 도출한다.

*/
class Solution {
    int[] b;
    int userNum, maxPrice;
    void perm(int cnt, int n, int[][] users, int[] emoticons){
        if (cnt == n){
            cal(users, emoticons);
            return ;
        }
        for (int i = 1; i < 5; i++) {
            b[cnt] = i * 10;
            perm(cnt + 1, n, users, emoticons);
        }
    }

     void cal(int[][] users, int[] emoticons){
        int user = 0, price = 0;
        for (int i = 0; i < users.length; i++) {
            int totalP = 0;
            for (int j = 0; j < emoticons.length; j++) {
                if (users[i][0] > b[j]) continue;
                int p = emoticons[j] * (100 - b[j]) / 100;
                totalP += p;
            }
            if (totalP >= users[i][1]) user++;
            else price += (int) totalP;
        }
        if (user > userNum){
            userNum = user;
            maxPrice = price;
        }
        else if (user == userNum){
            maxPrice = Math.max(maxPrice, price);
        }
    }
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        b = new int[emoticons.length];

        perm(0, emoticons.length, users, emoticons);

        answer[0] = userNum;
        answer[1] = maxPrice;
        return answer;
    }
}
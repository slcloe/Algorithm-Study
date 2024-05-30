import java.util.*;
class Solution {
    /*
    접근 방식 : 처음엔 dfs로 풀려고 했는데, 값을 제자리에 돌려놓는 로직을 수행하는 것이 비효율적이라 생각해서, 그냥 순열 사용해서 풀이.
              단순하게 풀었으면 훨씬 더 빨리 풀었을듯, 7개의 이모티콘이 4개의 할인율을 가질 수 있으니 4^7
    
    주안점
    1. 단순하게 풀자. 특별한 로직이 필요없고, 단순한 순열로 가능
    */

    static int price[][], nowEmoticons[], resultCount, resultSum, pick[];
    public int[] solution(int[][] users, int[] emoticons) {
        nowEmoticons = emoticons;
        price = users;
        resultCount = 0;
        resultSum = 0;
        pick = new int[emoticons.length];
        perm(0);
        return new int[] {resultCount, resultSum};
    }

    static void calc() {
        int[] resultPrice = new int[nowPrice.length];
        for(int i=0; i<nowPrice.length; i++) {
            for(int j=0; j<nowEmoticons.length; j++) {
                if(pick[j] >= nowPrice[i]) {
                    resultPrice[i] += (nowEmoticons[j] / 100) * (100 - pick[j]);
                }
            }
        }

        int count = 0;
        int sum = 0;
        for(int i=0; i<resultPrice.length; i++) {
            if(resultPrice[i] >= price[i]) {
                count++;
            } else {
                sum += resultPrice[i];
            }
        }
        if(resultCount < count) {
            resultCount = count;
            resultSum = sum;
        } else if(resultCount == count && resultSum < sum) {
            resultSum = sum;
        }
    }

    static void perm(int idx) {
        if(idx == nowEmoticons.length) {
            calc();
            return;
        }

        for(int i=10; i<=40; i+=10) {
            pick[idx] = i;
            perm(idx+1);
        }
    }

}
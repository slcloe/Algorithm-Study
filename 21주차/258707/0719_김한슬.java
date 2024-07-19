import java.util.*;

/*
[문제 풀이]

카드를 뽑는 경우의 수
1. coin을 0개 지불 하고 뽑기
2. coin을 1개 지불 하고 뽑기
3. coin을 2개 지불 하고 뽑기
위 3단계를 거쳐도 뽑지 못한 다면 게임을 종료한다.

어차피 쓸만한 카드를 미리 뽑아 손에 가지고 있어도 결국 코인을 지불하는 것은 똑같기 때문에
선택은 가장 나중으로 미룬다.

*/

class Solution {
    Set<Integer> card, pick;
    int coins, sum;
    
    boolean pick_0() {
        for(int n : card) {
            if (card.contains(sum - n)) {
                card.remove(n);
                card.remove(sum - n);
                // System.out.println(n);
                return true;
            }
        }
        return false;
    }
    
    boolean pick_1() {
        if (coins == 0)
            return false;
        
        for(int n : card) {
            if (pick.contains(sum - n)) {
                pick.remove(sum - n);
                card.remove(n);
                coins--;
                // System.out.println(n);
                return true;
            }
        }
        return false;
    }
    
    boolean pick_2() {
        if (coins <= 1)
            return false;
        
        for (int n : pick) {
            if (pick.contains(sum - n)) {
                pick.remove(sum - n);
                pick.remove(n);
                coins -= 2;
                // System.out.println(n);
                return true;
            }
        }
        
        return false;
    }
    
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int N = cards.length;
        coins = coin;
        
        card = new HashSet<>();
        pick = new HashSet<>();
        
        int idx = N / 3;
        for (int i = 0; i < idx; i++) {
            card.add(cards[i]);
        }
        
        sum = N + 1;
        int i;
        for (i = idx; i < N; i += 2) {
            answer++;
            pick.add(cards[i]);
            pick.add(cards[i + 1]);
        
            if (pick_0()) continue;
            if (pick_1()) continue;
            if (pick_2()) continue;
            
            break;
        }
        if (i == N) answer++;
        
        return answer;
    }
}

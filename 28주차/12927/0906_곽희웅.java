import java.util.*;
class Solution {
    /*
    
    접근 방식 : 처음엔 모든 경우의 수를 다 탐색하려 했는데, 무조건 시간초과가 발생할 것 같음.
              숫자가 클수록 제곱도 커지기 마련이니 PriorityQueue를 사용하면 되겠다고 고려함.
    
    */
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int cur : works) pq.offer(cur);
        for(int i=0; i<n; i++) {
            int N = pq.poll();
            if(N == 0) break;
            pq.offer(N-1);
        }
        
        long answer = 0;
        while(!pq.isEmpty()) {
            answer += (int) Math.pow(pq.poll(), 2);
        }
        return answer;
    }
}
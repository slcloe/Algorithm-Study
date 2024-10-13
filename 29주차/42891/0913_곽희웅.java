import java.util.*;
class Solution {

    /*

        접근 방식 : 정확도 테스트는 쉽게 통과할 수 있지만, 효율성 테스트는 통과하지 못함. 시간을 단축할 수 있는 풀이가 필요할 듯 함

    */
    public int solution(int[] food_times, long k) {
        List<Integer> list = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] == o2[1] ? o2[0] - o1[0] : o1[1] - o2[1]);
        for(int i=0; i<food_times.length; i++) {
            pq.offer(new int[] {i+1, food_times[i]});
        }
        
        int answer = 0;
        int start = 0;
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            long nowMul = (long) Math.abs(now[0] - start) * now[1];
            if(k > nowMul) {
                k -= nowMul;
                start = now[0];
            }
            else if(k == nowMul) {
                k = 0L;
                if(!pq.isEmpty()) answer = pq.peek()[0];
                else answer = -1;
                break;
            }
            else {
                k = 0L;
                answer = now[0] == food_times.length + 1 ? 0 : now[0];
                break;
            }
        }
        
        if(k > 0) answer = -1;
        return answer;
    }
}
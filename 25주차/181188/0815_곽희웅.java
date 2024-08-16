import java.util.*;
class Solution {
    /*

    접근 방식 : 시작 or 끝 지점을 기준으로 정렬 후 단순 계산 풀이
    주안점
    1. 만약 시작 지점으로 오름차순 정렬한다면 2, 3번째 선분은 각각 떨어져있지만 1번째 선분이 둘 모두를 덮는 크기일 때의 반례를 해결해야 함 => 요격 지점을 계속 바꿔줘야 함

    */
    public int solution(int[][] targets) {
        int answer = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for(int i=0; i<targets.length; i++) {
            pq.offer(targets[i]);
        }

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            while(!pq.isEmpty() && pq.peek()[0] < cur[1]) pq.poll();
            answer++;
        }

        return answer;
    }
}
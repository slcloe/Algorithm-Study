/*
 * | 활용 자료구조 | Priority Queue
 *
 * | 접근 방법 |
 *  1. 각 미사일 x 좌표 범위를 우선순위 큐에 넣는다
 *     1-1. 끝나는 지점 오름차순
 *     1-2. 시작 지점 오름차순
 *  2. 우선순위 큐의 원소들을 하나씩 빼면서 아래 조건을 체크한다
*   3. 현재 미사일의 종료 지점이 우선순위 큐 첫 미사일의 시작 지점보다 크면 우선순위 큐에서 계속 없앤다
 */

package a2408.study.week25;

import java.util.*;

public class pr_2_181188_요격_시스템 {
    public int solution(int[][] targets) {
        int answer = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[1] == o2[1]){ return o1[0] - o2[0]; }
            return o1[1] - o2[1];
        });

        for(int[] target: targets){ pq.offer(target); }
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int end = now[1];
            while(!pq.isEmpty() && pq.peek()[0] < end){ pq.poll(); }
            answer++;
        }

        return answer;
    }
}

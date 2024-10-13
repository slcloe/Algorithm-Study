/*
 * | 활용 자료구조 | Priority Queue
 *
 * | 접근 방법 |
 *  1. 제곱의 크기가 작게 하려면 수 자체가 작아야 한다
 *  2. 내림차순으로 우선순위 큐를 만든다
 *  3. n번 만큼 최대값을 poll 해서 1 감소 시킨 다음 다시 큐에 넣는다
 *  4. 큐에 남은 수를 제곱해서 더한다
 */

package a2409.study.week28;

import java.util.*;

public class pr_3_12927_야근_지수 {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int work: works){
            pq.offer(work);
        }
        for(int i=0; i<n; i++){
            int num = pq.poll();
            if(num == 0){
                break;
            }
            pq.offer(num - 1);
        }
        for(int work: pq){
            answer += work * work;
        }

        return answer;
    }
}

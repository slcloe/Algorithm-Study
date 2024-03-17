import java.util.*;
import java.io.*;

/*
풀이 방법
1. 모든 jobs를 ArrayList 에 넣는다.
2. 현재 진행 ms 는 curState로 표기한다.
3. priorityQueue 를 사용하여 curState 시점에서 가장 짧은 작업을 우선 수행한다.
4. 만약 curState 시점에서 할 수 있는 요청이 없다면 list 중에서 제일 먼저 요청되는 작업을 넣고 3번으로 돌아가 작업을 반복한다.

5. 마지막으로 jobs.length 를 나눠주면 정답 완성
*/
class Solution {
    
    public static int solution(int[][] jobs) {
        int answer = 0;

        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++) {
            list.add(new int[] {jobs[i][0], jobs[i][1]});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]){
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        
        int idx = 0;
        int curState = 0;

        while (!list.isEmpty() || !pq.isEmpty()){

            // 현재 curState 와 비교해서 실행시킬 수 있는 프로세스 넣기
            Iterator iter = list.iterator();
            while(iter.hasNext()){
                int cur[] = (int[]) iter.next();
                if (curState >= cur[0]) {
                    pq.offer(cur);
                    iter.remove();
                }
            }

            // 현재 진행할 수 있는 프로세스가 있다면 -> pq 에서 하나 빼서 계산
            if (!pq.isEmpty()){
                int cur[] = pq.poll();
                answer += curState + cur[1] - cur[0];
                curState = curState + cur[1];
                idx++;
            }
            // 현재 진행할 수 있는 프로세스가 없다면 -> 그냥 curState ++ 하고 돌리기
            else{
                Collections.sort(list, (o1, o2) -> {
                    if (o1[0] == o2[0]) return o1[1] - o2[1];
                    return o1[0] - o2[0];
                });
                pq.offer(list.get(0));
                curState = list.get(0)[0];
                list.remove(0);
            }
        }

        return answer /= jobs.length;
    }
        
}

import java.util.*;
class Solution {
    /*
    접근 방식 : 기본적으로 먼저 들어오는 요청부터 처리하며, 처리할 수 있는 요청일 때는 시간이 적게 걸리는 것을 처리함
               요청 배열을 시간순으로 정렬 후, process의 시간 내에 처리할 수 있는 작업들을 모두 pq에 넣음
    자료 구조 : 작업 시간이 작은 순으로 오름차순 정렬해야하기 때문에 PriorityQueue 사용
    
    주안점
    1. 같이 요청이 들어오지만 작업 시간은 다른 Task 존재
    2. idx의 경우 외부 메서드에서 변경하는 것이 main에 적용되지 않기 때문에 static 변수로 선언
    3. 소수점 밑은 버린다는 것을 유의
    */
    static int idx;
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int process = jobs[0][0];
        int answer = 0;
        idx = 0;
        findTask(process, jobs, pq);
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            process += cur[1];
            answer += process - cur[0];
            findTask(process, jobs, pq);
            if(pq.isEmpty() && idx < jobs.length) {
                process = jobs[idx][0];
                pq.offer(jobs[idx++]);
            }
        }
        return answer / jobs.length;
    }
    
    static void findTask(int process, int[][] jobs, PriorityQueue<int[]> pq) {
        while(idx < jobs.length && jobs[idx][0] <= process) pq.offer(jobs[idx++]);
    }
}
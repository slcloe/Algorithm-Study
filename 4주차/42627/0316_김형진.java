package week4;
import java.util.*;

// 수행중인 작업이 없을때
// 1. 할 수 있는 작업이 한개 -> 그거 ㄱ
// 2. 할 수 있는 작업이 여러개 -> 시간 긴거부터 (평균을 낯추기 위해) -> 해당 시간 할수있는 작업을 pq에 저장

class Solution {
    public int solution(int[][] jobs) {
        int ans = 0;
        int acc = 0;
        int cnt = 0;
        int len = jobs.length;
        int time = 0;
        int idx = 0;
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        while(cnt<len){
            // 할 수 있는 작업 다 담기
            while(idx<len && jobs[idx][0] <= time){
                pq.add(jobs[idx++]);
            }
            // 작업 하나 수행시 cnt ++, 현재시간 + 작업소요시간 누적
            // 할 일이 없네
            if(pq.isEmpty()){
                time = jobs[idx][0];
            } else{ //있네
                int[] canDo = pq.poll();
                acc += canDo[1] + time - canDo[0];
                time += canDo[1];
                cnt++;
            }
        }
        ans = acc/len;
        return ans;
    }
}
public class 디스크컨트롤러 {
    public static void main(String[] args)throws Exception {
        int[][] jobs = new int[][]{
                {0,3},
                {1,9},
                {2,6}
        };
        Solution s = new Solution();
        System.out.println(s.solution(jobs));
    }
}

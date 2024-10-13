import java.util.*;
class Solution {
    /*
    
    접근 방식 : 그냥 있는 그대로 완전탐색으로 구현한다면 5^15의 말도 안되는 시간이 걸림.
              그리디 방식으로 인원을 한 명씩 늘려가며 가장 큰 폭의 시간이 줄어드는 유형에 1명씩 추가해주며 풀이
    
    */
    public int solution(int k, int n, int[][] reqs) {
        int count = k;
        int[] nums = new int[k+1];
        for(int i=1; i<=k; i++) nums[i] = 1;

        while(count++ < n) {
            int[] origin = calc(nums, reqs);

            int maxDiff = 0;
            int maxDiffIdx = 0;

            int[] nextNums = new int[k+1];
            for(int i=1; i<=k; i++) {
                nextNums[i] = nums[i]+1;
            }

            int[] now = calc(nextNums, reqs);
            for(int i=1; i<=k; i++) {
                if(maxDiff < origin[i] - now[i]) {
                    maxDiff = origin[i] - now[i];
                    maxDiffIdx = i;
                }
            }
            nums[maxDiffIdx]++;
        }

        int[] last = calc(nums, reqs);
        int sum = 0;
        for(int i=1; i<=k; i++) sum += last[i];

        return sum;
    }

    static int[] calc(int[] nums, int[][] reqs) {
        PriorityQueue<Integer>[] pqs = new PriorityQueue[nums.length];
        int[] result = new int[nums.length];
        for(int i=1; i<nums.length; i++) {
            pqs[i] = new PriorityQueue<>();
            for(int j=0; j<nums[i]; j++) {
                pqs[i].offer(0);
            }
        }

        for(int[] req : reqs) {
            int minStartTime = pqs[req[2]].poll();

            if(minStartTime <= req[0]) {
                pqs[req[2]].offer(req[0] + req[1]);
            } else {
                result[req[2]] += minStartTime - req[0];
                pqs[req[2]].offer(minStartTime + req[1]);
            }
        }
        return result;
    }
}
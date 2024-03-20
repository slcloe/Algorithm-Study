/*
 * | 접근 방법 |
 *  1. 작업 배열 jobs를 정렬한다
 *     정렬 기준: 1) 소요 시간 오름차순, 2) 요청 시점 오름차순
 *  2. 하나의 작업이 끝났을 때의 시간을 뜻하는 endAt 변수를 둔다
 *     초기 값은 0이며, 하나의 작업에 대한 endAt은 다음 작업에 대한 startAt과 같은 의미이다
 *  3. 작업 배열 jobs를 탐색하며
 *     3-1. 이미 완료된 작업이라면 무시한다
 *     3-2. 해당 작업을 즉시 수행할 수 있는 상태라면 탐색을 끝내고 해당 작업을 선택한다
 *          해당 작업의 요청 시점이 이전 작업의 endAt 이하인 경우
 *     3-3. 해당 작업을 즉시 수행할 수 없는 상태라면 요청 시점이 가장 빠른 것으로 갱신해간다
 *          해당 작업의 요청 시점이 이전 작업의 endAt을 초과하는 경우
 *  4. 선택된 작업에 대해 endAt을 갱신한다
 *     4-1. 해당 작업을 즉시 수행할 수 있는 상태라면(3-2) 기존 endAt에 해당 작업의 소요 시간을 더한다
 *     4-2. 해당 작업을 즉시 수행할 수 없는 상태라면(3-3) endAt을 해당 작업의 요청 시점에 소요 시간을 더한 값으로 갱신한다
 *  5. 해당 작업에 대한 대기 시간을 answer에 더한다
 *     대기 시간은 endAt(해당 작업의 완료 시점)에서 jobs[idx][0](해당 작업의 요청 시점)을 뺀 값이다
 */

package a2403.week3;

import java.util.*;

public class pr_3_42627_디스크_컨트롤러 {
    public int solution(int[][] jobs) {
        int answer = 0;
        int J = jobs.length;

        Arrays.sort(jobs, (j1, j2) -> j1[1] == j2[1]? j1[0] - j2[0]: j1[1] - j2[1]);

        int endAt = 0;
        for(int i=0; i<J; i++){
            int idx = 0;
            int min0 = 1001;
            boolean findIdx = false;

            for(int j=0; j<J; j++){
                if(jobs[j][0] < 0){ continue; } // 이미 수행 완료됐을 경우
                if(jobs[j][0] <= endAt){ // 바로 시작할 수 있는 경우
                    idx = j;
                    findIdx = true;
                    break;
                }

                // 요청까지 기다려야 하는 경우
                if(jobs[j][0] >= min0){ continue; }
                min0 = jobs[j][0];
                idx = j;
            }

            if(findIdx){ endAt += jobs[idx][1]; }
            else{ endAt = jobs[idx][0] + jobs[idx][1]; }

            answer += endAt - jobs[idx][0];
            jobs[idx][0] = -1;
        }

        return answer/J;
    }
}

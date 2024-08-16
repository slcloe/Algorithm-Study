import java.util.*;

/*
    그래프들의 특성을 이용해서 다음과 같은 조건으로 각 그래프의 존재 유무를 확인할 수 있다.
    
    1. 인바운드가 0이면서 아웃바운드가 2 이상인 경우, 해당 정점은 추가된 정점이다.
        막대그래프의 첫 번째 정점과 헷갈릴 수 있지만 문제에서 친절하게 그래프의 수는 2개 이상이라고 정의되어 있다.
    2. 인바운드가 1 이상이면서 아웃바운드가 2인 경우, 해당 정점은 8자 그래프의 중간 정점이다.
    3. 인바운드가 1 이상이면서 아웃바운드가 0일 경우, 해당 정점은 막대 그래프의 마지막 정점이다.
    4. 이외의 모든 정점은 인바운드와 아웃바운드가 1로 모두 동일하다. 
    (추가 정점에 의해 아웃바운드가 추가된 경우를 제외하면)
    도넛 그래프의 수는 (전체 그래프의 수) - (8자 그래프의 수) - (막대 그래프의 수)로 구할 수 있다.
    (전체 그래프의 수) == (추가 정점의 아웃 바운드 수)
*/

class Solution {
    
    static final int MAX_VERTEX = 1000000;
    static int[] outbound = new int[MAX_VERTEX];
    static int[] inbound = new int[MAX_VERTEX];
    
    public int[] solution(int[][] edges) {
        int[] answer = {0, 0, 0, 0};
   
        for (int[] edge : edges) {
            outbound[edge[0]-1]++;
            inbound[edge[1]-1]++;
        }
        
        for (int i = 0; i < MAX_VERTEX; i++) {
            if (inbound[i] == 0 && outbound[i] >= 2) {
                answer[0] = i+1;
            }
            
            if (inbound[i] >= 1 && outbound[i] >= 2) {
                answer[3]++;
            }
            
            if (inbound[i] >= 1 && outbound[i] == 0) {
                answer[2]++;
            }
        }
        
        answer[1] = outbound[answer[0] - 1] - answer[2] - answer[3];
        
        return answer;
    }
}

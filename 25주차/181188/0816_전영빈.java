import java.util.*;

/*
    특정 바운더리 안에서 얼마나 많은 간선을 포함할 수 있는가를 구하는 문제.
    모든 간선들에 대해 끝점에 대해 내림차순, 시작점에 대해 오름차순으로 정렬하자.
    
    현재 포함된 간선들을 포함할 수 있는 집합 간섭의 시작점에 요격점을 박는다고 생각하면
    다음 간선이 포함되기 위해서는 해당 간선의 끝점이 요격점보다 뒤에 있어야 한다.
    다음 요격점의 위치는 현재 요격점의 위치와 간선의 시작점 중 더 높은 값.
*/

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (a, b) -> {
            if (a[1] != b[1]) {
                return -Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        
        int edge = Integer.MAX_VALUE;
        
        for (int[] bar : targets){       
            if (bar[1] > edge) {
                edge = Math.max(edge, bar[0]);
            } else {
                answer++;
                edge = bar[0];
            }
        }
        
        return answer;
    }
}

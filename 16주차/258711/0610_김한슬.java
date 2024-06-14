import java.util.*;

/*
문제 풀이 
생성점 : out 만 있음 
    out의 개수 == (a)+(b)+(c) 의 개수
    a. 막대그래프 : 끝의 개수를 구하면 됨
    b. 도넛그래프 : a 와 c 가 아닌 것
    c. 8자 그래프 : 2 in && 2 out 을 구하면 됨.
    
엣지케이스 :
    정점의 수가 1, 2, 3, 4 로 들어올 수도 있지만,
    1, 10 ,29, 40으로 불규칙하게 들어올 수도 있다.
*/
class Solution {


    public int[] solution(int[][] edges) {
        Set<Integer> set = new HashSet<>();
        int[][] degrees = new int[2][1_000_001];
        for (int[] edge : edges){
            set.add(edge[0]);
            set.add(edge[1]);
            degrees[0][edge[0]]++;
            degrees[1][edge[1]]++;
        }
        
        int[] answer = new int[4];
        
        Iterator<Integer> iterSet = set.iterator();
        while(iterSet.hasNext()){
            int i = iterSet.next(); 
            if (degrees[0][i] > 1 && degrees[1][i] == 0) // 생성점
                answer[0] = i;
            else if (degrees[0][i] == 0) // 막대그래프
                answer[2]++;
            else if (degrees[0][i] == 2 && degrees[1][i] >= 2) // 8자 그래프
                answer[3]++;
        }
        
        answer[1] = degrees[0][answer[0]] - (answer[2] + answer[3]);
        return answer;
    }
}


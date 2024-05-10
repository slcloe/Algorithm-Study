import java.util.*;

/*
문제 풀이

1. 보석 종류 구하기
2. start - end 구간 구하기
    a. map 을 사용하여 i 까지의 보석 개수를 저장한다.
    b. 이후 map에 저장된 보석 개수가 2이상일 경우 보석을 하나씩 빼가며 start ++ 를 해준다.
        ( 보석 개수가 최소 1개를 보장하는 index 가 start indx 에 저장된다.)
    c. map.size 가 1번에서 구했던 보석 종류와 같다 && 구간 길이가 이전에 저장된 구간보다 짧다면
       =>  start, end, length 갱신
*/

class Solution {
    int start = 0;
    Map<String, Integer> map = new HashMap<>();
    
    public int[] solution(String[] gems) {
        int answer[] = new int[2];
        int kind = new HashSet<>(Arrays.asList(gems)).size();
        int length = Integer.MAX_VALUE;
        Map<String, Integer> map = new HashMap<>();
        
        for (int end = 0; end < gems.length; end++) {
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
 
            while (map.get(gems[start]) > 1) {
                map.put(gems[start], map.get(gems[start]) - 1);
                start++;
            }
 
            if (map.size() == kind && length > (end - start)) {
                length = end - start;
                answer[0] = start + 1;
                answer[1] = end + 1;
            }
        }
        
        return answer;
    }
}

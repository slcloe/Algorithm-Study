/*
 * | 활용 알고리즘 | Two Pointers
 *
 * | 활용 자료구조 | HashSet, HashMap
 *
 * | 접근 방법 |
 *  1. 두 포인터 모두 첫 번째 인덱스에 놓는다
 *  2. 현재 두 포인터 범위 내에 해당 보석이 있다면 +1, 없다면 1을 맵에 넣는다(키: 보석)
 *  3. idx번째 보석이 중복된다면 보석의 개수를 -1하고 시작 인덱스를 +1한다
 */

package a2405.study.week11;

import java.util.*;

public class pr_3_67258_보석_쇼핑 {
    class Solution {
        public int[] solution(String[] gems) {
            int[] answer = new int[2];
            int G = gems.length, idx = 0, len = 100_001;
            HashSet<String> set = new HashSet();
            for(String gem: gems){ set.add(gem); }
            int S = set.size();

            HashMap<String, Integer> map = new HashMap();
            for(int g=0; g<G; g++){
                String key = gems[g];
                map.put(key, map.getOrDefault(key, 0) + 1);

                while(1 < map.get(gems[idx])){
                    map.put(gems[idx], map.get(gems[idx]) - 1);
                    idx++;
                }
                int diff = g-idx;
                if(map.size() == S && diff < len){
                    len = diff;
                    answer[0] = idx + 1;
                    answer[1] = g + 1;
                }
            }
            return answer;
        }
    }
}

import java.util.*;
class Solution {
    /*
    접근 방식 : 처음에는 Map과 Set을 사용해서 풀었는데, 반례가 모두 맞아도 제출 테스트케이스가 틀렸음
              투 포인터, 슬라이딩 윈도우 방식을 해도 같은 상황이 반복돼서 accept 코드를 보고 풀었음

    주안점
    1. 거리가 같아도 가장 빨리 만족된 케이스가 답이라는 것을 유의
    2. 화내면 어차피 안됨..
    */
    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>();

        int nowStart = 0;
        int nowEnd = 0;

        int firstIdx = 0;
        int lastIdx = 0;
        for(int i=0; i<gems.length; i++) {
            nowEnd = i;
            if(!map.containsKey(gems[i])) {
                map.put(gems[i], i);
                firstIdx = nowStart;
                lastIdx = nowEnd;
            } else {
                map.put(gems[i], i);
                if(gems[i].equals(gems[nowStart])) {
                    nowStart = gems.length;
                    for(String value : map.keySet()) {
                        nowStart = Math.min(nowStart, map.get(value));
                    }
                    nowStart = nowStart == gems.length ? 0 : nowStart;
                    if(nowEnd - nowStart < lastIdx - firstIdx) {
                        firstIdx = nowStart;
                        lastIdx = nowEnd;
                    }
                }
            }
        }

        return new int[] {firstIdx+1, lastIdx+1};
    }
}
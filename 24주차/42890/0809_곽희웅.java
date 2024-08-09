import java.util.*;
class Solution {
    
    /*
    접근 방식 : 후보키에 선정되기 위해서 조합을 활용하여 중복되는 경우의 수 없는 컬럼들을 고른 후 검증
    
    주안점
    1. 유일성을 만족하고 최소성을 만족해야 하기 때문에 검증 로직을 총 2번 돌려야 한다.
    2. 최소성을 만족할 때 pick 기준으로 검사하면 예외가 발생하기 때문에 nonDuplSet 안에 있는 것들 기준으로 해야한다.
    */
    
    static Set<String> nonDuplSet;
    static int[] numSet, pick;
    static String[][] rel;
    public int solution(String[][] relation) {
        nonDuplSet = new HashSet<>();
        
        // array copy
        rel = new String[relation.length][relation[0].length];
        for(int i=0; i<relation.length; i++) {
            for(int j=0; j<relation[0].length; j++) {
                rel[i][j] = relation[i][j];
            }
        }
        
        // make Set
        numSet = new int[relation[0].length];
        for(int i=0; i<relation[0].length; i++) numSet[i] = i;
        
        for(int i=1; i<relation[0].length; i++) {
            pick = new int[i];
            comb(0, 0, i);
        }
        return nonDuplSet.size() == 0 ? 1 : nonDuplSet.size();
    }
    
    static void minimalEstimate() {
        boolean flag = false;
        end: for(int i=0; i<pick.length; i++) {
            String curS = "";
            for(int j=0; j<=i; j++) curS += pick[j] + "";
            
            for(String result : nonDuplSet) {
                int count = 0;
                for(int j=0; j<result.length(); j++) {
                    if(curS.contains(String.valueOf(result.charAt(j)))) count++;
                }
                if(count == result.length()) {
                    flag = true;
                    break end;
                }
            }
        }
        
        if(!flag) {
            String resultS = "";
            for(int i=0; i<pick.length; i++) {
                resultS += pick[i] + "";
            }
            nonDuplSet.add(resultS);
        }
    }
    
    static void estimate() {
        Set<String> eSet = new HashSet<>();
        
        for(int i=0; i<rel.length; i++) {
            String curS = "";
            for(int cur : pick) curS += rel[i][cur] + "";
            eSet.add(curS);
        }
        if(eSet.size() == rel.length) {
            minimalEstimate();
        }
    }
    
    static void comb(int start, int cur, int limit) {
        if(cur == limit) {
            estimate();
            return;
        }
        
        for(int i=start; i<numSet.length; i++) {
            pick[cur] = numSet[i];
            comb(i+1, cur+1, limit);
        }
    }
}
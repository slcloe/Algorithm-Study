import java.util.*;

/*
[문제해설]

1. combination 을 사용하여 검사할 컬럼의 조합을 뽑아낸다.
2. 후보키 의 조건 2개에 대해 검사한다.
    * 유일성 : isminimun()
        combination으로 뽑아서 위 조건은 따로 검사 해야할 필요성을 못느꼈지만, 필수적이다.
        
        *** 예외 케이스 ***
        [0, 1]을 뽑고 이후에 [0, 1, 3]을 뽑게 되는데, 이때 [0, 3]이 후보키가 될 수 있다면
        [0, 1, 3]에서도 후보키를 만족한다고 뜰 것이다. 
        따라서, 유일성 조건을 따로 명시해줘야한다.
        
        유일성 조건에서는 하나의 컬럼만 제외하고도 최소성이 보장되는지 확인하면 되기 때문에,
        각 인덱스 별로 하나씩 빼가면서 최소성 검사를 실행한다.
        
    * 최소성 : isKey()
        set 을 활용하여 append(tuple + " ")을 한 string을 넣은 후, set의 개수를 확인한다.
        set의 개수가 튜플의 총 수와 일치하면 해당 케이스는 최소성을 만족한다.

*/


class Solution {
    String[][] r;
    int N, M;
    int answer = 0;
    int b[];
    void comb(int n, int start) {
        // System.out.println(n + " " + Arrays.toString(b));
        // 확인
        if (isminimun(n)) {
            answer++;
            // System.out.println("** " + n + " " + Arrays.toString(b));
            return;
        }
        
        if (start == M)
            return;
        for(int i = start; i < M; i++) {
            b[n] = i;
            comb(n + 1, i + 1);
        }
        
    }
    
    boolean isminimun(int n) {
        if (!isKey(n, -1)) return false;
        
        for(int i = 0; i < n; i++) {
            if (isKey(n, i)) return false;
        }
        return true;
        
    }
    
    boolean isKey(int n, int j) {
        HashSet<String> set = new HashSet<>();
        
        for(String [] tuple : r) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < n; i++) {
                if (j == i) continue;
                sb.append(tuple[b[i]]).append(' ');
            }
            set.add(sb.toString());
            
        }
        
        if (set.size() == N)
            return true;
        else return false;
    }
    
    
    public int solution(String[][] relation) {
        N = relation.length;
        M = relation[0].length;
        b = new int[M];
        r = relation;
        
        comb(0, 0);
        return answer;
    }
}

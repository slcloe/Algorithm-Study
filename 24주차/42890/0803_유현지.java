/*
 * | 활용 알고리즘 | Combination
 *
 * | 활용 자료구조 | ArrayList, HashSet
 *
 * | 접근 방법 |
 *  1. 컬럼 개수를 C라고 할 때, C에서 1개~C개로 조합을 만든다
 *  2. 조합이 만들어지면 최소성 검사를 먼저 진행한다
 *     2-1. 최소성을 만족하지 못한다면 진행할 필요가 없다
 *  3. 최소성 검사를 통과했다면 Set을 사용해 유일성 검사를 진행한다
 */

package a2408.study.week24;

import java.util.*;

public class pr_2_42890_후보키 {
    static int R, C, PICK, answer = 0;
    static boolean[] visit;
    static ArrayList<String> list = new ArrayList();

    public int solution(String[][] relation) {
        R = relation.length;
        C = relation[0].length;
        visit = new boolean[C];

        for(int c=1; c<=C; c++){
            PICK = c;
            makeComb(relation, 0, C-1, 0);
        }

        return answer;
    }

    static void makeComb(String[][] relation, int cnt, int start, int cols){
        if(cnt == PICK){
            for(String l: list){
                String strCols = cols + "";
                int L = l.length();
                int checkCnt = 0;
                for(int i=0; i<L; i++){
                    if(strCols.contains(l.charAt(i)+"")){ checkCnt++; }
                }
                if(checkCnt == L){ return; }
            }

            HashSet<String> set = new HashSet();
            for(int r=0; r<R; r++){
                String keyTmp = "";
                int colsTmp = cols;
                for(int c=0; c<cnt; c++){
                    int col = colsTmp % 10;
                    keyTmp += relation[r][col];
                    colsTmp /= 10;
                }
                set.add(keyTmp);
            }

            if(set.size() == R){
                list.add(cols+"");
                answer++;
            }
            return;
        }
        for(int c=start; c>=0; c--){
            if(visit[c]){ continue; }
            visit[c] = true;
            makeComb(relation, cnt+1, c-1, 10*cols+c);
            visit[c] = false;
        }
    }
}

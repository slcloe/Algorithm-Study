/*
 * | 활용 알고리즘 | DFS
 *
 * | 활용 자료구조 | HashSet
 *
 * | 접근 방법 |
 *  1. 각각의 불량 사용자 아이디에 대해 일치하는 사용자들을 리스트 배열에 저장한다
 *  2. 각각의 배열에 담긴 사용자들에 대해 DFS를 수행한다
 *  3. 모든 불량 사용자 아이디에 부합하는 조합을 찾았다면 중복되지 않도록 HashSet에 넣는다
 *  4. DFS가 끝난 다음 HashSet의 크기가 답이 된다
 */

package a2404.study.week10;

import java.util.*;

public class pr_3_64064_불량_사용자 {
    static int lenUser, lenBan, answer=0;
    static ArrayList<Integer>[] candidates;
    static boolean[] v;
    static HashSet<String> set = new HashSet();

    public int solution(String[] user_id, String[] banned_id) {
        lenUser = user_id.length;
        lenBan = banned_id.length;

        v = new boolean[lenUser];
        candidates = new ArrayList[lenBan];

        for(int l=0; l<lenBan; l++){
            candidates[l] = new ArrayList();
            String banId = banned_id[l];
            int lenBanId = banId.length();
            boolean[] stars = new boolean[lenBanId];
            for(int i=0; i<lenBanId; i++){
                if(banId.charAt(i) == '*'){ stars[i] = true; }
            }

            for(int i=0; i<lenUser; i++){
                String userId = user_id[i];
                if(userId.length() != lenBanId){ continue; }
                boolean isSame = true;
                for(int j=0; j<lenBanId; j++){
                    if(stars[j]){ continue; }
                    if(banId.charAt(j) != userId.charAt(j)){
                        isSame = false;
                        break;
                    }
                }
                if(isSame){ candidates[l].add(i); }
            }
        }

        DFS(0);
        answer = set.size();
        return answer;
    }

    private void DFS(int idx){
        if(idx == lenBan){
            set.add(Arrays.toString(v));
            return;
        }
        for(int candidate: candidates[idx]){
            if(v[candidate]){ continue; }
            v[candidate] = true;
            DFS(idx+1);
            v[candidate] = false;
        }
    }
}

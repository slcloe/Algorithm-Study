import java.util.*;

/*
문제 해설

1. 문자열을 대조하여 재제 아이디가 될 수 있는지 검사한다.
2. 이때 제재 아이디가 될 수 있다면 banned_id[i] 와 index 가 같은 ArrayList g[i] 에
    사용자의 id 를 넣는다.
    ArrayList[] 에는 각 banned_id를 적용했을 때 제재당할 수 있는 아이디의 index 값이 저장된다.
3. banned_id 를 조회하며 중복없이 제재 아이디의 경우의 수를 뽑는다.
    이때, 결과 자체는 중복이 될 수 있으므로 set 을 사용하여 중복을 제거함.
    Set<Integer> 를 사용한 이유 : user_id 배열의 크기는 8 이하이므로 비트 마스킹을 사용하여
    경우의 수를 체크 했다.

*/



class Solution {
    
    ArrayList<Integer> g[];
    Set<Integer> set;
    boolean[] v;
    
    void comb(int cnt, int size) {
        if (cnt == size){
             
            int num = 0;
            for(boolean i : v) {
                if (i){    
                    num++;
                }
                num = num << 1;
            }
            set.add(num);
            
            return ;
        }
        
        for (int i = 0; i < g[cnt].size(); i++){
            int index = g[cnt].get(i);

            if (v[index]) continue;
            v[index] = true;
            comb(cnt + 1, size);
            v[index] = false;
        }
        
        
    }
    
    boolean isBanned(String user, String banned) {
        if (user.length() != banned.length()) return false;
        for(int i = 0; i < user.length(); i++){
            if (banned.charAt(i) == '*') continue;
            if (banned.charAt(i) != user.charAt(i)) return false;
        }
        return true;
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        g = new ArrayList[banned_id.length];
        set = new HashSet<>();
        v = new boolean[user_id.length];
        
        for(int i = 0;i < banned_id.length; i++){
            g[i] = new ArrayList<>();
            for (int j = 0;j <user_id.length;j++) {
                if (isBanned(user_id[j], banned_id[i]))
                    g[i].add(j);
            }
        }
        
        comb(0, banned_id.length);
        
        return set.size();
    }
}

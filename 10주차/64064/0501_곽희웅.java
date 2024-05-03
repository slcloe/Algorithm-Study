import java.util.*;
class Solution {
    /*
    접근 방식 : 단순하게 banned_id에 부합하는 user_id들을 저장해놓고 완전탐색하면 된다고 생각하여 bfs로 구현
    주안점
    1. 순서와 관계없이 내용이 같으면 하나로 세어야 하기 때문에, Set을 사용하여 처리.
       대신 정렬을 해주어야 중복을 방지할 수 있음
    2. 처음에는 방문 배열을 다차원으로 구성할까 생각했지만, banned_id마다 한 차원이라고 고려하면 공간복잡도가 안될 것이라고 고려
    */
    static class Ban {
        int idx;
        List<String> banList;
        
        public Ban(int idx) {
            this.idx = idx;
            this.banList = new ArrayList<>();
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        Map<String, Object> map = new HashMap<>();
        for(String ban : banned_id) {
            List<String> list = new ArrayList<>();
            // 해당하는 유저 아이디 탐색
            for(String user : user_id) {
                boolean flag = false;
                // 만약 길이가 같지 않다면 제외
                if(user.length() != ban.length()) continue;
                for(int i=0; i<user.length(); i++) {
                    if(ban.charAt(i) != '*' && ban.charAt(i) != user.charAt(i)) {
                        flag = true;
                    }
                }
                if(!flag) list.add(user);
            }
            map.put(ban, list);
        }
        Set<String> duplicateCheck = new HashSet<>();
        bfs(banned_id, map, duplicateCheck);
        
        return duplicateCheck.size();
    }
    
    static void bfs(String[] banned_id, Map<String, Object> map, Set<String> duplicateCheck) {
        PriorityQueue<Ban> pq = new PriorityQueue<>((o1, o2) -> o2.idx - o1.idx);
        pq.offer(new Ban(0));
        while(!pq.isEmpty()) {
            Ban ban = pq.poll();
            // banned_id의 마지막까지 만족한다면, 정렬 후 join하여 Set에 추가
            if(ban.idx == banned_id.length) {
                Collections.sort(ban.banList);
                duplicateCheck.add(String.join("", ban.banList));
                continue;
            }
            List<String> newBanList = (List<String>) map.get(banned_id[ban.idx]);
            for(String newBan : newBanList) {
                // 새로운 요소가 현재 Ban이 가지고 있는 banList에 존재하지 않는다면
                // 새로운 Ban을 만들고 idx+1을 하여 pq에 추가
                if(!ban.banList.contains(newBan)) {
                    Ban nextBan = new Ban(ban.idx+1);
                    nextBan.banList.addAll(ban.banList);
                    nextBan.banList.add(newBan);
                    pq.offer(nextBan);
                }
            }
        }
    }
}

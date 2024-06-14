import java.util.;
class Solution {
    
    접근 방식  생성된 점은 진입 차수가 없고 진출 차수가 1이상인 노드임. 그렇기 때문에 생성된 점을 먼저 찾는다.
              후에 생성된 점은 개입하지 않으면서 탐색 진행.
              1. 한 점이라도 진출 차수가 없고, 진입 차수는 있다면 막대 그래프
              2. 한 점이라도 진출 차수가 2 이상이라면 8자 그래프
              3. 둘 다 아니라면 도넛 그래프
    주안점
    1. 생성된 점을 구하고 일반적으로 탐색을 시작하면, 모든 노드를 생성했기 때문에 원래 없는 노드까지 추가될 수 있음.
    2. 그래서 처음에 간선으로 다 연결하고, 간선이 없는 노드는 애초에 없는 것이기 때문에 방문 배열을 true로 초기화.
    3. 사실 위 접근 방식에 근거하여 탐색하지 않고 조건 검사만 하는 것이 최적임.
    
    
    static ListInteger[] out, reverse;
    static boolean[] v;
    static boolean isEight, isRod;
    public int[] solution(int[][] edges) {
        int[] answer = {};
        int doughnut = 0;
        int rod = 0;
        int eight = 0;
        
        int max = 0;
        for(int i=0; iedges.length; i++) {
            max = Math.max(edges[i][0], Math.max(max, edges[i][1]));
        }
        
        out = new List[max+1];
        reverse = new List[max+1];
        for(int i=1; i=max; i++) {
            out[i] = new ArrayList();
            reverse[i] = new ArrayList();
        }
        
        for(int i=0; iedges.length; i++) {
            out[edges[i][0]].add(edges[i][1]);
            reverse[edges[i][1]].add(edges[i][0]);
        }
        
        v = new boolean[max+1];
        for(int i=1; i=max; i++) {
            if(out[i].isEmpty() && reverse[i].isEmpty()) v[i] = true;
        }
        
         생성된 노드 찾기
        int share = 0;
        for(int i=1; i=max; i++) {
            ListInteger nowOut = out[i];
            ListInteger nowReverse = reverse[i];
            if(nowOut.size()  1 && nowReverse.isEmpty()) {
                share = i;
                break;
            }
        }
        
         생성된 노드 제거하기
        ListInteger shareOut = out[share];
        for(int so  shareOut) {
            ListInteger nowReverse = reverse[so];
            ListInteger curReverse = new ArrayList();
            for(int cur  nowReverse) if(cur != share) curReverse.add(cur);
            reverse[so] = curReverse;
        }
        
        v[share] = true;
        for(int i=1; i=max; i++) {
            if(v[i]) continue;
            isEight = false;
            isRod = false;
            dfs(i, i);
            if(isRod) rod++;
            else if(isEight) eight++;
            else doughnut++;
            
        }
        return new int[]{share, doughnut, rod, eight};
    }
    
    static void dfs(int idx, int start) {
        v[idx] = true;
        
        ListInteger nowOut = out[idx];
        ListInteger nowReverse = reverse[idx];
        
        for(int i  nowOut) if(!v[i]) dfs(i, start);
        
        if(nowOut.size()  1) isEight = true;
        
        for(int i  nowReverse) if(!v[i]) dfs(i, start);
        
        if(nowOut.size() == 0) isRod = true;
    }
}
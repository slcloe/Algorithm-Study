import java.io.*;
import java.util.*;
public class 0304_곽희웅 {
    
    /*
    접근 방식 : 처음에는 단순한 완전 탐색 트리 문제인줄 알고 접근했으나 뒤에서부터 탐색하며 가장 오래 걸리는 서브트리부터
               탐색해야한다는 것을 깨달음
    자료 구조 : ProrityQueue를 사용해서 노드에 걸리는 시간을 정렬
    
    주안점
    1. 깊이가 깊다고, 자식이 많다고 해서 무조건 오래걸리는 트리는 아님
    2. 조건이 같다면 현재 자식의 수만큼 +를 해줘야 함
    */
    static List<Integer>[] g;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        g = new List[N+1];
        for(int i=0; i<=N; i++) {
            g[i] = new ArrayList<>();
        }
        int root = Integer.parseInt(st.nextToken());
        for(int i=1; i<N; i++) {
            g[Integer.parseInt(st.nextToken())].add(i);
        }

        System.out.println(dfs(0));

    }

    static int dfs(int num) {
        int count = 0;
        int max = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        // 자식이 없는 노드의 max는 0
        for(int cur : g[num]) {
            pq.offer(new int[] {cur, dfs(cur)});
        }

        // 자식의 수만큼 +를 해줘야 하기 때문에 count를 증가시킴
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            count++;
            max = Math.max(max, cur[1] + count);
        }
        return max;
    }
}

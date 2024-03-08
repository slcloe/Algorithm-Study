import java.io.*;
import java.util.*;
public class 0308_곽희웅 {

    /*
    접근 방식 : 최소 스패닝 트리 알고리즘을 사용해야하는 문제로, 프림 알고리즘을 사용하였습니다.
    자료 구조 : PriorityQueue를 사용해서 무게가 큰 다리를 우선순위로 올렸습니다.

    주안점
    1. 노드를 구성할 때 다리 개수가 아닌 집 개수로 크기를 구성해야 함
    2. 완전 탐색을 하며 끝 지점과 연결된 다리 중 가장 무게가 큰 것이 정답이라고 할 수 있지만, 그 전 다리에서 더 무게가 낮을 수 있음
    */

    static List<int[]>[] g;
    static int N, S, E;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        g = new List[N+1];
        for(int i=1; i<=N; i++) {
            g[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int H1 = Integer.parseInt(st.nextToken());
            int H2 = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            g[H1].add(new int[] {H2, W});
            g[H2].add(new int[] {H1, W});
        }
        System.out.println(bfs());
    }

    static int bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[2] - o1[2]);
        int max = 0;
        boolean[] v = new boolean[N+1];
        for(int[] road : g[S]) pq.offer(new int[] {S, road[0], road[1]});
        v[S] = true;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[1] == E) {
                max = Math.max(max, cur[2]);
                continue;
            }
            if(v[cur[1]]) continue;
            v[cur[1]] = true;
            for(int[] road : g[cur[1]]) if(!v[road[0]]) pq.offer(new int[] {cur[1], road[0], Math.min(cur[2], road[1])});
        }
        return max;
    }
}

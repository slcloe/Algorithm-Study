import java.util.*;
import java.io.*;
public class Main_bj_10423 {

    /*

    접근 방식 : 전형적인 MST 문제, 단순히 풀이하면 발전소와 상관없이 정렬되기 때문에 주의해야 함

    주안점
    1. 처음 간선들을 PriorityQueue에 넣고, 탐색할 때 어차피 비용 오름차순으로 정렬돼있기 때문에 중복 적용되지 않도록 아직 방문하지 않은 곳만 탐색해야 함

     */
    static List<int[]>[] g;
    static int N;
    static int total, node[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        g = new List[N+1];

        st = new StringTokenizer(br.readLine(), " ");
        List<Integer> ps = new ArrayList<>();
        for(int i=0; i<K; i++) ps.add(Integer.parseInt(st.nextToken()));

        for(int i=1; i<=N; i++) g[i] = new ArrayList<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            g[U].add(new int[] {V, W});
            g[V].add(new int[] {U, W});
        }

        total = 0;
        node = new int[N+1];
        Arrays.fill(node, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for(int next : ps) {
            for(int[] p : g[next]) {
                if(!ps.contains(p[0])) pq.offer(p);
            }
            node[next] = 0;
        }

        prim(pq);

        for(int i=1; i<=N; i++) total += node[i];
        System.out.println(total);

    }

    static void prim(PriorityQueue<int[]> pq) {
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            if(node[now[0]] > now[1]) {
                node[now[0]] = now[1];
                for(int[] next : g[now[0]]) {
                    if(node[next[0]] == Integer.MAX_VALUE) pq.offer(next);
                }
            }
        }
    }
}

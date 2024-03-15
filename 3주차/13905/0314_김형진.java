package week3;
// 세부
import java.util.*;
import java.io.*;
public class BOJ13905 {
    static int N, M, start, end;
    static boolean[] visited;
    static int[] dist;
    static List<int[]> edges[];
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st= new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N+1];
        dist = new int[N+1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st= new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[s].add(new int[] {e,w});
            edges[e].add(new int[] {s,w});
        }

        PriorityQueue <int[]> pq = new PriorityQueue <>((o1, o2) -> -(o1[1]-o2[1]));
        visited = new boolean[N+1];
         dist[start] = Integer.MAX_VALUE;
         pq.add(new int[] {start, 0});

        while(!pq.isEmpty()){
            int cur = pq.poll()[0];
            if(visited[cur]) continue;
            visited[cur] = true;
            // edge 출발하는 정점을 순회
            for (int i = 0; i < edges[cur].size(); i++) {
                int next_edge = edges[cur].get(i)[0];
                int next_cost = edges[cur].get(i)[1];
                dist[next_edge] = Math.max(dist[next_edge], Math.min(dist[cur], next_cost));
                pq.add(edges[cur].get(i));
            }
        }
        System.out.println(dist[end]);
    }
}

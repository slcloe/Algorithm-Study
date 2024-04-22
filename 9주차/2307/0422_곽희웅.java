import java.io.*;
import java.util.*;

public class Main {

    /*
    접근 방식 : 처음에 최단 시간 및 최단 경로를 다익스트라로 구해야한다는 것과 최단 경로 내에서 하나씩 빼면서 구해야한다는 것은 바로 깨달음
               하지만 dfs로 경로를 기억하면 메모리 초과 발생, 힌트 보고 풀었음
    주안점
    1. 최단 경로를 Queue, List 등에 보관하여 구성하면 메모리 초과 발생
    2. 1은 항상 같은 노드와 연결되는 것이 아니기 때문에 최단 경로를 빼는 순서는 N부터 시작해야 함
     */

    static int N, M;
    static List<int[]>[] g;
    static boolean[] v;
    static int[] dist, path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new List[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g[n1].add(new int[]{n2, w});
            g[n2].add(new int[]{n1, w});
        }
        v = new boolean[N + 1];
        v[1] = true;
        dist = new int[N+1];
        path = new int[N+1];
        Arrays.fill(dist, 100_000_000);
        dist[1] = 0;
        bfs2();

        int result = Integer.MIN_VALUE;
        for(int i=N; i>0; i=path[i]) {
            int nextResult = bfs(path[i], i);
            if (nextResult == Integer.MAX_VALUE) {
                result = Integer.MIN_VALUE;
                break;
            }
            result = Math.max(result, nextResult);
        }
        if (result == Integer.MIN_VALUE) System.out.println(-1);
        else System.out.println(result - dist[N]);
    }

    static int bfs(int n1, int n2) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        v = new boolean[N + 1];
        pq.offer(new int[]{1, 0});
        int arrived = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == N) {
                arrived = Math.min(arrived, cur[1]);
                continue;
            }
            if (v[cur[0]]) continue;
            v[cur[0]] = true;
            for (int[] next : g[cur[0]]) {
                if ((cur[0] == n1 && next[0] == n2) || v[next[0]]) continue;
                pq.offer(new int[]{next[0], cur[1] + next[1]});
            }
        }
        return arrived;
    }

    static void bfs2() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[] {1, 0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            for(int[] next : g[cur[0]]) {
                if(dist[next[0]] > dist[cur[0]] + next[1]) {
                    dist[next[0]] = dist[cur[0]] + next[1];
                    path[next[0]] = cur[0];
                    pq.offer(new int[] {next[0],  dist[next[0]]});
                }
            }
        }
    }
}

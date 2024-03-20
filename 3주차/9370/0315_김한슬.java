import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 풀이 방법
 * 간선의 가중치를 저장할 때
 * 경로에 포함된 특정 경로에 대한 가중치는 2 * w - 1
 * 일반 가중치는 2 * w
 *
 * minEdge 의 결과가 홀수라면 특정 경로가 포함된 가중치의 결과라고 판단 가능.
 *
 */

public class Main {
    static int n, m, t;

    static ArrayList<int []>[] graph;
    static ArrayList<Integer> stopover;
    static int[] minEdge;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n + 1];
            minEdge = new int[n + 1];

            for (int j = 0; j <= n ; j++) {
                graph[j] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine(), " ");
            int s, g, h;
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            for (int j = 0; j < m; j++) {
                int a, b, d;

                st = new StringTokenizer(br.readLine(), " ");

                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                if ((g == a && h == b) || (h == a && g == b)){
                    graph[a].add(new int[]{b, d * 2 - 1});
                    graph[b].add(new int[]{a, d * 2 - 1});
                }else{
                    graph[a].add(new int[]{b, d * 2});
                    graph[b].add(new int[]{a, d * 2});
                }
            }

            stopover = new ArrayList<>();
            for (int j = 0; j < t; j++) {
                stopover.add(Integer.parseInt(br.readLine()));
            }

            dijkstra(s);
            Collections.sort(stopover);
            for(int node: stopover) {
                System.out.println(minEdge[node]);
                if (minEdge[node] % 2 == 1) sb.append(node + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dijkstra(int u)
    {
        boolean[] v = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));

        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[u] = 0;

        pq.add(new int[] {u, 0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if (v[cur[0]]) continue;
            v[cur[0]] = true;

            for(int[] node: graph[cur[0]]){
                if (!v[node[0]] && minEdge[node[0]] > minEdge[cur[0]] + node[1]){
                    minEdge[node[0]] = minEdge[cur[0]]+node[1];
                    pq.add(new int[]{node[0], minEdge[node[0]]});
                }
            }
        }

    }
}

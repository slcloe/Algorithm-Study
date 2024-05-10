package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
풀이 방법
1. 주어진 그래프에서의 최단 경로를 구한다 ( result )
2. 최단경로를 구성한 vertex에 검문소가 있다고 가정하여 다익스트라를 다시 계산한다
3. 다시 계산된 경로 중 가장 가중치가 큰 경로를 저장한ㄷ ( delay )
4. 정답 : result - delay ( 만약, delay 의 값이 MAX_INTEGER이라면 도착할 수 없다는 뜻으로 -1를 출력한다.)

 */
public class week9_2307_0422_김한슬 {
    static int N, M;
    static ArrayList<int[]>[] g;
    static int[] path;
    static int[] minEdge;

    static int dijkstra(){
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        minEdge[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
           return Integer.compare(o1[1], o2[1]);
        }); // start, distance

        pq.offer(new int[] {1, minEdge[1]});

        while(!pq.isEmpty()) {
            int cur[] = pq.poll();
            int minVertex = cur[0];

            if (cur[1] > minEdge[cur[0]]) continue;
            if (cur[0] == N) break;

            for(int[] edge : g[minVertex]){
                if (minEdge[edge[0]] > minEdge[minVertex] + edge[1]) {
                    minEdge[edge[0]] = minEdge[minVertex] + edge[1];
                    path[edge[0]] = cur[0];
                    pq.offer(new int[]{edge[0], minEdge[edge[0]]});
                }
            }
        }

        return minEdge[N];
    }

    static int dijkstraDelay(int from, int to){
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1[1], o2[1]);
        }); // start, distance

        pq.offer(new int[] {1, minEdge[1]});

        while(!pq.isEmpty()) {
            int cur[] = pq.poll();
            int minVertex = cur[0];

            if (cur[1] > minEdge[cur[0]]) continue;
            if (cur[0] == N) break;

            for(int[] edge : g[minVertex]){
                if (minVertex == from && edge[0] == to) continue;
                if (minVertex == to && edge[0] == from) continue;
                if ( minEdge[edge[0]] > minEdge[minVertex] + edge[1]) {
                    minEdge[edge[0]] = minEdge[minVertex] + edge[1];
                    pq.offer(new int[]{edge[0], minEdge[edge[0]]});
                }
            }
        }

        return minEdge[N];
    }

    public static void main(String[] args)  throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int result;
        int delay;
        g = new ArrayList[N + 1];
        minEdge = new int[N + 1];
        path = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            g[from].add(new int[]{to, weight});
            g[to].add(new int[]{from, weight});
        }

        result = delay = dijkstra();
        int a = N;
        while (a != 1) {
            int res = dijkstraDelay(a, path[a]);
            delay = Math.max(delay, res);
            a = path[a];
        }

        if (delay == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(delay - result);
    }
}

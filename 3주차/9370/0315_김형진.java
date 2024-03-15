package week3;

import java.util.*;
import java.io.*;

//        다익스트라 3번? -> X
//        s -> g
//        g -> h
//        h -> e
//
//        찾은 풀이는 모든 간선의 가중치에 2를 곱한 뒤 g->h간선에만 1을 빼서 조정
//        최단거리를 구했을 때 경로의 가중치 합이 홀수임을 확인하면 g->h간선을 지났다는 것이 식별 가능하다
public class BOJ9370 {

    static int T;
    static int n, m, t;
    static int s, g, h;

    static List<int[]>[] graph;
    static int[] path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n + 1];
            path = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            Arrays.fill(path, Integer.MAX_VALUE);

            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                // 여기..
                int weight = 2 * Integer.parseInt(st.nextToken());

                if ((from == g && to == h) || (from == h && to == g)) {
                    weight -= 1;
                }

                graph[from].add(new int[] {to, weight});
                graph[to].add(new int[] {from, weight});
            }

            findPath(s);
            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < t; i++) {
                int possibleEnd = Integer.parseInt(br.readLine());
                if (path[possibleEnd] % 2 != 0) {
                    result.add(possibleEnd);
                }
            }

            Collections.sort(result);
            for (Integer n : result) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static void findPath(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        path[start] = 0;
        pq.add(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int curPos = current[0];
            int curWeight = current[1];

            if (curWeight > path[curPos]) continue;

            for (int[] next: graph[curPos]) {
                int cost = curWeight + next[1];

                if (path[next[0]] > cost) {
                    path[next[0]] = cost;
                    pq.add(new int[]{next[0], cost});
                }
            }
        }
    }
}
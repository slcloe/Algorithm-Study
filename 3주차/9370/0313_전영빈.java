import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    예상 목적지가 실제 목적지가 되기 위해서는 시작 지점부터 해당 목적지까지의 최단 경로에 g~h 간의 간선이 포함되어야 한다.
    이 경우, 최단 경로가 될 수 있는 케이스는 다음의 두 가지이다.
    1. 시작지점 - g - h - 예상 목적지.
    2. 시작지점 - h - g - 예상 목적지.
    따라서 다익스트라 알고리즘을 이용하여 시작지점과 g, h에서부터 다른 지점까지의 최단경로를 구해 놓는다.
    이후, 위의 두 가지 케이스의 경로와 시작 지점에서부터 예상 목적지까지의 최단 경로의 가중치 값이 같을 때
    해당 예상 목적지는 실제 목적지임이 확정된다.
 */
public class 미확인도착지_0313_전영빈 {

    static int n;
    static int m;
    static int t;
    static int s;
    static int g;
    static int h;
    static List<int[]>[] edge;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            edge = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                edge[i] = new ArrayList<>();
            }
            /*
                ArrayList 초기화 시 반복문을 사용하지 않고 fill 을 이용하게 되면
                안에 있는 모든 ArrayList 객체가 같은 래퍼런스로 잡히는 것 같다.
                fill을 사용해 배열을 초기화하고 원소를 추가하면 모든 인덱스에 대해
                add 연산이 적용된다.
             */
//            Arrays.fill(edge, new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()) - 1;
            g = Integer.parseInt(st.nextToken()) - 1;
            h = Integer.parseInt(st.nextToken()) - 1;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int d = Integer.parseInt(st.nextToken());

                edge[a].add(new int[]{d, b});
                edge[b].add(new int[]{d, a});
            }

            int[] sDist = dijkstra(s);
            int[] gDist = dijkstra(g);
            int[] hDist = dijkstra(h);

            List<Integer> dest = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                int cand = Integer.parseInt(br.readLine()) - 1;

                if (sDist[g] + gDist[h] + hDist[cand] == sDist[cand] || sDist[h] + gDist[h] + gDist[cand] == sDist[cand]) {
                    dest.add(cand+1);
                }
            }

            Collections.sort(dest);
            for (Integer d : dest) {
                sb.append(d + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static int[] dijkstra(int start) {
        /*
            간선의 최대 가중치는 1000 이고 간선은 최대 50000개 존재하므로
            가능한 특정 지점 간의 최단 거리 최대 값은 50000000.
            따라서 int 범위로 표현 가능하다.
         */
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> {
            return o1[0] - o2[0];
        });
        heap.offer(new int[]{0, start});

        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            if (current[0] > dist[current[1]]) {
                continue;
            }

            for (int[] e : edge[current[1]]) {
                if (dist[current[1]] + e[0] < dist[e[1]]) {
                    dist[e[1]] = dist[current[1]] + e[0];
                    heap.offer(new int[]{dist[e[1]], e[1]});
                }
            }
        }

        return dist;
    }
}

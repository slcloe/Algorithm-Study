import java.io.*;
import java.util.*;
public class 0311_곽희웅 {

    /*
    접근 방식 : 도로의 개수 50000 * 도로의 최대 길이 1000(원래는 999) 해도 1억보다 작기 때문에 dijkstra 배열의 최대값은 1억
               가능한 목적지의 수만큼 다익스트라를 진행하면 시간 초과, 처음에 시작점, 경유점 2개에서 다익스트라를 진행하여 계산
    자료 구조 : 리스트 배열을 사용하여 그래프를 구성, PriorityQueue를 사용하여 다익스트라 진행

    주안점
    1. 문제 이해가 어려웠는데, 각 목적지로 가는 최단 경로가 G - H를 지나는지에 대한 문제임
    2. S -> G + G - H의 길이를 하면 S -> H가 됨, 이러한 개념으로 양쪽의 최단 거리 구하기
     */

    static List<int[]>[] g;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int c=0; c<C; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            int S = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            g = new List[N+1];

            for(int i=1; i<=N; i++) {
                g[i] = new ArrayList<>();
            }
            int cross = 0;
            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int D = Integer.parseInt(st.nextToken());
                g[A].add(new int[] {B, D});
                g[B].add(new int[] {A, D});
                if((A == G && B == H) || (A == H && B == G)) cross = D;
            }

            List<Integer> dst = new ArrayList<>();
            for(int i=0; i<T; i++) dst.add(Integer.parseInt(br.readLine()));

            // G와 H에서 모든 목적지 후보의 최단 거리를 구한 후
            // S -> G + G와 H의 길이는 S -> H
            // S -> H + G와 H의 길이는 S -> G
            // 여기서 다른 목적지 후보들 중 최단 거리 구하기
            int[] distS = dijkstra(S);
            int[] distG = dijkstra(G);
            int[] distH = dijkstra(H);

            List<Integer> list = new ArrayList<>();
            for(int d : dst) {
                if((distS[G]+cross+distH[d] == distS[d]) || (distS[H]+cross+distG[d] == distS[d])) list.add(d);
            }

            Collections.sort(list);
            StringBuilder db = new StringBuilder();
            for(int d : list) {
                db.append(d).append(" ");
            }
            sb.append(db.toString().strip()).append("\n");
        }
        System.out.print(sb.toString());
    }
    static int[] dijkstra(int start) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, 100_000_000);
        boolean[] v = new boolean[N+1];
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int [] {start, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(v[cur[0]]) continue;
            v[cur[0]] = true;
            for(int[] road : g[cur[0]]) {
                if(!v[road[0]] && dist[road[0]] > cur[1] + road[1]) {
                    dist[road[0]] = cur[1] + road[1];
                    pq.offer(new int[] {road[0], dist[road[0]]});
                }
            }
        }
        return dist;
    }
}

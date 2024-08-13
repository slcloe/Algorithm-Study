import java.io.*;
import java.util.*;
public class Main {
    
    /*
    
    접근 방식 : 그냥 다익스트라 문제, 여우는 기본적으로 풀면 되지만 늑대를 구할 때 유의해야 함
    
    주안점
    1. 나누기 2, 곱하기 2를 해야하기 때문에 d가 홀수인 상황을 방지하기 위해 처음에 *2를 해줌
    2. 그냥 Deque을 사용하면 무조건 시간초과가 나기 때문에 PriorityQueue 사용 필수
    3. 홀수, 짝수, 홀수 순서대로 나아가기 때문에 2차원 배열을 활용한다는 것을 이해하면 편함
    4. 여담으로 d*2를 두 번 넣어주면 통과하는데 d를 받을 때 *2를 해서 넣어주면 시간초과....?
    
    */
    static int N, M;
    static List<int[]>[] g;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        g = new List[N+1];
        for(int i=1; i<=N; i++) {
            g[i] = new ArrayList<>();
        }

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            g[a].add(new int[] {b, d*2});
            g[b].add(new int[] {a, d*2});
        }

        int[] fox = dijkstra();
        int[][] wolf = dijkstra2();
        int count = 0;
        for(int i=2; i<=N; i++) {
            if(fox[i] < Math.min(wolf[0][i], wolf[1][i])) count++;
        }
        System.out.print(count);
    }

    static int[][] dijkstra2() {
        int[][] dijkstra = new int[2][N+1];
        Arrays.fill(dijkstra[0], Integer.MAX_VALUE);
        Arrays.fill(dijkstra[1], Integer.MAX_VALUE);
        dijkstra[0][1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        // 0이면 이번 오솔길은 두 배 빨리
        // 1이면 이번 오솔길은 두 배 느림
        // 처음은 무조건 두 배 빨리
        pq.offer(new int[] {1, 0, 0});
        while(!pq.isEmpty()) {
            int[] node = pq.poll();

            if(dijkstra[node[1]][node[0]] < node[2]) continue;

            for(int[] cur : g[node[0]]) {
                int curLoad = node[1] == 0 ? cur[1] / 2 : cur[1] * 2;
                int next = node[1] == 0 ? 1 : 0;

                if(dijkstra[next][cur[0]] > curLoad + node[2]) {
                    dijkstra[next][cur[0]] = curLoad + node[2];
                    pq.offer(new int[] {cur[0], next, dijkstra[next][cur[0]]});
                }
            }
        }
        return dijkstra;
    }

    static int[] dijkstra() {
        int[] dijkstra = new int[N+1];
        Arrays.fill(dijkstra, Integer.MAX_VALUE);
        dijkstra[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[] {1, 0});
        while(!pq.isEmpty()) {
            int[] node = pq.poll();

            if(dijkstra[node[0]] < node[1]) continue;

            for(int[] cur : g[node[0]]) {
                if(dijkstra[cur[0]] > cur[1] + node[1]) {
                    dijkstra[cur[0]] = cur[1] + node[1];
                    pq.offer(new int[] {cur[0], dijkstra[cur[0]]});
                }
            }
        }
        return dijkstra;
    }
}

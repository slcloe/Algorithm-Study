import java.io.*;
import java.util.*;

public class Main_bj_20303 {

    static int group[][], candy[];

    static boolean[] v;

    static List<Integer>[] g;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        candy = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) candy[i] = Integer.parseInt(st.nextToken());

        g = new List[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int F1 = Integer.parseInt(st.nextToken());
            int F2 = Integer.parseInt(st.nextToken());
            g[F1].add(F2);
            g[F2].add(F1);
        }

        group = new int[N + 1][2];
        v = new boolean[N + 1];
        int idx = 0;
        for (int i = 1; i <= N; i++) if (!v[i]) bfs(i, idx++);

        int[] knapsack = new int[K];
        for (int[] cur : group) {
            for (int i = K - 1; i >= cur[0]; i--) {
                knapsack[i] = Math.max(knapsack[i], knapsack[i - cur[0]] + cur[1]);
            }
        }
        System.out.println(knapsack[K - 1]);
    }

    static void bfs(int i, int idx) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(i);
        int sum = 0;
        int cry = 0;
        while (!dq.isEmpty()) {
            int cur = dq.poll();
            cry++;
            sum += candy[cur];
            v[i] = true;
            for (int node : g[cur]) {
                if (!v[node]) {
                    v[node] = true;
                    dq.offer(node);
                }
            }
        }
        group[idx][0] = cry;
        group[idx][1] = sum;
    }
}
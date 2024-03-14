import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    생각한 해결 방안
    1. 이분 탐색과 bfs를 이용해서 S부터 E까지의 최대 개수 구하기.
    2. 크루스칼 알고리즘을 변형하여 최대 가중치 스패닝 트리를 구하여 최대 개수 구하기.

    1번 방법으로는 풀어본 적이 있어서 2번 방법으로 풀어봤다.
 */
public class 세부_0312_전영빈 {

    static int N;
    static int M;
    static int S;
    static int E;
    static PriorityQueue<int[]> heap;
    static int[] parent;
    static int[] degree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()) - 1;
        E = Integer.parseInt(st.nextToken()) - 1;

        parent = new int[N];
        degree = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            degree[i] = 1;
        }

        heap = new PriorityQueue<int[]>((o1, o2) -> {
            return -(o1[0] - o2[0]);
        });

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            heap.offer(new int[]{k, h1-1, h2-1});
        }

        int count = 0;
        int sol = 0;

        /*
            크루스칼 알고리즘에서 간선을 N-1개 선택했다면 이는 이미 스패닝 트리가 완성되었다는 뜻이므로
            더 이상 반복문을 돌릴 필요가 없다.
         */
        while (!heap.isEmpty() && count < N-1) {
            int[] edge = heap.poll();

            int x = find(edge[1]);
            int y = find(edge[2]);

            if (x != y) {
                union(x, y);
                count++;
                sol = edge[0];
            }

            /*
                S와 E가 같은 부모를 가지면, 즉 같은 집합에 속하면
                굳이 스패닝 트리를 구할 필요가 없다.
             */
            if (find(S) == find(E)) {
                break;
            }
        }

        if (find(S) == find(E)){
            System.out.println(sol);
        } else {
            System.out.println(0);
        }
    }

    static int find(int x) {
        // 경로 압축.
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (degree[x] >= degree[y]) {
            parent[y] = x;
            degree[x] += degree[y];
        } else {
            parent[x] = y;
            degree[y] += degree[x];
        }
    }
}

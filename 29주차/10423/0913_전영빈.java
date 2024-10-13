import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    모든 정점을 연결하는 최소 비용 그래프 -> MST
    특정 도시에는 이미 발전소가 존재하고, 한 도시가 두 개의 발전소로부터 전기를 공급받으면 안된다.
    -> 발전소가 존재하는 도시가 이미 MST 집합에 포함되어 있다고 가정하고 풀이하자.

    크루스칼 알고리즘으로 풀이.
 */

public class 전기가부족해_0913_전영빈 {

    static int N, M, K;
    static boolean[] visited;
    static List<int[]>[] edge;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        edge = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edge[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            visited[Integer.parseInt(st.nextToken())] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edge[u].add(new int[]{v, w});
            edge[v].add(new int[]{u, w});
        }

        System.out.println(mst(N - K));
    }

    static int mst(int threshold) {

        int res = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        for (int i = 1; i < visited.length; i++) {
            if (visited[i]) {
                for (int[] e : edge[i]) {
                    if (!visited[e[0]]) {
                        heap.add(new int[]{e[1], i, e[0]});
                    }
                }
            }
        }

        for (int i = 0; i < threshold; i++) {
            while (!heap.isEmpty()) {
                int[] target = heap.poll();

                if (!visited[target[2]]) {
                    res += target[0];

                    for (int[] e : edge[target[2]]) {
                        if (!visited[e[0]]) {
                            heap.add(new int[]{e[1], target[2], e[0]});
                        }
                    }

                    visited[target[2]] = true;
                    break;
                }
            }
        }

        return res;
    }
}

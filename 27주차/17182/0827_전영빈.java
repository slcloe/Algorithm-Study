import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    모든 행성들을 방문할 때의 최단 거리를 구하는 문제.
    단, 똑같은 간선이라도 진행 방향에 따라 가중치가 다르고 이미 방문한 행성을 다시 방문해도 된다는 조건이 있다.
    조건에 따라 A->B->C 동선보다 A->B->A->C 와 같은 동선이 보다 빠를 수도 있다.

    따라서, i번과 j번까지의 거리는 i와 j 사이의 최단 거리가 아니며, 실제 i와 j의 최단 거리를 찾아야 한다.
    플로이드-워셜 알고리즘을 통해 전체 노드 간의 최단 거리를 찾자.
    
    이후, 백트래킹을 통해 노드의 방문 순서에 따른 동선의 최단 거리를 계산하자.
 */

public class 우주탐사선_0827_전영빈 {

    static int N;
    static int[][] weight;
    static int sol = Integer.MAX_VALUE;
    static boolean[] selected;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        weight = new int[N][N];
        selected = new boolean[N];
        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                weight[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    weight[i][j] = Math.min(weight[i][j], weight[i][k] + weight[k][j]);
                }
            }
        }

        selected[K] = true;
        bt(K, 0, 0);

        System.out.println(sol);
    }

    static void bt(int current, int depth, int value) {
        if (value >= sol) {
            return;
        }

        if (depth == N - 1) {
            sol = value;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!selected[i]) {
                selected[i] = true;
                bt(i, depth + 1, value + weight[current][i]);
                selected[i] = false;
            }
        }
    }
}

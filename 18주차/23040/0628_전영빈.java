import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
누텔라 경로가 되기 위한 조건은 경로의 문자열 중 첫 번째 문자만 검정색이고 나머지 문자는 모두 빨강색이어야 한다.
즉, 그래프 상에서 인접한 검정색 문자는 클러스터링해서 관리해도 된다.
유니온-파인드를 통해 인접한 검정색 문자들끼리 클러스터링한다.
이 때, 클러스터의 값은 클러스터링된 검정색 문자 노드의 개수.

이후 빨간색 문자들을 대상으로 인접한 노드를 탐색했을 때, 검정색 문자를 있으면 누텔라 경로를 만들 수 있다.
 */

public class 누텔라트리_0628_전영빈 {

    static int N;
    static List<Integer>[] edge;
    static int[] degree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());
        edge = new ArrayList[N];
        degree = new int[N];
        for (int i = 0; i < N; i++) {
            edge[i] = new ArrayList<Integer>();
            degree[i] = -1;
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            edge[u].add(v);
            edge[v].add(u);
        }

        char[] color = br.readLine().toCharArray();

        for (int i = 0; i < N; i++) {

            if (color[i] != 'R') {
                continue;
            }

            for (Integer j : edge[i]) {
                if (color[j] != 'R') {
                    continue;
                }

                union(i, j);
            }
        }

        long sol = 0;

        for (int i = 0; i < N; i++) {
            if (color[i] == 'B') {
                for (Integer red : edge[i]) {
                    if (color[red] == 'R') {
                        sol += degree[find(red)];
                    }
                }
            }
        }

        System.out.println(sol * -1);
    }

    static int find(int a) {
        if (degree[a] > -1) {
            return degree[a] = find(degree[a]);
        }

        return a;
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }

        if (degree[a] <= degree[b]) {
            degree[a] += degree[b];
            degree[b] = a;
        } else {
            degree[b] += degree[a];
            degree[a] = b;
        }
    }
}

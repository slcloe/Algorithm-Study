import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    모든 노드들에 대해서 탐색을 하면서 다음과 같은 조건으로 ㄷ 트리와 ㅈ 트리를 찾는다.

    1. 해당 노드에 연결된 간선이 3개 이상 있으면 그 노드를 가운데 노드로 한 ㅈ 트리를 만들 수 있다.
    만들 수 있는 트리의 개수는 (간선의 개수)C3

    2. 연결된 두 개의 노드를 통해 ㄷ 트리를 만들 수 았다.
    트리가 만들어지기 위해서는 각 노드가 2개 이상의 간선을 가지고 있어야 한다. (둘 간의 연결된 간선은 제외하므로)
    만들 수 있는 트리의 개수는 (A 노드의 간선의 개수 - 1) * (B 노드의 간선의 개수 - 1)

    두 조건을 계산하는 과정에서 간선의 개수가 지나치게 크면 int 범위를 넘어 오버플로우가 발생할 수 있다.
    long 으로 간선의 길이를 처리해주자.
 */

public class ㄷㄷㄷㅈ_0725_전영빈 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Integer>[] edge = new ArrayList[N];
        boolean[] visited = new boolean[N];
        long dc = 0L;
        long gc = 0L;

        for (int i = 0; i < N; i++) {
            edge[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            edge[u].add(v);
            edge[v].add(u);
        }

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            long cl = edge[i].size();

            if (cl >= 3) {
                gc += cl * (cl - 1) * (cl - 2) / 6;
            }

            for (Integer next : edge[i]) {
                if (!visited[next]) {
                    long nl = edge[next].size();
                    if (cl >= 2 && nl >= 2) {
                        dc += (cl - 1) * (nl - 1);
                    }
                }
            }
        }

        if (dc == gc * 3) {
            System.out.println("DUDUDUNGA");
        } else if (dc > gc * 3) {
            System.out.println("D");
        } else {
            System.out.println("G");
        }
    }
}

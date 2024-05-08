import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_bj_27945 {

    /*
    접근 방식 : 처음에는 그래프 bfs 탐색, 다음에는 날짜 오름차순으로 정렬 후 갈 수 있는 최대 연속 숫자를 구했는데, 시간초과
               후에 에디토리얼 확인 후 사이클 검사를 어떻게 할지 고민했는데, union find로 같은 부모를 가진 두 노드면 break 시킴
    주안점
    1. 숫자를 list에 추가하여 연속된 숫자 중 가장 큰 것을 찾아도 되지만, 어차피 키위새는 하루라도 디저트를 거를 수 없기 때문에 연속되지 않으면 break
    2. kruskal 알고리즘의 방식으로 부모를 같게 하며 사이클을 검사, 여담으로 prim 알고리즘으로 최소 신장 트리를 구한 후 값을 도출하는 방법이 있다고 함
     */

    static int N, v[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int N1 = Integer.parseInt(st.nextToken());
            int N2 = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{N1, N2, T});
        }

        if (!pq.isEmpty() && pq.peek()[2] != 1) {
            System.out.println(1);
        } else {
            v = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                v[i] = i;
            }

            int maxDay = 1;
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                if (cur[2] != maxDay) {
                    break;
                }
                if (union(cur[0], cur[1])) {
                    break;
                }
                maxDay++;
            }
            System.out.println(maxDay);
        }
    }

    static boolean union(int N1, int N2) {
        int result1 = find(N1);
        int result2 = find(N2);
        if (result1 == result2) {
            return true;
        }
        v[result2] = v[result1];
        return false;
    }

    static int find(int i) {
        if (v[i] == i) {
            return i;
        }
        return v[i] = find(v[i]);
    }
}

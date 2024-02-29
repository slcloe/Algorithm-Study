import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    순서가 있는 작업을 처리 => 위상 정렬.
 */

public class 문제집_0227_전영빈 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        /*
            기본적으로 모든 문제의 가중치는 0으로 초기화 한다.
            가능하면 번호가 작은 문제(쉬운 문제)부터 풀어야 하므로 문제 간에 특별한 관계가 없을 경우,
            최소 힙에 가중치가 0인 문제들을 넣으면 번호가 작은 순으로 반환된다.
         */
        int[] degree = new int[N];

        List<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        /*
            문제간에 연관 관계가 있는 경우 도착 문제(B)에 가중치를 1만큼 더한다.
            즉, B 문제는 A 문제가 처리되기 전까지 우선순위 큐에 추가될 수 없다.
         */
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A - 1].add(B - 1);
            degree[B-1]++;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if (degree[i] == 0) {
                heap.offer(i);
            }
        }

        while (!heap.isEmpty()) {
            int current = heap.poll();
            sb.append(current+1 + " ");

            /*
                문제가 해결될 때마다 그 문제와 연관 관계가 있는 문제들의 가중치를 1만큼 줄인다.
                이 때, 그 문제의 가중치가 0이 되면 해당 문제는 이제 풀어도 되는 문제로 간주하고 우선순위 큐에 추가한다.
             */
            for (Integer next : graph[current]) {
                if (--degree[next] == 0) {
                    heap.offer(next);
                }
            }
        }

        System.out.println(sb.toString());
    }
}

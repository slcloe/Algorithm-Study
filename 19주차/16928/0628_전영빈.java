import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
놓치지 말아야 할 부분이 많은 문제.

1. 나이브하게 생각해서 각 지점마다 1 ~ 6 의 범위로 bfs 해야지 => 메모리 초과.
2. 그러면 1 ~ 6 범위에 사다리가 있는 경우만 따로 처리하고 그 이외에는 6만큼 디폴트로 이동 => 6에 사다리가 있을 수도 있음.
3. 뱀은 아래로 내려가니 사다리만 처리하자 => 뱀을 통해서 이동 가능한 특이 케이스를 탐색할 수 없음.

따라서, 1 ~ 6의 범위에 사다리나 뱀이 있는 경우에 대해서 탐색했다.
또한, Set을 통해서 사다리나 뱀이 아닌 주사위를 통해 이동할 수 있는 값을 관리하다가 이 중 최대값에 대해서만 일반이동을 했다.
 */

public class 뱀과사다리게임_0628_전영빈 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> slide = new HashMap<>();
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            slide.put(u, v);
        }

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {1, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            HashSet<Integer> move = new HashSet<>(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));

            if (current[0] >= 94) {
                System.out.println(current[1] + 1);
                System.exit(0);
            }

            for (int i = 1; i <= 6; i++) {
                int next = current[0] + i;

                if (slide.containsKey(next)) {
                    move.remove(i);
                    queue.offer(new int[]{slide.get(next), current[1] + 1});
                }
            }

            if (!move.isEmpty()) {
                queue.offer(new int[]{current[0] + Collections.max(move), current[1] + 1});
            }
        }
    }
}

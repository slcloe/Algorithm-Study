import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_bj_11067 {

    /*
    접근 방식 : 카페의 수는 10만이기 떄문에 절대 완탐으로 안될 것이라고 고려.
               결국 가로 좌표로 뒤로 가지 않는다는 것이고, 대각선으로 가는 것이 아니기 때문에 정렬로 하나씩 더함

    주안점
    1. 우선 가로축 방향으로 정렬 후 0부터 증가시키면서 한 개인지, 여러 개인지 고려
    2. 여러 개라면 세로 축으로 정렬 후 처음 혹은 마지막이 현재의 세로 값이 같은 값을 찾고, 반복문으로 삽입
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                pq.offer(new int[]{x, y});
            }

            int[][] result = new int[pq.size() + 1][2];

            int startY = 0;
            int startX = 0;
            int count = 1;
            while (!pq.isEmpty()) {
                if (pq.peek()[1] > startY) {
                    startY = pq.peek()[1];
                }

                List<int[]> list = new ArrayList<>();
                while (!pq.isEmpty() && pq.peek()[1] == startY) {
                    list.add(pq.poll());
                }

                if (list.size() == 1) {
                    int[] cur = list.get(0);
                    startX = cur[0];
                    startY = cur[1];
                    result[count][0] = cur[0];
                    result[count][1] = cur[1];
                    count++;
                } else {
                    list.sort((o1, o2) -> o1[0] - o2[0]);
                    if (list.get(0)[0] == startX) {
                        for (int i = 0; i < list.size(); i++) {
                            result[count][0] = list.get(i)[0];
                            result[count][1] = list.get(i)[1];
                            count++;
                        }
                        startX = list.get(list.size() - 1)[0];
                    } else {
                        for (int i = list.size() - 1; i >= 0; i--) {
                            result[count][0] = list.get(i)[0];
                            result[count][1] = list.get(i)[1];
                            count++;
                        }
                        startX = list.get(0)[0];
                    }
                }
            }
            st = new StringTokenizer(br.readLine(), " ");
            int M = Integer.parseInt(st.nextToken());
            for (int i = 0; i < M; i++) {
                int now = Integer.parseInt(st.nextToken());
                sb.append(result[now][1]).append(" ").append(result[now][0]).append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}

import java.io.*;
import java.util.*;

public class Main {

    /*
    접근 방식 : 결국 한 집이나 사무실에서 시작하여 세는 것이 가장 크기 때문에 시작점 혹은 도착점을 정렬해야 함
               이번 코드에서는 도착점을 오름차순 정렬하여 계산

    주안점
    1. 현재 거리가 L보다 크다면 max가 될 수 없으므로 continue
    2. PQ에 저장한 후 가장 작은 시작점과 비교 후 연산
     */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] spot = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int S1 = Integer.parseInt(st.nextToken());
            int S2 = Integer.parseInt(st.nextToken());
            if (S1 < S2) {
                spot[i][0] = S1;
                spot[i][1] = S2;
            } else {
                spot[i][0] = S2;
                spot[i][1] = S1;
            }
        }
        int L = Integer.parseInt(br.readLine());
        Arrays.sort(spot, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);

        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if (spot[i][1] - spot[i][0] > L) {
                continue;
            }
            pq.offer(spot[i][0]);

            while (!pq.isEmpty()) {
                if (spot[i][1] - pq.peek() <= L) {
                    break;
                }
                pq.poll();
            }
            answer = Math.max(answer, pq.size());
        }
        System.out.println(answer);
    }
}

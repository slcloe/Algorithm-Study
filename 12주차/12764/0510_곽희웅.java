import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_bj_12764 {

    /*
    접근 방식 : DP처럼 보이지만, 그냥 우선순위 큐를 통해서 풀 수 있었던 문제.
               현재 기다리고 있는 사람, 현재 이용하고 있는 사람, 현재 이용 가능한 자리를 우선순위 큐로 관리하여 해결.

    주안점
    1. StringBuilder의 isEmpty 메서드는 Java11로 제출하면 오류가 발생하니 15로 제출해야 함.
       만약 11로 하고싶다면 length를 사용하면 됨.
    2. isEmpty로 확인해주는 이유는 만약 N만큼의 자리를 모두 이용하게 되는 경우에 값이 들어가지 않을 수 있기 때문임.
    3. 시작 시간과 종료 시간들은 겹치지 않음을 인지하자 !
    */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 자리를 이용한 사람의 누적 수 배열
        int[] accu = new int[N + 1];

        // 현재 가장 작은 자리
        PriorityQueue<Integer> seat = new PriorityQueue<>();

        // 가장 먼저 사용할 사람
        PriorityQueue<int[]> person = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        // 가장 먼저 종료될 사람
        PriorityQueue<int[]> user = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            seat.offer(i);
            person.offer(
                new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        for (int i = 0; i <= 1_000_000; i++) {
            if (user.isEmpty() && person.isEmpty()) {
                break;
            }
            // 만약 가장 먼저 사용할 사람의 시작 시간이 i라면
            // 가장 작은 수의 자리인 nowSeat과 현재 종료 시간을 가지고 user에 넣어줌
            // 해당 자리의 이용자 수 +1
            if (!person.isEmpty() && person.peek()[0] == i) {
                int nowSeat = seat.poll();
                int[] cur = person.poll();
                user.offer(new int[]{cur[1], nowSeat});
                accu[nowSeat]++;
            }
            // 만약 종료 시간이 i라면
            // 우선순위 큐에서 제거 후 자리 반환
            if (!user.isEmpty() && user.peek()[0] == i) {
                int[] cur = user.poll();
                seat.offer(cur[1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (accu[i] == 0) {
                sb.append(i - 1).append("\n");
                break;
            }
        }

        if (sb.isEmpty()) {
            sb.append(N).append("\n");
        }

        for (int i = 1; i <= N; i++) {
            if (accu[i] == 0) {
                break;
            }
            sb.append(accu[i]).append(" ");
        }
        System.out.print(sb.toString());
    }
}

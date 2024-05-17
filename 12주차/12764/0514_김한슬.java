package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * 문제 풀이
 * 1. pq 를 사용하여 먼저 사용 시작, 먼저 사용 종료를 한 순으로 컴퓨터 사용을 배치한다.
 * 2. curSeats pq 를 사용하여 현재 사용하고 있는 좌석의 종료시간을 넣는다.
 * 3. remainSeats pq 를 사용하여 curSeats를 순회하여 사용종료된 자리를 offer 한다.
 * 4. 만약 remainSeats 가 비어있다면 사용가능 좌석이 없다 -> 새로운 컴퓨터를 할당해야함.
 * 5. 만약 remainSeats 가 있다면 사용가능 좌석이 있다 -> 좌석 번호 오름차순으로 할당한다. -> curSeats 에 update
 *
 * pq 를 3개 쓴 이유 : 한 시점에서 사용 가능 좌석이 2개 이상 일 때, 사용 종료 순이 아닌 좌석번호 순으로 배정해야함.
 *                    remainSeats 가 없다면 사용 종료 순으로 밖에 컴퓨터를 할당할 수 밖에 없기 때문에 remainSeat 에 사용가능한 좌석을 모두 담아둬야함.
 */
public class week12_12764_0514_김한슬 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = 1;
        int N = Integer.parseInt(br.readLine());
        int[] seats = new int[N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
            return Integer.compare(o1[0], o2[0]);
        });

        PriorityQueue<int[]> curSeats = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
            else return Integer.compare(o1[0], o2[0]);
        });
        // endTime, seat

        PriorityQueue<Integer>remainSeats = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            pq.offer(new int[] {a, b});
        }

        int[] seat = pq.poll();
        curSeats.offer(new int[] {seat[1] ,0});
        seats[0]++;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            while(!curSeats.isEmpty() && curSeats.peek()[0] <= cur[0]){
                remainSeats.offer(curSeats.poll()[1]);
            }

            if (remainSeats.isEmpty()) {
                seats[size]++;
                curSeats.offer(new int[] {cur[1], size});
                size++;
            } else {
                int removeSeat = remainSeats.poll();
                seats[removeSeat]++;
                curSeats.offer(new int[] {cur[1] ,removeSeat});
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            sb.append(seats[i]).append(" ");
        }
        System.out.print(sb.toString());
    }
}

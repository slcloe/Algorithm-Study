import java.io.*;
import java.util.*;
public class Main_bj_7983 {

    /*
    접근 방식 : 처음에는 answer라는 값을 두고, 최대값을 갱신하는 방법으로 했는데, 그렇게 하지 않아도 됨
               마감 시간이 가장 큰 순서대로 내림차순 정렬하고, 데드라인을 감소시키며 최종 값을 출력

    주안점
    1. 노는 시간을 확보한다는 것은 마감 기한을 모두 그만큼 감소시킨다는 것과 동일함
    2. 처음에 문제를 잘못 이해해서 그냥 언제라도 가장 길게 놀 수 있는 기간인 줄 알았는데, 1일부터임을 명심해야 함
     */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            pq.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        int end = pq.peek()[1];
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(end >= cur[1]) end = cur[1] - cur[0];
            else end -= cur[0];
        }
        System.out.print(end);
    }
}
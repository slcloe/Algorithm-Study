import java.util.*;
import java.io.*;
public class Main_bj_1756 {

    /*

    접근 방식 : 밑이 아무리 넓어도 위가 좁으면 들어갈 수 없는 원리를 이용하여 풀이
    주안점
    1. 그냥 구현이 아니라, 오븐의 지름을 다듬어주는 작업이 필요함. 그리디 요소가 포함되어 있다고 고려됨.

     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        Deque<Integer> dq = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine(), " ");
        int before = Integer.parseInt(st.nextToken());
        dq.offer(before);
        for(int i=1; i<D; i++) {
            int now = Integer.parseInt(st.nextToken());
            before = Math.min(before, now);
            dq.offer(before);
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            while(!dq.isEmpty()) {
                int under = dq.pollLast();
                if(cur <= under) break;
            }
        }

        if(!dq.isEmpty()) System.out.print(dq.size()+1);
        else System.out.print(0);
    }
}

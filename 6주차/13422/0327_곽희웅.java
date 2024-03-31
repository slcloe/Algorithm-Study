import java.io.*;
import java.util.*;
public class 0327_곽희웅 {

    /*
    접근 방식 : N, M이 최대 100_000이며 K는 10억, 한 집당 최대 보유 머니는 10_000으로 100_000 * 10_000 = 10억이기 때문에 int형.
               단순 2중 반복문으로 하면 시간 초과나기 때문에 슬라이딩 윈도우 방식으로 수행

    주안점
    1. N과 M이 같을 때는 계속해서 같은 집들만 탐색하기 때문에 1가지의 방법으로 보아야 함
     */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            int[] home = new int[N+M-1];
            for(int i=0; i<N; i++) home[i] = Integer.parseInt(st.nextToken());
            for(int i=N; i<N+M-1; i++) home[i] = home[i-N];

            int sum = 0;
            for(int i=0; i<M; i++) {
                sum += home[i];
            }
            int count = 0;
            if(sum < K) count++;
            if(N != M) {
                for(int i=1; i<N; i++) {
                    sum -= home[i-1];
                    sum += home[i+M-1];
                    if(sum < K) count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.print(sb.toString());
    }
}

import java.io.*;
import java.util.*;
import java.util.*;

public class BOJ_13422 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int N, M, K, cnt, sum;
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cnt = 0;
            sum = 0;
            int[] houses = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N + M - 1; i++) {
                if(i < N) {
                    houses[i] = Integer.parseInt(st.nextToken());
                    sum+= houses[i];
                } else {
                    sum+= houses[i-N];
                }
                if(i >= M-1) {
                    if(sum < K) {
                        cnt++;
                    }
                    sum-= houses[i-M+1];
                }
                if(N == M && i == N-1) {
                    break;
                }
            }
            System.out.println(cnt);
        }
    }
}

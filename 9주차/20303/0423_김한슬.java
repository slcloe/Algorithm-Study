package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
/*
문제풀이 :
1. dfs 를 사용하여 연결된 친구들의 group 관계를 저장한다.
2. group에 속한 아이 수 : 사탕 개수 로 이루어진 knapsack 리스트를 만든다
3. dp를 통해 K-1 의 아이의 사탕을 빼앗았을때 최대값을 구한다.
    점화식 : dp[j][i] = Math.max(dp[j][i - 1], object[1] + dp[(int)(j - object[0])][i - 1]);
    최대 무게가 w 인 가방에서 i 번째 물건까지 판단했을 때의 최대값을 저장.

 */
public class week9_20303_0422_김한슬 {
    static int N, M, K;
    static int[] arr;
    static ArrayList<Integer> g[];
    static boolean v[];
    static ArrayList<long []> knapsack;
    static int cnt = 0;

    static long calKnapsack(int index, long sum) {
        v[index] = true;
        cnt++;

        for(int idx : g[index]) {
            if (!v[idx])
                sum += calKnapsack(idx, 0);
        }

        sum += arr[index];
        return sum;
    }

    static long calDp(){

        long[][] dp = new long[K][knapsack.size() + 1];

        for (int i = 1; i < dp[0].length; i++) {
            long[] object = knapsack.get(i - 1);

            for (int j = 1; j < dp.length; j++) {
                if (j >= object[0]){
                    dp[j][i] = Math.max(dp[j][i - 1], object[1] + dp[(int)(j - object[0])][i - 1]);
                }
                else dp[j][i] = dp[j][i - 1];
            }
        }

        return dp[K - 1][knapsack.size()];
    }

    static long calDP(){
        long dp[][] = new long[2][K];
        for (long[] cur : knapsack) {
            for (int i = 0; i < K; i++) {
                if(i >= cur[0]) {
                    dp[1][i] = Math.max(dp[0][i], dp[0][(int) (i-cur[0])]+cur[1]);
                }else {
                    dp[1][i] = dp[0][i];
                }
            }
            for(int i=0; i<K; i++) {
                dp[0][i]=dp[1][i];
            }
        }
        return dp[1][K - 1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        g = new ArrayList[N + 1];
        v = new boolean[N + 1];
        knapsack = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            g[a].add(b);
            g[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            if (v[i]) continue;

            cnt = 0;
            long sum = calKnapsack(i, 0);
            knapsack.add(new long[] {cnt, sum});
        }

        Collections.sort(knapsack, (o1, o2) -> {
            if (o1[0] == o2[0]) return Long.compare(o1[1], o2[1]);
            else return Long.compare(o1[0], o2[0]);
        });

//        System.out.println(calDp());
        System.out.println(calDP());
    }
}

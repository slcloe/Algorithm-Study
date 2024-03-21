package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 풀이 방법
 * 1. N,M 의 최대값이 500,000 이기 때문에 N,M을 기준으로 한 완탐은 시간초과를 일으킨다.
 * 2. K 의 최대값은 100 이기 때문에 K 를 기준으로 완탐을 한다.
 * 3. 별이 트렘펄린 안에 최대로 들어오기 위해서는 빗변에 별이 위치해야 한다.
 * 4. 트렘펄린 위치 결정조건을 한개로 하게 되면 빗변이 아닌 모서리에 별이 위치하게 된다.
 * 5. 따라서, 별 두개의 좌표를 결정조건으로 두어서 계산을 수행한다.
 */
public class week4_14658_0319_김한슬 {
    static int N, M, L, K;
    static ArrayList<int[]> list = new ArrayList<>();

    static int trampoline(int i, int j) {
        int cnt = 0;
        for(int[] s: list){
            if(i <= s[0] && s[0] <= i+L && j <= s[1] && s[1] <= j+L) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new int[]{a, b});
        }
        int result = Integer.MIN_VALUE;
        for(int[] s1: list){
            for(int[] s2: list){
                result = Math.max(result, trampoline(s1[0], s2[1]));
            }
        }
        System.out.println(K - result);
    }

}

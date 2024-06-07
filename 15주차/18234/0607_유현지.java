/*
 * | 활용 알고리즘 | Greedy
 *
 * | 접근 방법 |
 *  1. 당근을 훔치지 않아도 되기 때문에, T-N일 동안 훔치지 않고 영양제를 주기만 한다
 *  2. 그 다음부터 p를 기준으로 오름차순 정렬한다
 */

package a2406.study.week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_g3_18234_당근_훔쳐_먹기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
        }
    }
}

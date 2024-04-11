/*
 * | 활용 알고리즘 | DFS, DP
 * | 활용 자료구조 | array of ArrayList
 *
 * | 접근 방법 |
 *  1. 부모 노드가 얼리어답터인 경우/아닌 경우를 나누어 생각한다
 *      1-1. 부모 노드가 얼리어답터가 아니라면 자식 노드는 모두 반드시 얼리어답터여야 한다
 *      1-2. 부모 노드가 얼리어답터라면 자식 노드는 얼리어답터일 때/아닐 때 경우가 나뉜다
 *  2. 아무 노드(편의상 1번 노드)에서부터 DP를 시작한다
 *     결국 모든 노드를 탐색하므로 어떤 노드에서 시작해도 상관 없다
 *  3. DP 배열은 [노드 번호][0/1]의 구조이며,
 *     0/1은 각각 해당 노드가 얼리어답터가 아닐 때/맞을 때의 최소 얼리어답터 수이다
 *  4. 시작 노드에서부터 해당 노드의 자식들을 탐색하며 DP 배열을 갱신한다
 *  5. 어떤 노드든 [노드 번호][0], [노드 번호][1] 중 더 작은 값이 답이 된다
 */

package a2404.week7;

import java.io.*;
import java.util.*;

public class bj_g3_2533_사회망_서비스_SNS {
    static int N;
    static int[][] dp;
    static boolean[] v;
    static List<Integer>[] tree;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2];
        v = new boolean[N+1];
        tree = new ArrayList[N+1];
        for(int n=1; n<=N; n++){
            tree[n] = new ArrayList<>();
        }
        for(int n=0; n<N-1; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        makeDP(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void makeDP(int idx){
        v[idx] = true;
        dp[idx][0] = 0;
        dp[idx][1] = 1;

        for(int child: tree[idx]){
            if(v[child]){ continue; }
            makeDP(child);
            dp[idx][0] += dp[child][1];
            dp[idx][1] += Math.min(dp[child][0], dp[child][1]);
        }
    }
}
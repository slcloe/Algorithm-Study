/*
 * | 활용 알고리즘 | Union-Find, Dynamic Programming(Knapsack)
 *
 * | 활용 자료구조 | HashMap
 *
 * | 접근 방법 |
 *  1. 친구 관계에 따라 Union-Find로 군집화 한다
 *  2. 군집 번호: {인원, 사탕 총 합}의 형태로 Map을 만든다
 *  3. 배낭 알고리즘으로 K-1명에게 사탕을 뺏을 때 사탕의 최대값을 구한다
 *
 *  | 주의 사항 |
 *  1. Union-Find 수행 시 두 수의 조상 중 더 작은/큰 수를 기준으로 군집화한다
 */

package a2404.study.week9;

import java.io.*;
import java.util.*;

public class bj_g3_20303_할로윈의_양아치 {
    static int N, M, K;
    static int[] candies, parent;
    static HashMap<Integer, int[]> groups = new HashMap<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        candies = new int[N+1];
        parent = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int n=1; n<=N; n++){
            candies[n] = Integer.parseInt(st.nextToken());
            parent[n] = n;
        }
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        for(int n=1; n<=N; n++){
            int key = parent[parent[n]];
            if(groups.containsKey(key)){
                groups.get(key)[0]++;
                groups.get(key)[1] += candies[n];
            }
            else{
                groups.put(key, new int[] {1, candies[n]});
            }
        }

        int[] dp = new int[K];

        for(int[] group: groups.values()){
            int numChildren = group[0];
            int numCandies = group[1];

            for(int k=K-1; k>numChildren; k--){
                dp[k] = Math.max(dp[k], dp[k-numChildren] + numCandies);
            }
        }
        System.out.println(dp[K-1]);
    }

    static int find(int a){
        if(a == parent[a]){ return a; }
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b){ return; }
        else if(a < b){ parent[b] = a; }
        else{ parent[a] = b; }
    }
}
/* 반례
4 4 4
10 10 10 10
1 4
3 2
4 3
2 1
--------------
0
*/
package club9;

import java.io.*;
import java.util.*;

// 1. 이어진 친구들 사탕 하나로 묶기 (rel -> BFS)
// 2. knapsack

public class BOJ_20303 {
    static int N, M, K;
    static int[] candies;
    static boolean[] visit;
    static ArrayList<Integer>[] rel;
    static ArrayList<Integer> candyList = new ArrayList<>();
    static ArrayList<Integer> children = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        rel = new ArrayList[N];
        visit = new boolean[N];

        for (int i = 0; i < N; i++)
            rel[i] = new ArrayList<>();

        candies = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            candies[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            rel[a].add(b);
            rel[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            if (visit[i])
                continue;
            BFS(i);
        }
        int[][] dp = new int[2][K];
        for (int i = 0; i < candyList.size(); i++) {
            int child = children.get(i);
            int candy = candyList.get(i);
            for (int j = 0; j < K; j++) {
                if (j >= child && dp[0][j - child] + candy > dp[0][j])
                    dp[1][j] = dp[0][j - child] + candy;
                else
                    dp[1][j] = dp[0][j];
            }
            dp[0] = dp[1].clone();
        }
        System.out.println(dp[1][K - 1]);
    }

    static void BFS(int start) {
        visit[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int child = 1;
        int candy = candies[start];
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i : rel[now]) {
                if (visit[i])
                    continue;
                visit[i] = true;
                q.offer(i);
                child++;
                candy += candies[i];
            }
        }
        candyList.add(candy);
        children.add(child);
    }
}

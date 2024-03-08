package week2;

import java.io.*;
import java.util.*;

public class BOJ1135 {
    static int N;
    static int[] dp;
    static ArrayList<Integer>[] tree;

    private static int dfs(int cur) {
        int day = 0;
        int max = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int next : tree[cur]) {
            dp[next] = dfs(next);
            q.add(new int[]{next, dp[next]});
        }
        while (!q.isEmpty()) {
            int[] node = q.poll();
            max = Math.max(max, node[1] + (++day));
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N];
        tree = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int current = Integer.parseInt(st.nextToken());
            if(i==0) continue;
            tree[current].add(i);
        }
        System.out.println(dfs(0));
    }

}
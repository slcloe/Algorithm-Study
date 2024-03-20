package week3;

import java.io.*;
import java.util.*;

public class BOJ2042 {
    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        tree = new long[N * 5];
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }
        initTree(0, N - 1, 1);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                long diff = c - arr[b];
                arr[b] = c;
                updateTree(0, N - 1, 1, b, diff);
            }
            if (a == 2) {
                int c = Integer.parseInt(st.nextToken()) - 1;
                long res = findTree(0, N - 1, 1, b, c);
                sb.append(res).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    private static long findTree(int start, int end, int now, int left, int right) {
        if (left > end || right < start)
            return 0;
        if (left <= start && right >= end)
            return tree[now];

        int mid = (start + end) / 2;
        long resL = findTree(start, mid, now * 2, left, right);
        long resR = findTree(mid + 1, end, now * 2 + 1, left, right);
        return resL + resR;
    }

    private static void updateTree(int start, int end, int now, int idx, long diff) {
        if (idx < start || idx > end)
            return;
        tree[now] += diff;
        if (start == end)
            return;
        int mid = (start + end) / 2;
        updateTree(start, mid, now * 2, idx, diff);
        updateTree(mid + 1, end, now * 2 + 1, idx, diff);
    }

    private static long initTree(int start, int end, int now) {
        if (start == end)
            return tree[now] = arr[start];
        int mid = (start + end) / 2;
        long resL = initTree(start, mid, now * 2);
        long resR = initTree(mid + 1, end, now * 2 + 1);
        return tree[now] = resL + resR;
    }

}
package week7;

import java.io.*;
import java.util.*;

class Solution {
    static int n, m;
    static boolean[][] v;
    static int[] di = new int[]{-1, 0, 1, 0};
    static int[] dj = new int[]{0, 1, 0, -1};
    static int[] oil;

    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        oil = new int[m];
        v = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !v[i][j]) {
                    search(i, j, land);
                }
            }
        }
        for (int o : oil) {
            answer = Math.max(answer, o);
        }
        return answer;
    }

    boolean isOOB(int i, int j) {
        return (i < 0 || i >= n || j < 0 || j >= m);
    }

    void search(int i, int j, int[][] land) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        v[i][j] = true;
        int cnt = 1;
        Set<Integer> set = new HashSet<>();

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            set.add(cj);

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if (isOOB(ni, nj)) continue;

                if (land[ni][nj] == 1 && !v[ni][nj]) {
                    q.add(new int[]{ni, nj});
                    v[ni][nj] = true;
                    cnt++;
                }
            }
        }
        for (int index : set) {
            oil[index] += cnt;
        }
    }
}


public class PG_250136 {
    public static void main(String[] args)throws IOException {
        Solution sol = new Solution();
        int[][] land1 = new int[][] {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        int[][] land2 = new int[][] {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}};

        System.out.println(sol.solution(land1));
        System.out.println(sol.solution(land2));
    }
}

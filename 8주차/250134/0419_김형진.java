import java.io.*;
import java.util.*;

class Solution {
    int n,m;
    boolean[][] v;
    int[] di = new int[] {-1,0,1, 0};
    int[] dj = new int[] { 0,1,0,-1};
    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        for (int j = 0; j < m; j++) {
            System.out.println(j);
            answer = Math.max(answer,dig(j,land));
        }
        return answer;
    }
    boolean isOOB (int i, int j){
        return (i < 0 || i >= n || j < 0 || j >= m);
    }
    int dig(int j, int[][] land){
        int res = 0;
        Queue<int[]> q = new ArrayDeque<>();
        v= new boolean[n][m];
        for (int i = 0; i < n; i++) {
            if (land[i][j] == 1) {
                q.offer(new int[]{i, j});
            }
        }

        System.out.println(q);
        while(!q.isEmpty()){
            int[] current =q.poll();
            int ci = current[0];
            int cj = current[1];

            v[ci][cj] = true;
            res++;

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(isOOB(ni,nj)) continue;
                if(v[ni][nj] || land[ni][nj]==0) continue;
                q.offer(new int[]{ni,nj});
            }
        }
        return res;
    }
}

public class PG_250136 {
    public static void main(String[] args)throws IOException {
        Solution sol = new Solution();
        int[][] land1 = new int[][] {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        int[][] land2 = new int[][] {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}};

        System.out.println(sol.solution(land1));
//        System.out.println(sol.solution(land2));
    }
}

import java.util.*;
class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int area = 0;
        int maxArea = 0;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1 ,0};
        boolean[][] v = new boolean[m][n];
        for(int i=0; i<picture.length; i++) {
            for(int j=0; j<picture[0].length; j++) {
                if(!v[i][j] && picture[i][j] != 0) {
                    
                    int count = 0;
                    Deque<int[]> dq = new ArrayDeque<>();
                    v[i][j] = true;
                    dq.offer(new int[] {i, j});
                    while(!dq.isEmpty()) {
                        count++;
                        int[] cur = dq.poll();
                        for(int k=0; k<4; k++) {
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];
                            if(nx >= 0 && ny >= 0 && nx < m && ny < n && !v[nx][ny] && picture[nx][ny] == picture[i][j]) {
                                dq.offer(new int[] {nx, ny});
                                v[nx][ny] = true;
                            }
                        }
                    }
                    area++;
                    maxArea = Math.max(maxArea, count);
                    
                }
            }
        }
        return new int[] {area, maxArea};
    }
}
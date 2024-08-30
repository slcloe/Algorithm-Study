import java.util.*;

/*
  그냥 bfs.
*/

class Solution {
    
    static int M;
    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        answer[0] = 0;
        answer[1] = 0;
        
        M = m;
        N = n;
        board = picture;
        visited = new boolean[m][n];
  
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    answer[0]++;
                    answer[1] = Math.max(answer[1], bfs(i, j, picture[i][j]));
                }
            }
        }
        
        return answer;
    }
    
    static int bfs(int y, int x, int color) {
        int count = 0;
        visited[y][x] = true;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {y, x});
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            count++;
            
            for (int i = 0; i < 4; i++) {
                int ny = current[0] + dy[i];
                int nx = current[1] + dx[i];
                
                if (ny >= 0 && ny < M && nx >= 0 && nx < N
                    && board[ny][nx] == color 
                    && !visited[ny][nx]) {
                    queue.offer(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }
        
        return count;
    }
}

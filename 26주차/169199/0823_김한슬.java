/*
[문제 해결 방법]

bfs 탐색을 사용하여 가장 빠른 경로를 찾는다.

*/

import java.util.*;

class Solution {
    int x, y;
    int gx, gy;
    
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    boolean[][] v;
    
    public int solution(String[] board) {
        int N, M;
        N = board.length;
        M = board[0].length();
        v = new boolean[N][M];
        
        for(int i = 0; i < board.length;i++){
            for(int j = 0;j < M;j++){
                if (board[i].charAt(j) == 'R') {
                    x = i;
                    y = j;
                }
                if (board[i].charAt(j) == 'G') {
                    gx = i;
                    gy = j;
                }
            }
        }
        
        int answer = -1;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        v[x][y] = true;
        queue.offer(new int[] {x, y, 0});
        
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            if (cur[0] == gx && cur[1] == gy) {
                answer = cur[2];
                break;
            }
            
            for(int i = 0 ;i < 4; i++) {
                int tx = dx[i] + cur[0];
                int ty = dy[i] + cur[1];
                
                if (tx < 0 || tx >= N || ty < 0 || ty >= M) continue;
                if (board[tx].charAt(ty) == 'D') continue;
                if (v[tx][ty]) continue;
                
                
                v[tx][ty] = true;
                queue.offer(new int[] {tx, ty, cur[2] + 1});
            }
            
        }

        return answer;
    }
}

import java.util.*;
class Solution {
    
    /*
    일반 다익스트라 반례
    [[0, 0, 0, 0, 0, 0, 0, 0, 0, 1], [0, 1, 1, 1, 1, 1, 1, 1, 0, 1], [0, 1, 1, 1, 1, 1, 1, 1, 0, 1], [0, 1, 1, 1, 1, 1, 1, 0, 0, 1], [0, 1, 1, 1, 1, 1, 1, 0, 1, 1], [0, 1, 1, 1, 1, 1, 1, 0, 1, 1], [0, 1, 1, 1, 1, 0, 0, 0, 0, 0], [0, 1, 1, 1, 1, 0, 1, 1, 1, 0], [0, 0, 0, 0, 0, 0, 1, 1, 1, 0], [1, 1, 1, 1, 1, 1, 1, 1, 1, 0]]
    
    정답 : 4200
    */
    
    /*
    접근 방식 : 처음엔 일반 2차원 다익스트라로 구현했지만, 64점으로 실패.
               이후에 2차원 메모제이션 방식을 사용하려고 하니 dfs를 사용해야해서
               힌트를 보고 3차원 방향 배열로 변경해주니 통과
    주안점
    1. 위 반례처럼 일반 다익스트라로 작성하면 중간값은 더 크지만 마지막 값은 더 작은 경우가 계산되지 않음
    2. 중간 공간들은 4방향 모두 들어갈 수 있지만, [N-1, N-1] 좌표는 왼쪽과 위쪽에서밖에 접근할 수 없기 때문에 두 값만 비교
    */
    
    public int solution(int[][] board) {
        return bfs(board);
    }
    
    static int bfs(int[][] board) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {0, 0, 0});
        dq.offer(new int[] {0, 0, 1});
        
        // 4방향에서 최소값을 구해야 하기 때문에 3차원 배열
        int[][][] dist = new int[board.length][board.length][4];
        
        // dist 배열 초기화
        for(int i=0; i<dist.length; i++) {
            for(int j=0; j<dist.length; j++) {
                Arrays.fill(dist[i][j], 100_000_000);
            }
        }
        Arrays.fill(dist[0][0], 0);
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(!checkRange(nx, ny, board.length)) continue;
                if(board[nx][ny] == 1) continue;
                int money = cur[2] % 2 == 0 && i % 2 == 0 ? 100 
                      : (cur[2] % 2 == 1 && i % 2 == 1 ? 100 
                        : 600);
                if(dist[nx][ny][i] >= dist[cur[0]][cur[1]][cur[2]] + money) {
                    dist[nx][ny][i] = dist[cur[0]][cur[1]][cur[2]] + money;
                    dq.offer(new int[] {nx, ny, i});
                }
            }
        }
        return Math.min(dist[board.length-1][board.length-1][0], dist[board.length-1][board.length-1][1]);
    }
    
    static boolean checkRange(int x, int y, int range) {
        return x >= 0 && y >= 0 && x < range && y < range;
    }
}
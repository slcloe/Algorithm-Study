import java.util.*;
class Solution {
    /*

    접근 방식 : 미끄러지는 방법 구현 후 방향에 따라 조건을 체크해주며 확인
    주안점
    1. D 옆에 [G, .]의 두 가지 경우가 있다는 것을 명심
    
    */
    static char[][] map;
    static boolean[][] v;
    static int answer;
    public int solution(String[] board) {
        answer = Integer.MAX_VALUE;
        map = new char[board.length][board[0].length()];
        int[] start = new int[2];
        int[] last = new int[2];
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length(); j++) {
                if(board[i].charAt(j) == 'G') {
                    last[0] = i;
                    last[1] = j;
                } else if(board[i].charAt(j) == 'R') {
                    start[0] = i;
                    start[1] = j;
                }
                map[i][j] = board[i].charAt(j);
            }
        }
        v = new boolean[map.length][map[0].length];
        bfs(start, last);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    static void bfs(int[] start, int[] last) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {start[0], start[1], 0});
        v[start[0]][start[1]] = true;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int count = 0;
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            for(int i=0; i<4; i++) {
                int[] next = move(cur, dx[i], dy[i], i);
                if(next[0] == -1 && next[1] == -1) continue;
                else {
                    if(map[next[0]][next[1]] == 'G') {
                        answer = Math.min(answer, cur[2]+1);
                        continue;
                    }
                    dq.offer(new int[] {next[0], next[1], cur[2]+1});
                }
            }
        }
        
    }
    
    static int[] move(int[] cur, int x, int y, int dir) {
        int nextX = cur[0] + x;
        int nextY = cur[1] + y;
        while(nextX >= 0 && nextY >= 0 && nextX < map.length && nextY < map[0].length) {
            if(map[nextX][nextY] == 'D') {
                if(!v[nextX - x][nextY - y] && (map[nextX - x][nextY - y] == '.' || map[nextX - x][nextY - y] == 'G')) {
                    v[nextX - x][nextY - y] = true;
                    return new int[] {nextX - x, nextY - y};
                } else {
                    return new int[] {-1, -1};
                }
            } else if(dir == 0 && nextY == map[0].length - 1) {
                if(!v[nextX][nextY] && (map[nextX][nextY] == '.' || map[nextX][nextY] == 'G')) {
                    v[nextX][nextY] = true;
                    return new int[] {nextX, nextY};
                } else {
                    return new int[] {-1, -1};
                }
            } else if(dir == 1 && nextX == map.length - 1) {
                if(!v[nextX][nextY] && (map[nextX][nextY] == '.' || map[nextX][nextY] == 'G')) {
                    v[nextX][nextY] = true;
                    return new int[] {nextX, nextY};
                } else {
                    return new int[] {-1, -1};
                }
            } else if(dir == 2 && nextY == 0) {
                if(!v[nextX][nextY] && (map[nextX][nextY] == '.' || map[nextX][nextY] == 'G')) {
                    v[nextX][nextY] = true;
                    return new int[] {nextX, nextY};
                } else {
                    return new int[] {-1, -1};
                }
            } else if(dir == 3 && nextX == 0) {
                if(!v[nextX][nextY] && (map[nextX][nextY] == '.' || map[nextX][nextY] == 'G')) {
                    v[nextX][nextY] = true;
                    return new int[] {nextX, nextY};
                } else {
                    return new int[] {-1, -1};
                }
            }
            else {
                nextX += x;
                nextY += y;
            }
        }
        return new int[] {-1, -1};
    }
}
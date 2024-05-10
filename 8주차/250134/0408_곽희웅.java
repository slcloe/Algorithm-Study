import java.util.*;
class Solution {
    
    /*
    접근 방식 : 두 말을 같이 움직이며 방문 처리를 해줘야 하므로 dfs가 적합하다고 고려.
    
    주안점
    1. red, blue 따로 방문처리를 해야하므로 3차원 방문 배열 사용.
    2. 범위, 벽, 다른 말이 있는 칸, 방문한 칸 등등 밟으면 안되는 조건을 꼼꼼히 봐야 함.
    3. 반드시 다른 칸으로 움직여야 한다는 점을 고려하여 반복문 설계.
    */
    
    static int N, M, answer;
    static int[] redA, blueA;
    static int[] dx = {0, 1, 0 ,-1};
    static int[] dy = {1, 0, -1 ,0};
    static int[][] map;
    static boolean flag;
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        // red 시작
        int[] red = new int[2];
        // blue 시작
        int[] blue = new int[2];
        // red 도착 위치
        redA = new int[2];
        // blue 도착 위치
        blueA = new int[2];
        // 메서드에서 사용할 map
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            map[i] = maze[i];
            for(int j=0; j<M; j++) {
                if(maze[i][j] == 1) {
                    red[0] = i;
                    red[1] = j;
                } else if(maze[i][j] == 2) {
                    blue[0] = i;
                    blue[1] = j;
                } else if(maze[i][j] == 3) {
                    redA[0] = i;
                    redA[1] = j;
                } else if(maze[i][j] == 4) {
                    blueA[0] = i;
                    blueA[1] = j;
                }
            }
        }
        
        answer = Integer.MAX_VALUE;
        // 0 : red, 1 : blue 방문 처리 배열 선언
        boolean[][][] v = new boolean[2][N][M];
        v[0][red[0]][red[1]] = true;
        v[1][blue[0]][blue[1]] = true;
        dfs(red, blue, v, 0);
        return flag ? answer : 0;
    }
    
    static boolean same(int rx, int ry, int bx, int by) {
        return rx == bx && ry == by;
    }
    
    static boolean cross(int rx, int ry, int bx, int by, int[] red, int[] blue) {
        return (rx == blue[0] && ry == blue[1]) && (bx == red[0] && by == red[1]);
    }
    
    static boolean check(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
    
    static boolean redIsArrived(int[] red) {
        return red[0] == redA[0] && red[1] == redA[1];
    }
    
    static boolean blueIsArrived(int[] blue) {
        return blue[0] == blueA[0] && blue[1] == blueA[1];
    }
    
    static void dfs(int[] red, int[] blue, boolean[][][] v, int cnt) {
        if(redIsArrived(red) && blueIsArrived(blue)) {
            flag = true;
            answer = Math.min(answer, cnt);
            return;
        }
        cnt++;
        for(int i=0; i<4; i++) {
            // red 이동
            int rx = red[0] + dx[i];
            int ry = red[1] + dy[i];
            // 만약 red가 도착했다면 그대로
            if(redIsArrived(red)) {
                rx = red[0];
                ry = red[1];
            }
            // 범위 바깥인지, 벽인지, 도착하지 않았을 때 방문한 칸인지 확인
            if(check(rx, ry) || map[rx][ry] == 5 || (!redIsArrived(red) && v[0][rx][ry])) continue;
            for(int j=0; j<4; j++) {
                // blue 이동
                int bx = blue[0] + dx[j];
                int by = blue[1] + dy[j];
                // 만약 blue가 도착했다면 그대로
                if(blueIsArrived(blue)) {
                    bx = blue[0];
                    by = blue[1];
                }
                // 범위 바깥인지, 벽인지, 도착하지 않았을 때 방문한 칸인지 확인
                if(check(bx, by) || map[bx][by] == 5 || (!blueIsArrived(blue) && v[1][bx][by])) continue;
                // 서로가 같은 좌표인지 확인
                if(same(rx, ry, bx, by)) continue;
                // 서로의 자리를 뒤바꾼지 확인
                if(cross(rx, ry, bx, by, red, blue)) continue;
                int[] nextRed = {rx, ry};
                int[] nextBlue = {bx, by};
                v[0][rx][ry] = true;
                v[1][bx][by] = true;
                dfs(nextRed, nextBlue, v, cnt);
                v[0][rx][ry] = false;
                v[1][bx][by] = false;
            }
        }
    }
}
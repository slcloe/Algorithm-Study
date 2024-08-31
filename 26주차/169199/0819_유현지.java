/*
 * | 활용 알고리즘 | BFS
 *
 * | 접근 방법 |
 *  1. 로봇의 위치에서 목표 지점까지 BFS로 탐색한다
 *  2. 로봇을 이동시킬 때는 장애물이나 벽을 만나기 전까지 쭉 이동시킨다
 *  3. 방문 배열을 만들 때는 네 방향에서 해당 칸에 들어온 여부를 판단하기 위해 3차원 배열을 사용한다
 */

package a2408.study.week26;

import java.util.*;

public class pr_2_169199_리코쳇_로봇 {
    static int R, C;
    static int[] robot, goal, dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static char[][] map;

    public int solution(String[] board) {
        int answer = 0;

        R = board.length;
        C = board[0].length();
        map = new char[R][C];
        for(int r=0; r<R; r++){
            map[r] = board[r].toCharArray();
            for(int c=0; c<C; c++){
                if(map[r][c] == 'R'){
                    robot = new int[] {r, c};
                }
                else if(map[r][c] == 'G'){
                    goal = new int[] {r, c};
                }
            }
        }
        answer = BFS();

        return answer;
    }

    static boolean isOut(int r, int c){
        return r<0 || R<=r || c<0 || C<=c;
    }

    static int BFS(){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][][] visit = new boolean[R][C][4];
        int rr = robot[0];
        int rc = robot[1];
        queue.offerLast(new int[] {0, rr, rc});
        for(int d=0; d<4; d++){
            visit[rr][rc][d] = true;
        }

        while(!queue.isEmpty()){
            int[] now = queue.pollFirst();
            int s = now[0];
            int r = now[1];
            int c = now[2];
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(isOut(nr, nc) || map[nr][nc] == 'D'){ continue; }
                while(!isOut(nr+dr[d], nc+dc[d]) && map[nr+dr[d]][nc+dc[d]] != 'D'){
                    nr += dr[d];
                    nc += dc[d];
                }
                if(nr==goal[0] && nc==goal[1]){
                    return s+1;
                }
                if(visit[nr][nc][d]){ continue; }
                queue.offerLast(new int[] {s+1, nr, nc});
                visit[nr][nc][d] = true;
            }
        }
        return -1;
    }
}

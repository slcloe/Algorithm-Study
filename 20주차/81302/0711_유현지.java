/*
 * | 활용 알고리즘 | DFS
 *
 * | 접근 방법 |
 *  1. 맵을 탐색하며 지원자를 만날 때마다 DFS를 수행한다
 *     행, 열, 거리, 지금까지 파티션을 만났는지 여부를 들고 다닌다
 *  2. 다른 지원자를 만났을 때 가능 여부를 체크한다
 *     2-1. 거리가 2 이하이면서
 *     2-2. 사이에 파티션도 없었다면 불가능한 경우이다
 */

package a2407.study.week20;

import java.util.*;

class pr_2_81302_거리두기_확인하기 {
    static boolean isFail;
    static boolean[][] visit;
    static char[][] room;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for(int i=0; i<5; i++){
            isFail = false;
            room = new char[5][5];
            visit = new boolean[5][5];
            for(int r=0; r<5; r++){
                for(int c=0; c<5; c++){
                    room[r][c] = places[i][r].charAt(c);
                }
            }
            for(int r=0; r<5; r++){
                for(int c=0; c<5; c++){
                    if(room[r][c] == 'P'){
                        visit = new boolean[5][5];
                        DFS(r, c, 0, false);
                    }
                    if(isFail){ break; }
                }
                if(isFail){ break; }
            }
            answer[i] = isFail? 0: 1;
        }

        return answer;
    }

    static boolean isOut(int r, int c){
        return r<0 || 4<r || c<0 || 4<c;
    }

    static void DFS(int r, int c, int dist, boolean isBlocked){
        if(isFail){ return; }
        if(room[r][c] == 'P' && dist != 0){
            if(dist < 3 && !isBlocked){
                isFail = true;
            }
            return;
        }
        visit[r][c] = true;
        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(isOut(nr, nc) || visit[nr][nc]){ continue; }
            if(room[nr][nc] == 'X'){ DFS(nr, nc, dist+1, true); }
            else{ DFS(nr, nc, dist+1, isBlocked); }

        }
        visit[r][c] = false;
    }
}
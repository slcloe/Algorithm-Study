import java.io.*;
import java.util.*;
public class 0223_곽희웅 {

    /*
    접근 방식 : 8x8의 크기를 가진 체스판에서 벽이 점점 내려오는 구조에서 목표 위치까지 도달할 수 있나 검증하는 문제이기 때문에
    BFS 방식으로 가능한 경우의 수를 방문 처리 해준다면, 시간초과나지 않을 것이라고 고려
    자료 구조 : Deque을 사용해서 데이터를 수정, 삭제하기 용이하게 구성

    주안점
    1. Deque의 size를 재서 다음 1초동안 움직이는 경우의 수를 침범하지 않도록 구성
    2. 제자리의 경우의 수를 팔방탐색에 더해줌
    3. 맨 아래에 있는 벽은 사라지며, 벽이 내려오는 것이기 때문에 맨 위에 있는 체스판은 모두 빈 칸이 됨
    */

    // 동 서 남 북 북동 북서 남동 남서 제자리
    static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1, 0};
    static int[] dy = {1, -1, 0, 0, 1, -1, -1, 1, 0};
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[8][8];
        for(int i=0; i<8; i++) {
            String S = br.readLine();
            for(int j=0; j<8; j++) {
                map[i][j] = S.charAt(j);
            }
        }

        if(play()) System.out.println(1);
        else System.out.println(0);
    }

    static boolean play() {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {7, 0});
        boolean flag = false;
        end: while(true) {
            // 벽 이동 후 벽에 부딪힌 세계선 확인
            int size = dq.size();
            for(int i=0; i<size; i++) {
                int[] cur = dq.poll();
                if(map[cur[0]][cur[1]] != '#') dq.offer(cur);
            }
            // 확인 후 가능한 지점으로 이동
            if(dq.isEmpty()) break;
            size = dq.size();
            boolean[][] v = new boolean[8][8];
            for(int i=0; i<size; i++) {
                int[] cur = dq.poll();
                for(int j=0; j<9; j++) {
                    int nx = cur[0] + dx[j];
                    int ny = cur[1] + dy[j];
                    if(nx >= 0 && ny >= 0 && nx < 8 && ny < 8 && !v[nx][ny] && map[nx][ny] != '#') {
                        if(nx == 0 && ny == 7) {
                            flag = true;
                            break end;
                        }
                        v[nx][ny] = true;
                        dq.offer(new int[] {nx, ny});
                    }
                }
            }
            // 벽이 내려옴
            // 벽 이동 시 맨 아래에 있는 벽은 범위 밖으로 나가며 사라짐
            for(int i=6; i>=0; i--) {
                for(int j=0; j<8; j++) {
                    map[i+1][j] = map[i][j];
                }
            }
            for(int i=0; i<8; i++) map[0][i] = '.';
        }
        return flag;
    }
}

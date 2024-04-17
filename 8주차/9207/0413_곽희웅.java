import java.io.*;
public class Main_bj_9207 {
    /*
    접근 방식 : 핀이 왼쪽으로 움직일 수 있으면 왼쪽 핀도 오른쪽으로 움직일 수 있기 때문에, dfs 방식으로 해야한다고 생각.

    주안점
    1. 5x9 크기 배열이라는 것을 주의..
    2. 결국 [원래 핀 개수 - 이동 회수]가 최종 핀 개수인 것을 알고 하면 편함.
     */

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1 ,0};
    static char[][] map;
    static int minPin, minMove, pins;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            map = new char[5][9];
            pins = 0;
            for(int i=0; i<5; i++) {
                map[i] = br.readLine().toCharArray();
                for(int j=0; j<9; j++) if(map[i][j] == 'o') pins++;
            }
            minPin = pins;
            minMove = pins - 1;
            play(0);
            if(t < T-1) br.readLine();
            sb.append(minPin).append(" ").append(minMove).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void check(int cnt) {
        if(minPin > pins - cnt) {
            minPin = pins - cnt;
            minMove = cnt;
        } else if(minPin == pins - cnt) minMove = Math.min(minMove, cnt);
    }

    static void play(int cnt) {
        boolean flag = false;
        for(int i=0; i<5; i++) {
            for(int j=0; j<9; j++) {
                if(map[i][j] == 'o') {
                    // 동서남북으로 움직일 수 있는지 확인
                    for(int k=0; k<4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(nx >= 0 && ny >= 0 && nx < 5 && ny < 9 && map[nx][ny] == 'o') {
                            int px = nx + dx[k];
                            int py = ny + dy[k];
                            if(px >= 0 && py >= 0 && px < 5 && py < 9 && map[px][py] == '.') {
                                flag = true;
                                map[i][j] = '.';
                                map[nx][ny] = '.';
                                map[px][py] = 'o';
                                play(cnt+1);
                                map[i][j] = 'o';
                                map[nx][ny] = 'o';
                                map[px][py] = '.';
                            }
                        }
                    }
                }
            }
        }
        if(!flag) check(cnt);
    }
}

import java.io.*;
import java.util.*;


/*
문제풀이

1. 백트레킹을 위한 dfs 구현
2. 인접한 두 핀이 있는 부분을 찾는다. (1핀, 2핀)이라고 한다면
3. 1핀은 빈칸, 2핀은 빈칸, 2핀다음 자리는 핀이 있는 상태로 dfs수행함.
4. dfs 탐색이 끝났을 경우 다시 원래대로 돌려놓는다.

 */
public class Main {

    static int N;
    static char[][] map;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int pins, minpins, movepins;

    static void dfs(char[][] arr, int depth, int curpins){
        boolean check = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr[i][j] == 'o'){
                    for (int k = 0; k < 4; k++) {
                        int tx = dx[k] + i;
                        int ty = dy[k] + j;
                        if (tx < 0 || tx >= 5 || ty < 0 || ty >= 9) continue;
                        if (arr[tx][ty] != 'o') continue;
                        int ttx = dx[k] + tx;
                        int tty = dy[k] + ty;
                        if (ttx < 0 || ttx >= 5 || tty < 0 || tty >= 9) continue;
                        if (arr[ttx][tty] != '.') continue;

                        arr[i][j] = '.';
                        arr[tx][ty] = '.';
                        arr[ttx][tty] = 'o';
                        dfs(arr, depth + 1,curpins - 1);
                        arr[i][j] = 'o';
                        arr[tx][ty] = 'o';
                        arr[ttx][tty] = '.';
                        check = true;

                    }
                }
            }
        }

        if (!check){
            if (curpins < minpins){
                minpins = curpins;
                movepins = depth;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());

        for(int i = 0;i < N; i++){
            map = new char[5][9];
            pins = 0;
            minpins = movepins = Integer.MAX_VALUE;
            for (int j = 0; j < 5; j++) {
                map[j] = br.readLine().toCharArray();
                for (int k = 0; k < 9; k++) {
                    if (map[j][k] == 'o') pins++;
                }
            }

            dfs(map, 0, pins);
            sb.append(minpins).append(" ").append(movepins).append("\n");
            if (i != N - 1)
                br.readLine();
        }
        System.out.println(sb.toString());
    }

}

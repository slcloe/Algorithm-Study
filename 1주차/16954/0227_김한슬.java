package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class week1_16954_0227_김한슬 {
    static char[][] arr;

    // 제자리 & 4방 & 4대각선
    static int dx[] = {1, -1, 0, 0, 1, 1, -1, -1, 0};
    static int dy[] = {0, 0, 1, -1, -1, 1, 1, -1, 0};

    static int bfs(){

        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {7, 0, 0}); // x, y, depth

        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            if (cur[0] >= cur[2] && arr[cur[0] - cur[2]][cur[1]] == '#') continue; // 벽 이동 후 장애물 체크

            for (int i = 0; i < 9; i++) {
                int tx = cur[0] + dx[i];
                int ty = cur[1] + dy[i];

                if (tx < 0 || tx >= 8 || ty < 0 || ty >= 8) continue; // 범위 체크
                if (tx >= cur[2] && arr[tx - cur[2]][ty] == '#') continue; // 장애물 체크
                if (tx == 0 && ty == 7) return 1; // 목표 지점 체크

                queue.offer(new int[] {tx, ty, cur[2] + 1});
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new char[8][8];
        for (int i = 0; i < 8; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        System.out.println(bfs());
    }
}

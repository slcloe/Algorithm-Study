package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.StringTokenizer;

public class week1_31404_0228_김한슬 {
    static int h, w;
    static int r, c, d;
    static int[][] arrA, arrB;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
  // 방향별 visited 체크 배열
    static boolean[][][] v;
    static int[][] s;

    static int clean() {
        int cnt = 0;
        int dust = 0;
        boolean isCycle = false;

        while(true){
          // 이미 한번 방문한 지점인지 체크함.
            boolean isA = false;
          // 처음 방문한 곳이면 arrA에 따라 방향변경
            if (s[r][c] == -1) {
                d += arrA[r][c];
                isA = true;
                isCycle = false;
            }
              // 이미 방문했다면 arrB에 따라 방향변경
            else {
                d += arrB[r][c];

            }
            d %= 4;

          // s 배열에 cnt 업데이트
            s[r][c] = cnt++;
          // 만약 처음 방문한 곳이면 dust에 cnt 를 넣어둠
            if (isA) dust = cnt;
          // d 방향으로 이동
            r += dx[d];
            c += dy[d];
          // 범위체크
            if (r < 0 || r >= h || c < 0 || c >= w) break;
          // 이미 방문했던 곳이라면
            if (!isA) {
              // 방향별로 v 배열이 존재함. 이미 같은 방향으로 방문했다면 탐색 종료
                if (v[r][c][d]) return dust;
            // d 에 따른 방문 v 배열 업데이트
                v[r][c][d] = true;
            }
        }
        return dust;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        arrA = new int[h][w];
        arrB = new int[h][w];
        v = new boolean[h][w][4];
        s = new int[h][w];

        for (int i = 0; i < h; i++) {
            String str = br.readLine();
            for (int j = 0; j < w; j++) {
                arrA[i][j] = str.charAt(j) - '0';
                s[i][j] = -1;
            }
        }
        for (int i = 0; i < h; i++) {
            String str = br.readLine();
            for (int j = 0; j < w; j++) {
                arrB[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.println(clean());
    }
}



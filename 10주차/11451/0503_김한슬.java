package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
문제 해설

1. bfs 를 사용하여 구현함.
2. 유효한 방문은 두 점의 좌표에 따라 결정됨 -> 4가지 값((x, y)* 2)을 사용하여 방문 체크 해야한다.
3. 백준에서 마지막 출력값을 System.out.println() 으로 했더니 계속 틀렸다고 해서
    System.out.print() 로 바꿨더니 정답이 나왔다.ㅠㅠ

 */

public class week10_11451_0503_김한슬 {
    static int N, M;
    static char arr[][] = new char[50][50];
    static boolean v[][][][];
    static int[] dx = {0, 0, 1 ,-1};
    static int[] dy = {1, -1, 0, 0};
    static char[] way = {'E', 'W', 'S', 'N'};
    static int[][] pt = new int[2][2];
    static StringBuilder sb = new StringBuilder();
    static class Man{
        public int[][] pt;
        public String ways;

        public Man(int[][] pt, String ways) {
            this.pt = pt;
            this.ways = ways;
        }
    }

    static void getSolution(){
        init();
        bfs();
    }

    static void bfs() {

        ArrayDeque<Man> queue = new ArrayDeque<>();
        v = new boolean[N][M][N][M];
        v[pt[0][0]][pt[0][1]][pt[1][0]][pt[1][1]] = true;
        queue.offer(new Man(pt, ""));

        while(!queue.isEmpty()){
            Man cur = queue.poll();

            if (cur.pt[0][0] == cur.pt[1][0] && cur.pt[0][1] == cur.pt[1][1]) {
                sb.append(cur.ways.length()).append(' ').append(cur.ways).append('\n');
                return ;
            }

            for (int i = 0; i < 4; i++) {
                int[] pt1 = checkPos(cur.pt[0][0], cur.pt[0][1], dx[i] + cur.pt[0][0], dy[i] + cur.pt[0][1]);
                int[] pt2 = checkPos(cur.pt[1][0], cur.pt[1][1], dx[i] + cur.pt[1][0], dy[i] + cur.pt[1][1]);

                if (pt1[0] == -1 || pt2[0] == -1) continue;

                if (v[pt1[0]][pt1[1]][pt2[0]][pt2[1]]) continue;
                if (v[pt2[0]][pt2[1]][pt1[0]][pt1[1]]) continue;

                v[pt1[0]][pt1[1]][pt2[0]][pt2[1]] = true;
                queue.offer(new Man(new int[][] {pt1, pt2}, cur.ways + way[i]));
            }
        }

        sb.append("IMPOSSIBLE").append('\n');
    }

    static int[] checkPos(int x, int y, int tx, int ty) {
        if (tx < 0) tx = N - 1;
        else if (tx >= N) tx = 0;

        if (ty < 0) ty = M - 1;
        else if (ty >= M) ty = 0;

        if (arr[tx][ty] == 'X')
            return new int[] {x, y};
        else if (arr[tx][ty] == 'G')
            return new int[] {-1, -1};
        else
            return new int[] {tx, ty};
    }

    static void init() {
        int idx = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 'P'){
                    pt[idx][0] = i;
                    pt[idx][1] = j;
                    idx++;
                }
                if (idx == 2) return;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for (int j = 0; j < N; j++) {
                String str = br.readLine();
                for (int k = 0; k < M; k++) {
                    arr[j][k] = str.charAt(k);
                }
            }
            getSolution();
        }

        System.out.print(sb.toString());
    }
}

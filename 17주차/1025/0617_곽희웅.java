import java.io.*;
import java.util.StringTokenizer;

public class Main_bj_1025 {

    /*
    접근 방식 : 어디 있는 수더라도 어느 방향으로든 뻗어나갈 수 있기 때문에, 모든 경우의 수를 나눠야겠다고 생각함.
              완전탐색으로 인덱스 설정을 꼼꼼하게 해야하는 문제.

    주안점
    1. N과 M이 1일 경우가 edge case일 수 있음
    2. 증가폭이 변수가 되어야하는데, 현재 값 자체가 변수가 되는 경우가 있음. 이 경우에 반례는 통과하지만 문제 해결은 안됨
    3. 완전제곱수 판별 로직에서 (int)를 써주지않으면 자동 double로 형변환이 돼서 뭐든 다 완전제곱수가 되니 주의
     */

    static String[][] map;
    static int N, M, max;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N][M];
        for(int i=0; i<N; i++) map[i] = br.readLine().split("");

        max = -1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                // 시작점이 완전 제곱수일 경우
                check(Integer.parseInt(map[i][j]));

                // x == 0, y--
                for(int y=1; y<=j; y++) findVzHm(map[i][j], i, j, y);

                // x == 0, y++
                for(int y=1; y<=M-j; y++) findVzHp(map[i][j], i, j, y);

                // y == 0, x--
                for(int x=1; x<=i; x++) findHzVm(map[i][j], i, j, x);

                // y == 0, x++
                for(int x=1; x<=N-i; x++) findHzVp(map[i][j], i, j, x);

                // x--
                for(int x=1; x<=i; x++) {
                    // y--
                    for(int y=1; y<=j; y++) findVmHm(map[i][j], i, j, x, y);
                    // y++
                    for(int y=1; y<=M-j; y++) findVmHp(map[i][j], i, j, x, y);
                }

                // x++
                for(int x=1; x<N-i; x++) {
                    // y--
                    for(int y=1; y<=j; y++) findVpHm(map[i][j], i, j, x, y);
                    // y++
                    for(int y=1; y<=M-j; y++) findVpHp(map[i][j], i, j, x, y);
                }
            }
        }
        System.out.print(max);
    }

    static void check(int target) {
        if(Math.pow((int) Math.sqrt(target), 2) == target) max = Math.max(max, target);
    }

    // Y 증가폭이 0일 때, x--
    static void findHzVm(String s, int curX, int curY, int x) {
        int nowX = curX - x;
        while(nowX >= 0) {
            s += map[nowX][curY];
            check(Integer.parseInt(s));
            nowX -= x;
        }
    }

    // Y 증가폭이 0일 때, x++
    static void findHzVp(String s, int curX, int curY, int x) {
        int nowX = curX + x;
        while(nowX < N) {
            s += map[nowX][curY];
            check(Integer.parseInt(s));
            nowX += x;
        }
    }

    // X 증가폭이 0일 때, y--
    static void findVzHm(String s, int curX, int curY, int y) {
        int nowY = curY - y;
        while(nowY >= 0) {
            s += map[curX][nowY];
            check(Integer.parseInt(s));
            nowY -= y;
        }
    }

    // X 증가폭이 0일 때, y++
    static void findVzHp(String s, int curX, int curY, int y) {
        int nowY = curY + y;
        while(nowY < M) {
            s += map[curX][nowY];
            check(Integer.parseInt(s));
            nowY += y;
        }
    }

    // x--, y++
    static void findVmHp(String s, int curX, int curY, int x, int y) {
        int nowX = curX - x;
        int nowY = curY + y;
        while(nowX >= 0 && nowY < M) {
            s += map[nowX][nowY];
            check(Integer.parseInt(s));
            nowX -= x;
            nowY += y;
        }
    }

    // x--, y--
    static void findVmHm(String s, int curX, int curY, int x, int y) {
        int nowX = curX - x;
        int nowY = curY - y;
        while(nowX >= 0 && nowY >= 0) {
            s += map[nowX][nowY];
            check(Integer.parseInt(s));
            nowX -= x;
            nowY -= y;
        }
    }

    // x++, y--
    static void findVpHm(String s, int curX, int curY, int x, int y) {
        int nowX = curX + x;
        int nowY = curY - y;
        while(nowX < N && nowY >= 0) {
            s += map[nowX][nowY];
            check(Integer.parseInt(s));
            nowX += x;
            nowY -= y;
        }
    }

    // x++, y++
    static void findVpHp(String s, int curX, int curY, int x, int y) {
        int nowX = curX + x;
        int nowY = curY + y;
        while(nowX < N && nowY < M) {
            s += map[nowX][nowY];
            check(Integer.parseInt(s));
            nowX += x;
            nowY += y;
        }
    }
}

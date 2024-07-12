import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
[문제풀이]
단순 구현문제
토네이도의 방향은 4방향, 이동하는 칸수는 2번씩 반복되어 증가한다.

1. tornado 함수에서 이동하는 칸수를 기준으로 해 같은 행위를 2번씩 반복한다.
2. 만약 이동하려고 하는 방향이 범위 내라면 맵 안에 존재하고, 만약 아니라면 sandout에 누적한다.
3. 모래의 손실률은 각 퍼센티지마다 sand 배열에 저장 후 위치별로 계산함.

*/


public class Main {

    static int N;
    static int[][] arr;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int sandOut = 0;

    static void tornado(){
        int dir = 0;
        int x = N / 2;
        int y = N / 2;
        for (int i = 1; i < N; i++) {
            moveTornado(x, y, dir, i);
            x += dx[dir] * i;
            y += dy[dir] * i;
            dir = (dir + 1) % 4;
            moveTornado(x, y, dir, i);
            x += dx[dir] * i;
            y += dy[dir] * i;
            dir = (dir + 1) % 4;
        }
        moveTornado(x, y, dir, N - 1);
    }

    static void moveTornado(int x, int y, int dir, int repeat){
        int tx = x;
        int ty = y;
        for (int i = 0; i < repeat; i++) {
            tx += dx[dir];
            ty += dy[dir];
            calSand(tx, ty, dir);
        }
    }

    static void calSand(int x, int y, int dir){
        int way = (dir + 1) % 4;
        int[] sand = new int[5];
        int sum = 0;
        int tx, ty;
        sand[0] = (int) (arr[x][y] * 0.01);
        sand[1] = (int) (arr[x][y] * 0.02);
        sand[2] = (int) (arr[x][y] * 0.05);
        sand[3] = (int) (arr[x][y] * 0.07);
        sand[4] = (int) (arr[x][y] * 0.10);

        if (sand[0] != 0){
            sum += sand[0] * 2;
            tx = x - dx[dir];
            ty = y - dy[dir];
            isRange(tx + dx[way], ty + dy[way], sand[0]);
            isRange(tx - dx[way], ty - dy[way], sand[0]);
        }
        if (sand[1] != 0){
            sum += sand[1] * 2;
            tx = x; ty = y;
            isRange(tx + dx[way] * 2, ty + dy[way] * 2, sand[1]);
            isRange(tx - dx[way] * 2, ty - dy[way] * 2, sand[1]);
        }
        if (sand[2] != 0){
            sum += sand[2];
            tx = x; ty = y;
            isRange(tx + dx[dir] * 2, ty + dy[dir] * 2, sand[2]);
        }
        if (sand[3] != 0){
            sum += sand[3] * 2;
            tx = x; ty = y;
            isRange(tx + dx[way], ty + dy[way], sand[3]);
            isRange(tx - dx[way], ty - dy[way], sand[3]);
        }
        if (sand[4] != 0){
            sum += sand[4] * 2;
            tx = x + dx[dir];
            ty = y + dy[dir];
            isRange(tx + dx[way], ty + dy[way], sand[4]);
            isRange(tx - dx[way], ty - dy[way], sand[4]);
        }

        tx = x + dx[dir];
        ty = y + dy[dir];
        isRange(tx, ty, arr[x][y] - sum);
        arr[x][y] = 0;
    }

    static void isRange(int x, int y, int sand){
        if (x < 0 || x >= N || y < 0 || y >= N)
            sandOut += sand;
        else
            arr[x][y] += sand;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tornado();

        System.out.println(sandOut);

    }
}

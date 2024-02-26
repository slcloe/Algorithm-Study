import java.io.*;
import java.util.*;
public class 0225_곽희웅 {

    /*
        접근 방식 : 최대 64*64 크기의 배열을 하나의 로봇 청소기만 탐색하는 것이기 때문에 1초의 시간 제한은 충분히 들어올 수 있다고 고려
        자료 구조 : 하나의 로봇 청소기만 탐색하기 때문에 처음 받은 좌표에 계속해서 변하는 좌표 값만 대입

        주안점
        1. 아무리 반복해도 더 이상 먼지를 제거할 수 없는 경우를 구할 때 방문했던 좌표뿐만 아니라 방향까지 고려해야 함
           - 방향에 따라 방문한 좌표라도 사이클이 달라질 수 있기 때문
        2. 전체 이동 횟수와 먼지 없는 칸을 방문했을 때의 횟수를 따로 측정.
           - 만약 먼지가 있는 칸을 방문했다면 먼지 없는 칸의 방문 횟수를 0으로 초기화
           - 이때 map에서 벗어나지 않았는지 확인하는 if문 밖에 초기화하는 코드가 있어야 함
        3. 최종 답은 (총 이동 횟수 - 먼지 없는 칸을 방문했을 때의 횟수)
        - Deque을 사용한 코드 : 메모리(290,000) 시간(576)
        - R, C, D에 값을 대입한 코드 : 메모리(12,000) 시간(232)
    */

    static int H, W, A[][], B[][];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        A = new int[H][W];
        B = new int[H][W];
        for(int i=0; i<H; i++) {
            String S = br.readLine();
            for(int j=0; j<W; j++) {
                A[i][j] = S.charAt(j) - '0';
            }
        }
        for(int i=0; i<H; i++) {
            String S = br.readLine();
            for(int j=0; j<W; j++) {
                B[i][j] = S.charAt(j) - '0';
            }
        }
        play(R, C, D);
    }

    static void play(int R, int C, int D) {
        boolean[][] map = new boolean[H][W];
        int lastX = R;
        int lastY = C;
        int lastD = D;
        int cleanCount = 0;
        int count = 0;
        while(true) {
            count++;
            if(!map[R][C]) {
                map[R][C] = true;
                int dir = (D + A[R][C]) % 4;
                int nx = R + dx[dir];
                int ny = C + dy[dir];
                cleanCount = 0;
                if(nx >= 0 && ny >= 0 && nx < H && ny < W) {
                    R = nx;
                    C = ny;
                    D = dir;
                    lastX = nx;
                    lastY = ny;
                    lastD = dir;
                } else break;
            } else {
                cleanCount++;
                int dir = (D + B[R][C]) % 4;
                int nx = R + dx[dir];
                int ny = C + dy[dir];
                if(nx >= 0 && ny >= 0 && nx < H && ny < W) {
                    if(nx == lastX && ny == lastY && dir == lastD) break;
                    R = nx;
                    C = ny;
                    D = dir;
                } else break;
            }
        }
        System.out.println(count-cleanCount);
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_bj_18405 {

    /*
    접근 방식 : 전형적인 구현 문제, 배열 리스트를 K만큼 생성한 후 가능한 경우의 수를 계속 업데이트해주기만 하면 된다.
    
    주안점
    1. index가 1부터 시작하며 이미 바이러스가 침투된 공간에는 다른 바이러스가 들어갈 수 없음
     */

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<int[]>[] g = new List[K + 1];
        for (int i = 1; i <= K; i++) {
            g[i] = new ArrayList<>();
        }

        int[][] map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    g[map[i][j]].add(new int[]{i, j});
                }
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int second = 0;
        while (true) {
            if (second++ == S) {
                System.out.println(map[X][Y]);
                break;
            }

            for (int i = 1; i <= K; i++) {
                if (!g[i].isEmpty()) {
                    infect(i, g[i], map, N);
                }
            }
        }
    }

    static void infect(int order, List<int[]> g, int[][] map, int N) {
        List<int[]> next = new ArrayList<>();
        for (int[] cur : g) {
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (check(nx, ny, N) || map[nx][ny] != 0) {
                    continue;
                }
                map[nx][ny] = order;
                next.add(new int[]{nx, ny});
            }
        }
        g.clear();
        g.addAll(next);
    }

    static boolean check(int nx, int ny, int N) {
        return nx < 1 || ny < 1 || nx > N || ny > N;
    }
}

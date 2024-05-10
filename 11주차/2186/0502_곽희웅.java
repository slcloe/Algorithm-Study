import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_bj_2186 {

    /*
    접근 방식 : 처음에는 최단 거리로 탐색하여 개수를 계산한다고 생각했는데, 결국 BFS로 계산하게 된다면 최악의 경우 20^80 이상이 나옴
               이후 DFS로 이미 계산한 곳을 return하여서 시간을 줄여봐도 시간초과가 발생하여 힌트를 보고 풀었음
    주안점
    1. 0으로 초기화를 하게 된다면, [문자열을 구성할 수 없는 경우] | [아직 방문하지 않은 경우]를 나눌 수 없어 시간초과가 발생함
    2. -1일 때는 dfs, 0일 때는 계산할 필요 없으니 continue, 둘 모두 아니라면 이미 계산된 경우기 때문에 더함
    3. 초기화와 닫힌 세계선을 잘 구별할 수 있어야 다음에 같은 문제가 나와도 풀 수 있을 것이라고 생각함..
     */

    static int N, M, K, answer;
    static String T;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][][] dist;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[100][100][81];
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String S = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = S.charAt(j);
            }
        }
        T = br.readLine();
        answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == T.charAt(0)) {
                    answer += dfs(i, j, 1);
                }
            }
        }
        System.out.println(answer);
    }

    static int dfs(int x, int y, int idx) {
        if(idx == T.length()) return dist[x][y][idx] = 1;
        dist[x][y][idx] = 0;
        for (int k=1; k<=K; k++) {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i] * k;
                int ny = y + dy[i] * k;
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == T.charAt(idx)) {
                    if(dist[nx][ny][idx+1] == 0) continue;
                    if(dist[nx][ny][idx+1] > 0) {
                        dist[x][y][idx] += dist[nx][ny][idx+1];
                    } else {
                        dist[x][y][idx] += dfs(nx, ny, idx + 1);
                    }
                }
            }
        }
        return dist[x][y][idx];
    }
}
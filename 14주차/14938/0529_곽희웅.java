import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_bj_14938 {
    /*
    접근 방식 : 전형적인 다익스트라 문제라고 생각, 전에 다익스트라로 풀어서 플루이드 워셜로 solve
               총 노드가 100개이기 때문에 100^3은 가능할 것이라고 고려
    주안점
    1. 플루이드 워셜을 위한 2차원 배열 초기화 시 최대값을 너무 크게 넣으면 오류 발생
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[] item = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        int[][] map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(map[i], 100_000_000);
            map[i][i] = 0;
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int N1 = Integer.parseInt(st.nextToken());
            int N2 = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            map[N1][N2] = map[N2][N1] = D;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (map[i][j] <= M) {
                    sum += item[j];
                }
            }
            result = Math.max(sum, result);
        }
        System.out.println(result);
    }
}

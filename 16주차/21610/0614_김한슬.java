import java.io.*;
import java.util.*;


/*
문제풀이
1. 구름 이동  d 방향으로 s 칸 이동
2. 이동한 칸의 물 1++
3. 대각선 방향 순회하여 물 >= 1 이 이상인 것
4. 구름 초기화
5. 물 +2 있는 것들 구름으로 만들기

 result : 물의 합 구하기

-- 어디서 에러가 나는지 출력으로 아직 다 못찍어봤다. 이후 수정 예정


 */
public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static boolean[][] v;
    static ArrayList<int[]> clouds = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        v = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            // magic
            cloudMoves(d, s);
            cloudRemove();
            cloudCreate();

        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += map[i][j];
            }
        }
        System.out.println(result);
    }

    // 구름 이동
    static void cloudMoves(int d, int s) {
//        for (int i = 0; i < clouds.size(); i++) {
//            int[] cloud = clouds.get(i);
//            cloud[0] = (N + cloud[0] + dx[d] * (s % N)) % N;
//            cloud[1] = (N + cloud[1] + dy[d] * (s % N)) % N;
//            map[cloud[0]][cloud[1]]++;
//        }

        for(int[] cloud : clouds) {
            cloud[0] = (N + cloud[0] + dx[d] * s) % N;
            cloud[1] = (N + cloud[1] + dy[d] * s) % N;
            map[cloud[0]][cloud[1]]++;
        }
    }
    // 구름 사라짐
    // 마법 적용

    static void cloudRemove() {
//        for (int i = 0; i < clouds.size(); i++) {
//            int[] cloud = clouds.get(i);
//            int cnt = 0;
        for(int[] cloud : clouds ) {
            v[cloud[0]][cloud[1]] = true;
            for (int j = 1; j <= 7 ; j += 2) {
                int tx = cloud[0] + dx[j];
                int ty = cloud[1] + dy[j];
                if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
                if (map[tx][ty] >= 1)
                    map[cloud[0]][cloud[1]]++;
            }
//            map[cloud[0]][cloud[1]] += cnt;
        }
    }

    static void cloudCreate() {
        clouds = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!v[i][j] && map[i][j] >= 2) {
                    map[i][j] -= 2;
                    clouds.add(new int[] {i, j});
                }
            }
        }
        v = new boolean[N][N];
    }

}

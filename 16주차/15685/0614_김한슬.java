import java.io.*;
import java.util.*;


/*
문제풀이
1. g세대 만큼 사용되는 좌표의 방향을 list에 넣는다.
    이전 세대의 좌표를 다음세대를 구하는데 모두 쓰이기 때문에
    list의 마지막 좌표부터 탐색하며 list에 넣는다.

    (규칙) 이전 세대의 벡터는 다음 세대를 거치면 반시계방향으로 회전한다.

2. x, y 부터 시작하여 list에 저장된 방향대로 선분을 회전해 가며 map에 저장한다.
3. 이후 map 에서 인접한 4개의 꼭지점이 true 라면 result++;


 */
public class Main {
    static int result = 0;
    static int N;
    static boolean[][] map = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void dragon(int x, int y, int d, int g) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(d);

        for (int i = 0; i < g; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                list.add((list.get(j) + 1) % 4);
            }
        }

        map[y][x] = true;
        for(int dir : list) {
            x += dx[dir];
            y += dy[dir];
            map[y][x] = true;
        }


    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        int x, y, d, g;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            dragon(x, y, d, g);
        }

        for (int i = 0 ; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1])
                {
//                    System.out.println(i + " " + j);
                    result++;
                }
            }
        }
        System.out.println(result);

    }

}

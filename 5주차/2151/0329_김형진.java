// 풀이
// 방문배열을 3차원으로 -> i , j, d(방향)
// 좌표는 i,j 방향, 설치한 거울 수로 한다
// # 두개중 하나를 시작점으로 BFS한다
// 1. 해당 좌표가 목적지 (#) 인지 체크
//     도착 시 거울 수 최솟값 갱신
// 2. 해당 좌표에 거울이 있는지 체크
//      2-1. 거울이 있다면? 거울 cnt ++,
//      2-2. 거울 설치 방향대로 d를 꺾는다
// 3. 좌표를 queue에 넣어서 탐색하긴 하는데
//      해당 좌표를 방문한 방향에 따라 방문처리
//      최솟값이 오도록

package week5;
import java.io.*;
import java.util.*;
public class BJ_2151 {
    static char[][] map;
    static boolean[][][] v;
    static int[] start;
    static int N;

    static int[] di = {-1,0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                char current = line.charAt(j);
                map[i][j] = current;
                if (current == '#') {
                    start = new int[] {i,i,0,0};
                }
            }
        }
    }
    static void bfs(int i, int j){

    }
}

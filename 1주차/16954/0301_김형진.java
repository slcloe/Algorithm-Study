package week1;

// 움직이는 미로 탈출
import java.io.*;
import java.util.*;

public class BOJ16954 {

    static int[] di = {0,-1, -1, 0, 1, 1, 1, 0 , -1};
    static int[] dj = {0, 0, 1, 1, 1, 0, -1, -1, -1};
    static char[][] map;
    static int[][] walls;

    static boolean result = false;

    static void Escape(){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {7,0}); // 출발

        while(!q.isEmpty()){
            boolean[][] v = new boolean[8][8];
            int size = q.size();
            // 1칸 움직이기
            for (int i = 0; i < size; i++) {
                int[] ij = q.poll();
                int ii = ij[0];
                int jj = ij[1];

                if(map[ii][jj]=='#') continue;

                if(ii== 0 && jj== 0){
                    result = true;
                    return;
                }
                for (int d = 0; d < 9; d++) {
                    int ni = ii + di[d];
                    int nj = jj + dj[d];

                    // 범위 밖
                    if(ni<0 || ni>=8 || nj<0 || nj>=8) continue;

                    // 벽 아래
                    if (ni> 0 && map[ni-1][nj] == '#') continue;

                    // 벽 위치
                    if(map[ni][nj]=='#') continue;

                    if(!v[ni][nj]){
                        v[ni][nj] = true;
                        q.offer(new int[]{ni, nj});
                    }
                }
            }

            // 벽 움직이기
            for (int i = 7; i > 0; i--) {
                map[i] = map[i-1].clone();
            }
            Arrays.fill(map[0], '.');
        }
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        walls = new int[8][8];
        map= new char[8][8];


        for (int i = 0; i < 8; i++) {
            String line= br.readLine();
            for (int j = 0; j < 8; j++) {
                char current= line.charAt(j);
                map[i][j] = current;
            }
        }
        Escape();
        System.out.println(result?1:0);

    }
}
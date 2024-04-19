import java.io.*;
import java.util.*;

public class BOJ9207 {
    static int pin;
    static int min_pin;
    static int min_move;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = { 0, 1, 0,-1};
    static char[][] map;

    static boolean isOBB(int i, int j){
        return (i<0 || i>=5 || j<0 || j>= 9);
    }

    static void move(int i, int j, int dist, int cnt){
        if(cnt <= min_pin){
            min_pin = cnt;
            min_move = dist;
        }
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if(isOBB(ni,nj)) continue;

            if(map[ni][nj] =='o'){
                int nni = ni + di[d];
                int nnj = nj + dj[d];

                if(isOBB(nni,nnj)) continue;

                if(map[nni][nnj]=='.') {
                    map[i][j] = '.';
                    map[ni][nj] = '.';
                    map[nni][nnj] = 'o';
                    for (int k = 0; k < 5; k++) {
                        for (int l = 0; l < 9; l++) {
                            if(map[k][l]=='o') move(k,l,dist+1, cnt-1);
                        }
                    }
                    map[i][j] = 'o';
                    map[ni][nj] = 'o';
                    map[nni][nnj] = '.';
                }
            }
        }

    }
    public static void main(String[] args)throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        List<int[]> pins = new ArrayList<>();

        for (int tc = 0; tc < T; tc++) {
            pin = 0;
            min_move =  Integer.MAX_VALUE;
            map = new char[5][9];
            for (int i = 0; i < 5; i++) {
               String line = br.readLine();
                for (int j = 0; j < 9; j++) {
                    char current = line.charAt(j);
                    if(current == 'o') {
                        pins.add(new int[]{i,j});
                        pin ++;
                    }
                    map[i][j] = current;
                }
            }
            min_pin = pin;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if(map[i][j]=='o') move(i,j,0,pin);
                }
            }
            System.out.println(min_pin+" "+min_move);
            if(tc != T-1) br.readLine();
        }
    }
}

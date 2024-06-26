import java.io.*;
import java.util.*;
public class BOJ_1025 {
    static boolean isSquareNumber(int num){
        return Math.sqrt(num)==Math.floor(Math.sqrt(num));
    }
    static boolean isOOB(int N, int M, int n, int m){
        return (n<0 || n>=N || m<0 || m>=M);
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N= Integer.parseInt(input[0]);
        int M= Integer.parseInt(input[1]);
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j)-48;
            }
        }

        int max = -1;

        // 시작 지점을 정하고
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                // 이동 간격을 정함
                for (int ti = -N; ti < N; ti++) {
                    for (int tj = -M; tj < M; tj++) {
                        if(ti==0 && tj==0) continue;
                        int sum = 0;
                        // 움직이기 시작
                        int ni= i;
                        int nj= j;
                        while(!isOOB(N,M,ni,nj)){
                            sum = sum*10 + map[ni][nj];
                            if(isSquareNumber(sum)){
                                max = Math.max(sum,max);
                            }
                            ni += ti;
                            nj += tj;
                        }
                    }
                }
            }
        }
        System.out.println(max);
    }
}

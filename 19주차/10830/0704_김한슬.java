import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
[문제 풀이]

exp 이 짝수일때:
    n * n 이므로 n 을 재귀를 통해 구해서 n * n 을 구한다.
exp 이 홀수일때:
    n * n * 1 이므로 n을 재귀를 통해 구해서 n * n 계산 후 1 을 더 곱하여 구한다.
   
 */


public class Main {

    static int N;
    static long B;
    static int[][] arr;

    static int[][] pow(int[][] arr, long b){
        if (b == 1L) return arr;

        int[][] res = pow(arr, b / 2);
        res = mul(res, res);

        if (b % 2 == 1L) {
            res = mul(res, arr);
        }

        return res;
    }

    static int[][] mul(int[][] arr1, int[][] arr2) {
        int res[][] = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    res[i][j] += arr1[i][k] * arr2[k][j];
                    res[i][j] %= 1000;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] result = pow(arr, B);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}


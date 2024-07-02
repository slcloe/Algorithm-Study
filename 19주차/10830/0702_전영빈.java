import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
단순히 행렬을 B번 제곱하면 되지만 문제는 B의 크기가 최대 100,000,000,000 라는 것.
따라서, O(logN) 정도의 시간 복잡도를 갖는 풀이 방법이 필요하다.

제곱근의 성질을 이용하면
A^n를 A^n/2 * 2 (짝수의 경우) 혹은 A^n-1 * A^1 (홀수의 경우) 로 변환할 수 있다.
이를 적절히 사용하여 B번의 제곱을 logB번의 제곱으로 줄여야 하는 문제였다.
 */

public class 행렬제곱_0702_전영빈 {

    static int N;
    static long B;
    static long[][] matrix;
    static long[][] sol;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        matrix = new long[N][N];
        sol = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                matrix[i][j] = Long.parseLong(st.nextToken());
                sol[i][i] = 1L;
            }
        }

        divide();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void divide() {
        while (B > 0) {
            // 지수가 홀수인 경우
            if (B % 2 == 1) {
                conquer(sol, matrix);
            }
            conquer(matrix, matrix);

            B /= 2;
        }
    }

    static void conquer(long[][] m1, long[][] m2) {
        long[][] temp = new long[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    temp[i][j] += m1[i][k] * m2[k][j];
                    temp[i][j] %= 1000L;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                m1[i][j] = temp[i][j];
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[문제풀이]
1. dfs 탐색으로 모든 경우의 수를 탐색했다.
2. +, - 일 때,
    이전에 계산한 num * op 를 더하고
    다음에 연산할 num과 op를 인수로 넘긴다.
3. ' ' 일 때, num 에 계산해야할 숫자를 *= 10, += num 연산을 수행해두고
    sum과의 연산은 뒤로 미룬다.
 */

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    static void calResult(int n, int idx, int num, int sum, int op, String str) {
        if (n == idx) {
            sum += num * op;
            if (sum == 0) {
                sb.append(str).append('\n');
            }
            return;
        }

        calResult(n, idx + 1, (num * 10) + (idx + 1), sum, op, str + ' ' + (idx + 1));
        calResult(n, idx + 1, idx + 1, sum + (op * num), 1, str + '+' + (idx + 1));
        calResult(n, idx + 1, idx + 1, sum + (op * num), -1, str + '-' + (idx + 1));


    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int t = 0; t < N; t++) {
            int n = Integer.parseInt(br.readLine());
            calResult(n, 1, 1, 0, 1, "1");

            sb.append('\n');
        }

        System.out.print(sb.toString());



    }

}



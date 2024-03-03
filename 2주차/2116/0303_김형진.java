// 주사위 쌓기

import java.util.*;
import java.io.*;
public class BOJ2116 {
    static int result;
    static void solve(int N,int depth, int side, int acc, int[][] dices, int[] op){
        if(depth==N){
            result = Math.max(acc, result);
            return;
        }
        // 윗면 index
        int op1_idx = 1;

        for (int i = 1; i <= 6; i++) {
            if(dices[depth][i]== side){
                op1_idx = i;
                break;
            }
        }
        // 밑면 index
        int op2_idx = op[op1_idx];
        int otherside = dices[depth][op2_idx];

        // 옆면중 최대값
        int max_side=0;
        for (int i = 6; i >= 1; i--) {
            if(i != side && i != otherside){
                max_side = i;
                break;
            }
        }
        solve(N, depth +1, otherside, acc+max_side, dices, op);
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dices = new int[N][7];
        result = 0;

        //0 <-> 5
        //1 <-> 3
        //2 <-> 4
        int[] op = new int[] {0, 6, 4, 5, 2, 3, 1};

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 6; j++) {
                dices[i][j] += Integer.parseInt(st.nextToken());
            }
        }

//        solve(0, N, 1, 0);
        for (int i = 1; i <= 6; i++) {
            solve(N, 0, dices[0][i], 0, dices, op);
        }
        System.out.println(result);
    }
}

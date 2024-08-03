package com.example.algo;
import java.io.*;

public class Main_bj_7490 {

    /*

    접근 방식 : 처음 읽었을 때 구현 문제라는 걸 눈치챈 후에 이을 수 있는 숫자를 잇고 시작할까 고민했는데, dfs로 풀 수 있다고 생각
              풀다보니 부분집합 느낌으로 풀게 되었고, String을 들고 다니며 저장하는 것보다는 문자 배열을 마지막에 완성시키는게 복잡도 측면에서 유리하다고 생각됨

    주안점
    1. dfs 풀이할 때, 다음 들어갈 수식 없이 하게되면 연속성이 보장되지 않음

     */
    static StringBuilder sb;
    static int limit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        // 처음 1은 무조건 고정
        // 그 다음부터 '+', '-', ' ' 이어붙이기
        for(int t=0; t<T; t++) {
            limit = Integer.parseInt(br.readLine());
            // 현재 단계, 현재 값, 총 합, 다음 들어갈 수식, 가지고 있는 수식 배열
            dfs(1, 1, 0, '+', new char[limit]);
            if(t < T - 1) sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    static void dfs(int idx, int now, int sum, char cal, char[] arr) {
        if(idx >= limit) {
            sum += cal == '+' ? now : now * -1;
            if(sum == 0) sb.append(makeResult(arr)).append("\n");
            return;
        }
        arr[idx] = ' ';
        dfs(idx+1, Integer.parseInt(String.valueOf(now)+(idx+1)), sum, cal, arr);

        int next = cal == '+' ? sum + now : sum - now;
        arr[idx] = '+';
        dfs(idx+1, idx+1, next, '+', arr);
        arr[idx] = '-';
        dfs(idx+1, idx+1, next, '-', arr);
    }

    static String makeResult(char[] arr) {
        StringBuilder result = new StringBuilder();
        for(int i=1; i<limit; i++) {
            result.append(i);
            result.append(arr[i]);
        }
        result.append(limit);
        return result.toString();
    }
}

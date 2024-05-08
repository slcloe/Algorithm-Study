package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
문제풀이

1. 왼쪽 기준으로 정렬 후 같다면 오른쪽 기준으로 오름차순 정렬함.
2. 만약 첫번째에서 기준을 만족하지 못한다면 continue;
3. 첫번째의 왼쪽과 그 다음에 비교할 대상의 오른쪽이 같을 때까지 while 문 돌림

결과 -> 시간 초과


 */

public class week10_13334_0503_김한슬 {

    static int n;
    static int d;
    static ArrayList<int[]> g;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        g = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());

            g.add(new int[] {Math.min(h, o), Math.max(h, o)});
        }

        d = Integer.parseInt(br.readLine());

        g.sort((o1, o2) -> {
           if (o1[0] == o2[0]) return o1[1] - o2[1];
           else return o1[0] - o2[0];
        });


        int i = 0;
        int result = 0;
        while (i < n) {
            int front = g.get(i)[0];
            int cnt = 0;
            int j = i;
            if (g.get(j)[1] > front + d) {
                i = next(i);
                continue;
            }
            while (j < n) {
                if (front + d >= g.get(j)[1])
                    cnt++;
                j++;
            }
            i = next(i);
            result = Math.max(result, cnt);
            if (n - i < result) break;
        }
        System.out.println(result);
    }

    static int next(int idx) {
        int cur = g.get(idx)[0];

        int i = idx + 1;
        while (i < n && cur == g.get(i)[0]) {
            i++;
        }
        return i;
    }
}

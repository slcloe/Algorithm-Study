package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/*
문제풀이
1. 저장된 vertex 를 N-1 개를 고른다.
2. graph 탐색을 한다.
3. 만약 한번 방문한 곳을 다시 방문했다면 해당 경우는 모든 곳을 탐색할 수 없다.
4. 이후 vertex에서 디저트 가게를 여는 곳들의 연속적인 최대 length 를 구한다.


 */
public class week11_27945_0509_김한슬 {

    static int N, M;
    static ArrayList<int[]> g[];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        g = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            g[from].add(new int[] {to ,t});
            g[to].add(new int[] {from, t});
        }


    }
}

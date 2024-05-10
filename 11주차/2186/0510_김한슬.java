package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
문제풀이

1. bfs 탐색을 한다.
2. 무지성 bfs 탐색을 할 시에 메모리 초과 & 시간초과 우려가 있기 때문에 v를 사용할 수 있는 방법을 고려해야한다.
3. 하지만 한번 방문한 곳을 다시 방문해도 상관없기 때문에 dp[][][] 3차원 배열을 써 depth 별로 dp 를 생성한다.


 */
public class week11_2186_0509_김한슬 {

    static int N, M, K;
    static long result = 0;
    static char[][] arr;
    static int[][] dp;
    static char[] goal;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static long getSolution() {
        long result = 0;
        ArrayDeque<int[]> deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == goal[0]) {
                    deque.offer(new int[] {i, j, 1});
                    dp[i][j] = 1;
                }
            }
        }


        while (!deque.isEmpty()) {
            int cur[] = deque.poll();

            if (cur[2] == goal.length) {

            }


        }

        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        dp = new int[N][M];


        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            arr[i] = str.toCharArray();
        }

        goal = br.readLine().toCharArray();

    }
}

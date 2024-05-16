package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 해설
 *
 * 1. pq를 사용하여 depth, virus number 순으로 순회한다.
 * 2. 문제 조건 중 virus num의 오름차순 부터 virus 가 퍼지게 됨.
 *      -> 이미 바이러스가 퍼져 있다면 새로운 바이러스로 덮을 수 없다
 *      -> 0인 부분만 바이러스로 덮을 수 있음.
 * 3. S 초까지 모든 계산을 완료 후 arr[X][Y] 를 출력
 */

public class week12_18405_0514_김한슬 {
    static int N, K;
    static int[][] arr;
    static int S, X, Y;
    static PriorityQueue<int[]> pq;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
            else return Integer.compare(o1[0], o2[0]);
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0) {
                    pq.offer(new int[] {0, arr[i][j], i, j});
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == S) continue;
            for (int i = 0; i < 4; i++) {
                int tx = cur[2] + dx[i];
                int ty = cur[3] + dy[i];

                if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
                if (arr[tx][ty] != 0) continue;
                arr[tx][ty] = cur[1];

                if (cur[0] + 1 != S)
                    pq.offer(new int[] {cur[0] + 1, cur[1], tx, ty});
            }
        }

        System.out.println(arr[X][Y]);
    }
}

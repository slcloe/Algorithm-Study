package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  풀이과정
 *
 *  구간합을 구하기 위한 세그먼트 트리를 구현해야함.
 *
 *  재귀함수를 사용하여 구간 별 합을 노드에 저장했음
 *
 *  함수 init
 *  root node : n ~ m ~ k 의 합 ( (n + k) / 2 = m ) 이다.
 *  left node : n ~ m 의 합
 *  right node : m + 1 ~ k 의 합 을 나타낸다.
 *
 *  함수 sum : left ~ right 구간의 합을 구하는 과정
 *
 *  init 을 구하는 과정 처럼 재귀함수를 통해 구현했음.
 *  하지만 init 과 달리 내가 원하는 구간과 정확한 합이 저장되어있는 노드가 없을 수도 있기 때문에
 *  범위를 중간중간 체크해야함.
 *
 *  함수 update : 해당 노드의 값을 변경하고 그에 대한 누적합을 업데이트 하는 과정
 *
 *  init 함수 로직이 기본이 되고,
 *  해당 index 의 범위가 트리 노드에 해당이 되면 diff 만큼 값을 변경하면서 child 를 탐색한다.
 *
 *  index에 포함되지 않는 node는 더 이상 탐색하지 않는다.
 *
 */
public class week3_2042_0311_김한슬 {

    static int N, M, K;
    static long[] tree;
    static long[] arr;

    public static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    public static long sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }


    public static void update(int start, int end, int node, int idx, long dif) {
        // 범위 밖에 있는 경우
        if (idx < start || idx > end) {
            return;
        }

        tree[node] += dif;
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, dif);
        update(mid + 1, end, node * 2 + 1, idx, dif);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N ; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        tree = new long[N * 4];

        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long dif = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, dif);
            } else {
                sb.append(sum(1, N, 1, b, (int) c) + "\n");
            }
        }

        System.out.println(sb.toString());
        br.close();
    }
}

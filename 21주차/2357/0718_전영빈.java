import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    나이브하게 매 번마다 루프를 통해 최댓값과 최솟값을 찾는다고 생각하면 시간 복잡도는 O(N * M) - 100,000^2.
    제한시간 2초 안에 풀이가 불가능하다.
    M번의 탐색은 줄일 수 없으니 최댓값과 최솟값을 찾는 로직의 시간 복잡도를 O(logN) 수준으로 만들어야 한다.

    매 탐색마다 a부터 b 구간 까지의 최댓값과 최솟값을 구하니 세그먼트 트리를 통한 메모이제이션을 통해 logN 내에 풀이할 수 있다.
    최소, 최대 세그먼트 트리를 만들어 구간 값을 구했다.
 */

public class 최솟값과최댓값_0718_전영빈 {

    static int N;
    static int M;

    static int[] sequence;
    static int[] maxTree;
    static int[] minTree;
    static int treeSize;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        treeSize = (int) Math.pow(2, (int) Math.ceil(Math.log(N) / Math.log(2)) + 1);
        maxTree = new int[treeSize];
        minTree = new int[treeSize];

        sequence = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        initMax(1, N, 1);
        initMin(1, N, 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            sb.append(getMin(1, N, 1, left, right))
                    .append(" ")
                    .append(getMax(1, N, 1, left, right))
                    .append("\n");
        }

        System.out.print(sb.toString());
    }

    static int initMax(int start, int end, int index) {
        if (start == end) {
            return maxTree[index] = sequence[start];
        }

        int mid = (start + end) / 2;
        return maxTree[index] =
                Math.max(initMax(start, mid, index * 2),
                        initMax(mid + 1, end, index * 2 + 1));

    }

    static int initMin(int start, int end, int index) {
        if (start == end) {
            return minTree[index] = sequence[start];
        }

        int mid = (start + end) / 2;
        return minTree[index] =
                Math.min(
                        initMin(start, mid, index * 2),
                        initMin(mid + 1, end, index * 2 + 1));
    }

    static int getMax(int start, int end, int index, int left, int right) {
        if (left > end || right < start) {
            return Integer.MIN_VALUE;
        } else if (left <= start && right >= end) {
            return maxTree[index];
        }

        int mid = (start + end) / 2;

        return Math.max(
                getMax(start, mid, index * 2, left, right),
                getMax(mid + 1, end, index * 2 + 1, left, right));
    }

    static int getMin(int start, int end, int index, int left, int right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        } else if (left <= start && right >= end) {
            return minTree[index];
        }

        int mid = (start + end) / 2;

        return Math.min(
                getMin(start, mid, index * 2, left, right),
                getMin(mid + 1, end, index * 2 + 1, left, right));
    }
}

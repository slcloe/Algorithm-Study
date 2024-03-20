import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    N <= 10^6 이므로 구간의 합을 구하기 위해 N개의 원소를 순회한다고 하면 시간 복잡도는 O(10^6).
    K, M <= 10^4 이므로 전체 최대 시간 복잡도는 O(10^10)이다.
    100억 연산은 2초 내 처리가 불가능하므로 연산의 횟수를 축소시켜야 하는데
    반복 횟수를 줄일 수는 없으므로 결국 구간의 합을 구하는 시간 복잡도를 축소시켜야 한다.
    따라서, 구간의 합을 구하는 연산의 복잡도인 O(n)를 O(log n) 수준까지 줄여야 한다. => 세그먼트 트리
 */
public class 구간합구하기_0315_전영빈 {

    static long[] numbers;
    static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        /*
            완전 이진트리 인덱스는 어차피 1부터 시작하니까
            계산을 용이하기 위해 N+1 크기로 배열 선언.
         */
        numbers = new long[N + 1];
        for (int i = 1; i < N + 1; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        /*
            완전 이진트리의 높이를 구하기 위해서는 트리 원소의 개수에 log_2를 취해야 되는데
            자바에서 제공하는 log 함수는 log_10 이기 때문에 로그 연산을 통해 log_2를 취했다.
         */
        int height = (int) (Math.ceil(Math.log(N) / Math.log(2))) + 1;
        int treeSize = (int) Math.pow(2, height);
        tree = new long[treeSize];

        initTree(1, 1, N);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // Integer -> Long는 비트 손실이 일어날 가능성이 있으므로 Long -> Integer
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long diff = c - numbers[b];
                numbers[b] = c;
                updateTree(1, 1, N, b, diff);
            } else {
                sb.append(sumTree(1, 1, N, b, (int) c) + "\n");
            }
        }

        System.out.println(sb.toString());
    }

    static long initTree(int idx, int start, int end) {
        // 해당 idx가 리프 노드일 경우
        if (start == end) {
            return tree[idx] = numbers[start];
        }

        // 해당 idx가 리프 노드가 아닐 경우, 해당 노드의 value는 좌측 자식 노드 + 우측 자식 노드.
        int middle = (start+end) / 2;
        return tree[idx] = initTree(idx * 2, start, middle) + initTree(idx * 2 + 1, middle + 1, end);
    }

    static void updateTree(int idx, int start, int end, int changed, long diff) {
        // idx가 범위 밖일 경우.
        if (idx < start || idx > end) {
            return;
        }

        tree[idx] += diff;

        if (start != end) {
            int middle = (start+end)/2;
            updateTree(idx * 2, start, middle, changed, diff);
            updateTree(idx * 2 + 1, middle + 1, end, changed, diff);
        }
    }

    static long sumTree(int idx, int start, int end, int left, int right) {
        // 현재 검색 범위가 구하고자 하는 범위 left ~ right에서 벗어나는 경우
        if (left > end || right < start) {
            return 0;
        }

        // 현재 검색 범위가 구하고자 하는 범위와 완전히 들어맞는 경우.
        // 현재 위치의 노드 값이 정답.
        if (start >= left && end <= right) {
            return tree[idx];
        }

        int middle = (start+end) / 2;
        return sumTree(idx * 2, start, middle, left, right) + sumTree(idx * 2 + 1, middle + 1, end, left, right);
    }
}

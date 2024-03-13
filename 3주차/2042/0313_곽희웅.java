import java.io.*;
import java.util.*;
public class 0313_곽희웅 {

    /*
    접근 방식 : N이 최대 100만이기 때문에 단순히 숫자를 탐색해서 변경하고 값을 구하는 것은 시간초과
               세그먼트 트리를 구성함으로써 시간 복잡도를 줄임
               아직 정확히 모두 이해한 것은 아닌데, 다음에도 다시 풀어봐야 할 듯
    자료 구조 : 값에 대한 입력은 다 long 범위이기 때문에 값이 포함되는 변수들은 모두 long으로 변환

    주안점
    1. 숫자가 주어질때마다 makeTree를 수행하면 제대로 만들어지지 않으며, 배열에 저장후 root의 변화에 따라 저장해야 함
    2. 트리 메서드의 범위 지정을 조심해야 하고, update 수행 시 한 쪽으로만 가게 된다는 것을 유의
    3. 구간 합을 구하는 메서드에서 범위 안이면 바로 해당 노드의 값을 반환하는 것을 유의
     */

    static long[] tree, nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil(Math.log(N)/ Math.log(2));
        tree = new long[(int)Math.pow(2, h+1)];
        nums = new long[N+1];
        for(int i=1; i<=N; i++) {
            nums[i]  = Long.parseLong(br.readLine());
        }

        makeTree(1,1, N);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            if(st.nextToken().equals("1")) updateTree(1, 1, N, Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
            else sb.append(getTree(1, 1, N, Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))).append("\n");
        }
        System.out.print(sb.toString());
    }

    static long getTree(int root, int start, int end, int S, int E) {
        if(end < S || E < start) return 0;
        if(S <= start && end <= E) return tree[root];
        int mid = (start + end) / 2;
        return getTree(root*2, start, mid, S, E) + getTree(root*2+1, mid+1, end, S, E);
    }

    static long updateTree(int root, int start, int end, int idx, long value) {
        if(start == end) return tree[root] = value;
        int mid = (start + end) / 2;
        if(start <= idx && idx <= mid) return tree[root] = updateTree(root*2, start, mid, idx, value) + tree[root*2+1];
        else return tree[root] = tree[root*2] + updateTree(root * 2 + 1, mid + 1, end, idx, value);
    }

    static long makeTree(int root, int start, int end) {
        if(start == end) return tree[root] = nums[start];
        int mid = (start + end) / 2;
        return tree[root] = makeTree(root*2, start, mid) + makeTree(root*2+1, mid+1, end);
    }
}

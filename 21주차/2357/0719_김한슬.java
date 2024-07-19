import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
[문제 풀이]

세그먼트 트리를 사용해야하는 문제,

세그먼트 트리로 최대 트리와 최소 트리를 만든 후
순회를 통해 최대, 최소를 구한다.

세그먼트 트리 구현 방법은 다른 코드를 참조해서 풀이했다.

 */

public class Main {
    static int n, min, max;
    static int[] elements,minTree, maxTree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        elements = new int[n+1];
        for(int i = 1; i< n+1; i++) {                              // n 개의 정수 입력
            elements[i] = Integer.parseInt(br.readLine());
        }
        int size = getTreeSize();
        minTree =new int[size];
        maxTree =new int[size];
        // 최대 트리와 최소 트리를 생성한다.
        init(0, minTree, 1,n,1);                //  세그먼트 트리 min  생성
        init(1, maxTree, 1,n,1);                //  세그먼트 트리 max  생성

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            min = Integer.MAX_VALUE; max = Integer.MIN_VALUE;

            query(0, minTree, 1,n,1,a,b);
            query(1,maxTree, 1,n,1,a,b);
            sb.append(min+" "+max+"\n");
        }
        System.out.println(sb.toString());
    }

    // 1-1. 세그먼트 트리 사이즈 구하기
    static int getTreeSize() {
        int h = (int)Math.ceil(Math.log(n)/Math.log(2)) +1;
        return (int)Math.pow(2, h);
    }

    // 1-2. 세그먼트 트리 생성
    // type 0: 최소, type 1: 최대
    static void init(int type, int[] tree, int start, int end, int node) {
        if(start == end) { // leaf node 일 때,
            tree[node] = elements[start];
        }else {
            int mid = (start+end)/2;

            // 구간을 미리 구한 후
            init(type, tree, start, mid, node*2);
            init(type, tree, mid+1, end, node*2+1);


            // 계산된 값을 바탕으로 왼쪽과 오른쪽 중 type 에 맞는 값을 택한다.
            // tree[node * 2] : left node
            // tree[node * 2 + 1] : right node
            if(type ==0) {
                if(tree[node*2] < tree[node*2+1]) {
                    tree[node] = tree[node*2];
                }else {
                    tree[node] = tree[node*2+1];
                }
            }else {
                if(tree[node*2] > tree[node*2+1]) {
                    tree[node] = tree[node*2];
                }else {
                    tree[node] = tree[node*2+1];
                }
            }
        }
    }

    // 2. 구간 [l,r] 최대 최소 구하기
    // type 0: 최소, type 1: 최대
    static void query(int type, int[] tree, int start, int end, int node, int l, int r) {
        if(l > end || r < start) return;
        if(l <= start && end <= r) { // 해당 노드가 구하고자하는 l ~ r 의 범위 내에 있을 때,
            if(type==0) {
                min = Math.min(min, tree[node]);
            }else {
                max = Math.max(max, tree[node]);
            }
            return; // 구간 내의 max, min 은 이미 구했기 때문에 더이상의 탐색은 불필요하다.
        }

        int mid = (start+end)/2;
        query(type, tree, start, mid, node*2, l ,r); // 왼쪽 노드 탐색
        query(type, tree, mid+1, end, node*2+1, l ,r); // 오른쪽 노드 탐색
    }
}


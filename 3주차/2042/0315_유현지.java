package a2403.week2;

/*
 * | 활용 자료구조 | Segment Tree
 *   참고 링크 : https://blog.naver.com/ndb796/221282210534
 * | 활용 알고리즘 | Binary Search
 * | 접근 방법 |
 *  1. 1~N의 합을 가진 루트 노드의 왼쪽 자식 노드는 1~N/2의 합, 오른쪽 자식 노드는 N/2+1~N의 합 ...
 *  2. 위의 규칙에 따라 재귀적으로 세그먼트 트리를 만든다
 *     * 세그먼트 트리의 크기 = 2^0 + 2^1 + 2^2 + ... + 2^X
 *       (2^X: N 이상이면서 가장 작은 2의 제곱수)
 *  3. 수의 갱신이 일어나면 세그먼트 트리에서 해당 수의 리프 노드에서부터 루트 노드까지
 *     모든 노드에서 기존 수와 변경할 수의 차를 더하며 세그먼트 트리를 갱신한다
 */

import java.io.*;
import java.util.*;

public class bj_g1_2042_구간_합_구하기 {
    static long[] arr, segTree;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        int size = 1;
        int two = 1;
        while (N > two) {
            two *= 2;
            size += two;
        }
        segTree = new long[size + 1];
        for(int n=1; n<=N; n++){ arr[n] = Long.parseLong(br.readLine()); }
        makeSegTree(1, 1, N);

        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            if(st.nextToken().equals("1")){
                updateTree(1, 1, N, Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
                continue;
            }
            sb.append(getPartialSum(1, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
        }
        System.out.println(sb);
    }

    static long makeSegTree(int vertex, int start, int end){
        if(start == end){ return segTree[vertex] = arr[start]; }
        int mid = (start + end) / 2;
        return segTree[vertex] = makeSegTree(vertex * 2, start, mid) + makeSegTree(vertex * 2 + 1, mid + 1, end);
    }

    static void updateTree(int treeIdx, int start, int end, int vertex, long num){
        if(start == end){
            if(start != vertex){ return; }
            long diff = num - arr[vertex];
            arr[vertex] = num;
            int v = treeIdx;
            while(v > 0){
                segTree[v] += diff;
                v /= 2;
            }
            return;
        }
        int mid = (start + end) / 2;
        if(vertex <= mid){
            updateTree(treeIdx * 2, start, mid, vertex, num);
            return;
        }
        updateTree(treeIdx * 2 + 1, mid + 1, end, vertex, num);
    }

    static long getPartialSum(int treeIdx, int vertexStart, int vertexEnd, int findStart, int findEnd){
        if(findEnd < vertexStart || vertexEnd < findStart){ return 0; }
        if(findStart <= vertexStart && vertexEnd <= findEnd){ return segTree[treeIdx]; }
        int mid = (vertexStart + vertexEnd) / 2;
        return getPartialSum(treeIdx * 2, vertexStart, mid, findStart, findEnd)
                + getPartialSum(treeIdx * 2 + 1, mid + 1, vertexEnd, findStart, findEnd);
    }
}

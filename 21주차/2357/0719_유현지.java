package a2407.study.week21;

import java.io.*;
import java.util.*;

public class bj_g1_2357_최솟값과_최댓값 {
    static class Node{
        int min;
        int max;

        public Node(int min, int max){
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString(){
            return min+" "+max;
        }
    }

    static int N, I = 0, L;
    static int MIN = 0;
    static int MAX = 1_000_000_001;
    static int[] arr;
    static Node[] tree;

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = 2*N;
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        tree = new Node[L];
        for(int i=0; i<L; i++){ tree[i] = new Node(MAX, MIN); }
        for(int i=0; i<N; i++){ arr[i] = Integer.parseInt(br.readLine()); }
        buildTree(1);
        for(int i=0; i<L; i++){
            System.out.println(i+" | "+tree[i].toString());
        }
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

        }
    }

    static boolean isLeaf(int idx){
        return L <= 2 * idx;
    }

    static void buildTree(int idx){
        if(isLeaf(idx)){
            tree[idx] = new Node(arr[I], arr[I++]);
            return;
        }
        int left = 2 * idx;
        int right = left + 1;
        buildTree(left);
        buildTree(right);
        tree[idx].min = Math.min(tree[left].min, tree[right].min);
        tree[idx].max = Math.max(tree[left].max, tree[right].max);
    }
}

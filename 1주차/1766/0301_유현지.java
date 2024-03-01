package a2402.study.week5;

/*
* 활용 알고리즘 : 그래프 위상 정렬
* 활용 자료구조 : Node Array, Priority Queue
* 참고 링크 : https://cdragon.tistory.com/entry/Algorithm-Topological-Sorting%EC%9C%84%EC%83%81-%EC%A0%95%EB%A0%AC
* 메모
*   - 처음에는 Node에 boolean visited를 추가로 사용했었으나
*     indegree == 0인 경우를 visited = true로 봐도 무방하므로 삭제함
*/

import java.io.*;
import java.util.*;

public class bj_g2_1766_문제집 {
    static class Node{
        int num;
        int indegree;
        ArrayList<Integer> next;

        public Node(int num){
            this.num = num;
            this.indegree = 0;
            this.next = new ArrayList<>();
        }

        @Override
        public String toString(){
            return "num: "+num+", indegree: "+indegree+", next: "+next.toString();
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Node[] problems = new Node[N+1];
        for(int i=0; i<=N; i++){ problems[i] = new Node(i); }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            problems[B].indegree++;
            problems[A].next.add(B);
        }
        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> o1.num - o2.num);
        for(Node problem: problems){ if(problem.indegree==0){ pq.offer(problem); } }
        pq.poll();
        while(!pq.isEmpty()){
            Node now = pq.poll();
            sb.append(now.num).append(" ");
            for(int node: now.next){
                if(problems[node].indegree == 0){ continue; }
                if(--problems[node].indegree == 0){ pq.offer(problems[node]); }
            }
        }
        System.out.println(sb);
    }
}

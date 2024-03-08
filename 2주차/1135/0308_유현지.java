package a2403.week1;

/*
 * | 활용 자료구조 | Node Tree, PriorityQueue
 * | 접근 방법 |
 *  상사는 본인의 직속 부하 중 가장 많은 직속 부하를 가지고 있는 직속 부하(...)에게 먼저 뉴스를 전달한다
 * */

import java.io.*;
import java.util.*;

/*
 * 어차피 밑으로만 가서
 * isKnowNews 필요 없을 것 같기도
 */

public class bj_g2_1135_뉴스_전하기 {
    static class Node{
        int num;
        int parent;
//        int childrenCnt;
        List<Integer> children;
        boolean isKnowNews;

        public Node(int num, int parent){
            this.num = num;
            this.parent = parent;
//            this.childrenCnt = 0;
            this.children = new ArrayList<>();
            this.isKnowNews = false;
        }

        @Override
        public String toString(){
            return "num: "+num+
                    " | parent: "+parent+
                    " | children: "+children.toString()+
//                    " | childrenCnt: "+childrenCnt+
                    " | isKnowNews: "+isKnowNews;
        }
    }
    static Node[] company;

    static int chooseNext(int num){
        company[num].isKnowNews = true;
        if(company[num].children.isEmpty()){ return -1; }
        int nextAnnouncer = -1;
        int maxChildrenCnt = -1;
        for(int child: company[num].children){
            if(company[child].children.size() <= maxChildrenCnt){ continue; }
            maxChildrenCnt = company[child].children.size();
            nextAnnouncer = child;
        }
        return nextAnnouncer;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        company = new Node[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        company[0] = new Node(0, Integer.parseInt(st.nextToken()));
        for(int n=1; n<N; n++){
            company[n] = new Node(n, Integer.parseInt(st.nextToken()));
            company[company[n].parent].children.add(n);
//            company[company[n].parent].childrenCnt++;
        }
        for(int n=0; n<N; n++){ System.out.println(company[n].toString()); }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[] {1, 0});
        while(!pq.isEmpty()){
            /* 미완 */
            int[] now = pq.poll();
            int next = chooseNext(now[1]);
            System.out.println("step: "+now[0]+" | num: "+now[1]+" | next: "+next);
            if(next == -1){ continue; };
            pq.offer(new int[] {now[0] + 1, next});
        }
    }
}

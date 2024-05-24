package solved;

import java.io.*;
import java.util.*;

public class BOJ_7983 {
    static class Subject implements Comparable<Subject>{
        int time;
        int day;
        public Subject (int time, int day){
            this.time = time;
            this.day = day;
        }

        @Override
        public int compareTo(Subject s) {
            return s.day-this.day;
        }
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Subject> pq = new PriorityQueue<>();
        for (int t = 0; t < n; t++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            pq.offer(new Subject(n1, n2));
        }

        int last = pq.peek().day;

        while(!pq.isEmpty()){
            Subject tmp = pq.poll();
            if(last >= tmp.day){
                last = tmp.day-tmp.time;
            }else{
                last -= tmp.time;
            }
        }

        System.out.println(last);
    }
}

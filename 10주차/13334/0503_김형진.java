import java.io.*;
import java.util.*;

class Way implements Comparable<Way>{
    int start;
    int end;
    public Way(int start, int end){
        this.start = start;
        this.end = end;
    }
    public int compareTo(Way w){
        return (end== w.end) ? start-w.start : end-w.end;
    }
}
public class Main {
    static int N,L;
    static ArrayList<Way> wayhome= new ArrayList<>();
    public static void main(String[] args)throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int result= 0;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st= new StringTokenizer(br.readLine());
            int a= Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());

            int start= Math.min(a,b);
            int end  = Math.max(a,b);

            wayhome.add(new Way(start,end));
        }
        L= Integer.parseInt(br.readLine());
        Collections.sort(wayhome);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

//        for(Way w : wayhome){
//            System.out.println(w.start+" , "+w.end);
//        }

        for (int i = 0; i < N; i++) {
            Way cur = wayhome.get(i);
            if(cur.end-cur.start> L) continue;

            pq.add(cur.start);
            while(!pq.isEmpty()){
                if(cur.end-pq.peek()<= L) break;
                pq.poll();
            }
            result = Math.max(result,pq.size());
        }
        System.out.println(result);
    }

}
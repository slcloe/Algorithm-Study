import java.io.*;
import java.util.*;
class Carrot implements Comparator<Carrot>{
    long w;
    long p;

    public Carrot(long w, long p){
        this.w= w;
        this.p= p;
    }

    @Override
    public int compare(Carrot o1, Carrot o2) {
        return (int)(o1.p - o2.p);
    }
}
public class BOJ_18234 {
    static int N;
    static int T;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N= Integer.parseInt(st.nextToken());
        T= Integer.parseInt(st.nextToken());
        long answer = 0;
        List<Carrot> carrots= new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st= new StringTokenizer(br.readLine());

            long w= Integer.parseInt(st.nextToken());
            long p= Integer.parseInt(st.nextToken());

            carrots.add(new Carrot(w,p));
        }

        Collections.sort(carrots,(c1,c2)-> (int)(c1.p-c2.p));

        for (int i = 0; i < N; i++) {
            long tmp= (i+T-N) * carrots.get(i).p + carrots.get(i).w;
            answer += tmp;
        }

        System.out.println(answer);
    }
}

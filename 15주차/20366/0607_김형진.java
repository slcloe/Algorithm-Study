import java.util.*;
import java.io.*;
public class BOJ_20366 {
    static int N;
    static int[] snowball;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args)throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        snowball = new int[N];
        st= new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snowball[i]= Integer.parseInt(st.nextToken());
        }
        Arrays.sort(snowball);

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int start= 0;
                int end  = N-1;
                int elsa = snowball[i] + snowball[j];
                while(start<end){
                    if(start==i || start==j){
                        start ++;
                        continue;
                    }
                    if(end==i || end==j){
                        end --;
                        continue;
                    }
                    int anna = snowball[start] + snowball[end];

                    answer = Math.min(Math.abs(elsa-anna),answer);

                    if(elsa>anna) start ++;
                    else if(elsa<anna) end--;
                    else{
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}

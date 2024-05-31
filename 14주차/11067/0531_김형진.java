import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T= Integer.parseInt(br.readLine());
        int N;
        boolean[] v;
        ArrayList<Integer>[] cafe;
        for (int i = 0; i < T; i++) {
            N= Integer.parseInt(br.readLine());
            cafe = new ArrayList[N];
            for (int j = 0; j < N; j++) {
                String[] ij = br.readLine().split(" ");
                int ii= Integer.parseInt(ij[0]);
                int jj= Integer.parseInt(ij[1]);

                if(cafe[ii] == null) cafe[ii] = new ArrayList<>();
                cafe[ii].add(jj);

            }
            String[] line = br.readLine().split(" ");
            Deque<Integer> target = new ArrayDeque<>();
            for (int j = 1; j <= Integer.parseInt(line[0]); j++) {
                target.offer(Integer.parseInt(line[j]));
            }
            int[][] answer = new int[N+1][2];
            int targetIdx= 1;
            int before = 0;
            for (int j = 0; j < N; j++) {
                if(cafe[j] == null) continue;
                cafe[j].sort(Comparator.comparingInt(a -> a));

                if(before != cafe[j].get(0)){
                    Collections.reverse(cafe[j]);
                }
                for (int k = 0; k < cafe[j].size(); k++) {
                    answer[targetIdx]= new int[]{j, cafe[j].get(k)};
                    targetIdx++;
                }

                before= answer[targetIdx-1][1];
            }
//            for (int j = 0; j < N; j++) {
//                if(answer[j] != null)System.out.println(j+" : "+answer[j][0]+" , "+answer[j][1]);
//            }
            while(!target.isEmpty()){
                int idx = target.poll();
                int[] cur = answer[idx];
                System.out.print(cur[0]+" "+cur[1]);
                System.out.println();
            }
        }
    }
}
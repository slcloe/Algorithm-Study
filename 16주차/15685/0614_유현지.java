package a2406.study.week16;

import java.io.*;
import java.util.*;

public class bj_g3_15685_드래곤_커브 {
    static int[] dr = {1, 0, -1, 0}, dc = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int n=0; n<N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            ArrayList<int[]> dragon = new ArrayList<>();
            dragon.add(new int[] {X, Y});
            dragon.add(new int[] {X+dr[D], Y+dc[D]});
            for(int g=0; g<G; g++){
                int S = dragon.size();
                int[] last = dragon.get(S-1);

            }
        }
    }
}

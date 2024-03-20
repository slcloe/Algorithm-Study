package week4;

import java.io.*;
import java.util.*;

//- 접근
//        완탐은 절대 불가
//        어디서 탐색을 시작해야할 지 고려
//        1. 별의 좌표를 좌상단으로 해서 별의 개수만큼 탐색 -> x 충분한 탐색을 할 수 없는듯
//        2. 별 두개의 좌표를 포함하는 지점(아닌 경우도 있음) -> ㅇ

public class BOJ14658 {
    static int N,M,L,K,result;
    static boolean[][] earth;
    static ArrayList<int[]> stars;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        result = 0;
        stars = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars.add(new int[]{x,y});
        }

        for (int[] star1 : stars) {
            for(int[] star2 : stars){
                int cnt = 0;
                int startX = Math.min(star1[0], star2[0]);
                int startY = Math.min(star1[1], star1[1]);
                for (int[] star: stars) {
                    int x = star[0];
                    int y = star[1];
                    if(x >= startX && x <= startX+L && y>= startY && y<= startY+L){
                        cnt ++;
                    }
                }
                result = Math.max(cnt, result);
            }

        }
        System.out.println(K-result);
    }
}

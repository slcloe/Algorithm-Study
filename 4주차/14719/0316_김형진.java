package week4;
import java.util.*;
import java.io.*;

//- 접근
//        이전 위치의 가장 높은 벽을 기록-> 기록한 벽보다 높은 벽이 나오면 누적값 기록 -> 좀 복잡함
//        각자의 위치에서 좌우 시점을 봤을때 현 위치가 얼만큼 찰 것인지 확인 가능
//        1. 양 쪽 다 내 위치보다 높은 벽이 보인다? -> 내 위치는 물이 찬다
//        2. 한쪽 혹은 양쪽 다 높은 벽이 안 보인다 -> 내 위치는 안전

public class BOJ14791 {
    static int H,W;
    static int[] walls;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        walls = new int[W];
        // 시작

        int result = 0;
        for (int i = 0; i < W; i++) {
            walls[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < W-1; i++) {
            int cur = walls[i];

            int L = 0;
            for (int j = 0; j < i; j++){
                L = Math.max(L,walls[j]);
            }

            int R = 0;
            for (int j = i+1; j < W; j++) {
                R = Math.max(R,walls[j]);
            }

            if(L<= cur || R<= cur) continue;

            result += Math.min(L,R) - cur;
        }

        System.out.println(result);
    }
}

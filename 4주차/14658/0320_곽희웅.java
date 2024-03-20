import java.io.*;
import java.util.*;
public class 0320_곽희웅 {

    /*
    접근 방식 : 500_000 크기의 2차원 배열은 답이 없었고, 별의 개수가 100개이기 때문에 별 기준으로 찾아야 한다는 것을 인지함.
               별을 꼭짓점에 두고 4분면 탐색을 해봤지만, 시간이 안됐고 이후에 두 점을 이용한 풀이는 해석을 참고함.
    자료 구조 : 특별한 것 없이 ArrayList를 사용해서 n^3으로 풀이

    주안점
    1. 별을 가장 많이 없앨 수 있는 방법은 특정 점을 꼭짓점을 두는 게 맞다.
    2. 하지만 그렇다면 별이 각각 떨어져 있을 때의 반례가 있으며 그렇기에 두 점을 기준으로 가장 작은 X, Y를 구하여 계산.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int C = Integer.parseInt(st.nextToken())+1;
        int R = Integer.parseInt(st.nextToken())+1;
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<int[]> stars = new ArrayList<>();
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new int[] {x, y});
        }
        int min = K;
        for(int i=0; i<K; i++) {
            int[] firstStar = stars.get(i);
            for(int j=0; j<K; j++) {
                int[] secondStar = stars.get(j);
                if(Math.abs(firstStar[0] - secondStar[0]) > L || Math.abs(firstStar[1] - secondStar[1]) > L) continue;
                int minX = Math.min(firstStar[0], secondStar[0]);
                int minY = Math.min(firstStar[1], secondStar[1]);
                int maxX = minX + L;
                int maxY = minY + L;
                int count = 0;
                for(int k=0; k<K; k++) {
                    int[] cur = stars.get(k);
                    if(cur[0] >= minX && cur[1] >= minY && cur[0] <= maxX && cur[1] <= maxY) count++;
                }
                min = Math.min(min, K-count);
            }
        }
        System.out.print(min);
    }
}

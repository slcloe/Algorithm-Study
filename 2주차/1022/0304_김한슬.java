package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *     -3 -2 -1  0  1  2  3
 *     --------------------
 * -3 |37 36 35 34 33 32 31
 * -2 |38 17 16 15 14 13 30
 * -1 |39 18  5  4  3 12 29
 *  0 |40 19  6  1  2 11 28
 *  1 |41 20  7  8  9 10 27
 *  2 |42 21 22 23 24 25 26
 *  3 |43 44 45 46 47 48 49
 *
 *  1. 위의 표에서 level 을 지정할 것이다.
 *  if level == 1
 *  (-1, 1) (1, 1,) (-1, -1), (-1, 1) 의 꼭지점으로 그려진 사각형이다.
 *  (r1, c1) 의 level 은 각 지점의 절대값의 최대값이다.
 *
 *  2. 해당 점의 level 을 알았다면 레벨에 해당하는 4개의 꼭지점의 수를 구한다.
 *
 *  ex )
 *  level 0 : 1
 *  level 1 : 3, 5, 7, 9
 *  level 2 : 13, 17, 21, 25
 *  level 3 : 31, 37, 43, 49
 *  ....
 *
 *  3. 각 꼭지점과 구하고자 하는 좌표의 차이를 계산하여 좌표의 값을 계산한다.
 *
 */
public class week2_1022_0306_김한슬 {

    static int r1, c1, r2, c2;

    static int calNum(int r, int c){
        int level = Math.max(Math.abs(r), Math.abs(c));

        if (level == 0) return 1;

        int[] arr = new int[5];
        int levels = (2 * level - 1) * (2 * level - 1);
        int section = 2 * level;
        arr[0] = levels + 1;
        for (int i = 1; i <= 4; i++) {
            arr[i] = levels + section * i;
        }

        if (Math.abs(r) == Math.abs(c)){
            if (r > 0 && c > 0){ //++ 4
                return arr[4];
            }else if (r > 0 && c < 0){//+- 3
                return arr[3];
            }else if (r < 0 && c > 0){ // -+ 1
                return arr[1];
            }else{ //-- 2
                return arr[2];
            }
        }

        if (Math.abs(r) == level && r < 0) { // 1
            return arr[1] + level - c;
        } else if (Math.abs(r) == level && r > 0){ // 2
            return arr[3] + level + c;
        } else if (Math.abs(c) == level && c < 0){
            return arr[3] - level + r;
        } else if (Math.abs(c) == level && c > 0){
            return arr[1] - level - r;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        int[][] arr = new int[r2 - r1 + 1][c2 - c1 + 1];
        int max = -1;

        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                arr[i - r1][j - c1] = calNum(i, j);
                max = Math.max(max, arr[i - r1][j - c1]);
            }
        }
        int cnt = 0, i = 1;

        while (i <= max){
            cnt++;
            i *= 10;
        }

        for(int[] a : arr){
            for(int b : a){
                int length = Integer.toString(b).length();
                for (int j = 0; j < cnt - length; j++) {
                    sb.append(' ');
                }
                sb.append(b).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}

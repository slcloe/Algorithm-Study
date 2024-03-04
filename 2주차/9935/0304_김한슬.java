package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;



/**
 * 1. 첫번째 문자열과 tnt 문자열을 입력받는다.
 * 2. 첫번째 문자열을 문자 하나씩 ArrayList 에 추가한다.
 * 3. 이때, tnt 문자열중 가장 마지막 문자와 같다면 비교를 시작한다.
 * 4. 문자열 끝->시작 방향으로 검사를 진행하여 같다면 tnt 문자열에 해당하는 문자열을 삭제한다.
 * 5. 2번으로 되돌아가 반복한다.
 */
public class week2_9935_0304_김한슬 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Character> strs = new ArrayList<>();
        String str1 = br.readLine();
        String str = br.readLine();

        for (int index = 0; index < str1.length(); index++) {
            strs.add(str1.charAt(index));

            if (strs.size() < str.length()) continue;
            int j;
            for (j = 0; j < str.length(); j++) {
                if (strs.get(strs.size() - j - 1) != str.charAt(str.length() - j - 1)) {
                    break;
                }
            }
            if (j == str.length()) {
                for (int i = 0; i < str.length(); i++) {
                    strs.remove(strs.size() - 1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : strs) {
            sb.append(ch);
        }

        if (strs.size() == 0) {
            System.out.println("FRULA");
        } else System.out.println(sb.toString());
    }
}

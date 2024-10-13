import java.util.*;
import java.io.*;
public class Main_bj_2179 {
    /*

    접근방식 : 접두사가 가장 긴, 가장 먼저 입력된 단어의 쌍을 구하는 문제. Map과 List를 활용해서 접두사와 해당 접두사를 가진 단어를 매핑.
              그 후 접미사로 붙인 입력 순서를 통해 답 출력
    주안점
    1. Map의 Value에 List를 담으려면 Object로 자료형을 선언한 후에 getValue 시 명시적 형변환을 해주어야 함

     */
    static String firstWord, secondWord;
    static int firstOrder, secondOrder;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Object> map = new HashMap<>();
        for(int i=0; i<N; i++) {
            String now = br.readLine()+":"+i;
            for(int j=0; j<=now.length(); j++) {
                String cur = now.substring(0, j);
                List<String> list = (List<String>)map.getOrDefault(cur, new ArrayList<>());
                list.add(now);
                map.put(cur, list);
            }
        }

        int maxLength = 0;
        firstOrder = 20001;
        secondOrder = 20001;
        firstWord = "";
        secondWord = "";
        for(String key : map.keySet()) {
            if(key.length() > maxLength) {
                List<String> list = (List<String>)map.get(key);
                if(list.size() > 1) {
                    maxLength = key.length();
                    String[] first = list.get(0).split(":");
                    String[] second = list.get(1).split(":");
                    insert(first, second);
                }
            } else if(key.length() == maxLength) {
                List<String> list = (List<String>)map.get(key);
                if(list.size() > 1) {
                    String[] first = list.get(0).split(":");
                    String[] second = list.get(1).split(":");
                    if(Integer.parseInt(first[1]) < firstOrder) {
                        insert(first, second);
                    } else if(Integer.parseInt(first[1]) == firstOrder) {
                        if(Integer.parseInt(second[1]) < secondOrder) {
                            insert(first, second);
                        }
                    }
                }
            }
        }
        System.out.println(firstWord);
        System.out.print(secondWord);
    }

    private static void insert(String[] first, String[] second) {
        firstWord = first[0];
        firstOrder = Integer.parseInt(first[1]);
        secondWord = second[0];
        secondOrder = Integer.parseInt(second[1]);
    }
}

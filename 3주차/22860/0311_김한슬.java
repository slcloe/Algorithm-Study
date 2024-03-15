package week3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 풀이 방법
 *
 * 1. file list 와 folder list를 따로 구현했다.
 * 2. file 이름 중복을 제거하기 위한 HashSet 사용
 *    탐색 효율을 위한 HashMap 사용
 *
 *  3. result 에서는 file 의 종류와 file 개수를 따로 구해야하기 때문에
 *     HashMap 을 사용하여 이름에 대한 중복은 제거하고
 *                       개수에 대한 정보는 Integer 에 저장함.
 *
 */
public class week3_22860_0311_김한슬 {
    static int N, M;
    static HashMap<String, ArrayList<String>> folders = new HashMap<>();
    static HashMap<String, HashSet<String>> files = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    static HashMap<String, Integer> result;

    static void input(String parent, String child, int isFile){
        if (isFile == 0){
            if (!files.containsKey(parent))
                files.put(parent, new HashSet<>());
            files.get(parent).add(child);
        }else{
            if (!folders.containsKey(parent))
                folders.put(parent, new ArrayList<>());
            folders.get(parent).add(child);
        }
    }

    static void findChild(String parent){
        if (files.containsKey(parent)){
            for(String file : files.get(parent)){
                if (!result.containsKey(file))
                    result.put(file, 1);
                else
                    result.put(file, result.get(file) + 1);
            }
        }
        if (folders.containsKey(parent)){
            for(String child: folders.get(parent)){
                findChild(child);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        files.put("main", new HashSet<>());
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            input(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            String folder[] = st.nextToken().split("/");

            result = new HashMap<>();
            findChild(folder[folder.length - 1]);

            int cnt = 0;
            for (Map.Entry<String, Integer> filetype : result.entrySet()) {
                cnt += filetype.getValue();
            }
            sb.append(result.size() + " " + cnt + "\n");
        }

        System.out.println(sb.toString());
    }
}



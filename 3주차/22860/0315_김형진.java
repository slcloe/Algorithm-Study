package week3;

import java.io.*;
import java.util.*;

public class BOJ22860 {

    static int N, M, Q, cnt = 0;

    static Map<String, HashSet<String>> folders = new HashMap<>();
    static Map<String, HashSet<String>> files = new HashMap<>();

    static HashMap<String, Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 입력
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());

            String parent = st.nextToken();
            String name = st.nextToken();
            int folderOrFile = Integer.parseInt(st.nextToken());

            if (folderOrFile == 1) {
                if(!folders.containsKey(parent)) folders.put(parent, new HashSet<>());
                folders.get(parent).add(name);
            } else if (folderOrFile == 0) {
                if(!files.containsKey(parent)) files.put(parent, new HashSet<>());
                files.get(parent).add(name);
            }
        }

        // 쿼리
        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            String[] folders = st.nextToken().split("/");

            result = new HashMap<>() ;

            find(folders[folders.length-1]);

            for (Map.Entry<String, Integer> filetype : result.entrySet()) {
                cnt += filetype.getValue();
            }
            System.out.println(result.size()+" "+cnt);
        }

    }

    static void find(String parent){
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
                find(child);
            }
        }
    }


}
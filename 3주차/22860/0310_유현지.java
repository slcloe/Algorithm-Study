package a2403.week2;

/*
 * | 활용 자료구조 | Node Array, ArrayList, HashSet, HashMap, Queue
 * | 활용 알고리즘 |
 * | 접근 방법 |
 *  1. Folder Node에 부모, 이름, 폴더 하위 파일(ArrayList), 중복 제거한 폴더 하위 파일(HashSet) 저장
 *  2. 입력 받으면서
 *     폴더 : Node 배열에 추가, [폴더 이름: 인덱스] 형식으로 HashMap에 추가
 *     파일 : Queue에 [부모 폴더, 파일명] 형식으로 추가
 *  3. Queue의 파일들에 대해 부모 폴더부터 main까지 모든 Folder Node의 ArrayList, HashSet에 파일 추가
 *     경로의 마지막 폴더 이름에 대한 접근만으로 파악할 수 있게 하게 하는 과정
 *  4. 쿼리 경로의 마지막 폴더 이름에 대해 ArrayList, HashSet 길이 출력
 */

import java.io.*;
import java.util.*;

public class bj_g3_22860_폴더_정리_small {
    static class Folder{
        String parent;
        String name;
        ArrayList<String> files;
        HashSet<String> unique;

        public Folder(String parent, String name){
            this.parent = parent;
            this.name = name;
            this.files = new ArrayList<>();
            this.unique = new HashSet<>();
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();
        ArrayDeque<String[]> files = new ArrayDeque<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Folder[] folders = new Folder[N + 1];
        folders[0] = new Folder(null, "main");
        map.put("main", 0);

        int folderIdx = 1;
        for(int n=0; n<N+M; n++){
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String name = st.nextToken();
            if(st.nextToken().equals("1")){
                folders[folderIdx] = new Folder(parent, name);
                map.put(name, folderIdx++);
                continue;
            }
            files.offerLast(new String[] {parent, name});
        }

        while(!files.isEmpty()){
            String[] file = files.pollFirst();
            String parent = file[0];
            String name = file[1];
            while(parent != null){
                int parentIdx = map.get(parent);
                folders[parentIdx].files.add(name);
                folders[parentIdx].unique.add(name);
                parent = folders[parentIdx].parent;
            }
        }

        int Q = Integer.parseInt(br.readLine());
        for(int q=0; q<Q; q++){
            st = new StringTokenizer(br.readLine(), "/");
            String target = "";
            while(st.hasMoreTokens()){ target = st.nextToken(); }
            sb.append(folders[map.get(target)].unique.size()).append(" ")
                    .append(folders[map.get(target)].files.size()).append("\n");
        }
        System.out.println(sb);
    }
}

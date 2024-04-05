package a2403.week6;

import java.io.*;
import java.util.*;

public class pr_3_72412_순위_검색 {
    // HashMap<String, Integer> mapL = new HashMap<>();
    // mapL.put("cpp", 1);
    // mapL.put("java", 2);
    // mapL.put("python", 3);
    int lenI, lenQ, idxF;
    char L, P, C, F, S;
    Info infoQuery;
    Info[] infoList;

    class Info implements Comparable<Info> {
        char language;
        boolean isBE;
        boolean isJunior;
        boolean isChicken;
        int score;

        public Info(String language, String isBE, String isJunior, String isChicken, String score){
            this.language = language.charAt(0);
            this.isBE = isBE.equals("backend")? true: false;
            this.isJunior = isJunior.equals("junior")? true: false;
            this.isChicken = isChicken.equals("chicken")? true: false;
            this.score = Integer.parseInt(score);
        }

        @Override
        public int compareTo(Info o){
            return this.score - o.score;
        }

        @Override
        public String toString(){
            return "| "+language+" | "+(isBE?"B":"F")+" | "+(isJunior?"J":"S")+" | "+(isChicken?"C":"P")+" | "+score+" |";
        }
    }

    public int[] solution(String[] info, String[] query) {

        lenI = info.length;
        lenQ = query.length;
        // System.out.println(lenI + " " + lenQ);

        int[] answer = new int[lenQ];
        infoList = new Info[lenI];
        for(int i=0; i<lenI; i++){
            StringTokenizer st = new StringTokenizer(info[i]);
            infoList[i] = new Info(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
        }
        // for(Info i: infoList){ System.out.println(i.toString()); }
        // System.out.println();
        Arrays.sort(infoList);
        // for(Info i: infoList){ System.out.println(i.toString()); }

        for(int i=0; i<lenQ; i++){
            // System.out.println(q);
            query[i] = query[i].replaceAll("and ", "");
            StringTokenizer st = new StringTokenizer(query[i]);
            // StringTokenizer st = new StringTokenizer(q);
            answer[i] = getCount(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
        }
        return answer;
    }

    private int getCount(String l, String p, String c, String f, String s){
        // System.out.println(s);
        int cnt = 0;

        idxF = -1;
        findIdx(Integer.parseInt(s), 0, lenI-1);
        // System.out.println(idxF+" "+infoList[idxF]);
        // System.out.println();
        if(idxF == -1){ return cnt; }


        L = l.charAt(0);
        P = p.charAt(0);
        C = c.charAt(0);
        F = f.charAt(0);
        S = s.charAt(0);
        // System.out.println(l + " " + p + " " + c + " " + f + " " + s);
        infoQuery = new Info(l, p, c, f, s);
        for(int i=idxF; i<lenI; i++){
            // System.out.println(checkCondition(i));
            if(checkCondition(i)){ cnt++; }
        }

        // System.out.println(cnt);
        return cnt;
    }

    private void findIdx(int S, int from, int to){
        if(idxF != -1){ return; }

        int mid = (from + to) / 2;
        if(mid == 0){ idxF = 0; return; }
        int nowScore = infoList[mid].score;
        int preScore = infoList[mid-1].score;

        if(S <= nowScore && preScore < S){ idxF = mid; return; }
        else if(S <= nowScore && 0 <= mid-1){ findIdx(S, from, mid-1); }
        if(mid+1 < lenI){ findIdx(S, mid+1, to); }
    }

    private boolean checkCondition(int idx){
        // System.out.println(L+" "+P+" "+C+" "+F+" "+S);
        // System.out.println(infoList[idx].toString()+"  "+infoQuery.toString());
        if(F != '-' && infoList[idx].isChicken != infoQuery.isChicken){ return false; }
        if(C != '-' && infoList[idx].isJunior != infoQuery.isJunior){ return false; }
        if(P != '-' && infoList[idx].isBE != infoQuery.isBE){ return false; }
        if(L != '-' && infoList[idx].language != infoQuery.language){ return false; }
        return true;
    }
}

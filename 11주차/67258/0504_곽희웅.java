{\rtf1\ansi\ansicpg949\cocoartf2757
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 import java.util.*;\
class Solution \{\
    /*\
    \uc0\u51217 \u44540  \u48169 \u49885  : \u52376 \u51020 \u50640 \u45716  Map\u44284  Set\u51012  \u49324 \u50857 \u54644 \u49436  \u54400 \u50632 \u45716 \u45936 , \u48152 \u47168 \u44032  \u47784 \u46160  \u47582 \u50500 \u46020  \u51228 \u52636  \u53580 \u49828 \u53944 \u52992 \u51060 \u49828 \u44032  \u53952 \u47160 \u51020 \
              \uc0\u53804  \u54252 \u51064 \u53552 , \u49836 \u46972 \u51060 \u46377  \u50952 \u46020 \u50864  \u48169 \u49885 \u51012  \u54644 \u46020  \u44057 \u51008  \u49345 \u54889 \u51060  \u48152 \u48373 \u46076 \u49436  accept \u53076 \u46300 \u47484  \u48372 \u44256  \u54400 \u50632 \u51020 \
    \
    \uc0\u51452 \u50504 \u51216 \
    1. \uc0\u44144 \u47532 \u44032  \u44057 \u50500 \u46020  \u44032 \u51109  \u48744 \u47532  \u47564 \u51313 \u46108  \u52992 \u51060 \u49828 \u44032  \u45813 \u51060 \u46972 \u45716  \u44163 \u51012  \u50976 \u51032 \
    2. \uc0\u54868 \u45236 \u47732  \u50612 \u52264 \u54588  \u50504 \u46120 ..\
    */\
    public int[] solution(String[] gems) \{\
        Map<String, Integer> map = new HashMap<>();\
        \
        int nowStart = 0;\
        int nowEnd = 0;\
        \
        int firstIdx = 0;\
        int lastIdx = 0;\
        for(int i=0; i<gems.length; i++) \{\
            nowEnd = i;\
            if(!map.containsKey(gems[i])) \{\
                map.put(gems[i], i);\
                firstIdx = nowStart;\
                lastIdx = nowEnd;\
            \} else \{\
                map.put(gems[i], i);\
                if(gems[i].equals(gems[nowStart])) \{\
                    nowStart = gems.length;\
                    for(String value : map.keySet()) \{\
                        nowStart = Math.min(nowStart, map.get(value));\
                    \}\
                    nowStart = nowStart == gems.length ? 0 : nowStart;\
                    if(nowEnd - nowStart < lastIdx - firstIdx) \{\
                        firstIdx = nowStart;\
                        lastIdx = nowEnd;\
                    \}\
                \}\
            \}\
        \}\
        \
        return new int[] \{firstIdx+1, lastIdx+1\};\
    \}\
\}}
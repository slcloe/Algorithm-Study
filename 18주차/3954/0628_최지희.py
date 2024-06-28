import sys
input=sys.stdin.readline

def main():
    tc=int(input())
    for _ in range(tc):
        sm,sc,si=map(int,input().split())
        c=input().rstrip()
        i=input().rstrip()
        arr=[0]*sm
        pointer=0
        cidx=0
        iidx=0
        max_cidx=sc
        pair=dict()
        prior=[]
        for idx in range(sc):
            if c[idx]=="[":
                prior.append(idx)
            elif c[idx]=="]":
                pair[idx]=prior.pop()
                pair[pair[idx]]=idx
        cnt=0
        while cidx<sc:
            cnt+=1
            if c[cidx]=="-":
                arr[pointer]=(arr[pointer]-1)%256
            elif c[cidx]=="+":
                arr[pointer]=(arr[pointer]+1)%256
            elif c[cidx]=="<":
                pointer=(pointer-1)%sm
            elif c[cidx]==">":
                pointer=(pointer+1)%sm
            elif c[cidx]=="[":
                if not arr[pointer]:
                    cidx=pair[cidx]
            elif c[cidx]=="]":
                if arr[pointer]:
                    cidx=pair[cidx]
            elif c[cidx]==",":
                if iidx==si:
                    arr[pointer]=255
                else:
                    arr[pointer]=ord(i[iidx])
                    iidx+=1
            if cnt>=50000000:
                max_cidx=min(cidx,max_cidx)
            cidx+=1
            if cnt>=100000000:
                print(f"Loops {max_cidx} {pair[max_cidx]}")
                break
        else:
            print("Terminates")
main()

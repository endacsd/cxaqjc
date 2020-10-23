aa'a



```c
#include <stdio.h>

int main ()
{

  int i;
  int j;
  int k;

  i = 5;
  j = 6;
  k = 12;

   

  if (i > 13){
    int x,y;
	j = 10;
	k = 0;	
  }
  else
    {
      j = 11;
      k = -10;
    }

    while (k < 5)
    {
        k = i + j;
        i++;      
    }

  return j;
}

```





```bash
gcc -fdump-translation-unit  main.c
```









控制流图







基本块





入口指令

* 第一条指令
* 任意一个条件或无条件转移指令的目标指令是一个入口指令
* 紧跟在一个条件或无条件转移指令之后的指令是入口指令





基本块识别



分支，循环，goto,break,continue语句和return 语句







cond_expr

return_expr

goto_expr label

label expr





只需要注意这些即可

让后我们需要注意的实际上还是



stmt  需要注意的是其 一系列语句

这些语句是顺序的





goto_expr 到 label 需要有一条边

return_expr 到出口



cond_expr 分出两条边 连向 label 



statement_list   变为顺序执行	   直到 cond 分出两条路





需要考虑实际上只有

root,statement_list(会被化简)





循环



在L中有一个被称为入口的节点，它是唯一前驱可能在L之外的节点  (只有一个入口)

L中的每个节点都有一个到达L的入口节点的非空路径，而且路径都在L中 (每个点都可以到达入口)





* 必经点 必须走到的节点
* 回边  回到之前的边
* 循环的识别   回到最早的 d



节点u的必经节点集D(u) 控制流图节点u的所有必经节点的集合

P(u)代表节点u的所有前驱点的集合。



d 是 u的必经节点的充要条件是 节点d 也是节点 u的前驱 P(u) 的任意节点中的必经节点

(传递性的节点)



计算

D(u)

d-> u 表示必须经过d到达u
$$
d \in D(u) \Leftrightarrow \exist x\in P(u) , d\in D(x)
$$


$$
u\in D(u)
$$


```c++


for(int i=1;i<=n;i++) D[i].add(i),Q.add(i);
while(!Q.empty()){
	//
   	//
    int u=Q.pop();
    for(int d=1;d<=n;d++){
        //
        for(int i=phed[d];i;i=nex[i]){
            x=mys[i];
            if(d in  D[x]){
                D[u].add(d);
                break;
            }
        }
    }
}
```








D(u)           u到D(u)中的有向边

u-> d 是回边      d,u 以及有通路不经过d 到达n的所有节点到达的，d是该循环的唯一入口节点





d 是 走到 u 的必经节点  如果 u 还存在其他的分支， 那么也必然经过 d 节点

所以 d -> u -> d这部分都是



其次，会存在其他入口吗，如果有的 必然是通过d 进来的，否则必经节点就不对







问题在于是不存在分支 ？

压栈保存？ 否 找前驱节点即可， 因为其前驱必然经过 u   所以必定经过 d ， 







设n->d为一回边，则在流程图中，那些不经过结点d而能到达结点n的所有结点，包括结点d和n本身，便构成了控制流图中的一个循环。此循环以结点d为其惟一入口。









![img](https://p.ananas.chaoxing.com/star3/origin/7a3ec1e662646eb8c5f14aa4de3909c6)

![img](https://p.ananas.chaoxing.com/star3/origin/26cd2e09fc661b517f030c1af13dc877)

![img](https://p.ananas.chaoxing.com/star3/700_500/ccf2ee66f325d64728d6f348e4663ffc)
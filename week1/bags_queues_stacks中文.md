# API(applicaiton programing interface)
## API结构：
> 无参数构造函数\
> 向collection中添加元素的方法\
> 检测collection是否为空的方法\
> 返回collection大小的方法\

## 泛型(Generics)
> 也叫参数化类型\
> 可以用抽象数据类型来存储任意类型的数据\

## 自动装箱(Autoboxing)
> 参数类型必须被实例化为引用类型(reference type)\
> 泛型代码可以处理原始数据类型\

## 可迭代的集合类型(Iterable collections)
>  很多时候我们需要迭代访问集合中的所有元素\
> 一级语言特性(first-class status)\

## 背包(bags)
> 不支持从中删除元素的集合数据类型\
> 收集元素并迭代遍历所有元素\
> 迭代顺序不确定且与client无关\
> 实例\
```
// 计算所有数据的标准差（使用背包，数据输入的顺序与结果无关）
public class Stats{
  public static voiid main(String[] args){
    Bag<Double> numbers = new Bag<Double>();
    while (!StdIn.isEmpty())
      numbers.add(StdIn.readDouble());
     int N = numbers.size();
     double sum = 0.0;
     for (double x : numbers)
        sum += x;
     double mean = sum/N;
     
     sum = 0.0;
     for (double x : numbers)
        sum += (x - mean)*(x - mean);
     double std = Math.sqrt(sum/(N-1));
     
     StdOut.printf("Mean: %.2f\n', mean);
     StdOut.printf("Std dev: %.2f\n", std);
  }
}
```

## 先进先出队列(FIFO Queue)
> 先进先出的策略 (First in first out -- FIFO)\
> 使用foreach语句迭代时，元素的处理顺序即是被添加到队列中的顺序\
> 使用队列时保存元素的同时保存了他们的相对顺序\
> 实例\
```
public static int[] readInts(String name){
  In in = new In(name);
  Queue<Integer> q = new Queue<Integer>();
  while (!in.isEmpty())
    q.enqueue(in.readInt());
    
  int N = q.size():
  int[] a = new int[N];
  for (int i = 0; i < N; i++)
     a[i] = q.dequeue();
  return a;
}
```

## 下压栈(Pushdown stacks)
> 新来的元素放在最上面，取的时候也从最上面开始取\
> 例如邮件系统(后进先出)\
> 好处是永远可以使用最新的元素，坏处是不清空栈的话，很早的元素永远不会被使用\
> 实例
```
Public class Reserve{
  public static void main(String[] args){
    Stack<Integer> stack;
    stack = new Stack<Integer>();
    while(!StdIn.isEmpty())
      stack.push(StdIn.readInt());
      
    for (int i : stack)
      StOut.printLn(i);
  }
}
```

## 算数表达式求值(Arithmetic expression evaluation)
> 一个使用栈(stack)的用例\
> 思想阐述\
>> 将操作数压入操作数栈\
>> 将运算符压入运算符栈\
>> 忽略左括号
>> 在遇到右括号时，弹出一个运算符，弹出所需数量的操作数，并将运算符和操作数的运算结果压入操作数栈\
> 此处为双栈运用(操作数栈 + 操作符栈)

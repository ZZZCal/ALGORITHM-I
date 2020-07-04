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
> 此处为双栈运用(操作数栈 + 操作符栈)
> 思想阐述
>> 将操作数压入操作数栈\
>> 将运算符压入运算符栈\
>> 忽略左括号\
>> 在遇到右括号时，弹出一个运算符，弹出所需数量的操作数，并将运算符和操作数的运算结果压入操作数栈\
>> 每次操作符只对两个数进行运算

# 集合类数据类型的实现
## 定容栈(Fixed-capacity stack)
## 泛型(generics)
> 使用Item类
```
public class FixedCapacityStack<Item>
```
> 这个声明体现了Item这个类型参数(type parameter)\
> 声明使用Item相当于一个象征性的占位符，后面再决定使用什么类型，这样我们就可以使用这个栈处理任意数据类型，但必须是引用类型\
> 我们可以依靠自动装箱将原始转换为相应的封装类型\
> 注意，在java中不能创建泛型数组，必须使用类型转换\
```
a = new Item[cap]; //This is wrong!!!
a = (Item[]) new Object[cap]; //Right declare
```
## 调整数组的大小
> 使用数组表示栈意味着client必须预先估计栈的最大容量\
> Java中数组一旦创立则大小无法改变，因此栈使用的空间只能是这个最大容量的一部分\
> 集合如果比数组还大那么有可能会溢出(overflow)\
> 定义一个resize()方法，可以将栈移动到不同大小的数组当中\

> 在push()方法前需检查栈是否已满，使用isFull()方法
>> 现在在push()中检查，通过检查栈大小N和数组大小a.length是否相等。如果数组太小则将数组长度加倍，然后使用a[N++] = item插入新元素

> 在pop()中，首先删除栈顶的元素，也要检测数组是否太大
>> 检测条件为栈大小是否小于数组的1/4，如果小于则将数组大小减半，保持数组约为半满状态\
>> 同时也要防止空数组导致对象游离\
>> 这种情况下栈永远不会溢出也不会使用率低于1/4

## 对象游离(loitering)
> Java的垃圾回收：回收所有无法被访问的对象的内存\
>> pop()中被弹出的元素仍处于数组中，但实际上已经是一个孤儿(orphan)了，它不会被访问了，但是数组中的引用仍存在，成为游离\
>> 避免游离只需要将被弹出的元素值设为null即可，这样引用会被一直覆盖，然后前面一个元素被回收\

## 迭代(Iteration)
> 集合类数据类型的基本操作之一，就是能够使用foreach语句通过迭代遍历并处理集合中的每个元素\
> 集合类数据类型中我们必须要实现：
>> 实现一个iterator()方法并返回一个Iterator对象\
>> Iterator类必须包括两个方法：hasNext()--返回一个布尔值 和 next()--返回集合中的一个泛型元素C

# 链表(Linked lists)
> 定义：是一种递归的数据结构。它或者为空，或者是指向一个结点(node)的引用。该节点还有一个泛型的元素和一个指向另一条链表的引用。

## 结点记录
```
private class Node{
  Item item;
  Node next;
}
```

## 构造链表
> 链表表示的是一列元素，类似于数组，但是每一个元素都是一个结点

### 表头插入结点
> 将first保存在oldfirst中\
> 将新node赋予first\
> 将first的item域设为新值，next域指向oldfirst\
```
Node oldfirst = first;
first = new Node();
first.item = "not";
first.next = oldfirst;
```

###在表头删除结点
```
first = first.next;
```
> 注意，只有一个赋值语句，因为一旦改变了first的值，再也无法访问曾经的结点了，曾经的结点变成了一个孤儿

### 在表尾插入结点
```
Node oldlast = last;
last = new Node();
last.item = "not";
oldlast.next = last;
```

### 遍历(Traversal)
> 将循环的索引变量x初始化为链表的首结点\
> 通过x.item访问和x相关联的元素，并将x设为x.next来访问链表中的下一个结点\
> 直到x为null为止
```
for (Node x = first; x! = null; x = x.next){
  // 处理x.item
}
```

### 栈的实现
> 栈的API实现即：\
> 将栈保存为一条链表, 栈的顶部即为表头, 实例变量first指向栈顶\
> 使用push()压入一个元素时，该元素加在表头\
> 使用pop()删除元素时，也是从表头删除\
> size()方法的实现，使用实例变量N保存元素的个数，在压入元素时将N+1，弹出时N-1\
> 实现isEmpty()，只需检查first是否为null，或者N是否为0

> 链表的使用：
>> 可以处理任意类型的数据\
>> 所需的空间总是和集合的大小成正比\
>> 操作所需的时间总是和集合的大小无关

> 下压堆栈(链表实现)
```
//略
```

### 队列的实现
> 将队列表示为一条从最早插入的元素到最近插入的元素的链表，实例变量last指向队列的结尾\
> 元素入列(enqueue())，将它添加到表尾\
> 元素出列(dequeue())，删除表头的结点(代码和Stack的pop()相同，但链表为空时，需要更新last的值)\
> size()和isEmpty()的实现都和Stack相同\
> 也使用泛型参数Item\

> 链表是数组的一种重要的替代方式！！

### 背包的实现
> 只需要将Stack中的push()改成add()，并去掉pop()功能即可
> 要在集合数据类型实现迭代，必须添加代码
```
// 引用java的Iterator接口
import java.util.Iterator;
// 类必然会提供一个Iterator()方法
implements Iterable<Item>
// Iterator()方法本身只是简单地从实现了Iterator接口的类中返回一个对象
// 下列代码保证了类必然会实现方法hasNext(), next(), remove()和foreach语句
public Iterator<Item> iiterator(){
	return new listIterator();
}
```

## 总结
> 现在有两种表示对象集合的方式:数组和链表\
> Java内置了数组，链表也很容易使用标准方法实现，分别被称为顺序存储和链式存储\ 












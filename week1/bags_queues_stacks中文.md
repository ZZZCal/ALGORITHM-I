# API(applicaiton programing interface)
## API结构：
>> 无参数构造函数\
>> 向collection中添加元素的方法\
>> 检测collection是否为空的方法\
>> 返回collection大小的方法\

## 泛型(Generics)
>> 也叫参数化类型\
>> 可以用抽象数据类型来存储任意类型的数据\

## 自动装箱(Autoboxing)
>> 参数类型必须被实例化为引用类型(reference type)\
>> 泛型代码可以处理原始数据类型\

## 可迭代的集合类型(Iterable collections)
>>  很多时候我们需要迭代访问集合中的所有元素\
>> 一级语言特性(first-class status)\

## 背包(bags)
>> 不支持从中删除元素的集合数据类型\
>> 收集元素并迭代遍历所有元素\
>> 迭代顺序不确定且与client无关\
>> 实例\
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

## 队列(Queue)
>> 先进先出的策略 (First in first out -- FIFO)\
>> 使用foreach语句迭代时，元素的处理顺序即是被添加到队列中的顺序\
>> 


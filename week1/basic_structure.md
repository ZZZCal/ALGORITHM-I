# Basic Struture
## Primitive data types
> integer, real number, boolean value, characters\
> byte: 8 bits; short: 16 bits; int: 32 bits; long: 64 bits\
> float: 32 bits; double: 64 bits\
> boolean: 8 bits\
> char: 16 bits
## Statements
### Declaration
> create variables\
> example: ```int a```
### Assignments
> associate a data-type value with a variable\
> example: ```a = 5```\
> Declaration and Assignments can stated at the same time
### Conditional
> example:
``` 
if (<boolean expression>){<block statements>}
elif (<boolean expression>){<block statements>}
else                   {<block statements>}
```
### Loops
> example:
```
while(<boolean expression>){<block statements>}
    
for(<initialize>; <boolean expression>; <increment>){<block statements>}
```
> break: immediately exits the loop\
> continue: begins the next interation of the loop
### Call
> invoke other methods\
> expamle: ```int key = StdIn.readInt();```
### Return
> return from a method\
> example: ```return false```
## Arrays
### Create an array
```
# long form
double[] a;
a = new double[N];
for (int i = 0; i < N; i++)
    a[i] = 0.0;

# short form
double[] a = new double[N]; #initializaiton all element = 0.0
int[] a = {numbers};
```
## Static methods
> functions actually
### Defining
```
public static double sqrt(double c){
    <body block statements>
    return x;
}
```
> first line is signature, it contains return type, method name and argument(type + varaible)\
### invoking

## Strings
## Input/output
## Data abstration

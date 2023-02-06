#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
int x;//全局变量


/**
 * 数字常量
 * 0x 或 0X 表示十六进制，0 表示八进制，不带前缀则默认表示十进制
 * 071 合法
 * 08 不合法，超过了8进制能表示的最大数8
 * 0xFeeL 合法 16进制
 *
 *
 * 字符常量 'a'
 *
 * 常量定义方式
 * 使用 #define 预处理器。
    使用 const 关键字。
 * @return
 */

#define WITH 10

enum DAY
{
    MON, TUE, WED, THU, FRI, SAT, SUN
};
//声明
int * array(int);
void func();
void point();

/**
 * C中存储类的的作用？
 *存储类定义 C 程序中变量/函数的范围（可见性）和生命周期
 * auto 存储类是所有局部变量默认的存储类
 * register 存储类用于定义存储在寄存器中而不是 RAM 中的局部变量，并不意味着变量将被存储在寄存器中，只是存储在寄存器中的变量必须使用这个修饰？
 * static 存储类指示编译器在程序的生命周期内保持局部变量的存在，而不需要在每次它进入和离开作用域时进行创建和销毁。因此，使用 static 修饰局部变量可以在函数调用之间保持局部变量的值。
 * extern 存储类用于提供一个全局变量的引用，全局变量对所有的程序文件都是可见的。当您使用 extern 时，对于无法初始化的变量，会把变量名指向一个之前定义过的存储位置。
 */

/**
 * switch 语句中的 expression 是一个常量表达式，必须是一个整型或枚举类型
 */

/**
 * 函数传值调用与引用调用
 * 传值调用：传的是基本类型的值
 * 引用调用：传地址
 */
void swap(int a,int *b){
    a=a+1;
    *b=*b+1;
}

void func(){
    int aa=10;
    int bb=30;
    printf("before aa %d,bb %d\n",aa,bb);

    swap(aa,&bb);

    printf("after aa %d,bb %d\n",aa,bb);
}

/**
 * 多维数组
 * 数组指针等问题
 */

int * array(int a){
    //声明
    int n[ 10 ]; /* n 是一个包含 10 个整数的数组 */
    int i,j;

    /* 初始化数组元素 */
    for ( i = 0; i < 10; i++ )
    {
        n[ i ] = i + 100; /* 设置元素 i 为 i + 100 */
    }

    /* 输出数组中每个元素的值 */
    for (j = 0; j < 10; j++ )
    {
        printf("Element[%d] = %d\n", j, n[j] );
    }

    //多维数组
    int aa[5][10][3];

    int bb[3][4] = {
            {0, 1, 2, 3} ,   /*  初始化索引号为 0 的行 */
            {4, 5, 6, 7} ,   /*  初始化索引号为 1 的行 */
            {8, 9, 10, 11}   /*  初始化索引号为 2 的行 */
    };

    //数组作为参数  三种方式 int *p ,int p[10],int p[]

    //返回数组

    //指向数组的指针
    int *arr;
    arr=bb;
    return arr;
}
/**
* 指针
*/

int max(int x, int y)
{
    return x > y ? x : y;
}
void populate_array(int *array,size_t arraySize,int (* ap)(void));
int getNextRandomValue(void);

void point(){
    //指针
    int a=10;
    int *p;
    p=&a;
    printf("out location %p\n",p);

    printf("out value %d\n",*p);


    //todo 指针数组
    int b[]={3,7,9};
    int *p1[3];

    for(int i=0;i<3;i++){
        p1[i]=&b[i];//把数组b 位置1的地址赋值给指针p1[i]
    }

    for(int i=0;i<3;i++){
        printf("array location %p\n",p1[i]);
        printf("array value %d\n",*p1[i]);
    }


    //todo 函数指针是指向函数的指针变量
    //typedef int (*fun_ptr)(int,int); // 声明一个指向同样参数、返回值的函数指针类型

    /* p 是函数指针 */
    int (* pp)(int, int) = & max; // &可以省略
    int a1, b1, c, d;

    printf("请输入三个数字:");
    scanf("%d %d %d", & a1, & b1, & c);

    /* 与直接调用函数等价，d = max(max(a, b), c) */
    d = pp(pp(a1, b1), c);
    printf("最大的数字是: %d\n", d);



    //todo 回调函数：函数指针作为某个函数的参数
    int myarray[10];
    /* getNextRandomValue 不能加括号，否则无法编译，因为加上括号之后相当于传入此参数时传入了 int , 而不是函数指针*/
    populate_array(myarray, 10, getNextRandomValue);//传入函数名，不能带()
    for(int i = 0; i < 10; i++) {
        printf("%d ", myarray[i]);
    }
    printf("\n");
}

// 获取随机值
int getNextRandomValue(void)
{
    return rand();
}

void populate_array(int *array,size_t arraySize,int (* ap)(void)){
    for (size_t i=0; i<arraySize; i++)
        array[i] = ap();
}

/**
 * 字符串：在 C 语言中，字符串实际上是使用空字符 \0 结尾的一维字符数组。
 */
void stringA(){
    char str[]={'w','o','r','l','d','\0'};
    char str1[20]="hello";//C 编译器会在初始化数组时，自动把 \0 放在字符串的末尾
    printf("str %s\n",str);
    printf("str1 %s\n",str1);


    //C 中有大量操作字符串的函数
    size_t size=strlen(str);
    //把str加到str1后面，注意：如果str1没有足够空间来接收str，就会报错
    strcat(str1,str);//注意：
    size_t size2=strlen(str1);

    printf("strlen str size %d\n",size);
    printf("strcat %s,size %d\n",str1,size2);
}


/**
 * 结构体
 */
struct Books
{
    char  title[50];
    char  author[50];
    char  subject[100];
    int   book_id;
} book = {"C 语言", "RUNOOB", "编程语言", 123456};


/**
 * C 语言提供了 typedef 关键字，您可以使用它来为类型取一个新的名字
 *
 * typedef vs #define
#define 是 C 指令，用于为各种数据类型定义别名，与 typedef 类似，但是它们有以下几点不同：

typedef 仅限于为类型定义符号名称，#define 不仅可以为类型定义别名，也能为数值定义别名，比如您可以定义 1 为 ONE。
typedef 是由编译器执行解释的，#define 语句是由预编译器进行处理的。
 */
#define TRUE  1  //数值定义别名
#define Aint int  //类型定义别名
void typedefD(){
    typedef unsigned char ab;
    ab b='1';
    ab b2='2';

    Aint a=11;

    printf("%d",TRUE);
    printf("a %d",a);
}


void io(){
    //C 语言中的 I/O (输入/输出) 通常使用 printf() 和 scanf() 两个函数。
    /**
     * getchar() & putchar() 函数
int getchar(void) 函数从屏幕读取下一个可用的字符，并把它返回为一个整数。这个函数在同一个时间内只会读取一个单一的字符。您可以在循环内使用这个方法，以便从屏幕上读取多个字符。

int putchar(int c) 函数把字符输出到屏幕上，并返回相同的字符。这个函数在同一个时间内只会输出一个单一的字符。您可以在循环内使用这个方法，以便在屏幕上输出多个字符。

     gets() & puts() 函数
char *gets(char *s) 函数从 stdin 读取一行到 s 所指向的缓冲区，直到一个终止符或 EOF。

int puts(const char *s) 函数把字符串 s 和一个尾随的换行符写入到 stdout。
     */

    char data[100];
    gets(data);

    puts("ceshi data");
}

void file(){
    /**
     * FILE *fopen( const char *filename, const char *mode );
     * int fclose( FILE *fp );关闭文件
     * int fgetc( FILE * fp );读取一个字符
     * char *fgets( char *buf, int n, FILE *fp );读取
     * 函数 fgets() 从 fp 所指向的输入流中读取 n - 1 个字符。它会把读取的字符串复制到缓冲区 buf，并在最后追加一个 null 字符来终止字符串。
        如果这个函数在读取最后一个字符之前就遇到一个换行符 '\n' 或文件的末尾 EOF，
        则只会返回读取到的字符，包括换行符。您也可以使用 int fscanf(FILE *fp, const char *format, ...)
        函数来从文件中读取字符串，但是在遇到第一个空格和换行符时，它会停止读取。

     * int fputc( int c, FILE *fp );写入一个字符
     * int fputs( const char *s, FILE *fp );写入一个字符串
     */
    FILE *f;
    f= fopen("/Users/opayc/products/rock_c/source/Test.txt","a+");

    char writeData[]="\n888888";
    fputs(writeData,f);

    printf("writeData finish\n");
    //todo 写完之后，指针位置在最后，使用下面函数来使文件指针指导文件开始 或者用 rewind 让文件指针的位置回到文件的起始位置
    fseek(f,0,SEEK_SET);

    //循环读取文件内容
    int readData[5];
    while (fgets(readData,5,f) != NULL){
        printf("%s",readData);
    }

    fclose(f);

    printf("\nfile close finish\n");
}

/**
 * todo 预处理器？
 *#define	定义宏
#include	包含一个源代码文件
#undef	取消已定义的宏
#ifdef	如果宏已经定义，则返回真
#ifndef	如果宏没有定义，则返回真
#if	如果给定条件为真，则编译下面代码
#else	#if 的替代方案
#elif	如果前面的 #if 给定条件不为真，当前条件为真，则编译下面代码
#endif	结束一个 #if……#else 条件编译块
#error	当遇到标准错误时，输出错误消息
#pragma	使用标准化方法，向编译器发布特殊的命令到编译器中
 */

/**
 * 头文件是扩展名为 .h 的文件，包含了 C 函数声明和宏定义，被多个源文件中引用共享。需要使用 C 预处理指令 #include 来引用它
 * 建议把所有的常量、宏、系统全局变量和函数原型写在头文件中，在需要的时候随时引用这些头文件。
 * #include <file>  引用系统，它在系统目录的标准列表中搜索名为 file 的文件
 * #include "file"  引用用户头文件，搜索当前文件路径
 *
 * 只引用一次头文件
    如果一个头文件被引用两次，编译器会处理两次头文件的内容，这将产生错误。为了防止这种情况，标准的做法是把文件的整个内容放在条件编译语句中。
    #ifndef HEADER_FILE
    #define HEADER_FILE

    the entire header file file

    #endif
 */


/**
 * C 错误处理???
 * C 语言不提供对错误处理的直接支持，但是作为一种系统编程语言，它以返回值的形式允许您访问底层数据。在发生错误时，
 * 大多数的 C 或 UNIX 函数调用返回 1 或 NULL，同时会设置一个错误代码 errno，该错误代码是全局变量，表示在函数调用期间发生了错误。
 * 您可以在 errno.h 头文件中找到各种各样的错误代码。所以，C 程序员可以通过检查返回值，
 * 然后根据返回值决定采取哪种适当的动作。开发人员应该在程序初始化时，
 * 把 errno 设置为 0，这是一种良好的编程习惯。0 值表示程序中没有错误。
 *
 * errno、perror() 和 strerror()
C 语言提供了 perror() 和 strerror() 函数来显示与 errno 相关的文本消息。

perror() 函数显示您传给它的字符串，后跟一个冒号、一个空格和当前 errno 值的文本表示形式。
strerror() 函数，返回一个指针，指针指向当前 e
 */
extern int errno ;
void error(){
    FILE * pf;
    int errnum;
    pf = fopen ("unexist.txt", "rb");
    if (pf == NULL)
    {
        //stderr全局错误变量 ，全局errno错误号
        errnum = errno;
        fprintf(stderr, "错误号: %d\n", errno);
        perror("通过 perror 输出错误");
        fprintf(stderr, "打开文件错误: %s\n", strerror( errnum ));
    }
    else
    {
        fclose (pf);
    }
}

#define MAX 100
#define DOUBLE(X) (X+X)

int main() {
    const int a=20;
    printf("Hello, World!\n");
    printf("%d",WITH);

    //在C 语言中，枚举类型是被当做 int 或者 unsigned int 类型来处理的，所以按照 C 语言规范是没有办法遍历枚举类型的。
    enum DAY day=WED;
    printf("%d\n",day);

    int ret = 10 * DOUBLE(MAX);
    printf("ret %d\n",ret);

//    func();

    //point();
    //stringA();

//    printf("%s",book.author);
//    struct Books Book1;//声明，作为函数申明，必须带struct标识
//    struct Books *pb;
//    pb=&Book1;
    //使用指向该结构的指针访问结构的成员，您必须使用 -> 运算符 pb->author


//    file();

//    error();
//    typedefD();
    return 0;
}
/**
 * 类型强转
 * 结构体类型强转，默认字节对其方式来进行对应  int对应int，非相同类型采用最近匹配
 * 方式为：
 * struct sockaddr *soc=(struct sockaddr *)&name;
 */

/**
 * 编译器把.c文件编译成纯汇编语言，再将之汇编成跟CPU相关的二进制码，生成各个目标文件 (.obj文件)
 * 连接阶段，将各个目标文件中的各段代码进行绝对地址定位，生成跟特定平台相关的可执行文件
 *
 *
 * .h 与.c是如何关联的？
 * 　　假定编译程序编译myproj.c（其中含main()）时，发现它include了mylib.h（其中声明了函数void test()），
 * 那么此时编译器将按照事先设定的路径（Include路径列表及代码文件所在的路径）查找与之同名的实现文件（扩展名为.cpp或.c，此例中为mylib.c），
 * 如果找到该文件，并在其中找到该函数（此例中为void test()）的实现代码，则继续编译；如果在指定目录找不到实现文件，
 * 或者在该文件及后续的各include文件中未找到实现代码，则返回一个编译错误.其实include的过程完全可以"看成"是一个文件拼接的过程，
 * 将声明和实现分别写在头文件及C文件中，或者将二者同时写在头文件中，理论上没有本质的区别。-----动态方式
 *
 *
 * 静态方式---我们使用的编译器默认的库文件都是使用的这种方式
 * 我们所要做的，就是写出包含函数，类等等声明的头文件（a.h,b.h,...），以及他们对应的实现文件（a.cpp,b.cpp,...），
 * 编译程序会将其编译为静态的库文件（a.lib,b.lib,...）。在随后的代码重用过程中，我们只需要提供相应的头文件（.h）和相应的库文件（.lib），就可以使用过去的代码了。
　　相对动态方式而言，静态方式的好处是实现代码的隐蔽性，即C++中提倡的"接口对外，实现代码不可见"。有利于库文件的转发.
 */

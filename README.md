# java-test

[TOC]

## 一、Java异常处理

### 1、Java异常分类

![Java异常类层次结构图](C:\Users\IOYU\Evernote\Databases\Attachments\ed5a3ee75f1b8372b8802f14439ad33f.png.backup)

异常分类：

- 可查异常（Checked Exception）：编译器要求必须处理的异常
    - 非运行时异常
- 不可查异常（Unchecked Exception）：编译器不要求必须处理的异常
    - 运行时异常（RuntimeException）：如NPE等一般都是由于程序逻辑BUG导致，需要程序从逻辑上避免
    - 错误（Error）：程序之外的异常，无法处理

注意：异常和错误的区别：异常能被程序本身可以处理，错误是无法处理。

### 2、重点

![异常捕获处理流程 ](C:\Users\IOYU\Evernote\Databases\Attachments\23c0991e3d3b03434f32225b9476bbd5.png.backup)

- try/catch/finally中return的异同
    - try中return：如果发生异常，会被catch捕获；如果没有发生异常，则会为finally进行覆盖
    - catch中return也会被finally进行覆盖

### 3、参考

- https://blog.csdn.net/hguisu/article/details/6155636

### 4、注意事项

1. 【强制】Java 类库中定义的一类RuntimeException可以通过预先检查进行规避，而不应该通过catch 来处理，比如：IndexOutOfBoundsException，NullPointerException等等。
说明：无法通过预检查的异常除外，如在解析一个外部传来的字符串形式数字时，通过catch NumberFormatException来实现。
     正例：if (obj != null) {...}
     反例：try { obj.method() } catch (NullPointerException e) {...}

2. 【强制】异常不要用来做流程控制，条件控制，因为异常的处理效率比条件分支低。

**3. 【强制】对大段代码进行try-catch，这是不负责任的表现。catch时请分清稳定代码和非稳定代码，稳定代码指的是无论如何不会出错的代码。对于非稳定代码的catch尽可能进行区分异常类型，再做对应的异常处理。**

**4. 【强制】捕获异常是为了处理它，不要捕获了却什么都不处理而抛弃之，如果不想处理它，请将该异常抛给它的调用者。最外层的业务使用者，必须处理异常，将其转化为用户可以理解的内容。**

5. 【强制】有try块放到了事务代码中，catch异常后，如果需要回滚事务，一定要注意手动回滚事务。

6. 【强制】finally块必须对资源对象、流对象进行关闭，有异常也要做try-catch。
说明：如果JDK7及以上，可以使用try-with-resources方式。

7. 【强制】不能在finally块中使用return，finally块中的return返回后方法结束执行，不会再执行try块中的return语句。

8. 【强制】捕获异常与抛异常，必须是完全匹配，或者捕获异常是抛异常的父类。
说明：如果预期对方抛的是绣球，实际接到的是铅球，就会产生意外情况。

**9. 【推荐】方法的返回值可以为null，不强制返回空集合，或者空对象等，必须添加注释充分说明什么情况下会返回null值。调用方需要进行null判断防止NPE问题。**
说明：本手册明确防止NPE是调用者的责任。即使被调用方法返回空集合或者空对象，对调用者来说，也并非高枕无忧，必须考虑到远程调用失败、序列化失败、运行时异常等场景返回null的情况。

**10. 【推荐】防止NPE，是程序员的基本修养，注意NPE产生的场景：**
     1）返回类型为基本数据类型， return 包装数据类型的对象时，自动拆箱有可能产生 NPE 。
     2） 数据库的查询结果可能为null。
     3） 集合里的元素即使isNotEmpty，取出的数据元素也可能为null。
     4） 远程调用返回对象时，一律要求进行空指针判断，防止NPE。
     5） 对于Session中获取的数据，建议NPE检查，避免空指针。
     6） 级联调用obj.getA().getB().getC()；一连串调用，易产生NPE。

     反例：public int f() { return Integer对象}， 如果为null，自动解箱抛NPE。
     正例：可以使用JDK8的Optional类来防止NPE问题。

11. 【推荐】定义时区分unchecked / checked 异常，避免直接使用RuntimeException抛出，更不允许抛出Exception或者Throwable，应使用有业务含义的自定义异常。推荐业界已定义过的自定义异常，如：DAOException / ServiceException等。

**12. 【参考】在代码中使用“抛异常”还是“返回错误码”，对于公司外的http/api开放接口必须使用“错误码”；而应用内部推荐异常抛出；跨应用间RPC调用优先考虑使用Result方式，封装isSuccess、“错误码”、“错误简短信息”。**
说明：关于RPC方法返回方式使用Result方式的理由：
     1）使用抛异常返回方式，调用方如果没有捕获到就会产生运行时错误。
     2）如果不加栈信息，只是new自定义异常，加入自己的理解的error message，对于调用端解决问题的帮助不会太多。如果加了栈信息，在频繁调用出错的情况下，数据序列化和传输的性能损耗也是问题。

13. 【参考】避免出现重复的代码（Don’t Repeat Yourself），即DRY原则。
说明：随意复制和粘贴代码，必然会导致代码的重复，在以后需要修改时，需要修改所有的副本，容易遗漏。必要时抽取共性方法，或者抽象公共类，甚至是共用模块。
     正例：一个类中有多个public方法，都需要进行数行相同的参数校验操作，这个时候请抽取：
     private boolean checkParam(DTO dto) {...}


## 二、Java注释

### 1、注释

1、注释方式

```
# 1、JavaDoc注释
/**
 *
 */

# 2、多行注释
/*
 *
 */

# 3、单行注释
//
```

2、注释使用技巧

多行注释 + 单行注释 + 标号，结构性更强，代码逻辑更清晰

```
/*
 * 1、步骤一
 */

// 1.1、XXX
// 1.2、XX

...

/*
 * 2、步骤二
 */

...

```

### 2、JavaDoc

1、参考
- https://zh.wikipedia.org/wiki/Javadoc
- https://www.cnblogs.com/boring09/p/4274893.html

2、常用标记：

- @author 作者/邮箱
- @param 变量名 描述文本
- @return 描述文本
- @throws、@exceptions 类 描述
- @version 当前版本
- @since 起始版本
- **@see 引用**
    - 类名
    - （当前类）#属性名或方法名
    - 类名#属性名或方法名
- @deprecated 描述

3、其他标记：

- {@inheriDoc}：继承父类注释
- **{@link 参考}：如{@link Character#MIN_RADIX}，用法同“ @see”**
- **{@linkplain 参考}：同{@link}，但链接的标签以纯文本字体显示，而不是代码字体**
- {@value #STATIC_FIELD}：返回静态成员的值
- {@code 文本}：在代码字体中格式化文字文本，等同于<code> {@literal} </code>
- {@literal 文本}：表示文本，文本被解释为不包含HTML标记或嵌套的javadoc标记
- {@serial 文本}：在Javadoc注释中用于默认的可序列化字段
- {@serialData 文本}：记录writeObject()或writeExternal()方法写入的数据
- {@serialField 文本}：记录ObjectStreamField组件

4、HTML排版：

- ```<br>```：回车/换行
- ```<p>```：段落
- ```<ol>+<li>```：有序列表
- ```<ul>+<li>```：无序列表
- ```<var>```：变量
- ```<h1>/<h2>/...```：标题1~6
- ```<pre>```：预定义文本

5、HTML字体格式：

- ```<i>```：斜体
- ```<b>```：粗体
- ```<sub>```：下标
- ```<sup>```：上标
- ```<ins>```：下划线
- ```<del>```：删除线
- ```<small>```：小号字体
- ```<big>```：大号字体

参考：《HTML文本格式化》

注：手动生成Doc示例（https://docs.oracle.com/javase/1.5.0/docs/tooldocs/solaris/javadoc.html#runningjavadoc）

```
javadoc -encoding utf8 -sourcepath src/main/java -d api/ -subpackages com.san
```

### 3、注意事项

1. 【强制】类、类属性、类方法的注释必须使用Javadoc规范，使用/**内容*/格式，不得使用//xxx方式。
说明：在IDE编辑窗口中，Javadoc方式会提示相关注释，生成Javadoc可以正确输出相应注释；在IDE中，工程调用方法时，不进入方法即可悬浮提示方法、参数、返回值的意义，提高阅读效率。

**2. 【强制】所有的抽象方法（包括接口中的方法）必须要用Javadoc注释、除了返回值、参数、异常说明外，还必须指出该方法做什么事情，实现什么功能。**
说明：对子类的实现要求，或者调用注意事项，请一并说明。

**3. 【强制】所有的类都必须添加创建者和创建日期**

4. 【强制】方法内部单行注释，在被注释语句上方另起一行，使用//注释。方法内部多行注释使用/* */注释，注意与代码对齐。

**5. 【强制】所有的枚举类型字段必须要有注释，说明每个数据项的用途。**

**6. 【推荐】与其“半吊子”英文来注释，不如用中文注释把问题说清楚。专有名词与关键字保持英文原文即可。 反例：“TCP连接超时”解释成“传输控制协议连接超时”，理解反而费脑筋。**

**7. 【推荐】代码修改的同时，注释也要进行相应的修改，尤其是参数、返回值、异常、核心逻辑等的修改。 **
说明：代码与注释更新不同步，就像路网与导航软件更新不同步一样，如果导航软件严重滞后，就失去了导航的意义。

8. 【参考】合理处理注释掉的代码。尽量在目标代码上方详细说明，而不是简单的注释掉。如果无用，则直接删除。
说明：代码被注释掉有两种可能性：
     1）后续会恢复此段代码逻辑。
     2）永久不用。

     前者如果没有备注信息，难以知晓注释动机。后者建议直接删掉（代码仓库保存了历史代码）。

9. 【参考】对于注释的要求：

     第一、能够准确反应设计思想和代码逻辑；
     第二、能够描述业务含义，使别的程序员能够迅速了解到代码背后的信息。

     完全没有注释的大段代码对于阅读者形同天书，注释是给自己看的，即使隔很长时间，也能清晰理解当时的思路；注释也是给继任者看的，使其能够快速接替自己的工作。

10. 【参考】好的命名、代码结构是自解释的，注释力求精简准确、表达到位。避免出现注释的一个极端：过多过滥的注释，代码的逻辑一旦修改，修改注释是相当大的负担。
     反例：
          // put elephant into fridge
          put(elephant, fridge);

     方法名put，加上两个有意义的变量名elephant和fridge，已经说明了这是在干什么，语义清晰的代码不需要额外的注释。

**11. 【参考】特殊注释标记，请注明标记人与标记时间。注意及时处理这些标记，通过标记扫描，经常清理此类标记。线上故障有时候就是来源于这些标记处的代码。**
     1） 待办事宜（TODO）:（ 标记人，标记时间，[预计处理时间]） 表示需要实现，但目前还未实现的功能。这实际上是一个Javadoc的标签，目前的Javadoc还没有实现，但已经被广泛使用。只能应用于类，接口和方法（因为它是一个Javadoc标签）。
     2） 错误，不能工作（FIXME）:（标记人，标记时间，[预计处理时间]） 在注释中用FIXME标记某代码是错误的，而且不能工作，需要及时纠正的情况。

### 4、Q&A

Q：private级别上的才需要进行注释？
A：都需要注释，但只有public级别的注释才会在生成API Docs中展现

## 三、Java命名规范

### 1、基本原则

- 见名知义
- 通俗易懂
- 避免歧义
- 简洁省力

### 2、经典命名方法

1、驼峰命名法

- 大驼峰：UpperCamelCase，Camel
- 小驼峰：lowerCamelCase，camel

2、匈牙利命名法

- 系统匈牙利命名法
    - 类型前缀 + 大驼峰，如lAccountNum表示长整数变量
- 匈牙利应用命名法
    - 目的前缀 + 大驼峰，如usName表示非安全字符串

连接：https://zh.wikipedia.org/wiki/匈牙利命名法

3、下划线命名法

单词小写，以下划线连接

### 3、命名内容

1、GAV（GroupID、ArtifactID、Version）

- 简单：com.公司名.应用名.模块名
- 高级：com.{公司/BU }.业务线 [.子业务线]，最多4级

2、项目名：单个名词

3、模块名：应用名-模块名

3、包名：全小写

- 一级包名常见的有：com/cn/org/net，二级包名以公司或个人来命名，三级包名根据应用进行命名，四级包名为模块名或层级名。
- 四级及四级以上包名根据应用架构自行定义，有人喜欢先分功能模块，再分架构模块，而有些人恰好相反。

常见功能模块：
- controller
- action
- service
- dao
- bean
- db
- util

4、类名：大驼峰

- 对象实体：名词，如FileInputStream、BufferedImage
- 功能性：形容词，如Searilizeable
- 动词：XxxxAction

注1：动作应该封装在实体对象中，能力可以封装在接口中
注2：抽象类前缀Abstract、接口实现类Impl后缀

5、方法名 / 函数名：小驼峰，动词开头

注：常见特殊的约定俗称用法：
- 情态动词(can/should/need/will)+动词，如“canDelete, canEnter, shouldDie, needDecode, willRain”
- 系词(is)+名词，如“isCard, isVip, isBoss, isMonster”
- 系词(is)+形容词，如“isBeautiful, isVestmented, isRunning”

6、变量名：小驼峰，类型前缀/后缀 + 单复数

- 基本类型：类型缩写前缀，如intA、shtB、byeC
- 引用类型：类型或类型缩写后缀，如confirmButton、confirmBtn

注1：接口变量名为接口名

注2：基本类型缩写如下：

![Java基本类型缩写](C:\Users\IOYU\Evernote\Databases\Attachments\ceda0a272328f15a864a41345f073e3c.png.backup)

注3：计算机编程常用缩写见《Java常用缩写》

7、常量名：大写单词组合，以下划线（_）进行组合

### 4、命名重点

- 动词 vs 名词
- 单数 vs 复数
- 类型
- 缩写

### 5、参考

- https://blog.csdn.net/zhu_xun/article/details/19912411
- https://www.ibm.com/developerworks/cn/java/deconding-code-specification-part-1/index.html

## 四、Java常用缩写

### 1、基本原则

- 尽量不使用缩写，除非是约定俗成的缩写，如Internationalization缩写成I18N
- 词组过长，影响代码简洁度，可使用缩写

### 2、基本要点

- 见名知义
- 通俗易懂
- 避免歧义
- 简洁省力

### 3、如何使用缩写

1、常用缩写集锦

参考
- 常用缩写：https://blog.csdn.net/elegant__/article/details/9748835
- 缩写词查询：https://www.abbreviations.com

2、自定义缩写

- 优先使用代码中已存在的缩写或缩写规则，保持一致性
- 单个单词取前4个字母，多个单词取词组首字母小写（可读性很差，一般用于方法体内部）

### 4、Q&A

Q：大小写区分？
A：
     缩写都需要全大写或全小写，如TCP（Transmission Control Protocol）作为前缀时全小写，作为后缀时全大写，在中间时首字母大写（大驼峰）


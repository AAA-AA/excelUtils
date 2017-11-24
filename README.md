# Excel导入导出工具类
A simple excel import and export tool with java

### excelUtils 1.0版本相关特性
* 支持**集合类**的导入导出
* 配置简单，只需对将要导入、导出的实体类添加Import、Export注解
* 支持对Date类型的导入导出处理
* 侵入性小，将该工具包打包后直接引入即可

### The features of ExcelUtils 1.0 version
* Import and export support for collection classes
* The configuration is simple, simply adding Import and Export annotations to the entity class to import and export
* Support for processing Date type when handle the import and export

### 使用指导
* 首先获取源代码，可以使用下载压缩包或者clone的方式，clone命令：```git clone git@github.com:AAA-AA/excelUtils.git```
* 获取到源代码后，打开工程，找到test目录，里面有个ExcelTest类，根据自己的系统修改相应的路径，然后直接运行即可


### User Guide
* In the first,you should get the source code, you can either download the zip of this project or use command of git,just like here:```git clone git@github.com:AAA-AA/excelUtils.git```
* After you have got the code, then, you should open the project, and find the package of test, in the package, there  is a class of ExcelTest, you should configure the path before start

### 关于更多

### And More

### 注意事项
* 此工具类中执行导入操作时需注意，请务必在classpath路径下包含excel_map.properties文件，里面配置需要导出的指定属性

### Pay Attention

<hr>


##### Version 1.0.1
> 1.更改依赖的作用域，避免依赖方重复引入

##### Version 1.0.0

> 1.基础初始化版本


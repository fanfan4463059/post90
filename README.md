# post90
post90

# DevelopBuildTools
 是一个java项目，里面有这一些提升 android和IOS 开发效率
 
 com.developbuildtools.RunningMain ---执行类

# 一.实现json转成model

 DevelopBuildTools\src\com\developbuildtools\files\json2model\JsonToFileTools.java
 
用法：

 ①一段符合json的字符串
 
 ②最外层model的名称
 
 ③android-Model 包名地址
 
 ---静静的等待model生成就好了
 
 原理：
 
 1.将json的key集合记录
 
 2.根据json特征 {、[、{[。判断属于，默认String类型、新model类型、listModel类型
 
 3.根据提供的参数，在桌面生成需要的文件
 
 PS:目前只有android+IOS的model
 
# 二.目前就一个，等更新

有建议，联系我 QQ：175406226

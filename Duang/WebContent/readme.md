## git使用
### http://www.cnblogs.com/draem0507/archive/2013/01/14/2859954.html

## git版本开发代码管理
```html
	整体项目分为master和develop两个分支，master主要用来发布网站使用．develop主要是用来分开使用．
	平时每个人开发的时候，从develop中clone一下并创建一个开发者自己的分支(xx、xx、xx)．当开发工作完成后，提交本地仓库并git push自己的分支．
	最后先将develop合并到自己的分支(开发期间可能被开发者进行过修改)，以确保合并成功．合并无误后，再将当前合并后的xx分支合并到develop分支中．(注：这里的合并操作先是在本地分支合并．然后再合并到远程分支．有点多操作一步)．
	到最后工作结束后再将develop合并到master分支，通过master上线运行．
	另外对于线上环境有紧急bug要修改的时候．再从master里创建一个分支．独立维护．结束后，再分别同步master和develop两个分支．
```

##了解
###1.注解模板：file->codetemplates.xml
###2.先设置编码-8的
###3.命名看注解切入那儿
###4.services和dao层的异常全部抛出（严禁捕获），在action try catch
###5.jsp放web-info下面
###6.tomcat用file文件夹下面那个吧，编码设置好了，不然添加数据也许会乱码，mysql，使用的是5.7（192.168.11.56）

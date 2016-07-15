## git使用
### http://www.cnblogs.com/draem0507/archive/2013/01/14/2859954.html

## git版本开发代码管理
```html
	<p>整体项目分为master和develop两个分支，master主要用来发布网站使用．develop主要是用来分开使用．</p>
	<p>平时每个人开发的时候，从develop中clone一下并创建一个开发者自己的分支(xx、xx、xx)．当开发工作完成后，提交本地仓库并git push自己的分支．</p>
	<p>最后先将develop合并到自己的分支(开发期间可能被开发者进行过修改)，以确保合并成功．合并无误后，再将当前合并后的xx分支合并到develop分支中．(注：这里的合并操作先是在本地分支合并．然后再合并到远程分支．有点多操作一步)．</p>
	<p>到最后工作结束后再将develop合并到master分支，通过master上线运行．</p>

	<p>另外对于线上环境有紧急bug要修改的时候．再从master里创建一个分支．独立维护．结束后，再分别同步master和develop两个分支．</p>
```
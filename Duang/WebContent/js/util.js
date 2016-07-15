/**
 * 获取元素,或者在DOM加载完成时执行某函数
 * @param arg 此参数为字符串时,认为是 c$.getElement 的缩写,用来获取元素。用法参考 c$.getElement
 *            如果 arg 是 function 则认为是 c$.ready 的缩写,在dom加载完成时执行。没有此参数则返回 c$ 对象。
 * @param dom 需要选择的DOM对象,默认是 window.document
 * @return 没有arg参数时返回 c$ 对象，arg参数是字符串时返回查询的元素，arg参数是函数时没有返回内容。
 * @example
 *  c$("mytext")  // 返回 id 或者 name 为"mytext"的元素
 *  c$("#mytext") // 返回 id 为"mytext"的元素
 *  c$("@mytext") // 返回 name 为"mytext"的所有元素
 *  c$(".class1") // 返回 class 为"class1"的所有元素
 *  c$("$div")    // 返回 标签 为"div"的所有元素
 *  c$("$div #text1")  // 返回 div 标签里面 id 为"text1"的元素(支持多级查询，以空格分隔)
 *  c$(function(){alert('执行DOM加载完成事件');}); // 为 c$.ready(fun) 的缩写
 *  c$.函数名(参数列表)    // 调用这工具类里面的函数
 *  c$().函数名(参数列表)  // 调用这工具类里面的函数
 */
var c$ = window.c$ = function(arg, dom) {
	// 如果没有参数，则返回 本对象；让程序可以这样写： c$().函数名(参数列表)
	if (arguments.length === 0) return c$;
	if (typeof arg == 'function') return c$.ready(arg);
	// 有参数则调用获取元素的函数,为 c$.getElement 的缩写
	return c$.getElement(arg, dom);
};

/**
 * 这是错误调试程序(仅IE、fiefox有效,w3c标准里面没有此定义)
 * 当页面发生错误时，提示错误讯息；仅测试环境里会提示，正式环境下不提示错误。
 * 注意：chrome、opera 和 safari 浏览器不支持 onerror 事件
 * @param msg   出错讯息
 * @param url   出错档案的地址
 * @param sLine 发生错误的行
 * @return true 返回true,会消去 IE下那个恼人的“网页上有错误”的提示
 */
window.onerror = function(msg, url, sLine) {
	// 测试时可以提示出错信息；正式发布时不提示
	if (c$.isTest) {
		var errorMsg = "当前页面的javascript发生错误.\n\n";
		errorMsg += "错误: " + msg + "\n";
		errorMsg += "URL: " + url + "\n";
		errorMsg += "行: " + sLine + "\n\n";
		errorMsg += "点击“确定”消去此错误，“取消”保留此错误。\n\n";
		return window.confirm( errorMsg );
	}
	// 返回true,会消去 IE下那个恼人的“网页上有错误”的提示
	return true;
};

/**
 * 是否测试环境
 * @return 如果是本机或者局域网，则认为是测试环境(为 true)； 其它则认为是正在发布的环境(为 false)
 * @example if (c$.isTest) alert('这是测试环境'); // 注意,这个是值,不是函数
 */
c$.isTest = (function() {
	var url = window.location.href;
	// 判断网址,如果是本机或者局域网，则认为是测试环境
	return (url.indexOf("http://localhost") === 0 || url.indexOf("http://127.0.0.1") === 0 ||
			url.indexOf("http://192.168.") === 0 || url.indexOf("file://") === 0);
})();


/* ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 浏览器兼容 start
 * --------------------------------------------------------------- */

/**
 * 运行平台判断(是否 windows)
 * @return 如果是 windows 平台则为“true”，其它平台则返回“false”
 * @example if (c$.isWin) alert('这是 window 平台'); // 注意,这个是值,不是函数
 */
c$.isWin = (window.navigator.appVersion.toLowerCase().indexOf("win") != -1);

/**
 * 获取浏览器类型
 * @return [浏览器名称, 版本]
 * @example if (c$.browser()[0] == 'IE') alert('这是 IE 浏览器, 版本号为:' + c$.browser()[1]);
 */
c$.browser = function() {
	var thisFun = arguments.callee;
	// 如果曾经获取过,不用再重复判断,以提高效率
	return thisFun._retValue || (thisFun._retValue = (function() {
		var ua = window.navigator.userAgent.toLowerCase();
		if (ua.indexOf('msie') > -1) return ['IE', ua.match(/msie ([\d.]+)/)[1]];
		if (ua.indexOf('firefox') > -1) return ['Firefox', ua.match(/firefox\/([\d.]+)/)[1]];
		if (ua.indexOf('opera') > -1) return ['Opera', ua.match(/opera.([\d.]+)/)[1]];
		if (ua.indexOf('chrome') > -1) return ['Chrome', ua.match(/chrome\/([\d.]+)/)[1]];
		if (ua.indexOf('safari') > -1) return ['Safari', ua.match(/version\/([\d.]+)/)[1]];
		return ['Other', null];
	})());
};

/**
 * 浏览器判断(注意,以下这些是值,不是函数)
 * @example
 *   if (c$.browser.isIE) alert('这是 IE 浏览器');
 *   if (c$.browser.isIE6) alert('这是 IE6 浏览器');
 *   if (c$.browser.isNav) alert('这是 Netscape 浏览器');
 *   if (c$.browser.isFF) alert('这是 Firefox 浏览器');
 *   if (c$.browser.type == 'IE') alert('这是 IE 浏览器, 版本号为:' + c$.browser.version);
 *   alert('这是 ' + c$.browser.type + ' 浏览器, 版本号为:' + c$.browser.version);
 */
//如果是火狐等浏览器则为“true”，IE浏览器则返回“false”
c$.browser.isNav = (window.navigator.appName.indexOf("Netscape") != -1);
//是否火狐
c$.browser.isFF = (window.navigator.userAgent.indexOf("Firefox") != -1);
//是否IE
c$.browser.isIE = (window.navigator.appName.indexOf("Microsoft") != -1);
//是否IE6
c$.browser.isIE6 = (window.navigator.userAgent && window.navigator.userAgent.split(";")[1].toLowerCase().indexOf("msie 6.0") != -1);
//另一种获取浏览器类型的写法(值为字符串),值会有: IE, Firefox, Opera, Chrome, Safari, Other
c$.browser.type = c$.browser()[0];
//浏览器的版本(值为字符串)
c$.browser.version = c$.browser()[1];


/**
 * 获取事件的 event
 */
c$.getEvent = function() {
	if (c$.browser.isIE) return window.event; // IE
	var func = c$.getEvent.caller;
	while (func != null) {
		var arg0 = func.arguments[0];
		if (arg0 instanceof Event) {
			return arg0;
		}
		func = func.caller;
	}
	return null;
};

//标准浏览器就FIX,让火狐兼容IE的写法
if (window.addEventListener) {
	try {
		// window.event 兼容
		window.constructor.prototype.__defineGetter__("event", c$.getEvent);

		// event.srcElement 兼容；获取浏览器的事件源
		Event.prototype.__defineGetter__("srcElement", function(){ return this.target || this.srcElement; });

		// event.fromElement 兼容
		Event.prototype.__defineGetter__("fromElement",  function(){
			var node;
			if (this.type == "mouseover") {
				node = this.relatedTarget;
			}
			else if (this.type == "mouseout") {
				node = this.target;
			}
			if (!node) return;
			while (node.nodeType != 1) {
				node = node.parentNode;
			}
			return node;
		});

		// event.toElement 兼容
		Event.prototype.__defineGetter__("toElement",  function(){
			var node;
			if (this.type == "mouseout") {
				node = this.relatedTarget;
			}
			else if (this.type == "mouseover") {
				node = this.target;
			}
			if (!node) return;
			while (node.nodeType != 1) {
				node = node.parentNode;
			}
			return node;
		});

		// <element>.runtimeStyle 兼容
		HTMLElement.prototype.__defineGetter__("runtimeStyle", function(){ return this.style; });

		// <element>.innerText 兼容 (Getter)
		HTMLElement.prototype.__defineGetter__("innerText", function(){
			var anyString = "";
			var childS = this.childNodes;
			for (var i = 0, length = childS.length; i < length; i++) {
				if (childS[i].nodeType == 1)
					anyString += childS[i].tagName == "BR" ? '\n' : childS[i].innerText;
				else if(childS[i].nodeType == 3) {
					anyString += childS[i].nodeValue;
				}
			}
			return anyString;
		});
		// <element>.innerText 兼容 (Setter)
		HTMLElement.prototype.__defineSetter__("innerText", function(sText) { this.textContent = sText; });
	} catch(e){}
}
/* -------------------------------------------------------------------
 * 浏览器兼容 end
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ */



/* ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 内置对象额外添加函数 start
 * --------------------------------------------------------------- */

/**
 * 去除字符串的前后空格
 * @return 去除前后空格后的字符串
 * @example  " dd dd ".trim()  // 返回: "dd dd"
 */
String.prototype.trim = function(direct) {
	return this.replace(new RegExp("(^(\\s*　*)*)|((\\s*　*)*$)", "g"), "");
};

/**
 * 全部替换字符串中的指定内容(正则表达式替换)
 * @param regexp 把字符串里的regexp内容替换成newSubStr(此参数会作为正则表达式的字符串处理)
 * @param newSubStr 把字符串里的regexp内容替换成newSubStr
 * @param flags 正则表达式的匹配方式,有: g global(全文查找出现的所有 pattern),i ignoreCase(忽略大小写),m multiLine(多行查找)；默认是 "gm"
 * @return 替换后的字符串。注意：当regexp为空时,字符串的每个字前面都会加上newSubStr
 * @example
 *  "add dda".replaceAll('a', '55')  // 返回: "55dd dd55"
 *  "add+d+da".replaceAll('\\+', ' ')  // 支持正则表达式的字符串替换,特殊字符需要转义,返回: "add d da"
 */
String.prototype.replaceAll = function(regexp, newSubStr, flags) {
	regexp = regexp || '';
	newSubStr = newSubStr || '';
	flags = flags || 'gm';
	var raRegExp = new RegExp("" + regexp, flags);
	return this.replace(raRegExp, "" + newSubStr);
};

/**
 * 全部替换字符串中的指定内容(非正则表达式替换)
 * @param oldStr 要替换的内容
 * @param newStr 替换成这个内容
 * @return 替换后的字符串。注意：当 oldStr 为空时,不会替换
 * @example
 *  "add dda".replaceAllStr('a', '55')  // 返回: "55dd dd55"
 *  "add+d+da".replaceAllStr('+', ' ')   // 不支持正则表达式的字符串替换,返回: "add d da"
 */
String.prototype.replaceAllStr = function(oldStr, newStr) {
	if (!oldStr) return this;
	var str = this;
	while(str.indexOf(oldStr) != -1) str = str.replace(oldStr, newStr);
	return str;
};

/**
 * 判断是否以子串开头
 * @param sub 被判断的子串
 * @return 是以子串开头则返回 true，否则返回false
 */
String.prototype.startWith = function(sub) {
	if (this === sub || sub === '') return true;
	return this.length >= sub.length && this.slice(0, sub.length) === sub;
};

/**
 * 判断是否以子串结尾
 * @param sub 被判断的子串
 * @return 是以子串结尾则返回 true，否则返回false
 */
String.prototype.endWith = function(sub) {
	if (this === sub || sub === '') return true;
	return this.length >= sub.length && this.slice(0 - sub.length) === sub;
};

/**
 * 字符串格式化输出
 * @param value 格式化的对象内容(说明: 1. 属性名称区分大小写; 2. 没有匹配到到属性输出原始字符串。)
 * @return 格式化后的字符串
 * @example  "#1 Name:#Name, Age:#Age".format({Name:"zhangsan", Age:23 }); // 返回："#1 Name:zhangsan, Age:23"
 */
String.prototype.format = function(value) {
	return this.replace(new RegExp('#\\w+', 'gi'), function(match) {
		var name = match.substring(1);
		return value.hasOwnProperty(name) ? value[name] : match;
	});
};

/**
 * 获取字符长度
 * @param chsLength 一个非拉丁文(如中文)占多少个字符，默认为2个 (改数据库时需改这里)
 * @return 字符长度
 * @example "aa哈哈".chsLeng() // 返回: 6
 */
String.prototype.chsLeng = function(chsLength) {
	chsLength = (parseInt(chsLength) >= 0) ? parseInt(chsLength) : 2;
	//去除非拉丁文(如中文)的长度
	var noChsLength = this.replace(new RegExp('[^\x00-\xff]','gm'), "").length;
	//中文长度
	var chineseLength = (this.length - noChsLength) * chsLength;
	return noChsLength + chineseLength;
};

/**
 * 检查字符串是否包含中文，是则返回true，否则返回false (注:空字符串返回 false)
 * @param isAllChinese 是否要求全部都为中文
 * @return 不填 isAllChinese，或者 isAllChinese 为 false 时，只要包含有中文即返回true，不包含一个中文则返回false
 * @return 当 isAllChinese 为 true 时，要求字符串全部都为中文则返回 true，如果有一个不为中文则返回false
 *
 * @example
 *  "aa哈哈".hasChinese() // 返回: true
 *  "aa哈哈".hasChinese(true) // 返回: false
 */
String.prototype.hasChinese = function(isAllChinese) {
	if ("" === this) return false;
	//去除中文的长度
	var noChsLength = this.replace(new RegExp('[^\x00-\xff]','gm'), "").length;
	// \u4E00-\u9FA5 是汉字, \uFE30-\uFFA0 是全角符号, \u3002 是句号， \u201C \u201D 是双引号
	// var noChsLength = this.replace(new RegExp('([\u4E00-\u9FA5]|[\uFE30-\uFFA0]|[\u3002\u201C\u201D])','gm'), "").length;
	return isAllChinese ? (noChsLength === 0) : (noChsLength < this.length);
};

/**
 * 检查字符串是否只由汉字、字母、数字组成
 * @return boolean 如果通过验证返回true,否则返回false (注:空字符串返回 false)
 */
String.prototype.isChineseOrNumberOrLetter = function() {
	return new RegExp("^[0-9a-zA-Z\u4e00-\u9fa5]+$").test(this);
};

/**
 * 检查字符串是否只由数字、26个英文字母或者下划线组成
 * @return boolean 符合返回true,否则返回false (注:空字符串返回 false)
 */
String.prototype.isNumber_Letter = function() {
	return new RegExp("^\\w+$",'g').test(this);
};

/**
 * 检查字符串是否只由数字、26个英文字母组成
 * @return boolean 符合返回true,否则返回false (注:空字符串返回 false)
 */
String.prototype.isNumberLetter = function() {
	return new RegExp("^[A-Za-z0-9]+$",'g').test(this);
};

/**
 * 检查字符串是否为email地址
 * @return boolean 符合返回true,否则返回false (注:空字符串返回 false)
 */
String.prototype.isEmail = function() {
	return new RegExp("^[a-z0-9][a-z0-9\\-_.]*[a-z0-9]+@(([a-z0-9]([a-z0-9]*[-_]?[a-z0-9]+)+\\.[a-z0-9]+(\\.[a-z0-9]+)?)|(([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.(([\\d]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.){2}([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))))$",'gi').test(this.trim());
};

/**
 * 检查字符串是否为日期格式(正确格式如: 2011-03-28 或者 11/3/28, 2011年03月28日, 20111028)
 * @return boolean 符合返回true,否则返回false (注:空字符串返回 false)
 */
String.prototype.isDate = function() {
	// 匹配检查
	var r = this.hasChinese() ?
			this.match(/^(\d{1,4})(年)((0?[1-9])|(1[0-2]))月((0?[1-9])|([12]\d)|(3[01]))日?$/) : // 中文处理
				this.match(/^(\d{1,4})(-|\/|\.)?((0?[1-9])|(1[0-2]))\2((0?[1-9])|([12]\d)|(3[01]))$/);
			if ( r == null ) return false;
			// 日期是否存在检查
			var d = new Date(r[1], r[3]-1, r[6]);
			return ((d.getFullYear()==r[1] || d.getYear()==r[1]) && (d.getMonth()+1)==r[3] && d.getDate()==r[6]);
};

/**
 * 检查字符串是否为时间格式(正确格式如: 13:04:06 或者 21时5分10秒, 210521)
 * @return boolean 符合返回true,否则返回false (注:空字符串返回 false)
 */
String.prototype.isTime = function() {
	// 匹配检查
	var a = this.hasChinese() ?
			this.match(/^(\d{1,2})([时時])(\d{1,2})分(\d{1,2})秒(\d+([毫微纳納诺諾皮可飞飛阿托]秒)?)?$/) : // 中文处理
				this.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})([.]?\d+)?$/);
			if (a == null) return false;
			// 时间检查
			if (a[1]>=24 || a[3]>=60 || a[4]>=60) return false;
			// 如果有“:”来分隔时间,则秒后面的数也要求有“.”来分隔
			if (a[2]==':' && a[5] && a[5].indexOf('.')==-1) return false;
			// 验证成功
			return true;
};

/**
 * 检查字符串是否为日期和时间格式 (正确格式如: 2003/12/05 13:04:06 或者 2001年10月20日10时5分30秒, 20110208230406)
 * @return boolean 符合返回true,否则返回false (注:空字符串返回 false)
 */
String.prototype.isDateTime = function() {
	var dateTimes = this.split(' ');
	// 中文时,可以不用空格隔开日期和时间
	if (dateTimes.length != 2 && this.indexOf('日') != -1) {
		dateTimes = this.split('日');
		dateTimes[0] += '日';
	}
	// 无符号时,可以不用空格隔开日期和时间
	if (dateTimes.length != 2 && this.indexOf(':') == -1
			&& this.indexOf('-') == -1 && this.indexOf('/') == -1 && this.indexOf('.') == -1) {
		// 完整日期和时间
		if (this.length >= 14) {
			dateTimes[0] = this.substr(0, 8);
			dateTimes[1] = this.substr(8);
		}
		// 短日期和时间,认为日期部分为6位
		else {
			dateTimes[0] = this.substr(0, 6);
			dateTimes[1] = this.substr(6);
		}
	}
	// 英文时，必须空格隔开日期和时间
	if (dateTimes.length != 2) return false;
	return (dateTimes[0].isDate() && dateTimes[1].isTime());
};

/**
 * 检查字符串是否为URL地址
 * @return boolean 符合返回true,否则返回false (注:空字符串返回 false)
 */
String.prototype.isUrl = function() {
	return /^(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/.test(this);
};

/**
 * 转换字符串成 Unicode 编码
 * @return 转换后的字符串
 * @example "哈,哈".toUnicode() 返回: "\u54C8\u002C\u54C8"
 */
String.prototype.toUnicode = function() {
	// 注，不会编码的字符：  *  +  -  .  /  @  _  0-9  a-z  A-Z
	return escape(this).
	// 替换中文
	replace(new RegExp('%u[0-9a-f]{4}', 'gim'), function(match) {
		return '\\' + match.substring(1);
	}).
	// 替换英文符号
	replace(new RegExp('%[0-9a-f]{2}', 'gim'), function(match) {
		return "\\u00" + match.substring(1);
	});
};

/**
 * 转换字符串成 Html 页面上显示的编码
 * @return 转换后的字符串
 * @example "<div>".toHtmlCode() 返回: "&lt;div&gt;"
 */
String.prototype.toHtmlCode = function() {
	var div = document.createElement('div');
	var text = document.createTextNode(this);
	div.appendChild(text);
	return div.innerHTML;
};

/**
 * 转换字符串由 Html 页面上显示的编码变回正常编码(以 toHtmlCode 函数对应)
 * @return 转换后的字符串
 * @example "&nbsp;".toTextCode() // 返回: " "
 */
String.prototype.toTextCode = function() {
	// 以下逐一转换
	var sour = this.
	replaceAll("&#37;", "%"). // 百分号
	replaceAll("&lt;", "<", "gim").replaceAll("&#60;", "<"). // 小于号
	replaceAll("&gt;", ">", "gim").replaceAll("&#62;", ">"). // 大于号
	replaceAll("&#39;", "'"). // 单引号
	replaceAll("&#43;", "+"). // 加号
	replaceAll("\n?<br\\s*/?>\n?", "\n", "gim"). // 换行符
	replaceAll("&quot;", '"', "gim").replaceAll("&#34;", '"'). // 双引号
	replaceAll("&nbsp;", " ", "gim").replaceAll("&#160;", " "). // 空格
	replaceAll("(&amp;)|(&#38;)", "&", "gim"); // & 符号,为避免二次转换,最后才转换
	return sour;
};

/**
 * 清除HTML标签
 * @return 清除标签后的内容
 * @example "<div>haha</div>".removeHtmlTag() 返回: "haha"
 */
String.prototype.removeHtmlTag = function() {
	var text = this.trim().
	replaceAll("<!--.*-->", "").  // 清除注释
	replaceAll("</title>", "\n", "gim"). // 标题换行: </title> ==> 换行符
	replaceAll("</tr>", "\n", "gim"). // tr换行: </tr> ==> 换行符
	replaceAll("<[^>]+>", ""). // html标签清除
	toTextCode(); // 转换字符串由 Html 页面上显示的编码变回正常编码
	return text;
};

/**
 * 格式化时间字符串
 * @param format 格式化的字符串(默认为：yyyy-MM-dd HH:mm:ss )
 * @return 格式化时间后的字符串
 * @example new Date().format("yyyy-MM-dd HH:mm:ss.S")
 */
Date.prototype.format = function(format) {
	// 默认显示格式
	format = format || "yyyy-MM-dd HH:mm:ss";

	var o = {
			"M{1,2}" : this.getMonth()+1, // 月份  (返回1~12,或者01~12)
			"d{1,2}" : this.getDate(), // 日期  (返回1~31,或者01~31)
			"[h|H]{1,2}" : this.getHours(), // 小时  (返回1~23,或者01~23)
			"m{1,2}" : this.getMinutes(), // 分钟  (返回1~59,或者01~59)
			"s{1,2}" : this.getSeconds(), // 秒  (返回1~59,或者01~59)
			"q{1,2}" : Math.floor((this.getMonth()+3)/3), // 季度  (返回1~4,或者01~04)
			"S" : this.getMilliseconds() // millisecond  (返回1~999,或者01~99) 注意，“S”只能写一个
	}

	// 年份处理
	if (/([y|Y]+)/.test(format)) {
		format = format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}
	// 逐个处理
	for (var k in o) {
		if (new RegExp("("+ k +")").test(format)) {
			format = format.replace(RegExp.$1, (RegExp.$1.length == 1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length)));
		}
	}
	return format;
};

//opera浏览器 没有 数组的 concat 函数,兼容它
if (!Array.prototype.concat) {
	Array.prototype.concat = function() {
		var array = [];
		for (var i = 0, length = this.length; i < length; i++) array.push(this[i]);
		for (var i = 0, length = arguments.length; i < length; i++) {
			if(arguments[i].constructor == Array) {
				for(var j = 0, arrayLength = arguments[i].length; j < arrayLength; j++) {
					array.push(arguments[i][j]);
				}
			} else {
				array.push(arguments[i]);
			}
		}
		return array;
	};
}

/**
 * 数组的逐个执行
 * @param func 对数组里的每个元素执行的函数
 * @return 返回数组本身,以便使用连缀
 *
 * @example
 *  var arr = [1, 2, 3];
 *  arr.each(function(value, i){
 *      alert(value); // 会把逐个值 alert 处理
 *      alert(this.length); // 这里面的 this 是指被调用的数组 arr
 *  });
 */
Array.prototype.each = function(func) {
	for (var i = 0, length = this.length; i < length; i++) func.call(this, this[i], i);
	return this;
};

/**
 * 清空数组
 * @return 返回本数组(即清空后的情况,可以不接收返回值,原数组已经被清空)
 *
 * @example
 *  var arr = [1, 2, 3];
 *  arr.clear();
 *  alert(arr.length); // 提示为0
 *  alert(arr[0]); // 提示为 undefined
 */
Array.prototype.clear = function() {
	this.length = 0;
	return this;
};

/**
 * 查找数组里的某个值的下标
 * @param value 要查找的值
 * @return 查找到的值的下标,有多个这样的值时返回第一个查找到的下标,找不到则返回 -1
 *
 * @example
 *  var arr = ['a', 'b', 'c'];
 *  arr.indexOf('c'); // 返回 2
 */
Array.prototype.indexOf = function(value) {
	for (var i = 0, length = this.length; i < length; i++) {
		if (this[i] === value) return i;
	}
	return -1;
};

/**
 * 查找数组里的某个值的下标(从后面找起)
 * @param value 要查找的值
 * @return 查找到的值的下标,有多个这样的值时返回最后一个查找到的下标,找不到则返回 -1
 *
 * @example
 *  var arr = ['a', 'b', 'c', 'd', 'c'];
 *  arr.lastIndexOf('c'); // 返回 4
 */
Array.prototype.lastIndexOf = function(value) {
	for (var i = this.length - 1; i > 0; i--) {
		if (this[i] === value) return i;
	}
	return -1;
};

/**
 * 复制数组(浅拷贝)
 * @return 返回复制后的数组
 *
 * @example
 *  var arr = ['a', 'b', 'c', 'd', 'c'];
 *  var arr2 = arr.clone(); // 返回的是另一个数组,对 arr2 的操作不再影响 arr
 */
Array.prototype.clone = function() {
	return [].concat(this);
};

/**
 * 判断数组里是否包含此值
 * @param object 要查找的值, 可以有多个
 * @return 只有一个参数时：包含有则返回 true,没有则返回 false；有多个参数时,要求每一个参数都包含才返回 true,否则返回 false
 *  注：引用对象的判断,只是简单判断引用地址是否相等,不判断里面的值
 *
 * @example
 *  var arr = ['a', 'b', 'c'];
 *  arr.contains('c'); // 返回 true
 *  arr.contains('d'); // 返回 false
 *  arr.contains('c', 'a'); // 返回 true
 *  arr.contains('a', 'd'); // 返回 false
 */
Array.prototype.contains = function(value) {
	for (var i=0; i < arguments.length; i++) {
		if (this.indexOf(arguments[i]) === -1) return false;
	}
	return true;
};

/**
 * 删除数组里的某些值(只删除第一个找到的值)
 * @return 返回数组本身,以便使用连缀
 *
 * @example
 *  var arr = ['a', 'b', 'c', 'd', 'c'];
 *  arr.remove('c'); // 此时的数组删除了第一个'c', 为: ['a', 'b', 'd', 'c']
 *  arr.remove('a', 'd'); // 还可以同时删除多个值, 此时为: ['b', 'c']
 *  arr.remove('e'); // 传入数组里面没有的值,不对数组产生影响,也不会出现异常, 此时为: ['b', 'c']
 */
Array.prototype.remove = function() {
	for (var i = 0; i < arguments.length; i++) {
		var value = arguments[i];
		var index = this.indexOf(value);
		if (index > -1) this.splice(index, 1);
	}
	return this;
};

/**
 * 删除数组里的某些值(删除所有找到的值)
 * @return 返回数组本身,以便使用连缀
 *
 * @example
 *  var arr = ['a', 'b', 'c', 'd', 'c'];
 *  arr.removeAll('c'); // 此时的数组删除了所有的'c', 为: ['a', 'b', 'd']
 *  arr.removeAll('a', 'd'); // 还可以同时删除多个值, 此时为: ['b']
 *  arr.removeAll('e'); // 传入数组里面没有的值,不对数组产生影响,也不会出现异常, 此时为: ['b']
 */
Array.prototype.removeAll = function() {
	var index;
	for (var i = 0; i < arguments.length; i++) {
		var value = arguments[i];
		// 赋值给 index 且判断是否大于 -1, 避免获取两次 index 以提高效率
		while ((index = this.indexOf(value)) > -1) {
			this.splice(index, 1);
		}
	}
	return this;
};

/**
 * 返回没有重复值的新数组,原数组不改变
 * @return 返回过滤重复值后的新数组
 *
 * @example
 *  var arr = ['a', 'b', 'c', 'd', 'c', null];
 *  var arr2 = arr.unique(); // arr2 为: ['a', 'b', 'd', 'c', null]
 */
Array.prototype.unique = function() {
	var result = [];
	for (var i=0,l=this.length; i<l; i++) {
		for (var j=i+1; j<l; j++) {
			if (this[i] === this[j]) j = ++i;
		}
		result.push(this[i]);
	}
	return result;
};

/**
 * 对数组中的每个元素都执行一次指定的函数
 * 并且创建一个新数组，该数组元素是所有回调函数执行时返回值为 true 的原数组元素。原数组不改变
 * @param func 对数组里的每个元素执行的函数
 * @return 以数组形式返回执行函数时返回值为 true 的原数组元素
 *
 * @example
 *  var arr = [1, 23, 54, 67, 42, 66];
 *  var arr2 = arr.filter(function (value, index) {
 *      alert(this.length); // 这里面的 this 是指被调用的数组 arr
 *      return value % 2 === 0; // 返回偶数
 *  });
 *  alert(arr2); // 值为: [54, 42, 66]
 */
Array.prototype.filter = function(func) {
	var result = [], array = this;
	this.each(function(value, index) {
		if (func.call(array, value, index)) result.push(value);
	});
	return result;
};

/**
 * 以数组形式返回原数组中不为 null 与 undefined 的元素。原数组不改变
 * @return 以数组形式返回原数组中不为 null 与 undefined 的元素
 *
 * @example
 *  var arr = ['a', 'b', 'c', 'd', 'c', null];
 *  var arr2 = arr.notNull(); // arr2 为: ['a', 'b', 'c', 'd', 'c']
 */
Array.prototype.notNull = function() {
	return this.filter(function (value) {
		return value != null;
	});
};

/**
 * 返回数组里的最大值
 * 如果数组里有不能直接转成数值的值,或者有 NaN,则返回 NaN；空值按0处理,能转成数值的字符串会被转成数值处理
 * @return 返回数组里的最大值(number类型)
 *
 * @example
 *  var arr = ['66', 44, 32.1, null];
 *  var arr2 = ['66', 44, 32.1, null, 'a'];
 *  alert( arr.max() ); // 值为: 66
 *  alert( arr2.max() ); // 值为: NaN
 */
Array.prototype.max = function() {
	return Math.max.apply(null,this);
};

/**
 * 返回数组里的最小值
 * 如果数组里有不能直接转成数值的值,或者有 NaN,则返回 NaN；空值按0处理,能转成数值的字符串会被转成数值处理
 * @return 返回数组里的最小值(number类型)
 *
 * @example
 *  var arr = ['66', 44, 32.1, null, -1];
 *  var arr2 = ['66', 44, 32.1, null];
 *  alert( arr.min() ); // 值为: -1
 *  alert( arr2.min() ); // 值为: 0
 */
Array.prototype.min = function() {
	return Math.min.apply(null,this);
};

/* -------------------------------------------------------------------
 * 内置对象额外添加函数 end
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ */


/**
 * 获取单选按钮对应的编号和名称
 * @param boxName 单选按钮的name (注:不能根据id来查找)
 * @param init    单选按钮的默认值
 * @return 返回被选中的当选按钮的value
 */
c$.getRadioValue = function(boxName, init) {
	init = init || '';
	var elements = document.getElementsByName(boxName);
	// 如果没有找到所要的单选按钮，则直接返回
	if (!elements) return init;

	// 如果只有一个单选按钮
	if ("radio" === elements.type && elements.checked) {
		return elements.value;
	}
	// 循环检查选中哪个
	for (var i = 0, length = elements.length; i < length; i++) {
		//如果选中此单选按钮
		if ("radio" === elements[i].type && elements[i].checked) {
			return elements[i].value;
		}
	}

	return init;
};

/**
 * checkbox全选: 全部勾上 或者全部不选
 * @param checkboxList checkbox列表,可一个或者多个
 * @param checked 为true则勾上，为false则全部不勾上；没有这参数则以列表的第一个的反选为准
 * @return c$ 对象本身，以支持连缀
 */
c$.selectAll = function(checkboxList, checked) {
	// 没有时选择所有的checkbox
	if (!checkboxList) {
		var inputList = document.getElementsByTagName('INPUT');
		checkboxList = [];
		for (var i = 0, length = inputList.length; i < length; i++) {
			if (inputList[i].type === "checkbox") checkboxList.push(inputList[i]);
		}
		// 没有一个checkbox时,不用再执行
		if ( checkboxList.length == 0 ) return this;
	}
	// 没有这参数时
	if ( typeof(checked) === 'undefined' ) {
		checked = ( "checkbox" === checkboxList.type ) ? (!checkboxList.checked) : (!checkboxList[0].checked);
	}
	// 保证 checked 为 boolean 值
	checked = !!checked;
	// 只有一个時
	if ( "checkbox" === checkboxList.type ) {
		checkboxList.checked = checked;
	}
	// 两个或者两个以上时
	else {
		for ( var i = 0, length = checkboxList.length; i < length; i++ ) {
			checkboxList[i].checked = checked;
		}
	}
	return this;
};

/**
 * 创建一个类
 * @param arg 类里面的属性和方法,其中 init 或者 initialize 属性可作为类的初始化函数
 * @return 扩展后的类(可以不接收参数,原被扩展的类会被修改而引用不变)
 *
 * @example
 *  // 初始化类
 *  var Student = c$.create({
 *      init: function(name, age) {this.name=name; this.age=age;},
 *      show: function(){alert('name: ' + this.name + '  age:' + this.age);}
 *  });
 *  var stu1 = new Student('jack', 23); // new 一个实体类
 *  stu1.show();
 *  var stu2 = new Student('tom', 25); //  再 new 一个实体类
 *  stu2.show();
 */
c$.create = function(arg) {
	function _fun() {
		if (this.init && typeof this.init == 'function') this.init.apply(this, arguments);
		else if (this.initialize && typeof this.initialize == 'function') this.initialize.apply(this, arguments);
	}
	if (arg && typeof arg == 'object') _fun.prototype = arg;
	return _fun;
};

/**
 * 类的扩展
 * @param destination 被扩展的类
 * @param 要扩展的内容
 * @return 扩展后的类(可以不接收参数,原被扩展的类会被修改而引用不变)
 *
 * @example
 *  var a = new Object();
 *  c$.extend(a, {
 *      alertStr: function(str){alert(str);}
 *  });
 *  a.alertStr('要提示的内容'); // 调用
 */
c$.extend = function(destination, source) {
	for (var property in source) {
		destination[property] = source[property];
	}
	return destination;
};

/**
 * 获取对象的所有键的数组
 * @param object 对象
 * @return 对象的所有键的数组
 */
c$.keys = function(object) {
	var keys = [];
	for (var property in object) {
		keys.push(property);
	}
	return keys;
};

/**
 * 获取对象的所有值的数组
 * @param object 对象
 * @return 对象的所有值的数组
 */
c$.values = function(object) {
	var values = [];
	for (var property in object) {
		values.push(object[property]);
	}
	return values;
};

/**
 * 返回某对象的复制(浅拷贝)
 * @param object 对象
 * @return 对象的所有值的数组
 */
c$.clone = function(object) {
	return c$.extend({}, object);
};

/**
 * 将对象转换成字符串
 * @param strValue 包含字符串的对象
 * @return 对象的字符串
 */
c$.toStr = function(strValue) {
	if (strValue || 0 === strValue || false === strValue) return ("" + strValue);
	// 不能转换的返回空字符串
	return "";
};

/**
 * 转成 int 类型
 * @param str 需转换的字符串
 * @return int 数值,转换不成功则返回0
 */
c$.toInt = function(str) {
	//return (parseInt(str, 10) || 0);
	return str >> 0;
};

/**
 * 转成 float 类型
 * @param str 需转换的字符串
 * @return float 数值,转换不成功则返回0
 */
c$.toFloat = function(str) {
	return (parseFloat(str) || 0);
};

/**
 * 把数值转化成指定的数值字符串,指定小数位,可加逗号
 * @param value 需转化的数值
 * @param decimal 小数位。不指定则有小数的默认两位,没小数的不加小数(舍去部分会四舍五入)
 * @param signNumber 数字每隔多少位加一个逗号，默认不加逗号
 * @return 处理后的数值字符串
 * @example c$.toNumberStr('15154514541.6471', 2, 3)  // 返回字符串: 15,154,514,541.65
 */
c$.toNumberStr = function(value, decimal, signNumber) {
	var retValue = "";
	//如果没有value值,赋值为0
	value = value || "0";
	//如果不是数值
	if (false === c$.isNumber(value)) value = "0";

	//去除前后空格,去除数值中的逗号
	value = ("" + value).trim().replaceAll(",");
	//去除非数值，以及0开头的内容
	value = "" + parseFloat(value);

	//分解字符串； number[0]为整数部分， number[1]为小数部分
	var number = value.split(".");
	//如果字符串是个整数
	if (1 === number.length) number[1] = "";
	number[2] = "";

	//如果是负数
	if (0 === value.indexOf("-")) {
		number[0] = number[0].replace("-", "");
		retValue += "-";
	}
	//如果是加号开头
	if (0 === value.indexOf("+")) {
		number[0] = number[0].replace("+", "");
		retValue += "+";
	}

	//如果没有设定小数字，看数值情况，有小数的默认两位，没小数的不加小数
	if (!decimal && 0 !== decimal && value.indexOf(".") > 0) decimal = 2;
	decimal = decimal>>0; // 取整
	//如果是科学计数法的数字
	if (number[1].indexOf("e") > 0) {
		var numberArry = number[1].split("e");
		number[1] = numberArry[0];
		number[2] = "e" + numberArry[1];
		decimal -= parseInt(numberArry[1]);
	}
	//如果指定保留多少位小数
	if (0 < decimal) {
		//给结果补足要求保留的小数字
		for (var i = 0; i < decimal; i++) {
			number[1] += "0";
		}
		//四舍五入
		var tem_number = retValue + number[0] + number[1].substring(0, decimal) + "." + number[1].substring(decimal, number[1].length);
		tem_number = "" + Math.round(tem_number);
		var intLength = (retValue + number[0]).length;
		number[1] = tem_number.substring(intLength, intLength + decimal);
	}

	signNumber = signNumber>>0; // 取整
	//如果需要分隔数字
	if (0 < signNumber) {
		//整数的第一部分的长度
		var tem = number[0].length % signNumber;
		//如果整数部分的长度刚好是signNumber的倍数
		if (0 === tem) tem = signNumber;
		//整数的第一部分
		retValue += number[0].substring(0, tem);
		//整数的其他部分
		for (var i = 1; i < parseInt((number[0].length + signNumber - 1) / signNumber); i++) {
			var j = (i - 1) * signNumber + tem;
			retValue += "," + number[0].substring(j, j + signNumber);
		}
	}
	//如果不需要分隔数字
	else {
		retValue += number[0];
	}

	// 如果有小数则加上
	if ((number[1] + number[2]).length > 0) retValue += "." + number[1] + number[2];

	// 返回数值字符串
	return retValue;
};

/**
 * 获取 form 表单的所有元素(仅 input, checkbox 等可以表单提交的元素)
 * @param formField 表单(没有参数则是整个页面的所有 form),给出的不是 form 元素而是 div,table 之类的元素也可以
 * @return 返回一个数组,里面保存着获取的元素
 */
c$.getElements = function(formField) {
	//储存元素
	var elements = [];
	// 如果传入的是字符串,则根据此字符串获取对象
	if (typeof formField === 'string') formField = c$(formField);
	// 获取指定form的元素
	if (c$.isElement(formField)) {
		if (formField.tagName == 'FORM') {
			elements = formField.elements;
		}
		// 如果给出的不是form,而是div之类的,照样获取里面可提交的元素
		else {
			elements = formField.getElementsByTagName('INPUT');
			var selects = formField.getElementsByTagName('SELECT');
			if (selects && selects.length > 0) {
				for(var i=0, length = selects.length; i<length; i++){
					elements.push(selects[i]);
				}
			}
			var textareas = formField.getElementsByTagName('TEXTAREA');
			if (textareas && textareas.length > 0) {
				for(var i=0, length = textareas.length; i<length; i++){
					elements.push(textareas[i]);
				}
			}
		}
	}
	// 获取form的所有元素
	else {
		var formArray = document.forms;
		for (var i = 0; i < formArray.length; i++) {
			formField = formArray[i];
			for (var j = 0, length = formField.length; j < length; j++) {
				elements.push(formField[j]);
			}
		}
	}
	// 返回元素列表
	return elements;
};

/**
 * 检查窗体是否可送出
 * @param formField 待检查的form,给出的不是 form 元素而是 div,table 之类的元素也可以
 * @param type 提示类型,有： alert(逐步提示,缩写A)、confirm(逐步判断,缩写C)、show(文字显示,缩写S,默认是show)
 *  注: 以 alert 或者 confirm 提示信息时,只要有其中一个验证不正确则不再验证下面,直接返回 false,避免不断地alert；而show则验证完整个form
 *  如果 type 是 show 类型,且需要指定页面显示位置,则可指定显示元素的id或者name,如: type={id:'显示元素的id', name:'显示元素的name'}
 * @return boolean 检查是否通过
 *
 * @example
 *  <form action="#">
 *  <table>
 *     <tr>
 *       <td>名称：<input name="txt1" type="text" checkType="R{请输入名称}S{id:'error1'}" onchange="c$.checkElement(this)"/></td>
 *       <td id="error1">&nbsp;</td>
 *     </tr>
 *     <tr>
 *       <td>金额：<input name="txt2" type="text" checkType="S{id:'error2'}F{max:999.99, min:0, dec:2, msg:'请输入正确的金额'}" onchange="c$.checkElement(this)"/></td>
 *       <td id="error2">&nbsp;</td>
 *     </tr>
 *     <tr>
 *       <td>人数：<input name="txt3" type="text" checkType="S{id:'error3'}R{请输入人数}I{max:999, min:0, msg:'请输入正确的人数'}" onchange="c$.checkElement(this)"/></td>
 *       <td id="error3">&nbsp;</td>
 *     </tr>
 *  </table>
 *  <input type="submit" value="提交" onclick="return c$.checkForm(this.form);" />
 *  </form>
 */
c$.checkForm = function(formField, type) {
	// 类型默认是 show,
	type = type || 'show';
	// 转成小写
	type = typeof(type)=='string' ? type.toLowerCase() : type;

	var return_value = true;
	//元素列表
	var elements = c$.getElements(formField);

	//检查窗体的所有元素
	for (var index=0, length=elements.length; index < length; index++) {
		if (false === c$.checkElement(elements[index], type)) {
			return_value = false;
			// alert 和 confirm 验证需要中断,避免不断地提示
			if (type === 'alert' || type === 'a' || type === 'confirm' || type === 'c') return false;
		}
	}
	//检查通过
	return return_value;
};

/**
 * 检查一个元素
 * @param element 需检查的元素
 * @param type 提示类型,有： alert(缩写A)、confirm(缩写C)、show(文字显示,缩写S,默认是show)
 *  如果 type 是 show 类型,且需要指定页面显示位置,则可指定显示元素的id或者name,如: type={id:'显示元素的id', name:'显示元素的name'}
 * @retrn boolean 检查通过返回true,否则返回false
 *
 * @example
 *  <input id="txt1" name='txt1' type="text" checkType="R{请输入金额}F{max:999.99, min:0, dec:2, msg:'请输入正确的金额'}" />
 *  <input type="submit" value="提交" onclick="return c$.checkElement(document.getElementById('txt1'));" />
 *
 * 说明: 检查类型的关键词需紧跟大括号,不跟大括号则提示“M”的讯息或者默认的讯息;
 * 使用“C”关键词则是选择提示框,点选“确定”可忽略此提示
 * 大括号里面的 msg 属性是对应的提示讯息,需用引号括起来; 大括号里面没有冒号则认为全是提示讯息; 注:不能嵌套大括号
 */
c$.checkElement = function(element, type) {
	//防呆
	if (!element) return true;
	// 类型默认是 show,
	type = type || 'show';
	// 转成小写
	type = typeof(type)=='string' ? type.toLowerCase() : type;

	//所有需检查讯息
	var message = c$.toStr(element.getAttribute("checkType")).trim();
	if (''===message)return true;//防呆
	//获取需检查的类型,去除大括号里面的内容
	var checkType = message.replace(new RegExp("({[^}]*})*", 'g'), "").toUpperCase();
	var value = element.value;

	//验证类型
	//必须输入 (用法: checkType="R{请输入名称}" 或者 checkType="R{msg:'请输入名称'}" 或者 checkType="M{请输入名称}R")
	var R_pos = (checkType.indexOf('R') > -1);

	//整型 (用法: checkType="I{max:100, min:-50, name:'人数'}" 或者 checkType="I{min:-50,max:100,msg:'请输入正确的人数'}")
	// 注:有 name 属性时, msg 属性将不会生效; name属性是提示讯息的名称; 属性 max, min 分别表示最大值最小值(含)
	var I_pos = (checkType.indexOf('I') > -1);

	//浮点型 (用法: checkType="F{max:999.99, min:0, dec:2, name:'金额'}" 或者 checkType="F{dec:2,max:999.99,msg:'请输入正确的金额'}")
	// 注:有 name 属性时, msg 属性将不会生效; name属性是提示讯息的名称; dec 属性表示小数字数限制; 属性 max, min 分别表示最大值最小值(含)
	var F_pos = (checkType.indexOf('F') > -1);

	//验证字母数字 (用法: checkType="E{ID只能是字母和数字}" 或者 checkType="E{msg:'ID只能是字母和数字'}")
	var E_pos = (checkType.indexOf("E") > -1);

	//验证字母数字下划线 (用法: checkType="N{ID只能是字母,数字和下划线}")
	var N_pos = (checkType.indexOf("N") > -1);

	//验证输入长度 (用法: checkType="L{len:100, msg:'名称长度不能超过100'}")
	var L_pos = (checkType.indexOf("L") > -1);

	//验证 email (用法: checkType="@{请输入正确的邮箱地址}")
	var EMail_pos = (checkType.indexOf("@") > -1);

	//验证 日期 (用法: checkType="D{日期输入不正确}" 或者 checkType="D{msg:'日期输入不正确'}")
	var D_pos = (checkType.indexOf("D") > -1);

	//验证 时间 (用法: checkType="T{时间输入不正确}" 或者 checkType="T{msg:'时间输入不正确'}")
	var T_pos = (checkType.indexOf("T") > -1);

	//验证 日期和时间 (用法: checkType="V{时间输入不正确}" 或者 checkType="V{msg:'时间输入不正确'}")
	var V_pos = (checkType.indexOf("V") > -1);

	//验证 URL (用法: checkType="U{URL地址输入不正确}" 或者 checkType="U{msg:'URL地址输入不正确'}")
	var U_pos = (checkType.indexOf("U") > -1);

	// 共享的提示讯息,可以多个验证都提示此一个(用法: M{提示讯息})
	var M_pos = (checkType.indexOf('M') > -1);

	//确认提示框,以提示框的形式提示出来,并可以选择是否忽略此提示
	var C_pos = (checkType.indexOf('C') > -1) || type === 'confirm' || type === 'c';

	// 直接显示到页面,不使用提示框
	// 用法: 建议在函数的type参数上设值,也可以在元素上设定: checkType="R{请输入名称}S{id:'text1', name:'text1'}"  或者 checkType="SR{请输入名称}"
	// 可根据属性 id 或者 name 获取要显示的位置,不写参数则默认显示在节点的后面； 显示内容使用 .innerHTML 追加进去,红色字体显示
	var S_pos = (checkType.indexOf('S') > -1) || type === 'show' || type === 's' || !!(type.id) || !!(type.name);


	var thisFun = arguments.callee;
	/*
	 * 获取所需检查的类型的类别(匿名函数,仅供此函数内部使用; 缓存此函数以减少内存消耗)
	 * @param key 对应的键
	 * @param message 所有需检查讯息
	 * @return Object 所需检查的类型的类别
	 */
	var getCheckTypeObj = thisFun.getCheckTypeObj || (thisFun.getCheckTypeObj = function(key, message) {
		var upMess = message.toUpperCase();
		// 如果此关键词没有跟大括号,返回空类别
		if (upMess.indexOf(key + "{") < 0) return {};
		// 取出对应的大括号的讯息
		var key_mess = message.substring(upMess.indexOf(key + "{")+1);
		key_mess = key_mess.substring(0, key_mess.indexOf('}') + 1);
		try {
			// 将大括号的讯息转成的类别
			eval("var tem_obj = " + key_mess);
			return tem_obj;
		}
		catch (e) {
			// 如果大括号里面的讯息不能转成类别,则认为它全是提示讯息
			return {msg: key_mess.substring(1, key_mess.length - 1)};
		}
	});

	/*
	 * 提示出对应的讯息(匿名函数,仅供此函数内部使用)
	 * @param key 对应的键
	 * @param element 需检查的元素
	 * @param message 所有需检查讯息
	 * @param iniMess 默认的提示讯息
	 * @return boolean 一般都返回false,仅当显示选择框点选“是”时返回true
	 */
	var showDialog = thisFun.showDialog || (thisFun.showDialog = function(key, element, message, C_pos, S_pos, iniMess) {
		// 获取提示讯息
		var alert_message = (getCheckTypeObj(key, message).msg || getCheckTypeObj("M", message).msg || iniMess)  + "!";

		c$.setFocus(element);

		// "C"类型用 confirm 选择框
		if (C_pos) return confirm(alert_message);
		// "S"类型用文字显示到页面
		if (S_pos) {
			var tem_obj = getCheckTypeObj("S", message);
			// 创建一个对象来显示内容
			var font  = document.createElement('font');
			font.color = 'red';
			// 以 element 作为提示元素的标志
			font.element = element;
			font.innerHTML = alert_message;

			var showElement = null;
			if (tem_obj.id) showElement = document.getElementById(tem_obj.id);
			else if (tem_obj.name) showElement = document.getElementsByName(tem_obj.name)[0];
			else if (type.id) showElement = document.getElementById(type.id);
			else if (type.name) showElement = document.getElementsByName(type.name)[0];

			// 没有获取到显示的元素,或者设置id或者name属性时,追加到节点后面
			if (!showElement) {
				document.body.appendChild(font);
				// 插入到元素的后面
				var pos = element.nextSibling;
				pos.parentNode.insertBefore(font, pos);
			}
			else {
				showElement.appendChild(font);
				var br  = document.createElement('br');
				br.element = element;
				showElement.appendChild(br);
			}
		}
		else {
			// 使用 alert 提示出来
			alert(alert_message);
		}
		return false;
	});


	// 页面显示提示信息时,先清除页面的旧显示
	if (S_pos) {
		var tem_obj = getCheckTypeObj("S", message);
		var showElement = null;
		if (tem_obj.id) showElement = document.getElementById(tem_obj.id);
		else if (tem_obj.name) showElement = document.getElementsByName(tem_obj.name)[0];
		else if (type.id) showElement = document.getElementById(type.id);
		else if (type.name) showElement = document.getElementsByName(type.name)[0];

		// 没有获取到显示的元素,或者设置id或者name属性时,追加到节点后面
		if (!showElement) {
			var nextElement = element.nextSibling;
			if (nextElement && nextElement.tagName && nextElement.element === element) {
				nextElement.parentNode.removeChild(nextElement);
			}
		}
		else {
			var childs = showElement.childNodes;
			for (var i=0, length = childs.length; i < length; i++) {
				if (childs[i] && childs[i].tagName && childs[i].element === element) {
					showElement.removeChild(childs[i]); // 删除 font 标签
					showElement.removeChild(childs[i]); // 删除 br 标签
					break;
				}
			}
		}
	}

	//一定要输入
	if (R_pos && "" === value && false === showDialog("R", element, message, C_pos, S_pos, "请输入必要的值")) {
		return false;
	}
	//可以不输入时
	else if ("" === value) {
		return true;
	}
	//验证整形
	if (I_pos) {
		var tem_obj = getCheckTypeObj("I", message);
		if (S_pos) {
			var checkResult = c$.isInt(element, tem_obj.max, tem_obj.min, tem_obj.name, false, S_pos);
			if (true !== checkResult && false === showDialog("I", element, message, C_pos, S_pos, checkResult.errMessage || "请输入正确的整数值")) return false;
		}
		else if (false === c$.isInt(element, tem_obj.max, tem_obj.min, tem_obj.name, false)) {
			//有name属性时按名称提示,这里不用重复提示; 否则取提示讯息
			if (tem_obj.name || false === showDialog("I", element, message, C_pos, S_pos, "请输入正确的整数值")) {
				return false;
			}
		}
	}
	//验证浮点型
	if (F_pos) {
		var tem_obj = getCheckTypeObj("F", message);
		if (S_pos) {
			var checkResult = c$.isNumber(element, tem_obj.max, tem_obj.min, tem_obj.dec, tem_obj.name, false, S_pos);
			if (true !== checkResult && false === showDialog("F", element, message, C_pos, S_pos, checkResult.errMessage || "请输入正确的数值")) return false;
		}
		else if (false === c$.isNumber(element, tem_obj.max, tem_obj.min, tem_obj.dec, tem_obj.name, false)) {
			//有name属性时按名称提示,这里不用重复提示; 否则取提示讯息
			if (tem_obj.name || false === showDialog("F", element, message, C_pos, S_pos, "请输入正确的数值")) {
				return false;
			}
		}
	}
	//验证输入长度
	if (L_pos) {
		var tem_obj = getCheckTypeObj("L", message);
		if (value.length > tem_obj.len && false === showDialog("L", element, message, C_pos, S_pos, "输入的内容超过长度了，请缩短内容")) {
			return false;
		}
	}
	//验证字母数字组合
	if (E_pos && false === value.isNumberLetter() && false === showDialog("E", element, message, C_pos, S_pos, "此处只能输入字母和数字")) return false;
	//验证字母数字下划线组合
	if (N_pos && false === value.isNumber_Letter() && false === showDialog("N", element, message, C_pos, S_pos, "此处只能输入字母、数字和下划线")) return false;
	//email验证
	if (EMail_pos && false === value.isEmail() && false === showDialog("@", element, message, C_pos, S_pos, "请输入正确的邮箱地址")) return false;
	//日期验证
	if (D_pos && false === value.isDate() && false === showDialog("D", element, message, C_pos, S_pos, "请输入正确的日期")) return false;
	//时间验证
	if (T_pos && false === value.isTime() && false === showDialog("T", element, message, C_pos, S_pos, "请输入正确的时间")) return false;
	//日期和时间验证
	if (V_pos && false === value.isDateTime() && false === showDialog("V", element, message, C_pos, S_pos, "请输入正确的时间")) return false;
	//URL验证
	if (U_pos && false === value.isUrl() && false === showDialog("U", element, message, C_pos, S_pos, "请输入正确的URL地址")) return false;

	//检查通过
	return true;
};

/**
 * 整数检查，判断对象是否为数字(包括负数、科学记数法、逗号分隔的数)
 * @param element 需检查的对象,或者是字符串
 * @param max 最大值(含), 默认为 2147483647
 * @param min 最小值(含), 默认为 -2147483648
 * @param name 提示的讯息的名称
 * @param isMust 是否可以为空,true表示不可以为空, 否则可为空
 * @param isAlert 是否以alert提示出错信息,为true时返回出错信息但不提示,否则以alert提示。验证没错时,不管 isAlert 是什么值都将返回 true
 *        注： isAlert 为false或者没有这参数时,直接 alert 提示出错信息,返回结果是 true 或 false。
 *        但 isAlert 为true,且出错时,将返回结果对象：{result:true|false, errMessage:'出错信息'}
 * @return boolean 验证符合则返回true,否则返回false
 * @example  if(c$.isInt(document.getElementById("amt"), 9999999, -9999999, "人数", flase))...
 */
c$.isInt = function(element, max, min, name, isMust, isAlert) {
	// 没有最大值时,默认为整型的最大值
	if (typeof(max) !== 'number') {
		max = 2147483647;
	}
	// 没有最大值时,默认为整型的最大值
	if (typeof(min) !== 'number') {
		min = -2147483648;
	}
	return c$.isNumber(element, max, min, 0, name, isMust, isAlert);
};

/**
 * 数字检查，判断对象是否为数字(包括负数、科学记数法、逗号分隔的数)
 * @param element 需检查的对象,或者是字符串
 * @param max 最大值(含)
 * @param min 最小值(含)
 * @param decimal 小数多少位
 * @param name 提示的讯息的名称
 * @param isMust 是否可以为空,true表示不可以为空, 否则可为空
 * @param isAlert 是否以alert提示出错信息,为true时返回出错信息但不提示,否则以alert提示。验证没错时,不管 isAlert 是什么值都将返回 true
 *        注： isAlert 为false或者没有这参数时,直接 alert 提示出错信息,返回结果是 true 或 false。
 *        但 isAlert 为true,且出错时,将返回结果对象：{result:true|false, errMessage:'出错信息'}
 * @return boolean 验证符合则返回true,否则返回false
 *
 * @example if(c$.isNumber(document.getElementById("amt"), 9999999.999, -9999999.999, 3, "金额", flase))...
 */
c$.isNumber = function(element, max, min, decimal, name, isMust, isAlert) {
	var thisFun = arguments.callee;
	//出错时: 提示讯息,设定焦点,返回true
	var doError = thisFun.doError || (thisFun.doError = function(msg, name, element, isAlert) {
		var tem_name = name || '此项';
		msg = msg.format({ name:tem_name });
		// 有 isAlert 时不提示，但返回出错结果
		if (isAlert) return {result:false, errMessage:msg};
		// 有 name 属性时提示讯息,没有则不提示
		if (name) {
			c$.setFocus(element);
			alert(msg);
		}
		return false;
	});

	//获取字符串
	var str = element.value || element.innerHTML || "" + element;
	//去除空白; 去除逗号; 转成大写
	str = str.replace(/\s/gi, '').replace(/[,]/gi, '').toUpperCase();

	//不可以为空; 提示讯息: 请输入 name !
	if (true === isMust && "" === str) return doError("请输入 #name !", name, element, isAlert);
	//可以为空
	if (!isMust && "" === str) return true;

	//是否为数值; 提示讯息: name 不是正确的数字!
	if (!str.match(/^[+-]?\d+([.]?\d*)([eE][+-]\d+)?$/g)) return doError("#name 不是正确的数字!", name, element, isAlert);
	// 如果没有指定最大值,不可以超过数值的最大值
	if (0 !== max && !max) max = Number.MAX_VALUE;
	//判断最大值,提示讯息: name 不能大于 max
	if (parseFloat(str) > parseFloat(max)) return doError("#name 不能大于 " + max, name, element, isAlert);
	// 如果没有指定最小值,不可以小于数值的最小值
	if (0 !== min && !min) min = -1 * Number.MAX_VALUE;
	//判断最小值,提示讯息: name 不能小于 min
	if (parseFloat(str) < parseFloat(min)) return doError("#name 不能小于 " + min, name, element, isAlert);

	//小数判断
	decimal = parseInt(decimal);
	if (decimal >= 0 && (str.indexOf(".") > -1 || str.indexOf("E") > -1)) {
		//获取小数点后的数字
		var val = str.replace(/^[+-]?\d+[.]/g, '');
		val = val.replace(/0*([E][+-]\d+)?$/g, '');
		//小数长度
		var decimalLength = val.length;

		//如果是科学记算法
		if (str.indexOf("E") > -1) {
			decimalLength = val.length - parseInt(str.substring(str.indexOf("E") + 1, str.length));
		}
		// 要求整数时; 提示讯息: name 必须是整数
		if (decimal === 0 && (decimalLength > 0 || (str.indexOf(".") > -1 && str.indexOf("E") == -1))) {
			return doError("#name 必须是整数!", name, element, isAlert);
		}
		//小数位判断; 提示讯息: name 请保留 decimal 位小数
		if (decimalLength > decimal) {
			return doError("#name 请保留 " + decimal + " 位小数!", name, element, isAlert);
		}
	}
	//检验通过
	return true;
};

/**
 * 判断对象是否函数
 * 说明：因为 typeof 判断可能会把 dom 对象、正则等判断为 function，故此做严谨判断
 * @param fn 需判断的对象
 * @return 是函数则为 true, 否则为 false
 */
c$.isFunction = function(fn) {
	return (!!fn && !fn.nodeName && fn.constructor != String && fn.constructor != RegExp && fn.constructor != Array && /function/i.test( fn + "" ));
};

/**
 * 判断对象是否页面元素
 * @param obj 需判断的对象
 * @return 是页面元素则为 true, 否则为 false
 */
c$.isElement = function(obj) {
	return !!(obj && obj.nodeType == 1);
};

/**
 * 清除form里各元素的值
 * @param form 需操作的form元素对象,默认所有form,给出的不是 form 元素而是 div,table 之类的元素也可以
 * @return c$ 对象本身，以支持连缀
 */
c$.clearForm = function(form) {
	//元素列表
	var elements = c$.getElements(form);

	// 逐个元素处理
	for (var i = 0, length = elements.length; i < length; i++) {
		var tagName = elements[i].tagName.toLowerCase();
		if (tagName === "textarea" || tagName === "select") {
			elements[i].value = "";
			continue;
		}

		// 处理 input
		// "button", "submit", "reset", "hidden", "image" 类型的不处理
		if (!elements[i].type) continue;
		var type = elements[i].type.toLowerCase();
		// 选择项
		if ("checkbox" === type || "radio" === type) elements[i].checked = false;
		// 单行输入框, 多行输入框, 下拉选单
		else if ("text" === type || "password" === type) elements[i].value = "";
		// 文件上传框
		else if ("file" === type) {
			//elements[i].value = ""; // firfox
			//elements[i].outerHTML = elements[i].outerHTML; // IE
			c$.clearFileInput(elements[i]); // 上面两行的写法也可以,只是改用这种更加好
		}
	}
	return this;
};

/**
 * 清空file的值
 * @param file file元素
 * @return c$ 对象本身，以支持连缀
 */
c$.clearFileInput = function(file) {
	// 建立临时form来实现
	var form = document.createElement('form');
	document.body.appendChild(form);
	// 记住file在旧窗体中的地址
	var pos = file.nextSibling;
	form.appendChild(file);
	form.reset();
	pos.parentNode.insertBefore(file,pos);
	document.body.removeChild(form);
	return this;
};

/**
 * 过滤数字(不符合的不让输入)
 * @param isFloat 是否允许输入一个小数点
 * @param event 兼容 IE 和 FireFox 的事件
 * @example <input type="text" name="stdcost" onkeydown="return c$.inputNumber(true, event);"/>
 */
c$.inputNumber = function(isFloat, event) {
	event = event || window.event;
	// 获取事件源
	var source = event.target || event.srcElement;
	// 不允许 shift 键
	if ( event.shiftKey === true) return false;

	var keyCode = event.charCode || event.keyCode;
	// 只允许输入数字、删除、左右键; 小数时可输入一个小数点
	if ( (keyCode >= 48 && keyCode <= 57) || (keyCode >= 96 && keyCode <= 105) ||
			keyCode === 8  || keyCode === 46 || keyCode === 39 || keyCode === 37 ||
			(isFloat && (keyCode === 110 || keyCode === 190) && source.value.length>0 && source.value.indexOf(".") == -1)
	) return true;

	return false;
};

/**
 * 过滤字母(不符合的不让输入)
 * @param event 兼容 IE 和 FireFox 的事件
 * @example <input type="text" name="stdcost" onkeydown="return c$.inputLetter(event);"/>
 */
c$.inputLetter = function(event) {
	event = event || window.event;
	// 不允许 ctrl 键和 shift 键
	if ( event.ctrlKey === true || event.shiftKey === true) return false;

	var keyCode = event.charCode || event.keyCode;
	// 只允许输入字母、删除、左右键
	if ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 37 || keyCode == 39 || keyCode == 46) return true;

	return false;
};

/**
 * 过滤字母数字(不符合的不让输入)
 * @param event 兼容 IE 和 FireFox 的事件
 * @example <input type="text" name="stdcost" onkeydown="return c$.inputNumLetter(event);"/>
 */
c$.inputNumLetter = function(event) {
	event = event || window.event;
	// 不允许 ctrl 键和 shift 键
	if ( event.ctrlKey === true || event.shiftKey === true) return false;

	var keyCode = event.charCode || event.keyCode;
	// 只允许输入字母、数字、删除、左右键
	if ((keyCode >= 65 && keyCode <= 90) ||
			(keyCode >= 96 && keyCode <= 105) || (keyCode >= 48 && keyCode <= 57) ||
			keyCode == 8 || keyCode == 37 || keyCode == 39 || keyCode == 46
	) return true;

	return false;
};

/**
 * 设定按钮是否可用
 * @param isDisabled 是否不可用,true为不可用，false为可用(默认可用)
 * @param dom Dom对象，默认是 document
 * @return c$ 对象本身，以支持连缀
 *
 * @example
 *  c$.setButtonUsed(true);  // 设置页面上所有的按钮都不可用
 *  c$.setButtonUsed();  // 恢复页面上所有的按钮都可用
 */
c$.setButtonUsed = function(isDisabled, dom) {
	isDisabled = isDisabled || false;
	dom = dom || document;

	//INPUT对象
	var obj = dom.getElementsByTagName("INPUT");
	//INPUT对象判断
	for (var i = 0, length = obj.length; i < length; i++) {
		var type = obj[i].type.toUpperCase();
		if (type == "BUTTON" || type == "SUBMIT" || type == "RESET") {
			obj[i].disabled = isDisabled;
		}
	}

	//BUTTON对象
	var obj = dom.getElementsByTagName("BUTTON");
	//INPUT对象判断
	for (var i = 0, length = obj.length; i < length; i++) {
		obj[i].disabled = isDisabled;
	}
	return this;
};


/**
 * 设置元素可用或者不可用
 * @param objEle 元素对象(要设置多个元素可传此参数为数组,如果元素是iframe,则改变整个iframe)
 * @param canUse 是否可用,true为可用，false为不可用(默认不可用)
 * @return c$ 对象本身，以支持连缀
 *
 * @example
 *  c$.setElementDisable(document.getElementById('text1'));  // 设置id为text1的元素不可用
 *  c$.setElementDisable(document.getElementById('text1'),true);  // 恢复id为text1的元素可用
 */
c$.setElementDisable = function(objEle, canUse) {
	// 防呆
	if (!objEle || (!objEle.tagName && !objEle.length)) return this;
	// 设置是否可用(默认不可用)
	var disabled = !canUse;

	if (objEle.tagName) {
		var tagName = objEle.tagName.toLowerCase();
		// 输入框,可用而不可写
		if ("textarea" === tagName) {
			objEle.disabled = false;
			objEle.readOnly = disabled;
		}
		// 图片, 下拉菜单, 选择框
		else if ("img" === tagName || 'button' === tagName || 'select' === tagName) {
			objEle.disabled = disabled;
		}
		// A 标签
		else if ("a" === tagName) {
			objEle.disabled = disabled;
			// 修改点击事件
			if (disabled) {
				// 有 onclick 事件，则先备份着
				if (objEle.onclick) objEle.onclick_bak = objEle.onclick;
				// 修改点击事件
				objEle.onclick = function(){return false;};
			}
			// 让 A 可用时,让它可点击，并还原点击事件
			else {
				// 有备份着 onclick 事件，则还原
				if (objEle.onclick_bak) {
					objEle.onclick = objEle.onclick_bak;
					objEle.onclick_bak = null;
				}
				else {
					objEle.onclick = null;
				}
			}
		}
		// iframe 框架,设置里面的全部元素
		else if ("iframe" === tagName) {
			var dom = objEle.contentDocument || objEle.contentWindow.document;
			// 存在跨域问题时, iframe 的 document 不可访问
			if (dom) c$.setDomDisable(canUse, dom);
		}
		// input 元素
		else if (objEle.type) {
			var type = objEle.type.toLowerCase();
			// 输入框,可用而不可写
			if ("text" === type) {
				objEle.disabled = false;
				objEle.readOnly = disabled;
			}
			// 选择框,按钮
			else if ("radio" === type || "checkbox" === type || "button" === type || "submit" === type || "reset" === type) {
				objEle.disabled = disabled;
			}
		}
	}
	// 数组，要递归
	else if (objEle.length) {
		for (var i = 0, length = objEle.length; i < length; i++) {
			c$.setElementDisable(objEle[i], canUse);
		}
	}
	return this;
};


/**
 * 设置整个页面的元素都可用或者都不可用 (对frame,iframe里面的元素暂未处理)
 * @param canUse 是否可用,true为可用，false为不可用(默认不可用)
 * @param dom Dom对象，默认是 document
 * @param notChangeArr 不需要设置的元素的数组
 * @return c$ 对象本身，以支持连缀
 *
 * @example
 *  c$.setDomDisable();  // 设置整个页面所有的元素都不可用
 *  c$.setDomDisable(true);  // 恢复整个页面所有的元素都可用
 *  c$.setDomDisable(false, document, [document.getElementById('btn'), document.getElementById('btn2')]); // 除id为btn,btn2的两个元素外，设置整个页面不可用
 */
c$.setDomDisable = function(canUse, dom, notChangeArr) {
	dom = dom || window.document;
	notChangeArr = notChangeArr || [];
	var elements = dom.getElementsByTagName("*");
	// 逐个元素遍历修改
	out: for (var i = 0, length = elements.length; i < length; i++ ) {
		if (!elements[i] || !elements[i].tagName) continue;
		// 页面上不需更改的元素
		for (var j = 0, len = notChangeArr.length; j < len; j++) {
			// continue到外循环
			if (elements[i] === notChangeArr[j]) continue out;
		}
		c$.setElementDisable(elements[i], canUse);
	}
	return this;
};

/**
 * 设置页面覆盖层
 * @param flag 为true则覆盖,页面不可操作； 否则取消覆盖，页面可操作
 * @param zIndex 覆盖层的z-index,默认99999
 * @return c$ 对象本身，以支持连缀
 *
 * @example
 *  c$.cover();  // 取消页面的覆盖,页面可操作
 *  c$.cover(true);  // 覆盖整个页面,页面不可操作
 */
c$.cover = function(flag, zIndex) {
	var thisFun = arguments.callee;
	// thisFun.overlay 作为覆盖层对象,是这函数的全局变量
	// 覆盖
	if (flag === true) {
		// 隐藏滚动条
		var htmls = document.getElementsByTagName("html");
		for (var i = 0, length = htmls.length; i < length; i++) {
			htmls[i].style.overflow = "hidden";
		}
		// 不存在覆盖层对象,则创建
		if (!thisFun.overlay) {
			thisFun.overlay = document.createElement("div");
			// 覆盖层样式
			var cssText = "position:absolute;";
			cssText += "width:" + Math.max(document.body.scrollWidth, document.body.clientWidth) + "px;";
			cssText += "height:" + Math.max(document.body.scrollHeight, document.body.clientHeight) + "px;";
			cssText += "left: 0px;";
			cssText += "top: 0px;";
			cssText += "background-color: #D4D4D4;";
			cssText += "filter:alpha(opacity=50);";
			cssText += "-moz-opacity:0.50;";
			cssText += "opacity:0.50;";
			thisFun.overlay.style.cssText = cssText;
		}
		thisFun.overlay.style.zIndex = zIndex || 99999;
		document.body.appendChild(thisFun.overlay);
	}
	// 取消覆盖
	else {
		// 不存在覆盖层对象,则不再操作
		if (!thisFun.overlay) return this;
		thisFun.overlay.parentNode.removeChild(thisFun.overlay);
		// 恢复滚动条
		var htmls = document.getElementsByTagName("html");
		for (var i = 0, length = htmls.length; i < length; i++) {
			htmls[i].style.overflow = "";
		}
	}
	return this;
};

/**
 * 打开窗口(没有高和宽参数时默认全屏)
 * @param url 窗口地址
 * @param name 窗口名称
 * @param width 窗口宽度
 * @param height 窗口高度
 * @return 返回打开的子窗口对象
 * @example c$.openWin('../common/Member.html?pform=form1&retid=Memid&retname=MemName', '');
 */
c$.openWin = function(url, name, width, height) {
	// 默认宽度和高度为全屏
	width = width || window.screen.width;
	height = height || window.screen.height;
	// 让窗口显示在屏幕中间
	var top = (window.screen.height - height)/2;
	var left = (window.screen.width - width)/2;
	name = name || "";
	var param = 'scrollbars=yes,top=' + top + ',left=' + left + ',width=' + width + ',height=' + height + ',resizable=yes';
	return window.open(url, name, param);
};

/**
 * 关闭窗口(IE上的关闭窗口时不提示)
 * @param win 窗口对象
 * @return c$ 对象本身，以支持连缀
 */
c$.closeWin = function(win) {
	win = win || window;
	win.opener = null; // 关闭IE6不提示
	win.open("","_self"); // 关闭IE7不提示
	//关闭窗口
	win.close();
	return this;
};

/**
 * 删除节点
 * @param element 要删除的元素
 * @return c$ 对象本身，以支持连缀
 * @example c$.removeElement(document.getElementById('text1')) // 删除id为text1的节点
 */
c$.removeElement = function(element) {
	if (element && element.nodeName) {
		element.parentNode.removeChild(element);
	}
	return this;
};

/**
 * 给网址加上时间戳,避免浏览器缓存(已经有时间戳的，会修改时间戳)
 * @param url 网址,没有时使用所在页面的地址
 * @param flag 时间戳的参数名称,默认用 timeStamp
 *
 * @example
 *  c$.addTimeStamp('http://localhost/index.html') // 返回字符串： http://localhost/index.html?timeStamp=1315377347731
 *  c$.addTimeStamp('http://localhost/index.html?a=1&timeStamp=1315377347731')  // 返回字符串：http://localhost/index.html?a=1&timeStamp=1315388347762
 */
c$.addTimeStamp = function(url, flag) {
	url = url || window.location.href;
	// 时间戳的参数名称
	flag = flag || 'timeStamp';
	// 判断网址是否已经有时间戳,有则替换掉旧的时间戳
	var flag_reg = new RegExp('([?&])' + flag + '=\\d*');
	if (flag_reg.test(url)) {
		return url.replace(flag_reg, RegExp.$1 + flag + '=' + new Date().getTime());
	}
	// 给网址增加时间戳参数
	url += (url.indexOf("?") > 0) ? "&" : "?";
	url += flag + "=" + new Date().getTime();
	return url;
};

/**
 * 获取URL里面的网站域名
 * @param url 网址；没有参数时默认使用所在网页的网址
 * @return 返回网站的域名字符串；不是有效网址时返回空字符串
 */
c$.getHost = function(url) {
	// 没有参数时，默认用本站的
	url = url || window.location.href;
	// 用正则表达式来匹配
	var match = url.match(/.*\:\/\/([^\/]*).*/);
	// 能匹配则返回匹配的内容
	if (match) return match[1];
	// 无法匹配(不是有效的网址)，则返回 null
	return "";
};

/**
 * 将 JSON 对象内容转成字符串(注：中文会被url转码)
 * @param obj 被转换的JSON对象
 * @param key2lowerCase 是否需要将key转成小写,为true则转成小写，否则不转
 * @return 返回字符串形式的json内容
 * @example c$.json2str({a:1, b:[1,'2',3], c:'哈哈'}) // 返回字符串: '{"a":1,"b":[1,"2",3],"c":"%E5%93%88%E5%93%88"}'
 */
c$.json2str = function (obj, key2lowerCase) {
	var arr = [];
	// undefined, null, false, 0, NaN, ''
	if (!obj) return "" + obj;
	// 转换字符串
	var format = function(value) {
		// 对字符串的处理，URI转码, 以便提交数据
		if (typeof value === 'string') return '"' + encodeURIComponent(value) + '"';
		// 处理 日期
		if (value && value.constructor === Date) return '"' + value.format() + '"';
		// 处理 数组
		if (value && value.constructor === Array) {
			// 使用临时变量，避免修改源数据
			var temArr = [];
			for (var i=0; i < value.length; i++) {
				if (typeof(value[i]) == 'function') continue; // 自添加的函数,不用转换
				temArr.push(format(value[i]));
			}
			return '[' + temArr.join(',') + ']';
		}
		// 如果是 函数
		if (typeof value === 'function') try { return format(value());}catch(e){ return value + ""; };
		// 如果是 object, 这里认为是 json，递归调用
		if (typeof value === 'object' && value != null) return c$.json2str(value, key2lowerCase);
		// undefined, null, bool, number 类型，直接返回
		return value;
	}
	// bool, number, string, function, 数组
	if (typeof obj !== 'object' || obj.constructor === Array) return format(obj);
	for (var key in obj) {
		// 将key转成小写
		var tem_key = key2lowerCase ? ("" + key).toLowerCase() : key;
		arr.push('"' + encodeURIComponent(tem_key) + '":' + format(obj[key]));
	}
	return '{' + arr.join(',') + '}';
};

/**
 * 分解URL请求参数
 * @param href 网址；没有参数时默认使用所在网页的网址
 * @param key2lowerCase 是否需要将key转成小写,为true则转成小写，否则不转(默认不干涉)
 * @return 返回json形式的参数内容
 * @example c$.getRequestParams("http://localhost/index.html?d2d=哈哈&dd=oo111") // 返回对象: {d2d:'哈哈', dd:'oo111'}
 */
c$.getRequestParams = function(href, key2lowerCase) {
	href = href || window.location.href;
	var result = {};
	var regex = /(\w+)=([^&]+)/gi;
	var ms = href.match(regex);
	if (ms == null) return result;

	for(var i = 0; i < ms.length; i++) {
		var ns = ms[i].match(regex);
		var key = RegExp.$1;
		key = key2lowerCase ? ("" + key).toLowerCase() : key;
		try { result[key] = decodeURIComponent(RegExp.$2); }catch(e){}
	}

	return result;
};

/**
 * 对链接进行 UTF-8 编码
 * @param href 网址; 没有网址返回空字符串
 * @param key2lowerCase 是否需要将key转成小写,为true则转成小写，否则不转(默认不干涉)
 * @return 返回转编码后的网址
 * @example c$.encodeUrl("http://localhost/index.html?d2d=哈哈&dd=oo111") // 返回: "http://localhost/index.html?d2d=%E5%93%88%E5%93%88&dd=oo111"
 */
c$.encodeUrl = function(href, key2lowerCase) {
	return c$.toQueryStr(null, href, key2lowerCase);
};

/**
 * 把对象格式化成 URL 的参数形式
 * @param obj 需要转成参数的对象
 * @param href 网址; 没有网址则只返回格式化后的参数部分,有网址则拼接到网址上(还会修改网址上原有的值)
 * @param key2lowerCase 是否需要将key转成小写,为true则转成小写，否则不转(默认不干涉)
 * @return 返回编码后的字符串
 * @example
 *  c$.toQueryStr({d2d:'看看', b:2}, "http://localhost/index.html?d2d=哈哈&dd=oo111") // 返回: "http://localhost/index.html?d2d=%E7%9C%8B%E7%9C%8B&dd=oo111"
 *  c$.toQueryStr({d2d:'哈哈', b:2}) // 返回: "d2d=%E5%93%88%E5%93%88&b=2"
 */
c$.toQueryStr = function(obj, href, key2lowerCase) {
	if (!href || typeof href != 'string') {
		href = "";
	}
	// 把网址上的参数拼接到 obj 类里面
	else {
		if (!obj || typeof obj != 'object') {
			obj = c$.getRequestParams(href, key2lowerCase);
		} else {
			obj = c$.extend(c$.getRequestParams(href, key2lowerCase), obj);
		}
	}

	// 截取出网址(去掉参数部分)
	var index = href.indexOf("?");
	if (index > 0)
		href = href.substring(0, index) + '?';

	var parts = [];
	for (var key in obj) {
		key = key2lowerCase ? ("" + key).toLowerCase() : key;
		parts.push(encodeURIComponent(key) + '=' + encodeURIComponent(obj[key]));
	}
	href += parts.join('&');
	return href;
};

/**
 * 获取URL的参数
 * @param name 要获取的参数名称
 * @return String类型 返回获取到的值,没有对应的键时返回 null
 */
c$.getRequestValue = function(name) {
	var res = new RegExp( "[\\?&]" + name + "=([^&#]*)" ).exec( window.location.href );
	return res ? res[1] : null;
};

/**
 * 添加 onload 事件(多次调用此函数时会保证各函数的执行顺序)
 * @param fun 要添加到 onload 事件的函数
 * @param win 要添加 onload 事件的窗口对象,默认为 window
 * @return c$ 对象本身，以支持连缀
 */
c$.addOnloadFun = function(fun, win) {
	if (!fun || typeof fun !== 'function') return this;
	win = win || window;

	var thisFun = arguments.callee;
	// thisFun.funList 作为本函数的全局变量,保证多次调用本函数时可以共用
	thisFun.funList = thisFun.funList || [];
	thisFun.funList.push(fun);
	if (thisFun.funList.length > 1) return this;

	var doReady = function () {
		var fn,  i = 0,  readyList;
		if (thisFun.funList) {
			// 为了减少变量查询带来的性能损耗，将它赋值给本地变量
			readyList = thisFun.funList;
			// 释放引用
			thisFun.funList = null;
			while ( (fn = readyList[ i++ ]) ){
				fn.call( win );
			}
		}
	};
	// IE
	if (win.attachEvent) {
		win.attachEvent('onload', doReady);
	}
	// firfox
	else if (win.addEventListener) {
		win.addEventListener('load', doReady, false);
	}

	return this;
};

/**
 * 获取鼠标的坐标
 * @return 返回鼠标位置,形式如：{x: 531, y: 26}
 */
c$.getMousePos = function(ev) {
	ev = ev || window.event;
	var getX = 0;
	var getY = 0;
	// firfox
	if (typeof(ev.pageX) == 'number') {
		getX = 'ev.pageX';
		getY = 'ev.pageY';
	}
	// IE
	else if (document.body) {
		getX = 'ev.clientX + document.body.scrollLeft - document.body.clientLeft';
		getY = 'ev.clientY + document.body.scrollTop - document.body.clientTop';
	}
	// 重置函数,以提高效率,不用每次都判断
	var funstr = 'c$.getMousePos = function(ev) {';
	funstr += '   ev = ev || window.event;';
	funstr += '   return { x: ' + getX + ', y: ' + getY + ' };';
	funstr += '};';
	eval(funstr);
	// 执行此函数,上面已经重置了此函数
	return c$.getMousePos(ev);
};

/**
 * 获取元素位置
 * @param element 元素
 * @return 返回元素位置,形式如：{left: 531, top: 26}
 */
c$.getElementPos = function(element) {
	var left = 0, top = 0;
	do {
		left += element.offsetLeft;
		top += element.offsetTop;
		// 循环追加获取父节点的位置
	} while (element = element.offsetParent);
	return {left: left, top: top};
};

/**
 * 创建一个哈希表对象
 * @return 所创建的哈希表对象
 *
 * @example
 *  var ht = c$.HashTable();  // 创建一个哈希表对象
 *  ht.add('id', 55);   // 添加值
 *  ht.set('id', 66);   // 设值
 *  ht.get('id');       // 取值,没有此值时返回 null
 *  ht.remove('id');    // 删除值
 *  ht.clear();         // 清空哈希表
 *  ht.contains('id');  // 判断是否包含此键,返回 true 或者 false
 *  ht.containsValue(55); // 判断是否包含此值,返回 true 或者 false
 *  ht.count();         // 统计此哈希表里面有多少元素,返回一个整数
 *  ht.keys();          // 返回一个数组,里面包含此哈希表里面所有的 key
 *  ht.toString();      // 返回一个此哈希表的 key 和 value 格式化后的字符串,如: '{"a":1,"b":"哈哈"}'
 *  ht.clear().add('id', 55).set('name', 'myName').remove('age'); // add,set,remove,clear 函数支持连缀
 */
c$.HashTable = function() {
	// 分别保存 key 和 value
	// 原本是用 Object 来保存, 但如果 Object 被扩展了,将会影响结果,且 opera 的 Object 总是含有一个 event,最终改用数组。
	var keyArr = [], valueArr = [];

	// 返回一个哈希对象
	return {
		// 添加一个元素
		add : function(key, value) {
			var index = keyArr.indexOf(key);
			// 没有此值时添加
			if (index === -1) {
				keyArr.push(key);
				valueArr.push(value);
			}
			// 有此值时修改
			else {
				valueArr[index] = value;
			}
			// 返回自身,以便连缀
			return this;
		},

		// 删除一个元素
		remove : function(key) {
			var index = keyArr.indexOf(key);
			if (index > -1) {
				keyArr.remove(key);
				valueArr.splice(index, 1);
			}
			return this;
		},

		// 获取一个元素
		get : function(key) {
			var index = keyArr.indexOf(key);
			if (index > -1) {
				return valueArr[index];
			}
			return null;
		},

		// 修改一个元素
		set : function(key, value) {
			return this.add(key, value);
		},

		// 获取所有的 key
		keys : function() {
			// 返回一个复制的 key 数组,以免被外部修改
			return keyArr.clone();
		},

		// 清空
		clear : function() {
			keyArr.clear();
			valueArr.clear();
			return this;
		},

		// 判断是否包含此键
		contains : function(key) {
			return keyArr.contains(key);
		},

		// 判断是否包含此值
		containsValue : function(value) {
			return valueArr.contains(value);
		},

		// 统计
		count : function() {
			return keyArr.length;
		}

		// 格式化
		,toString : function() {
			var arr = [];
			for (var i = 0, n = keyArr.length; i < n; i++) {
				arr.push('"' + encodeURIComponent(keyArr[i]) + '":' + c$.json2str(valueArr[i]));
			}
			return '{' + arr.join(',') + '}';
		}
	};
};

/**
 * 设定iframe适应高度(必须在 iframe元素加载后执行,如在 onload 里面执行,否则无效)
 * @param iframe iframe元素
 * @return c$ 对象本身，以支持连缀
 * @example <iframe src='test.html' onload='c$.setIframeHeight(this)' />  // 加载 iframe 时，让它自动调整高度
 */
c$.setIframeHeight = function(iframe) {
	iframe = iframe || document.getElementsByTagName("iframe")[0] || window.parent.document.getElementsByTagName("iframe")[0];
	if ( !iframe ) return this;
	// IE
	if ( iframe.Document && iframe.Document.body.scrollHeight ) {
		iframe.height = iframe.Document.body.scrollHeight;
	}
	// Firefox
	else if ( iframe.contentDocument && iframe.contentDocument.documentElement ) {
		iframe.height = iframe.contentDocument.documentElement.scrollHeight + 'px';
		//jQuery(iframe).height(jQuery(iframe.contentDocument.documentElement).height());
	}
	return this;
};

/**
 * 添加收藏
 * @param sURL 网站地址
 * @param sTitle 网站名称
 * @return c$ 对象本身，以支持连缀
 */
c$.addFavorite = function(sURL, sTitle) {
	sURL = sURL || window.location.href;
	sTitle = sTitle || document.title;
	var fns = [
	           function () { window.external.addFavorite(sURL, sTitle);},// IE
	           function () { window.sidebar.addPanel(sTitle, sURL, "");},// firefox (仅当地址为 ftp://, http:// 开头时可以正常运行)
	           ];
	for (var i=0, n=fns.length; i < n; i++) {
		try { fns[i](); return this; }catch(e){}
	}

	// 以上都不行
	alert("加入收藏失败，请使用Ctrl+D进行添加");
	return this;
};

/**
 * 设为首页
 * @param obj 设置对象
 * @param url 网站地址
 * @return c$ 对象本身，以支持连缀
 * @example  <a onclick="c$.setHome(this);">设为首页</a>
 */
c$.setHome = function(obj, url) {
	// 默认地址为本页面
	url = url || window.location.href;
	try { // IE
		obj = obj || document.body;
		obj.style.behavior = 'url(#default#homepage)';
		obj.setHomePage(url);
	}
	catch(e) {
		if (window.netscape) {
			try { // firefox
				window.netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			}
			catch (e) {
				alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
			}
			var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref('browser.startup.homepage',url);
		}
	}
	return this;
};

/**
 * 设定为焦点
 * @param element 对象
 * @return c$ 对象本身，以支持连缀
 * @example c$.setFocus(docuemnt.getElementById('text1')); // 在id为text1的元素上设置焦点
 */
c$.setFocus = function(element) {
	//element.focus();
	setTimeout(function(){try { element.focus(); } catch (e){}}, 10);
	return this;
};

/* ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * Ajax start
 * --------------------------------------------------------------- */

/**
 * 创建 XMLHttpRequest
 * @return XMLHttpRequest
 */
c$.createXMLHttpRequest = function() {
	var fns = [
	           function () { return new XMLHttpRequest(); }, // w3c, IE7+
	           function () { return new ActiveXObject('Msxml2.XMLHTTP'); }, // IE6
	           function () { return new ActiveXObject('Microsoft.XMLHTTP'); }, // IE5
	           ];
	var xmlHttp = false;
	for (var i=0, n=fns.length; i < n; i++) {
		try {
			xmlHttp = fns[i]();
			c$.createXMLHttpRequest = fns[i]; // 重置函数, 不用每次调用都重复判断
			break;
		}catch(e){}
	}
	return (xmlHttp || false);
};

/**
 * 发送 Ajax 请求
 * 需改变的参数则需写上，使用默认的不用写，所有的参数都可以不写
 * @return paramObj 参数对象,具体参考下面的用例
 * @return c$ 对象本身，以支持连缀
 *
 * c$.ajax({
 *    url : "submit.html",                         // 需要发送的地址(默认: 当前页地址)
 *    param : "a=1&b=2",                           // 需要发送的传参字符串
 *    async : true,                                // 异步或者同步请求(默认: true, 异步请求)。如果需要发送同步请求，请将此选项设置为 false
 *    cache : true,                                // 是否允许缓存请求(默认: true, 允许缓存)
 *    method : "GET",                              // 请求方式(默认: "GET"),也可用"POST"
 *    success : function(xmlHttp){....},           // 请求成功返回的动作
 *    error : function(xmlHttp, status){....},     // 请求失败时的动作
 *    complete : function(xmlHttp, status){....}   // 请求返回后的动作(不管成败,且在 success 和 error 之后运行)
 * });
 */
c$.ajax = function(paramObj) {
	// 创建 XMLHttpRequest
	var xmlHttp = c$.createXMLHttpRequest();
	// 如果不支缓 Ajax，提示信息
	if (!xmlHttp) {
		alert("您的浏览器不支持 Ajax，部分功能无法使用！");
		return this;
	}

	// 需要发送的地址(默认: 当前页地址)
	paramObj.url = paramObj.url || "#";
	// 异步或者同步请求(默认: true, 异步请求)
	paramObj.async = paramObj.async || true;
	// 缓存策略(默认: 缓存)
	if (false == paramObj.cache) {
		//paramObj.url = c$.addTimeStamp(paramObj.url); // 发送地址,加上时间戳,下面改用设头部信息的形式，减少对传参的影响
		xmlHttp.setRequestHeader("If-Modified-Since","0");
		xmlHttp.setRequestHeader("Cache-Control","no-cache");
	}
	// 请求方式(默认: "GET")
	paramObj.method = paramObj.method || "GET";
	// get形式，将参数放到URL上
	if ("GET" == ("" + paramObj.method).toUpperCase() && paramObj.param) {
		paramObj.url += (paramObj.url.indexOf("?") > 0) ? "&" : "?";
		paramObj.url += paramObj.param;
		paramObj.param = null;
		paramObj.url = c$.encodeUrl(paramObj.url);
	}
	// 发送请求
	xmlHttp.open(paramObj.method, paramObj.url, paramObj.async);
	// 执行回调方法
	xmlHttp.onreadystatechange = function() {
		// XMLHttpRequest对象响应内容解析完成
		if (4 !== xmlHttp.readyState) return;
		// 状态正常时
		if (200 === xmlHttp.status) {
			// 请求成功时的动作
			if (paramObj.success) paramObj.success(xmlHttp);
		}
		else {
			// 请求失败时的动作
			if (paramObj.error)  paramObj.error(xmlHttp, xmlHttp.status);
			// 默认的出错处理
			else alert("页面发生Ajax错误，请联系管理人员! \n错误类型：" + xmlHttp.status + ": “" + location.pathname + "”");
		}
		// 请求返回后的动作(不管成败,且在 success 和 error 之后运行)
		if (paramObj.complete) paramObj.complete(xmlHttp, xmlHttp.status);
	};
	// 请求方式("POST")
	if (paramObj.method.toUpperCase() === "POST") xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	// 发送参数
	xmlHttp.send(paramObj.param);
	return this;
};

/**
 * 获取xmlHttp里符合的资料
 * @param  xmlHttp XMLHttpRequest
 * @param  tagName 资料的 TagName
 * @param  init    默认值
 * @param  index   第几个子元素
 * @return 符合的数据的字符串
 */
c$.getAjaxValue = function(xmlHttp, tagName, init, index) {
	init = init || "";
	index = index || 0;
	// 没法继续执行
	if (!xmlHttp || !tagName) return init;

	try {
		//获取xmlHttp里对应的值
		var element1 = xmlHttp.responseXML.getElementsByTagName(tagName)[index].firstChild;
		var value = element1.nodeValue || element1.data;
		//如果能获取值
		if (value) return value;
	}
	catch (e) {}
	// 异常或者没能取到值时,返回默认值
	return init;
};

/* -------------------------------------------------------------------
 * Ajax end
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ */


/**
 * 获取元素
 * @param str 直接写则是指元素的ID或者name
 *  如果需要精确指定则使用“#”开头表示id,“@”开头表示name,“.”开头表示class,“$”开头表示tagName
 *  根据 name、class、tagName 查找时,如果只找到一个则返回一个元素,找到多个则返回数组； 根据id查找时只会返回一个
 * @param dom 需要选择的DOM对象,默认是 window.document
 * @return 如果能找到指定名称的元素，返回元素对象； 如果找不到指定的元素，返回 null
 *
 * @example
 *  c$.getElement("mytext")  // 返回 id 或者 name 为"mytext"的元素
 *  c$.getElement("#mytext") // 返回 id 为"mytext"的元素
 *  c$.getElement("@mytext") // 返回 name 为"mytext"的元素
 *  c$.getElement(".class1") // 返回 class 为"class1"的元素
 *  c$.getElement("$div")    // 返回 标签 为"div"的所有元素
 *  c$.getElement("$div #text1")  // 返回 div 标签里面 id 为"text1"的元素(支持多级查询，以空格分隔)
 */
c$.getElement = function(str, dom) {
	if (!str) return null;
	// 默认要查找的 dom 对象
	dom = dom || document;

	// 如果本身是页面对象,直接返回
	if (c$.isElement(str)) return str;

	// 判断返回：只有一个的时候，返回一个元素；多个则返回数组
	var return_arr = function(elements) {
		// 参数是数组
		if (elements && elements.constructor === Array) {
			// 空数组时
			if (elements.length === 0) return false;
			// 只有一个的时候，直接返回一个元素
			if (elements.length === 1 && c$.isElement(elements[0])) return elements[0];
			// 多个时传回数组
			return elements;
		}
		// 参数是元素
		if (c$.isElement(elements)) return elements;
		// 没有符合的
		return false;
	};

	// 获取查找的类型,以及查找的字符串
	var getType = function(str) {
		var type = null;
		var find_str = str.substr(1);
		// 判断要查询的类型
		switch(str.substr(0, 1)) {
		case "#": type = 'id'; break;
		case "@": type = 'name'; break;
		case ".": type = 'class'; break;
		case "$": type = 'tagName'; break;
		// 没有精确指定时
		default:  find_str = str;
		}
		return [type, find_str];
	};

	// 获取dom里面的元素
	var getDomElements = function(type, find_str, DOM) {
		var elements = [];
		if (DOM) {
			// 如果 dom 是个数组
			if (DOM.length && DOM.constructor === Array) {
				for(var i=0, length = DOM.length; i<length; i++){
					if (DOM[i].getElementsByTagName) {
						var tem_elements = [];
						// 根据 tagName 获取元素
						if (type === 'tagName') {elements = elements.concat(DOM[i].getElementsByTagName(find_str));}
						// 获取 dom 里面的所有元素
						else {elements = elements.concat(DOM[i].getElementsByTagName('*') || DOM[i].all);}
					}
				}
			}
			// 单个的 dom
			else {
				if (DOM.getElementsByTagName) {
					// 根据 tagName 获取元素
					if (type === 'tagName') {elements = DOM.getElementsByTagName(find_str);}
					// 获取 dom 里面的所有元素
					else {elements = DOM.getElementsByTagName('*') || DOM.all;}
				}
			}
		}
		if (elements && elements.length) {
			var ets = []; // 返回值
			for (var i=0, length = elements.length; i < length; i++) {
				var et = elements[i];
				if (c$.isElement(et)) {
					// 判断要查询的类型
					switch(type) {
					case 'id': if(et.id == find_str)ets.push(et); break;
					case 'name': if(et.name == find_str)ets.push(et); break;
					case 'class': if((" "+et.className+" ").indexOf(" "+find_str+" ") != -1)ets.push(et); break;
					case 'tagName': if(et.tagName.toUpperCase() == find_str.toUpperCase())ets.push(et); break;
					// 没有精确指定时
					default: if(et.id == find_str || et.name == find_str)ets.push(et);
					}
				}
			}
			var et = return_arr(ets);
			if (et) return et;
		}
		// 没有找到时
		return null;
	};

	// 根据 string 来获取对象
	if ("string" === typeof(str)) {
		//按照空格分割参数
		var values = str.trim().replace(/\s+/g, " ").split(" ");
		var type = getType(values[0])[0];
		var find_str = getType(values[0])[1];
		// 优化一下效率; 根据 id 获取元素
		if (dom.getElementById && (type === 'id' || type === null)) {
			var element = dom.getElementById(find_str);
			if (c$.isElement(element)) {
				if (values.length === 1)return element;
				dom = element;
				values.shift();
			}
		}
		// 优化一下效率; 根据 name 获取元素
		if (dom.getElementsByName && (type === 'name' || type === null)) {
			var elements = dom.getElementsByName(find_str);
			var et = return_arr(elements);
			if (et) {
				if (values.length === 1)return et;
				dom = et;
				values.shift();
			}
		}
		// 遍历查询
		for(var i=0, length = values.length; i<length; i++) {
			type = getType(values[i])[0];
			find_str = getType(values[i])[1];
			dom = getDomElements(type, find_str, dom);
			if (dom === null) return null; // 找不到时不再继续
		}
		if (dom) return dom;
	}

	// 找不到元素，则返回 null
	return null;
};

/**
 * 获取元素的值
 * @param name 元素的ID或者name
 * @param init 默认值
 * @return 元素的值(value 或者 innerHTML 或者 checkbox、radio选中的值)
 */
c$.getValue = function(name, init) {
	//返回值
	var retValue = "";
	//如果没有传入默认值
	if (arguments.length <= 1) init = "";
	var data = c$.getElement(name);
	//如果没有找到所要的元素，则直接返回默认值
	if (!data) return init;

	//如果它是选择框
	if ("checkbox" === data.type) {
		if (data.checked) return data.value;
		return init;
	}
	//如果它是单选按钮
	if ("radio" === data.type || (1 < data.length && "radio" === data[0].type)) {
		return c$.getRadioValue(name, init);
	}

	//获取元素的值; 没有value属性则取innerHTML
	retValue = data.value || data.innerHTML;
	//如果没有取到值，返回默认值
	return (retValue || init);
};

/**
 * 表单序列化
 * @param form 表单的id,name 或者表单对象,给出的不是 form 元素而是 div,table 之类的元素也可以
 * @param encode 是否将值转成 URL 编码
 * @return 表单序列化后的字符串
 */
c$.serialize = function(form, encode) {
	//元素列表
	var elements = c$.getElements(form);
	// 拼接参数
	var param = "";
	for (var i = 0, length = elements.length; i < length; i++) {
		if (elements[i].type) {
			var type = elements[i].type.toLowerCase();
			// 选择项,没选中时跳过
			if (type == "radio" || type == "checkbox") {
				if (!elements[i].checked) continue;
			}
			// 按钮不需要序列化
			if ("button" === type || "submit" === type || "reset" === type) continue;
		}
		// 保存名称和值
		var name = elements[i].name;
		var value = elements[i].value;
		// 如果需要转变中文编码
		value = encode ? encodeURIComponent(value) : value;
		if (param.length > 0) param += "&";
		param += name + "=" + value;
	}
	return param;
};

/**
 * 当DOM载入就绪时执行某函数(比window.onload更早)
 * @param fun [必填]需要执行的函数
 * @return c$ 对象本身，以支持连缀
 *
 * @example
 *  c$.ready(function(){alert('执行DOM加载完成事件');});
 *  c$(function(){alert('执行DOM加载完成事件');}); // 为 c$.ready(fun) 的缩写
 */
c$.ready = function (fun) {
	if (!fun || typeof fun !== 'function') return this;
	var thisFun = arguments.callee;
	// thisFun.funList 作为本函数的全局变量,保证多次调用本函数时可以共用
	thisFun.funList = thisFun.funList || [];
	thisFun.funList.push(fun);
	if (thisFun.funList.length > 1) return this;
	// 执行的函数
	var doReady = function () {
		var fn,  i = 0,  readyList;
		if (thisFun.funList) {
			// 为了减少变量查询带来的性能损耗，将它赋值给本地变量
			readyList = thisFun.funList;
			// 释放引用
			thisFun.funList = null;
			while( (fn = readyList[ i++ ]) ){
				fn.call( document );
			}
		}
	};
	// FF, Opera, 高版webkit, 其他
	if (document.addEventListener) {
		document.addEventListener( "DOMContentLoaded", function(){
			document.removeEventListener( "DOMContentLoaded", arguments.callee, false );
			doReady();
		}, false );
		window.addEventListener('load', doReady, false); // 为了保险起见，还注册了 onload 事件
	}
	// IE
	else if (document.attachEvent) {
		document.attachEvent("onreadystatechange", function() {
			if (document.readyState === "complete") {
				document.detachEvent( "onreadystatechange", arguments.callee );
				doReady();
			}
		});
		window.attachEvent('onload', doReady);

		// 判断文档是否处于最顶层,如果文档处于iframe中，调用doScroll方法成功时并不代表DOM加载完毕
		var isToplevel = false;
		try {
			isToplevel = window.frameElement == null;
		} catch(e) {}

		if (document.documentElement.doScroll && isToplevel) {
			(function(){
				try {
					document.documentElement.doScroll("left");
				} catch (error) {
					window.setTimeout(arguments.callee, 0);
					return;
				}
				doReady();
			})();
		}
	}
	return this;
};


/**
 * 设置全选组件
 * @param objid
 * @param subName
 */
function setCheckAll(objid,subName){
	var allCheck = document.getElementById(objid);
	var subs = document.getElementsByName(subName);
	allCheck.onclick = function(){
		if(allCheck.checked){
			for (var int = 0; int < subs.length; int++) {
				subs[int].checked = 1;
			}
		}else{
			for (var int = 0; int < subs.length; int++) {
				subs[int].checked = 0;
			}
		}
	};
	for (var j = 0; j < subs.length; j++) {
		subs[j].onclick = function(){
			var con = true;
			for (var m = 0; m < subs.length; m++) {
				if(!subs[m].checked){
					con = false;
				}
			}
			if(con == true){
				allCheck.checked = 1;
			}else{
				allCheck.checked = 0;
			}
		};
	}
}


/**
 * 将字符串转换成大写
 * @return boolean 符合返回true,否则返回false (注:空字符串返回 false)
 */
String.prototype.isNotNull = function() {
	if(this == null || this.trim().length == 0){
		return false;
	}
    return true;
};


/**
 * 将字符串转换成大写
 * @return boolean 符合返回true,否则返回false (注:空字符串返回 false)
 */
String.prototype.toUpperCase = function() {
	if(this == null || this.trim().length == 0){
		return "";
	}
	return this.toLocaleUpperCase();
};


/**
 * 将字符串转换成小写
 * @return boolean 符合返回true,否则返回false (注:空字符串返回 false)
 */
String.prototype.toLowerCase = function() {
	if(this == null || this.trim().length == 0){
		return "";
	}
	return this.toLocaleLowerCase();
};



/**
 * 判断身份证儿  合法返回真，假的或空的返回假
 * @param str
 */
String.prototype.isIDCard = function(){
	if(this == null || this.trim().length == 0){
		return false;
	}
	num = this.toUpperCase(); 
	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)))  {
		return false;
	}
	var len, re;
	len = num.length;
	if (len == 15){
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
		var arrSplit = num.match(re);
		var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay){
			return false;
		}else{
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
			var nTemp = 0, i;  
			num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
			for(i = 0; i < 17; i ++){
				nTemp += num.substr(i, 1) * arrInt[i];
			}
			num += arrCh[nTemp % 11];  
			return true;  
		}  
	}
	if (len == 18){
		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
		var arrSplit = num.match(re);
		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay){
			return false;
		}else{
			var valnum;
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
			var nTemp = 0, i;
			for(i = 0; i < 17; i ++){
				nTemp += num.substr(i, 1) * arrInt[i];
			}
			valnum = arrCh[nTemp % 11];
			if (valnum != num.substr(17, 1)){
				return false;
			}
			return true;
		}
	}
	return false; 
};


/**
 * 判断手机号码  合法返回真，假的或空的返回假
 * @param str
 */
String.prototype.isPhone = function(){
	var regPartton=/1[3-8]+\d{9}/;
	if(this==null || this.trim().length == 0){
		return false;
	}else if(!regPartton.test(this)){
		return false;
	}else{
		return true;
	}
};


/**
 * 判断座机号码  合法返回真，假的或空的返回假
 * @param str
 */
String.prototype.isTel = function(){
	if(this==null || this.trim().length == 0){
		return false;
	}
	var result=this.match(/\d{3}-\d{8}|\d{4}-\d{7}/);
	if(result==null){
		return false;
	} 
	return true;
};


/**
 * 判断QQ号  合法返回真，假的或空的返回假
 * @param str
 */
String.prototype.isQQ = function(){
	if(this==null || this.trim().length == 0){
		return false;
	}
	var result=this.match(/[1-9][0-9]{4,}/);
	if(result==null){
		return false;
	}
	return true;
};


/**
 * 获取选中的复选框
 * @param objName 复选框名字
 * @returns  选中的复选框的值的数组
 */
function getChecked(objName){
	var subs = document.getElementsByName(objName);
	var arr = new Array();
	for(var i=0;i<subs.length;i++){
		if(subs[i].checked){
			arr.push(subs[i].value);
		}
	}
	return arr;
}


/**
 * 根据json填充form
 * @param formId       from的id
 * @param jsonData     json数据，注：复选框的json数据格式应该为："aihao":["tiyu","nba"]
 */
function fillForm(formId,jsonData){
   var inputs = $("#"+formId+" input");
   for(var one in jsonData){  
	   for(var i=0; i<inputs.length; i++){
		   if(inputs[i].name.toLowerCase() == one){
			   if(inputs[i].type == "text" || inputs[i].type == "hidden" || inputs[i].type == "password"){
				   inputs[i].value = jsonData[one];
			   }else if(inputs[i].type == "radio"){
				   var radios = document.getElementsByName(inputs[i].name);
				   for(var m=0; m<radios.length; m++){
					   if(radios[m].value == jsonData[one]){
						   //console.log(radios[m]);
						   radios[m].checked = 1;
						   //radios[m].setAttribute("checked", "checked");
						   //console.log(radios[m].checked);
						   break;
					   }
				   }
			   }else if(inputs[i].type == "checkbox"){
				   var checkboxs = document.getElementsByName(inputs[i].name);
				   for(var two in jsonData[one]){
					   for(var m=0; m<checkboxs.length; m++){
						   if(checkboxs[m].value == jsonData[one][two]){
							   checkboxs[m].checked = 1;
						   }
					   }
				   }
			   }
//			   else if(inputs[i].type == "image"){
//				   inputs[i].src = jsonData[one];
//			   }
			   break;
		   }
	   }
   }  
}


/**
 * 进入分页
 * @param num
 */
function pageTo(num){
	//	var url;
	//    var href=window.location.href;
	//    var ind=href.lastIndexOf("&num");
	//    if(ind>0){
	//    	url=(href.split("&num"))[0];
	//    }else{
	//    	url=(href.split("num"))[0];
	//    }
	//    var index=url.lastIndexOf("?");
	//    if(index>0){
	//    	href=url+"&num="+num;
	//    }else{
	//    	href=url+"?num="+num;
	//    }
	//    window.location.href=href;
	var numVal = c$.toInt(num);
	if(numVal>0){
		var myForm = $("form[for='queryByPage']");
		if(myForm!=null){
		   $("input[name='currentPageNum']").val(num);
		   myForm.submit(); 
		}
	}
}
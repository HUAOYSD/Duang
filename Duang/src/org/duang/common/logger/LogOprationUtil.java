package org.duang.common.logger;

/** 
 * 日志行为行为
 * @ClassName: Opration 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author 白攀
 * @date 2016-7-16 下午1:36:23 
 *  
 */ 
public enum LogOprationUtil {
	ADD {
		@Override
		public String getStartWords() {
			return "add,save,insert";
		}

		@Override
		public String getNum() {
			return "1";
		}

		@Override
		public boolean isStart(String words) {
			words = words==null?"":words.trim().toLowerCase();
			for(String str : getStartWords().split(",")){
				if(words.startsWith(str)){
					return true;
				}
			}
			return false;
		}

		@Override
		public String getType() {
			return "增加行为";
		}
	},DELETE {
		@Override
		public String getStartWords() {
			return "del,remove,cancel";
		}

		@Override
		public String getNum() {
			return "2";
		}

		@Override
		public boolean isStart(String words) {
			words = words==null?"":words.trim().toLowerCase();
			for(String str : getStartWords().split(",")){
				if(words.startsWith(str)){
					return true;
				}
			}
			return false;
		}

		@Override
		public String getType() {
			return "删除行为";
		}
	},UPDATE {
		@Override
		public String getStartWords() {
			return "update,edit,modify,send,move";
		}

		@Override
		public String getNum() {
			return "3";
		}

		@Override
		public boolean isStart(String words) {
			words = words==null?"":words.trim().toLowerCase();
			for(String str : getStartWords().split(",")){
				if(words.startsWith(str)){
					return true;
				}
			}
			return false;
		}

		@Override
		public String getType() {
			return "编辑行为";
		}
	},LOGIN {
		@Override
		public String getStartWords() {
			return "login";
		}

		@Override
		public String getNum() {
			return "4";
		}

		@Override
		public boolean isStart(String words) {
			words = words==null?"":words.trim().toLowerCase();
			for(String str : getStartWords().split(",")){
				if(words.startsWith(str)){
					return true;
				}
			}
			return false;
		}

		@Override
		public String getType() {
			return "登录行为";
		}
	},LOGOUT {
		@Override
		public String getStartWords() {
			return "logout";
		}

		@Override
		public String getNum() {
			return "5";
		}

		@Override
		public boolean isStart(String words) {
			words = words==null?"":words.trim().toLowerCase();
			for(String str : getStartWords().split(",")){
				if(words.startsWith(str)){
					return true;
				}
			}
			return false;
		}

		@Override
		public String getType() {
			return "登出行为";
		}
	},EXPORT {
		@Override
		public String getStartWords() {
			return "export";
		}

		@Override
		public String getNum() {
			return "6";
		}

		@Override
		public boolean isStart(String words) {
			words = words==null?"":words.trim().toLowerCase();
			for(String str : getStartWords().split(",")){
				if(words.startsWith(str)){
					return true;
				}
			}
			return false;
		}

		@Override
		public String getType() {
			return "导出行为";
		}
	},IMPORT {
		@Override
		public String getStartWords() {
			return "import";
		}

		@Override
		public String getNum() {
			return "7";
		}

		@Override
		public boolean isStart(String words) {
			words = words==null?"":words.trim().toLowerCase();
			for(String str : getStartWords().split(",")){
				if(words.startsWith(str)){
					return true;
				}
			}
			return false;
		}

		@Override
		public String getType() {
			return "导入行为";
		}
	},QUERY {
		@Override
		public String getStartWords() {
			return "get,is,exist,check,count,find,query,show";
		}

		@Override
		public String getNum() {
			return "8";
		}

		@Override
		public boolean isStart(String words) {
			words = words==null?"":words.trim().toLowerCase();
			for(String str : getStartWords().split(",")){
				if(words.startsWith(str)){
					return true;
				}
			}
			return false;
		}

		@Override
		public String getType() {
			return "查询行为";
		}
	},GO {
		@Override
		public String getStartWords() {
			return "go,to,open";
		}

		@Override
		public String getNum() {
			return "9";
		}

		@Override
		public boolean isStart(String words) {
			words = words==null?"":words.trim().toLowerCase();
			for(String str : getStartWords().split(",")){
				if(words.startsWith(str)){
					return true;
				}
			}
			return false;
		}

		@Override
		public String getType() {
			return "跳转行为";
		}
	};
	public abstract String getType();
	public abstract String getStartWords();
	public abstract String getNum();
	public abstract boolean isStart(String words);
}

package org.duang.enums;

import org.duang.util.DataUtils;

/**   
 * 文件上传路径
 * @ClassName:  UploadFile   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年9月14日 下午4:11:39      
 */  
public enum UploadFile {
	PATH{
		@Override
		public String getVal() {
			return "resources\\file\\basic\\";
		}

		@Override
		public String toString() {
			return "上传路径";
		}

		@Override
		public String getVal(String path) {
			if(DataUtils.notEmpty(path)){
				return "resources\\file\\basic\\" + path;
			}else{
				return "resources\\file\\basic\\";
			}
		}

		@Override
		public String appPath() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	TEM{ //临时
		@Override
		public String getVal() {
			return "resources\\tem\\";
		}

		@Override
		public String toString() {
			return "上传路径";
		}

		@Override
		public String getVal(String path) {
			if(DataUtils.notEmpty(path)){
				return "resources\\tem\\" + path+"\\";
			}else{
				return "resources\\tem\\";
			}
		}

		@Override
		public String appPath() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	CONTRACT{
		@Override
		public String getVal() {
			return "contract";
		}

		@Override
		public String toString() {
			return "合同";
		}

		@Override
		public String getVal(String path) {
			if(DataUtils.notEmpty(path)){
				return "\\"+path+"\\contract";
			}else{
				return "\\contract";
			}
		}

		@Override
		public String appPath() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	SALARY{
		@Override
		public String getVal() {
			return "\\salary";
		}

		@Override
		public String toString() {
			return "薪资证明";
		}

		@Override
		public String getVal(String path) {
			if(DataUtils.notEmpty(path)){
				return "\\"+path+"\\salary";
			}else{
				return "\\salary";
			}
		}

		@Override
		public String appPath() {
			// TODO Auto-generated method stub
			return null;
		}

	},
	//身份证
	IDCARD {
		@Override
		public String getVal(String userId) {
			if(DataUtils.notEmpty(userId)){
				return "\\"+userId+"\\idcard";
			}else{
				return "\\idcard";
			}
		}

		@Override
		public String toString() {
			return "身份证";
		}

		@Override
		public String getVal() {
			return "\\idcard";
		}

		@Override
		public String appPath() {
			// TODO Auto-generated method stub
			return null;
		}

	},
	//身份证
	HOUSE{
		@Override
		public String getVal(String userId) {
			if(DataUtils.notEmpty(userId)){
				return "\\"+userId+"\\house";
			}else{
				return "\\house";
			}
		}

		@Override
		public String toString() {
			return "房子";
		}

		@Override
		public String getVal() {
			return "\\house";
		}

		@Override
		public String appPath() {
			// TODO Auto-generated method stub
			return null;
		}

	},
	//身份证
	CAR{
		@Override
		public String getVal(String userId) {
			if(DataUtils.notEmpty(userId)){
				return "\\"+userId+"\\car";
			}else{
				return "\\car";
			}
		}

		@Override
		public String toString() {
			return "车产";
		}

		@Override
		public String getVal() {
			return "\\car";
		}

		@Override
		public String appPath() {
			// TODO Auto-generated method stub
			return null;
		}

	},
	//头像
	HEAD {
		@Override
		public String getVal() {
			return "\\head";
		}

		@Override
		public String toString() {
			return "头像";
		}

		@Override
		public String getVal(String userId) {
			if(DataUtils.notEmpty(userId)){
				return userId+"\\head";
			}else{
				return "\\head";
			}
		}

		@Override
		public String appPath() {
			return "resources/file/basic/";
		}

	},
	//广告
	AD{
		@Override
		public String getVal() {
			return "ad";
		}

		@Override
		public String toString() {
			return "广告";
		}

		@Override
		public String getVal(String userId) {
			if(DataUtils.notEmpty(userId)){
				return "\\"+userId+"\\ad";
			}else{
				return "\\ad";
			}
		}
		@Override
		public String appPath() {
			return "resources/file/basic/ad/";
		}

	},
	//新闻资讯
		NEWS{
			@Override
			public String getVal() {
				return "news";
			}

			@Override
			public String toString() {
				return "新闻资讯";
			}

			@Override
			public String getVal(String userId) {
				if(DataUtils.notEmpty(userId)){
					return "\\"+userId+"\\news";
				}else{
					return "\\news";
				}
			}

			@Override
			public String appPath() {
				// TODO Auto-generated method stub
				return null;
			}

		},
	//银行卡
	BANK{
		@Override
		public String getVal() {
			return "\\bindcard";
		}

		@Override
		public String toString() {
			return "银行卡";
		}

		@Override
		public String getVal(String userId) {
			if(DataUtils.notEmpty(userId)){
				return "\\"+userId+"\\bindcard";
			}else{
				return "\\bindcard";
			}
		}

		@Override
		public String appPath() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	
	/**
	 * 获取路径
	 * @Title: getVal   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param path 用户的id唯一标识或者包组合
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月14日 上午11:14:17
	 * @return: String      
	 * @throws
	 */
	public abstract String getVal(String path);
	
	/**
	 * 获取路径
	 * @Title: getVal   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月14日 上午11:14:17
	 * @return: String      
	 * @throws
	 */
	public abstract String getVal();
	
	/**
	 * app调用时候返回的路径问题
	 * @Title: appPath   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年11月10日 上午11:51:38
	 * @return: String      
	 * @throws
	 */
	public abstract String appPath();
}

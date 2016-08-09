package org.duang.util;

/**
 * 定义常见常量
 * @ClassName:  ConstantCode   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月3日 下午3:18:57
 */
public interface ConstantCode {
	/**
	 * 冻结
	 */
	String FREEZE = "1";
	/**
	 * 解冻
	 */
	String UNFREEZE = "0";
	
	/**
	 * 未删除
	 */
	String UNDELETE = "0";
	/**
	 * 未删除
	 */
	int UNDELETE_INT = 0;
	
	/**
	 * 删除
	 */
	String DELETE = "1";
	
	/**
	 * 默认
	 */
	String DEFAULT = "0";
	
	/**
	 * select标签没有选择
	 */
	int NOSELECTED = -1;
	/**
	 * select标签没有选择
	 */
	String NOSELECTED1 = "-1";
	
	/**
	 * 上传头像
	 */
	String upload_user_head = "0";
	/**
	 * 上传身份证前照
	 */
	String upload_user_idcard_1 = "1";
	/**
	 * 上传身份证后照
	 */
	String upload_user_idcard_2 = "2";
	
}

package org.duang.util;


/**
 * 访问丰付的接口数据
 * @ClassName:  SendSumapayData   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年12月12日 上午11:24:13
 */
public class SendSumapayData {
	
	/**
     * 获取 分账列表，对应记录一笔用户的进账明细；记录一条或多条商户的进账明细。
     * @Title: getSubledgerList   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param memberInfoId
     * @param: @return
     * @param: @throws Exception  
     * @author LiYonghui    
     * @date 2016年11月9日 下午2:31:42
     * @return: String      返回拼接好的参数类型   a=b&c=d 
     * @throws
     */
    public static String getSubledgerList(String memberInfoId,String sum) throws Exception{
	    	StringBuffer paramBuffer = new StringBuffer("[{");
			 paramBuffer.append("\"roleType\"")
	         .append(":")
	         .append("\"0\"")
	         .append(",")
	         .append("\"roleCode\"")
	         .append(":")
	         .append("\""+memberInfoId+"\"")
	         .append(",")
	         .append("\"inOrOut\"")
	         .append(":")
	         .append("\"0\"")
	         .append(",")
	         .append("\"sum\"")
	         .append(":")
	         .append("\""+sum+"\"")
			 .append("}")
			 .append("]");
		return paramBuffer.toString();
    }

}

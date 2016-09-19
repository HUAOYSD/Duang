package org.duang.action.sys;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.Contract;
import org.duang.service.ContractService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 合同Action
 * @ClassName:  ContractAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年9月18日 上午11:25:36
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "contract")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/contract/contractList.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })

public class ContractAction extends BaseAction<Contract> {
	private static final long serialVersionUID = 1L;
	
	private ContractService contractService;
	@Resource(name = "contractserviceimpl")
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	/**
	 * 页面跳转 ---列表页面
	 * @Title: gotolist   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月18日 下午5:02:07
	 * @return: String      
	 * @throws
	 */
	public String gotolist(){
		return "list";
	}
	
	/**
	 * @Title: query   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月18日 下午5:02:54
	 * @return: void      
	 * @throws
	 */
	public void query() {
		try {
			List<Contract> list = new ArrayList<Contract>();
			if(entity != null && DataUtils.notEmpty(entity.getName())){
				condsUtils.addProperties(true, "name");
				condsUtils.concatValue(new String[] { entity.getName(), "like" });
				condsUtils.addProperties(false, "order");
				condsUtils.addValues(false, Order.desc("createTime"));
				list = contractService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			}else{
				list = contractService.queryAllEntity(Order.desc("createTime"));
			}
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("合同ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("合同ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 封装集合
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月18日 下午5:06:44
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<Contract> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (Contract con : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", con.getId());
				map.put("createTime", DateUtils.date2Str(con.getCreateTime()));
				map.put("state", con.getState());
				map.put("name", con.getName());
				map.put("conPath", con.getConPath());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("合同封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("合同封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 下载合同
	 * @Title: downContract   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月18日 下午5:11:13
	 * @return: String      
	 * @throws
	 */
	public void downContract() {
		try{
			if(entity != null && entity != null){
				Contract contract = contractService.findById(entity.getId());
				if(contract != null){
					downFile(contract);
				}else{
					msg="数据过期，请刷新重试";
				}
			}else {
				msg="请选择下载的合同";
			}
		}catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("跳转到上传广告图片页面错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("跳转到上传广告图片页面错误：" + e.getLocalizedMessage(), this.getClass());
		} 
	}
	
	private void downFile(Contract contract) throws Exception{
        //处理文件名
        String realname = contract.getName();
        //设置响应头，控制浏览器下载该文件
        getResponse(null).setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        String path = getRequest().getSession().getServletContext().getRealPath("/") ;
        FileInputStream in = new FileInputStream(path + contract.getConPath());
        //创建输出流
        OutputStream out = getResponse(null).getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
	}
	
	 
}

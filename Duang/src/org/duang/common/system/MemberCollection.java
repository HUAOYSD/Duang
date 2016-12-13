package org.duang.common.system;

import java.util.HashMap;

import net.sf.json.JSONObject;

import org.duang.entity.MemberInfo;
import org.duang.service.MemberInfoService;
import org.duang.util.DataUtils;
import org.springframework.stereotype.Component;

/**   
 * App登录的会员用户集合
 * @ClassName:  MemberCollection   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月6日 上午11:04:58      
 */ 
@Component
public class MemberCollection {  
	private static MemberCollection instance;  
	private MemberCollection() {  
		members = new HashMap<String, JSONObject>();  
		id_token = new HashMap<String, String>();  
	}  
	public static MemberCollection getInstance(String token,MemberInfoService service) throws Exception{  
		if (instance == null) {  
			instance = new MemberCollection();  
		}  
		if (DataUtils.notEmpty(token)) {
			JSONObject jsonObject = instance.optJsonObject(token);
			if (jsonObject == null || jsonObject.size() == 0) {
				jsonObject = new JSONObject();
				MemberInfo entity = service.findEntity("token", token);
				jsonObject.put("pd", entity.getPassword());
				jsonObject.put("token", token);
				jsonObject.put("id", entity.getId());
				jsonObject.put("isAuth", String.valueOf(entity.getIsAuth()));
				jsonObject.put("idcard", entity.getIdCard());
				instance.putJsonObject(token, jsonObject);
			}
		}
		return instance;  
	}  

	private HashMap<String, JSONObject> members;  
	private HashMap<String, String> id_token;

	public synchronized void putJsonObject(String token, JSONObject jsonObject) throws Exception{  
		String id = jsonObject.optString("id");
		String old_token = "";
		if (id_token.containsKey(id)) {
			old_token = id_token.get(id);
			removeJsonObject(old_token, id);
		}
		members.put(token, jsonObject);  
		id_token.put(id, token);  
	}  

	public synchronized JSONObject optJsonObject(String token) throws Exception{  
		return members.get(token);  
	}  

	public synchronized void removeJsonObject(String token, String id) throws Exception{
		members.remove(token);
		id_token.remove(id);
	}  
	
	public synchronized String getMainField(String token) throws Exception{
		return optJsonObject(token).optString("id", "");
	}
	
	public synchronized String getField(String token, String field) throws Exception{
		return optJsonObject(token).optString(field, "");
	}
	
}  
package org.duang.common.system;

import java.util.HashMap;
import net.sf.json.JSONObject;


/**   
 * App登录的会员用户集合
 * @ClassName:  MemberCollection   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月6日 上午11:04:58      
 */  
public class MemberCollection {  

	private static MemberCollection instance;  
	private MemberCollection() {  
		members = new HashMap<String, JSONObject>();  
		id_token = new HashMap<String, String>();  
	}  
	public static MemberCollection getInstance() {  
		if (instance == null) {  
			instance = new MemberCollection();  
		}  
		return instance;  
	}  

	private HashMap<String, JSONObject> members;  
	private HashMap<String, String> id_token;

	public synchronized void putJsonObject(String token, JSONObject jsonObject) {  
		String id = jsonObject.optString("id");
		String old_token = "";
		if (id_token.containsKey(id)) {
			old_token = id_token.get(id);
			removeJsonObject(old_token, id);
		}
		members.put(token, jsonObject);  
		id_token.put(id, token);  
	}  

	public synchronized JSONObject optJsonObject(String token) {  
		return members.get(token);  
	}  

	public synchronized void removeJsonObject(String token, String id) {
		members.remove(token);
		id_token.remove(id);
	}  

}  
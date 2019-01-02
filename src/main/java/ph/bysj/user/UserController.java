package ph.bysj.user;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class UserController extends Controller{
	public void index(){

	}	
	public void changeNickName(){
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String userid = getPara("id");
		String nickname = getPara("nickname");

		
		String SQL = "update user set nickname=? where id=?";
		Integer result = Db.update(SQL, nickname,userid);
		if (result == 1){
			renderJson("{\"status\":\"success\",\"msg\":\"修改成功\"}");
		}else{
			renderJson("{\"status\":\"failure\",\"msg\":\"修改失败\"}");
		}
		
	}
	
	public void changePass(){
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String pass = getPara("pass");
		String userid = getPara("id");
		
		String SQL = "update user set password = ? where id=?";
		
			Integer result = Db.update(SQL, pass,userid);
			if (result == 1){
				renderJson("{\"status\":\"success\",\"msg\":\"修改成功\"}");
			}else{
				renderJson("{\"status\":\"failure\",\"msg\":\"修改失败\"}");
			}
	}
		

	
	public void changeAddr1(){
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String addr = getPara("addr1");
		String userid = getPara("id");

		String SQL = "update user set addr1=? where id=?";
		Integer result = Db.update(SQL,addr,userid);
		if (result == 1){
			renderJson("{\"status\":\"success\",\"msg\":\"修改成功\"}");
		}else{
			renderJson("{\"status\":\"failure\",\"msg\":\"修改失败\"}");
		}
	}
	public void changeAddr2(){
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String addr = getPara("addr2");
		String userid = getPara("id");

		String SQL = "update user set addr2=? where id=?";
		Integer result = Db.update(SQL,addr,userid);
		if (result == 1){
			renderJson("{\"status\":\"success\",\"msg\":\"修改成功\"}");
		}else{
			renderJson("{\"status\":\"failure\",\"msg\":\"修改失败\"}");
		}
	}
	public void changeAddr3(){
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String addr = getPara("addr3");
		String userid = getPara("id");

		String SQL = "update user set addr3=? where id=?";
		Integer result = Db.update(SQL,addr,userid);
		if (result == 1){
			renderJson("{\"status\":\"success\",\"msg\":\"修改成功\"}");
		}else{
			renderJson("{\"status\":\"failure\",\"msg\":\"修改失败\"}");
		}
	}
}

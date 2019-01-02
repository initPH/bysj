package ph.bysj.back;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

public class BackController extends Controller{
	// 注册帐号
		public void add() {
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			String username = getPara("username");
			String password = getPara("password");
			String addr = getPara("addr");
			//店铺名字
			String shopName = getPara("shopName");

			String SQL = "select id from seller where username=?";

			Integer result = Db.queryFirst(SQL, username);

			if (result == null) {
				Record seller = new Record().set("username", username).set("password", password).set("name", shopName).set("addr", addr);
				System.out.println(seller);
				Db.save("seller", seller);
				renderJson("{\"status\":\"success\",\"msg\":\"注册成功\"}");
			} else {
				renderJson("{\"status\":\"failure\",\"msg\":\"注册失败,帐号已经存在\"}");
			}

		}

		// 登录
		public void login() {

			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			String username = getPara("username");
			String password = getPara("password");
			String SQL = "select * from seller where username=? and password=?";
			Record result = Db.findFirst(SQL, username, password);
			System.out.println(result);
			if (result != null) {
				renderJson(result);
			} else {
				renderJson("{\"status\":\"failure\",\"msg\":\"登录失败，检查你的帐号和密码\"}");
			}
		}
		//获取店铺名字
		public void getName(){
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			String id = getPara("id");
			String SQL = "select name from seller where id=?";
			Record result = Db.findFirst(SQL,id);
			renderJson(result);
		}
		//获取店铺商品
		public void getGoods(){
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			String id = getPara("id");
			String SQL = "select * from goods where seller_id = ? order by id desc";
			List<Record> result = Db.find(SQL, id);
			int i = 1;
			for (Record record : result) {
				record.set("index", i++);
			}
			renderJson(result);
			
		}


		//获取订单
		public void getOrder(){
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			String id = getPara("id");
//			String SQL = "select * from goods,user,goodorder where seller_id = ? and goods.id=goodorder.good_id and goodorder.users_id=user.id";
			String SQL = "select goodorder.id as id1,goods.id as id2,user.id as id3,goodorder.*,goods.*,user.* from goodorder LEFT JOIN user on goodorder.users_id=user.id LEFT JOIN goods on goodorder.good_id=goods.id where seller_id=?";
			List<Record> result = Db.find(SQL, id);
			System.out.println(11111);
			System.out.println(result);
			System.out.println(11111);
			int i = 1;
			for (Record record : result) {
				record.set("index", i++);
			}
			
			renderJson(result);
		}
		public void verifySend(){
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			String id = getPara("id");
			System.out.println(id);
			String SQL = "update goodorder set is_send=true where id=?";
			int result = Db.update(SQL,id);
			if(result>0){
				renderJson("{\"status\":\"success\",\"msg\":\"发货成功\"}");
			}else{
				renderJson("{\"status\":\"failed\",\"msg\":\"发货失败\"}");
			}
		}
		
		
		
		//添加商品
		public void addgood(){
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			String name = getPara("name");
			String price = getPara("price");
			String sell_point = getPara("sell_point");
			String img_url = getPara("img_url");
			System.out.println(img_url);
			String number = getPara("number");
			String seller_id = getPara("seller_id");
			Record good = new Record().set("name", name).set("price", price).set("sell_point", sell_point).set("number", number).set("seller_id", seller_id).set("img_url", img_url);
			if(Db.save("goods", good)){
				renderJson("{\"status\":\"success\",\"msg\":\"添加成功\"}");
			}else{
				renderJson("{\"status\":\"failed\",\"msg\":\"添加失败\"}");
			}
			
		}
		public void modifygood(){
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			String name = getPara("name");
			String price = getPara("price");
			String sell_point = getPara("sell_point");
			
			String number = getPara("number");
			String seller_id = getPara("seller_id");
			String id = getPara("id");
			String SQL = "update goods set name=?,price=?,sell_point=?,number=?,seller_id=? where id=?";
			Db.update(SQL,name,price,sell_point,number,seller_id,id);
			renderJson("{\"status\":\"success\",\"msg\":\"修改成功\"}");
			
		}
		
		public void changeName(){
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			String name = getPara("name");
			String seller_id = getPara("seller_id");
			String SQL = "update seller set name=? where id=?";
			int result = Db.update(SQL,name,seller_id);
			if(result>0){
				renderJson("{\"status\":\"success\",\"msg\":\"修改成功\"}");
			}else{
				renderJson("{\"status\":\"failed\",\"msg\":\"修改失败\"}");
			}
		}
		public void changePass(){
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			String seller_id = getPara("seller_id");
			String pass = getPara("pass");
			String pass1 = getPara("pass1");
			String SQL = "select * from seller where id=? and password=?";
			List<Record> result = Db.find(SQL,seller_id,pass);
			if(result.size()==1){
				String SQL1 = "update seller set password=? where id=?";
				int res = Db.update(SQL1,pass1,seller_id);
				if(res==1){
					renderJson("{\"status\":\"success\",\"msg\":\"修改成功\"}");
				}else{
					renderJson("{\"status\":\"failed\",\"msg\":\"修改失败\"}");
				}
			}else{
				renderJson("{\"status\":\"failed\",\"msg\":\"修改失败,原始密码错误\"}");
			}
		}
		public void deleteGood(){
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			String id = getPara("id");
			String SQL = "delete from goods where id=?";
			int result = Db.delete(SQL,id);
			System.out.println(result);
		}
		
		public void uppic() throws IOException, FileUploadException{
			getResponse().addHeader("Access-Control-Allow-Origin", "*");
			Map<String,Object> map=new HashMap<>();
			HttpServletRequest request = getRequest();
			if(ServletFileUpload.isMultipartContent(request)){
				FileItemFactory factory = new DiskFileItemFactory();
			    ServletFileUpload upload = new ServletFileUpload(factory);
			    List<FileItem> items = upload.parseRequest(request);
			    String filePath = "";
			    UUID saveName = UUID.randomUUID();
			    for(FileItem i: items){
			    	String fileName = i.getName();
			    	String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length()); 
			    	System.out.println(i.getName());
			    	if(!i.isFormField()&&i.getSize()>0){
				    	Files.write(Paths.get("src/main/webapp/upload/"+saveName+fileTyle), i.get());
				    	filePath ="upload/"+saveName+fileTyle;
				    	map.put("filePath",filePath);
				    }
			    }
			    
			}
			
			renderJson(map);
		}
		
		
		
}

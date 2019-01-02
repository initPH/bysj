package ph.bysj.api;

/***
 * 商品页面的api
 */
import java.util.Arrays;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class ApiController extends Controller {

	// 首页物品
	public void index() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		
		
		Integer pageNum = Integer.valueOf(getPara("pageNum"));
		Integer orderType = Integer.valueOf(getPara("orderType"));
		
	
		if(orderType==1){
			System.out.println("hothothothothothot");
			String SQL = "select * from goods order by buy_times desc limit ?,? ";
			List<Record> goodsList = Db.find(SQL,(pageNum-1)*20,20);
			renderJson(goodsList);
		}else if(orderType==2){
			System.out.println("highhighhighhighhighhigh");
			String SQL = "select * from goods order by price desc limit ?,? ";
			List<Record> goodsList = Db.find(SQL,(pageNum-1)*20,20);
			renderJson(goodsList);
		}else{
			System.out.println("lowlowlowlowlowlow");
			String SQL = "select * from goods order by price  limit ?,? ";
			List<Record> goodsList = Db.find(SQL,(pageNum-1)*20,20);
			renderJson(goodsList);
		}
		
	}
	//所有商品的数目
	public void totalNum(){
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String SQL = "select count(1) from goods";
		Long Num = Db.queryFirst(SQL);
		renderJson(Num);
	}

	// 注册帐号
	public void add() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String username = getPara("username");
		String password = getPara("password");

		String SQL = "select id from user where username=?";

		Integer result = Db.queryFirst(SQL, username);

		if (result == null) {
			Record user = new Record().set("username", username).set("password", password);
			System.out.println(user);
			Db.save("user", user);
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
		String SQL = "select * from user where username=? and password=?";
		Record result = Db.findFirst(SQL, username, password);
		System.out.println(result);
		if (result != null) {
			renderJson(result);
		} else {
			renderJson("{\"status\":\"failure\",\"msg\":\"登录失败，检查你的帐号和密码\"}");
		}
	}
	public void cookieLogin() {

		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String id = getPara("id");
		
		String SQL = "select * from user where id=?";
		Record result = Db.findFirst(SQL,id);
		System.out.println(result);
		renderJson(result);
		
	}

	// 添加购物车
	public void addToCar() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String userid = getPara("userid");
		String goodid = getPara("goodid");
		String SQL = "select id from car where users_id=? and good_id=?";
		Integer result = Db.queryFirst(SQL, userid, goodid);
		if (result == null) {
			Record car = new Record().set("users_id", userid).set("good_id", goodid);
			Db.save("car", car);
			renderJson("{\"status\":\"success\",\"msg\":\"添加成功\"}");
		} else {
			Db.update(String.format("update car set count=count+1 where id=%d", result));
			renderJson("{\"status\":\"success\",\"msg\":\"数量加1\"}");
		}
	}

	// 添加订单
	public void addToOrder() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String userid = getPara("userid");
		String goodid = getPara("goodid");
		String count = getPara("count");
		String addr = getPara("addr");
		
		//商品被购买次数加1
		String SQL0 = "update goods set buy_times=buy_times+? where id=?";
		Db.update(SQL0,count,goodid);
		
		//用户 商品信息
		String SQL = "insert into goodorder(users_id,good_id,count,addr) values(?,?,?,?)";
		Record goodorder = new Record().set("users_id", userid).set("good_id", goodid).set("count", count).set("addr", addr);
		boolean result = Db.save("goodorder",goodorder);
		if (result) {
			renderJson("{\"status\":\"success\",\"msg\":\"添加成功\"}");
		} else {
			renderJson("{\"status\":\"success\",\"msg\":\"数量增加\"}");
		}
	}

	// 获取购物车信息
	public void getCar() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String userid = getPara("userid");
		String SQL = "select * from car,goods where users_id=? and goods.id = car.good_id";
		List<Record> carList = Db.find(SQL, userid);
		renderJson(carList);
	}
	//获取购物车信息
	public void getOrder() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String userid = getPara("userid");
		String SQL = "select * from goodorder,goods where users_id=? and goods.id = goodorder.good_id order by goodorder.id desc";
		List<Record> carList = Db.find(SQL, userid);
		renderJson(carList);
	}

	// 删除购物车信息
	public void delCar() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String userid = getPara("userid");
		String goodid = getPara("goodid");
		String SQL = "delete from car where users_id=? and good_id=?";
		Db.delete(SQL, userid, goodid);
		renderJson("{\"status\":\"success\",\"msg\":\"删除成功\"}");
	}

	// 获取登录用户的信息
	public void getUserInfo() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String userid = getPara("userid");
		String SQL = "select * from user where id=?";
		renderJson(Db.findFirst(SQL, userid));

	}
	//根据穿过来的物品的id返回物品的信息
	public void goodDetail(){
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String goodid = getPara("goodid");
		String SQL = "select * from goods where id=?";
		Record result = Db.findFirst(SQL,goodid);
		renderJson(result);
	}
	//退款
	public void refund(){
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String userid = getPara("userid");
		String goodid = getPara("goodid");
		String SQL = "delete from goodorder where users_id=? and good_id=?";
		Db.delete(SQL,userid,goodid);
		renderJson("{\"status\":\"success\",\"msg\":\"删除成功\"}");
	}
	//是否收货
	public void verifyRecive(){
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String userid = getPara("userid");
		String goodid = getPara("goodid");
		String SQL = "update goodorder set is_recive=1 where users_id=? and good_id=?";
		Db.update(SQL, userid,goodid);
		renderJson("{\"status\":\"success\",\"msg\":\"确认成功\"}");
	}
}

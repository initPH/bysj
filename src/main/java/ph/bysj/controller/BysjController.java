package ph.bysj.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class BysjController extends Controller{
	public void index(){
		String SQL = "select * from goods";
		List<Record> result = Db.find(SQL);
		renderJson(result);
	}
}

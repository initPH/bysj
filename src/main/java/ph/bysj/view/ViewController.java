package ph.bysj.view;

import com.jfinal.core.Controller;
public class ViewController extends Controller{
	public void index(){
		render("/dist/index.html");
	}
	public void back(){
		render("/back/index.html");
	}
	
}

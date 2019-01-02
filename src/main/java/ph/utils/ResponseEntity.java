package ph.utils;

import java.sql.Timestamp;
import java.util.Map;

import ph.bysj.model.Identify;

public class ResponseEntity {
    private Integer state;//消息状态
    private String msg; //消息内容
    private Object data;//存放数据

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //判断请求默认参数是否完整
    public boolean isCompelete(String version,String token,int time ){
        if(version==null||token==null||time==0){
            return false;
        }
        return true;
    }

    //判断请求是否超时
    public boolean isOverTime(Timestamp oldTime,int time){
        Timestamp nowTime=common.getCurrentDate();
        if( nowTime.getTime()-oldTime.getTime()>time){
            return true;
        }
        return false;
    }
}

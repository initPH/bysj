package ph.bysj;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcUtils;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.json.JacksonFactory;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.*;
import com.jfinal.plugin.activerecord.dialect.*;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import ph.bysj.api.ApiController;
import ph.bysj.back.BackController;
import ph.bysj.controller.BysjController;
import ph.bysj.model.ExamInfo;
import ph.bysj.model.Identify;
import ph.bysj.user.UserController;
import ph.bysj.view.ViewController;
import ph.utils.FileUtil;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class BysjConfig extends JFinalConfig{

    private long startTime=0;

    //配置属性
    protected static Map<String,String> props=new HashMap<String, String>();

    public static Map<String,String> getProps(){
        return props;
    }

    public void configConstant(Constants constants) {
        startTime=System.currentTimeMillis();
        constants.setEncoding("UTF-8");
        System.err.println("Config Constants Starting...");
        //初始化配置
        iniConfig(props);
        // POST内容最大500M(安装包上传)
        constants.setMaxPostSize(1024 * 1024 * 500);
        
        constants.setBaseUploadPath("D:/sys/static/temp");
    }

    public void configRoute(Routes routes) {
        routes.add("/",ViewController.class);
        routes.add("/api",ApiController.class);
        routes.add("/users",UserController.class);
        routes.add("/back",BackController.class);
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {
        System.err.println("Config Plugins Starting...");

        Map<String,String> map=BysjConfig.getProps();
        String url=map.get("sys.url");
        String user=map.get("sys.user");
        String pwd=map.get("sys.pwd");
        DruidPlugin druidPlugin=new DruidPlugin(url,user,pwd);
        plugins.add(druidPlugin);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setDevMode(true);
        arp.setShowSql(true);
        arp.setDialect(new MysqlDialect());

        plugins.add(druidPlugin);

        plugins.add(arp);

//        log.info("EhCachePlugin EhCache缓存");
//        plugins.add(new EhCachePlugin());
//
//        log.info("SqlXmlPlugin 解析并缓存 xml sql");
//        plugins.add(new SqlXmlPlugin());
    }

    public void configInterceptor(Interceptors interceptors) {

    }

    public void configHandler(Handlers handlers) {

    }

    public void iniConfig(Map<String,String> props){
        String resPath= PathKit.getRootClassPath()+ File.separator;
        //加载默认配置
        boolean flag=loadConfig(props,resPath+"default");
        if(flag){
            LogKit.info("默认配置加载成功:(resources/default)\n");
        }

    }

    public boolean loadConfig(Map<String,String> props,String path){
        if (!FileUtil.isDir(path)){
            return false;
        }
        File[] files=FileUtil.getFiles(path);
        for (File file: files) {
            if(!file.getName().endsWith(".config")){
                continue;
            }
            Properties properties=FileUtil.getProp(file);
            Set<Object> keySet=properties.keySet();
            for (Object ks:keySet) {
                String key=ks.toString();
                props.put(key,properties.getProperty(key));
            }
            LogKit.info(file.getName());
        }
        return true;
    }


    /**
     * init Druid
     *
     * @param url
     *            JDBC
     * @param username
     *            数据库用户
     * @param password
     *            数据库密码
     * @return
     */
    protected DruidPlugin initDruidPlugin(String driver, String url, String username, String password) {
        // 设置方言
        WallFilter wall = new WallFilter();
        String dbType = null;
        try {
            dbType = JdbcUtils.getDbType(url, JdbcUtils.getDriverClassName(url));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        wall.setDbType(dbType);

        DruidPlugin dp = new DruidPlugin(url, username, password);
        if (!StringUtils.isEmpty(driver)) {
            dp.setDriverClass(driver);
        }
        dp.addFilter(new StatFilter());
        dp.addFilter(wall);
        return dp;

    }
}

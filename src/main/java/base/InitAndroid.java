package base;

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

/**
 * Created by xx on 2017/10/25.
 */


public class InitAndroid {

    public MacacaClient driver = new MacacaClient();
    Logger logger = LogManager.getLogger(getClass());
    String classRootpath = System.getProperty("user.dir");
    String appPath = classRootpath + "/apps/xxxx.apk";
    //单例初始化的driver

    private static InitAndroid initMacaca = null;
    String response;
    public synchronized static InitAndroid getInstance() {
        if(initMacaca == null) {
            initMacaca = new InitAndroid();
        }
        return initMacaca;
    }

    private InitAndroid(){
        initdriver();
    }



    public MacacaClient initdriver(){
        JSONObject porps = new JSONObject();
//		初始化数据
        porps.put("platformName", "Android");
        porps.put("app",appPath);
        porps.put("package", "com.xxxx");//appPackage 启动包名
        porps.put("activity", ".ui.activitys.GuideActivity");//appActivity 启动类名
        porps.put("reuse",3);  //不重新启动app做 测试 为3就不调用initAPP方法
        JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("desiredCapabilities", porps);
        try {
            driver.initDriver(desiredCapabilities);
        }catch (Exception e){
            logger.info("初始化driver失败，确认是否开启服务");
            System.out.println("初始化driver失败，确认是否开启服务");
            e.printStackTrace();
        }

        return driver;
    }
    @AfterTest
    public void afterTest() throws Exception {
        driver.quit();
    }

    @AfterClass
    public void afterClass() throws Exception {
        //每一个用例完毕结束这次测试
        driver.quit();
    }
}

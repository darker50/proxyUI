package inter;

import base.AndroidCommon;
import pages.AiExamPage;

import java.text.SimpleDateFormat;

/**
 * Created by xx on 2017/11/28.
 */


public class uiClick {
    AndroidCommon common = AndroidCommon.getInstance();
    AiExamPage aiExam = new AiExamPage();
    int i=0;
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式


    public uiClick(){

    }
    public void startClick(CallBack callBack,String fileName) {
        try {
            String lianxi = "//*[@text=\"考题练习\"]";
            String backButton = "//*[@resource-id=\"com.xxxx:id/exercise_btn_back\"]";
            if(!common.isEmentExit(lianxi)){
                common.clickBack();
            }
            common.clickByXpath(lianxi);
            String fileNamePng = fileName+".png";
            Thread.sleep(3000);

            common.saveScreepshot("logs/"+fileNamePng);
            Thread.sleep(1000);
            common.clickByXpath(backButton);
            System.out.println(i);
            i++;

        } catch (Exception e) {
            e.printStackTrace();
        }
        //回调继续调用点击，进行广告点击
        callBack.contiuClick();
    }
}
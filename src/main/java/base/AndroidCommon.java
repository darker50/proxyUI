package base;

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;
import macaca.client.common.ElementSelector;

/**
 * Created by xx on 2016/12/19.
 */
public class AndroidCommon {
    //单例操作
    MacacaClient driver;
    private AndroidCommon() {
        this.driver = InitAndroid.getInstance().driver;
    }
    private static volatile AndroidCommon common;

    public static AndroidCommon getInstance(){
        if (common == null){
            common = new AndroidCommon();
        }
        return common;
    }


    public  String getIDText(String id) throws Exception {
        return  (driver.elementByXPath("//*[@resource-id=\""+id+"\"]")).getText();
    }

    public void clickByText(String text) throws Exception {
        driver.elementByXPath("//*[@text=\""+text+"\"]").click();
    }

    public void clickBack() throws Exception {
        driver.back();
    }

//    public void clickByName(String Name) throws Exception {
//        driver.elementByXPath("//*[@name=\"考题练习\"]");
//    }

    public void clickByID(String id) throws Exception {
//        driver.elementById(id).click();
        driver.elementByXPath("//*[@resource-id=\""+id+"\"]").click();
    }


    public void clickByXpath(String xpath) throws Exception {
        driver.elementByXPath(xpath).click();
    }

    public void saveScreenshot(String fileName) throws Exception {
        driver.saveScreenshot(fileName);
    }
    public void titleSwipeUp() throws Exception {
        driver.drag(560,1200,560,400,0.1);
    }

    public void titleSwipeDown() throws Exception {
        driver.drag(560,400,560,1200,1);
    }

    public void findElement(){


    }

    public void swipeLeft() throws Exception {
        JSONObject windowsize = driver.getWindowSize();
        int x = windowsize.getIntValue("width");
        int y = windowsize.getIntValue("height");
        JSONObject coord = new JSONObject();
        coord.put("fromX",x/8*7);
        coord.put("fromY",y/2);
        coord.put("toX",x/5);
        coord.put("toY",y/2);
        driver.touch("drag",coord);
    }

    //mate9滑动上一页专用

    public void swipeUpTest() throws Exception {
        driver.drag(560,1200,560,400,1);

    }

    public void swipeDownTest() throws Exception {
        driver.drag(560,400,560,1200,1);

    }

    public void swipeUp() throws Exception {
        JSONObject windowsize = driver.getWindowSize();
        int x = windowsize.getIntValue("width");
        int y = windowsize.getIntValue("height");
        JSONObject coord = new JSONObject();
        coord.put("fromX",x/2);
        coord.put("fromY",y*3/4);
        coord.put("toX",x/2);
        coord.put("toY",y/4);
        driver.touch("drag",coord);
    }

    public void swipeDown() throws Exception {
        JSONObject windowsize = driver.getWindowSize();
        int x = windowsize.getIntValue("width");
        int y = windowsize.getIntValue("height");
        JSONObject coord = new JSONObject();
        coord.put("fromX",x/2);
        coord.put("fromY",y/4);
        coord.put("toX",x/2);
        coord.put("toY",y*3/4);
        driver.touch("drag",coord);
    }



    public void swipeRight() throws Exception {
        JSONObject windowsize = driver.getWindowSize();
        int x = windowsize.getIntValue("width");
        int y = windowsize.getIntValue("height");
        JSONObject coord = new JSONObject();
        coord.put("fromX",x/5);
        coord.put("fromY",y/2);
        coord.put("toX",x/8*7);
        coord.put("toY",y/2);
        driver.touch("drag",coord);
    }

    public boolean isEmentExit(String id) throws Exception {
        return driver.isElementExist("xpath","//*[@resource-id=\""+id+"\"]");
    }


    public boolean isEmentExitByXpath(String xpath) throws Exception {
        return driver.isElementExist("xpath",xpath);
    }

    public void isElementExitClickByXpath(String xpath) throws Exception {
        if (isEmentExitByXpath(xpath)) {
            clickByXpath(xpath);
        }
    }



    public void isElementExitClickByID(String id) throws Exception {
        if (isEmentExit(id)) {
            clickByID(id);
        }
    }

    public ElementSelector  getElements(String id) throws Exception {
        return driver.elementsById(id);
    }

    public void saveScreepshot(String fileName) throws Exception {
        driver.saveScreenshot(fileName);
    }

//    public boolean tryWaitToDisplayed(String id,int waitTime){
//        boolean waitDisplayed = false;
//        final By input_loc = By.id(id);
//        try {
//            waitDisplayed = new WebDriverWait(driver, waitTime).until(new ExpectedCondition<Boolean>() {
//                public Boolean apply(WebDriver d) {
//                    return d.findElement(input_loc).isDisplayed();
//                }
//            });
//            waitDisplayed = true;
//        }catch (Exception e){
//
//        }
//        return waitDisplayed;
//    }

}

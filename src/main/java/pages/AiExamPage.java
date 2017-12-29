package pages;

import base.AndroidCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xx on 2017/10/25.
 */


public class AiExamPage {
    AndroidCommon common = AndroidCommon.getInstance();
    private final String Ai_Exam_ID = "com.xxxx:id/tv_start_exam";
    private final String DOINFOID = "com.xxxx:id/txvDoInfo";
    private final String SUBMITID = "com.xxxx:id/txvSubmit";
    private final String SHOUCANGID = "com.xxxx:id/tv_second";
    private final String SETTINGID = "com.xxxx:id/tv_fourth";
    private final String CANCLESUBMITID = "com.xxxx:id/xxxxbaseui_tv_left_btn";
    private final String CONFIRMSUBMITID = "com.xxxx:id/xxxxbaseui_tv_right_btn";
    private final String OPTIONS_A_ID = "com.xxxx:id/rlyt_answer_item1";
    private final String OPTIONS_B_ID = "com.xxxx:id/rlyt_answer_item2";
    private final String OPTIONS_C_ID = "com.xxxx:id/rlyt_answer_item3";
    private final String OPTIONS_D_ID = "com.xxxx:id/rlyt_answer_item4";
    private final String COUNTIUN = "com.xxxx:id/xxxxbaseui_tv_left_btn";
    private final String TITLE = "com.xxxx:id/tv_question_body";
    private final String TITLETYPE= "com.xxxx:id/tv_question_type";
    private final String ErrorTips= "com.xxxx:id/tv_question_type";
    private final String CancleTXT= "com.xxxx:id/tv_discuss_canceltxt";//不切换夜间模式
    private final String BTN_OK= "com.xxxx:id/btn_ok";//加入错题ok
    private final String MulCommint = "com.xxxx:id/btn_commit";//多选提交
    private final String ExamTips = "//android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]";//多选提交



    public String titleno;
    public String titlelevel;
    public String title;
    public String titleType;
    public String answer;

    Boolean countiu = false;


    public void clickStartExam() throws Exception {
        common.isElementExitClickByID(Ai_Exam_ID);
//        common.clickByID(Ai_Exam_ID);
    }

    /***
     * 返回题目
     * @return
     * @throws Exception
     */

    public String getTitle() throws Exception{
        return common.getIDText(TITLE).split("\\(")[0];
    }

    public void tryClickExamsTips() throws Exception{
        common.isElementExitClickByXpath(ExamTips);
    }



    public void getAnswer() throws Exception{
        common.isElementExitClickByID(COUNTIUN);
        common.isElementExitClickByID(CancleTXT);
        common.isElementExitClickByID(BTN_OK);
        List d = new ArrayList();
        if(common.isEmentExit(TITLE)){
            title = common.getIDText(TITLE);
        }else {
            common.swipeDownTest();
            title = common.getIDText(TITLE);
        }
        Pattern pattern = Pattern.compile("(?<=\\()(.*?)(?=\\))");
        Matcher m = pattern.matcher(title);
        while (m.find()){
            d.add(m.group());
        }
//        answer = (String) d.get(0);
        if(d.get(2).toString().length()>3){
            titleno = d.get(2).toString().substring(3);
            titlelevel = d.get(3).toString().substring(3);
        }else{
            titleno = d.get(3).toString().substring(3);
            titlelevel = d.get(4).toString().substring(3);
        }

    }
    public void alwayClickA() throws Exception {
        common.isElementExitClickByID(COUNTIUN);
        common.isElementExitClickByID(CancleTXT);
        common.isElementExitClickByID(BTN_OK);
        if(common.getIDText(TITLETYPE).equals("多选")){
            clickOptionA();
            clickOptionB();
            if(common.isEmentExit(MulCommint)){
                common.clickByID(MulCommint);
            }else {
                common.swipeUpTest();
                common.clickByID(MulCommint);
            }
        }else {
            clickOptionA();

        }
    }


    public void clickAnswer() throws Exception {
        //多选答案点击
        if(answer.length()>1){
            char[] c = answer.toCharArray();
            for(char ch:c){
                switch (ch){
                    case '1':
                        clickOptionA();
                        break;
                    case '2':
                        clickOptionB();
                        break;
                    case '3':
                        clickOptionC();
                        break;
                    case '4':
                        clickOptionD();
                        break;
                    default:
                        break;

                }
            }
            //确认按钮不存在当前页面时下滑
            if(common.isEmentExit(MulCommint)){
                common.clickByID(MulCommint);
            }else {
                common.swipeUpTest();
                common.clickByID(MulCommint);
            }

        }
        //单选答案点击
        else if (answer.length()==1){
            switch (answer.toString()){
                case "1":
                    clickOptionA();
                    break;
                case "2":
                    clickOptionB();
                    break;
                case "3":
                    clickOptionC();
                    break;
                case "4":
                    clickOptionD();
                    break;
            }
        }else {
            clickOptionA();
        }

//        if(getTitleType().equals("多选")){
//            clickOptionA();
//            clickOptionB();
//            //提交按钮不存在时，上滑页面
//            if(common.isEmentExit(MulCommint)){
//                common.clickByID(MulCommint);
//            }else {
//                common.swipeUpTest();
//                common.clickByID(MulCommint);
//            }
//
//        }else {
//            clickOptionA();
//        }
    }




    public void getTitleNo() throws Exception{
        String answer = common.getIDText(TITLE).split("\\(")[0];;
    }




    /**
     * 点击选项A
     * @throws Exception
     */
    public void clickOptionA() throws Exception{
        common.isElementExitClickByID(COUNTIUN);
        common.clickByID(OPTIONS_A_ID);
    }

    /**
     * 点击选项B
     * @throws Exception
     */
    public void clickOptionB() throws Exception{
        common.isElementExitClickByID(COUNTIUN);
        common.clickByID(OPTIONS_B_ID);
    }

    /**
     * 点击选项C
     * @throws Exception
     */
    public void clickOptionC() throws Exception{
        common.isElementExitClickByID(COUNTIUN);

        if(common.isEmentExit(OPTIONS_C_ID)){
            common.clickByID(OPTIONS_C_ID);
        }else {
            common.swipeUpTest();
            common.clickByID(OPTIONS_C_ID);
        }

        }

    /**
     * 点击选项D
     * @throws Exception
     */
    public void clickOptionD() throws Exception{
        common.isElementExitClickByID(COUNTIUN);
        if(common.isEmentExit(OPTIONS_D_ID)){
            common.clickByID(OPTIONS_D_ID);
        }else {
            common.swipeUpTest();
            common.clickByID(OPTIONS_D_ID);
        }
    }


    public void confirmSubmit() throws Exception{
        common.clickByID(SUBMITID);
//        common.clickByID(CONFIRMSUBMITID);
    }

    /**
     * 获取doinfo总题数
     * @return
     * @throws Exception
     */
    public Integer getTitleCount() throws Exception {
        String info = common.getIDText(DOINFOID);
        return Integer.valueOf(info.split("\\(")[0].split("/")[1]);
    }

    /**
     * 获取题目的类型
     * @return
     * @throws Exception
     */
    public String getTitleType() throws Exception {
        if(common.isEmentExit(TITLETYPE)){
            return common.getIDText(TITLETYPE);
        }else {
            common.swipeDownTest();
            return common.getIDText(TITLETYPE);
        }
    }
}

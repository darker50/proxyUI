package inter;

import lee.study.proxyee.server.HttpProxyServer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xx on 2017/11/28.
 */


public class Wang implements CallBack {
    private uiClick ui;
    private HttpProxyServer httpProxyServer;
    public String fileName="";

    public Wang(uiClick li){
        this.ui = li;
    }


    public void startCollectADJson(){
        System.out.println("准备收集数据，调用UI进行点击");
        play();
    }


    public void play(){
        System.out.println("我去逛街了");
        ui.startClick(Wang.this,fileName);
    }


    @Override
    public void contiuClick(){
        System.out.println("调用UI点击方法");
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fileName = date.format(new Date()).toString();
        testProxy.bq = fileName;
        startCollectADJson();
    }


    }

package inter;


import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import lee.study.proxyee.intercept.HttpProxyIntercept;
import lee.study.proxyee.intercept.HttpProxyInterceptInitializer;
import lee.study.proxyee.intercept.HttpProxyInterceptPipeline;
import lee.study.proxyee.server.HttpProxyServer;
import macaca.client.MacacaClient;


/**
 * Created by xx on 2017/11/28.
 */


public class testProxy {
    static MacacaClient driver;
//    testProxy test = new testProxy();

    public static String bq = "new";

    public static void main(String[] args){
        //启动一个线程，做代码监控使用
        new Thread(new Runnable() {
            public testProxy test = new testProxy();
            @Override
            public void run() {
                test.initProxy();
            }
        }).start();


        uiClick li = new uiClick();
        //使用回调的方式，循环进行操作
        Wang wang = new Wang(li);
        wang.contiuClick();
    }

    public void initProxy(){
        new HttpProxyServer()
//        .proxyConfig(new ProxyConfig(ProxyType.SOCKS5, "127.0.0.1", 1085))  //使用socks5二级代理
                .proxyInterceptInitializer(new HttpProxyInterceptInitializer() {
                    @Override
                    public void init(HttpProxyInterceptPipeline pipeline) {
                        pipeline.addLast(new HttpProxyIntercept() {
                            @Override
                            public void beforeRequest(Channel clientChannel, HttpRequest httpRequest,
                                                      HttpProxyInterceptPipeline pipeline) throws Exception {
                                //替换UA，伪装成手机浏览器
                                httpRequest.headers().set(HttpHeaderNames.USER_AGENT,
                                        "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
                                //删除要压缩传输，减少后期触压操作
                                httpRequest.headers().remove("Accept-Encoding");
                                //转到下一个拦截器处理
                                pipeline.beforeRequest(clientChannel, httpRequest);
                            }

                            @Override
                            public void afterResponse(Channel clientChannel, Channel proxyChannel,
                                                      HttpResponse httpResponse,
                                                      HttpProxyInterceptPipeline pipeline) throws Exception {
                                //拦截响应，添加一个响应头
                                httpResponse.headers().add("intercept", "test");
                                //转到下一个拦截器处理
                                pipeline.afterResponse(clientChannel, proxyChannel, httpResponse);
                            }
                        });
                    }
                }).start(8002);
    }
}

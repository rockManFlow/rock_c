package com.kuark.tool.advance.advance20191228;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author rock
 * @detail
 * @date 2020/4/22 14:05
 */

/**
 * 原理：
 * okhttpClient
 * Request对象进行url+method+body等参数构建
 * 有个newCell(request) 对象---》RealCell
 *
 * RealCell对象中有同步+异步方法----在分别调用Dispatcher对象对应的同步及异步方法
 * Dispatcher对象维护3个队列及线程池和对应的同步异步方法
 * 线程池进行异步线程的：远程调用+收尾执行动作
 * 同步线程队列中：远程调用+执行收尾动作
 *
 * 如何进行远程调用？
 * 分别调用各个拦截器链进行拦截器的执行
 * 拦截器包含：各个拦截器
 * 链是个list集合的调用：在刚开始的时候index=0,之后每个拦截器中index+1，就会调用下一个拦截器根据下标的方式
 * 真正的调用也是对websocket底层方式封装来调用的
 */
public class OkHttpResolver {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    public static OkHttpClient client=new OkHttpClient();

    public static void main(String[] args) throws IOException {
        RequestBody body = RequestBody.create("json", JSON);
        Request request = new Request.Builder()
                .url("url")
                .post(body)
                .build();
        //同步请求
        ResponseBody resp = client.newCall(request).execute().body();
        resp.string();
    }

    public static void asyn(){
        RequestBody body = RequestBody.create("json", JSON);
        Request request = new Request.Builder()
                .url("url")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                response.body().string();
            }
        });
    }
}

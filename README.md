# RetrofitClient
一个用于android网络请求的工具，基于RxJava、Retrofit进行了封装。

依赖：

工程目录build.gradle下添加jcenter()仓库（AS新建项目默认就有）：

```
allprojects {
    repositories {
        jcenter()
    }
}
```
然后app的build.gradle：

```
implementation 'com.liduo:retrofitclient:1.0.5'
```
使用：

1.初始化，传入baseUrl

```
RetrofitClient.init("http://www.baidu.com");
```

2.get请求

```
RetrofitClient.getInstance().get()// get
                .setApiName("/testapi")// 接口路径，如果与baseUrl不同，这里可以传全路径比如 http://www.xxx.com/testapi
                .addHeader("headerKey", "headerValue") // 请求头
                .addQuery("queryKey1", 100)// get参数，值可以为基本数据类型，这里用object接收的
                .addQuery("queryKey2", "hello world")
                .call(new ResponseCallback<String>() { //发起请求并回调到主线程，可以传入泛型
                    @Override
                    public void onResponse(String response) {

                    }

                    @Override
                    public void onError(String errMsg) {

                    }
                });
```

3.post 传表单参数附件

```
RetrofitClient.getInstance().postForm() //post表单
                .setApiName("testApi") 
                .addHeader("headerKey", "headerValue")
                .addParam("param1", "param1Value") //表单参数
                .addFile("fileName", new File("xxxx/xxx/xx")) // 文件附件
                .call(new ResponseCallback<String>() {
                    @Override
                    public void onResponse(String response) {
                        
                    }

                    @Override
                    public void onError(String errMsg) {

                    }
                });
```

4.post 传json

```
RetrofitClient.getInstance().postJson() //上传json格式
                .setApiName("testApi")
                .addHeader("headerKey", "headerValue")
                .setJson(new User()) //设置json数据，这里有三个重载方法，setJson(Object object),setJson(String jsonString),setJson(JSONObject jsonObject)
                .call(new ResponseCallback<String>() {
                    @Override
                    public void onResponse(String response) {

                    }

                    @Override
                    public void onError(String errMsg) {

                    }
                });
```

5.还支持put请求，与get，post类似。

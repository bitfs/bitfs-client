# 工程说明
本工程为BitFS支付接入示例工程，用于帮助商户快速接入以及调试。本工程使用springboot编写，可以直接运行的完整工程。<br>
备注:运行本程序需要先开通对应环境的商户号，修改配置文件./src/main/resources/application.yml,替换如下信息：
```    
    bitfs-client:
      pay-config:
        #请求的接口url
        trade-url: https://gwapi.bitfs.com
        #appkey
        app-key: todo replace your app key
        #商户密钥
        secret-key: todo replace your secret key
        #商户id
        mch-key: todo replace your mch key
        #结果回调地址,需公网可访问的地址才能回调
        notice-url: todo replace your notice url
```
# 主要目录说明
```
bitfs-client
|--src
   |--main
       |--java
       |  |--com.bitfs
       |     |--client (接入的主要类库)
       |     |--config (demo的配置类)
       |     `--demo.controller (类库使用demo)
       `--resources
          |--static (demo的页面文件)
```


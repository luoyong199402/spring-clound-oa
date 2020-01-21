### 该模块为认证服务模块
该模块支持两种认证方式。

1. 授权码模式
2. 密码模式 

#### 授权码模式认证过程
1. 访问 /oauth/authorize 这个端点获取授权码
```lombok.config
http://localhost:8080/oauth/authorize?response_type=code&client_id=client1&redirect_uri=http://baidu.com

response_type=code          固定参数， 表示这是一个授权码模式
client_id=client1           客户端id。由后台配置生成
redirect_uri=http://baidu.com  重定向地址： 授权码会跟在这个URL后面。 获取授权码的 “redirect_uri” 和 获取token的  “redirect_uri” 必须一致

访问该地址后。会弹出用户认证界面。 在用户成功认证之后。会跳转到  “redirect_uri” 这个地址。 并把授权码以参数的形式放在后面
https://www.baidu.com/?code=b4t1qY

code=b4t1qY     即为授权码
```

2. 访问 /oauth/token 这个端点获取token信息
```http request
post http://localhost:8080/oauth/token
{
  grant_type: authorization_code  // 固定写法。 表示获取授权码的方式为授权码模式获取
  code: QBN1qU                    // 从上面获取的授权码
  redirect_uri: http://baidu.com  // 重定向的uri  必须和获取授权码的uri 的 “redirect_uri” 一样
  client_id: client1              // 客户端id     表示那个客户端获取授权码
  client_secret: 123456           // 秘钥         系统配置的秘钥
}

响应的结果为： 
{
   "access_token": "cdc557f1-63a7-4213-af19-7546b36f7b8d",   // token信息
   "token_type": "bearer",
   "refresh_token": "3aa5ef3e-9449-42fd-b6d9-4e302b5d06b8",  // 刷新令牌
   "expires_in": 43199,                                      // 过期时间
   "scope": "test"                                           // 作用域
}

```


#### 密码模式认证过程
直接请求即可获取
```http request
post http://localhost:8080/oauth/token?grant_type=password&username=admin&password=admin&client_id=client1&client_secret=123456&scope=test
```
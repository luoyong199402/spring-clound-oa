添加oauth 客户端

```http request
post http://localhost:8080/oauth-client
{ 
   "scope": [
        "test"
    ],
    "client_id": "client1",
    "client_secret": "{bcrypt}$2a$10$GFez4DHC15WlIq1Wm3PVB.xVPNowBMB04PsLGo3FRDCCH66ttyEpq",
    "resource_ids": [
        "123"
    ],
    "authorized_grant_types": [
        "refresh_token",
        "password",
        "authorization_code"
    ],
    "redirect_uri": [
        "http://baidu.com"
    ],
    "autoapprove": [
        "true"
    ],
    "access_token_validity": 43200,
    "refresh_token_validity": 2592000,
    "authorities": [
        "test"
    ],
    "additionalInformation": {
        "key": "test"
    }
}

```


## Authservice授权验证服务

### 1. 说明

- Main分支使用OAuth2验证和授权
- Jwt分支使用JWT验证和授权

### 2.Postman获取token

#### 2.1 获取token
- url: http://127.0.0.1:8188/oauth/token
- method: `post`
- form-body:
  
    - grant_type: password
    - scope: webclient
    - client_id: oauth2
    - client_secret: 123456
    - username: ligy
    - password: 123456
    
#### 2.2 获取用户信息

- url: http://127.0.0.1:8188/user
- method: `get`
- header:

    - Authorization: `值为获取的token_type + access_token`

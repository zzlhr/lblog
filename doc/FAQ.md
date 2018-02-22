### nginx反向代理出现/admin/ 目录下session失效问题解决方案
配置nginx： 

``` proxy_set_header  Cookie $http_cookie; ```

### 
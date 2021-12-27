## LicensingService服务
#### 1.笔记

- OAuth2RestTemplate通过`new OAuth2RestTemplate()`创建无法传递头部的`Authorization`,需要通过UserInfoRestTemplateFactory中获取OAuth2RestTemplate才能自动传递`Authorization`,同时不能添加`@LoadBalanced`注解,示例代码如下:
```java
@Bean
public OAuth2RestTemplate oauth2RestTemplate(UserInfoRestTemplateFactory factory) {
    OAuth2RestTemplate restTemplate = factory.getUserInfoRestTemplate();
    List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
    //添加拦截器
    if (Objects.isNull(interceptors)) {
        restTemplate.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
    } else {
        interceptors.add(new UserContextInterceptor());
        restTemplate.setInterceptors(interceptors);
    }
    return restTemplate;
}
```
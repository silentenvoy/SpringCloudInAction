package wiki.ccp.authservice.controller;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @RequestMapping(value = "/user", produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user", user.getUserAuthentication().getPrincipal());
        userMap.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userMap;
    }
}

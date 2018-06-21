package com.xjy.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1818:20
 * @Description:  自定义用户不要使用spring默认给你的用户名密码，==================核心===============
 * @Modified By:
 */
//只要把自己定义的userdetailservice声明为spring容器管理的类，它就会覆盖默认的userdetailService，全局注入都是使用这个
@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

//    在这里可以注入mapper了
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("用户名{}",s);
        //先根据用户名查找用户信息
        //这里的密码是数据库查询出来的密码，这里的权限也是数据库查询的权限，查完后都设置到user中
        //根据数据库查找到的用户信息判断用户是否被冻结,再设置到下面的user中
        //passwordEncoder.encode("123456")这句话应该是你在注册的时候用的，切记这里是模拟加密而已
        return new User(s,passwordEncoder.encode("123456"),
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}

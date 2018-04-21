package com.rayenyang.webpj.mybatis;

import com.rayenyang.webpj.config.ApplicationConfig;
import com.rayenyang.webpj.entity.user.UserInfo;
import com.rayenyang.webpj.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * description:
 * Created by rayenyang on 2017/7/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@ActiveProfiles("test")
public class MyBatisTest {

//    @Autowired
//    private SqlSessionTemplate sqlSessionTemplate;
    
    @Autowired
    ResourceLoader resourceLoader;
    
    @Autowired
    private UserMapper userMapper;
    
    
    @Test
    public void testSelect(){
//        final UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
//        User user = new User();
//        user.setUserName("mybatis-study2");
//        user.setPassword("1234");
//        userMapper.insert(user);
//        final int userId = user.getUserId();
//        System.out.println(userId);
//        System.out.println(user);
        final UserInfo dbUser = userMapper.selectUserInfoById(1);
        System.out.println(dbUser);
    }
    
    @Test
    public void testListUser(){
//        final UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
//        final List<User> users = mapper.listUser();
//        for (User user : users) {
//            System.out.println(user);
//        }
    }
    
    @Test
    public void listResource() throws IOException {
        final ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        final Resource[] resources = patternResolver.getResources("classpath*:mapper/*.xml");
        for (Resource resource : resources) {
            System.out.println(resource.getFilename());
        }
    }
}

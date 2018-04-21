package com.rayenyang.webpj.service;

import com.rayenyang.webpj.annotation.WebPJ;
import com.rayenyang.webpj.entity.Argument;
import com.rayenyang.webpj.entity.TLoginLogEntity;
import com.rayenyang.webpj.entity.TUserEntity;
import com.rayenyang.webpj.event.FirstEvent;
import com.rayenyang.webpj.event.SecondEvent;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rayenyang on 2017/1/4.
 */
@ValidateOnExecution
@Service("pj_test")
public class TestService implements ApplicationEventPublisherAware {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    //    @Inject
    private SessionFactory sessionFactory;
    
    private String id;
    
    private ApplicationEventPublisher publisher;
    
    @WebPJ("aayrhbb")
//    @Async
    public List<String> test(String id) {
        final String name = Thread.currentThread().getName();
        System.out.println(name + ":" + this.id);
        this.id = id;
        System.out.println("set id!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + ":" + this.id);
        List<String> idList = new LinkedList<>();
        idList.add(id);
        return idList;
    }
    
//    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void printTime(){
        System.out.println(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
    }
    
    @Transactional
    public void txTest(String name) {
//        String sql = "INSERT INTO t_user(user_name, credits, password, last_visit, last_ip) " +
//                "VALUES (?,?,?,?,?)";
//        jdbcTemplate.update(sql, name, 10, "", new Date(), "");
        String logSql = "INSERT INTO t_login_log(user_id, ip, login_time) " +
                "VALUES (?,?,?)";
        jdbcTemplate.update(logSql, null, "111", new Date());
        
        TUserEntity userEntity = new TUserEntity();
        userEntity.setUserName(name);
        userEntity.setCredits(10);
        userEntity.setPassword("1234");
        userEntity.setLastIp("111.111.111.111");
        userEntity.setLastVisit(new Date());
        sessionFactory.getCurrentSession().save(userEntity);
        TLoginLogEntity loginLogEntity = new TLoginLogEntity();
        loginLogEntity.setUserId(1);
        loginLogEntity.setIp("111.111.111.111");
        loginLogEntity.setLoginTime(new Date());
        sessionFactory.getCurrentSession().save(loginLogEntity);
        
    }
    
    @Transactional(noRollbackFor = ArithmeticException.class)
    public void testTransation(String name) {
        jdbcTemplate.update("INSERT INTO t_user(user_name) VALUES (?)", name);
        System.out.println(9/0);
        final String username = jdbcTemplate.queryForObject("SELECT user_name FROM t_user WHERE user_name=?", String.class, name);
        System.out.println(username);
    }
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
    
    public void createEvent(String id, int level) {
        publisher.publishEvent(new FirstEvent(this, id, level));
        publisher.publishEvent(new SecondEvent(this, id, level));
    }
    
    @EventListener
    public void receiveEvent(FirstEvent event) {
        System.out.println(event.getSource().getClass().equals(TestService.class));
        System.out.println("receive:");
        System.out.println(event);
    }
    
    @AssertTrue
    public boolean printArg(@NotNull Argument argument) {
        System.out.println(argument);
        return argument.getMaxAge() < 5;
    }
    
    
    public void autoBox1(Integer i,String s){
        System.out.println("autoBox1:" + i);
    }
    
    public void autoBox2(int i){
        System.out.println("autoBox2:" + i);
    }
    
    @PostConstruct
    private void prepare() {
        System.out.println("创建Test");
    }
    
    @PreDestroy
    private void clean() {
        System.out.println("销毁Test");
    }
}

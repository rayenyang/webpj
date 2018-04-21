package com.rayenyang.webpj.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * description:
 * Created by rayenyang on 2017/5/31.
 */
@Service("prototypeTest")
public class PrototypeTest implements IPrototypeTest {
    private int a = (int) (Math.random() * 10);

    @PostConstruct
    public void init(){
        System.out.println(this);
        System.out.println("prototype construct" + a);
    }

    @Bean("inner")
    @RequestScope
    public Inner getInner(){
        return new Inner();
    }

    @PreDestroy
    public void destroy(){
        System.out.println("prototype destroy");
    }

    @Override
    public int get() {
        return a;
    }

    public class Inner{
        private int random = (int) (Math.random()*100);

        public int getRandom() {
            return random;
        }

        public void setRandom(int random) {
            this.random = random;
        }
    }

}

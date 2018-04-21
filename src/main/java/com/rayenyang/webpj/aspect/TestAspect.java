package com.rayenyang.webpj.aspect;

import com.rayenyang.webpj.annotation.WebPJ;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description:
 * Created by rayenyang on 2017/4/19.
 */
@Aspect
@Component
public class TestAspect {
    
    private static final Log LOG = LogFactory.getLog(TestAspect.class);

    @Pointcut(value = "execution(* com.rayenyang.webpj.service.TestService.test(String)) " +
            "&& args(id) " +
            "&& @annotation(com.rayenyang.webpj.annotation.WebPJ)")
    private void pointCut(String id){}
    
    @Pointcut(value = "execution(* com.rayenyang.webpj.service.TestService.test(*)) " +
            "&& @annotation(com.rayenyang.webpj.annotation.WebPJ)")
    private void pointCut2(){}
    

//    @Around(value = "pointCut(id)")
    private List<String> testAspect(ProceedingJoinPoint jp, String id){
        System.out.println("----before test aspect----");
        System.out.println("id = " + id);
        try {
            final MethodSignature signature = (MethodSignature) jp.getSignature();
            final WebPJ su = signature.getMethod().getAnnotation(WebPJ.class);
            final String value = su.value();
            System.out.println(value);
    
            List<String> list = (List<String>) jp.proceed();
            LOG.info(list);
            list.clear();
            return list;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("----after test aspect----");
        return null;

    }
    
//    @AfterReturning(value = "pointCut2()", returning = "idList")
    private void testAspect2(List<String> idList){
        LOG.info("catch id:" + idList);
        idList.add("aop");
    }
    
    
    @Before("execution(* com.rayenyang.webpj.service.TestService.*(..)) && args(i,..)")
    private void testAutoBoxing(int i){
        LOG.info("aspect:" + i);
    }
}

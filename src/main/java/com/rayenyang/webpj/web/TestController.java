package com.rayenyang.webpj.web;

import com.rayenyang.webpj.entity.Argument;
import com.rayenyang.webpj.service.IPrototypeTest;
import com.rayenyang.webpj.service.PrototypeTest;
import com.rayenyang.webpj.service.TestService;
import com.rayenyang.webpj.service.impl.CacheTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * description:
 * Created by rayenyang on 2017/5/31.
 */
@RestController
@PropertySource("classpath:test.properties")
public class TestController {
    public static final Log LOG = LogFactory.getLog(TestController.class);
    
    @Autowired
    private IPrototypeTest test;
    
    @Autowired
    private PrototypeTest.Inner inner;
    
    @Autowired
    private CacheTest cacheTest;
    
    @Autowired
    private TestService testService;
    
    @Value("${testname}")
    private String userName;
    
    
    @GetMapping("/double/{num}")
    public int get(@PathVariable Integer num) {
        return cacheTest.getDouble(num);
    }
    
    
    @GetMapping("/random")
    public int getBean() {
        return inner.getRandom();
    }
    
    
    @GetMapping("/event")
    public void testEvent(String id, int level) {
        testService.createEvent(id, level);
    }
    @GetMapping("/async")
    public void testAsync(String id){
        testService.test(id);
    }
    
    @GetMapping("/args")
    public void addArg(@Valid Argument argument, Errors errors){
        if (errors.hasErrors()){
            final List<ObjectError> allErrors = errors.getAllErrors();
            allErrors.forEach(objectError -> LOG.error(objectError.getCodes()[0]+objectError.getDefaultMessage()));
            return;
        }
        testService.printArg(argument);
    }
    
    @GetMapping("/username")
    String getUserName(){
        System.out.println("get username");
        return userName;
    }
}

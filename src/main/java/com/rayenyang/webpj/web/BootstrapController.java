package com.rayenyang.webpj.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.rayenyang.webpj.entity.Log;
import com.rayenyang.webpj.entity.LogValid;
import com.rayenyang.webpj.entity.user.User;
import com.rayenyang.webpj.service.Cart2;
import com.rayenyang.webpj.service.ICart;
import com.rayenyang.webpj.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rayenyang on 2017/1/4.
 */

@Api(value = "/bootstrap",description = "bootstrap接口文档")
@RestController
public class BootstrapController {

    private org.apache.commons.logging.Log log = LogFactory.getLog(getClass());

    @Resource(name = "pj_test")
    private TestService testService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Inject
    private ICart cart;
    
    @Resource(name = "cart2")
    private Cart2 cart2;


//    @InitBinder
    protected void initBinder(DataBinder dataBinder) {
//        dataBinder.setValidator(new LogValid());
        dataBinder.addValidators(new LogValid());
    }
    
    @ApiOperation("主页")
    @GetMapping("/home")
    public ModelAndView bootstrap(HttpServletRequest req) {
        req.getRemoteHost();
        req.getRemoteAddr();
        System.out.println(req.getRequestURI());
        ModelAndView modelAndView=new ModelAndView("home");
        return modelAndView;
//        return "home";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<String> list(HttpServletRequest req) {
        List<String> list = new LinkedList<>();
        list.add(req.getRemoteUser());
        list.add(req.getRemoteAddr());
        list.add(req.getRemoteHost());
        return list;
    }

    @RequestMapping(value = "/log", produces = "application/json; charset=utf-8")
    @ResponseBody
    public void test(@Valid @RequestBody Log log, Errors errors, HttpServletResponse resp) throws IOException {
//        resp.setContentType("application/json");
        if (errors.hasErrors()) {
            final List<FieldError> fieldErrors = errors.getFieldErrors();
            StringBuilder errorMsgBuilder = new StringBuilder();
            for (FieldError fieldError : fieldErrors) {
                errorMsgBuilder.append(fieldError.getCode())
                        .append(" ");
            }
            final String errorMsg = errorMsgBuilder.toString();
            System.out.println(errorMsg);
            throw new RuntimeException(errorMsg);
//            resp.getWriter().println(errorMsg);
//            return errorMsg;
        } else {
            resp.getWriter().println(log.toString());
//            return log.toString();
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String processException(/*HttpServletResponse resp,*/ RuntimeException e) throws IOException {
//        resp.getWriter().println("handle exception:" + e.getMessage());
        e.printStackTrace();
        return "handle exception:" + e.getMessage();
    }

    @GetMapping("/tx/{name}")
    public void txTest(@PathVariable String name) {
        System.out.println("txtest");
        testService.txTest(name);
    }

    @GetMapping("/session/")
    public void sessionTest(){
        System.out.println(cart.get());
        System.out.println(cart2.get());
    }
    
    @GetMapping("/logout")
    public void sessionLogout(HttpSession session){
        session.invalidate();
    }



    @JsonView(User.WithPassword.class)
    @GetMapping("/jv")
    @ResponseBody
    public User testJsonView(){
        User user = new User();
        user.setUserName("a");
        user.setPassword("1234");
        return user;
    }

    @GetMapping("/thread")
    public void thread(String id){
        testService.test(id);
    }
    
    
    @PostMapping("/form")
    String form(@RequestParam int id){
        return "receive " + id;
    }
}

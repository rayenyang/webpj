package com.rayenyang.webpj.web;

import com.rayenyang.webpj.entity.user.User;
import com.rayenyang.webpj.view.ExcelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * description:
 * Created by rayenyang on 2017/6/19.
 */
@Controller
public class ExcelController implements Serializable{
    
    @GetMapping("/excel")
    public ModelAndView getExcel(HttpServletRequest req){
        req.getRemoteAddr();
        req.getRemoteHost();
        List<User> userList = new LinkedList<User>(){
            {
                add(new User("yrh","1234"));
                add(new User("yrh1","1234"));
                add(new User("yrh2","1234"));
                add(new User("yrh3","1234"));
            }
        };
        return new ModelAndView(new ExcelView(), "userList", userList);
    }
    
}

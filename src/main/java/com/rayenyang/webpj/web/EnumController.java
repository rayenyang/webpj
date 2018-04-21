package com.rayenyang.webpj.web;

import com.rayenyang.webpj.entity.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * description:
 * Created by rayenyang on 2017/5/24.
 */
@RestController
public class EnumController {

    @Autowired
    JdbcTemplate jdbcTemplate;
//    @InitBinder
//    protected void initBinder(WebDataBinder binder){
//        binder.registerCustomEditor(Fruit.Color.class, new String2Color());
//    }

    @PostMapping("/enum")
    public Fruit test(@RequestBody Fruit fruit){
//        fruit = new Fruit();
        fruit.setDate(LocalDate.now());
        System.out.println(jdbcTemplate.equals(Fruit.getJdbcTemplate()));
        System.out.println(jdbcTemplate == Fruit.getJdbcTemplate());
        return fruit;
    }

    @GetMapping("/enum2")
    public String test2(){
        System.out.println("test2");
        return "enum2> ---- ";
    }

}

class String2Color implements Converter<String, Fruit.Color>{
    @Override
    public Fruit.Color convert(String s) {
        return Fruit.Color.get(s);
    }
}

//class String2Color extends PropertyEditorSupport {
//    @Override
//    public void setAsText(String text) throws IllegalArgumentException {
//        final Fruit.Color color = Fruit.Color.get(text);
//        setValue(color);
//    }
//}




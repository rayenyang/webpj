package com.rayenyang.webpj.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.rayenyang.webpj.event.FirstEvent;
import com.rayenyang.webpj.util.SpringContextHolder;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

/**
 * description:
 * Created by rayenyang on 2017/5/25.
 */
public class Fruit {
    
    

    private Color color;
    private String name;
    private LocalDate date;
    private static final JdbcTemplate jdbcTemplate;

    static {
        jdbcTemplate = (JdbcTemplate) SpringContextHolder.context.getBean("jdbcTemplate");
    }

    public static JdbcTemplate getJdbcTemplate(){
        return jdbcTemplate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        final String count = jdbcTemplate.queryForObject("SELECT count(*) FROM t_user", String.class);
        System.out.println("user:" + count);
        this.date = date;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "color=" + color +
                ", name='" + name + '\'' +
                '}';
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public enum Color {
        RED("r-red"),BLUE("r-blue");
        private String color;

        Color(String color) {
            this.color = color;
        }

        @JsonCreator
        public static Color get(String color){
            final Color[] values = values();
            for (Color value : values) {
                if(value.toString().equals(color)){
                    return value;
                }
            }
            return null;
        }

        @JsonValue
        @Override
        public String toString() {
            return color;
        }
    }
}
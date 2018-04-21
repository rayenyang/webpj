package com.rayenyang.webpj.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * description:
 * Created by rayenyang on 2017/7/3.
 */
public class Argument {
    @NotNull
    private String name;
    @NotNull
    @Max(10)
    private Integer maxAge;
    
    @Override
    public String toString() {
        return "Argument{" +
                "name='" + name + '\'' +
                ", maxAge=" + maxAge +
                '}';
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getMaxAge() {
        return maxAge;
    }
    
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }
}

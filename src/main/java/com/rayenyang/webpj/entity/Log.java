package com.rayenyang.webpj.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * description:
 * Created by rayenyang on 2017/3/1.
 */
public class Log {
    @JsonProperty("begin")
    private String start;
    private Integer count;


    @Override
    public String toString() {
        return "Log{" +
                "start='" + start + '\'' +
                ", count=" + count +
                '}';
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

package com.rayenyang.webpj.event;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * description:
 * Created by rayenyang on 2017/6/14.
 */
public class FirstEvent extends ApplicationEvent implements Serializable {
    
    private static final long serialVersionUID = 8774128676880423191L;
    private String eventId;
    private int eventLevel;
    
    @Override
    public String toString() {
        return "FirstEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventLevel=" + eventLevel +
                '}';
    }
    
    public FirstEvent(Object source) {
        super(source);
    }
    
    public FirstEvent(Object source, String eventId, int eventLevel) {
        super(source);
        this.eventId = eventId;
        this.eventLevel = eventLevel;
    }
    
    public String getEventId() {
        return eventId;
    }
    
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    
    public int getEventLevel() {
        return eventLevel;
    }
    
    public void setEventLevel(int eventLevel) {
        this.eventLevel = eventLevel;
    }
}

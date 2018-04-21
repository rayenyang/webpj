package com.rayenyang.webpj.event;

import org.springframework.context.ApplicationEvent;

/**
 * description:
 * Created by rayenyang on 2017/6/14.
 */
public class SecondEvent extends ApplicationEvent {
    
    private String eventId;
    private int eventLevel;
    
    @Override
    public String toString() {
        return "SecondEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventLevel=" + eventLevel +
                '}';
    }
    
    public SecondEvent(Object source) {
        super(source);
    }
    
    public SecondEvent(Object source, String eventId, int eventLevel) {
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

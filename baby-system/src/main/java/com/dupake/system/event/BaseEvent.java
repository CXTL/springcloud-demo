package com.dupake.system.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName BaseEvent
 * @Description 超类 事件
 * @Author
 * @Date 2020/6/15 12:25
 */
public abstract class BaseEvent<T> extends ApplicationEvent {

    private static final long serialVersionUID = 895628808370649881L;

    protected T eventData;

    public BaseEvent(Object source, T eventData) {
        super(source);
        this.eventData = eventData;
    }

    public BaseEvent(T eventData) {
        super(eventData);
    }

    public T getEventData() {
        return eventData;
    }

    public void setEventData(T eventData) {
        this.eventData = eventData;
    }
}

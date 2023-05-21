package org.example.customType;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.format.FormatMapper;

import java.io.Serializable;

public class MyJson implements Serializable{
    private String stringProp;

    private Long longProp;

    public String getStringProp() {
        return stringProp;
    }

    public void setStringProp(String stringProp) {
        this.stringProp = stringProp;
    }

    public Long getLongProp() {
        return longProp;
    }

    public void setLongProp(Long longProp) {
        this.longProp = longProp;
    }

}

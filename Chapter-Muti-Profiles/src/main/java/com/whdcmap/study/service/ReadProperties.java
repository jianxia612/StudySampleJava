package com.whdcmap.study.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 蜀山剑侠
 * @version 1.0.0
 */
@Component
public class ReadProperties {

    @Value("${com.whdcmap.study.name}")
    private String name;
    @Value("${com.whdcmap.study.title}")
    private String title;
    @Value("${com.whdcmap.study.desc}")
    private String desc;

    @Value("${com.whdcmap.study.value}")
    private String value;
    @Value("${com.whdcmap.study.number}")
    private Integer number;
    @Value("${com.whdcmap.study.bignumber}")
    private Long bignumber;
    @Value("${com.whdcmap.study.test1}")
    private Integer test1;
    @Value("${com.whdcmap.study.test2}")
    private Integer test2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getBignumber() {
        return bignumber;
    }

    public void setBignumber(Long bignumber) {
        this.bignumber = bignumber;
    }

    public Integer getTest1() {
        return test1;
    }

    public void setTest1(Integer test1) {
        this.test1 = test1;
    }

    public Integer getTest2() {
        return test2;
    }

    public void setTest2(Integer test2) {
        this.test2 = test2;
    }

    @Override
    public String toString() {
        return "BlogProperties{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", value='" + value + '\'' +
                ", number=" + number +
                ", bignumber=" + bignumber +
                ", test1=" + test1 +
                ", test2=" + test2 +
                '}';
    }
}

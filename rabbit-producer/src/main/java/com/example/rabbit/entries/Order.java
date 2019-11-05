package com.example.rabbit.entries;

import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author LinYongJin
 * @date 2019/11/5 20:33
 */
public class Order implements Serializable {

    private String name;

    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}

/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package com.dupake.tools.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public abstract class QueryParam implements Serializable{
    private static final long serialVersionUID = -3263921252635611410L;

	private Integer page =1;
	private Integer limit = 10;
    private String keyword;

    public void setCurrent(Integer current) {
	    if (current == null || current <= 0){
	        this.page = 1;
        }else{
            this.page = current;
        }
    }

    public void setSize(Integer size) {
	    if (size == null || size <= 0){
	        this.limit = 10;
        }else{
            this.limit = size;
        }
    }

}

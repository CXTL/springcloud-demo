package com.dupake.common.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/19 下午3:55
 */
public class PageUtil<T> {


    //2 使用原生 Java api 进行手动分页
    public  static <T>T  getPageContentByApi(List<T> list, int pageNo, int pageSize){

        //总记录数
        int total = list.size();
        // 开始索引
        int fromIndex = (pageNo-1) * pageSize;
        // 结束索引
        int toIndex = fromIndex + pageSize;
        // 如果结束索引大于集合的最大索引，那么规定结束索引=集合大小
        if(toIndex > total){
            toIndex = total;
        }
        if(fromIndex <= total){
           return (T) list.subList(fromIndex, toIndex);
        }
        return (T) new ArrayList<>();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        System.out.println(JSON.toJSONString(getPageContentByApi(list, 3, 2)));
    }

}

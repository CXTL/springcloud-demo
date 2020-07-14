package com.dupake.search.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author zhaoliancan
 * @version 1.0.0
 * @ClassName Person.java
 * @Description TODO
 * @createTime 2019年09月11日 17:47:00
 */
@Data
@Document(indexName = "person")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person implements Serializable {
    @Id
    private String skuId;
    private String name;
    private String address;
    private Integer age;
    private String sex;
    private String city;
}

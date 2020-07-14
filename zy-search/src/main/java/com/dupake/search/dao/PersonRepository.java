package com.dupake.search.dao;

import com.dupake.search.domain.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhaoliancan
 * @version 1.0.0
 * @ClassName PersonRepository.java
 * @Description TODO
 * @createTime 2019年09月11日 17:50:00
 */
public interface  PersonRepository extends ElasticsearchRepository<Person,String> {


}

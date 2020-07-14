package com.dupake.search.service;

import com.dupake.search.domain.Person;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author zhaoliancan
 * @version 1.0.0
 * @ClassName PersonService.java
 * @Description TODO
 * @createTime 2019年09月11日 17:51:00
 */
public interface PersonService {

    long count();

    Person save(Person person);

    void delete(Person person);

    Iterable<Person> getAll();

    List<Person> getByName(String name);

    Page<Person> pageQuery(Integer pageNo, Integer pageSize, String kw);
}

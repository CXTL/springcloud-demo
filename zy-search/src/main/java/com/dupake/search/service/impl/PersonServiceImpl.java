package com.dupake.search.service.impl;

import com.dupake.search.dao.PersonRepository;
import com.dupake.search.domain.Person;
import com.dupake.search.service.PersonService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoliancan
 * @version 1.0.0
 * @ClassName PersonServiceImpl.java
 * @Description TODO
 * @createTime 2019年09月11日 17:53:00
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public long count() {
        return personRepository.count();
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public Iterable<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> getByName(String name) {
        List<Person> personList=new ArrayList<>();
        MatchQueryBuilder queryBuilder=new MatchQueryBuilder("name",name);
        Iterable<Person> personIterable=personRepository.search(queryBuilder);
        personIterable.forEach(personList::add);
        return personList;
    }

    @Override
    public Page<Person> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("name",kw)).withPageable(PageRequest.of(pageNo,pageSize)).build();
        return personRepository.search(searchQuery);
    }
}

package com.example.elasticsearchspringdata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PeopleRepository  extends ElasticsearchRepository <People, String> {
    Page<People> findByName(String name, Pageable pageable);
}

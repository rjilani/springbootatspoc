package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

public interface QuestionsRepository extends CrudRepository<Post, Integer>  {
}

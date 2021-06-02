package com.vague.service.repo;

import com.vague.service.models.BlackList;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepo extends CrudRepository<BlackList, String> {
    int countAllByLogin(String login);
}

package com.vague.service.repo;

import com.vague.service.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Repository
@Component
public interface UsersRepo extends CrudRepository<Users, String> {
}

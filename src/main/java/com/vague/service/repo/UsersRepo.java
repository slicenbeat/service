package com.vague.service.repo;

import com.vague.service.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<Users, String> {
}

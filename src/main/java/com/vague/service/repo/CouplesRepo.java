package com.vague.service.repo;

import com.vague.service.models.Couples;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CouplesRepo extends CrudRepository<Couples, Integer> {
    int countAllByUser1_LoginAndUser2_Login(String login1, String login2);

    //@Query(value = "select login1 from couples where login2 = ?1", nativeQuery = true)
    //List<String> findLogins(String login);
}

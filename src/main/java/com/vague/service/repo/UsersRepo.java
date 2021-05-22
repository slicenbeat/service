package com.vague.service.repo;

import com.vague.service.models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Component
public interface UsersRepo extends CrudRepository<Users, String> {

    @Query(value = "select count(*)*100/(select count(*) from couples where like1 = like2) as result, tab1.age_difference from \n" +
            "(select id as id_couple, abs(user1.age - user2.age) as age_difference from (couples join users user1 on \n" +
            "couples.login1 = user1.login and couples.like1 = couples.like2) join users user2 on couples.login2 = user2.login) tab1\n" +
            "group by age_difference",
            nativeQuery = true)
    List<String> StatistAgeDifference();

    @Query(value = "select user1.gender as gender_1, user2.gender as gender_2, count(*)*100/(select count(*) from couples where like1 = like2) as result \n" +
            "from ((couples join users user1 on couples.login1 = user1.login and couples.like1 = couples.like2) \n" +
            "join users user2 on couples.login2 = user2.login) group by user1.gender, user2.gender",
            nativeQuery = true)
    List<String> StatistGenderCouples();
}

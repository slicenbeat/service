package com.vague.service.repo;

import com.vague.service.models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Repository
@Component
public interface UsersRepo extends CrudRepository<Users, String> {

    int countAllByLogin(String login);

    //Выбор юзера по заданному логину
    Users findByLogin(String login);

    //Выбор всех юзеров, возраст которых находится в заданном диапазоне
    List<Users> findByAgeGreaterThanEqualAndAgeLessThanEqual(int min, int max);

    //Выбор всех юзеров, за исключением самого себя и тех, кого ты уже лайкнул
    @Query(value = "select * from Users where login not in (select login2 from couples where login1 = ?1) and login != ?1 \n" +
            "and login not in (select login1 from couples where login2 = ?1 and like1 = true and like2 = true)",
            nativeQuery = true)
    List<Users> findNeedUsers(String login);

    @Query(value = "select * from Users where login in (select login1 from Couples where like1 = true and like2 = true \n" +
            "and login2 = ?1) or login in (select login2 from Couples where like1 = true and like2 = true and login1 = ?1)",
    nativeQuery = true)
    List<Users> matchsForUsersByLogin(String login);

    //Вывод статистики о процентном соотношении возраста в парах
    @Query(value = "select count(*)*100/(select count(*) from couples where like1 = like2) as result, tab1.age_difference from \n" +
            "(select id as id_couple, abs(user1.age - user2.age) as age_difference from (couples join users user1 on \n" +
            "couples.login1 = user1.login and couples.like1 = couples.like2) join users user2 on couples.login2 = user2.login) tab1\n" +
            "group by age_difference",
            nativeQuery = true)
    List<String> StatistAgeDifference();

    //Вывод статистики о процентном соотношении гендеров в парах
    /*@Query(value = "select user1.gender as gender_1, user2.gender as gender_2, count(*)*100/(select count(*) from couples where like1 = like2) as result \n" +
            "from ((couples join users user1 on couples.login1 = user1.login and couples.like1 = couples.like2) \n" +
            "join users user2 on couples.login2 = user2.login) group by user1.gender, user2.gender",
            nativeQuery = true)
    List<String> StatistGenderCouples();*/
    @Query(value = "select count(*)*100/(select count(*) from couples where like1 = like2) from couples where like1 = like2 " +
            "and ((login1 in (select login from users where gender = ?1)) and (login2 in (select login from users " +
            "where gender = ?2)))", nativeQuery = true)
    double StatCoupGend(String genderOne, String genderTwo);
}

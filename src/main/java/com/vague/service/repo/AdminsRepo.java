package com.vague.service.repo;

import com.vague.service.models.Admins;
import com.vague.service.models.Couples;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdminsRepo extends CrudRepository<Admins, String> {

    //@Query("select  from Couples where user1.age = 20")
    //Admins StatistCouples();
}

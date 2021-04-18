package com.vague.service;

import com.vague.service.models.Couples;
import com.vague.service.models.Users;
import com.vague.service.repo.CouplesRepo;
import com.vague.service.repo.UsersRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
class ServiceApplicationTests {
	@Autowired
	private UsersRepo userRepo;
	private CouplesRepo couplesRepo;

	@Test
	//@Transactional
	void test() {
		//Users user = new Users("women228", "11", "f", "Samara", "name_women1", "", "2222222222", 25);
		//userRepo.save(user);
		userRepo.deleteById("women228");
	}

}

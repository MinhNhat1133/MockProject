package com.vti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{

	User findByEmail(String name);

	boolean existsByEmail(String email);

	@Modifying
	@Transactional
	@Query(value="SELECT u.* FROM users u JOIN orders o ON u.id = o.customer_id WHERE u.email = :email AND o.status < 100 ",nativeQuery = true)
	User findUserByEmail4LogIn(@Param("email") String email);

	@Query(value="SELECT u.* FROM users u JOIN orders o ON u.id = o.customer_id WHERE u.email = :email AND o.status = 100",nativeQuery = true)
	User getUserHasOrderStatusNotActiveByEmail(@Param("email") String email);

}

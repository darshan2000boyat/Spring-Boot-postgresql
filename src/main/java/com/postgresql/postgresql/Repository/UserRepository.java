package com.postgresql.postgresql.Repository;

import com.postgresql.postgresql.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.id > :id1 AND u.id < :id2")
    List<User> findUserInIdRange(@Param("id1") Long min, @Param("id2") Long max);
}

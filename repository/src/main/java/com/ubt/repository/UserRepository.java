package com.ubt.repository;
import com.ubt.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Server, Integer> {
    Server findByUsername(String username);

    Server findById(int id);
}

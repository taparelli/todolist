package br.com.gabrielacosta.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserModelo, UUID>{
    UserModelo findByUsername(String username);
}

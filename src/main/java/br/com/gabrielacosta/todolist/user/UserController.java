package br.com.gabrielacosta.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        private IUserRepository userRepository;

        @PostMapping("/")
        public ResponseEntity creat(@RequestBody UserModelo userModelo){
               var user = this.userRepository.findByUsername(userModelo.getUsername());

                if (user != null) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
                }

                var passwordHashred = BCrypt.withDefaults()
                        .hashToString(12,userModelo.getPassword().toCharArray());
                userModelo.setPassword(passwordHashred);

                var userCreated = this.userRepository.save(userModelo);
                return ResponseEntity.status(HttpStatus.OK).body(userCreated);
        }
}

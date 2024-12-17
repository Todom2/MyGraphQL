package org.example.mygraphqltest.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mygraphqltest.domain.message.service.MessageService;
import org.example.mygraphqltest.domain.user.entity.Users;
import org.example.mygraphqltest.domain.user.entity.UserInput;
import org.example.mygraphqltest.domain.user.repository.UserRepository;
import org.example.mygraphqltest.global.exception.BusinessException;
import org.example.mygraphqltest.global.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    public Mono<Users> findUserByName(String name) {

        return userRepository.findUsersByName(name)
                .switchIfEmpty(Mono.error(new UserNotFoundException("error.user.not_found")));
    }
    public Mono<Users> findUserById(Integer id) {
        return userRepository.findById(id);
    }
    public Flux<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Mono<Users> createUser(UserInput input) {
        return userRepository.existsByName(input.getName())
                .flatMap(exists -> {
                    if (exists) {
                        // 중복된 사용자가 있는 경우 예외를 던진다
                        return Mono.error(new BusinessException.DuplicateUserNameException("error.user.duplicate_name"));
                    } else {
                        // 새 사용자 생성
                        return userRepository.save(Users.builder()
                                .name(input.getName())
                                .email(input.getEmail())
                                .build()
                        );
                    }
                });
    }

    public Mono<Users> updateUserById(Integer id, UserInput input) {
        return userRepository.findById(id)
                .flatMap(existingUsers -> {
                    existingUsers.setName(input.getName());
                    existingUsers.setEmail(input.getEmail());
                    return userRepository.save(existingUsers);
                });
    }

    public Mono<Users> updateUserByName(String name, UserInput input) {
        return userRepository.findUsersByName(name)
                .flatMap(existingUsers -> {
                    existingUsers.setName(input.getName());
                    existingUsers.setEmail(input.getEmail());
                    return userRepository.save(existingUsers);
                });
    }

    public Mono<Users> deleteUserById(Integer id){
        return userRepository.findById(id)
                .flatMap(users -> userRepository.delete(users).then(Mono.just(users)));
    }

    public Mono<Users> deleteUserByName(String name){
        return userRepository.findUsersByName(name)
                .flatMap(users -> userRepository.delete(users).then(Mono.just(users)));
    }
}

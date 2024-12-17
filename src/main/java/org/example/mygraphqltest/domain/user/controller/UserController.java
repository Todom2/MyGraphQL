package org.example.mygraphqltest.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mygraphqltest.domain.user.entity.Users;
import org.example.mygraphqltest.domain.user.entity.UserInput;
import org.example.mygraphqltest.domain.user.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @QueryMapping
    public Mono<Users> justThrow(@Argument String name){
        throw new IllegalArgumentException();
    }
    @QueryMapping
    public Mono<Users> getUserByName(@Argument String name) {
        return userService.findUserByName(name);
    }

    @QueryMapping
    public Mono<Users> getUserById(@Argument Integer id){
        return userService.findUserById(id);
    }
    @QueryMapping
    public Flux<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @MutationMapping
    public Mono<Users> createUser(@Argument UserInput input) {
        return userService.createUser(input);
    }

    @MutationMapping
    public Mono<Users> updateUserById(@Argument Integer id, @Argument UserInput input) {
        return userService.updateUserById(id,input);
    }

    @MutationMapping
    public Mono<Users> updateUserByName(@Argument String name, @Argument UserInput input) {
        return userService.updateUserByName(name,input);
    }

    @MutationMapping
    public Mono<Users> deleteUserById(@Argument Integer id) {
        return userService.deleteUserById(id);
    }

    @MutationMapping
    public Mono<Users> deleteUserByName(@Argument String name) {
        return userService.deleteUserByName(name);
    }
}

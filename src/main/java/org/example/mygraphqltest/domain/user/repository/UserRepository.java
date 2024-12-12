package org.example.mygraphqltest.domain.user.repository;

import org.example.mygraphqltest.domain.user.entity.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<Users, Integer> {
    // 추가적인 쿼리 메서드를 정의할 수 있음 (예: findByEmail, findByName 등)
    Mono<Boolean> existsByName(String Name);
    Mono<Users> findUsersByName(String Name);
}

package org.example.mygraphqltest.domain.message.repository;

import org.example.mygraphqltest.domain.message.entity.Messages;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MessageRepository extends ReactiveCrudRepository<Messages, Long> {
    Mono<Messages> findByLocaleAndCode(String locale, String code);
}

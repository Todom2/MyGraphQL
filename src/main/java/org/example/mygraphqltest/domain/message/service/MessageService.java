package org.example.mygraphqltest.domain.message.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mygraphqltest.domain.message.entity.Messages;
import org.example.mygraphqltest.domain.message.repository.MessageRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    public Mono<String> getMessage(String locale, String code){
        return messageRepository.findByLocaleAndCode(locale,code)
                .map(Messages::getMessage)
                .defaultIfEmpty("[" + code + "]");
    }
}

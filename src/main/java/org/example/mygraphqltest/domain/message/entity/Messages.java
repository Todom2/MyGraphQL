package org.example.mygraphqltest.domain.message.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("messages")
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    @Id
    private Long id;
    private String locale; // 예: "ko_KR", "en_US"
    private String code;   // 예: "error.user.duplicate_name"
    private String message; // 해당 언어로 번역된 메시지

}

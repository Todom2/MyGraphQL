package org.example.mygraphqltest.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.aot.generate.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users") // PostgreSQL의 테이블 이름으로 "user" 사용
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    private Integer id; // Primary key로 사용할 필드

    private String name;

    private String email;

    @Builder
    public static Users toEntity(
            Integer id,
            String name,
            String email
    ) {
        return new Users(
                id,
                name,
                email
        );
    }
}

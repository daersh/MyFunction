package com.daersh.daersh_project.refresh;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.Date;

@Getter

@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("refresh_token")       // key 값을 prefix 함
@ToString
public class Refresh {
    @Id
    private String userId; //refresh:<id> refresh:aaa
    @Indexed // 값으로 검색할 시 추가한다.
    private String refresh;
//    @TimeToLive // 만료시간을 설정(초(second) 단위)
    private Date expiration;
}

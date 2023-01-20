package com.agust.hero.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Hero {
    private long id;
    private String name;
    private String identity;
}

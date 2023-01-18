package com.agust.hero.entity;

import lombok.Builder;

@Builder
public class Hero {
    private long id;
    private String name;
    private String identity;
}

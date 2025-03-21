package com.backend.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Img {
    private String name;  // 图片名称
    private String url;   // 图片 URL
}

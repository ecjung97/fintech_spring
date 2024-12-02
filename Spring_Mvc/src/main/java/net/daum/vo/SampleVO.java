package net.daum.vo;

import lombok.Data;

@Data /* @Data는 lombok library에서 재공하는 annotation으로 setter, getter, toString, equals, hashCode, 기본생성자,
         canEqual메서드까지 자동으로 생성한다. */
public class SampleVO {
    
    private int mno; // 변수명이 JSON데이터의 키이름이 된다.
    private String firstName; // 이름
    private String lastName; // 성
}

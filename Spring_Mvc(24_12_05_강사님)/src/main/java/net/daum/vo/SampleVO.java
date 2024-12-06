package net.daum.vo;

import lombok.Data;

@Data  //@Data는 lombok 라이브러리에서 제공하는 애너테이션으로 setter,getter,toString,equals,hashCode,기본생성자,canEqual메서드까지
//자동으로 생성한다.
public class SampleVO {

	private int mno;//변수명이 JSON데이터의 키이름이 된다.
	private String firstName;//성
	private String lastName;//이름
	
}

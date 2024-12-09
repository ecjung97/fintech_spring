package net.daum.vo;

import lombok.Getter; 
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString(exclude = {"val03"}) // exclude속성을 사용하여 val03변수를 toString() 메서드 사용 시 제외, @ToString lombok 라이브러리
// toString() 메서드 자동제공
public class SampleVO {
	
	private String val01;
	private String val02;
	private String val03;
	
}

package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    // memberForm 쓰는 이유 : 엔티티를 핵심 비즈니스 로직만 두고 뷰에 대한 로직은 따로 둬야 유지보수가 쉬워지기 때문

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}

package com.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
@Setter
// 불변객체로 남기기 위하여 Setter 제거
// 불변이라는 작은 제약으로 부작용이라는 큰 재앙 막기
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // INFO: HHH000182: No default (no-argument) constructor for class: com.vo.Address (class must be instantiated by Interceptor)
    // 오류 해결
    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}

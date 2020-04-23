package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B")  // 싱글 테이블이라 구분할 수 있는 값을 줘야 한다.
@Getter
@Setter
public class Book extends Item {
    private String author;
    private String isbn;
}

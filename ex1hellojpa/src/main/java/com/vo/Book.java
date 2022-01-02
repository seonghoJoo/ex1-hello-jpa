package com.vo;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
@Setter
@Getter
public class Book extends Item{

    private String author;
    private String isbn;
}

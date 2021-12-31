package com.example.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data  //이건 위험하다 쥐의할 필요가 있다. 필요한 어노테이션만 붙여주는걸 추천
//@Getter @Setter
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {}

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

package com.example.itemservice.web.basic;

import com.example.itemservice.domain.item.Item;
import com.example.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }


    //    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item",item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model){
//        @ModelAttribute : 데이터를 객체로 받을 뿐 아니라 지정된 네임태그 기준으로 모델에 자동으로 넣어준다
        itemRepository.save(item);
//        model.addAttribute("item",item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model){
//        @ModelAttribute : 데이터를 객체로 받을 뿐 아니라 지정된 네임태그 기준으로 모델에 자동으로 넣어준다,
//        네임태그가 지정되어 있지 않다면 클래스명(Item -> item)을 기준으로 모델에 담기게 된다.
        itemRepository.save(item);
//        model.addAttribute("item",item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV4(Item item, Model model){
//        @ModelAttribute : 데이터를 객체로 받을 뿐 아니라 지정된 네임태그 기준으로 모델에 자동으로 넣어준다,
//        네임태그가 지정되어 있지 않다면 클래스명(Item -> item)을 기준으로 모델에 담기게 된다.
//        ModelAttribute는 생략가능하다.
        itemRepository.save(item);
//        model.addAttribute("item",item);
        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV5(Item item){//PRG(Post/Redirect/Get) 적용
        itemRepository.save(item);
//        return "basic/item";
        return "redirect:/basic/items/"+item.getId();
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
        itemRepository.save(new Item("itemC",30000,30));
    }
}

package com.AP_assignment_4;

import java.util.ArrayList;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Assignment_testing{
    Functionality f1=new Functionality();

    @Test
    void Item_availability_test(){
        f1.item_name = new ArrayList<>();
        f1.item_prize = new ArrayList<>();
        f1.item_category = new ArrayList<>();
        f1.item_availability = new ArrayList<>();

        // Add basic items
        f1.item_name.add("coffee");
        f1.item_prize.add("40");
        f1.item_category.add("vegetarian");
        f1.item_availability.add("yes");

        f1.item_name.add("tea");
        f1.item_prize.add("20");
        f1.item_category.add("vegetarian");
        f1.item_availability.add("yes");

        f1.item_name.add("pizza");
        f1.item_prize.add("150");
        f1.item_category.add("vegetarian");
        f1.item_availability.add("yes");

        f1.item_name.add("chicken burger");
        f1.item_prize.add("100");
        f1.item_category.add("non-vegetarian");
        f1.item_availability.add("no");

        // Test availability
        assertTrue(f1.check_item_availability("coffee"));
        assertFalse(f1.check_item_availability("chicken burger"));
    }

    @Test
    void adding_item_updating_prize_test(){
        f1.customer_order_and_quantity = new TreeMap<>();
        f1.item_name = new ArrayList<>();
        f1.item_prize = new ArrayList<>();
        f1.item_category = new ArrayList<>();
        f1.item_availability = new ArrayList<>();

        // basic item in menu
        f1.item_name.add("coffee");
        f1.item_prize.add("40");
        f1.item_category.add("vegetarian");
        f1.item_availability.add("yes");

        f1.item_name.add("tea");
        f1.item_prize.add("20");
        f1.item_category.add("vegetarian");
        f1.item_availability.add("yes");

        f1.item_name.add("pizza");
        f1.item_prize.add("150");
        f1.item_category.add("vegetarian");
        f1.item_availability.add("yes");

        f1.item_name.add("chicken burger");
        f1.item_prize.add("100");
        f1.item_category.add("non-vegetarian");
        f1.item_availability.add("no");
        // for vip customer rs 100 extra
        // add item to cart
        f1.customer_order_and_quantity.put("coffee", 10);
        // checking total prize
        assertEquals(500, f1.total_prize_to_pay(1));
        // adding other item to cart and checking updated prize
        f1.customer_order_and_quantity.put("tea", 10);
        assertEquals(700, f1.total_prize_to_pay(1));

        // updating quantity of item in cart and checking updated prize
        f1.customer_order_and_quantity.put("coffee", 20);
        assertEquals(1100, f1.total_prize_to_pay(1));

        // set item quantity to negative and checking the updated prize
        f1.customer_order_and_quantity.put("coffee", -10);
        assertEquals(300, f1.total_prize_to_pay(1));
    }
}
package com.AP_assignment_4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

interface Necessary_Functionality{
    void admin_home_screen();
    void customer_home_screen(int is_vip);
}

class Functionality implements Necessary_Functionality{

    void create_file(String file_name){
        try {
            File f1=new File(file_name);
            // check for file existence
            if(f1.exists()){
                return;
            }
            else{
                f1.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // constructor
    Functionality(){
        // adding few basic things
        add_basic_thing();
        // for necessary file creation
        create_file("order_history_non_vip.txt");
        create_file("order_history_vip.txt");
        create_file("cart_vip.txt");
        create_file("cart_non_vip.txt");
    }

    Scanner s2=new Scanner(System.in);
    int selected_functionality_home_admin;
    int selected_functionality_menu;

    int selected_functionality_home_customer;

    void next_page(){
        int n1=5;
        for(int i=0;i<n1;i++){
            System.out.println();
        }
    }

    // using array list to save data
    ArrayList<String> item_name=new ArrayList<>();
    ArrayList<String> item_prize=new ArrayList<>();
    ArrayList<String> item_category=new ArrayList<>();
    ArrayList<String> item_availability=new ArrayList<>();

    ArrayList<String> pending_orders=new ArrayList<>();
    ArrayList<String> completed_orders=new ArrayList<>();
    ArrayList<String> total_reported_orders=new ArrayList<>();
    ArrayList<String> canceled_orders=new ArrayList<>();
    ArrayList<String> special_request=new ArrayList<>();


    ArrayList<String> normal_customer_orders =new ArrayList<>();
    ArrayList<String> vip_customer_orders =new ArrayList<>();
    TreeMap<String,Integer> customer_order_and_quantity=new TreeMap<>();



    public void add_basic_thing() {
        // Adding items to internal data structures
        item_name.add("coffee");
        item_prize.add("40");
        item_category.add("vegetarian");
        item_availability.add("yes");

        item_name.add("tea");
        item_prize.add("20");
        item_category.add("vegetarian");
        item_availability.add("yes");

        item_name.add("pizza");
        item_prize.add("150");
        item_category.add("vegetarian");
        item_availability.add("yes");

        item_name.add("chicken burger");
        item_prize.add("100");
        item_category.add("non-vegetarian");
        item_availability.add("no");

        // Writing the items to the menu.txt file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu.txt"))) {
            for (int i = 0; i < item_name.size(); i++) {
                String itemLine = item_name.get(i) + "," + item_prize.get(i) + "," + item_availability.get(i);
                writer.write(itemLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }




    // admin home screen
    public void admin_home_screen(){
        System.out.println("                                             Byte Me!                                           ");
        System.out.println();
        System.out.println("Welcome Admin                                                                                  ");
        System.out.println();
        System.out.println("                          Menu Management        □     (For Menu Management Enter : 0)       ");
        System.out.println("                          Order Management       □     (For Order Management Enter : 1)                       ");
        System.out.println("                          Report Generation      □     (For Report Generation Enter : 2)          ");
        System.out.println();
        System.out.println("For Logout : (Enter : 3)                                                                          ");
        System.out.println();
        System.out.print("Your choice : ");
        selected_functionality_home_admin=s2.nextInt();
        s2.nextLine();
        // next page
        next_page();
        // according to chosen functionality
        if(selected_functionality_home_admin==0){
            menu_management();
            admin_home_screen();
        }
        else if(selected_functionality_home_admin==1){
            order_management();
            admin_home_screen();
        }
        else if(selected_functionality_home_admin==2){
            report_generation();
            admin_home_screen();
        }
        else{
            System.out.println("Logout Successfully");
            return;
        }
    }

    // menu management
    public void menu_management(){
        System.out.println("                                             Byte Me!                                           ");
        System.out.println();
        System.out.println("Manage the MENU                                                                                  ");
        System.out.println();
        System.out.println("                     Add new items               □     (For Add new items Enter : 0)       ");
        System.out.println("                     Update existing items       □     (For Update existing items Enter : 1)                       ");
        System.out.println("                     Remove items                □     (For Remove items Enter : 2)          ");
        System.out.println();
        System.out.print("Your choice : ");
        selected_functionality_menu=s2.nextInt();
        s2.nextLine();
        // next page
        next_page();
        // according to chosen functionality
        if(selected_functionality_menu==0){
            String new_item_name;
            String new_item_prize;
            String new_item_category;
            String new_item_availability;
            System.out.print("Enter the name of item : ");
            new_item_name=s2.nextLine();
            System.out.print("Enter the prize of item : ");
            new_item_prize=s2.nextLine();
            System.out.print("Enter the category of item : ");
            new_item_category=s2.nextLine();
            System.out.print("Enter the availability of item (yes/no): ");
            new_item_availability=s2.nextLine();
            item_name.add(new_item_name);
            item_prize.add(new_item_prize);
            item_category.add(new_item_category);
            item_availability.add(new_item_availability);
            // adding in txt file

            // Writing the items to the menu.txt file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu.txt"))){
                for (int i = 0; i < item_name.size(); i++) {
                    String itemLine = item_name.get(i) + "," + item_prize.get(i) + "," + item_availability.get(i);
                    writer.write(itemLine);
                    writer.newLine(); // Adds a new line after each item
                }
            } catch (IOException e) {
                System.out.println("Error writing to the menu.txt file: ");
            }
        }
        else if(selected_functionality_menu==1){
            String updated_item_name;
            String updated_item_prize;
            String updated_item_availability;
            System.out.print("Enter the name of item you want to update : ");
            updated_item_name=s2.nextLine();
            int updated_item_index=item_name.indexOf(updated_item_name);
            System.out.println("Prize of item is : "+item_prize.get(updated_item_index) );
            System.out.println("Availability of item is : "+item_availability.get(updated_item_index) );

            System.out.print("Enter new price of item : ");
            updated_item_prize=s2.nextLine();
            System.out.print("Enter new availability of item (yes/no): ");
            updated_item_availability=s2.nextLine();

            item_prize.set(updated_item_index,updated_item_prize);
            item_availability.set(updated_item_index,updated_item_availability);

            // adding in txt file
            // Writing the items to the menu.txt file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu.txt"))){
                for (int i = 0; i < item_name.size(); i++){
                    String itemLine = item_name.get(i) + "," + item_prize.get(i) + "," + item_availability.get(i);
                    writer.write(itemLine);
                    writer.newLine(); // Adds a new line after each item
                }
            } catch (IOException e) {
                System.out.println("Error writing to the menu.txt file: ");
            }
        }
        else if(selected_functionality_menu==2){
            String deleted_item_name;
            System.out.print("Enter the name of item you want to remove : ");
            deleted_item_name=s2.nextLine();
            int deleted_item_index=item_name.indexOf(deleted_item_name);
            item_name.remove(deleted_item_index);
            item_prize.remove(deleted_item_index);
            item_category.remove(deleted_item_index);
            item_availability.remove(deleted_item_index);

            // adding in txt file
            // Writing the items to the menu.txt file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu.txt"))){
                for (int i = 0; i < item_name.size(); i++) {
                    String itemLine = item_name.get(i) + "," + item_prize.get(i) + "," + item_availability.get(i);
                    writer.write(itemLine);
                    writer.newLine(); // Adds a new line after each item
                }
            } catch (IOException e) {
                System.out.println("Error writing to the menu.txt file: ");
            }
        }
        else{
            System.out.println("Wrong option selected");
            return;
        }
    }

    // order management
    public void order_management(){
        System.out.println("                                             Byte Me!                                           ");
        System.out.println();
        System.out.println("Manage the ORDERS                                                                                 ");
        System.out.println();
        System.out.println("                  View pending orders            □     (For View pending orders Enter : 0)             ");
        System.out.println("                  Update order status            □     (For Update order status Enter : 1)            ");
        System.out.println("                  Process refunds                □     (For Process refunds Enter : 2)                ");
        System.out.println("                  Handle special requests        □     (For Handle special requests Enter : 3)          ");
        System.out.println();
        System.out.print("Your choice : ");
        selected_functionality_menu=s2.nextInt();
        s2.nextLine();
        // next page
        next_page();
        // according to chosen functionality
        if(selected_functionality_menu==0){
            System.out.println("List of Pending Orders :- ");
            // print pending order
            for(String a : pending_orders){
                System.out.println(a);
            }
        }
        else if(selected_functionality_menu==1){
            System.out.println("Updating order status :-");
            for(int i=0;i<pending_orders.size();i++){
                String is_make;
                System.out.println("Item name is : "+pending_orders.get(i));
                System.out.print("Do you want to mark order as complete(y or n) : ");
                is_make=s2.nextLine();
                if(is_make.equals("y")){
                    completed_orders.add(pending_orders.get(i));
                    total_reported_orders.add(pending_orders.get(i));
                }
                else{
                    canceled_orders.add(pending_orders.get(i));
                }
            }
            pending_orders.clear();
            try {
                FileWriter f4=new FileWriter("pending_orders.txt");
                f4.close();
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        else if(selected_functionality_menu==2){
            System.out.println("Refunded items :-");
            for(int i=0;i<canceled_orders.size();i++){
                String is_cancel;
                System.out.println("Item name is : "+canceled_orders.get(i));
                System.out.print("Do you want to refund for this order (y or n) : ");
                is_cancel=s2.nextLine();
                if(is_cancel.equals("y")){
                }
                else{
                    canceled_orders.remove(i);
                }
            }
        }
        else if(selected_functionality_menu==3){
            System.out.println("Handling special request :-");
            for(int i=0;i<special_request.size();i++){
                System.out.println(special_request.get(i));
            }
        }
        else{
            System.out.println("Wrong option selected");
            return;
        }
    }

    // report generation
    public void report_generation(){
        int total_sales=0;
        System.out.println("Daily sales report :-");
        System.out.println();
        for(int i=0;i<total_reported_orders.size();i++){
            System.out.println(total_reported_orders.get(i));
            int item_ind=item_name.indexOf(total_reported_orders.get(i));
            total_sales=total_sales+Integer.parseInt(item_prize.get(item_ind));
        }
        System.out.println();
        System.out.println("Total sales for today is : "+total_sales);
    }






    // customer home screen
    public void customer_home_screen(int is_vip){
        System.out.println("                                             Byte Me!                                           ");
        System.out.println();
        System.out.println("Welcome Customer                                                                                  ");
        System.out.println();
        System.out.println("                            Browse Menu           □     (For Browse Menu Enter : 0)                   ");
        System.out.println("                            Cart Operations       □     (For Cart Operations Enter : 1)                       ");
        System.out.println("                            Order Tracking        □     (For Order Tracking Enter : 2)                       ");
        System.out.println();
        System.out.println("For Logout : (Enter : 3)                                                                          ");
        System.out.println();
        System.out.print("Your choice : ");
        selected_functionality_home_customer=s2.nextInt();
        s2.nextLine();
        // next page
        next_page();
        // according to chosen functionality
        if(selected_functionality_home_customer==0){
            browse_menu();
            customer_home_screen(is_vip);
        }
        else if(selected_functionality_home_customer==1){
            cart_operations(is_vip);
            customer_home_screen(is_vip);
        }
        else if(selected_functionality_home_customer==2){
            order_tracking();
            customer_home_screen(is_vip);
        }
        else{
            System.out.println("Logout Successfully");
            return;
        }
    }

    // Browse menu
    public void browse_menu(){
        System.out.println("                                             Byte Me!                                           ");
        System.out.println();
        System.out.println("Browse the MENU                                                                                  ");
        System.out.println();
        System.out.println("                     View all items             □     (For View all items Enter : 0)       ");
        System.out.println("                     Search functionality       □     (For Search functionality Enter : 1)     ");
        System.out.println("                     Filter by category         □     (For Filter by category Enter : 2)     ");
        System.out.println("                     Sort by price              □     (For Sort by price Enter : 3)          ");
        System.out.println();
        System.out.print("Your choice : ");
        selected_functionality_menu=s2.nextInt();
        s2.nextLine();
        // next page
        next_page();
        // according to chosen functionality
        if(selected_functionality_menu==0){
            System.out.println("Viewing all items :-");
            System.out.println();
            for(int i=0;i<item_name.size();i++){
                System.out.println("Item name : "+item_name.get(i));
                System.out.println("Item prize : "+item_prize.get(i));
                System.out.println("Item category : "+item_category.get(i));
                System.out.println("Item availability : "+item_availability.get(i));
                System.out.println();
            }
        }
        else if(selected_functionality_menu==1){
            System.out.println("Searching for item :-");
            System.out.println();
            String search_item_name;

            System.out.print("Enter the name of item you want to search for : ");
            search_item_name=s2.nextLine();
            int item_index=item_name.indexOf(search_item_name);
            System.out.println("Prize of item is : "+item_prize.get(item_index) );
            System.out.println("Category of item is : "+item_category.get(item_index) );
            System.out.println("Availability of item is : "+item_availability.get(item_index) );
        }
        else if(selected_functionality_menu==2){
            System.out.println("Filter by category :-");
            System.out.println();

            String selected_item_category;
            System.out.print("Enter the category of item you want to order : ");
            selected_item_category=s2.nextLine();
            for(int i=0;i<item_category.size();i++){
                if(selected_item_category.equals(item_category.get(i))){
                    System.out.println("Item name : "+item_name.get(i));
                    System.out.println("Item prize : "+item_prize.get(i));
                    System.out.println("Item availability : "+item_availability.get(i));
                    System.out.println();
                }
            }
        }
        else if(selected_functionality_menu==3){
            System.out.println("Sort by prize :-");
            System.out.println();

            ArrayList<Integer> temp1=new ArrayList<>();
            ArrayList<String> temp2=new ArrayList<>();
            for( String i:item_prize){
                temp1.add(Integer.parseInt(i));
            }
            Collections.sort(temp1);
            for(int i:temp1){
                temp2.add(Integer.toString(i));
            }

            for(String i:temp2){
                int ind_of_item=item_prize.indexOf(i);
                System.out.println("Item name : "+item_name.get(ind_of_item));
                System.out.println("Item prize : "+item_prize.get(ind_of_item));
                System.out.println("Item category : "+item_category.get(ind_of_item));
                System.out.println("Item availability : "+item_availability.get(ind_of_item));
                System.out.println();
            }
        }
        else{
            System.out.println("Wrong option selected");
            return;
        }
    }

    // checking item availability
    public boolean check_item_availability(String given_item_name){
        int given_item_index=item_name.indexOf(given_item_name);
        if(item_availability.get(given_item_index).equals("yes")){
            return true;
        }
        else{
            return false;
        }
    }

    // check total prize to pay
    public int total_prize_to_pay(int is_vip){
        int total_prize=0;
        for (Map.Entry<String,Integer> entry : customer_order_and_quantity.entrySet()){
            String key=entry.getKey();
            Integer value=entry.getValue();
            if(value <0){
                value=0;
            }
            int item_ind=item_name.indexOf(key);
            int prize_of_one_item=Integer.parseInt(item_prize.get(item_ind));
            total_prize=total_prize+ (prize_of_one_item * value);
        }
        if(is_vip==1){
            total_prize=total_prize+100;
        }
        return total_prize;
    }

    // cart operations
    public void cart_operations(int is_vip){
        System.out.println("                                             Byte Me!                                           ");
        System.out.println();
        System.out.println("Cart OPERATIONS                                                                                  ");
        System.out.println();
        System.out.println("                     Add items               □     (For Add items Enter : 0)             ");
        System.out.println("                     Modify quantities       □     (For Modify quantities Enter : 1)     ");
        System.out.println("                     Remove items            □     (For Remove items Enter : 2)          ");
        System.out.println("                     View total              □     (For View total Enter : 3)            ");
        System.out.println("                     Checkout process        □     (For Checkout process Enter : 4)     ");
        System.out.println();
        System.out.print("Your choice : ");
        selected_functionality_menu=s2.nextInt();
        s2.nextLine();
        // next page
        next_page();

        // according to chosen functionality
        if(selected_functionality_menu==0){
            // saving order history and cart data
            if(is_vip == 1){
                // for vip
                try{
                    FileWriter f1=new FileWriter("order_history_vip.txt",true);
                    String selected_item_name;
                    String selected_item_quantity;
                    int selected_item_index;

                    System.out.println("Add items to cart:-");
                    System.out.println();
                    System.out.print("Enter the name of item you want : ");
                    selected_item_name=s2.nextLine();
                    System.out.print("Enter the quantity of item you want : ");
                    selected_item_quantity=s2.nextLine();
                    selected_item_index=item_name.indexOf(selected_item_name);
                    if(check_item_availability(selected_item_name)){
                        customer_order_and_quantity.put(selected_item_name,Integer.parseInt(selected_item_quantity));
                        System.out.println("Item added successfully");
                        // writing order history
                        f1.write(selected_item_name+"\n");
                        f1.write(selected_item_quantity+"\n");
                        f1.write(item_prize.get(selected_item_index)+"\n");
                        f1.write("\n");
                    }
                    else{
                        System.out.println("Sorry item not available");
                    }
                    f1.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                // non vip
                try {
                    FileWriter f1=new FileWriter("order_history_non_vip.txt",true);

                    String selected_item_name;
                    String selected_item_quantity;
                    int selected_item_index;

                    System.out.println("Add items to cart:-");
                    System.out.println();
                    System.out.print("Enter the name of item you want : ");
                    selected_item_name=s2.nextLine();
                    System.out.print("Enter the quantity of item you want : ");
                    selected_item_quantity=s2.nextLine();
                    selected_item_index=item_name.indexOf(selected_item_name);
                    if(check_item_availability(selected_item_name)){
                        customer_order_and_quantity.put(selected_item_name,Integer.parseInt(selected_item_quantity));
                        System.out.println("Item added successfully");
                        // writing order history
                        f1.write(selected_item_name+"\n");
                        f1.write(selected_item_quantity+"\n");
                        f1.write(item_prize.get(selected_item_index)+"\n");
                        f1.write("\n");
                    }
                    else{
                        System.out.println("Sorry item not available");
                    }
                    f1.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if(selected_functionality_menu==1){
            System.out.println("Modify quantity for items :-");
            System.out.println();
            for (Map.Entry<String,Integer> entry : customer_order_and_quantity.entrySet()){
                String key=entry.getKey();
                Integer value=entry.getValue();
                System.out.println("Name of item : "+key);
                System.out.println("Quantity of item : "+value);
                System.out.println();
                System.out.print("Enter the new quantity of item : ");
                String new_quantity=s2.nextLine();
                customer_order_and_quantity.put(key,Integer.parseInt(new_quantity));
                System.out.println();
            }
        }
        else if(selected_functionality_menu==2){
            System.out.println("Remove items :-");
            System.out.println();
            String selected_item_name;

            System.out.print("Enter the name of item you want to remove : ");
            selected_item_name=s2.nextLine();
            if(customer_order_and_quantity.containsKey(selected_item_name)){
                customer_order_and_quantity.remove(selected_item_name);
                System.out.println("Item Removed Successfully");
            }
            else{
                System.out.println("Item is not in the cart");
            }
        }
        else if(selected_functionality_menu==3){
            System.out.println("Total prize to pay :-");
            System.out.println();

            int total_prize=total_prize_to_pay(is_vip);
            if(is_vip==1){
                System.out.println("You have to pay RS-100 extra for vip services. Thanks !");
                System.out.println("Your total cart value is : "+total_prize);
                System.out.println();
            }
        }
        else if(selected_functionality_menu==4){
            System.out.println("Checkout process :-");
            System.out.println();
            String is_confirm;
            for (Map.Entry<String,Integer> entry : customer_order_and_quantity.entrySet()){
                String key=entry.getKey();
                Integer value=entry.getValue();
                System.out.println("Name of item : "+key);
                System.out.println("Quantity of item : "+value);
                System.out.println();
            }
            System.out.print("For confirming your order enter (y or n) : ");
            is_confirm=s2.nextLine();
            int total_prize=0;
            for (Map.Entry<String,Integer> entry : customer_order_and_quantity.entrySet()){
                String key=entry.getKey();
                Integer value=entry.getValue();

                int item_ind=item_name.indexOf(key);
                int prize_of_one_item=Integer.parseInt(item_prize.get(item_ind));
                total_prize=total_prize+ (prize_of_one_item * value);
            }
            if(is_confirm.equals("y")){
                if(is_vip==1){
                    System.out.println("You have to pay RS-100 extra for vip services. Thanks !");
                    total_prize=total_prize+100;
                }
                System.out.println("Your total amount to pay is : "+total_prize);
                String amount_to_pay;
                System.out.print("Enter amount to pay : ");
                amount_to_pay=s2.nextLine();
                if(Integer.parseInt(amount_to_pay)<total_prize){
                    System.out.println("Insufficient Balance");
                }
                else{
                    System.out.println("Thanks, order placed successfully");
                    System.out.println("Your change is RS-"+(Integer.parseInt(amount_to_pay)-total_prize));
                    // updating list of orders for admin
                    for (Map.Entry<String,Integer> entry : customer_order_and_quantity.entrySet()){
                        String key=entry.getKey();
                        Integer value=entry.getValue();
                        if(is_vip==0){
                            // writing into cart
                            try {
                                FileWriter f2=new FileWriter("cart_non_vip.txt",true);
                                for(int i=0;i<value;i++){
                                    normal_customer_orders.add(key);
                                    f2.write(key+"\n");
                                }
                                f2.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else{
                            try {
                                FileWriter f2=new FileWriter("cart_vip.txt",true);
                                for(int i=0;i<value;i++){
                                    vip_customer_orders.add(key);
                                    f2.write(key+"\n");
                                }
                                f2.close();
                            } catch (IOException e){
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    // add in beginning for vip customers
                    pending_orders.addAll(0,vip_customer_orders);
                    // add in priority for normal customer
                    pending_orders.addAll(normal_customer_orders);

                    // writing to pending_order.txt
                    try {
                        FileWriter f3=new FileWriter("pending_orders.txt",true);
                        for(String i : pending_orders){
                            f3.write(i+"\n");
                        }
                        f3.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    // clear after adding to order list
                    vip_customer_orders.clear();
                    normal_customer_orders.clear();
                    customer_order_and_quantity.clear();
                }
                System.out.println();
                String special_request_by_customer;
                System.out.print("Enter any special request you would like for the order : ");
                special_request_by_customer=s2.nextLine();
                special_request.add(special_request_by_customer);
                System.out.println();
            }
            else{
                System.out.println("Order denied by customer");
            }
        }
        else{
            System.out.println("Wrong option selected");
            return;
        }
    }

    // order tracking
    public void order_tracking(){
        System.out.println("                                             Byte Me!                                           ");
        System.out.println();
        System.out.println("Order tracking                                                                                  ");
        System.out.println();
        System.out.println("                     View order status        □     (For View order status Enter : 0)       ");
        System.out.println("                     Cancel order             □     (For Cancel order Enter : 1)         ");
        System.out.println();
        System.out.print("Your choice : ");
        selected_functionality_menu=s2.nextInt();
        s2.nextLine();
        // next page
        next_page();
        // according to chosen functionality
        if(selected_functionality_menu==0){
            System.out.println("View order status :-");
            System.out.println();
            String item_check_status;

            System.out.print("Enter the item name to see the status : ");
            item_check_status=s2.nextLine();
            if(pending_orders.contains(item_check_status)){
                System.out.println("Your order is still pending !,You can cancel it if you want");
            }
            else if(completed_orders.contains(item_check_status)){
                System.out.println("Your order has been completed and will be delivered soon");
            }
            else if(canceled_orders.contains(item_check_status)){
                System.out.println("Sorry your order is canceled,your money will be refunded soon");
            }
            else{
                System.out.println("Wrong item selected");
            }
            System.out.println();
        }
        else if(selected_functionality_menu==1){
            System.out.println("Cancel order :-");
            System.out.println();
            String is_cancel;
            String item_to_cancel;

            System.out.print("Do you want to cancel your order(y or n) : ");
            is_cancel=s2.nextLine();
            if(is_cancel.equals("y")){
                System.out.print("Enter the item name you want to cancel : ");
                item_to_cancel=s2.nextLine();
                if(pending_orders.contains(item_to_cancel)){
                    pending_orders.remove(item_to_cancel);
                    System.out.println("Successfully canceled your order! your money will be refunded soon");
                }
                else{
                    System.out.println("Sorry ,But you order has been completed and can't be canceled");
                }
            }
            else{
                System.out.println("Thank !");
            }
            System.out.println();
        }
        else{
            System.out.println("Wrong option selected");
            return;
        }
    }
}
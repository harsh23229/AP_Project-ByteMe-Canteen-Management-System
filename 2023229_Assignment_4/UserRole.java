package com.AP_assignment_4;

import java.util.Scanner;

class Login_interface{
    Scanner s1=new Scanner(System.in);
    int role;
    // flag for checking vip customer
    int is_vip;

    void next_page(){
        int n1=5;
        for(int i=0;i<n1;i++){
            System.out.println();
        }
    }
    public void user_role() {
        System.out.println("                                             Byte Me!                                           ");
        System.out.println();
        System.out.println("WELCOME                                                                                          ");
        System.out.println();
        System.out.println("                                    Choose a Role to continue                                   ");
        System.out.println("                            Administrator           □   (For Administrator Enter : 0)            ");
        System.out.println("                            Customer(VIP)           □   (For Customer (VIP) Enter : 1)            ");
        System.out.println("                            Customer(REGULAR)       □   (For Customer (Regular) Enter : 2)        ");
        System.out.println("                            GUI Viewer              □   (To View Menu and Orders Enter : 3)      ");
        System.out.println();
        System.out.println("For Exit : (Enter : 4)                                                                          ");
        System.out.println();
        System.out.print("Your choice : ");
        role = s1.nextInt();
        s1.nextLine();
        next_page();

        if(role==0){
            System.out.println("Welcome, Administrator!");
        }
        else if(role==1){
            is_vip=1;
            System.out.println("Welcome, VIP Customer!");
        }
        else if (role==2) {
            is_vip=0;
            System.out.println("Welcome, Regular Customer!");
        }
        else if(role==3){
            System.out.println("Launching GUI for menu and orders...");
        }
        else{
            System.out.println();
            System.out.println("Exited Application Successfully");
        }
    }
}

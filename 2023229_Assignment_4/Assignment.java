package com.AP_assignment_4;

// Encapsulation
public class Assignment {

    // method for user role
    static void ask_for_user_role() {
        Login_interface l1 = new Login_interface();
        l1.user_role();

        if(l1.role == 0){
            // Administrator functionality
            admin_fun();
        }
        else if (l1.role==1){
            // vip Customer functionality
            customer_fun(l1.is_vip);
        }
        else if (l1.role==2){
            // regular Customer functionality
            customer_fun(l1.is_vip);
        }
        else if (l1.role==3){
            // launch gui
            launch_gui();
            ask_for_user_role();
        }
        else if (l1.role == 4){
            // exit the application
            return;
        }
    }

    // functionality class
    static Functionality s1=new Functionality();
    //  customer functionality
    static void customer_fun(int is_vip){
        s1.customer_home_screen(is_vip);
        // logout from customer functionality
        ask_for_user_role();
    }
    //  admin functionality
    static void admin_fun(){
        s1.admin_home_screen();
        // logout from admin functionality
        ask_for_user_role();
    }
    // Method to launch the GUI
    static void launch_gui() {
        // Instantiate the GUI
        CanteenGUI gui = new CanteenGUI();
        gui.show();
    }

    // main method to start the program
    public static void main(String[] args){
        // asking for user role
        ask_for_user_role();
    }
}
package com.AP_assignment_4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

// class for gui
class CanteenGUI{
    private JFrame frame;
    private JPanel menu_panel;
    private JPanel orders_panel;
    private JTable menu_table;
    private JTable orders_table;


    // constructor
    public CanteenGUI(){
        setupFrame();
        setup_menu_panel();
        setuporders_panel();
    }

    private void setupFrame(){
        frame=new JFrame("Welcome To BYTE ME!");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());
    }

    private void setup_menu_panel() {
        menu_panel=new JPanel(new BorderLayout());
        JLabel menuLabel=new JLabel("Menu", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 30));

        DefaultTableModel menuModel=new DefaultTableModel(new String[]{"Name","Price","Availability"},0);
        menu_table=new JTable(menuModel);
        loadMenuData(menuModel);

        JScrollPane scrollPane=new JScrollPane(menu_table);
        JButton viewOrdersButton=new JButton("View Orders");
        viewOrdersButton.addActionListener(e -> switchPanel("orders"));

        menu_panel.add(menuLabel,BorderLayout.NORTH);
        menu_panel.add(scrollPane,BorderLayout.CENTER);
        menu_panel.add(viewOrdersButton,BorderLayout.SOUTH);
        frame.add(menu_panel, "menu");
    }

    private void setuporders_panel(){
        orders_panel=new JPanel(new BorderLayout());
        JLabel ordersLabel=new JLabel("Pending Orders", SwingConstants.CENTER);
        ordersLabel.setFont(new Font("Arial", Font.BOLD,30));

        DefaultTableModel ordersModel=new DefaultTableModel(new String[]{"Order"},0);
        orders_table = new JTable(ordersModel);
        loadOrdersData(ordersModel);

        JScrollPane scrollPane = new JScrollPane(orders_table);
        JButton viewMenuButton = new JButton("View Menu");
        viewMenuButton.addActionListener(e -> switchPanel("menu"));

        orders_panel.add(ordersLabel, BorderLayout.NORTH);
        orders_panel.add(scrollPane, BorderLayout.CENTER);
        orders_panel.add(viewMenuButton, BorderLayout.SOUTH);

        frame.add(orders_panel, "orders");
    }

    private void switchPanel(String panelName){
        CardLayout cl=(CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), panelName);
    }
    private void loadMenuData(DefaultTableModel model){
        try (BufferedReader br = new BufferedReader(new FileReader("menu.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                model.addRow(line.split(","));
            }
        }catch (IOException e){
            System.out.println("Error loading menu data.");
        }
    }

    private void loadOrdersData(DefaultTableModel model) {
        try (BufferedReader br = new BufferedReader(new FileReader("pending_orders.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                model.addRow(new Object[]{line});
            }
        } catch (IOException e){
            System.out.println("Error loading orders data.");
        }
    }

    public void show() {
        frame.setVisible(true);
    }
}

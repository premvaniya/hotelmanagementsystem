package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import java.awt.event.*;

public class Checkout extends JFrame implements ActionListener {
    Choice ccustomer;
    JLabel lblroomnumber, lblcheckintime, lblcheckouttime;
    JButton checkout, back;
    Conn c;

    Checkout() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblid = new JLabel("Customer ID");
        lblid.setBounds(30, 80, 100, 30);
        add(lblid);

        ccustomer = new Choice();
        ccustomer.setBounds(150, 80, 150, 25);
        add(ccustomer);

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 30);
        add(lblroom);

        lblroomnumber = new JLabel("");
        lblroomnumber.setBounds(200, 130, 100, 30);
        add(lblroomnumber);

        JLabel lblcheckin = new JLabel("Check-in Time");
        lblcheckin.setBounds(30, 180, 100, 30);
        add(lblcheckin);

        lblcheckintime = new JLabel("");
        lblcheckintime.setBounds(200, 180, 100, 30);
        add(lblcheckintime);

        JLabel lblcheckout = new JLabel("Checkout Time");
        lblcheckout.setBounds(30, 230, 100, 30);
        add(lblcheckout);

        Date date = new Date();
        lblcheckouttime = new JLabel("" + date);
        lblcheckouttime.setBounds(200, 230, 100, 30);
        add(lblcheckouttime);

        checkout = new JButton("Checkout");
        checkout.setBounds(30, 280, 120, 30);
        checkout.addActionListener(this);
        add(checkout);

        back = new JButton("Back");
        back.setBounds(170, 280, 120, 30);
        back.addActionListener(this);
        add(back);

        // Initializing Conn once in the constructor
        c = new Conn();

        // Populate the Choice component with customer IDs
        try {
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                ccustomer.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setBounds(300, 200, 800, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == checkout) {
            String selectedCustomer = ccustomer.getSelectedItem();
            if (selectedCustomer != null) {
                try {
                    // Retrieve the details of the selected customer
                    ResultSet rs = c.s.executeQuery("select * from customer where number = '" + selectedCustomer + "'");
                    if (rs.next()) {
                        String roomNumber = rs.getString("room");

                        // Perform checkout operations
                        String query1 = "delete from customer where number = '" + selectedCustomer + "'";
                        String query2 = "update rooms set availabilility = 'Available' where roomnumber = '" + roomNumber + "'";
                        
                        // Use executeUpdate for DELETE and UPDATE operations
                        c.s.executeUpdate(query1);
                        c.s.executeUpdate(query2);

                        JOptionPane.showMessageDialog(null, "Checkout done");
                        setVisible(false);
                        new Reception();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selected customer not found.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error during checkout");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a customer.");
            }
        } else {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String args[]) {
        new Checkout();
    }
}

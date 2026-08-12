package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {
   
    MiniStatement(String pinnumber) {
         setTitle("Mini Statement");

         setLayout(null);

         JLabel mini = new JLabel();
         add(mini);
         
         JLabel bank = new JLabel("State Bank Of India");
         bank.setBounds(150, 20, 130, 20);
         add(bank);

         JLabel card = new JLabel();
         card.setBounds(20, 80, 300, 20);
         add(card);
         
         JLabel bal = new JLabel();
         bal.setBounds(20, 400, 300, 20);
         add(bal);

         try {
             Conn conn = new Conn();
             ResultSet rs = conn.s.executeQuery("select * from login where pin = '"+pinnumber+"'");
             while (rs.next()) {
                 card.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
             }
         } catch (Exception e) {
             System.out.println(e);
         }
         
         try {
             Conn conn = new Conn();
             int balance = 0;             
             ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pinnumber+"'"); 
             while (rs.next()) {
               mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");  
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
             }
             bal.setText("Your current account balance is Rs " + balance);
         } catch (Exception e) {
            System.out.println(e);
         }
         
         mini.setBounds(20, 140, 400, 200);

         setSize(400, 600);
         setLocation(20, 20);
         getContentPane().setBackground(Color.WHITE);
         setVisible(true);
     }

     public static void main(String args[]) {
         new MiniStatement("");
    }
    
}


package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AddRooms extends JFrame implements ActionListener{
    JButton add, cancel;
    JTextField tfroom, tfprice;
    JComboBox bedcombo, availablecombo, cleancombo;
    AddRooms(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("ADD ROOMS");
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        heading.setBounds(150,20,200,20);
        add(heading);
        
        JLabel lblroomno = new JLabel("ROOM NO:");
        lblroomno.setFont(new Font("Tahoma",Font.BOLD,16));
        lblroomno.setBounds(60,80,120,30);
        add(lblroomno);
        tfroom = new JTextField();
        tfroom.setBounds(200,80,150,30);
        add(tfroom);
        
        JLabel lblavailable = new JLabel("AVAILABLE:");
        lblavailable .setFont(new Font("Tahoma",Font.BOLD,16));
        lblavailable .setBounds(60,130,120,30);
        add(lblavailable );
        String availableOptions[] = {"Available","Occupied"};
        availablecombo = new JComboBox(availableOptions);
        availablecombo.setBounds(200,130,150,30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        JLabel lblclean = new JLabel("CLEANING:");
        lblclean .setFont(new Font("Tahoma",Font.BOLD,16));
        lblclean .setBounds(60,180,120,30);
        add(lblclean);
        String cleanOptions[] = {"Cleaned","Dirty"};
        cleancombo = new JComboBox(cleanOptions);
        cleancombo.setBounds(200,180,150,30);
        cleancombo.setBackground(Color.WHITE);
        add(cleancombo);
        
        JLabel lblprice = new JLabel("PRICE :");
        lblprice.setFont(new Font("Tahoma",Font.BOLD,16));
        lblprice .setBounds(60,230,120,30);
        add(lblprice);
        tfprice = new JTextField();
        tfprice.setBounds(200,230,150,30);
        add(tfprice);
        
        JLabel lbltype = new JLabel("BED:");
        lbltype .setFont(new Font("Tahoma",Font.BOLD,16));
        lbltype .setBounds(60,280,120,30);
        add(lbltype);
        String typeOptions[] = {"Single Bed","Double Bed"};
        bedcombo = new JComboBox(typeOptions);
        bedcombo.setBounds(200,280,150,30);
        bedcombo.setBackground(Color.WHITE);
        add(bedcombo);
        
        add =  new JButton("ADD ROOM");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds(60,350,130,30);
        add.addActionListener(this);
        add(add);
        cancel =  new JButton("CANCEL");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(220,350,130,30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400,30,500,300);
        add(image);
        
        setBounds(330,200,940,470);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == add){
            String roomnumber =tfroom.getText();
            String availability = (String) availablecombo.getSelectedItem();
            String status = (String) cleancombo.getSelectedItem();
            String price = tfprice.getText();
            String type = (String) bedcombo.getSelectedItem();
            if(roomnumber.equals("")){
            JOptionPane.showMessageDialog(null,"room number is required" );
            return;
            }
            if(price.equals("")){
            JOptionPane.showMessageDialog(null,"price is required" );
            return;
            }
            try{
                Conn conn =new Conn();
                String str = "insert into rooms values('"+roomnumber+"','"+availability+"','"+status+"','"+price+"','"+type+"')";
                conn.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null,"New Room Added Successfully");
            }catch(Exception e){
                e.printStackTrace();
            }
    }else{
            setVisible(false);
        }
    }
    public static void main(String[] args){
        new AddRooms();
    }
}

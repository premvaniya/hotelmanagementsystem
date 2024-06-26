
package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Date;
public class AddCustomer extends JFrame implements ActionListener{
    JComboBox comboid;
    JTextField tfnumber, tfname, tfcountry, tfdeposit;
    JRadioButton rmale, rfemale;
    Choice croom;
    JLabel checkintime;
    JButton add, back;
    
    AddCustomer(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel("NEW CUSTOMER FORM"); 
        text.setBounds(100,20,300,30);
        text.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(text);
        
        JLabel lblid = new JLabel("ID"); 
        lblid.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblid.setBounds(35,80,100,20);
        add(lblid);
        
        String options[]={"Aadhar Card", "Passport", "Driving License", "Voter-id Card", "Ration card" };
        comboid = new JComboBox(options);
        comboid.setBounds(200,80,150,25);
        comboid.setBackground(Color.WHITE);
        add(comboid);
        
        JLabel lblnumber = new JLabel("ID NO:"); 
        lblnumber.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblnumber.setBounds(35,120,100,20);
        add(lblnumber);
        tfnumber = new JTextField();
        tfnumber.setBounds(200,120,150,25);
        add(tfnumber);
        
        JLabel lblname = new JLabel("NAME:"); 
        lblname.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblname.setBounds(35,160,100,20);
        add(lblname);
        tfname = new JTextField();
        tfname.setBounds(200,160,150,25);
        add(tfname);
       
        
        JLabel lblgender = new JLabel("GENDER:"); 
        lblgender.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblgender.setBounds(35,200,100,20);
        add(lblgender);
        rmale = new JRadioButton("MALE");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200,200,60,20);
        add(rmale);
        rfemale = new JRadioButton("FEMALE");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(270,200,100,25);
        add(rfemale);
         ButtonGroup bg = new ButtonGroup();
        bg.add(rmale);
        bg.add(rfemale);
        
        JLabel lblcountry = new JLabel("COUNTRY:"); 
        lblcountry.setFont(new Font("Raleway", Font.PLAIN, 18));
        lblcountry.setBounds(35,240,100,20);
        add(lblcountry);
        tfcountry = new JTextField();
        tfcountry.setBounds(200,240,150,25);
        add(tfcountry);    
        
        JLabel lblroom = new JLabel("ROOM N0:"); 
        lblroom.setFont(new Font("Raleway", Font.PLAIN, 16));
        lblroom.setBounds(35,280,100,20);
        add(lblroom);
        croom = new Choice();
        try{
            Conn conn= new Conn();
            String query ="select * from rooms where availabilility = 'Available'";
            ResultSet rs =conn.s.executeQuery(query);
            while(rs.next()){
                croom.add(rs.getString("roomnumber"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        croom.setBounds(200,280,150,25);
        
        add(croom);
        
        JLabel lbltime = new JLabel("CHECK-IN:"); 
        lbltime.setFont(new Font("Raleway", Font.PLAIN, 16));
        lbltime.setBounds(35,320,100,20);
        add(lbltime);
        
        Date date = new Date();
        //1 + 2 + 3 +4 + 5 + "Code for Interview" +1 + 2 + 3 +4 + 5 
        checkintime = new JLabel(""+date); 
        checkintime.setFont(new Font("Raleway", Font.PLAIN, 16));
        checkintime.setBounds(200,320,150,20);
        add(checkintime);
        
        JLabel lbldeposit = new JLabel("DEPOSIT:"); 
        lbldeposit.setFont(new Font("Raleway", Font.PLAIN, 18));
        lbldeposit.setBounds(35,360,100,20);
        add(lbldeposit);
        tfdeposit = new JTextField();
        tfdeposit.setBounds(200,360,150,25);
        add(tfdeposit); 
        
        add = new JButton("ADD");
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.setBounds(50,410,120,25);
        add.addActionListener(this);
        add(add);
        back = new JButton("BACK");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(200,410,120,25);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/fifth.jpg"));
        Image  i2=i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,50,300,400);
        add(image);
        
        setBounds(350,200,800,550);
        setVisible(true);
    }
     public void actionPerformed(ActionEvent ae){
         if (ae.getSource()==add){
             String id = (String) comboid.getSelectedItem();
             String number = tfnumber.getText();
             String name = tfname.getText();
             String gender = null;
             
             if(rmale.isSelected()){
             gender = "MALE";
         }else{
                 gender="FEMALE";
             }
             
             
             String country = tfcountry.getText();
             String room=croom.getSelectedItem();
             String time=checkintime.getText();
             String deposit=tfdeposit.getText();
             
        if(number.equals("")){
            JOptionPane.showMessageDialog(null,"number is required" );
            return;
        }
         if(name.equals("")){
            JOptionPane.showMessageDialog(null,"name is required" );
            return;
        }
          if(gender.equals("")){
            JOptionPane.showMessageDialog(null,"gender is required" );
            return;
        }
          if(deposit.equals("")){
            JOptionPane.showMessageDialog(null,"deposit is required" );
            return;
        }
        if(country.equals("")){
            JOptionPane.showMessageDialog(null,"country is required" );
            return;
        }
         if(room.equals("")){
            JOptionPane.showMessageDialog(null,"room is required" );
            return;
        }
             try {
                 String query = "insert into customer values('"+id+"','"+number+"','"+name+"','"+gender+"','"+country+"','"+room+"','"+time+"','"+deposit+"')";
                 String query2 = "update rooms set availabilility ='Occupied' WHERE roomnumber='"+room+"'";
                 
                 Conn conn = new Conn();
                 conn.s.executeUpdate(query);
                 conn.s.executeUpdate(query2);
                 
                 
                 JOptionPane.showMessageDialog(null, "new customer added Successfully");
                 setVisible(false);
                 new Reception();
             }catch(Exception e){
                 e.printStackTrace();
             }
             
             
         }else if(ae.getSource()==back){
             setVisible(false);
             new Reception();
         }
     }
     
    public static void main(String agrs[]){
        new AddCustomer();
    }
}

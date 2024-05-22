
package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AddDriver extends JFrame implements ActionListener{
    JButton add, cancel;
    JTextField tfname, tfcar,tfage,tfmodel,tflocation;
    JComboBox availablecombo,  gendercombo;
    AddDriver(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("ADD Drivers");
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        heading.setBounds(150,20,200,20);
        add(heading);
        
        JLabel  lblroomno= new JLabel("NAME:");
        lblroomno.setFont(new Font("Tahoma",Font.BOLD,16));
        lblroomno.setBounds(60,80,120,30);
        add(lblroomno);
        tfname = new JTextField();
        tfname.setBounds(200,80,150,30);
        add(tfname);
        
        JLabel lblavailable = new JLabel("AGE:");
        lblavailable.setFont(new Font("Tahoma",Font.BOLD,16));
        lblavailable.setBounds(60,130,120,30);
        add(lblavailable );
        tfage = new JTextField();
        tfage.setBounds(200,130,150,30);
        add(tfage);
        
        JLabel lblclean = new JLabel("GENDER:");
        lblclean .setFont(new Font("Tahoma",Font.BOLD,16));
        lblclean .setBounds(60,180,120,30);
        add(lblclean);
        String cleanOptions[] = {"MALE","FEMALE"};
        gendercombo = new JComboBox(cleanOptions);
        gendercombo.setBounds(200,180,150,30);
        gendercombo.setBackground(Color.WHITE);
        add(gendercombo);
        
        JLabel lblprice = new JLabel("CAR :");
        lblprice.setFont(new Font("Tahoma",Font.BOLD,16));
        lblprice .setBounds(60,230,120,30);
        add(lblprice);
        tfcar = new JTextField();
        tfcar.setBounds(200,230,150,30);
        add(tfcar);
        
        JLabel lblmodel = new JLabel("MODEL :");
        lblmodel.setFont(new Font("Tahoma",Font.BOLD,16));
        lblmodel.setBounds(60,280,120,30);
        add(lblmodel);
        tfmodel = new JTextField();
        tfmodel.setBounds(200,280,150,30);
        add(tfmodel);
        
        JLabel lbltype = new JLabel("STATUS:");
        lbltype.setFont(new Font("Tahoma",Font.BOLD,16));
        lbltype.setBounds(60,330,120,30);
        add(lbltype);
        String typeOptions[] = {"AVAILABLE","ON DRIVE"};
        availablecombo = new JComboBox(typeOptions);
        availablecombo.setBounds(200,330,150,30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        JLabel lbllocate = new JLabel("LOCATION:");
        lbllocate.setFont(new Font("Tahoma",Font.BOLD,16));
        lbllocate.setBounds(60,380,120,30);
        add(lbllocate);
        tflocation = new JTextField();
        tflocation.setBounds(200,380,150,30);
        add(tflocation);
        
        
        add =  new JButton("ADD ROOM");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds(60,430,130,30);
        add.addActionListener(this);
        add(add);
        cancel =  new JButton("CANCEL");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(220,430,130,30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image  i2=i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,80,500,300);
        add(image);
        
        setBounds(300,200,980,600);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == add){
            String name =tfname.getText();
            String age =tfage.getText();
            String gender = (String) gendercombo.getSelectedItem();
            String car =tfcar.getText();
            String model =tfmodel.getText();
            String status = (String) availablecombo.getSelectedItem();
            String location = tflocation.getText();
            if(name.equals("")){
            JOptionPane.showMessageDialog(null,"name is required" );
            return;
            }
            if(age.equals("")){
            JOptionPane.showMessageDialog(null,"age is required" );
            return;
            }
            try{
                Conn conn =new Conn();
                String str = "insert into adddrivers values('"+name+"','"+age+"','"+gender+"','"+car+"','"+model+"','"+status+"','"+location+"')";
                conn.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null,"Driver updated Successfully");
            }catch(Exception e){
                e.printStackTrace();
            }
    }else{
            setVisible(false);
        }
    }
    public static void main(String[] args){
        new AddDriver();
    }
}

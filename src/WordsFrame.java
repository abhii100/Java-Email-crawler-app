
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abhijit
 */

public class WordsFrame extends javax.swing.JFrame {

    /**
     * Creates new form WordsFrame
     */
    DefaultListModel lm=new DefaultListModel();
    public WordsFrame() {
        initComponents();
        try
        {
        
        lm.clear();
           Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
           PreparedStatement ps=con.prepareStatement("select * from words");
           
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
               lm.addElement(rs.getString(1));
           }
           l1.setModel(lm);
        }
        catch(Exception e)
                {
                JOptionPane.showMessageDialog(this,""+e);
                
                }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tf1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        l1 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(470, 458));
        setUndecorated(true);
        getContentPane().setLayout(null);
        getContentPane().add(tf1);
        tf1.setBounds(60, 120, 180, 30);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arrow-16.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 0, 50, 50);

        jLabel1.setBackground(new java.awt.Color(57, 139, 247));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("                         Words");
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 480, 50);

        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(310, 120, 110, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Present Words");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(80, 190, 200, 16);

        jScrollPane1.setViewportView(l1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 220, 180, 200);

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(310, 300, 120, 30);

        jLabel2.setBackground(new java.awt.Color(47, 65, 89));
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 50, 480, 420);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        dispose();
         AdminView a=new AdminView();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try
        {
        if(tf1.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Field is empty");
           
        }
        {
           Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
           PreparedStatement ps=con.prepareStatement("insert into Words values(?) ");
           ps.setString(1,tf1.getText());
           int x=ps.executeUpdate();
           if(x==1)
           {
               JOptionPane.showMessageDialog(this,"Data Saved");
              
           }
           else
           {
               JOptionPane.showMessageDialog(this,"Data not Saved");
           }
        }
        }
        catch(Exception e)
                {
                JOptionPane.showMessageDialog(this,""+e);
                
                }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        int index=l1.getSelectedIndex();
        if(index<0)
        {
            JOptionPane.showMessageDialog(this,"Please Select Value");
        }
        else
        {
            String name=l1.getSelectedValue();
            try
            {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
                PreparedStatement ps=con.prepareStatement("delete from words where words=?");
                ps.setString(1,name);
                int x=ps.executeUpdate();
                if(x==1)
                {
                    JOptionPane.showMessageDialog(this,"Word Removed");
                    lm.clear();
                    ps=con.prepareStatement("select * from words");
           
                    ResultSet rs=ps.executeQuery();
                    while(rs.next())
                    {
                        lm.addElement(rs.getString(1));
                    }
                    l1.setModel(lm);
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Sorry Problem Occur");
                }
            }
            catch(Exception e)
            {
                
            }
            
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WordsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WordsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WordsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WordsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WordsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> l1;
    private javax.swing.JTextField tf1;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thucthi;

import Class.Ban;
import coffee.ListBan;
import coffee.MenuManager;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ClassThucthi.*;
import coffee.ListNhanVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author boss
 */
public class InsertBan extends javax.swing.JPanel {

    /**
     * Creates new form InsertBan
     */
    public InsertBan() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tenban = new javax.swing.JLabel();
        txttenban = new javax.swing.JTextField();
        ok = new javax.swing.JButton();
        close = new javax.swing.JButton();

        setAutoscrolls(true);

        tenban.setText("Tên bàn");

        ok.setText("OK");
        ok.setAutoscrolls(true);
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        close.setText("CLOSE");
        close.setAutoscrolls(true);
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tenban, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttenban)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(ok)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                .addComponent(close)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttenban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenban))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ok)
                    .addComponent(close))
                .addGap(26, 26, 26))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        ListBan.PanelBan.removeAll();
            ListBan.PanelBan.add(new JPanel());
            ListBan.PanelBan.validate();
    }//GEN-LAST:event_closeActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
      if(txttenban.getText().equals("")){
          JOptionPane.showMessageDialog(this,"Bạn chưa nhập đủ thông tin về bàn !!!!");
      }
      try{
          Ban b=new Ban();
          b.setDeskname(txttenban.getText());
          b.setComments("");
          b.setStatus("Trống");
          Themdatabase td=new Themdatabase();
          int k=td.themDesk(b);
          if(k==1)JOptionPane.showMessageDialog(this,"Thêm thành công "+txttenban.getText());
           DefaultTableModel model= (DefaultTableModel) coffee.ListBan.jTable1.getModel();
           model.setRowCount(0);
          Connection cnn=ConnectedDatabase.getConnection();
          String sql=null;
          sql="select * from desk";
        try {
          Statement  ptm= cnn.createStatement();
            ResultSet rs=ptm.executeQuery(sql);
            while(rs.next()){
                String desknumber=rs.getString("desknumber");
                String deskname=rs.getString("deskname");
                String status=rs.getString("status");
                String  comments=rs.getString("comments");
                Object []data={desknumber,deskname,status,comments};
               
                model.addRow(data);
                
            }
            
        } catch (SQLException ex) {
            ListNhanVien list1=new ListNhanVien();
           JOptionPane.showMessageDialog(list1,"lỗi"+ ex);
        } 
      }catch(Exception ex){
          JOptionPane.showMessageDialog(this,"Lỗi: "+ex);
      }
              
    }//GEN-LAST:event_okActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close;
    private javax.swing.JButton ok;
    private javax.swing.JLabel tenban;
    private javax.swing.JTextField txttenban;
    // End of variables declaration//GEN-END:variables
}

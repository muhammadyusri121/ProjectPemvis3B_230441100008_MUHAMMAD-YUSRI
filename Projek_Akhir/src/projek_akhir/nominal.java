/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projek_akhir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author zeroo
 */
public class nominal extends javax.swing.JFrame {

    Connection koneksi;
    int idgame;
    private String pilihan;

    private int hitung_harga = 0;
    private boolean validasi = true;
    private Dashboard dashboard;
    
//    String idnya;
//    String jumlah_diamond;
//    String harga1;
//    String nama_game;
    

    
    private int diamond;
    private int hargaDiamond100;
    private int hargaDiamond1000;
    private int hargaDiamond10000;
    private int hargaDiamond100000;

   
    
    

    /**
     * Creates new form nominal
     */
    public nominal(Dashboard dashboard) {
        this.dashboard = dashboard;
        initComponents();
        koneksi = database.getConnection();
        updateRadioButtonText();

        
//        panelradio1.setLayout(new BoxLayout(panelradio1, BoxLayout.Y_AXIS));


    }

    private nominal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    


    
    
    public void setPilihan(String pilihanBaru) {
    this.pilihan = pilihanBaru;
    updateRadioButtonText();
    setHargaFromDatabase();

//    private void loadDataHargaMobileLegend() {
//        try {
//            // Query untuk mengambil data berdasarkan nama_game
//            String sql = "SELECT id_diamond, jumlah_diamond, nama_game, harga FROM harga_diamond WHERE nama_game = 'mobile legend'";
//
//            PreparedStatement ps = koneksi.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            // Cek apakah ada data yang ditemukan
//                if (rs.next()) {
//                // Mengambil nilai dari kolom "nama_game"
//                idnya = rs.getString("id_diamond");
//                jumlah_diamond = rs.getString("jumlah_diamond");
//                harga1 = rs.getString("harga");
//                nama_game = rs.getString("nama_game");
//
//            } else {
//                // Jika tidak ada data
//                JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk game ini.");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Terjadi kesalahan: " + e.getMessage());
//        }

    }
    
private void updateRadioButtonText() {

    if ("callofduty".equals(pilihan)) {
        radio.setText("Call of Duty 100 diamond");
        radio1.setText("Call of Duty 1000 diamond");
        radio2.setText("Call of Duty 10000 diamond");
        radio3.setText("Call of Duty 100000 diamond");
    }
    else if ("freefire".equals(pilihan)) {
        radio.setText("Free Fire 100 diamond");
        radio1.setText("Free Fire 1000 diamond");
        radio2.setText("Free Fire 10000 diamond");
        radio3.setText("Free Fire 100000 diamond");
    }
    else if ("mobilelegend".equals(pilihan)) {
        radio.setText("Mobile Legend 100 diamond");
        radio1.setText("Mobile Legend 1000 diamond");
        radio2.setText("Mobile Legend 10000 diamond");
        radio3.setText("Mobile Legend 100000 diamond");
    }
}


private void setHargaFromDatabase() {
    String sql = "SELECT * FROM harga_game WHERE nama_game = ?";
    

    try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
         
        // Set parameter query (pilihan game)
        ps.setString(1, "mobilelegend");
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            // Ambil nilai harga dari hasil query
            int diamond100 = rs.getInt("diamond_100");
            int diamond1000 = rs.getInt("diamond_1000");
            int diamond10000 = rs.getInt("diamond_10000");
            int diamond100000 = rs.getInt("diamond_100000");


            // Set harga berdasarkan pilihan game
            if ("freefire".equals(pilihan)) {
                // Gunakan harga Free Fire
                hargaDiamond100 = diamond100;
                hargaDiamond1000 = diamond1000;
                hargaDiamond10000 = diamond10000;
                hargaDiamond100000 = diamond100000;
            } else if ("mobilelegend".equals(pilihan)) {
                // Gunakan harga Mobile Legend
                hargaDiamond100 = diamond100;
                hargaDiamond1000 = diamond1000;
                hargaDiamond10000 = diamond10000;
                hargaDiamond100000 = diamond100000;
            } else if ("callofduty".equals(pilihan)) {
                // Gunakan harga Call of Duty
                hargaDiamond100 = diamond100;
                hargaDiamond1000 = diamond1000;
                hargaDiamond10000 = diamond10000;
                hargaDiamond100000 = diamond100000;
            }
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    private void hitungHarga() {
        // Atur harga diamond sesuai game yang dipilih
    if ("freefire".equals(pilihan)) {
        int diamond100 = hargaDiamond100;
        int diamond1000 = hargaDiamond1000;
        int diamond10000 = hargaDiamond10000;
        int diamond100000 = hargaDiamond100000;

        if (radio.isSelected()) {
            hitung_harga += diamond100;
        }

        if (radio.isSelected()) {
            hitung_harga += diamond100;
            diamond = 100;
        }
        if (radio1.isSelected()) {
            hitung_harga += diamond1000;
            diamond = 1000;
        }
        if (radio2.isSelected()) {
            hitung_harga += diamond10000;
            diamond = 10000;
        }
        if (radio3.isSelected()) {
            hitung_harga += diamond100000;
            diamond = 100000;
        }  harga.setText(String.valueOf(hitung_harga));
    } else if ("mobilelegend".equals(pilihan)) {
        // Atur harga diamond khusus untuk Mobile Legend, jika berbeda
        int diamond100 = hargaDiamond100;
        int diamond1000 = hargaDiamond1000;
        int diamond10000 = hargaDiamond10000;
        int diamond100000 = hargaDiamond100000;
        

        if (radio.isSelected()) {
            hitung_harga += diamond100;
            diamond = 100;
        }
        if (radio1.isSelected()) {
            hitung_harga += diamond1000;
            diamond = 1000;
        }
        if (radio2.isSelected()) {
            hitung_harga += diamond10000;
            diamond = 10000;
        }
        if (radio3.isSelected()) {
            hitung_harga += diamond100000;
            diamond = 100000;
        }
            harga.setText(String.valueOf(hitung_harga));
    }else if ("callofduty".equals(pilihan)) {
        // Atur harga diamond khusus untuk Mobile Legend, jika berbeda
        int diamond100 = hargaDiamond100;
        int diamond1000 = hargaDiamond1000;
        int diamond10000 = hargaDiamond10000;
        int diamond100000 = hargaDiamond100000;
      

        if (radio.isSelected()) {
            hitung_harga += diamond100;
            diamond = 100;
        }
        if (radio1.isSelected()) {
            hitung_harga += diamond1000;
            diamond = 1000;
        }
        if (radio2.isSelected()) {
            hitung_harga += diamond10000;
            diamond = 10000;
        }
        if (radio3.isSelected()) {
            hitung_harga += diamond100000;
            diamond = 100000;
        }
            harga.setText(String.valueOf(hitung_harga));

}


  
    
    
    
    
    
    
    // Method untuk mengatur opsi JRadioButton
//    public void setRadioButtonOptions(List<String> options) {
//        // Bersihkan opsi lama
//        panelradio1.removeAll();
//        grup2.clearSelection();
//
//        // Tambah opsi baru
//        for (String option : options) {
//            JRadioButton jradio1 = new JRadioButton(option);
//            buttonGroup1.add(jradio1);
//            panelradio1.add(jradio1);
//        }
//        
//        // Refresh panel untuk menampilkan perubahan
//        panelradio1.revalidate();
//        panelradio1.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        grup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        harga = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        hitung = new javax.swing.JButton();
        radio1 = new javax.swing.JRadioButton();
        radio2 = new javax.swing.JRadioButton();
        radio3 = new javax.swing.JRadioButton();
        fid_game = new javax.swing.JTextField();
        radio = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        checkout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        batal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });
        jPanel1.add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 286, -1));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Masukkan ID Game");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 106, -1, -1));

        hitung.setText("Hitung");
        hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungActionPerformed(evt);
            }
        });
        jPanel1.add(hitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 433, -1, -1));

        buttonGroup1.add(radio1);
        radio1.setForeground(new java.awt.Color(255, 255, 255));
        radio1.setText("ml 1000 diamond");
        radio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio1ActionPerformed(evt);
            }
        });
        jPanel1.add(radio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 219, -1, -1));

        buttonGroup1.add(radio2);
        radio2.setForeground(new java.awt.Color(255, 255, 255));
        radio2.setText("ml 10000 diamond");
        radio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio2ActionPerformed(evt);
            }
        });
        jPanel1.add(radio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 254, -1, -1));

        buttonGroup1.add(radio3);
        radio3.setForeground(new java.awt.Color(255, 255, 255));
        radio3.setText("ml 100000 diamond");
        radio3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio3ActionPerformed(evt);
            }
        });
        jPanel1.add(radio3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 289, -1, -1));

        fid_game.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fid_gameActionPerformed(evt);
            }
        });
        jPanel1.add(fid_game, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 286, -1));

        buttonGroup1.add(radio);
        radio.setForeground(new java.awt.Color(255, 255, 255));
        radio.setText("ml 100 diamond");
        radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioActionPerformed(evt);
            }
        });
        jPanel1.add(radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 184, 327, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 444, -1));

        checkout.setText("Keranjang");
        checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutActionPerformed(evt);
            }
        });
        jPanel1.add(checkout, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 433, -1, -1));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Harga");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 348, -1, -1));

        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });
        jPanel1.add(batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 433, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_radioActionPerformed

    private void hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungActionPerformed
        // TODO add your handling code here:
                System.out.println("ini diamond yang kamu pilih"+ diamond);

        setHargaFromDatabase();
//        agar timbol bisa memproses 1 kali
         
           if (validasi == true){
               hitungHarga();
               validasi = false;
           }
           

        
//        radio.setText(idnya);
//        radio1.setText(idnya);
//        radio2.setText(idnya);
//        radio3.setText(idnya);

//        if(radio.isSelected()){

    }//GEN-LAST:event_hitungActionPerformed

    private void radio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio1ActionPerformed

    private void radio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio2ActionPerformed

    private void radio3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio3ActionPerformed

    private void checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutActionPerformed
        // TODO add your handling code here:
        String id = fid_game.getText();
//            Dashboard dashboard1 = new Dashboard(true);
            dashboard.setJumlahHarga(hitung_harga);
            dashboard.setIdGame(id);
           dashboard.setDiamond(diamond);
            dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_checkoutActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_batalActionPerformed

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:




    }//GEN-LAST:event_hargaActionPerformed

    private void fid_gameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fid_gameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fid_gameActionPerformed

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
            java.util.logging.Logger.getLogger(nominal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nominal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nominal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nominal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nominal().setVisible(true);
               

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton checkout;
    private javax.swing.JTextField fid_game;
    private javax.swing.ButtonGroup grup2;
    private javax.swing.JTextField harga;
    private javax.swing.JButton hitung;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton radio;
    private javax.swing.JRadioButton radio1;
    private javax.swing.JRadioButton radio2;
    private javax.swing.JRadioButton radio3;
    // End of variables declaration//GEN-END:variables
}

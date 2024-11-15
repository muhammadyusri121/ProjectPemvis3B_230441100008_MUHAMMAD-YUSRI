package projek_akhir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author zeroo
 */
public class Dashboard extends javax.swing.JFrame {

    private boolean logingaklu;
    private String NamaUser;
    Connection koneksi;
    private String jumlah_diamond;
    private float harga;
    private String nama_game;
    private String pilihan;
    private String Username1;
    private String usernameadmin;
    private int jumlah_beli;
    private int jumlahHarga;
    private int tampungharga;
    private int IdGame;
    private int diamondTampung;
    private int diamond;
    private int tampungIdGame;
     private String tampungnamauser;
    private Timestamp timestamp;
    
    

    /**
     * Creates new form Dashboard
     */
    // Konstruktor dengan parameter login status
    public Dashboard(boolean isLoggedIn) {
        this.logingaklu = isLoggedIn;
        koneksi = database.getConnection();
        initComponents();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        

        if (!this.logingaklu) {
            // Jika tidak login, kembali ke halaman login
            JOptionPane.showMessageDialog(this, "Anda harus login terlebih dahulu.");
            // Menutup halaman Dashboard
            this.dispose();
//            // Membuka halaman login
            login log = new login();
            log.setVisible(true);

        }
        

        


    }   


  
    
    
    
//    aku ingin meload data yang kusimpan tadi kedalam tabel dengan variabel tab_harga buatkan aku kode yang bisa kupanggil ketika refresh tam harga
    
////    save transaksi
    private void saveTransaksi() {
    String Snama_user= NamaUser;
    String Snama_game= pilihan;
    double Snominal_topup = tampungharga;
    int Sid_game =IdGame;
    int Sdiamond = diamond;
//    String waktu = "datetime";
  try{
     String sql = "INSERT INTO riwayat_transaksi (nama_user, nama_game, jumlah_topup,id_game,waktu,harga,username) VALUES ( ?, ?, ?,?,?,?,?)";
     PreparedStatement ps = koneksi.prepareStatement(sql);
     ps.setString(1,Snama_user );
     ps.setString(2,Snama_game);
     ps.setString(3, String.valueOf(Sdiamond));
     ps.setString(4,String.valueOf(Sid_game));
     ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
     ps.setString(6,String.valueOf(Snominal_topup));
     ps.setString(7,usernameadmin);
         
     ps.executeUpdate(); 
     JOptionPane.showMessageDialog(this, "Data saved successfully");

   } catch (SQLException e) {
     System.out.println("Error Save Data" + e.getMessage());
   }
 }
    
    
    
    private void loadDataToTable() {
    DefaultTableModel model = (DefaultTableModel) tabharga.getModel();
    model.setRowCount(0); // Menghapus data lama dari tabel

    String sql = "SELECT nama_user,nama_game, jumlah_topup, id_game, waktu, harga FROM riwayat_transaksi WHERE nama_user = ?";

    try {
        PreparedStatement ps = koneksi.prepareStatement(sql);
        ps.setString(1, "1"); // Menambahkan filter berdasarkan username
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String namaUser = rs.getString("nama_user");
            String namaGame = rs.getString("nama_game");
            int jumlahTopup = rs.getInt("jumlah_topup");
            int idGame = rs.getInt("id_game");
            Timestamp waktu = rs.getTimestamp("waktu");
            double harga = rs.getDouble("harga");

            // Menambahkan data ke dalam tabel
            model.addRow(new Object[]{ namaGame, jumlahTopup, idGame, waktu, harga});
        }
    } catch (SQLException e) {
        System.out.println("Error Load Data: " + e.getMessage());
    }
}


    
    
    
    
    
    
    
    
    
    

//    private void loadDataHargaMobileLegend() {
//    try {
//        String sql = "SELECT id_diamond, jumlah_diamond, nama_game, harga FROM harga_diamond WHERE nama_game = 'mobile legend'";
//        PreparedStatement ps = koneksi.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//
//        List<String> options = new ArrayList<>(); // List untuk opsi JRadioButton
//
//        // Ambil data dari ResultSet dan tambahkan ke list options
//        while (rs.next()) {
//            int jumlahDiamond = rs.getInt("jumlah_diamond");
//            double harga = rs.getDouble("harga");
//
//            String optionText = jumlahDiamond + " Diamond - Rp " + harga;
//            options.add(optionText);
//        }
//
//        // Tampilkan halaman nominal dan kirim opsi harga diamond
//        nominal nominalFrame = new nominal();
//        nominalFrame.setRadioButtonOptions(options); // Kirim list opsi ke nominal
//        nominalFrame.setVisible(true);
//        this.dispose();
//
//    } catch (SQLException e) {
//        System.out.println("Terjadi kesalahan: " + e.getMessage());
//    }
//    }
//    private void loadDataHargaMobileLegend() {
//        try {
//            // Query untuk mengambil data berdasarkan nama_game
//            String sql = "SELECT id_diamond, jumlah_diamond, nama_game, harga FROM harga_diamond WHERE nama_game = 'mobile legend'";
//
//            PreparedStatement ps = koneksi.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            // Cek apakah ada data yang ditemukan
//            if (rs.next()) {
//                // Mengambil nilai dari kolom "nama_game"
//                jumlah_diamond = rs.getString("jumlah_diamond");
//                harga = rs.getFloat("harga");
//                nama_game = rs.getString("nama_game");
//            } else {
//                // Jika tidak ada data
//                JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk game ini.");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Terjadi kesalahan: " + e.getMessage());
//        }
//    }

    public void setNamaUser(String nama_user) {
        this.NamaUser = nama_user;
        
        tampungnamauser = nama_user;
        
        namaout.setText(tampungnamauser);


    }
public void setDiamond(int diamond){
    this.diamond = diamond;
    String diamondTampung = String.valueOf(diamond); // Mengonversi int ke String
    outdiamond.setText(diamondTampung); // Menetapkan nilai String ke outdiamond
}

    
    public void setUsername1(String username){
        this.Username1 = username;
        
        usernameadmin = Username1;
         
    }
    public void setIdGame(String id){
        this.IdGame = Integer.parseInt(id);
        tampungIdGame = IdGame;
        
        System.out.println(IdGame);
         
    }
   
    // Setter untuk mengubah jumlahHarga
    public void setJumlahHarga(int hitung_harga) {
        this.jumlahHarga = hitung_harga;
        
        tampungharga += jumlahHarga;
        
        // Menampilkan jumlahHarga di komponen harga, jika ada
        outjumlahharga.setText(String.valueOf(tampungharga));
        
        
        System.out.println("harga yang dikirim"+jumlahHarga);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        beli_coc = new javax.swing.JButton();
        beli_aov = new javax.swing.JButton();
        beli_pubg = new javax.swing.JButton();
        beli_cod = new javax.swing.JButton();
        beli_ml = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        beli_ff = new javax.swing.JButton();
        profil = new javax.swing.JLabel();
        namaout = new javax.swing.JLabel();
        admin = new javax.swing.JButton();
        outjumlahharga = new javax.swing.JTextField();
        fbayar = new javax.swing.JTextField();
        namaout1 = new javax.swing.JLabel();
        namaout2 = new javax.swing.JLabel();
        namaout3 = new javax.swing.JLabel();
        outkembalian = new javax.swing.JTextField();
        checkout = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        hitung = new javax.swing.JButton();
        namaout4 = new javax.swing.JLabel();
        outdiamond = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabharga = new javax.swing.JTable();
        refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projek_akhir/images/logo_game/AOV Arena Of Valor Flat logo Designs.jpeg"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 173, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projek_akhir/images/logo_game/_.jpeg"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 276, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projek_akhir/images/logo_game/Clash of Clans Logo.jpeg"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 51, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projek_akhir/images/logo_game/e6bd5f82-a4ea-40e6-bad9-de71106032a3.jpeg"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projek_akhir/images/logo_game/Free Fire logo vector in _EPS, .SVG free download - Brandlogos.jpeg"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 580, -1, -1));

        beli_coc.setBackground(new java.awt.Color(0, 102, 102));
        beli_coc.setForeground(new java.awt.Color(255, 255, 255));
        beli_coc.setText("Beli");
        beli_coc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beli_cocMouseClicked(evt);
            }
        });
        beli_coc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beli_cocActionPerformed(evt);
            }
        });
        jPanel2.add(beli_coc, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 105, -1, -1));

        beli_aov.setText("Beli");
        beli_aov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beli_aovActionPerformed(evt);
            }
        });
        jPanel2.add(beli_aov, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 227, -1, -1));

        beli_pubg.setText("Beli");
        beli_pubg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beli_pubgActionPerformed(evt);
            }
        });
        jPanel2.add(beli_pubg, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, -1, -1));

        beli_cod.setBackground(new java.awt.Color(0, 102, 102));
        beli_cod.setForeground(new java.awt.Color(255, 255, 255));
        beli_cod.setText("Beli");
        beli_cod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beli_codActionPerformed(evt);
            }
        });
        jPanel2.add(beli_cod, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, -1, -1));

        beli_ml.setText("Beli");
        beli_ml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beli_mlActionPerformed(evt);
            }
        });
        jPanel2.add(beli_ml, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 330, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projek_akhir/images/logo_game/Call of duty.jpeg"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, -1, -1));

        beli_ff.setText("Beli");
        beli_ff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beli_ffActionPerformed(evt);
            }
        });
        jPanel2.add(beli_ff, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 630, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 82, 319, 692));

        profil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projek_akhir/images/Desain tanpa judul.png"))); // NOI18N
        profil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilMouseClicked(evt);
            }
        });
        jPanel1.add(profil, new org.netbeans.lib.awtextra.AbsoluteConstraints(819, 32, -1, -1));

        namaout.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        namaout.setText("Your Name");
        jPanel1.add(namaout, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, -1, -1));

        admin.setText("admin");
        admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminActionPerformed(evt);
            }
        });
        jPanel1.add(admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 82, -1, -1));

        outjumlahharga.setEditable(false);
        outjumlahharga.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel1.add(outjumlahharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 233, 40));

        fbayar.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel1.add(fbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 440, 233, 40));

        namaout1.setFont(new java.awt.Font("sansserif", 3, 24)); // NOI18N
        namaout1.setText("Total Harga");
        jPanel1.add(namaout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, -1, 30));

        namaout2.setFont(new java.awt.Font("sansserif", 3, 24)); // NOI18N
        namaout2.setText("Total Bayar");
        jPanel1.add(namaout2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, -1, 30));

        namaout3.setFont(new java.awt.Font("sansserif", 3, 24)); // NOI18N
        namaout3.setText("Kembalian");
        jPanel1.add(namaout3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 510, -1, 30));

        outkembalian.setEditable(false);
        outkembalian.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel1.add(outkembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 540, 233, 40));

        checkout.setFont(new java.awt.Font("sansserif", 1, 17)); // NOI18N
        checkout.setText("checkout");
        checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutActionPerformed(evt);
            }
        });
        jPanel1.add(checkout, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 630, 130, 50));

        jButton2.setFont(new java.awt.Font("sansserif", 1, 17)); // NOI18N
        jButton2.setText("Reset");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 631, 100, 49));

        hitung.setFont(new java.awt.Font("sansserif", 1, 17)); // NOI18N
        hitung.setText("Hitung");
        hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungActionPerformed(evt);
            }
        });
        jPanel1.add(hitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 631, 125, 49));

        namaout4.setFont(new java.awt.Font("sansserif", 3, 24)); // NOI18N
        namaout4.setText("Jumlah Diamond");
        jPanel1.add(namaout4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, -1, 30));

        outdiamond.setEditable(false);
        outdiamond.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel1.add(outdiamond, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 233, 40));

        jTabbedPane1.addTab("Dashboard", jPanel1);

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        tabharga.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Game", "Tanggal Transaksi", "Nama Game", "Jumlah pembelian", "Harga"
            }
        ));
        jScrollPane1.setViewportView(tabharga);

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(refresh)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(refresh)
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Riwayat Transaksi", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void beli_cocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beli_cocMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_beli_cocMouseClicked

    private void profilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilMouseClicked
        // TODO add your handling code here:
    profil profil1 = new profil();
          profil1.setNamaUserProfil(Username1);
          System.out.println("penembaknya"+ Username1);
        
        
        profil1.setVisible(true);
        
    }//GEN-LAST:event_profilMouseClicked

//        private void updateNominalFrame() {
//    nominal1.setPilihan(pilihan); // Pastikan nominalFrame memiliki method ini
//}
    private void beli_mlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beli_mlActionPerformed
        // TODO add your handling code here:
//    loadDataHargaMobileLegend();
    if (tampungharga <1){
        pilihan = "mobilelegend";
        nominal nominal1 = new nominal(this);
        nominal1.setPilihan(pilihan);
        nominal1.setVisible(true);
    }else{
        JOptionPane.showMessageDialog(null,"anda, memiliki belajaan yang belum dibayar, silahkan checkout yang ada di keranjang atau reset untuk membatalkan");
    }
        

//    String optionText = jumlah_diamond + " Diamond - Rp " + harga;
//            options.add(optionText);
//        }
//
//
//        nominal1.setRadioButtonOptions(options); 
//    // Kirim list opsi ke nominal
//      
        


    }//GEN-LAST:event_beli_mlActionPerformed

    private void beli_cocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beli_cocActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_beli_cocActionPerformed

    private void beli_pubgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beli_pubgActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_beli_pubgActionPerformed

    private void beli_ffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beli_ffActionPerformed
        // TODO add your handling code here:
        pilihan = "freefire";
        nominal nominal1 = new nominal(this);
        nominal1.setPilihan(pilihan);
        nominal1.setVisible(true);
    }//GEN-LAST:event_beli_ffActionPerformed

    private void beli_codActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beli_codActionPerformed
        // TODO add your handling code here:
            pilihan = "callofduty";
        nominal nominal1 = new nominal(this);
        nominal1.setPilihan(pilihan);
        nominal1.setVisible(true);
    }//GEN-LAST:event_beli_codActionPerformed

    
    private void adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminActionPerformed
        // TODO add your handling code here:
        
              // Periksa apakah NamaUserProfil sama dengan "muhammad"
        if ("muhammad".equalsIgnoreCase(usernameadmin)) {
//            System.out.println("usename admin adalah"+ usernameadmin);
            admin.setVisible(true); // Tampilkan tombol jika username adalah "muhammad"
            admin admin1 =new admin();
            admin1.setVisible(true);
} else {
            JOptionPane.showMessageDialog(null, "Anda bukan admin jangan ngada ngada");
            admin.setVisible(false); // Sembunyikan tombol untuk username lain
}
    }//GEN-LAST:event_adminActionPerformed

    private void beli_aovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beli_aovActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_beli_aovActionPerformed

    private void hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungActionPerformed
        // TODO add your handling code here:
        
    int bayar = Integer.parseInt(fbayar.getText());

        int kembalian =   bayar-tampungharga;
        outkembalian.setText(String.valueOf(kembalian));
    }//GEN-LAST:event_hitungActionPerformed

    private void checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutActionPerformed
        // TODO add your handling code here:
        saveTransaksi();
    }//GEN-LAST:event_checkoutActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        loadDataToTable();
        
    }//GEN-LAST:event_refreshActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard(false).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admin;
    private javax.swing.JButton beli_aov;
    private javax.swing.JButton beli_coc;
    private javax.swing.JButton beli_cod;
    private javax.swing.JButton beli_ff;
    private javax.swing.JButton beli_ml;
    private javax.swing.JButton beli_pubg;
    private javax.swing.JButton checkout;
    private javax.swing.JTextField fbayar;
    private javax.swing.JButton hitung;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel namaout;
    private javax.swing.JLabel namaout1;
    private javax.swing.JLabel namaout2;
    private javax.swing.JLabel namaout3;
    private javax.swing.JLabel namaout4;
    private javax.swing.JTextField outdiamond;
    private javax.swing.JTextField outjumlahharga;
    private javax.swing.JTextField outkembalian;
    private javax.swing.JLabel profil;
    private javax.swing.JButton refresh;
    private javax.swing.JTable tabharga;
    // End of variables declaration//GEN-END:variables

    void setJumalhHarga(int hitung_harga) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setJumlahHarga(JTextField id_game1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 

}

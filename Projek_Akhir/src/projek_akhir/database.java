/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projek_akhir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author zeroo
 */
public class database {
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USER = "zeroo";
    private static final String PASS = "2972";

    public static Connection getConnection() {
        Connection koneksi = null;
        try {
            koneksi = DriverManager.getConnection(URL, USER, PASS);
//            System.out.println("terkoneksi");
        } catch (SQLException e) {
            System.out.println("error bang : " + e.getMessage());
        }
        return koneksi;
    }
    
    public static void main(String[] args) {
        getConnection();
        
    }
}


package utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    public static void storeResult(BigDecimal iPhonePrice, BigDecimal iPhonePlusPrice) {
        Connection conn = null;
        try {
            String dbPath = DBUtil.class.getResource("/testresult.db").getPath();
            String url = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            String querry = "INSERT INTO TestData VALUES(" + iPhonePrice + ", " + iPhonePlusPrice
                    + ", printf('%s %s', date('now'), time('now')))";
            statement.executeQuery(querry);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

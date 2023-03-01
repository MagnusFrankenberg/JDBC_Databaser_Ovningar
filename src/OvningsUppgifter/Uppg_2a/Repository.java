package OvningsUppgifter.Uppg_2a;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Repository {

    public int getUppförande(String barnNamn) throws ClassNotFoundException, IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/OvningsUppgifter/Propertiesfil/settings.properties"));

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select rapport.uppförandetal as utal from barn" +
                             " inner join rapport on rapport.barnid=barn.id " +
                             " and barn.namn = "+ "'"+barnNamn+"'")
        ) {
            int uppförandetal=0;
            while (rs.next()){
                 uppförandetal = rs.getInt("utal");
            }

            return uppförandetal;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}


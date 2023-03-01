package OvningsUppgifter.Uppg_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Repository {

    public List<Barn> getBarn() throws ClassNotFoundException, IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/OvningsUppgifter/Propertiesfil/settings.properties"));

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select barn.id, barn.namn, rapport.uppförandetal from barn" +
                             " inner join rapport on rapport.barnid=barn.id ")
        ) {

            List<Barn> barnList = new ArrayList<>();
            while (rs.next()){
                Barn temp = new Barn();
                int id = rs.getInt("id");
                temp.setId(id);
                String namn = rs.getString("namn");
                temp.setNamn(namn);
                int uppförandetal = rs.getInt("uppförandetal");
                temp.setUppförandetal(uppförandetal);
                barnList.add(temp);
            }

            return barnList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Tomte> getTomte() throws ClassNotFoundException, IOException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/OvningsUppgifter/Propertiesfil/settings.properties"));

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("select id, namn from Tomte")
        ) {

            List<Tomte> tomteList = new ArrayList<>();
            while (rs.next()){
                Tomte temp = new Tomte();
                int id = rs.getInt("id");
                temp.setId(id);
                String namn = rs.getString("namn");
                temp.setNamn(namn);
                tomteList.add(temp);
            }

            return tomteList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

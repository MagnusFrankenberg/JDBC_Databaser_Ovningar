package OvningsUppgifter.Uppg_3a;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {

    private Connection c;
    private Properties p = new Properties();

    public Repository(){
        try{
            p.load(new FileInputStream("src/OvningsUppgifter/Propertiesfil/settings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bytTomteNamn(String oldName, String newName){

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             PreparedStatement stmt = c.prepareStatement("update Tomte set namn = ? where namn = ?");

        ) {
            stmt.setString(1, newName);
            stmt.setString(2, oldName);
            int result = stmt.executeUpdate();
            System.out.println(result +" rad updaterades");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public List<Tomte> allaTomtar() throws SQLException {
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

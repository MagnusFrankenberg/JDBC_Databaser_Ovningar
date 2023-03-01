package OvningsUppgifter.Uppg_3b;


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

    public void taBortTomte(String tomteNamn){

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             PreparedStatement stmt1 = c.prepareStatement("select id from Tomte where namn = ? into @myint");
             PreparedStatement stmt2 = c.prepareStatement("delete from Tomte WHERE Tomte.id = @myint");

        ) {
            stmt1.setString(1, tomteNamn);
            stmt1.executeQuery();
            stmt2.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

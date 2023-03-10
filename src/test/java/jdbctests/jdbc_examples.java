package jdbctests;

import org.testng.annotations.Test;

import javax.xml.transform.Result;
import java.sql.*;

public class jdbc_examples {

    //connection string
    String dbUrl = "jdbc:oracle:thin:@54.144.70.64:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select region_name from regions");

        while(resultSet.next()){
            String regionName = resultSet.getString("region_name");
            System.out.println(regionName);
        }

        //using same object to run another query
        //================
        resultSet = statement.executeQuery("Select * from countries");
        System.out.println("------------------------------------------------------");
        while(resultSet.next()){
            String countries = resultSet.getString(2);
            System.out.println(countries);
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void CountAndNavigate() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //how to find how many record(rows) i have for the query
        //go to last row
        resultSet.last();

        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        System.out.println(resultSet.getString("department_name"));

        //we need to move before the first row to get full list since we are at the last row right now
        resultSet.beforeFirst();

        System.out.println("---------------While loop starts-------------");
        while (resultSet.next()){
            System.out.println(resultSet.getString("department_name"));
        }




        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void metadata() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //get the databases related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User: "+ dbMetadata.getUserName());
        System.out.println("Database product name: "+ dbMetadata.getDatabaseProductName());
        System.out.println("Database product version: "+ dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver: "+ dbMetadata.getDriverName());
        System.out.println("Driver version: "+ dbMetadata.getDriverVersion());

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many column we have ?
        System.out.println("Column count:"+rsMetadata.getColumnCount());

        //column names
        System.out.println(rsMetadata.getColumnName(1));

        System.out.println("-------------------------------------------");

        //print all the column names dynamically
        int columnCount = rsMetadata.getColumnCount();

        for (int i = 1; i <=columnCount; i++) {
            System.out.println(rsMetadata.getColumnName(i));

        }





        resultSet.close();
        statement.close();
        connection.close();
    }
    @Test
    public void testLocation() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select postal_code,city from locations");

        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);


        resultSet.beforeFirst();
        while (resultSet.next()){
            System.out.println(
                            resultSet.getString("city")+" : "+
                            resultSet.getString("postal_code"));
           // System.out.println();
        }
        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

}

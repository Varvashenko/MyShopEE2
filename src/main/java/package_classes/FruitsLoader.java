package package_classes;

import java.sql.*;
import java.util.*;

public class FruitsLoader {
    private ArrayList<String> listFruits;
    static final String _URL = "jdbc:mysql://localhost:3306/mydbtest";
    static final String _USERNAME = "root";
    static final String _USERPASS = "anta";

    public FruitsLoader() {
        listFruits = new ArrayList<String>();
    }
    public void loadFruits() {
        String nameFruit;
        listFruits.clear();

        // connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            listFruits.add("DB.Class_Error : " + ex.getMessage());
            return;
        }
        try (Connection connection = DriverManager.getConnection(_URL, _USERNAME, _USERPASS)) {
            if(connection.isClosed()) {
                listFruits.add("DB.Close");
                return;
            }

            // get statement for Query
            Statement statement = connection.createStatement();
            String selectUsers = "SELECT id,name FROM mydbtest.fruits_table;";
            ResultSet resultSet = statement.executeQuery(selectUsers);
            while( resultSet.next() ) {
                nameFruit = resultSet.getString(2);
                listFruits.add( nameFruit );
            }

        } catch (SQLException ex) {
            listFruits.add( "DB.SQL_Error : " + ex.getMessage() );
        }
    }
    public int getCountOfFruits() {
        return listFruits.size();
    }
    public String getFruits(int index) {
        return listFruits.get(index);
    }
    public String saveNewFruit(String nameFruit) {
        // load Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            return "DB.Class_Error : " + ex.getMessage();
        }
        // select - query
        nameFruit = nameFruit.toLowerCase();
        int maxId = 0;
        int count = 0;
        // connection
        try (Connection connection = DriverManager.getConnection(_URL, _USERNAME, _USERPASS)) {
            if(connection.isClosed()) {
                return "DB.Close";
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            // 1. find Fruit2
            String findFruit2 = "SELECT COUNT(*) FROM mydbtest.fruits_table WHERE name='%s';";
            findFruit2 = String.format(findFruit2, nameFruit);
            resultSet = statement.executeQuery(findFruit2);
            if( resultSet.next() )
                count = resultSet.getInt(1);
            if(count != 0) {
                return "DB.Dublicate fruit";
            }
            // 2. select max(id)
            String searchMaxID = "SELECT MAX(id) FROM mydbtest.fruits_table;";
            resultSet = statement.executeQuery(searchMaxID);
            if( resultSet.next() )
                maxId = resultSet.getInt(1);
            maxId ++;
            // 3. insert statement for Query
            String insertFruit = "INSERT INTO mydbtest.fruits_table (id, name, price, rest) VALUES (%d, '%s', 0.0, 0.0);";
            insertFruit = String.format(insertFruit, maxId, nameFruit);
            int rows = statement.executeUpdate(insertFruit);
            if(rows == 0) return "DB.Error.Insert";
        } catch (SQLException ex) {
            return "DB.SQL_Error : " + ex.getMessage();
        }
        return nameFruit;
    }
}
/*
CREATE TABLE `mydbtest`.`fruits_table` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `price` FLOAT NULL,
  `rest` FLOAT NULL,
  PRIMARY KEY (`id`));
INSERT INTO `mydbtest`.`fruits_table` (`id`, `name`, `price`, `rest`) VALUES ('1', 'apple', '10', '100');
SELECT * FROM mydbtest.fruits_table;
DELETE FROM `mydbtest`.`fruits_table` WHERE (`id` = '7');
 */
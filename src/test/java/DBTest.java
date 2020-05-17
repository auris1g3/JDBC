import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.testng.Assert.assertEquals;

public class DBTest extends BaseTest {
    Statement statement = null;

    @Test(priority = 1)
    public void createTable() {
        try {
            statement = conn.createStatement();
            String createTable = "CREATE TABLE REGISTRATION " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(createTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void insertData() {

    }

    @Test(priority = 3)
    public void selectFromTable() {
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, first, last, age FROM Registration LIMIT 1");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                assertEquals(1, id);

                String first = resultSet.getString("first");
                assertEquals("Max", first);

                String last = resultSet.getString("last");
                assertEquals("Smith", last);

                int age = resultSet.getInt("age");
                assertEquals(18, age);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void updateData() {
        try {
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE Registration SET age = 30 where id = 1");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test(priority = 5)
    public void selectAfterUpdate() {
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT age FROM Registration WHERE id = 1");
            while (resultSet.next()) {
                int age = resultSet.getInt("age");
                assertEquals(30, age);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test(priority = 6)
    public void selectWithAsc() {
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Registration ORDER BY first ASC LIMIT 1");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                assertEquals(2, id);

                String first = resultSet.getString("first");
                assertEquals("Alex", first);

                String last = resultSet.getString("last");
                assertEquals("Williams", last);

                int age = resultSet.getInt("age");
                assertEquals(25, age);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test(priority = 7)
    public void deleteData() {
        try {
            statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM Registration WHERE id = 1");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test(priority = 8)
    public void selectAfterDelete() {
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Registration WHERE id = 1");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                assertEquals("NULL", id);

                String first = resultSet.getString("first");
                assertEquals("NULL", first);

                String last = resultSet.getString("last");
                assertEquals("NULL", last);

                int age = resultSet.getInt("age");
                assertEquals("NULL", age);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test(priority = 9)
    public void dropTable() {
        try {
            statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE Registration");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test(priority = 10)
    public void dropDataBase() {
        try {
            statement = conn.createStatement();
            statement.executeUpdate("DROP DATABASE students");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

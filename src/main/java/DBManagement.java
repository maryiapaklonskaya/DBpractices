import java.sql.*;
import java.util.Scanner;

/*
USING THE STATEMENT CLASS, CREATE A METHOD TO GET ANY USER BY ID FROM YOUR TABLE
 */
/*
    CREATE A METHOD THAT USES A PREPARED STATEMENT TO UPDATE USERS ADDRESS USING THE USER ID;
    updateAddress(2, "40 kentmanni")
                 (userId, newAddress)

     CREATE A METHOD THAT USES A PREPARED STATEMENT TO UPDATE orders.comment USING customers.customerNumber;

 */

public class DBManagement {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "rootroot");
//            fetchOfAllProducts(connection);
            //updateCustomerName(connection, 496, "MARYIA TEST");

//            getCustomers(connection, true);
            getCustomers(connection, false);

            //updateAddress(connection, 496, "This is new comment - Today is Oct 3");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static void fetchOfAllProducts(Connection connection) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter user ID");

        int ID = scan.nextInt();
        String fetchProducts = "SELECT * FROM classicmodels.customers WHERE customerNumber = " + ID;


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(fetchProducts);

            while(resultSet.next()) {
                System.out.println("ID \t Name \t\t\t\t phone number");
                System.out.print(resultSet.getString(1) + "\t ");
                System.out.print(resultSet.getString(2) + "\t ");
                System.out.println(resultSet.getString(5));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCustomerName(Connection connection, int ID, String userName){
        String updateQuery = "UPDATE Product SET Name = ? WHERE Name = ?";

        try{
            PreparedStatement pStatement = connection.prepareStatement(updateQuery);
            pStatement.setInt(1, ID);
            pStatement.setString(2, userName);
            pStatement.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();

        }
    }

    public static void updateAddress(Connection connection, int ID, String newComment){
        String getUsersGuery = "Select * FROM customers WHERE customersNumber = " +ID;
        String updatetheCommentQuery = "UPDATE orders SET comments = ? WHERE ID = ?";
        int commentID = 0;

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getUsersGuery);

            PreparedStatement pStatement = connection.prepareStatement(updatetheCommentQuery);
            pStatement.setString(1, newComment);
            pStatement.setInt(2, ID);

            pStatement.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();

        }
    }

    /*
        CREATE A FUNCTION IN SQL TO RETURN USERS SORTED IN ASCENDING OR DESCENDING ORDER
        getUsers(boolean asc);
        getUsers(false) ===> descending order
     */

    public static void getCustomers(Connection connection, boolean inputBoolean){
        //String ASC = "CALL getAllCustomersASC;";

        if(inputBoolean) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("call getAllCustomersASC;");

                System.out.println("\nCustomerID   Customer Name ASC\n");

                while (resultSet.next()) {
                    System.out.print(resultSet.getString(1) + "   --->   ");
                    System.out.println(resultSet.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("call getAllCustomersDESC;");

                System.out.println("\nCustomerID   Customer Name DESC\n");

                while (resultSet.next()) {
                    System.out.print(resultSet.getString(1) + "   --->   ");
                    System.out.println(resultSet.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



}


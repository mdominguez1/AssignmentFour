import java.sql.*;
import java.util.Scanner;
import java.io.Console;
class Exercise11{
    public static void main(String args[]){
        try{
            Class.forName("org.postgresql.Driver").newInstance();
            //Scanner rader = new Scanner(System.in); //Reading from System.in
            //System.out.println("Enter your username: ");
        }catch(Exception e){
            System.out.println("Exception: " + e.toString());
            System.exit(0);
        }
        String serverIp = "localhost:5435";
        Scanner reader = new Scanner(System.in); //Reading from System.in
        System.out.println("Enter your database name: ");
        String databasename = reader.nextLine();
        System.out.println("Enter your username: ");
        String username = reader.nextLine();
        System.out.println("Enter your password: ");
        String password = reader.nextLine();
        reader.close();
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String cs = null;
        
        System.out.println("Find the names of all students who have taken at least one" + 
            " Comp. Sci. course; make sure there are no duplicate names in result.");
        String tablename = "student";
        cs = "jdbc:postgresql://" + serverIp + "/" + databasename + "?user=" + username +
            "&password=" + password;
        try{
            conn = DriverManager.getConnection(cs);
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT DISTINCT name FROM " + tablename + " NATURAL JOIN" + 
                " course WHERE dept_name = 'Comp. Sci.'");

            while(rset.next()){
                System.out.println(rset.getString(1));
            }
            stmt.close();
            rset.close();
            conn.close();
        }catch (Exception e){
            System.out.println("Exception: " + e.toString());
            System.exit(0);
        }


        System.out.println("Find the IDs and names of all students who have not taken" +
            " any course offering before Spring 2009.");
        
        try{
            conn = DriverManager.getConnection(cs);
            stmt = conn.createStatement();
            rset = stmt.executeQuery("(SELECT ID, name FROM " + tablename + " NATURAL JOIN takes) " + 
                "EXCEPT (SELECT ID, name FROM student NATURAL JOIN takes WHERE YEAR < 2009)");
            while(rset.next()){
                System.out.print(rset.getDouble(1) + "\t");
                System.out.println(rset.getString(2));
            }

            stmt.close();
            rset.close();
            conn.close();
        }catch(Exception e){
            System.out.println("Exception: " + e.toString());
            System.exit(0);
        }


        System.out.println("For each department, find the maximum salary of instructor" + 
            " in that department. You may assume that every department has at least one instructor.");
        
        tablename = "instructor";
        
        try{
            conn = DriverManager.getConnection(cs);
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT dept_name, MAX(salary) FROM " + tablename + " GROUP BY dept_name");

            while(rset.next()){
                System.out.print(rset.getString(1) + "\t");
                System.out.println(rset.getInt(2) + "\t");
            }

            stmt.close();
            rset.close();
            conn.close();
        }catch(Exception e){
            System.out.println("Exception: " + e.toString());
            System.exit(0);
        }

        System.out.println("Find the lowest, across all departments, of the per-department maximum" + 
            " salary computed by the preceding query.");
       
        tablename = " (SELECT dept_name, MAX(salary) max_sal FROM instructor GROUP BY dept_name) h ";

        try{
            conn = DriverManager.getConnection(cs);
            stmt = conn.createStatement();
            rset = stmt.executeQuery("select MIN(max_sal) FROM" + tablename );
            
            while(rset.next()){
                System.out.println(rset.getInt(1) + "\t");
            }

            stmt.close();
            rset.close();
            conn.close();
        }catch(Exception e){
            System.out.println("Exception: " + e.toString());
            System.exit(0);
        }

    }//end of main
}

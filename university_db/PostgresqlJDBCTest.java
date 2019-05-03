import java.sql.*;
class PostgresqlJDBCTest{
    public static void main(String args[]){
        try{
        Class.forName(
            "org.postgresql.Driver").newInstance();
        }catch(Exception e){
            System.out.println("Exception: " + e.toString());
            System.exit(0);
        }
        //Change these to suit your installation
        String serverIp = "localhost:5435";
        String databasename = "mdominguez1";
        String username = "mdominguez1";
        String password = "seven";
        String tablename = "customers"; // Make sure this table exists 
                                        // in your database
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String cs = "jdbc:postgresql://" + serverIp + "/" +
        databasename + "?user=" + username + "&password=" + password;
        try{
            conn = DriverManager.getConnection(cs);
            stmt = conn.createStatement();
            rset = stmt.executeQuery(
                "select * from " + tablename);
            while(rset.next()){
                System.out.print(rset.getString(1) + "\t");
                System.out.print(rset.getString(2) + "\t");
                System.out.print(rset.getString(3) + "\t");
                System.out.println(rset.getDouble(4));
            }
            stmt.close();
            rset.close();
            PreparedStatement pstmt = conn.prepareStatement(
                "Insert into customers values (?, ?, ?, ?)");
            pstmt.setString(1, "c009");
            pstmt.setString(2, "United");
            pstmt.setString(3, "Sylva");
            pstmt.setDouble(4, 20.00);
            pstmt.execute();
            pstmt.close();
            stmt = conn.createStatement();
            rset = stmt.executeQuery( "select * from " + tablename);
            while(rset.next()){
                System.out.print(rset.getString(1) + "\t");
                System.out.print(rset.getString(2) + "\t");
                System.out.print(rset.getString(3) + "\t");
                System.out.println(rset.getDouble(4));
            }
            stmt.close();
            rset.close();
        }catch(SQLException e){
            System.out.println("Exception: " + e.toString());
            System.exit(0);
        }
    }//end of main
}

import Modal.Furniture;

import java.sql.*;

public class HelloWorldService {
    public void getValuesFromDatabase() {
        try {
            int id;
            String username, password;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/loginservice", "root", "6790");
            //here loginservice is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from accountloginentity");
            while (rs.next()) {
                id = rs.getInt(1);
                username = rs.getString(4);
                password = rs.getString(5);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public boolean authenticateUser(LoginRequest loginRequest) throws Exception {
        String password = null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/loginservice", "root", "6790");
//here loginservice is database name, root is username and password is 6790
            stmt = con.createStatement();
            String queryEmail = "select password from accountloginentity" +
                    " where loginEntity = '" + loginRequest.getUsername() + "'";

            String queryNumber = "select password from accountloginentity " +
                    "where mobileNumber = '" + loginRequest.getUsername()  + "'";
            if (loginRequest.getType().equals("email")) {
                rs = stmt.executeQuery(queryEmail);
                while (rs.next()) {
                    password = rs.getString("password");
                }
            } else if (loginRequest.getType().equals("num")) {
                rs = stmt.executeQuery(queryNumber);

                while (rs.next()) {
                    password = rs.getString("password");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            con.close();
            rs.close();
            stmt.close();
        }

        if (password.equals(loginRequest.getPassword())) {
            return true;
        }
        return false;
    }
    public boolean signUpUser(SignUpRequest request) throws Exception
    {
        String email = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/loginservice", "root", "6790");
    //here loginservice is database name, root is username and password is 6790

            String insertQuery ="INSERT INTO accountloginentity (loginEntity,password,type,mobileNumber) VALUES ('"+request.getEmail()+
                    "','"+request.getPassword()+"','"+request.getType()+"','"+request.getMobileNumber()+"')";
            stmt = con.prepareStatement(insertQuery);
            stmt.executeUpdate();

            String checkQuery = "select loginEntity from accountloginentity" +
                    " where loginEntity = '" + request.getEmail() +"'";

            rs = stmt.executeQuery(checkQuery);
            while (rs.next()) {
                    email = rs.getString("loginEntity");
                }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            con.close();
            rs.close();
            stmt.close();
        }

        if (email.equals(request.getEmail())) {
            return true;
        }
        return false;
    }
    public Furniture furnitureDetails()
    {

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        Furniture f = new Furniture();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/loginservice", "root", "6790");
        //here loginservice is database name, root is username and password is 6790
            stmt = con.createStatement();
            String queryEmail = "select * from furnituredetails";
            rs = stmt.executeQuery(queryEmail);

                while (rs.next()) {
                    f.setId(rs.getInt("id"));
                    f.setProdname(rs.getString("prodname"));
                    f.setProdcomp(rs.getString("prodcomp"));
                    f.setRating(rs.getString("rating"));
                    f.setPrice(rs.getString("price"));
                    f.setImageurl(rs.getString("imageurl"));
                }
            }
         catch (Exception e) {
            System.out.println(e);
        } finally
        {   try {
            if(con!=null)
            con.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }   try {
            if(rs!=null)
                rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }   try {
            if(stmt!=null)
                stmt.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        }
        return f;
    }
}

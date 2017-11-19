package psychology;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class PsychologyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        PsychologyDatabase db = null;
        
        Connection connection;
        Statement statement = null;
        PreparedStatement ps = null;
        ResultSet resultset = null;
        
        String query;
        String parameter;
        String table = "";
        
        boolean hasresults;
            
        
        try {
            db = new PsychologyDatabase();
            connection = db.getConnection();
            request.authenticate(response);
            if (request.isUserInRole("professor")) {
                if (!request.getParameter("search").equals("")) {
                    String[] arr = request.getParameter("search").split(" ");
                    query = "SELECT userrole FROM users WHERE (firstname = ? AND lastname = ?)";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, arr[0]);
                    ps.setString(2, arr[1]);
                    resultset = ps.executeQuery();
                    String role = "";
                    while (resultset.next()){
                        role = resultset.getString("userrole");
                    }
                    if (role.equals("student")) {                    
                        query = "SELECT * FROM users WHERE (firstname = ? AND lastname = ?)";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, arr[0]);
                        ps.setString(2, arr[1]);
                        resultset = ps.executeQuery();
                        table += db.getResultSetTable(resultset);
                        out.println(arr[0] + " " + arr[1] + "'s Info: " + table);

                        query = "SELECT userid FROM users WHERE firstname = ? AND lastname = ?";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, arr[0]);
                        ps.setString(2, arr[1]);
                        resultset = ps.executeQuery();
                        int userID = 0;
                        while (resultset.next()) {
                            userID = resultset.getInt("userid");
                        }

                        query = "SELECT researchslotid FROM studentresearch WHERE userid = '" + userID + "'";
                        ps = connection.prepareStatement(query);
                        resultset = ps.executeQuery();
                        List<Integer> researchSlotList = new ArrayList<>();
                        while (resultset.next()) {
                            researchSlotList.add(resultset.getInt("researchslotid"));
                        }
                        table = "";
                        for(int i = 0; i < researchSlotList.size(); i++) {
                            query = "SELECT researchid FROM researchslot WHERE researchslotid = '" + researchSlotList.get(i) + "'";
                            ps = connection.prepareStatement(query);
                            resultset = ps.executeQuery();
                            int researchID = 0;
                            while (resultset.next()) {
                                researchID = resultset.getInt("researchid");
                            }

                            query = "SELECT * FROM research WHERE researchid = '" + researchID + "'";
                            ps = connection.prepareStatement(query);
                            resultset = ps.executeQuery();                        
                            table += db.getResultSetTable(resultset);                                                  
                        }
                        out.println("All Student Research: " + table);
                    }
                }
                else {
                    query = "SELECT * FROM users WHERE userrole = 'student'";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();
                    table += db.getResultSetTable(resultset);
                    out.println("User's Table: " + table);

                    table = "";
                    query = "SELECT * FROM research";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();
                    table += db.getResultSetTable(resultset);
                    out.println("Research Table: " + table);

                    table = "";
                    query = "SELECT * FROM studentresearch";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();
                    table += db.getResultSetTable(resultset);
                    out.println("Student Research Table: " + table);

                    table = "";
                    query = "SELECT * FROM researchslot";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();
                    table += db.getResultSetTable(resultset);
                    out.println("Research Slot Table: " + table);
                }


            }
            
            else if (request.isUserInRole("administrator")) {
                if (!request.getParameter("search").equals("")) {
                    String[] arr = request.getParameter("search").split(" ");

                    query = "SELECT * FROM users WHERE (firstname = ? AND lastname = ?)";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, arr[0]);
                    ps.setString(2, arr[1]);
                    resultset = ps.executeQuery();
                    table += db.getResultSetTable(resultset);
                    out.println(arr[0] + " " + arr[1] + "'s Info: " + table);

                    query = "SELECT userid FROM users WHERE firstname = ? AND lastname = ?";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, arr[0]);
                    ps.setString(2, arr[1]);
                    resultset = ps.executeQuery();
                    int userID = 0;
                    while (resultset.next()) {
                        userID = resultset.getInt("userid");
                    }

                    query = "SELECT researchslotid FROM studentresearch WHERE userid = '" + userID + "'";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();
                    List<Integer> researchSlotList = new ArrayList<>();
                    while (resultset.next()) {
                        researchSlotList.add(resultset.getInt("researchslotid"));
                    }
                    table = "";
                    for(int i = 0; i < researchSlotList.size(); i++) {
                        query = "SELECT researchid FROM researchslot WHERE researchslotid = '" + researchSlotList.get(i) + "'";
                        ps = connection.prepareStatement(query);
                        resultset = ps.executeQuery();
                        int researchID = 0;
                        while (resultset.next()) {
                            researchID = resultset.getInt("researchid");
                        }

                        query = "SELECT * FROM research WHERE researchid = '" + researchID + "'";
                        ps = connection.prepareStatement(query);
                        resultset = ps.executeQuery();                        
                        table += db.getResultSetTable(resultset);                                                  
                    }
                    out.println("All Student Research: " + table);                    
                }
                else {
                    query = "SELECT * FROM users";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();
                    table += db.getResultSetTable(resultset);
                    out.println("User's Table: " + table);

                    table = "";
                    query = "SELECT * FROM research";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();
                    table += db.getResultSetTable(resultset);
                    out.println("Research Table: " + table);

                    table = "";
                    query = "SELECT * FROM studentresearch";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();
                    table += db.getResultSetTable(resultset);
                    out.println("Student Research Table: " + table);

                    table = "";
                    query = "SELECT * FROM researchslot";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();
                    table += db.getResultSetTable(resultset);
                    out.println("Research Slot Table: " + table);
                }
            }
            else if (request.isUserInRole("student")) {
                String studentEmail = request.getUserPrincipal().getName();
                
                query = "SELECT * FROM users WHERE useremail = '" + studentEmail + "'";
                ps = connection.prepareStatement(query);
                resultset = ps.executeQuery();
                table += db.getResultSetTable(resultset);
                out.println("Your Info: " + table);

                query = "SELECT userid FROM users WHERE useremail = '" + studentEmail + "'";
                ps = connection.prepareStatement(query);
                resultset = ps.executeQuery();
                int userID = 0;
                while (resultset.next()) {
                    userID = resultset.getInt("userid");
                }

                query = "SELECT researchslotid FROM studentresearch WHERE userid = '" + userID + "'";
                ps = connection.prepareStatement(query);
                resultset = ps.executeQuery();
                List<Integer> researchSlotList = new ArrayList<>();
                while (resultset.next()) {
                    researchSlotList.add(resultset.getInt("researchslotid"));
                }
                table = "";
                for(int i = 0; i < researchSlotList.size(); i++) {
                    query = "SELECT researchid FROM researchslot WHERE researchslotid = '" + researchSlotList.get(i) + "'";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();
                    int researchID = 0;
                    while (resultset.next()) {
                        researchID = resultset.getInt("researchid");
                    }

                    query = "SELECT * FROM research WHERE researchid = '" + researchID + "'";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();                        
                    table += db.getResultSetTable(resultset);                                                  
                }
                out.println("Your Research: " + table);                    
                
            }
        }
        
        catch (Exception e) {
            System.out.println(e.toString());
        }
        
        finally {
            out.close();
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            if (statement != null) { try { statement.close(); statement = null; } catch (Exception e) {} }
            if (ps != null) { try { ps.close(); ps = null; } catch (Exception e) {} }
            if (db != null) { db.closeConnection(); }
        }
    }
    
    private void printAllData(String role) {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doGet(request, response);
        
    }

    @Override
    public String getServletInfo() { return "Database Security Lab Servlet"; }

}

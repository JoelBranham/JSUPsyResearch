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
                if (!request.getParameter("profsearch").equals("")) {
                    /* showing all students data */
                    String[] arr = request.getParameter("profsearch").split(" ");
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
                        query = "SELECT UserID, FirstName, LastName, Useremail, UserRole, UserPsychMajor, UserPsychMinor, CreditsCompleted FROM users WHERE (firstname = ? AND lastname = ?)";
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

                        query = "SELECT researchslotid FROM studentresearch WHERE userid = (SELECT userid FROM users WHERE firstname = ? AND lastname = ?)";
                        ps = connection.prepareStatement(query); 
                        ps.setString(1, arr[0]);
                        ps.setString(2, arr[1]);
                        resultset = ps.executeQuery();
                        List<Integer> researchSlotList = new ArrayList<>();
                        while (resultset.next()) {
                            researchSlotList.add(resultset.getInt("researchslotid"));
                        }
                        table = "";
                        for(int i = 0; i < researchSlotList.size(); i++) {
                            query = "SELECT * FROM research WHERE researchid = (SELECT researchid FROM researchslot WHERE researchslotid = '" + researchSlotList.get(i)+ "')";
                            ps = connection.prepareStatement(query);
                            resultset = ps.executeQuery();                        
                            table += db.getResultSetTable(resultset);                                                  
                        }
                        out.println("All Student Research: " + table);
                    }
                }
                else {
                    /* showing specified student data */
                    query = "SELECT UserID, FirstName, LastName, Useremail, UserRole, UserPsychMajor, UserPsychMinor, CreditsCompleted FROM users WHERE userrole = 'student'";
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
                if (!request.getParameter("adminsearch").equals("")) {
                    
                    /* show all users */
                    String[] arr = request.getParameter("adminsearch").split(" ");
                    query = "SELECT * FROM users WHERE (firstname = ? AND lastname = ?)";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, arr[0]);
                    ps.setString(2, arr[1]);
                    resultset = ps.executeQuery();
                    table += db.getResultSetTable(resultset);
                    out.println(arr[0] + " " + arr[1] + "'s Info: " + table);
                    
                
                    query = "SELECT researchslotid FROM studentresearch WHERE userid = (SELECT userid FROM users WHERE firstname = ? AND lastname = ?)";
                    ps = connection.prepareStatement(query); 
                    ps.setString(1, arr[0]);
                    ps.setString(2, arr[1]);
                    resultset = ps.executeQuery();
                    List<Integer> researchSlotList = new ArrayList<>();
                    while (resultset.next()) {
                        researchSlotList.add(resultset.getInt("researchslotid"));
                    }
                    table = "";
                    for(int i = 0; i < researchSlotList.size(); i++) {
                        query = "SELECT * FROM research WHERE researchid = (SELECT researchid FROM researchslot WHERE researchslotid = '" + researchSlotList.get(i)+ "')";
                        ps = connection.prepareStatement(query);
                        resultset = ps.executeQuery();                        
                        table += db.getResultSetTable(resultset);                                                  
                    }
                    out.println("All Student Research: " + table);                    
                }
                else {
                    /* show specified user */
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
                int userid = -1;
                boolean canExecute = true;
                String errorMessage = "";
                String studentEmail = request.getUserPrincipal().getName();
                query = "SELECT userid FROM users WHERE useremail = '" + studentEmail + "'";
                ps = connection.prepareStatement(query);
                resultset = ps.executeQuery(query);
                while (resultset.next()) {
                    userid = resultset.getInt("userid");
                }
                if (!request.getParameter("studentsearch").equals("")) {
                    /* Registering for slot if search is not empty */
                    int researchSlotID = -1;
                    try {
                        researchSlotID = Integer.parseInt(request.getParameter("studentsearch"));
                    }
                    catch (NumberFormatException e) {
                        canExecute = false;
                    }
                    
                    if (researchSlotID < 0) {
                        canExecute = false;
                        errorMessage += "Invalid researchSlotID.";
                    }
                    
                    query = "SELECT researchslotid FROM studentresearch WHERE userid = '" + userid + "'";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();
                    while (resultset.next() && canExecute) {
                        if (researchSlotID == resultset.getInt("researchslotid")) {
                            canExecute = false;
                            errorMessage += "You are already registered for research with researchSlotID: " + researchSlotID + ".\n";
                        }
                    }
                    if (canExecute){
                        query = "SELECT researchslotopenings FROM researchslot WHERE researchslotid = '" + researchSlotID + "'";
                        ps = connection.prepareStatement(query);
                        ResultSet rs = ps.executeQuery();
                        /* Checks to see if there exists a research with given researchSlotID */
                        if (!rs.next()){
                            errorMessage += "Invalid researchSlotID.";
                            canExecute = false;
                        }
                        /* Checks to see if research with associated researchSlotID has openings */
                        while (rs.next()){
                            if (rs.getInt("researchslotopenings") < 1){
                                errorMessage += "No available slots for research with researchSlotID: "  + researchSlotID + ".\n";
                                canExecute = false;
                            }
                        }
                        
                    }
                   
                    /* Updating researchslot table and adding studentresearch row if all of the safety features pass */
                    if (canExecute) {
                        query = "UPDATE psychology.researchslot SET researchslotopenings = researchslotopenings - 1 WHERE researchslotid = ?";
                        ps = connection.prepareStatement(query);
                        ps.setInt(1,researchSlotID);
                        ps.executeUpdate();

                        query = "INSERT INTO studentresearch (userid, researchslotid) VALUES (?,?)";
                        ps = connection.prepareStatement(query);
                        ps.setInt(1, userid);
                        ps.setInt(2, researchSlotID);
                        ps.executeUpdate();
                    }
                   
                }   
                /* Querying and showing student's name and data about his/her research */
                query = "SELECT UserID, FirstName, LastName, UserEmail, UserRole, UserPsychMajor, UserPsychMinor, CreditsCompleted FROM users WHERE useremail = '" + studentEmail + "'";
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

                    query = "SELECT RS.ResearchSlotID, R.ResearchID, R.ResearchName, RS.StartTime, RS.EndTime, R.ResearchFacilitator, R.ResearchDescription, R.ResearchCredits FROM research AS R INNER JOIN researchslot as RS ON RS.researchid = R.researchid WHERE r.researchid = '" + researchID + "' AND rs.researchslotid = '" + researchSlotList.get(i) + "'";
                    ps = connection.prepareStatement(query);
                    resultset = ps.executeQuery();                        
                    table += db.getResultSetTable(resultset);                                                  
                }
                out.println("Your Research: " + table);
                table = "";
                
                /* Querying and showing slots student can register for */
                query = "SELECT RS.ResearchSlotID, RS.ResearchID, R.ResearchName, RS.StartTime, RS.EndTime, R.ResearchFacilitator, R.ResearchDescription, R.ResearchCredits FROM researchslot as RS INNER JOIN research as R ON RS.researchid = R.researchid WHERE RS.researchslotopenings > 0 AND RS.researchslotid NOT IN (SELECT SR.researchslotid FROM studentresearch as SR WHERE SR.userid = '" + userID + "')";
                ps = connection.prepareStatement(query);
                resultset = ps.executeQuery();                    
                table += db.getResultSetTable(resultset); 
                out.println("Research slots you can register for: " + table);
                if (!canExecute) {
                    out.println(errorMessage);
                }
            }
        db.closeConnection();
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


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doGet(request, response);
        
    }

    @Override
    public String getServletInfo() { return "Database Security Lab Servlet"; }

}

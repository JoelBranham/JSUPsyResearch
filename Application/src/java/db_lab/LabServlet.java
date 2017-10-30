package db_lab;

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

public class LabServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        LabDatabase db = null;
        
        Connection connection;
        Statement statement = null;
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        
        String query;
        String parameter;
        String table = "";
        
        boolean hasresults;
            
        try {
            
            db = new LabDatabase();
            connection = db.getConnection();

            parameter = request.getParameter("search");
            query = "SELECT * FROM research";
           // query = "SELECT * FROM people WHERE lastname='" + parameter + "'";
            // INSERT NEW CODE HERE
            
            statement = connection.createStatement();
            // INSERT NEW CODE HERE
            // INSERT NEW CODE HERE
            
            hasresults = statement.execute(query);
            
            while ( hasresults || statement.getUpdateCount() != -1 ) {
                
                if ( hasresults ) {
                    resultset = statement.getResultSet();
                    table += db.getResultSetTable(resultset);
                }
                
                else {
                    
                    if ( statement.getUpdateCount() == -1 ) {
                        break;
                    }
                    
                }

                hasresults = statement.getMoreResults();
            
            }

            out.println("<p>Search Parameter: " + parameter + "</p>" + table);

        }
        
        catch (Exception e) {
            System.out.println(e.toString());
        }
        
        finally {
            
            out.close();
            
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
            if (statement != null) { try { statement.close(); statement = null; } catch (Exception e) {} }
            
            if (pstatement != null) { try { pstatement.close(); pstatement = null; } catch (Exception e) {} }
            
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

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

            parameter = request.getParameter("search");
           
            query = "SELECT * FROM research WHERE ResearchID=?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(parameter));
            resultset = ps.executeQuery();
            table += db.getResultSetTable(resultset);
            out.println("<p>Search Parameter: " + parameter + "</p>" + table);
            
            
//            statement = resultset.getStatement();
//            hasresults = resultset.toString().length() > 0;
//            while ( hasresults || statement.getUpdateCount() != -1 ) {
//                
//                if ( hasresults ) {
//                    // resultset = ps.executeQuery(query);
//                    System.out.println("Resultset---: " + resultset);
//                    table += db.getResultSetTable(resultset);
//                }
//                
//                else {
//                    if ( statement.getUpdateCount() == -1 ) {
//                        break;
//                    }
//                }
//                hasresults = statement.getMoreResults();
//            }
//            out.println("<p>Search Parameter: " + parameter + "</p>" + table);
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

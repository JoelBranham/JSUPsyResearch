<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    
    <head>
        
        <title>Database Security Lab</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <script type="text/javascript" src="libraries/jquery-2.2.4.min.js"></script>
        
    </head>
    
    <body>
        
        <%
            if ( request.isUserInRole("administrator") ) {
        %>

        <p>You are logged in as an ADMINISTRATOR.</p>

        <%
            }

            else if ( request.isUserInRole("user") ) {
        %>

        <p>You are logged in as a USER.</p>

        <%
            }
        %>
        
        <form name="searchform" id="searchform" onsubmit="return submitSearchForm();">

            <p>Enter Search Parameter: <input type="text" name="search" size="80"></p>
            <input type="submit" value="Submit">

        </form>
        
        <div id="resultsarea"></div>
        
        <script type="text/javascript">

            $(document).ready(function () {
                
                $("#resultsarea").html("<p>Results will appear here ...</p>");

            });
            
            var submitSearchForm = function() {

                if ( $("#searchform input[name=search]").val() === "" ) {
                    
                    alert("You must enter a search parameter!  Please try again.");
                    return false;
                    
                }

                $.ajax({

                    url: 'LabServlet',
                    method: 'GET',
                    data: $('#searchform').serialize(),

                    success: function(response) {

                        $("#resultsarea").html(response);

                    }

                });

                return false;

            };

        </script>
        
    </body>
    
</html>

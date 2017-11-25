<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    
    <head>
        
        <title>JSU Psychology Research</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <script type="text/javascript" src="libraries/jquery-2.2.4.min.js"></script>
        
    </head>
    
    <body>
        
        <%
            if ( request.isUserInRole("administrator") ) {
        %>
        <form name="searchform" id="searchform" onsubmit="return submitSearchForm();">

            <p>Enter first and last name of any user (or no input for all data) <input type="text" name="adminsearch" size="80"></p>
            <input type="submit" value="Submit">

        </form>
        
        <div id="resultsarea"></div>
        
        <script type="text/javascript">

            $(document).ready(function () {
                
                submitSearchForm();

            });
            
            var submitSearchForm = function() {
                $.ajax({

                    url: 'PsychologyServlet',
                    method: 'GET',
                    data: $('#searchform').serialize(),

                    success: function(response) {

                        $("#resultsarea").html(response);

                    }

                });

                return false;

            };

        </script>
        
        <p>You are logged in as an ADMINISTRATOR.</p>

        <%
            }
            
            else if ( request.isUserInRole("professor") ) {
        %>
        <form name="searchform" id="searchform" onsubmit="return submitSearchForm();">

            <p>Enter student first and last name (or no input for all data) <input type="text" name="profsearch" size="80"></p>
            <input type="submit" value="Submit">

        </form>
        
        <div id="resultsarea"></div>
        
        <script type="text/javascript">

            $(document).ready(function () {
                
                submitSearchForm();

            });
            
            var submitSearchForm = function() {
                $.ajax({

                    url: 'PsychologyServlet',
                    method: 'GET',
                    data: $('#searchform').serialize(),

                    success: function(response) {

                        $("#resultsarea").html(response);

                    }

                });

                return false;

            };
            

        </script>
        
        <p>You are logged in as a PROFESSOR.</p>

        
        <%
            }          
            else if ( request.isUserInRole("student") ) {
        %>
       
        
        
        <div id="resultsarea"></div>
        <form name="searchform" id="searchform" onsubmit="return submitSearchForm();">

            <p>Enter ResearchSlotID (shown above) to register for associated research (or nothing for all data):  <input type="text" name="studentsearch" size="80"></p>
            <input type="submit" value="Submit">

        </form>
        
        <script type="text/javascript">

            $(document).ready(function () {
                
                submitSearchForm();

            });
            
            var submitSearchForm = function() {
                $.ajax({

                    url: 'PsychologyServlet',
                    method: 'GET',
                    data: $('#searchform').serialize(),

                    success: function(response) {

                        $("#resultsarea").html(response);

                    }

                });

                return false;

            };
            

        </script>
        
        
        <%
            }
        %>
        
    </body>
    
</html>

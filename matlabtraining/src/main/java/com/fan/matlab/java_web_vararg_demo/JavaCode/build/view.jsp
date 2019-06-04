<%@ page import="vararg_java.vararg_javaclass" %>
<%@ page import="com.mathworks.toolbox.javabuilder.MWException" %>

<%@ taglib prefix="wf" uri="/WEB-INF/webfigures.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
  <head>
      <title>VarArg Java Servlet Example</title>
  </head>

  <body>
  <H1 align="center">VarArg Java Server Page</H1><BR><BR>
  <div ALIGN="CENTER">
      <wf:web-figure root="WebFigures" name="Vararg_Figure" scope="session"/>            
  </div>

  <%
     int i = ((Integer)pageContext.getSession().getAttribute("numOutputs")).intValue();
     if(i > 0)
     {
         double mean = ((Double)pageContext.getSession().getAttribute("mean")).doubleValue();
  %>
        <BR><BR>
        <table align="center" border="2">
            <tr><td>Mean :</td><td><%= mean %></td></tr>

        <% if(i > 1)
           {
              double stdDev = ((Double)pageContext.getSession().getAttribute("stdDev")).doubleValue();
        %>
            <tr><td>Std Dev :</td><td><%= stdDev %></td></tr>            
        <%
           }
        %>

        </table>
  <%
     }
  %>
  </body>
</html>






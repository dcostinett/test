<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%--
  Created by IntelliJ IDEA.
  User: costd035
  Date: 1/13/15
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>

  <%
      String[] genresSelected = request.getParameterValues("genre");
      List<String> genres = Arrays.asList(genresSelected);

      boolean isSciFiSelected = genres.contains("scifi");
      boolean isClassicsSelected = genres.contains("classic");
      boolean isFantasySelected = genres.contains("fantasy");


  %>


  </body>
</html>

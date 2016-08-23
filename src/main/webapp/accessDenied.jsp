<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Access Denied</title>
  </head>

<body>
<h1>Sorry, access is denied</h1>

<p>
<%= request.getAttribute("SPRING_SECURITY_403_EXCEPTION")%>
</p>
<p>
<%      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) { %>
        Authentication object as a String: <%= auth.toString() %><br /><br />
<%      } %>
</p>
<p>
<a href="<c:url value='/'/>">Home</a>.
</p>
</body>
</html>

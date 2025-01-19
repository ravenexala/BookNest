<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true"%>
<%
    // Invalidate the session to log the user out
    session.invalidate();
%>

<!-- Redirecting to the home page after logout -->
<c:redirect url="/BookNest" />

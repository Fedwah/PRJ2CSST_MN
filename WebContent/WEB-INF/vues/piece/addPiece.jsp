<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ajouter une piece</title>
</head>
<body>
<form action="piece" method="post">
Code de la piece :<input type="text" name="code"><br/>
Nom de la piece :<input type="text" name="nom"><br/>
Marque :<input type="text" name="mark"><br/>
Modele :<input type="text" name="modal"><br/>
<input type="submit" value="ajouter" name="action">
</form>

</body>
</html>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid">
	<h1 class="page-title">Importer les utilisateurs</h1>
	 
	 
          <form action="DataImport" method="post">

          <input name="filename" type="file" size="20">

            <input type="submit" value="Import">
           </form>
</div>
 
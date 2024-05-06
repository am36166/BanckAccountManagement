<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un Client</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>

 
 <div class="container mt-4">
 <c:if test="${not empty param.message}">
                                <div class="alert alert-success" role="alert">
                                    <i class="fas fa-check-circle"></i> ${param.message}
                                </div>
                            </c:if>
    <div class="row justify-content-center">
                    
        <div class="col-md-6">
            <h2>Devenir Client</h2>
            <form action="enregistrerClient.do" method="post">
                <div class="form-group">
                    <label for="email">Email :</label>
                    <input type="email" class="form-control" name="email" placeholder="Entrez votre email">
                </div>
                <div class="form-group">
                    <label for="nom">Nom :</label>
                    <input type="text" class="form-control" name="nom" placeholder="Entrez votre nom">
                </div>
                <button type="submit" class="btn btn-primary">Créer</button>
            </form>
        </div>
    </div>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script >
setTimeout(function() {
    $('.alert').fadeOut('slow');
}, 3000);
</script>
</body>
</html>

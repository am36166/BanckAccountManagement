<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un Compte</title>
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

                            <c:if test="${not empty param.erreur}">
                                <div class="alert alert-danger" role="alert">
                                    <i class="fas fa-exclamation-circle"></i> ${param.erreur}
                                </div>
                            </c:if>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2>Créer un Compte</h2>
            <form action="enregistrerCompte.do" method="post">
             <div class="form-group">
                        <label for="Code">Code client :</label>
                        <input type="number" class="form-control" name="codeclient" placeholder="Entrez le Code du client">
                    </div>
                <div class="form-group">
                        <label for="Code">Code Compte :</label>
                        <input type="text" class="form-control" name="Compte" placeholder="Entrez le Code du compte">
                    </div>
                <div class="form-group">
                    <label for="typeCompte">Type de compte :</label>
                    <select class="form-control" name="typeCompte" id="typeCompte">
                        <option value="courant">Compte Courant</option>
                        <option value="epargne">Compte Épargne</option>
                    </select>
                </div>
                <div id="epargneFields" style="display: none;">
                    <div class="form-group">
                        <label for="taux">Taux :</label>
                        <input type="text" class="form-control" name="tauxStr" placeholder="Entrez le taux">
                    </div>
                </div>
                <div id="courantFields" style="display: none;">
                    <div class="form-group">
                        <label for="decouvert">Découvert :</label>
                        <input type="text" class="form-control" name="decouvertStr" placeholder="Entrez le découvert">
                    </div>
                </div>
                <div class="form-group">
                    <label for="solde">Solde initial :</label>
                    <input type="text" class="form-control" name="soldeStr" placeholder="Entrez le solde initial">
                </div>
                <button type="submit" class="btn btn-primary">Créer</button>
            </form>
        </div>
    </div>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    $(document).ready(function() {
    	 setTimeout(function() {
             $('.alert').fadeOut('slow');
         }, 3000);

        $('#typeCompte').change(function() {
            if ($(this).val() === 'epargne') {
                $('#epargneFields').show();
                $('#courantFields').hide();
            } else {
                $('#epargneFields').hide();
                $('#courantFields').show();
            }
        });
    });
</script>
</body>
</html>

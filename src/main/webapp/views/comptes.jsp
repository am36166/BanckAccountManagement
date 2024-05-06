<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Votre Banque</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        /* Ajoutez vos styles personnalisés ici */
        .alert {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container mt-4">
    <div class="row align-items-start">
        <div class="col-md-6">
            <div class="card mb-2">
                <div class="card-header">
                    <i class="fas fa-user"></i> Consultation du Compte:
                </div>
                <div class="card-body">
                    <form action="consultercompte.do" method="get">
                        <div class="form-group">
                            <label for="codeCompte">Code Compte :</label>
                            <input type="text" class="form-control" name="codeCompte" value="${param.codeCompte}" placeholder="Entrez le code du compte">
                        </div>
                        <button type="submit" class="btn btn-primary"><i class="fas fa-search"></i> OK</button>
                    </form>
                </div>
            </div>
        </div>
      <c:if test="${compte != null}">
        
        <div class="col-md-6">
            <div class="card mb-2">
                <div class="card-header">
                    <i class="fas fa-exchange-alt"></i> Opérations sur le compte:
                </div>
                                            
                <div class="card-body">
                    <form action="effectuerTransaction.do" method="post">
                        <div class="form-group">
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
                            <c:if test="${compte != null}">
                                <input type="hidden" name="codeCompte" value="${compte.numCompte}">
                                <p>Code de compte: <c:out value="${compte.numCompte}"/></p>
                            </c:if>
                            <label for="typeTransaction">Type de transaction :</label>
                            <select class="form-control" name="typeTransaction" id="typeTransaction">
                                <option value="retrait">Retrait</option>
                                <option value="virement">Virement</option>
                                <option value="versement">Versement</option>
                            </select>
                        </div>
                        <div class="form-group" id="compteCibleField" style="display: none;">
                            <label for="compteCible">Compte cible :</label>
                            <input type="text" class="form-control" name="compteCible" id="compteCible" placeholder="Entrez le compte cible">
                        </div>
                        <div class="form-group">
                            <label for="montant">Montant :</label>
                            <input type="text" class="form-control" name="montant" placeholder="Entrez le montant">
                        </div>
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </form>
                </div>               
            </div>
        </div>
      </c:if>    
     
    </div>
  <c:if test="${compte != null}">
    
    <div class="row">
        <div class="col-md-6 mb-2">
            <div class="card mb-2">
                <div class="card-header">
                    Informations sur le compte:
                </div>
                <div class="card-body">
                    <c:if test="${client !=null }">
                        <p>Nom du Client: <c:out value="${client.nom}"/></p>
                        <p>Adresse email: <c:out value="${client.email}"/></p>
                    </c:if>
                    <c:if test="${compte != null}">
                        <p>Code de compte: <c:out value="${compte.numCompte}"/></p>
                        <p>Solde: <c:out value="${compte.solde}"/></p>
                        <p>Date de Création: <c:out value="${compte.dateCreation}"/></p>
                        <p>Type de compte: <c:out value="${nomClasse}"/></p>
                    </c:if>
                    <c:if test="${erreur != null}">
                        <p class="text-danger"><c:out value="${erreur}"/></p>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card mb-2">
                <div class="card-header">
                    Liste des opérations:
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Num</th>
                                <th>Type</th>
                                <th>Date</th>
                                <th>Montant</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="operation" items="${operations}">
                                <tr>
                                    <td><c:out value="${operation.numOperation}"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${operation.getClass().getSimpleName() eq 'Retrait'}">
                                                Retrait
                                            </c:when>
                                            <c:when test="${operation.getClass().getSimpleName() eq 'Versement'}">
                                                Versement
                                            </c:when>
                                            <c:otherwise>
                                                Type inconnu
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><c:out value="${operation.dateOperation}"/></td>
                                    <td><c:out value="${operation.montant}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>

                </div>
            </div>
        </div>
    </div>
 </c:if>    
  
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    $(document).ready(function() {
        // Permet de cacher les messages d'alerte après quelques secondes
        setTimeout(function() {
            $('.alert').fadeOut('slow');
        }, 3000);

        // Affiche ou cache le champ "Compte cible" en fonction du type de transaction sélectionné
        $('#typeTransaction').change(function() {
            var selectedValue = $(this).val();
            var compteCibleField = $('#compteCibleField');

            if (selectedValue === 'virement') {
                compteCibleField.show();
            } else {
                compteCibleField.hide();
            }
        });
    });
</script>
</body>
</html>
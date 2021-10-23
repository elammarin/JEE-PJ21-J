<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html><%
String ctxPath = request.getContextPath();
%>
<head>
  <title>Welcome</title>
  <meta http-equiv='Content-Type' content='text/html' />
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
  <script src="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/locales/bootstrap-datepicker.fr.min.js" charset="UTF-8"></script>
</head>
<body>
<div class="container">
<form method="POST" action="ordermanager.html">
  <div class="row">
    <div class="form-group col-lg-3">
      <label for="customerID" class="col-sm-15 col-form-label">Entrez votre identifiant client afin de récuperer vôtre commande :</label>
      <input type="text" name="customerID" class="form-control input" id="customerID" placeholder="Enter customerID">
    </div>
  </div>
  <button type="submit" class="btn btn-primary" >Valider</button>
</form>
</div>
<script src="<%= ctxPath %>/js/bootstrap.min.js" type="text/javascript"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<%= ctxPath %>/js/ie10-viewport-bug-workaround.js"></script>
</body>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CEP - Cadastro</title>
</head>
  <body>
    <h1>CEP ADICIONADO!!!</h1>
    <hr />
    <form action="add" method="post">
      CEP: <input type="text" name="cep.cep" /><br />
      Endereco: <input type="text" name="cep.endereco" /><br />
      Bairro: <input type="text" name="cep.bairro" /><br />
      Cidade: <input type="text" name="cep.cidade" /><br />
      Estado: <input type="text" name="cep.estado" /><br />
      Nome Estado: <input type="text" name="cep.nome_estado" /><br />
      Codigo Pais: <input type="text" name="cep.country_code" /><br />
      
      <input type="submit" value="Gravar" />
    </form>
  </body>
</html>
<%-- 
    Document   : index
    Created on : 29 ago 2023, 0:11:08
    Author     : NICOLAS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Iniciar Sesión</h1>
        <form action="aqui va algo, luego lo pongo" method="post">
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" required><br>

            <label for="contraseña">Contraseña:</label>
            <input type="password" id="contraseña" name="contraseña" required><br>

            <input type="submit" value="Iniciar Sesión">
        </form>

        <p>¿Ya tienes una cuenta? <a href="Registrarse.jsp">Registrarse</a></p>
    </body>
</html>

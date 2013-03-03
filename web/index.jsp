<%-- 
    Document   : index
    Created on : 22.1.2013, 17:09:56
    Author     : Arto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Työajanseuranta</title>
    </head>
    <body>
        <p>Kirjaudu sisään.</p>
        <form action="Kirjautuminen" method="POST">
            <fieldset>
                <legend>Anna käyttäjatunnus ja salasana</legend>
                <label for="tunnus">Käyttäjätunnus:</label>
                <input type="text" name="tunnus" id="tunnus" />
                <label for="salasana">Salasana:</label>
                <input type="password" name="salasana" id="salasana" />
                <input type="submit" value="Kirjaudu" />
            </fieldset>
        </form>
    </body>
</html>

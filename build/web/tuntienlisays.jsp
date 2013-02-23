<%-- 
    Document   : tuntienlisays
    Created on : 26.1.2013, 11:05:56
    Author     : Arto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Lisää kirjaus.</p>
        <form action="TuntienLisays" method="POST">
            <fieldset>
                <legend>Syötä käytetyt tunnit</legend>
                <label for="projekti">Projekti:</label>
                <input type="text" name="projekti" id="projekti" />
                <label for="tyolaji">Työlaji:</label>
                <input type="text" name="tyolaji" id="tyolaji" />
                <label for="tunnit">Tunnit:</label>
                <input type="number" name="tunnit" id="tunnit" />
                <input type="submit" value="Lisää" />
<!--                <select>
                    <option value="1">yksi</option>
                    <option value="2">kaksi</option>
                    <option value="3">kolme</option>
                    <option value="4">neljä</option>
                </select>-->
            </fieldset>
        </form>
    </body>
</html>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/login.css">
</head>
<body class="login-page">
<div class="container">
    <div class="login-box">
        <h2>Já estuda com a gente?</h2>
        <p>Faça seu login e boa aula!</p>
        <a href="/admin/home" class="btn-login">ENTRAR</a>
    </div>

    <div class="courses">
        <h2>Ainda não estuda com a gente?</h2>
        <p>São mais de mil cursos nas seguintes áreas:</p>

        <div class="grid">
            <c:forEach items="${categories}" var="category">
                <c:set var="safeColor" value="${empty category.color ? '#7CFFCB' : category.color}" />
                <div class="card" data-color="${safeColor}">
                    <h3 class="escola">Escola_</h3>
                    <h4 class="escola" style="margin: 0;">${category.name}</h4>
                    <p>
                        <c:forEach items="${category.courses}" var="course" varStatus="status">
                            ${course.name}<c:if test="${!status.last}">, </c:if>
                        </c:forEach>
                    </p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script src="/assets/js/color_category.js"></script>
</body>
</html>
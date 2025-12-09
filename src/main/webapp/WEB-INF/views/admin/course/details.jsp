<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Detalhes do Curso</title>
    <link rel="stylesheet" href="/assets/external-libs/bootstrap/css/bootstrap.min.css">
</head>
<body>

<div class="container">

    <section class="panel panel-primary vertical-space">

        <div class="panel-heading">
            <h1>Detalhes do Curso</h1>
        </div>

        <div class="panel-body form-horizontal">

            <div class="row form-group">

                <div class="col-md-9">
                    <label><strong>Nome do Curso:</strong></label>
                    <p class="form-control-static">${course.name()}</p>
                </div>

                <div class="col-md-9">
                    <label><strong>Código:</strong></label>
                    <p class="form-control-static">${course.code()}</p>
                </div>

                <div class="col-md-9">
                    <label><strong>Instrutor:</strong></label>
                    <p class="form-control-static">${course.instructor().name()}</p>
                </div>

                <div class="col-md-9">
                    <label><strong>Categoria:</strong></label>
                    <p class="form-control-static">${course.category().name()}</p>
                </div>

                <div class="col-md-9">
                    <label><strong>Status:</strong></label>
                    <p class="form-control-static">
                        <c:choose>
                            <c:when test="${course.status() == 'ACTIVE'}">
                                <span class="label label-success">Ativo</span>
                            </c:when>
                            <c:otherwise>
                                <span class="label label-danger">Inativo</span>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>

                <div class="col-md-9">
                    <label><strong>Descrição:</strong></label>
                    <p class="well">${course.description()}</p>
                </div>

                <div class="col-md-9">
                    <label><strong>Criado em:</strong></label>
                    <p class="form-control-static">
                        ${course.createdAt().format(formatter)}
                    </p>
                </div>

                <c:if test="${course.inactiveAt() != null}">
                    <div class="col-md-9">
                        <label><strong>Inativado em:</strong></label>
                        <p class="form-control-static">
                            ${course.inactiveAt().format(formatter)}
                        </p>
                    </div>
                </c:if>

            </div>

            <a class="btn btn-secondary" href="/admin/courses">Voltar</a>

        </div>

    </section>

</div>

</body>
</html>

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

            <div class="form-group row">
                <label class="col-md-3 control-label"><strong>Nome do Curso:</strong></label>
                <div class="col-md-9">
                    <p class="form-control-static">${course.name()}</p>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-md-3 control-label"><strong>Código:</strong></label>
                <div class="col-md-9">
                    <p class="form-control-static">${course.code()}</p>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-md-3 control-label"><strong>Instrutor:</strong></label>
                <div class="col-md-9">
                    <p class="form-control-static">${course.instructor().name()}</p>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-md-3 control-label"><strong>Categoria:</strong></label>
                <div class="col-md-9">
                    <p class="form-control-static">${course.category().name()}</p>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-md-3 control-label"><strong>Status:</strong></label>
                <div class="col-md-9">
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
            </div>

            <div class="form-group row">
                <label class="col-md-3 control-label"><strong>Criado em:</strong></label>
                <div class="col-md-9">
                    <p class="form-control-static">${course.createdAt().format(formatter)}</p>
                </div>
            </div>

            <c:if test="${course.inactiveAt() != null}">
                <div class="form-group row">
                    <label class="col-md-3 control-label"><strong>Inativado em:</strong></label>
                    <div class="col-md-9">
                        <p class="form-control-static">${course.inactiveAt().format(formatter)}</p>
                    </div>
                </div>
            </c:if>

            <div class="form-group row">
                <label class="col-md-3 control-label"><strong>Descrição:</strong></label>
                <div class="col-md-9">
                    <p class="well">${course.description()}</p>
                </div>
            </div>

            <a class="btn btn-secondary" href="/admin/courses">Voltar</a>

        </div>

    </section>

</div>

</body>
</html>

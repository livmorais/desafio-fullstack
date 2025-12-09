<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Cursos</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/assets/external-libs/bootstrap/css/bootstrap.min.css">
</head>

<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h1>Cursos</h1>
            <a class="btn btn-secondary" href="/admin/home">Voltar</a>
            <a class="btn btn-info new-button" href="/admin/course/new">Cadastrar novo</a>
        </div>

        <table class="panel-body table table-hover">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Código</th>
                <th>Instrutor</th>
                <th>Categoria</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.name()}</td>
                    <td>${course.code()}</td>
                    <td>${course.instructor().name()}</td>
                    <td>${course.category().name()}</td>

                    <td>
                        <c:choose>
                            <c:when test="${course.status() == 'ACTIVE'}">
                                <span class="label label-success">Ativo</span>
                            </c:when>
                            <c:otherwise>
                                <span class="label label-danger vertical-align-sub">Inativo</span>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <a class="btn btn-primary" href="/admin/course/${course.code()}/details">Detalhes</a>
                        <a class="btn btn-primary" href="/admin/course/${course.id()}/edit">Editar</a>
                        <c:choose>
                            <c:when test="${course.status() == 'ACTIVE'}">
                                <button class="btn btn-warning"
                                        data-toggle="modal"
                                        data-target="#confirmInactive-${course.code()}">
                                    Inativar
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-success"
                                        data-toggle="modal"
                                        data-target="#confirmActive-${course.code()}">
                                    Ativar
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>


<c:forEach items="${courses}" var="course">

    <div class="modal fade" id="confirmInactive-${course.code()}" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header clearfix">
                    <h4 class="modal-title pull-left">Confirmar Inativação</h4>
                    <button type="button" class="close pull-right" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    Tem certeza que deseja inativar o curso <strong>${course.name()}</strong>?
                </div>

                <div class="modal-footer text-right">
                    <form action="/course/${course.code()}/inactive" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-danger">Sim, inativar</button>
                    </form>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="confirmActive-${course.code()}" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header clearfix">
                    <h4 class="modal-title pull-left">Confirmar Ativação</h4>
                    <button type="button" class="close pull-right" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    Tem certeza que deseja ativar o curso <strong>${course.name()}</strong>?
                </div>

                <div class="modal-footer text-right">
                    <form action="/course/${course.code()}/active" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-success">Sim, ativar</button>
                    </form>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                </div>

            </div>
        </div>
    </div>

</c:forEach>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/assets/external-libs/bootstrap/js/bootstrap.min.js"></script>
<script>
function openDetailsModal(code) {
    $.get(`/admin/course/code/${code}/details`, function(data) {
        $("#detail-name").text(data.name);
        $("#detail-code").text(data.code);
        $("#detail-instructor").text(data.instructor.name);
        $("#detail-category").text(data.category.name);
        $("#detail-status").text(data.status === "ACTIVE" ? "Ativo" : "Inativo");
        $("#detail-description").text(data.description || "—");
        $("#detail-createdAt").text(data.createdAt || "—");
        $("#detail-inactiveAt").text(data.inactiveAt || "—");
        $("#courseDetailsModal").modal("show");
    });
}
</script>
</body>
</html>

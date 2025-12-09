<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Categoria</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/assets/external-libs/bootstrap/css/bootstrap.min.css">
</head>

<div class="container">
    <section class="panel panel-primary vertical-space">
        <div class="panel-heading">
            <h1>Editar Categoria</h1>
        </div>

        <form:form modelAttribute="newCategoryForm"
                   cssClass="form-horizontal panel-body"
                   action="/admin/category/${id}/edit"
                   method="post">

            <div class="row form-group">

                <div class="col-md-9">
                    <label for="category-name">Nome:</label>
                    <form:input path="name" id="category-name" cssClass="form-control" required="required"/>
                    <form:errors path="name" cssClass="text-danger" />
                </div>

                <div class="col-md-9">
                    <label for="category-code">Código:</label>
                    <form:input path="code" id="category-code" cssClass="form-control" required="required"/>
                    <form:errors path="code" cssClass="text-danger"/>
                </div>

                <div class="col-md-9">
                    <label for="category-color">Cor:</label>
                    <form:input path="color" id="category-color" cssClass="form-control" required="required"/>
                    <form:errors path="color" cssClass="text-danger" />
                </div>

                <div class="col-md-9">
                    <label for="category-order">Ordem:</label>
                    <form:input path="order" id="category-order" type="number" min="1" cssClass="form-control" required="required"/>
                    <form:errors path="order" cssClass="text-danger" />
                </div>

            </div>

            <input class="btn btn-success submit" type="submit" value="Salvar"/>
            <a class="btn btn-secondary" href="/admin/categories">Cancelar</a>

        </form:form>
    </section>
</div>
</html>

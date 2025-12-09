<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Curso</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/assets/external-libs/bootstrap/css/bootstrap.min.css">
</head>

<div class="container">
    <section class="panel panel-primary vertical-space">
        <div class="panel-heading">
            <h1>Editar Curso</h1>
        </div>

        <form:form modelAttribute="newCourseForm"
                   cssClass="form-horizontal panel-body"
                   action="/admin/course/${newCourseForm.id}/edit"
                   method="post">

            <div class="row form-group">

                <div class="col-md-9">
                    <label for="course-name">Nome do Curso:</label>
                    <form:input path="name" id="course-name" cssClass="form-control" required="required"/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>

                <div class="col-md-9">
                    <label for="course-code">Código:</label>
                    <form:input path="code" id="course-code" cssClass="form-control" required="required"/>
                    <form:errors path="code" cssClass="text-danger"/>
                </div>

                <div class="col-md-9">
                    <label for="course-instructor">Instrutor:</label>
                    <form:select path="instructorId" id="course-instructor" cssClass="form-control" required="required">
                        <form:option value="" label="Selecione o instrutor"/>
                        <form:options items="${instructors}" itemValue="id" itemLabel="name"/>
                        <form:errors path="instructorId" cssClass="text-danger"/>
                    </form:select>
                </div>

                <div class="col-md-9">
                    <label for="course-category">Categoria:</label>
                    <form:select path="categoryId" id="course-category" cssClass="form-control" required="required">
                        <form:option value="" label="Selecione a categoria"/>
                        <form:options items="${categories}" itemValue="id" itemLabel="name"/>
                        <form:errors path="categoryId" cssClass="text-danger"/>
                    </form:select>
                </div>

                <div class="col-md-9">
                    <label for="course-description">Descrição:</label>
                    <form:textarea path="description" id="course-description" cssClass="form-control" rows="4"/>
                    <form:errors path="description" cssClass="text-danger"/>
                </div>

            </div>

            <input class="btn btn-success submit" type="submit" value="Salvar"/>
            <a class="btn btn-secondary" href="/admin/courses">Cancelar</a>

        </form:form>
    </section>
</div>
</html>

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

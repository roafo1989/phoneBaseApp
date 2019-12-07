<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/topjava.common.js" defer></script>
<script type="text/javascript" src="resources/js/topjava.notes.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Notes</h3>
        <div class="card border-dark">
            <div class="card card-body pb-0">

            </div>
            <div class="card-footer text-right">
                <button class="btn btn-primary" onclick="updateFilteredTable()">
                    <span class="fa fa-filter"></span>
                    filter
                </button>
            </div>
        </div>
        <br/>
        <button type="button" class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            add
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>Name</th>
                <th>Number</th>
                <th>Comment</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${notes}" var="note">
                <jsp:useBean id="note" type="ru.home.phoneBaseApp.model.Note"/>
                <tr>
                    <td>${note.name}</td>
                    <td>${note.number}</td>
                    <td>${note.comment}</td>
                    <td><a onclick="makeEditable(${note.id})"><span class="fa fa-pencil"></span></a></td>
                    <td><a onclick="deleteRow(${note.id})"><span class="fa fa-remove"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle">add</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="col-form-label">name</label>
                        <input type="datetime-local" class="form-control" id="name" name="name"
                               placeholder="name">
                    </div>

                    <div class="form-group">
                        <label for="number" class="col-form-label">number</label>
                        <input type="number" class="form-control" id="number" name="number"
                               placeholder="number">
                    </div>

                    <div class="form-group">
                        <label for="comment" class="col-form-label">comment</label>
                        <input type="text" class="form-control" id="comment" name="comment" placeholder="comment">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    cancel
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    save
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
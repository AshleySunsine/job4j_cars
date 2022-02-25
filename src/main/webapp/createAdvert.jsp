<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Продажа машин</title>
</head>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>


<body>
<form action="<%=request.getContextPath()%>/saveAdvart.do" method="post">
    <input type="hidden"  id="userEmail" name="userEmail" value='${userEmail}'>
    <div class="row">
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/registry.jsp">Регистрация</a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">Выйти</a>
        </li>
    </div>
    <hr>
    <div class="col-md-4 mb-3">
        <label for="todoName">Название объявления</label>
        <input required type="text" class="form-control" name="todoName" id="todoName" placeholder="Название объявления">
    </div>

    <div class="col-md-4 mb-3">
        <label for="description">Описание</label>
        <textarea required class="form-control" id="description" name="description" rows="3"></textarea>
    </div>

    <div class="col-md-4 mb-3">
        <label for="inputMark">Марка</label>
        <select id="inputMark" class="form-control">
            <option selected>Марка</option>
            <option value="mark_1">Марка 1</option>
            <option value="mark_2">Марка 2</option>
            <option value="mark_3">Марка 3</option>
            <option value="mark_4">Марка 4</option>
        </select>
    </div>

    <div class="col-md-4 mb-3">
        <label for="inputBody">Кузов</label>
        <select id="inputBody" class="form-control">
            <option selected>Кузов</option>
            <option value="body_1">Кузов 1</option>
            <option value="body_2">Кузов 2</option>
            <option value="body_3">Кузов 3</option>
            <option value="body_4">Кузов 4</option>
        </select>
    </div>

    <div class="col-md-4 mb-3">
        <div class="form-check">
            <button type="submit" class="btn btn-primary">Сохранить объявление</button>
        </div>
    </div>
</form>

</body>
</html>

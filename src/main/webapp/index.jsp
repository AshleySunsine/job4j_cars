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
    <form action="<%=request.getContextPath()%>/createAdvert.jsp" method="post">

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
        <div class="form-check">
            <button id="createButton" onclick="window.location.href = '<%=request.getContextPath()%>/createAdvert.jsp'">Создать объявление</button>

            <script>
               if ("<%=request.getSession().getAttribute("user")%>" != "null") {
                   document.getElementById('createButton').style.display = "block";
                   } else {
                   document.getElementById('createButton').style.display = "None";
                       }
            </script>


        </div>
    </div>
</form>

<hr>

<form>
    <div class="col-md-7">
        <div class="row">
            <div class="card" style="width: 100%" name="Post">
                <div class="card-header">
                    Задачи:
                </div>
                <div class="card-body">
                    <table class="table table-striped table-secondary" id="notDoneTable">
                        <thead>
                        <tr>
                            <th scope="col">Пользователь</th>
                            <th scope="col">Id задачи</th>
                            <th scope="col">Название</th>
                            <th scope="col">Описание</th>
                            <th scope="col">Катигория</th>
                            <th scope="col">Дата создания</th>
                            <th scope="col">Статус</th>
                            <th scope="col">X</th>

                        </tr>
                        </thead>
                        <tbody>

                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <script>

    </script>
    <button type="button" class="btn btn-primary" onclick='ss()'>Обновить</button>

</form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit user</title>
    <link rel="stylesheet" href="ex_add-person.css">
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial;
        }

        .col-1{flex:0 0 auto;width:8.33333333%}
        .col-2{flex:0 0 auto;width:16.66666667%}
        .col-3{flex:0 0 auto;width:25%}
        .col-4{flex:0 0 auto;width:33.33333333%}
        .col-5{flex:0 0 auto;width:41.66666667%}
        .col-6{flex:0 0 auto;width:50%}
        .col-7{flex:0 0 auto;width:58.33333333%}
        .col-8{flex:0 0 auto;width:66.66666667%}
        .col-9{flex:0 0 auto;width:75%}
        .col-10{flex:0 0 auto;width:83.33333333%}
        .col-11{flex:0 0 auto;width:91.66666667%}
        .col-12{flex:0 0 auto;width:100%}

        .table-container{
            margin: 0 auto;
        }
        .table-container table{
            margin-bottom: 10px;
            border-style: solid none;
            border-width: 1px;
            border-color: lightgrey;
        }
        .table-container table caption{
            text-align: left;
            margin-bottom: 10px;
            position: relative;
        }
        .fa-xmark{
            position: absolute;
            top: 0;
            right: 10px;
        }
        table thead th:nth-child(1){
            width: 20%;
        }
        table thead th:nth-child(2){
            width: 80%;
        }
        table tbody tr td:first-child p{
            text-align: right;
            font-size: 15px;
            font-weight: 600;
        }
        table tbody td{
            padding: 5px 10px;
            vertical-align: top;
        }
        table tbody tr:first-child td{
            padding-top: 15px;
        }
        table tbody tr:last-child td{
            padding-bottom: 25px;
        }
        table tbody tr td:nth-child(2) input, table tbody tr td:nth-child(2) textarea, table tbody tr td:nth-child(2) select{
            width: 100%;
            padding: 5px;
            border-style: solid;
            border-radius: 4px;
            border-width: 1px;
            border-color: lightgrey;
            color: slategrey;
        }
        .footer{
            display: flex;
            justify-content: flex-end;
        }
        .footer input{
            padding: 8px;
            margin: 5px;
            border-style: none;
            border-radius: 4px;
            color: white;
            font-size: 10px;
        }
        .footer input:hover{
            cursor: pointer;
        }
        .footer input[type=submit]{
            background-color: aqua;
        }
        .footer input[type=reset]{
            background-color: rgb(139, 23, 23);
        }
        .fa-xmark:hover, .footer input[type=button]:hover{
            cursor: pointer;
        }
        tr td input + p, tr td select + p{
            color: red;
        }
    </style>
</head>
<body>
<div class="col-5 table-container">
    <form method="post">
        <table class="col-12">
            <caption>
                <h2>Edit User</h2>
                <a href="/user"><i class="fa-solid fa-xmark" style="color: #979aa1;"></i></a>
            </caption>
            <thead>
            <th></th>
            <th></th>
            </thead>
            <tbody>
            <tr>
                <td>
                    <p>Full Name</p>
                </td>
                <td>
                    <input value="${requestScope.user.getFullName()}" type="text" name="fullName" placeholder="Full Name">
                    <c:if test="${requestScope.errorsMap.containsKey('userNameInvalid')}">
                        <p>${requestScope.errorsMap.get('userNameInvalid')}</p>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Address</p>
                </td>
                <td>
                    <input value="${requestScope.user.getAddress()}" type="text" name="address" placeholder="Address">
                    <c:if test="${requestScope.errorsMap.containsKey('addressInvalid')}">
                        <p>${requestScope.errorsMap.get('addressInvalid')}</p>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <p>DOB</p>
                </td>
                <td>
                    <input value="${requestScope.user.getDob()}" type="text" name="dob" placeholder="DOB">
                    <c:if test="${requestScope.errorsMap.containsKey('dobInvalid')}">
                        <p>${requestScope.errorsMap.get('dobInvalid')}</p>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Role</p>
                </td>
                <td>
                    <select name="role">
                        <c:forEach var="role" items="${requestScope.roles}">
                            <option
                                    <c:if test="${role.getId() == requestScope.user.getRole().getId()}">
                                        selected
                                    </c:if>
                                    value="${role}">${role}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${requestScope.errorsMap.containsKey('eRoleInvalid')}">
                        <p>${requestScope.errorsMap.get('eRoleInvalid')}</p>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="footer">
            <input type="submit" value="Edit">
            <a href="/user"><input type="button" value="Cancel"></a>
        </div>
    </form>
</div>
</body>
<script src="https://kit.fontawesome.com/ded42364fe.js" crossorigin="anonymous"></script>
</html>

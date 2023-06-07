<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/6/2023
  Time: 2:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Product</title>
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
        .footer input[type=button]{
            padding: 8px;
            margin: 5px;
            border-style: none;
            border-radius: 4px;
            color: white;
            font-size: 10px;
        }
        .footer input[value=Save]{
            background-color: aqua;
        }
        .footer input[value=Cancel]{
            background-color: rgb(139, 23, 23);
        }
        .fa-xmark:hover, .footer input[type=button]:hover{
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="col-5 table-container">
    <form method="post">
        <table class="col-12">
            <caption>
                <h2 style="color: red">Delete This Product ?</h2>
                <i class="fa-solid fa-xmark" style="color: #979aa1;"></i>
            </caption>
            <thead>
            <th></th>
            <th></th>
            </thead>
            <tbody>
            <tr>
                <td>
                    <p>Name</p>
                </td>
                <td>
                    <input readonly type="text" name="name" value="${product.getName()}" placeholder="Name">
                </td>
            </tr>
            <tr>
                <td>
                    <p>Price</p>
                </td>
                <td>
                    <input readonly type="text" name="price" value="${product.getPrice()}" placeholder="Price">
                </td>
            </tr>
            <tr>
                <td>
                    <p>Description</p>
                </td>
                <td>
                    <input readonly type="text" name="description" value="${product.getDescription()}" placeholder="Description">
                </td>
            </tr>
            <tr>
                <td>
                    <p>Provider</p>
                </td>
                <td>
                    <input readonly type="text" name="provider" value="${product.getProviderName()}" placeholder="Provider">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="footer">
            <input type="submit" value="Delete">
            <input type="reset" value="Cancel">
        </div>
    </form>
</div>
</body>
<script src="https://kit.fontawesome.com/ded42364fe.js" crossorigin="anonymous"></script>
</html>

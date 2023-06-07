<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: sans-serif;
            border-radius: 0;
        }

        .col-1 {
            flex: 0 0 auto;
            width: 8.33333333%
        }

        .col-2 {
            flex: 0 0 auto;
            width: 16.66666667%
        }

        .col-3 {
            flex: 0 0 auto;
            width: 25%
        }

        .col-4 {
            flex: 0 0 auto;
            width: 33.33333333%
        }

        .col-5 {
            flex: 0 0 auto;
            width: 41.66666667%
        }

        .col-6 {
            flex: 0 0 auto;
            width: 50%
        }

        .col-7 {
            flex: 0 0 auto;
            width: 58.33333333%
        }

        .col-8 {
            flex: 0 0 auto;
            width: 66.66666667%
        }

        .col-9 {
            flex: 0 0 auto;
            width: 75%
        }

        .col-10 {
            flex: 0 0 auto;
            width: 83.33333333%
        }

        .col-11 {
            flex: 0 0 auto;
            width: 91.66666667%
        }

        .col-12 {
            flex: 0 0 auto;
            width: 100%
        }

        .data-container {
            padding: 15px;
            margin: 0 auto;
        }

        .data-container h2, .data-container h4, .data-container input[value="Add Product"], .data-container p {
            margin-bottom: 20px;
        }

        input[type=button] {
            color: white;
            cursor: pointer;
        }

        i {
            cursor: pointer;
        }

        input[type=button], #show-quantity, .show-search input {
            padding: 5px 5px 5px 20px;
            border-style: none;
            border-radius: 4px;
        }

        .add-button, .edit-button, .delete-button {
            position: relative;
        }

        .add-button .fa-plus, .edit-button .fa-pen, .delete-button .fa-trash-can {
            position: absolute;
            top: 2px;
            left: 2px;
        }

        #show-quantity, .show-search input {
            border: 1px solid lightgrey;
        }

        .data-container input[value="Add Product"], input[value="Search"] {
            background-color: rgb(54, 177, 54);
        }

        .data-container input[value="Edit"] {
            background-color: rgb(70, 38, 225);
        }

        .data-container input[value="Delete"] {
            background-color: rgb(189, 32, 53);
        }

        .data-container p {
            font-size: 13px
        }

        .data-container p > select {
            padding: 5px;
            font-size: 10px;
        }

        .show-search {
            display: flex;
            justify-content: space-between;
        }

        .data-table {
            width: 100%;
            text-align: left;
            margin-bottom: 5px;
            border-collapse: collapse;
        }

        .data-table td, .data-table th {
            border: 1px solid lightgrey;
            font-size: 15px;
        }

        .data-table th {
            padding: 5px;
        }

        .data-table tbody tr td {
            padding: 5px 5px 15px 5px;
        }

        .data-table tbody tr td:last-child {
            padding-bottom: 5px;
        }

        .data-table tbody tr:nth-child(odd) {
            background-color: whitesmoke;
        }

        .data-table thead th {
            position: relative;
        }

        .data-table thead th i {
            position: absolute;
            right: 10px;
        }

        .footer-data {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .footer-data p {
            margin: 0;
        }

        .footer-data .turn-pages {
            display: flex;
            justify-content: flex-end;
        }

        .footer-data .turn-pages p {
            width: 20px;
            padding: 5px;
            background-color: rgb(70, 38, 225);
        }

        .footer-data input[type=button] {
            color: slategrey;
            border: 1px solid lightgrey;
            padding-left: 5px;
            background-color: white;
            border-radius: 0;
        }

        .footer-data input[value=Previous] {
            border-top-left-radius: 4px;
            border-bottom-left-radius: 4px;
        }

        .footer-data input[value=Next] {
            border-top-right-radius: 4px;
            border-bottom-right-radius: 4px;
        }
    </style>
</head>
<body>
<div class="data-container col-7">
    <h2>Ajax CRUD with Bootstrap modals and Datatables</h2>
    <h4>Product List</h4>

    <span class="add-button">
      <a href="?action=create">
            <i class="fa-solid fa-plus" style="color: #ffffff;"></i>
            <input type="button" value="Add Product">
          </a>
</span>
    <div class="show-search col-12">
        <p>Show
            <select name="show-quantity" id="show-quantity">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option selected value="10">10</option>
            </select>
            entries
        </p>
        <form action="?action=search" method="post">
            <p>
                <input name="search-key" id="search-box" value="" type="text" placeholder="Searching...">
                <input type="submit" value="Search">
            </p>
        </form>
    </div>

    <table class="data-table">
        <thead>
        <tr>
            <th>Name
                <i class="fa-solid fa-arrow-down-short-wide" style="color: #808080;"></i>
            </th>
            <th>Price
                <i class="fa-solid fa-sort" style="color: #808080;"></i>
            </th>
            <th>Description
                <i class="fa-solid fa-sort" style="color: #808080;"></i>
            </th>
            <th>Provider
                <i class="fa-solid fa-sort" style="color: #808080;"></i>
            </th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    <a href="?action=view&id=${product.getId()}">${product.getName()}</a>
                </td>
                <td>${product.getPrice()}</td>
                <td>${product.getDescription()}</td>
                <td>${product.getProviderName()}</td>
                <td>
                    <a href="?action=edit&id=${product.getId()}">
                        <span class="edit-button">
                            <i class="fa-solid fa-pen" style="color: #ffffff;"></i>
                            <input type="button" value="Edit">
                        </span>
                    </a>

                    <a href="?action=delete&id=${product.getId()}">
                        <span class="delete-button">
                            <i class="fa-solid fa-trash-can" style="color: #ffffff;"></i>
                            <input type="button" value="Delete">
                        </span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Provider</th>
            <th>Action</th>
        </tr>
        </tbody>
    </table>
    <div class="footer-data col-12">
        <p class="col-9">Showing 1 to 5 of 5 entries</p>
        <div class="turn-pages col-3">
            <input type="button" value="Previous">
            <p>1</p>
            <input type="button" value="Next">
        </div>
    </div>
</div>
</body>
<script src="https://kit.fontawesome.com/ded42364fe.js" crossorigin="anonymous"></script>
</html>
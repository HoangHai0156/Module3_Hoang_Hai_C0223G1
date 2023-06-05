<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ProductDiscountCalculator</title>
</head>
<body>
<form action="/product-discount-calculator" method="post">
  <label>
    Product Description
    <input type="text" name="description">
  </label>
  <label>
    List Price
    <input type="text" name="listprice">
  </label>
  <label>
    Discount Percent
    <input type="text" name="discountpercent">
  </label>
  <button type="submit">Get result</button>
</form>
</body>
</html>
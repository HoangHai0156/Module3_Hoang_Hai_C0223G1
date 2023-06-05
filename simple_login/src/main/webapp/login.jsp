<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <script src="https://kit.fontawesome.com/ded42364fe.js" crossorigin="anonymous"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial;
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

        .container {
            margin: 0 auto;
            background-color: peachpuff;
            border-radius: 8px;
        }

        .login-table {
            margin: 0 auto;
            padding: 10px;
            width: 100%;
        }

        .login-table td {
            padding: 10px;
        }

        .user-input {
            position: relative;
        }

        .login-table .icon {
            position: absolute;
            top: 17px;
            left: 17px;
        }

        .login-table .fa-eye-slash {
            position: absolute;
            top: 17px;
            right: 25px;
        }

        .user-input input {
            height: 20px;
            width: 100%;
            padding: 15px 3px;
            padding-left: 30px;
            border-radius: 4px;
            border: none;
        }

        #remember {
            vertical-align: inherit;
        }

        #remember + label {
            text-align: left;
            font-size: 13px;
            color: darkgray;
        }

        .forgotpassword {
            text-align: right;
            font-size: 13px;
        }

        a {
            text-decoration: none;
        }

        .forgotpassword a {
            font-weight: 700;
        }

        .login-button {
            text-align: center;
        }

        .login-button button {
            width: 100%;
            padding: 5px;
            border-radius: 10px;
            border: none;
            background-color: royalblue;
            color: white;
        }

        table + p {
            padding: 0 10px 10px 10px;
            text-align: center;
            font-size: 15px;
            color: lightslategray;
        }

        .container fieldset {
            border-style: solid none none none;
            border-color: lightslategray;
            width: 90%;
            margin: 0 auto;
        }

        .container fieldset legend {
            text-align: center;
            font-size: 15px;
        }

        .connect-icons {
            display: flex;
            justify-content: center;
        }

        .connect-icon {
            font-size: 30px;
            margin: 10px;
        }

        input[type=checkbox]:hover, .login-table .fa-eye-slash:hover, #remember + label:hover, button:hover, .connect-icon:hover {
            cursor: pointer;
        }

        .fa-google {
            background: conic-gradient(from -45deg, #ea4335 110deg, #4285f4 90deg 180deg, #34a853 180deg 270deg, #fbbc05 270deg) 73% 55%/150% 150% no-repeat;
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
            -webkit-text-fill-color: transparent;
        }

    </style>
</head>
<body>
<div class="container col-3">
    <form action="/login" method="post">
        <table class="login-table">
            <thead>
            <th colspan="2">
                <h3>Login</h3>
            </th>
            </thead>
            <tbody>
            <tr>
                <td class="user-input" colspan="2">
                    <i class="fa-regular fa-user icon" style="color: #005eff;"></i>
                    <input type="text" name="username" placeholder="Username or Email">
                </td>
            </tr>
            <tr>
                <td class="user-input" colspan="2">
                    <i class="fa-solid fa-lock icon" style="color: #005eff;"></i>
                    <input type="password" name="password" placeholder="Enter your Password">
                    <i class="fa-solid fa-eye-slash" style="color: #868c98;"></i>
                </td>
            </tr>
            <tr>
                <td>
                    <input id="remember" type="checkbox">
                    <label for="remember">Remember me</label>
                </td>
                <td class="forgotpassword">
                    <a href="#">Forgot password?</a>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="login-button">
                    <button type="submit" value="Sign in">Login</button>
                </td>
            </tr>
            </tbody>
        </table>
        <p>
            Don't have an account?
            <a href="#">Sign up</a>
        </p>
        <fieldset>
            <legend>or connect with</legend>
        </fieldset>
        <div class="connect-icons">
            <i class="fa-brands fa-facebook connect-icon" style="color: #005eff;"></i>
            <i class="fa-brands fa-google connect-icon"></i>
            <i class="fa-brands fa-github connect-icon" style="color: #000000;"></i>
        </div>
    </form>
</div>
</body>
</html>
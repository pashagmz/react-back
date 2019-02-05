<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pasha Gomza</title>
</head>
<body>
<div class="logo"></div>

<form class="form login-page" action="/login" method="post">
    <div class="form-header">
        Log In
    </div>
    <div class="form-description">
        Enter your credentials below
    </div>
    <div class="fields">
        <div class="field">
            <div>
                <label>
                    Email
                    <input type="email" name="username" required="" autofocus>
                </label>
            </div>
        </div>
        <div class="field">
            <div>
                <label>
                    Password
                    <input type="password" name="password" required="">
                </label>
            </div>
        </div>
        <button id="loginButton" type="submit">
            <span class="btn-text">
            Log In
            </span>
        </button>


    </div>
<#if error??>
    <script>
        alert("Invalid email and password combination");
    </script>
</#if>
</form>


</body>
</html>
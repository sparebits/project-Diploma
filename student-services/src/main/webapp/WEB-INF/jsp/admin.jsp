<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>

<body>
    <!-- Create navigation for the website within a <header> tag -->
     <header>
       <nav class="navbar navbar-light bg-light">
       <a class="navbar-brand" href="#">
         <img class="img-fluid d-inline-block align-middle" width="50" height="50" src="img/motherboard.svg">
         <span class="ml-1">BZM</span>
       </a> 
         <ul class="navbar-nav mr-auto flex-row ml-3">
          <li class="nav-item active">
            <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link ml-3" href="/admin">Admin</a>
          </li>
          <li class="nav-item">
            <a class="nav-link ml-3" href="#">Contacts</a>
          </li>
        </ul>
        <div class="mr-5">
          <form class="text-center" action="placeholder.jsp" method="post">
            <input type="text" name="username" placeholder="Email">
            <input type="password" name="password" placeholder="Password">
            <button class="btn btn-primary" type="submit" name="login-submit">Login</button>
          </form>
        </div> 
      </nav>
     </header>
     <main class="container">
      <div class="row">
        <div class="col text-center mt-2">
            ${companiesInfo}
        </div>
      </div>
     </main>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    
</body>

</html>
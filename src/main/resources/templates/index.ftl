<!DOCTYPE html>

<html lang="en" ng-app="socialNetwork">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>
        <!-- NAVIGATION -->
        <nav class="navbar navbar-inverse" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/">Social Network</a>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li ui-sref-active="active"><a ui-sref="register">Register</a></li>
                    <li ui-sref-active="active"><a ui-sref="login">Login</a></li>
                    <li><a href="/logout">Logout</a></li>
                </ul>
            </div>
        </nav>
        <!-- MAIN CONTENT -->
        <div class="container">
            <div ui-view></div>
        </div>
        <!-- SCRIPTS -->
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/SecurityController.js"></script>
        <script src="js/app/UserService.js"></script>
        <script src="js/app/UserController.js"></script>
    </body>
</html>
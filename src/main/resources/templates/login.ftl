<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Log In</span></div>
        <div class="panel-body">
            <form method="POST" action="/login">
                <div class="form-group">
                    <input name="username" class="form-control" placeholder="Username" autofocus="true"/>
                    <input name="password" type="password" class="form-control" placeholder="Password"/>
                    <br/>
                    <div class="col-md-2 col-md-offset-5">
                        <button class="btn btn-lg btn-primary">Log In</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Log In</span></div>
        <div class="panel-body">
            <div class="alert alert-danger" ng-show="error">
                There was a problem logging in. Please try again.
            </div>
            <form ng-submit="ctrl.login()">
                <input class="form-control" name="username" placeholder="Username"
                       ng-model="ctrl.credentials.username"/>
                <input type="password" class="form-control" name="password" placeholder="Password"
                           ng-model="ctrl.credentials.password"/>
                <br/>
                <div class="col-md-2 col-md-offset-5">
                    <button class="btn btn-lg btn-primary">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
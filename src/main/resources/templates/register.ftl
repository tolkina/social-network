<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Register</span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">
                    {{ctrl.successMessage}}
                </div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">
                    {{ctrl.errorMessage}}
                </div>
                <form ng-submit="ctrl.submit()" name="form" class="form-horizontal">
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2" for="email">Email:</label>
                            <div class="col-md-12">
                                <input type="email" ng-model="ctrl.user.email" id="email" class="form-control input-sm"
                                       placeholder="Enter email"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2" for="firstName">First name:</label>
                            <div class="col-md-12">
                                <input ng-model="ctrl.user.firstName" id="firstName" class="form-control input-sm"
                                       placeholder="Enter first name"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2" for="lastName">Last name:</label>
                            <div class="col-md-12">
                                <input ng-model="ctrl.user.lastName" id="salary" class="form-control input-sm"
                                       placeholder="Enter last name"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2" for="password">Password:</label>
                            <div class="col-md-12">
                                <input type="password" ng-model="ctrl.user.password" id="password"
                                       class="form-control input-sm" placeholder="Enter password" required
                                       ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-actions col-xs-4 col-xs-offset-4">
                            <input type="submit" value="Register" class="btn btn-lg btn-primary"
                                   ng-disabled="form.$invalid || form.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-lg btn-warning">Reset Form
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
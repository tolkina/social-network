<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Creating a new User</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="form" class="form-horizontal">
                    <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2" for="username">Username:</label>
	                        <div class="col-md-10">
	                            <input type="text" ng-model="ctrl.user.username" id="username" class="username form-control input-sm" placeholder="Enter username" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2" for="firstName">First name:</label>
	                        <div class="col-md-10">
	                            <input type="text" ng-model="ctrl.user.firstName" id="firstName" class="form-control input-sm" placeholder="Enter first name" required/>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2" for="lastName">Last name:</label>
	                        <div class="col-md-10">
	                            <input type="text" ng-model="ctrl.user.lastName" id="salary" class="form-control input-sm" placeholder="Enter last name" required/>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-actions float-right">
	                        <input type="submit"  value="Add" class="btn btn-primary" ng-disabled="form.$invalid || form.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Users</span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>Username</th>
		                <th>First name</th>
		                <th>Second name</th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="user in ctrl.getAllUsers()">
		                <td>{{user.username}}</td>
		                <td>{{user.firstName}}</td>
		                <td>{{user.lastName}}</td>
		                <td>
							<button type="button" ng-click="ctrl.removeUser(user.username)"
                                    class="btn btn-danger btn-sm custom-width">Remove</button>
						</td>
		            </tr>
		            </tbody>
		        </table>
			</div>
		</div>
    </div>
</div>
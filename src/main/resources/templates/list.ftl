<div class="generic-container">
    <div class="panel panel-default">
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>#</th>
		                <th>Name</th>
		                <th>Email</th>
		                <th>Birthday</th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="user in ctrl.getAllUsers()">
						<td><a href="/#/profile/{{user.id}}">{{user.id}}</a></td>
		                <td>{{user.firstName}} {{user.lastName}}</td>
		                <td>{{user.email}}</td>
                        <td>{{user.birthday}}</td>
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
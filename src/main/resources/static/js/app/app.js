var app = angular.module('socialNetwork', ['ui.router', 'ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/',
    USER_SERVICE_API: 'http://localhost:8080/api/user/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller: 'UserController',
                controllerAs: 'ctrl',
                resolve: {
                    users: function ($q, UserService) {
                        console.log('Load all users');
                        var deferred = $q.defer();
                        UserService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            })
            .state('profile', {
                url: '/profile/:id',
                templateUrl: 'partials/profile',
                controller: function ($scope, $stateParams, UserService) {
                    $scope.user = UserService.getUser($stateParams.id);
                }
            })
            .state('register', {
                url: '/register',
                templateUrl: 'partials/register',
                controller: 'UserController',
                controllerAs: 'ctrl'
            })
            .state('login', {
                url: '/login',
                templateUrl: 'partials/login',
                controller: 'SecurityController',
                controllerAs: 'ctrl'
            });
    }]
);
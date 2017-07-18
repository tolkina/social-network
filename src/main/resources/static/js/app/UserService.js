'use strict';

angular.module('socialNetwork').factory('UserService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllUsers: loadAllUsers,
                getAllUsers: getAllUsers,
                getUser: getUser,
                createUser: createUser,
                updateUser: updateUser,
                removeUser: removeUser
            };

            return factory;

            function loadAllUsers() {
                console.log('Fetching all users');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all users');
                            $localStorage.users = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading users');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllUsers() {
                return $localStorage.users;
            }

            function getUser(username) {
                console.log('Fetching User with username :' + username);
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + username)
                    .then(
                        function (response) {
                            console.log('Fetched successfully User with username: ' + username);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading User with username: ' + username);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createUser(user) {
                console.log('Creating User');
                var deferred = $q.defer();
                $http.post(urls.USER_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while creating User: ' + errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateUser(user, username) {
                console.log('Updating User with id ' + username);
                var deferred = $q.defer();
                $http.put(urls.USER_SERVICE_API + username, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating User with username: ' + username);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeUser(username) {
                console.log('Removing User with username ' + username);
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + username)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing User with username: ' + username);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
        }
    ]
);
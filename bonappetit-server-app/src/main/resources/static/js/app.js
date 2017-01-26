var bonappetitServerApp = angular.module('bonappetitServerApp', ['ngMessages', 'ngResource']);

bonappetitServerApp.controller('staffMembersController', ['$scope', '$log', '$resource',
    function ($scope, $log, $resource) {
        $log.info('Fetching staff members');
        $log.info('$resource', $resource);

        var actions = {
            list: {
                method: 'GET',
                isArray: true,
                withCredentials: true
            }
        };
        var StaffMembersResource = $resource('http://localhost:8080/api/staffMembers', null, actions);
        $log.info('$scope.staffMembers before request', $scope.staffMembers);
        $scope.staffMembers = StaffMembersResource.list();
        $log.info('$scope.staffMembers after request', $scope.staffMembers);
        $log.info('$scope', $scope);

        //$scope.staffMembers = [
        //    {
        //        firstName: 'Hans',
        //        lastName: "Wurst"
        //    },
        //    {
        //        firstName: "John",
        //        lastName: "Cena"
        //    }
        //];

    }]);

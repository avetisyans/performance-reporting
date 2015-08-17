(function() {
    
    var CompareController = function ($scope, $routeParams, $timeout, perfFactory, sharingFactory, environments, $cacheFactory) {
/*        function init() {
            perfFactory.environments()
                .success(function(envs) {
                    sharingFactory.environments = envs;
                    $scope.environments = sharingFactory.environments;
                })
                .error(function(data, status, headers, config) {
                    console.log('Error while getting Environments');
                    console.log(data.error + ' ' + status); 
                });
        }
        
        init();*/
        $scope.displayDelete = function() {
            console.log('displaying mark...');
            $scope.mark = true;
        }
        
        $scope.environments = environments.data;
        $scope.numberOfRecentRuns = 3;
        $scope.title = "CompareControllerTitle";
        
        $scope.setEvironments = function(numberOfRecentRuns) {
            
            var httpCache = $cacheFactory.get('$http');
            httpCache.remove('http://10.152.28.75:8080/performance-report/rest/environments');
            console.log('removing cache ...');
            
                perfFactory.environments(numberOfRecentRuns)
                .success(function(_environments) {
                $scope.environments = _environments;
                    console.log('env has been set: ',$scope.environments);
                    $timeout(function() {
                        console.log('calling timeout');
                        
                        $('tr.testCaseTitle').each(function (el, val) {
                            if ($(val).hasClass("collapsed")) {
                                val.click();
                            }
                            
                            if (!$(val).hasClass("collapsed")) {
                                val.click();
                            }
                        });
                    });
                    

/*                    $scope.environments.forEach(function(envir) {
                        console.log('envName', envName);
                        if (envName === envir.name) {
                            console.log('equalssssssssss');
                            console.log('envir', envir);
                            $scope.env = envir;
                            return;
                        }
                    });
                    
                    allSuites = [];
                    tabArray = [];
                    
                    var i = 0;
                    $scope.env.testSuites.forEach(function (testSuite) {
                        if (i === 0) {
                            tabArray.push({
                            title: testSuite.name,
                            content: testSuite.testCaseStats,
                            active: true
                            });
                        } else {
                            tabArray.push({
                            title: testSuite.name,
                            content: testSuite.testCaseStats
                            });
                        }
                        ++i;
                        allSuites.push.apply(allSuites, testSuite.testCaseStats);
                    });
                    
                    tabArray.push({
                        title: 'All Suites*',
                        content: allSuites
                    });
                    
                    $scope.tabs = tabArray;*/
                    
                })
                .error(function(data, status, headers, config) {
                    console.log('Error while getting Runs');
                    console.log(data.error + ' ' + status);
                });
            
        }
        
    };
    
    CompareController.$inject = ['$scope', '$routeParams', '$timeout', 'perfFactory', 'sharingFactory', 'environments', '$cacheFactory'];
    
    angular.module('perf_reportingApp').controller('CompareController', CompareController);
    
    
}());





/*        function init() {
            perfFactory.runs()
                .success(function(runs) {
                $scope.runs = runs;
                })
                .error(function(data, status, headers, config) {
                    console.log('Error while getting Runs');
                    console.log(data.error + ' ' + status);
                });
        }*/
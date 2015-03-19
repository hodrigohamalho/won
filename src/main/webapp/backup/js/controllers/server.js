function ServerCtrl($scope, $window, $http){

    var JBOSSURL = contextPath + "/rest/jboss/";
    var DCURL = contextPath + "/rest/dc/";

    var
        $ = jQuery,
        ng = $scope,
        aj = $http,
        wi = $window
        ;


    var displayResume = function(){

        $http.get(JBOSSURL + "resume-jboss-info").success(function(data){
            ng.tree = data;
            ng.template = { url: contextPath+'/jsp/partials/_resume.jsp' };
        });
    };

    var updateSideMenu = function(){
        $(".sidebar .nav li").removeClass("active");
    };

    var checkIfAnyDCisRegistered = function(){
        $http.get(DCURL + "is-any-registered").success(function(data){
            if (data == false){
                $("#alert-holder").load(contextPath + "/jsp/dc/partials/_dc_alert_first_access.jsp");
            }else{
                displayResume();
            }
        });
    };

    updateSideMenu();
    checkIfAnyDCisRegistered();

}
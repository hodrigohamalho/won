Index = {
    checkIfAnyDCisRegistered: function(){
        $.ajax({
            url:"rest/dc/is-any-registered",
            success: function(data){
                if (data == false){
                    $("#alert-holder").load("jsp/dc/partials/_dc_alert_first_access.jsp");
                }
            }
        })
    }
};

$(function(){
   Index.checkIfAnyDCisRegistered();
});



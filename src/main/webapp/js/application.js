// This javascript is common to all pages

var loadIcon = "initializing...";
var AlertType = {
  SUCCESS: "success",
  INFO: "info",
  WARN: "warn",
  DANGER: "danger"
};

WON = {

    installSerializeObject: function(){
        $.fn.serializeObject = function()
        {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name] !== undefined) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
    },

    stringToBoolean: function(string){
        switch(string.toLowerCase()) {
            case "false": case "no": case "0": case "": return false;
            default: return true;
        }
    },

    message: function(type, message, description){
        var source = $("#alerts-template").html();
        var template = Handlebars.compile(source);

        var divClass = "";
        var btnIcon = "";

        if (type == AlertType.SUCCESS) {
            divClass = "alert-success";
            btnIcon = "glyphicon-ok";
        }else if (type == AlertType.INFO){
            divClass = "alert-info";
            btnIcon = "glyphicon-info-sign";
        }else if (type == AlertType.WARN){
            divClass = "alert-warn";
            btnIcon = "glyphicon-record";
        }else if (type == AlertType.DANGER){
            divClass = "alert-danger";
            btnIcon = "glyphicon-hand-right";
        }

        var msgBuilder = {
            title: message,
            description: description,
            divClass: divClass,
            icon: btnIcon
        }

        $("#alert-holder").html(template(msgBuilder));
    },

    scrollToElement: function (elementId) {
        $('html, body').animate({
            scrollTop: $(elementId).offset().top
        }, 2000)
    }

};

$(function(){

    loadIcon = "<img src='"+contextPath+"/gfx/load.gif' alt='loading...' />";
    WON.installSerializeObject();

});
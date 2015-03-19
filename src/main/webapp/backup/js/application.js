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

        // TODO to_angular_way
        $("#alert-holder").load(contextPath+"/jsp/partials/_alerts.jsp",function(){
            var alert = $("#alert");
            alert.addClass(msgBuilder.divClass);
            $(alert.find(".icon")).addClass(msgBuilder.icon);
            $(alert.find("#alert-title")).html(msgBuilder.title);
            if (msgBuilder.description == null){
                $(alert.find("#alert-body")).remove();
            }else{
                $(alert.find("#alert-description")).html(msgBuilder.description);
            }
        });
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
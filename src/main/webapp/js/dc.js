// If you are new in project, start reading the last section of this file
// it'll help you for understand the main functions used in this file
var searchableSingleton = 0;
var DCURL = contextPath + "/rest/dc/";
var form = $("#form-dc");

DC = {

    updateSideMenu: function(){
        $("#dc-nav").addClass("active");
    },

    installSearch: function(){
        $("#dc-table").searchable({
            searchField   : '#dc-search',
            striped       : true,
            oddRow        : { 'background-color': '#f5f5f5' },
            evenRow       : { 'background-color': '#fff' },
            hide          : function( elem ) {
                elem.fadeOut(50);
            },
            show          : function( elem ) {
                elem.fadeIn(50);
            },
            searchType    : 'fuzzy'
        });
    },

    listDC: function(){
        var dcTableBody = $("#dc-table-body");

        $.ajax({
            url: DCURL,
            type: "GET",
            dataType: "json",
            beforeSend: function(){
                var text = "<tr><td colspan='4'>"+loadIcon+"</td></tr>";
                dcTableBody.html(text);
            },
            success: function(domainControllers){
                var dcs = {
                    "domainControllers" : domainControllers
                };
                var source = $("#dc-table-body-template").html();
                var template = Handlebars.compile(source);
                dcTableBody.html(template(dcs));
            },
            complete: function(){
                if (searchableSingleton == 0){
                    DC.installSearch();
                    searchableSingleton ++;
                }
                DC.installCrudActions();
            }
        })
    },

    installForm: function(){
        $("[placeholder]").defaultValue();

        form.unbind();
        form.find("#id").val(-1);
        form.submit(function(e){
            e.preventDefault();

            $.ajax({
                url: DCURL,
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(form.serializeObject()),
                success: function(){
                    DC._resetForm();
                    DC.listDC();
                }
            });
        });

    },

    installTestConnectionAction: function () {

        $(".btn-test-connection").click(function(e){
            e.preventDefault();
            var self = $(this);

            var currentRow = $(this).parents("tr");
            var id = currentRow.find("div.id").html();
            var span = "";

            $.ajax({
                url: DCURL + "test-connection/"+ id,
                type: "GET",
                beforeSend: function(){
                    var spinButton = $(loadIcon).attr("width", "12px");
                    spinButton = $(loadIcon).attr("height", "14px");
                    span = self.html();
                    self.html(spinButton);
                },
                success: function(isConnectionWorking){
                    isConnectionWorking = WON.stringToBoolean(isConnectionWorking);
                    if (isConnectionWorking){
                       WON.message(AlertType.SUCCESS, "Connection working!", null);
                    }else{
                       WON.message(AlertType.DANGER, "Connection not established :/ ", null);
                    }
                },
                error: function(){
                    console.log("funfou não");
                },
                complete: function(){
                    self.html(span);
                }
            });
        });

    },

    installCrudActions: function(){

        DC.installEditAction();
        DC.installRemoveAction();
        DC.installTestConnectionAction();

    },

    installEditAction: function(){
        var self = this;

        $(".btn-edit").click(function(e){
            e.preventDefault();

            var currentRow = $(this).parents("tr");
            // TODO It should made a request to retrieve object from database, or not? :)
            self._fillForm(currentRow);

            WON.scrollToElement("#form-dc");

            form.unbind();
            form.submit(function(f){
                f.preventDefault();
                console.log(JSON.stringify(form.serializeObject()));

                $.ajax({
                    url: DCURL,
                    type: "PUT",
                    data: JSON.stringify(form.serializeObject()),
                    contentType: "application/json",
                    success: function(){
                        DC._resetForm();
                        DC.listDC();
                    },
                    complete: function(){
                        DC._resetForm();
                        DC.listDC();
                        DC.installForm();
                    }
                });
            });
        });
    },

    installRemoveAction: function(){
        $(".btn-remove").click(function(e){
            e.preventDefault();

            // TODO confirm before remove. http://qtip2.com/
            if (confirm("Are you sure?")){
                var currentRow = $(this).parents("tr");
                var id = currentRow.find("div.id").html();

                $.ajax({
                    url: DCURL + id,
                    type: "DELETE",
                    success: function(){
                        $(currentRow).hide("slow", function(){
                            $(currentRow).remove();
                        });
                    }
                });
            }
        });
    },

    _resetForm: function(){
        form[0].reset();
    },

    _fillForm: function(row){
        var id = row.find("div.id").html();
        form.find("#id").attr("value", id);

        row.find("td").each(function(){
            var name = $(this).data("name");
            var value = $(this).data("value");

            if (name != null){
                form.find("#"+name).val(value.toString());
            }
        });
    }

}

$(function(){

    DC.updateSideMenu();
    DC.listDC();
    DC.installForm();

});



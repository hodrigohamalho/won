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
            before: function(){
                var text = "<tr><td colspan='4'><img src='"+contextPath+"/gfx/load.gif' alt='loading...' /></td></tr>";
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

    installCrudActions: function(){

        DC.installEditAction();
        DC.installRemoveAction();

    },

    installEditAction: function(){
        var self = this;

        $(".btn-edit").click(function(e){
            e.preventDefault();

            var currentRow = $(this).parents("tr");
            self._fillForm(currentRow);

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



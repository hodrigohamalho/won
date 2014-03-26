<script id="alerts-template" type="text/x-handlebars-template">

    <div class="alert {{divClass}}">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <span class="glyphicon {{icon}}"></span> <strong>{{title}}</strong>
        {{#if description}}
            <hr class="message-inner-separator">
            <p>{{description}}</p>
        {{/if}}
    </div>

</script>
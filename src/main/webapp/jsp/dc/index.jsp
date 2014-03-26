<html>
<head>
    <title>Jboss Monitor - Domain Controller</title>
</head>
<body>

<h1 class="page-header">Domain Controller</h1>

<div class="row">
    <div class="col-lg-4 col-lg-offset-4">
        <input type="search" id="dc-search" value="" class="form-control" placeholder="Filter...">
    </div>
</div>
<div id="dc-table" class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Host</th>
                <th>Port</th>
                <th>Username</th>
                <th>Collecting Metrics?</th>
                <th>&nbsp;</th>
            </tr>
        </thead>
        <tbody id="dc-table-body"></tbody>
    </table>
</div>

<div class="col-md-6 col-md-offset-2">
    <form id="form-dc">
        <input type="hidden" name="id" id="id" value="-1"/>

        <label for="host">Host</label>
        <input type="text" name="host" class="form-control" id="host" placeholder="127.0.0.1"/>

        <label for="port">Port</label>
        <input type="text" name="port" class="form-control" id="port" placeholder="9990"/>

        <label for="username">Username</label>
        <input type="text" name="username" class="form-control" id="username" placeholder="admin"/>

        <label for="password">Password</label>
        <input type="password" name="password" class="form-control" id="password" />

        <label for="active">Collecting Metrics?</label>
        <select name="active" class="form-control" id="active">
            <option value="true">YES!</option>
            <option value="false">NO.</option>
        </select>

        <button type="submit" class="btn btn-primary pull-right" id="btn-register-dc">Save it!</button>
    </form>
</div>

<script id="dc-table-body-template" type="text/x-handlebars-template">
    {{#each domainControllers}}
    <tr>
        <td data-name="host" data-value="{{host}}"><div class="id hidden">{{id}}</div>{{host}}</td>
        <td data-name="port" data-value="{{port}}">{{port}}</td>
        <td data-name="username" data-value="{{username}}">{{username}}</td>

        {{#if active}}
            <td data-name="active" data-value="true"><button class="btn btn-info btn-sm">Collecting Metrics!</button></td>
        {{else}}
            <td data-name="active" data-value="false"><button class="btn btn-danger btn-sm">Not collecting</button></td>
        {{/if}}
        <td>
            <button type="button" class="btn btn-default btn-sm btn-edit" title="Edit">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
            <button type="button" class="btn btn-default btn-sm btn-remove" title="Remove it!">
                <span class="glyphicon glyphicon-remove"></span>
            </button>
            <button type="button" class="btn btn-default btn-sm btn-test-connection"
                    title="Test connection with Domain Controller">
                <span class="glyphicon glyphicon-transfer"></span>
            </button>
        </td>
        <td class="hidden" data-name="password" data-value="{{password}}"></td>
    </tr>
    {{/each}}
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/dc.js"></script>

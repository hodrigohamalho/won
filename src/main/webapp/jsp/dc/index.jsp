<html>
<head>
    <title>Jboss Monitor - Domain Controller</title>
</head>
<body>

<h1 class="page-header">Domain Controller</h1>

<div id="domain-controller" ng-controller="DomainCtrl">

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
            <tbody id="dc-table-body">
                <tr ng-repeat="dc in dcs" ng-switch on="active" >
                    <td>{{dc.host}}</td>
                    <td>{{dc.port}}</td>
                    <td>{{dc.username}}</td>
                    <td ng-if="dc.active"><button class="btn btn-info btn-sm">Collecting Metrics!</button></td>
                    <td ng-if="dc.active == false"><button class="btn btn-danger btn-sm">Not collecting</button></td>
                    <td>
                        <button type="button" ng-click="edit(dc)" class="btn btn-default btn-sm btn-edit" title="Edit">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </button>
                        <button type="button" ng-click="remove(dc)" class="btn btn-default btn-sm btn-remove" title="Remove it!">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                        <button type="button" class="btn btn-default btn-sm btn-test-connection"
                                title="Test connection with Domain Controller">
                            <span class="glyphicon glyphicon-transfer"></span>
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="col-md-6 col-md-offset-2">
        <form id="form-dc">
            <input type="hidden" ng-model="dc.id" value="-1"/>

            <label for="host">Host</label>
            <input type="text" id="host" ng-model="dc.host" class="form-control" placeholder="127.0.0.1"/>

            <label for="port">Port</label>
            <input type="text" id="port" ng-model="dc.port" class="form-control" placeholder="9990"/>

            <label for="username">Username</label>
            <input type="text" ng-model="dc.username" class="form-control" id="username" placeholder="admin"/>

            <label for="password">Password</label>
            <input type="password" ng-model="dc.password" class="form-control" id="password" />

            <label for="active">Collecting Metrics?</label>
            <select ng-model="dc.active" class="form-control" id="active">
                <option value="true">YES!</option>
                <option value="false">NO.</option>
            </select>

            <button type="submit" class="btn btn-primary pull-right" ng-click="save()">Save it!</button>
        </form>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/dc.js"></script>

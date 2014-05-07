<head>
    <title>Jboss Monitor - Dashboard</title>
</head>
<body>
    <h1 class="page-header">Dashboard</h1>
    <div id="alert-holder"></div>

    <div id="domain-controller" ng-controller="IndexCtrl">

            <div class="dcs" ng-repeat="(dc, val) in tree">

                <div class="dc panel panel-default">
                    <div class="panel-heading">{{dc}}</div>
                    <div ng-repeat="(host, val) in val">

                        <div class="hosts">
                            <div class="panel-body">
                                <div class="host" ng-repeat="(host, val) in val">
                                    <div class="col-md-3">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">{{host}}</div>
                                            <div class="panel-body">
                                                <ul class="nav navbar-nav servers">
                                                    <li ng-repeat="(server, val) in val['server-config']" class="dropdown">
                                                        <div class="btn-group">
                                                            <button type="button" class="btn dropdown-toggle server {{val['status']}}" data-toggle="dropdown">
                                                                {{server}} <span class="caret"></span>
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li><a href="#">Server Settings <span class="glyphicon glyphicon-cog pull-right"></span></a></li>
                                                                <li class="divider"></li>
                                                                <li><a href="#">Server stats <span class="glyphicon glyphicon-stats pull-right"></span></a></li>
                                                                <li class="divider"></li>
                                                                <li><a href="#">Messages <span class="badge pull-right"> 42 </span></a></li>
                                                            </ul>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

    </div>

    <!-- CUSTOM STYLESHEETS-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>
    <!-- CUSTOM JAVASCRIPTS -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</body>





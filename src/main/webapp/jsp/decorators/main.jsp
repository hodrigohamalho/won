<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html ng-app>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><decorator:title default="Wildfly Operations Network"/></title>

    <!-- VENDORS CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/vendor/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/vendor/bootstrap-theme.css"/>

    <!-- CUSTOM -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/application.css"/>

    <!-- VENDORS JAVASCRIPT -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/angular.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/bootstrap.js"></script>


    <!-- CUSTOM JAVASCRIPT -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/controllers/dc/index.js"></script>
    <decorator:head/>
  </head>

  <body>

      <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
          <div class="container-fluid">
              <div class="navbar-header">
                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                      <span class="sr-only">Toggle navigation</span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                  </button>
                  <a class="navbar-brand" href="${pageContext.request.contextPath}/">WildFly Operations Network</a>
              </div>
              <div class="navbar-collapse collapse">
                  <ul class="nav navbar-nav navbar-right">
                      <li><a href="#">Dashboard</a></li>
                      <li><a href="#">Settings</a></li>
                      <li><a href="#">Profile</a></li>
                      <li><a href="#">Help</a></li>
                  </ul>
                  <form class="navbar-form navbar-right">
                      <input type="text" class="form-control" placeholder="Search...">
                  </form>
              </div>
          </div>
      </div>

      <div class="container-fluid">
          <div class="row">
              <div class="col-sm-3 col-md-2 sidebar">
                  <ul class="nav nav-sidebar">
                      <li id="dc-nav"><a href="${pageContext.request.contextPath}/jsp/dc/index.jsp">Domain Controllers</a></li>
                      <li><a href="#">another page</a></li>
                      <li><a href="#">another page</a></li>
                      <li><a href="#">another page</a></li>
                  </ul>
                  <ul class="nav nav-sidebar">
                      <li><a href="">-</a></li>
                      <li><a href="">-</a></li>
                      <li><a href="">-</a></li>
                      <li><a href="">-</a></li>
                  </ul>
                  <ul class="nav nav-sidebar">
                      <li><a href="">-</a></li>
                      <li><a href="">-</a></li>
                      <li><a href="">-</a></li>
                  </ul>
              </div>

              <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                  TESTEEEEEE: {{ 'yet' + '!'}}
                <div id="alert-holder"></div>

                <decorator:body/>
              </div>

          </div>
      </div>

      <jsp:include page="/jsp/partials/_alerts.jsp"></jsp:include>
  </body>
</html>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><decorator:title default="Wildfly Operations Network"/></title>

    <!-- VENDORS CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/application.css"/>

    <!-- VENDORS JAVASCRIPT -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/docs.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/handlebars-v1.3.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/jquery.defaultvalue.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vendor/jquery.searchable-1.0.0.min.js"></script>

    <!-- CUSTOM JAVASCRIPT -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/application.js"></script>
    <script type="text/javascript">var contextPath="${pageContext.request.contextPath}";</script>
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
                <decorator:body/>
              </div>

          </div>
      </div>
  </body>
</html>

<#macro commonStyle>

<#-- favicon -->
<link rel="icon" href="favicon.ico" />

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="${request.contextPath}/static/adminlte/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" href="${request.contextPath}/static/plugins/font-awesome-4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css"> -->
<link rel="stylesheet" href="${request.contextPath}/static/plugins/ionicons-2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${request.contextPath}/static/adminlte/dist/css/AdminLTE-local.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="${request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<script>var base_url = '${request.contextPath}';</script>

</#macro>

<#macro commonScript>

<!-- jQuery 2.1.4 -->
<script src="${request.contextPath}/static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${request.contextPath}/static/adminlte/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="${request.contextPath}/static/adminlte/plugins/fastclick/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="${request.contextPath}/static/adminlte/dist/js/app.min.js"></script>
<#-- jquery.slimscroll -->
<script src="${request.contextPath}/static/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>

<#-- layer -->
<script src="${request.contextPath}/static/plugins/layer/layer.js"></script>

<script src="${request.contextPath}/static/js/common.1.js"></script>

</#macro>

<#macro commonHeader>
<header class="main-header">
    <nav class="navbar navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a href="${request.contextPath}/" class="navbar-brand"><b>XXL Code Generator</b></a>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                    <i class="fa fa-bars"></i>
                </button>
            </div>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a target="_blank" href="http://www.xuxueli.com/xxl-code-generator/">官方文档</a>
                        <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="hidden-xs">表结构</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${request.contextPath}/">XXX</a></li>
                        </ul>-->
                    </li>
                </ul>
            </div>
            <!-- /.navbar-custom-menu -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</header>
</#macro>

<#macro commonFooter >
<footer class="main-footer">
    <div class="container">
        Powered by <b>XXL Code Generator</b> v0.0.2
        <div class="pull-right hidden-xs">
            <strong>Copyright &copy; 2018-${.now?string('yyyy')} &nbsp;
                <a href="http://www.xuxueli.com/" target="_blank" >xuxueli</a>
                <a href="https://github.com/xuxueli/xxl-code-generator" target="_blank" >github</a>
            </strong><!-- All rights reserved. -->
        </div>
    </div>
</footer>
</#macro>
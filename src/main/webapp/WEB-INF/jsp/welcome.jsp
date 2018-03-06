<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Find That Recipe!</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

    <%--<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />--%>

    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->



    <!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
    <%--<c:url value="/css/bootstrap.min.css" var="bstrapmin" />--%>
    <link href="/css/bootstrap.min.css" rel="stylesheet" />

    <%--<c:url value="/css/bootstrap-select.min.css" var="bstrapsel" />--%>
    <link href="/css/bootstrap-select.min.css" rel="stylesheet" />


    <!-- Custom Fonts -->



    <link href="/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
          rel="stylesheet" type="text/css">
    <link
            href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
            rel="stylesheet" type="text/css">


    <!-- Custom CSS -->

    <%--<link href="/css/terminal.css" rel="stylesheet">--%>
    <%--<link href="/css/freelancer.css" rel="stylesheet">--%>
    <%--<link href="/css/custom.css" rel="stylesheet">--%>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body id="page-top" class="index">

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <%--<button type="button" class="navbar-toggle" data-toggle="collapse"--%>
                    <%--data-target="#bs-example-navbar-collapse-1">--%>
                <%--<span class="sr-only">Toggle navigation</span>--%>
                <%--<!-- <span class="icon-bar"></span>--%>
                <%--<span class="icon-bar"></span>--%>
                <%--<span class="icon-bar"></span> -->--%>
            <%--</button>--%>
            <a class="navbar-brand" href="#page-top">Find That Recipe!</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <!-- 			<div class="collapse navbar-collapse"
            id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="hidden"><a href="#page-top"></a></li>
                <li class="page-scroll">
                    <a href="#portfolio">Portfolio</a>
                </li>
                <li class="page-scroll">
                    <a href="#about">About</a>
                </li>
                <li class="page-scroll">
                    <a href="#contact">Contact</a>
                </li>
            </ul>
        </div> -->
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<!-- Header -->
<div class="header">
    <div class="container intro-header padder">
        <div class="row">
            <div class="col-lg-12">

                <div class="intro-text">
                    <%--<span class="name">What's in YOUR fridge?</span>--%>
                    <%--<hr class="star-light">--%>
                    <span id="info" class="skills">What's in YOUR fridge? The only tool that helps you find a recipe based on what YOU have in YOUR fridge!</span>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Tool Section -->
<section id="tool">
    <div id="errorMessage">
        <span></span>
    </div>
    <!-- Execute Batch job pages -->
    <form id="executeBatch" name="excecuteBatch" nonvalidate>
        <div id="ingredientInfo" class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h3>So, whats in your fridge?</h3>
                </div>
            </div>
            <div class="row control-group">
                <div id="all_ingredients" class="form-group col-xs-12 floating-label-form-group controls">
                    <label>Ingredients</label>
                    <br>
                    <input type="text" name="ings" class="form-control"
                                                  placeholder="Enter Ingredient" id="ingredient0" required
                                                  data-validation-required-message="Please enter a valid Ingredient">
                    <button id="rem0" disabled onclick="remove('0')" class="glyphicon glyphicon-minus inactive" aria-hidden="true"></button>
                    <button id="add0" onclick="add('1')" class="glyphicon glyphicon-plus" aria-hidden="true"></button>
                    <p id="0" class="help-block text-danger"></p>

                </div>
            </div>

            <div class="row col-xs-6 col-xs-offset-6 tool-nav-buttons">
                <a class="btn btn-success pull-right"
                   onclick="canGoNext(1, null)/*;loadOngo()*/">Next</a>
            </div>
        </div>

    </form>

    <form hidden id="executeBatch2" name="excecuteBatch2" nonvalidate>
        <div id="recipeInfo" class="container">

            <div class="row">
                <div class="col-lg-12 text-center">
                    <h3>Here are your recipes!! Choose one!</h3>
                </div>
            </div>


            <div class = "row control-group" id="allResults">

            </div>
        </div>


    </form>

    <form hidden id="executeBatch3" name="excecuteBatch3" nonvalidate>
            <div id="instructionsInfo" class="container">

                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h3>Instructions</h3>
                    </div>
                </div>


                <div class = "row control-group" id="instructionResults">

                </div>
            </div>


        </form>

</section>


<!-- Footer -->


<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
<div class="scroll-top page-scroll visible-xs visible-sm">
    <a class="btn btn-primary" href="#page-top"> <i
            class="fa fa-chevron-up"></i>
    </a>
</div>


<!-- FOOTER START-->

<!-- FOOTER END -->

<!-- jQuery -->



<%--<script src="resources/styles/js/jquery.js"></script>--%>

<!-- Bootstrap Core JavaScript -->
<script src="resources/styles/js/bootstrap.min.js"></script>
<script src="resources/styles/js/bootstrap-select.js"></script>


<!-- Plugin JavaScript -->

<script
        src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

<script src="resources/styles/js/classie.js"></script>
<script src="resources/styles/js/cbpAnimatedHeader.js"></script>

<!-- Contact Form JavaScript -->
<script src="resources/styles/js/jqBootstrapValidation.js"></script>
<script src="resources/styles/js/contact_me.js"></script>

<!-- Custom Theme JavaScript -->
<script src="resources/styles/js/freelancer.js"></script>

<!-- JS -->

<script src="/js/functionality.js"/>

<script src="resources/scripts/serverCalls.js"></script>
<script src="resources/scripts/jobRunSteps.js"></script>


</body>

</html>

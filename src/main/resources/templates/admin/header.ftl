<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>lhr's Blog后台管理</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../editor.md-master/css/editormd.min.css" />
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstarp.min.js"></script>
    <script src="../js/bootstrap-paginator.js"></script>
    <script src="../js/blog.js"></script>
    <link rel="stylesheet" href="../shards-ui-master/dist/css/shards.min.css">
    <link rel="stylesheet" href="../shards-ui-master/dist/css/shards-extras.min.css">
</head>
<style>
    .list-group-item:first-child{
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
    .list-group-item:last-child{
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }
    .rounded-circle{
        width: 30px;
        height: 30px;
    }
    html{
        height: 100%;
    }
    .pagination{
        webkit-box-pack: center!important;
        -ms-flex-pack: center!important;
        justify-content: center!important;
    }
    #editormd{
        height: 500px;
    }
    .blog-admin-menu{
        width: 200px;
        padding-right: 0;
        position: fixed;
        height: 100%;
        left: 0;
    }
    .blog-row{
        margin-top: 70px;
        margin-left: 200px;
        /*line-height: 60px;*/
        /*background: beige;*/

    }
    .navbar {
        position: fixed !important;
        height: 70px !important;
        width: 100%;
        top: 0;
        z-index: 999;
    }
</style>
<body>
<script>
    $(document).ready(function(){
        $('.blog-row').height($('html').height()- 74);
                // $('nav').height());
    });

</script>

<nav class="navbar bg-dark navbar-dark navbar-expand-lg">
    <a class="navbar-brand" href="#">${title ! '博客后台管理系统'}</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
    </div>
    <div class="nav justify-content-end">
        <div class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="dropdownMenuImage" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <img src="http://img0.imgtn.bdimg.com/it/u=2288384215,2187588675&fm=27&gp=0.jpg" alt="header image" class="rounded-circle">
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuImage">
                <a class="dropdown-item" href="update_password.html">修改密码</a>
                <a class="dropdown-item" href="loginout.html">注销</a>
            </div>
        </div>
    </div>
</nav>
<div style="width: 100%;">
    <div class="">
        <div class="blog-admin-menu bg-secondary">
            <a class="list-group-item list-group-item-secondary list-group-item-action <#if title == "文章管理">active</#if>" href="articles.html">文章管理</a>
            <a class="list-group-item list-group-item-secondary list-group-item-action <#if title == "友链管理">active</#if>" href="friendlink.html">友链管理</a>
        </div>
        <div class="blog-row">







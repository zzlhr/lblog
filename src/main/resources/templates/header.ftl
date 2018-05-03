<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <#if path == 'article'>
        <title>${article.articleTitle}</title>
        <meta name="keywords" content="<#list article.getTagList() as tag>${tag.getTagContent()},</#list>">
        <meta name="description" content="${article.articleDescribe}">
    <#else>
        <title>刘浩然的博客</title>
        <meta name="keywords" content="刘浩然的博客,Java,Web,程序员,博客,Python,C,golang,javascript,spring boot,spring data,spring data jpa,数据库,分布式,微服务,spring cloud">
        <meta name="description" content="记录技术的点滴，分享每一个突破。">
    </#if>
    <link rel="alternate" hreflang="zh-Hans" href="http://www.lhrsite.com/">
    <#--<link rel="stylesheet" href="css/bootstrap.min.css">-->
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <#--<script src="js/jquery.slim.min.js"></script>-->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <#--<script src="js/popper.min.js"></script>-->
    <script src="https://cdn.bootcss.com/popper.js/1.13.0/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <#--<script src="js/bootstarp.min.js"></script>-->
    <link rel="stylesheet" href="shards-ui-master/dist/css/shards.min.css">
    <link rel="stylesheet" href="shards-ui-master/dist/css/shards-extras.min.css">
    <link href="https://cdn.bootcss.com/prettify/r298/prettify.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/prettify/r298/run_prettify.js"></script>
    <style>
        .blog-article-list{
            margin-bottom: 3rem;
        }
        .blog-content{
            margin-top: 10px;
        }
        .blog-left-card{
            width: 100%;
            margin-bottom: 1rem;
        }
        .blog-main-content{
            margin-bottom: 1rem;
        }
        .blog-article-comment{
            margin-top: 1rem;
        }
        .blog-usre-header{
            width: 40px;
            height: 40px;
        }
        .navbar{
             padding: 0.5rem;
        }
        .article-img{
            width: 100%;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-dark bg-secondary navbar-expand-lg">
    <a class="navbar-brand" href="/">刘浩然的博客</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item <#if path == 'index'>active</#if>">
                <a class="nav-link" href="index.html">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item <#if path == 'articles'>active</#if> ">
                <a class="nav-link" href="articles.html">Blog</a>
            </li>
            <li class="nav-item <#if path == 'about'>active</#if>">
                <a class="nav-link" href="about.html">About</a>
            </li>
        </ul>
        <form action="/search.html" class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" name="keyword" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-light bg-light my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<div class="container blog-content">

    <div class="row">
        <div class="col-xl-8 blog-main-content">

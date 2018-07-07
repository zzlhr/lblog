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
        <title>${website.name}</title>
        <meta name="keywords" content="${website.keywords !""}">
        <meta name="description" content="${website.description !""}">
    </#if>
    <link rel="alternate" hreflang="zh-Hans" href="http://www.lhrsite.com/">
    <#--<link rel="stylesheet" href="css/bootstrap.min.css">-->
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <#--<script src="js/jquery.slim.min.js"></script>-->
    <#--<script src="js/bootstarp.min.js"></script>-->
    <#--<link href="https://cdn.bootcss.com/prism/9000.0.1/themes/prism.min.css" rel="stylesheet">-->
    <link rel="stylesheet" href="shards-ui-master/dist/css/shards.min.css">
    <link rel="stylesheet" href="shards-ui-master/dist/css/shards-extras.min.css">
    <link href="https://cdn.bootcss.com/prettify/r298/prettify.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/prettify/r298/prettify.min.css" rel="stylesheet">
    <style>
        .blog-article-list{
            margin-bottom: 3rem;
            opacity:0.6;
        }
        .blog-about{
            opacity: 0.6;
        }
        .blog-article-list:hover, .blog-left-card:hover, .blog-about:hover{
            opacity:0.9;
        }
        .blog-content{
            margin-top: 10px;
        }
        .blog-left-card{
            width: 100%;
            margin-bottom: 1rem;
            opacity:0.6;
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
        body{
            background: url("https://ws4.sinaimg.cn/large/006tKfTcly1ft1kermfmdj31kw11hkjy.jpg");

        }
        .blog-clarity{
            opacity:0.6;
        }

        .blog-clarity:hover{
            opacity: 0.9;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-dark bg-secondary navbar-expand-lg blog-clarity">
    <a class="navbar-brand" href="/">${website.name}</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item <#if path == 'index'>active</#if>">
                <a class="nav-link" href="index.html">Home</a>
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

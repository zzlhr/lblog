<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <#if path == 'article'>
        <title>${article.articleTitle}</title>
        <meta name="keywords" content="<#list article.getTagList() as tag>${tag.getTagContent()},</#list>">
        <meta name="description" content="${article.articleDescribe}">
    <#else>
        <title>lhr's-blog</title>
    </#if>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstarp.min.js"></script>
    <link rel="stylesheet" href="shards-ui-master/dist/css/shards.min.css">
    <link rel="stylesheet" href="shards-ui-master/dist/css/shards-extras.min.css">
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
    </style>
</head>
<body>
<nav class="navbar navbar-dark bg-primary navbar-expand-lg">
    <a class="navbar-brand" href="/">lhr的博客</a>
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
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<div class="container blog-content">
    <div class="row">
        <div class="col-xl-8 blog-main-content">
<html>

<head>
    <meta charset="UTF-8">
    <title>后台登录</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../editor.md-master/css/editormd.min.css" />
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstarp.min.js"></script>
    <link rel="stylesheet" href="../shards-ui-master/dist/css/shards.min.css">
    <link rel="stylesheet" href="../shards-ui-master/dist/css/shards-extras.min.css">
    <style>
        html body{
            height: 100%;
        }

        .blog-login{
            width: 300px;
            height: 400px;
            margin: auto;
            margin-top: 100px;
        }
    </style>
</head>
<body>
    <div class="blog-login">
        <div class="card text-white bg-dark mb-3" style="width: 100%">
            <div class="card-header">登录</div>
            <div class="card-body">
                <form action="login.html" method="post">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Username</label>
                        <input type="text" class="form-control" name="username" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter username">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">login</button>
                </form>
            </div>
        </div>
    </div>

</body>

</html>
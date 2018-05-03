<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>lblog install</title>
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../shards-ui-master/dist/css/shards.min.css">
    <link rel="stylesheet" href="../shards-ui-master/dist/css/shards-extras.min.css">
    <link href="https://cdn.bootcss.com/prettify/r298/prettify.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="card" style="width: 100%;">
        <div class="card-body">
            <h5 class="card-title">添加管理员</h5>
            <h6 class="card-subtitle mb-2 text-muted">安装过程中出现问题，请联系作者qq：2388399752，或者 https://github.com/zzlhr/lblog 发布问题</h6>

            <form action="user-add.html" method="post">
                <div class="form-group">
                    <label for="loginName">管理员登录名:</label>
                    <input type="text" class="form-control" name="loginName" id="loginName" placeholder="请输入登录名">
                </div>
                <div class="form-group">
                    <label for="username">管理员用户名:</label>
                    <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
                </div>
                <div class="form-group">
                    <label for="password">管理员密码:</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="请输入管理员密码">
                </div>
                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary">确认</button>
                </div>
            </form>

        </div>
    </div>

</div>



</body>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.13.0/popper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/prettify/r298/run_prettify.js"></script>
</html>
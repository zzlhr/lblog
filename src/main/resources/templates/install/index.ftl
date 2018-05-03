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
                <h5 class="card-title">开始安装</h5>
                <h6 class="card-subtitle mb-2 text-muted">安装过程中出现问题，请联系作者qq：2388399752，或者 https://github.com/zzlhr/lblog 发布问题</h6>

                <form action="init" method="post">
                    <div class="form-group">
                        <label for="host">网站域名:</label>
                        <input type="text" class="form-control" name="host" id="host" aria-describedby="hostHelp" placeholder="请输入网站域名">
                        <small id="hostHelp" class="form-text text-muted">如:www.lhrsite.com</small>
                    </div>
                    <div class="form-group">
                        <label for="name">网站名称:</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="请输入网站名称">
                    </div>
                    <div class="form-group">
                        <label for="keyword">网站关键字:</label>
                        <input type="text" class="form-control" name="keyword" id="keyword" placeholder="请输入网站关键字">
                    </div>
                    <div class="form-group">
                        <label for="description">网站描述:</label>
                        <input type="text" class="form-control" name="description" id="description" placeholder="请输入网站描述">
                    </div>
                    <div class="form-group">
                        <label for="master_name">站长名称:</label>
                        <input type="text" class="form-control" name="masterName" id="masterName" placeholder="请输入站长名称">
                    </div>
                    <div class="form-group">
                        <label for="master_email">站长邮箱:</label>
                        <input type="text" class="form-control" name="masterEmail" id="masterEmail" placeholder="请输入站长邮箱">
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
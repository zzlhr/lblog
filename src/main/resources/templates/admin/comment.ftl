<#include "header.ftl">
<div class="col-12 blog-card-content" style="top: 10px;">

    <div class="card text-white bg-secondary">
        <div class="card-header">
            <h5 class="float-left">评论管理</h5>
        </div>
        <div class="card-body">
            <table class="table table-dark">
                <thead>
                <tr>
                    <th>文章名</th>
                    <th>评论用户</th>
                    <th>评论ip</th>
                    <th>评论内容</th>
                    <th>评论时间</th>
                    <th>更新时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#escape x as x?html>
                    <#list comments.getContent() as comment>
                    <tr>
                        <th>${ comment.article.articleTitle !'' }</th>
                        <td>${ comment.user.username !'' }</td>
                        <td>${ comment.commentIp }</td>
                        <td>${ comment.commentContent!'' }</td>
                        <td>${ comment.createTime !''}</td>
                        <td>${ comment.updateTime !''}</td>
                        <td>
                            <#if comment.commentStatus == 0>
                                显示
                            <#elseif comment.commentStatus == 1>
                                隐藏
                            <#elseif comment.commentStatus == 2>
                                已删除
                            </#if>
                        </td>
                        <td>
                            <a href="comment-delect.html?id=${comment.id}" class="btn btn-sm btn-success">删除评论</a>
                        </td>
                    </tr>
                    </#list>
                    </#escape>
                </tbody>
            </table>
            <div>
                <ul id='bp-element' class="pagination"></ul>
            </div>
        </div>
    </div>
</div>

</div>

</div>
</div>

</body>
<script>
    try {
        var element = $('#bp-element');
        options = {
            bootstrapMajorVersion:4, //对应的bootstrap版本
            currentPage: ${ comments.number + 1 }, //当前页数，这里是用的EL表达式，获取从后台传过来的值
            numberOfPages: ${ comments.size }, //每页页数
            totalPages:${ comments.totalPages }, //总页数，这里是用的EL表达式，获取从后台传过来的值
            shouldShowPage:true,//是否显示该按钮
            itemTexts: function (type, page, current) {//设置显示的样式，默认是箭头
                switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "末页";
                    case "page":
                        return page;
                }
            },
            //点击事件
            onPageClicked: function (event, originalEvent, type, page) {
                location.href = changeUrlArg(location.href, "page", page);
            }
        };
        element.bootstrapPaginator(options);
        window.onload = function () {
            $('#bp-element').addClass("justify-content-center");
        };
    }catch (e) {
        console.log(e);
    }




</script>


<#include "footer.ftl">
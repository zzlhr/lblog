<#include "header.ftl">
<div class="col-12 blog-card-content" style="top: 10px;">

<div class="card text-white bg-secondary">
    <div class="card-header">
        <h5 class="float-left">文章列表</h5>
        <div class="float-right"><button id="add-article-btn" class="btn btn-sm btn-light">添加</button> </div>
    </div>
    <div class="card-body">
        <table class="table table-dark">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">标题</th>
                <th scope="col">作者</th>
                <th scope="col">创建时间</th>
                <th scope="col">更新时间</th>
                <th scope="col">状态</th>
                <th scope="col">操作</th>
            </tr>
            </thead>
            <tbody>
        <#list articles.getContents() as article>
        <tr>
            <th scope="row">${ article.id }</th>
            <td>${ article.articleTitle }</td>
            <td>${ article.articleAuthor.username }</td>
            <td>${ article.createTime }</td>
            <td>${ article.updateTime }</td>
            <td>
                <#if article.articleStatus == 0>
                    显示
                <#elseif article.articleStatus == 1>
                    隐藏
                <#elseif article.articleStatus == 2>
                    已删除
                </#if>
            </td>
            <td>
                <a href="article-update.html?id=${article.id}" class="btn btn-sm btn-success">编辑文章</a>
            </td>
        </tr>
        </#list>
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
            currentPage: ${ articles.number + 1 }, //当前页数，这里是用的EL表达式，获取从后台传过来的值
            numberOfPages: ${ articles.size }, //每页页数
            totalPages:${ articles.totalPages }, //总页数，这里是用的EL表达式，获取从后台传过来的值
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

    
    $('#add-article-btn').on('click',function () {
        location.href = 'article-add.html'
    })

    
</script>


<#include "footer.ftl">
<#include "header.ftl">
<#list articles.contents as article>
<div class="blog-article-list card">
    <div class="card-body">
    <h4><a href="article.html?id=${article.id}">${ article.articleTitle }</a></h4>
    <div>
        <small class="text-muted">由 ${article.articleAuthor.username} 发表于 ${ article.createTime } 更新与 ${ article.updateTime }</small>
    </div>
    <div>
        <#list article.getTagList() as tag>
            <#if tag_index % 9 == 0>
                <a href="#" class="badge badge-success">${tag.getTagContent()}</a>
            <#elseif tag_index % 7 == 0>
                <a href="#" class="badge badge-secondary">${tag.getTagContent()}</a>
            <#elseif tag_index % 6 == 0>
                <a href="#" class="badge badge-dark">${tag.getTagContent()}</a>
            <#elseif tag_index % 5 == 0>
                <a href="#" class="badge badge-danger">${tag.getTagContent()}</a>
            <#elseif tag_index % 4 == 0>
                <a href="#" class="badge badge-warning">${tag.getTagContent()}</a>
            <#elseif tag_index % 3 == 0>
                <a href="#" class="badge badge-info">${tag.getTagContent()}</a>
            <#elseif tag_index % 2 == 0>
                <a href="#" class="badge badge-light">${tag.getTagContent()}</a>
            <#elseif tag_index % 1 == 0>
                <a href="#" class="badge badge-primary">${tag.getTagContent()}</a>
            </#if>
        </#list>
    </div>
    <div>
        ${article.articleDescribe}
    </div>
    </div>
</div>
</#list>
<div>
    <ul id='bp-element' class="pagination"></ul>
</div>
<#include "footer.ftl">
</div>
</body>
<script src="js/bootstrap-paginator.js"></script>
<script src="js/blog.js"></script>
<script>
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
</script>
</html>
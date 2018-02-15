
<#include "header.ftl">
<#list articles as article>
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
<#include "footer.ftl">
</div>
</body>
</html>
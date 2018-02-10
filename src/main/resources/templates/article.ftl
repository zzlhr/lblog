<#include "header.ftl">
<div class="card"  style="padding-left: 20px; padding-right: 20px;">
    <div class="card-body">
        <div>
            <h3 class="text-center">${article.articleTitle}</h3>
            <p class="text-center text-muted">
                <small>
                    <span>作者: ${article.articleAuthor.username}</span>
                    <span>发布时间: ${article.createTime}</span>
                    <span>更新时间: ${article.updateTime}</span>
                </small>
            </p>
            <div class="text-center">
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
        </div>




        <div style="margin-top: 20px;">
            ${article.articleContentHtml}
        </div>
    </div>
</div>
<#include "footer.ftl">

</div>
</body>
</html>
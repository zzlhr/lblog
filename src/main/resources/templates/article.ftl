<#include "header.ftl">
<div class="card">
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
                        <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-success">${tag.getTagContent()}</a>
                    <#elseif tag_index % 7 == 0>
                        <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-secondary">${tag.getTagContent()}</a>
                    <#elseif tag_index % 6 == 0>
                        <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-dark">${tag.getTagContent()}</a>
                    <#elseif tag_index % 5 == 0>
                        <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-danger">${tag.getTagContent()}</a>
                    <#elseif tag_index % 4 == 0>
                        <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-warning">${tag.getTagContent()}</a>
                    <#elseif tag_index % 3 == 0>
                        <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-info">${tag.getTagContent()}</a>
                    <#elseif tag_index % 2 == 0>
                        <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-light">${tag.getTagContent()}</a>
                    <#elseif tag_index % 1 == 0>
                        <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-primary">${tag.getTagContent()}</a>
                    </#if>
            </#list>
            </div>
        </div>
        <div style="margin-top: 20px;">
            ${article.articleContentHtml}
        </div>

        <div></div>
    </div>
</div>
<div class="blog-article-comment card">
    <div class="card-body">
        <span style="font: 30;">评论</span>
        <#if user??>
            <div class="float-right">
                <span>${user.username}</span>
            </div>
        <#else>
            <div class="float-right">
                <a href="https://github.com/login/oauth/authorize?client_id=2e20720cb5be056663cf&redirect_uri=${domain}api/login.do" class="btn btn-sm btn-success">
                    <img src="/svg/mark-github.svg" /> &nbsp;Login
                </a>
            </div>
        </#if>
        <hr />

        <div>
            <#list comments as comment>
                <div>
                    <img class="rounded-circle blog-usre-header" src="${comment.user.headerUrl}">
                    <b>${comment.user.getUsername()} <small>于${comment.createTime}</small> 说:</b>
                </div>
                <div style="margin-left: 40px;">
                    <p>${comment.commentContent}</p>
                </div>
            </#list>
            <hr />
        </div>
        <div>
            <form action="send_comment" method="post">
                <div class="form-group">
                    <label for="comment">评论内容:</label>
                    <input hidden name="articleId" value="${article.id}">
                    <textarea class="form-control" name="comment" id="comment" rows="3"></textarea>
                </div>
                <div class="float-right">
                    <button type="submit" class="btn btn-sm btn-info">评论</button>
                </div>
            </form>
            <br>

        </div>

    </div>
</div>
<#include "footer.ftl">

</div>
</body>
<script>
    sessionStorage.setItem("article_id", ${article.id})
</script>
</html>
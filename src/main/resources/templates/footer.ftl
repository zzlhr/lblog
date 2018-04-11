</div>

            <div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">

                <div class="card blog-left-card">
                    <div class="card-body">
                        <h5 class="card-title">About me</h5>
                        <p class="card-text">刘浩然</p>
                        <footer class="blockquote-footer">思考、总结是进步得捷径。</footer>
                    </div>
                </div>

                <div class="card blog-left-card">
                    <div class="card-body">
                        <h5 class="card-title">Class</h5>
                        <p class="card-text">
                            <#list topTenTags as tag>
                            <#if tag_index % 9 == 0>
                                <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-success">${tag.getTagContent()}(${tag.getTagTime()})</a>
                            <#elseif tag_index % 7 == 0>
                                <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-secondary">${tag.getTagContent()}(${tag.getTagTime()})</a>
                            <#elseif tag_index % 6 == 0>
                                <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-dark">${tag.getTagContent()}(${tag.getTagTime()})</a>
                            <#elseif tag_index % 5 == 0>
                                <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-danger">${tag.getTagContent()}(${tag.getTagTime()})</a>
                            <#elseif tag_index % 4 == 0>
                                <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-warning">${tag.getTagContent()}(${tag.getTagTime()})</a>
                            <#elseif tag_index % 3 == 0>
                                <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-info">${tag.getTagContent()}(${tag.getTagTime()})</a>
                            <#elseif tag_index % 2 == 0>
                                <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-light">${tag.getTagContent()}(${tag.getTagTime()})</a>
                            <#elseif tag_index % 1 == 0>
                                <a href="tag.html?tag=${tag.getTagContent()}" class="badge badge-primary">${tag.getTagContent()}(${tag.getTagTime()})</a>
                            </#if>
                            </#list>
                        </p>
                    </div>
                </div>

                <div class="card blog-left-card">
                    <div class="card-body">
                        <h5 class="card-title">Friend Links</h5>
                        <div class="card-text">
                            <#list friendLinks as fl>
                                <a href="${fl.url}">${fl.value}</a>
                                <br/>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>

<script>
    // 使用代码高亮
    $('pre').addClass('prettyprint')
    $('code').addClass('prettyprint')
</script>
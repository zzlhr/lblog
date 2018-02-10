<#include "header.ftl">
<div class="col-12 blog-card-content" style="top: 10px;">

    <div class="card text-white bg-secondary">
        <div class="card-header">
            <h5 class="float-left">友链列表</h5>
            <div class="float-right"><button id="add-friendLink-btn" class="btn btn-sm btn-light">添加</button> </div>
        </div>
        <div class="card-body">
            <table class="table table-dark">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">名称</th>
                    <th scope="col">url</th>
                    <th scope="col">状态</th>
                    <th scope="col">操作</th>
                </tr>
                </thead>
                <tbody>
        <#list friendLinks as friendLink>
        <tr>
            <th scope="row">${ friendLink.id }</th>
            <td>${ friendLink.value }</td>
            <td>${ friendLink.url }</td>
            <td>
                <#if friendLink.status == 0>
                    显示
                <#elseif friendLink.status == 1>
                    隐藏
                </#if>
            </td>
            <td>
                <button onclick="edit('${ friendLink.value }', '${ friendLink.url }')" class="btn btn-sm btn-success">编辑</button>
                <a id="delect-friendLink-btn" href="friendlink-delect.html?id=${friendLink.id}" class="btn btn-sm btn-success">删除</a>
            </td>
        </tr>
        </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="modal" id="friendlink-edit-model" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">编辑友链</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="friendlink-edit.html" method="post">
                        <div class="form-group">
                            <label for="friendlink-value">名称</label>
                            <input type="text" class="form-control" name="value" id="friendlink-value" placeholder="Enter name.">
                        </div>
                        <div class="form-group">
                            <label for="friendlink-url">url</label>
                            <input type="text" class="form-control" name="url" id="friendlink-url" placeholder="Enter url.">
                        </div>
                        <button type="submit" class="btn btn-primary">添加</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>

</body>
<script>
    $('#add-friendLink-btn').on('click',function () {
        $('#friendlink-value').val('')
        $('#friendlink-url').val('')
        $('#friendlink-edit-model').modal('show');
    })
    function edit(value, url) {
        $('#friendlink-value').val(value)
        $('#friendlink-url').val(url)
        $('#friendlink-edit-model').modal('show');
    }

</script>

<#include "footer.ftl">
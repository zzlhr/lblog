<#include "header.ftl">
<form class="col-12 article-edit" action="<#if article?? >article-update.html<#else>article-add.html</#if>" method="post" style="height: 700px;">
    <#if article?? ><input hidden type="text" name="id" value="${article.id}"></#if>
    <div class="form-group">
        <label for="article-input-title">文章标题</label>
        <input type="text" class="form-control" id="article-input-title" <#if article?? >value='${ article.articleTitle ! ''}'</#if>name="title" placeholder="Enter article title.">
    </div>
    <div class="form-group">
        <label for="article-input-tag">文章标签</label>
        <input type="text" class="form-control" <#if article?? >value='<#list article.tagList as tag>${tag.tagContent},</#list>'</#if> name="tag" id="tag" placeholder="Enter article tag.">
    </div>
    <div class="form-group">
        <label for="article-input-describe">文章描述</label>
        <textarea class="form-control" id="article-input-describe" name="describe"  placeholder="Enter article describe."><#if article?? >${ article.articleDescribe ! ''}</#if></textarea>
    </div>
    <div class="custom-control custom-radio custom-control-inline">
        <input type="radio" id="status1" name="status" value="0" class="custom-control-input">
        <label class="custom-control-label" for="status1">显示</label>
    </div>
    <div class="custom-control custom-radio custom-control-inline">
        <input type="radio" id="status2" name="status" value="1"  class="custom-control-input">
        <label class="custom-control-label" for="status2">隐藏</label>
    </div>
    <div id="editormd">
        <textarea style="display:none;"><#if article?? >${ article.articleContentMd ! ''}</#if></textarea>
    </div>
    <div>

    </div>
    <button type="submit" class="btn btn-primary float-right">发布</button>
</form>


</div>
</div>
</div>

</body>
<script src="../editor.md-master/editormd.min.js"></script>
<script>
    $(function() {
        var editor = editormd("editormd", {
            // Autoload modules mode, codemirror, marked... dependents libs path
            path : "../editor.md-master/lib/",
            emoji : true,
            taskList : true,
            tocm            : true,         // Using [TOCM]
            tex : true,                   // 开启科学公式TeX语言支持，默认关闭
            saveHTMLToTextarea: true,
            flowChart : true,             // 开启流程图支持，默认关闭
            sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
            codeFold : true,
        });

        $("#editormd").on('paste', function (ev) {
            var data = ev.clipboardData;
            var items = (event.clipboardData || event.originalEvent.clipboardData).items;
            for (var index in items) {
                var item = items[index];
                if (item.kind === 'file') {
                    var blob = item.getAsFile();
                    var reader = new FileReader();
                    reader.onload = function (event) {
                        var base64 = event.target.result;
                        //ajax上传图片
                        $.ajax({
                            url:'uploadBase64.do',
                            data:{base:base64},
                            type:"post",
                            success:function (ret) {
                                console.log(ret.msg);
                                if (ret.code === 13000) {
                                    //新一行的图片显示
                                    editor.insertValue("\n![上传的图片](" + ret.path + ")");
                                }
                            }
                        });
                    }; // data url!
                    var url = reader.readAsDataURL(blob);
                }
            }
        });




    });




</script>
<#include "footer.ftl">
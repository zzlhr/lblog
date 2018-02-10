<#include "header.ftl">
<div class="col-4">
    <form action="update_password.html" method="post">
        <form>
            <div class="form-group">
                <label for="old-password">原密码</label>
                <input type="password" class="form-control" name="oldPassword" id="old-password" placeholder="Old Password">
            </div>
            <div class="form-group">
                <label for="new-password">新密码</label>
                <input type="password" class="form-control" name="newPassword" id="new-password" placeholder="New Password">
            </div>
            <div class="form-group">
                <label for="confirm-password">确认密码</label>
                <input type="password" class="form-control" name="confirmPassword" id="confirm-password" placeholder="Confirm password">
            </div>
            <button type="submit" class="btn btn-primary">确认</button>
        </form>
    </form>
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
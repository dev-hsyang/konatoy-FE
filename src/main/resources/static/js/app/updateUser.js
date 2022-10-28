var main = {

    init : function () {
        var _this = this;
        $('#btn-updateNick').on('click', function (){
            _this.updateNick();
        });
        $('#btn-updatePassword').on('click', function (){
            _this.updatePassword();
        });
    },

    updateNick : function () {
        var data = {
            nickname : $('#nickname').val(),
            oldPassword: $('#oldPassword1').val()
        };

        var id = $('#id').val();

        $.ajax({
            type : 'POST',
            url : '/api/mypage/update-nickname/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data),

            success : function(data) {
                if(data==-1){
                    alert('현재 비밀번호를 다시 확인해주세요');
                    document.modify.oldPassword1.focus();
                    exit();
                }

                alert('수정되었습니다.');
                window.location.href = "/mypage/update";
            },
            error : function (error){
                alert(JSON.stringify(error));
            }
        });
    },

    updatePassword : function (){
        var data = {
            oldPassword : $('#oldPassword2').val(),
            newPassword : $('#password').val()
        };

        var id = $('#id').val();

        $.ajax({
            type : 'POST',
            url : '/api/mypage/update-password/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data),

            beforeSend : function () {
                if(document.modify.password.value!=document.modify.password2.value){
                    alert("비밀번호를 다시 확인해주세요.");
                    document.modify.password2.focus();
                    exit;
                }
            },
            success : function (data) {
                if(data==-1){
                    alert('현재 비밀번호를 다시 확인해주세요');
                    exit();
                }
                alert('수정되었습니다.');
                alert('변경된 비밀번호로 다시 로그인 해주세요')
                window.location.href="/logout";
            },
            error : function (error){
                alert(JSON.stringify(error));
            }

        })
    }
}

main.init();
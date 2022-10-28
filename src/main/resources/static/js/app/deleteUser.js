var main = {

    init : function () {
        var _this = this;
        $('#btn-deleteUser').on('click', function (){
            _this.confirm();
            _this.delete();
        });
    },

    delete : function () {
        var id = $('#userID').val();
        $.ajax({
            type: 'POST',
            url: "/api/mypage/delete/" + id,

            success : function () {
                alert('회원탈퇴 성공했습니다.');
                window.location.href = '/logout';
            },
            error : function (error){
                alert(JSON.stringify(error));
            }
        })
    },

    confirm : function () {
        var confirmflag = confirm("정말 회원을 탈퇴하시겠습니까?");
        if (!confirmflag) {
            exit();
        }
    }
}
main.init();
var param;

var main = {


    init : function () {
        var _this = this;
        $('#btn-signUp').on('click', function () {
            _this.signUp();
        });
        $('#username').on("input", function (){
            _this.checkId();
        });
        $('#nickname').on("input", function (){
            _this.checkNick();
        });
        $('#select-club').on('change', function (){
            _this.selectClub();
        })
    },

    signUp: function () {
        var data = {
            username: $('#username').val(),
            password: $('#password').val(),
            nickname: $('#nickname').val()
        };

        var blankPattern = /[\s]/g;
        var specialPattern = /['~!@#$%^&*()\\\'\";:\/?]/gi;

        $.ajax({
            type: "POST",
            url: '/api/join'+param,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),

            beforeSend : function () {
                if(document.join.username.value==""){
                    alert("아이디를 입력하세요.");
                    document.join.username.focus();
                    exit;
                }else if(document.join.nickname.value==""){
                    alert("닉네임을 입력하세요.");
                    document.join.nickname.focus();
                    exit;
                }

                if(blankPattern.test(document.join.username.value)||specialPattern.test(document.join.username.value)){
                    alert("아이디는 공백이나 특수문자 제외입니다.");
                    document.join.username.focus();
                    exit;
                }else if(blankPattern.test(document.join.nickname.value)||specialPattern.test(document.join.nickname.value)){
                    alert("닉네임은 공백이나 특수문자 제외입니다.");
                    document.join.nickname.focus();
                    exit;
                }

                if(document.join.password.value!=document.join.password2.value){
                    alert("비밀번호가 일치하지 않습니다.");
                    document.join.password2.focus();
                    exit;
                }else if(document.join.password.value==""||document.join.password2.value==""){
                    alert("비밀번호를 입력하세요.");
                    document.join.password.focus();
                    exit;
                }


            },
            success: function () {
                alert('회원가입에 성공했습니다.');
                window.location.href = '/';
            },
            error: function (error) {
                alert(JSON.stringify(error));
            }
        });
    },
    checkId : function () {
        var username = $("#username").val();

        $.ajax({
            type: "GET",
            url: "/api/join/checkId?username=" + username,

            success : function(data) {
                if(data!=1){
                    $("#checkId").html('이미 사용중인 아이디입니다.');
                    $("#checkId").attr('color', 'red');
                    $("#btn-signUp").attr("disabled", true);
                }else{
                    $("#checkId").html('사용 가능한 아이디입니다.');
                    $("#checkId").attr('color','green');
                    $("#btn-signUp").attr('disabled', false);
                }
            }
        });
    },

    checkNick : function () {
        var nickname = $("#nickname").val();

        $.ajax({
            type: "GET",
            url: "/api/join/checkNick?nickname=" + nickname,

            success : function(data) {
                if(data!=1){
                    $("#checkNick").html('이미 사용중인 닉네임입니다.');
                    $("#checkNick").attr('color', 'red');
                    $("#btn-updateNick").attr("disabled", true);
                    $("#btn-signUp").attr("disabled", true);
                }else {
                    $("#checkNick").html('사용 가능한 닉네임입니다.');
                    $("#checkNick").attr('color', 'green');
                    $("#btn-updateNick").attr("disabled", false);
                    $("#btn-signUp").attr("disabled", false);
                }
            }
        });
    },

    selectClub : function () {
        var club = $('#select-club option:selected').val();
        param = "?club=" + club;
    }

}

main.init();
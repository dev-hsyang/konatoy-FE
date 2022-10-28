var main = {

    init: function () {
        var _this = this;
        $('#btn-signUp').on('click', function () {
            _this.signUp();
        });
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#username').on("input", function (){
            _this.checkId();
        });
        $('#nickname').on("input", function (){
            _this.checkNick();
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
            url: '/api/join',
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
                    alert("아이디를 다시 확인해주세요.");
                    document.join.username.focus();
                    exit;
                }else if(blankPattern.test(document.join.nickname.value)||specialPattern.test(document.join.nickname.value)){
                    alert("닉네임을 다시 확인해주세요.");
                    document.join.nickname.focus();
                    exit;
                }

                if(document.join.password.value!=document.join.password2.value){
                    alert("비밀번호를 다시 확인해주세요.");
                    document.join.password2.focus();
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

    save: function () {
        var data = {
            title: $('#title').val(),
            clubID: $('#club').val(),
            fileID: $('#fileID').val(),
            locID: $('#locID').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: "POST",
            url: "/api/posts",
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),

            success: function () {
                alert('글이 등록되었습니다.');
                window.location.href = "/board";
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
                }else{
                    $("#checkId").html('사용 가능한 아이디입니다.');
                    $("#checkId").attr('color','green');
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
                }else {
                    $("#checkNick").html('사용 가능한 닉네임입니다.');
                    $("#checkNick").attr('color', 'green');
                }
            }
        });
    }
}

main.init();
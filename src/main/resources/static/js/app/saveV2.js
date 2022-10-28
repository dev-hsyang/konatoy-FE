const Editor = toastui.Editor;
const editor = new Editor({
    el: document.querySelector('#editor'),
    height: '600px',
    initialEditType: 'wysiwyg',
    previewHighlight: false,
    previewStyle: 'vertical',
    hooks: {
        addImageBlobHook: (blob, callback) => {
            const formData = new FormData();
            formData.append('image', blob);
            let url="/images/";

            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                url: '/api/posts/image',
                data: formData,
                dataType: 'json',
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {
                    url += data.filename;
                    callback(url, '사진 대체 텍스트 입력');
                },
                error: function(error) {
                    alert(filename);
                    callback('image_load_fail', '실패 - 사진 대체 텍스트 입력');
                }
            });
        }
    }
});

var postID;

var main = {


    init : function () {
        var _this = this;

        $('#btn-save').on('click', function (){
            _this.save();
        })
    },

    save : function () {
        var data = {
            title: $('#title').val(),
            clubID: $('#club').val(),
            fileID: $('#fileID').val(),
            latitude: latitude,
            longtitude: longtitude,
            content: editor.getMarkdown()
        };

        if(data.title==null){
            alert("제목을 작성해야 합니다.");
            exit();
        }

        if(data.latitude==null){
            alert("위치를 지정해야 합니다.");
            exit();
        }

        $.ajax({
            type: "POST",
            url: "/api/posts",
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),

            success: function(postID){

                var files = $('#files')[0].files;
                var data = new FormData();
                for(let i=0; i<files.length; i++){
                    data.append('files', files[i]);
                }

                $.ajax({
                    type : 'POST',
                    enctype : 'multipart/form-data',
                    url : '/api/file/upload/'+ postID,
                    data : data,
                    processData : false, // 서버로 전송될때 기본적으로 url에 query형식으로 key=value로 날아가는데, 파일 업로드는 이러면 안되니까 false 명시
                    contentType : false, // 기본적으로 모든 문자들은 서버로 보내기전에 인코딩이 되는데, 파일이나 이미지를 전송할때는 인코딩하지 않아야하므로  false 명시
                    cache : false,
                    success : function (data) {
                    },
                    error : function (error) {
                        alert(JSON.stringify(error));
                    }
                });

                alert('글이 등록되었습니다.');
                window.location.href = "/boardV3";
            },
            error: function (error) {
                alert(JSON.stringify(error));
            }
        });
    },

    // file : function () {
    //
    //     var files = $('#files')[0].files;
    //     var data = new FormData();
    //     for(let i=0; i<files.length; i++){
    //         data.append('files', files[i]);
    //     }
    //
    //     $.ajax({
    //         type : 'POST',
    //         enctype : 'multipart/form-data',
    //         url : '/api/file/upload/'+postID,
    //         data : data,
    //         processData : false, // 서버로 전송될때 기본적으로 url에 query형식으로 key=value로 날아가는데, 파일 업로드는 이러면 안되니까 false 명시
    //         contentType : false, // 기본적으로 모든 문자들은 서버로 보내기전에 인코딩이 되는데, 파일이나 이미지를 전송할때는 인코딩하지 않아야하므로  false 명시
    //         cache : false,
    //         success : function (data) {
    //             alert(data);
    //             alert('postID: ' + postID);
    //         },
    //         error : function (error) {
    //             alert(JSON.stringify(error));
    //         }
    //     });
    // }
}

main.init();
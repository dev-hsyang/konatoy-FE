const Editor = toastui.Editor;
const view = Editor.factory({
    el: document.querySelector("#viewer"),
    viewer: true,
    height: '500px',
    initialValue: $('#content').val()
});

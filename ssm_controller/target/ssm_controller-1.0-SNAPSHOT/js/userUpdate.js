
function modify (userId) {
    var form = document.getElementById("update"+userId);
    var text = form.innerText;
    if(text==="修改"){
        document.getElementById("u"+userId).removeAttribute("disabled");
        document.getElementById("e"+userId).removeAttribute("disabled");
        document.getElementById("p"+userId).removeAttribute("disabled");
        document.getElementById("s"+userId).removeAttribute("disabled");

        document.getElementById("s"+userId).innerHTML="<option>开启</option><option>关闭</option>";

        //把修改按钮改成保存按钮
        document.getElementById("update"+userId).innerText="保存"
    }else if(text==="保存"){
        form.submit();
    }
}
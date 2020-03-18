//遮罩开启
function winOpenShade(){
    loadIndex =layer.load(2,{shade: [0.1, '#000'],shadeClose:false});
}
//遮罩关闭
function winCloseShade(){
    if(loadIndex != ""){
        layer.close(loadIndex);
        loadIndex = "";
    }else{
        layer.closeAll();
    }
}
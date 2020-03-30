
//身份证号
function checkCard(context){
    //var str = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
    //var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
    var reg = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/
    return reg.test(context);
}
//电话号码
function checkPhone(context){
    var str = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;

    return str.test(context);
}
//检查名称
function  checkName(context){
    var str = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
    return str.test(context)
}

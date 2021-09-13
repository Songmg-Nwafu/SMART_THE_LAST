function func(data,i){
   /* $("#img").attr({
        "src":data[i].pic.url,
        "usemap":"#"+data[i].pic.desc
    })*/
    $("#img").attr("src",data[i].pic.url);
    $("#img").attr("usemap","#"+data[i].pic.url);
}
function func(data,i){
	var len = $(data).length;
	var desc = data[i].pic.desc;
	var areas = data[i].pictures;
	var al = $(areas).length;
	$("#img").attr({
        "src":data[i].pic.url,
        "usemap":"#"+data[i].pic.desc
    });
	$("map").attr("name",data[i].pic.desc);

	for(var j = 0;j<al;j++){
		var x1  = areas[j].startX;
		var y1 = areas[j].startY;
		var x2 = areas[j].endX;
		var y2 = areas[j].endY;
		var cords = x1+","+y1+","+x2+","+y2;
		var sh = "<area shape = 'rect' coords='"+cords+"'></area>";
		$("map").append(sh);
	}
	var m = $("map");
	var a = $(m).children("area");
	console.log(m,a,a[1]);
//	为每一个area添加鼠标移入事件
	for(var k = 0;k<al;k++)
	{
		$(a[k]).mouseenter(function(k){
        return function (){
        	var tx = areas[k].endX;
        	var ty = areas[k].startY;
        	var svg = areas[k].information;
            $(".showTitle").css("display","block");
            $(".showTitle").css({
                left:tx+"px",
                top:ty+"px"
            });
            $(".showTitle").empty();
            $(".showTitle").append(svg);
		}
    	}(k));

	}

}

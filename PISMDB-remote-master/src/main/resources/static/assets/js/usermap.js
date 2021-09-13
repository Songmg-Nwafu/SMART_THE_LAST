function func(data,i){
	var len = $(data).length;
	var desc = data[i].pic.desc;
	var areas = data[i].pictures;
	var compoundGroup = data[i].compoundGroup;
	var proteinGroup = data[i].proteinGroup;
	var al = $(areas).length;

	function navlist(list){

		var content = "<a>Compounds</a><ul>";
		for(var i = 0;i<list.length;i++){

			let groupName = list[i].groupName;
			if (groupName != null) {
				groupName = groupName.replace(/\s/g, "");
				groupName = groupName.replace(/,/g, "");
			}
			console.log("groupName is " + groupName + " type: " + typeof groupName);
			content+="<li><a href='#' id='"+ groupName +"' class='group-title'>"+list[i].groupName+"</a>";
			if(list[i].id!=null){
				content+="<ul>";
				for(var j = 0;j<list[i].id.length;j++){
					/*content+="<li><a href='/cdetail?pismid=PISM00010'+list[i].id[j]>"+list[i].id[j]+"</a></li>"//*/
					let url = "cdetail?pismid=" + list[i].id[j];
					//console.log(url);
					content += "<li><a href='"+url+"'>"+list[i].id[j]+"</a></li>";//修改1,原代码字符串拼接错误
				}
				content+="</ul>";
			}
			content+="</li>";
		}
		content+="</ul>";
		return content;
	}

	$("#img").attr({
		"src":data[i].pic.url,
		"usemap":"#"+data[i].pic.desc
	});
	let content = navlist(compoundGroup);
	console.log("content: " + content);
	$("#compounds").empty();//修改2，原代码点击go之后，不会清除原来compounds目录下的内容，导致点击go后。目录会被不断添加
	$("#compounds").append(content);
	$("map").attr("name",data[i].pic.desc);

	for(var j = 0;j<al;j++){
		var x1  = areas[j].startX;
		var y1 = areas[j].startY;
		var x2 = areas[j].endX;
		var y2 = areas[j].endY;
		var cords = x1+","+y1+","+x2+","+y2;
		var sh = "<area shape = 'rect' coords='"+cords+"' />";
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
				let groupName = areas[k].groupName;
				if (groupName != null) {
					groupName = groupName.replace(/\s/g, "");
					groupName = groupName.replace(/,/g, "");
				}
				// var groupName = areas[k].pathwayId;
				// var svg = areas[k].information||'文字说明';
				var type = areas[k].type;//当前类型
				if(type === "M"){
					$("#compounds>ul").css("display","block");
					console.log($("#"+groupName),"后一个元素");
					$(".group-title").next("ul").css("display", "none");//修改3, 在打开被选中的属性组之前，先关闭所有的属性组
					console.log("groupName is " + groupName);
					let chooseId = "#" + groupName;
					console.log("id is " + chooseId);
					$(chooseId).next("ul").css("display","block");

				}else if(type === "p"){
					$("#proteinGroup").css("display","block");
				}
				var molecularPismid = areas[k].molecularPISMID;
				var pathwayId = areas[k].pathwayID;
				console.log(type ,molecularPismid,pathwayId,"数据")
				console.log("type is " + type + "," + type.length);
				// $(".showTitle").css("display","block");
				// $(".showTitle").css({
				//     left:tx+"px",
				//     top:ty+"px"
				// });
				// $(".showTitle").empty();
				// $(".showTitle").append(pathwayId);
			}
		}(k));
	}
}
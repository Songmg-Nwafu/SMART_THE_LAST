function page_ctrl2(data_obj,data) {
    // 获取操作的对象
    var obj_box=(data_obj.obj_box!== undefined)?data_obj.obj_box:function () {
        alert("amxmdmd");
        return;
    };

    //翻页容器dom对象,必要参数
    var total_item=(data.length!== undefined)?parseInt(Object.keys(data).length):0;//数据条目总数,默认为0,组件将不加载
    console.log(total_item);

    var per_num=(data_obj.per_num!== undefined)?parseInt(data_obj.per_num):20;//每页显示条数,默认为10条

    var current_page=(data_obj.current_page!== undefined)?parseInt(data_obj.current_page):1;//当前页,默认为1

    var total_page=Math.ceil(total_item/per_num);//计算总页数,不足2页,不加载组件
    console.log(total_page);

    if(total_page<1){

        //在指定容器内加载分页数据

        $(obj_box).append('<div class="pageContent"></div>');

    }else{
         //在指定容器内加载分页插件

        $(obj_box).append('<div class="page_ctrl"></div>');

        //在指定容器内加载分页数据

        $(obj_box).append('<div class="pageContent"></div>');


    }

    function page_even() {
        console.log(current_page,total_page,"page_even中的当前页数和总页数")
        
        let hasItem = total_item-(current_page-1)*20;
        
        if(current_page*20>hasItem){
            per_num = hasItem;
        }
        console.log(per_num,"页面总条数per_num")

        /*加载数据*/
        function change_content(){
            var pageContent='<ul id="contentList">';
            for(var i = 0;i<per_num;i++)//每页显示的页数。如何调整每页显示的内容刚好对应相应的俄页面
            {
                console.log("当前为便利值为"+per_num*(current_page)+i);
                console.log("当前循环中的per_num和current_page",per_num,current_page);
                pageContent+='<li>';
                pageContent+='<div class="item item1"><p class="Compound-id">ID: <a href="./detail.html?id='+data[per_num*(current_page-1)+i].id+'" id="PISMID">'+data[per_num*(current_page-1)+i].id+'</a></p></div>';
                pageContent+='<div class="item item2"><p><a href="./detail.html?id='+data[per_num*(current_page-1)+i].id+'"id="Chemical-name">'+data[per_num*(current_page-1)+i].name+'</a></p><div class="chemical-member"><ul><li><p id="basic">Basic Information</p></li><li><p id="pathway">Pathway</p></li><li><p id="related">Related Compounds</p></li><li><p id="supporting">Supporting Information</p></li></ul></div></div>';
                pageContent+='<div class="item item3"><ul><li>Protein <span id="IUPAC-name">'+data[per_num*(current_page-1)+i].basic.proteinName+'</span></li><li>Organism <span id="Chemical-Formula">'+data[per_num*(current_page-1)+i].basic.pismid+'</span></li><li>Uniprot ID <span id="SMILES">'+data[per_num*(current_page-1)+i].basic.uniprotID+'</span></li><li>targetID <span id="ClogP">'+data[per_num*(current_page-1)+i].basic.targetID+'</span></li><li>uniprotID<span id="MV">'+data[per_num*(current_page-1)+i].basic.uniprotID+'</span></li></ul></div>';

                // pageContent+='<div class="item item4"><div class="chemical-img"><img src="'+data[per_num*(current_page-1)+i].imgurl+'" alt=""></div></div>';
                pageContent+='</li>';
            }
            pageContent+='</ul>';
            $(obj_box).children('.pageContent').html(pageContent);

        }
        change_content();
        var append_html='<button class="first_page"><i class="fa fa-angle-double-left fa-lg" ></i></button>';
        append_html+='<button class="prev_page"><i class="fa fa-angle-left fa-lg" ></i></button>'
        for(var i=0;i<total_page-1;i++){
            if(total_page>8&&current_page>6&&i<current_page-3){
                if(i<2){
                    append_html+='<button class="page_num">'+(i+1)+'</button>';
                }
                else if(i==2){
                    append_html+='<span class="page_dot">···</span>';
                }
            }

            else if(total_page>8&&current_page<total_page-3&&i>current_page+1){
                if(current_page>6&&i==current_page+2){
                    append_html+='<span class="page_dot">···</span>';
                }else if(current_page<7){
                    if(i<8){
                        append_html+='<button class="page_num">'+(i+1)+'</button>';
                    }else if(i==8){
                        append_html+='<span class="page_dot">···</span>';
                    }
                }
                //append_html+='<span class="page_dot">···</span>';
            }

            else{
                if(i==current_page-1){
                    append_html+='<button class="page_num current_page">'+(i+1)+'</button>';
                }

                else{
                    append_html+='<button class="page_num">'+(i+1)+'</button>';
                }
            }
        }

        if(current_page==total_page){
            append_html+='<button class="page_num current_page">'+(i+1)+'</button>';
        }else{
            append_html+='<button class="page_num">'+(i+1)+'</button>';
        }

        append_html+='<button class="next_page"><i class="fa fa-angle-right fa-lg"></i></button>';
        append_html+='<button class="last_page"><i class="fa fa-angle-double-right fa-lg"></i></button>';
        $(obj_box).children('.page_ctrl').append(append_html);
        if(current_page==1){
            $(obj_box+' .page_ctrl .prev_page').attr('disabled','disabled').addClass('btn_dis');
            $(obj_box+' .page_ctrl .first_page').attr('disabled','disabled').addClass('btn_dis');
        }else{
            $(obj_box+' .page_ctrl .prev_page').removeAttr('disabled').removeClass('btn_dis');
            $(obj_box+' .page_ctrl .first_page').removeAttr('disabled').removeClass('btn_dis');
        }

        if(current_page==total_page){
            $(obj_box+' .page_ctrl .last_page').attr('disabled','disabled').addClass('btn_dis');
            $(obj_box+' .page_ctrl .next_page').attr('disabled','disabled').addClass('btn_dis');
        }else{
            $(obj_box+' .page_ctrl .next_page').removeAttr('disabled').removeClass('btn_dis');
            $(obj_box+' .page_ctrl .last_page').removeAttr('disabled').removeClass('btn_dis');
        }
    }

    page_even();
    $(obj_box+' .page_ctrl').on('click','button',function () {
        var that=$(this);
        if(that.hasClass('prev_page')){
            if(current_page!=1){
                current_page--;
                that.parent('.page_ctrl').html('');
                page_even();
                others(data);
            }
        }

        else if(that.hasClass('next_page')){
            if(current_page!=total_page){
                current_page++;
                that.parent('.page_ctrl').html('');
                page_even();
                others(data);
            }
        }
        else if(that.hasClass('first_page')){
            if(current_page!=1){
                current_page=1;
                that.parent('.page_ctrl').html('');
                page_even();
                others(data);
            }
        }
        else if(that.hasClass('last_page')){
            if(current_page!=total_page){
                current_page=total_page;
                console.log(current_page,total_page);
                that.parent('.page_ctrl').html('');
                page_even();
                others(data);
            }
        }
        else if(that.hasClass('page_num')&&!that.hasClass('current_page')){
            current_page=parseInt(that.html());
            that.parent('.page_ctrl').html('');
            page_even();
            others(data);
        }

        else if(that.hasClass('to_page_num')){
            current_page=parseInt(that.siblings('.input_page_num').val());
            that.parent('.page_ctrl').html('');
            page_even();
            others(data);
        }

         console.log(current_page,total_page,"点击事件中的当前页数，总页数")
    });

    function others(data){
        $(".chemical-member").parent().parent().mouseover(function(){
            $(this).css("cursor","pointer");});
        var b = $(".chemical-member");

        $.each(data,function(index,items){
            $(b[index]).find("li:first-child").children("p").after("<div class='current'></div>");
            $(b[index]).children("ul").children("li").click(function(){
                if($(this).children().is(".current")){}
                else{
                    $(this)
                        .children("p")
                        .after("<div class='current'></div>")
                        .parent("li")
                        .siblings("li")
                        .children(".current")
                        .remove();
                    // console.log($(this).)
                }

            });
            $(b[index]).find("li:nth-child(1)").click(function(){
                $(b[index]).parent().next().empty().append('<ul><li>Protein<span id="IUPAC_Name">'+items.basic.proteinName+'</span></li><li>Organism<span id="ChemicalFormular">'+items.basic.pismid+'</span></li><li>Uniport ID<span id="AlogP">'+items.basic.uniprotID+'</span></li><li>Gene <span id="Smiles">'+items.basic.targetID+'</span></li><li>Function<span id="MV"></span></li></ul>');

            });
            $(b[index]).find("li:nth-child(2)").click(function(){
                $(b[index]).parent().next().empty().append('<ul><li>cell wall formation <span id="IUPAC-name">'+items.pathway.pathwayName+'</span></li><li>Chemical Formula  <span id="Chemical-Formula">'+items.pathway.pISMID+'</span></li></ul>');
            });
            $(b[index]).find("li:nth-child(3)").click(function(){
                var a = "<ul>";
                for(var i = 0;i<items.related.compoundsList.length;i++)
                {
                    a+='<li>'+items.related.compoundsList[i]+'</li>'

                }
                a+="</ul>";

                $(b[index]).parent().next().empty().append(a);
            });
            $(b[index]).find("li:nth-child(4)").click(function(){
                $(b[index]).parent().next().empty().append('<ul><li>Function <span id="Function">'+items.supporting.function+'</span></li><li>Mechanism <span id="Mechanism">'+items.supporting.mechanism+'</span></li><li>Phenotype<span id="Phenotype">'+items.supporting.phenotype+'</span></li></ul>');
            });
            $(".item3").find("span").mouseover(function(){
                this.title = this.innerHTML;
            })

        });
    }
    others(data);

}

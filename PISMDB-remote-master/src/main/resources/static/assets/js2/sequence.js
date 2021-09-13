function page_ctrl2(data_obj,data) {
    // 获取操作的对象
    var obj_box=(data_obj.obj_box!== undefined)?data_obj.obj_box:function () {
        alert("amxmdmd");
        return;
    };

    //翻页容器dom对象,必要参数
    var total_item=(data.length!== undefined)?parseInt(Object.keys(data).length):0;//数据条目总数,默认为0,组件将不加载
    console.log(total_item);



    function page_even() {

        /*加载数据*/
        function change_content(){
            var pageContent = '<p>You can download the <a  target="_blank" href="./static/tmpfile/'+data[0].filepath+'">detail</a></p>';
            pageContent +='<ul id="contentList">';
            for(var i = 0;i<total_item;i++)//每页显示的页数。如何调整每页显示的内容刚好对应相应的俄页面
            {

                pageContent+='<li>';
                pageContent+='<div class="item item1"><p class="Compound-id">ID: <a href="#">'+data[i].targetID+'</a></p></div>';
                pageContent+='<div class="item item2"><p><a id="Chemical-name">'+data[i].proteinName+'</a></p><div class="chemical-member"><ul><li><p id="basic">Blastp Information</p></li><li><p id="pathway">Basic Information</p></li></ul></div></div>';
                pageContent+='<div class="item item3"><ul><li>Bitscore <span id="IUPAC-name">'+data[i].bitscore+'</span></li><li>Evalue <span id="Chemical-Formula">'+data[i].evalue+'</span></li><li>Qcovs <span id="SMILES">'+data[i].qcovs+'%</span></li><li>Ident <span id="ClogP">'+data[i].ident+'%</span></li></ul></div>';
                // pageContent+='<div class="item item4"><div class="chemical-img"><img src="'+data[per_num*(current_page-1)+i].imgurl+'" alt=""></div></div>';
                pageContent+='</li>';
            }
            pageContent+='</ul>';
            $(obj_box).children('.pageContent').html(pageContent);

        }
        change_content();
    }

    page_even();

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
                $(b[index]).parent().next().empty().append('<ul><li>Bitscore <span id="IUPAC-name">'+items.bitscore+'</span></li><li>Evalue <span id="Chemical-Formula">'+items.evalue+'</span></li><li>Qcovs <span id="SMILES">'+items.qcovs+'%</span></li><li>Ident <span id="ClogP">'+items.ident+'%</span></li></ul>');

            });
            $(b[index]).find("li:nth-child(2)").click(function(){
                $(b[index]).parent().next().empty().append('<ul><li>UniprotID <span id="IUPAC-name">'+items.uniprotID+'</span></li><li>Sequence <span id="Chemical-Formula"><a href="./static/fasta/'+items.sequencel+'">'+items.sequencel+'</a></span></li><li>PDB ID <span id="Chemical-Formula">'+items.pbid+'</span></li></ul>');
            });
            $(".item3").find("span").mouseover(function(){
                this.title = this.innerHTML;
            })

        });
    }
    others(data);

}

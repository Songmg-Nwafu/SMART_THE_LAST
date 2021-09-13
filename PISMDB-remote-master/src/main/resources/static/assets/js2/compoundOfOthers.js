function page_ctrl2(data_obj,data) {
    // 获取操作的对象
    var obj_box=(data_obj.obj_box!== undefined)?data_obj.obj_box:function () {
        alert("amxmdmd");
        return;
    };


    //翻页容器dom对象,必要参数
    var total_item=(data_obj.length!== undefined)?parseInt(data_obj.length):0;//数据条目总数,默认为0,组件将不加载
    console.log(total_item);

    var per_num=(data_obj.per_num!== undefined)?parseInt(data_obj.per_num):20;//每页显示条数,默认为20条

    var current_page=(data_obj.current_page!== undefined)?parseInt(data_obj.current_page):1;//当前页,默认为1

    var total_page=Math.ceil(total_item/per_num);//计算总页数,不足2页,不加载组件
    console.log(total_page);


    //$(obj_box).append('<div class="pageContent"></div>');
    $(obj_box).children('.pageContent').empty().html();

    function page_even() {
        console.log(current_page,total_page,"page_even中的当前页数和总页数")
        
        let hasItem = total_item-(current_page-1)*per_num;
        
        if(per_num>hasItem){
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
                //var smileslink = data[i].basic.smiles.replace(/=/g,"%");
                var smileslink = data[i].basic.smiles;
                var chemspider;
                var pubchem;
                if (data[i].compoundDataSource.chemSpider == undefined){
                    chemspider = "Query in ChemSpider";
                } else {
                    chemspider = data[i].compoundDataSource.chemSpider;
                }
                var chemarr = chemspider.split('%%')
                var chemitem = '<li>ChemSpider <span id="ChemSpider">'
                for (var j = 0; j < chemarr.length; j++){
                    chemitem += '<a target="_blank" href="http://www.chemspider.com/Search.aspx?q='+smileslink+'">'+chemarr[j]+'</a>&nbsp;&nbsp;'
                }
                chemitem+='</span></li>'
                if (data[i].compoundDataSource.pubChem == undefined){
                    pubchem = "Query in PubChem";
                } else {
                    pubchem = data[i].compoundDataSource.pubChem;
                }
                pageContent+='<li>';
                pageContent+='<div class="item item1"><p class="Compound-id"><a href="cdetail?id='+data[i].id+'" id="PISMID">'+data[i].id+'</a></p></div>';

                pageContent+='<div class="item item2"><p><a href="cdetail?id='+data[i].id+'"id="Chemical-name">'+data[i].name+'</a></p><div class="chemical-member"><ul><li><p id="basic">Basic Information</p></li><li><p id="supporting">Supporting Information</p></li></ul></div></div>';
                pageContent+='<div class="item item3"><ul><li>IUPAC Name <span id="IUPAC-name">'+data[i].basic.iupacName+'</span></li><li>Chemical Formula <span id="Chemical-Formula">'+data[i].basic.chemicalFormular+'</span></li><li>SMILES <span id="SMILES">'+data[i].basic.smiles+'<a target="_blank" href="http://www.chemspider.com/Search.aspx?q='+smileslink+'">'+"&nbsp;&nbsp;&nbsp;&nbsp;ChemSpider"+'</a><a target="_blank" href="https://pubchem.ncbi.nlm.nih.gov/#query='+smileslink+'">'+"&nbsp;&nbsp;&nbsp;&nbsp;PubChem"+'</a></span></li><li>3D structure file <span id="3D structure file"><a href="./static/jsmol/data/mol2/PIS'+data[i].id+".mol2"+'">'+data[i].id+".mol2"+'</a></span></li><li>Alog P <span id="ClogP">'+data[i].basic.alogP+'</span></li><li>MW <span id="MW">'+data[i].basic.molecularWeight+'</span></li>'+chemitem+'<li>PubChem <span id="PubChem"><a target="_blank" href="https://pubchem.ncbi.nlm.nih.gov/#query='+smileslink+'">'+pubchem+'</a></span></li></ul></div>';

                pageContent+='<div class="item item4"><div class="chemical-img"><img src="'+data[i].imgurl+'" alt=""></div></div>';
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
                //var smileslink = items.basic.smiles.replace(/=/g,"%");
                var smileslink = items.basic.smiles;
                var chemspider;
                var pubchem;
                if (items.compoundDataSource.chemSpider == undefined){
                    chemspider = "Query in ChemSpider";
                } else {
                    chemspider = items.compoundDataSource.chemSpider;
                }
                var chemarr = chemspider.split('%%')
                var chemitem = '<li>ChemSpider <span id="ChemSpider">'
                for (var j = 0; j < chemarr.length; j++){
                    chemitem += '<a target="_blank" href="http://www.chemspider.com/Search.aspx?q='+smileslink+'">'+chemarr[j]+'</a>&nbsp;&nbsp;'
                }
                chemitem+='</span></li>'
                if (items.compoundDataSource.pubChem == undefined){
                    pubchem = "Query in PubChem";
                } else {
                    pubchem = items.compoundDataSource.pubChem;
                }
                $(b[index]).parent().next().empty().append('<ul><li>IUPAC Name <span id="IUPAC_Name">'+items.basic.iupacName+'</span></li><li>Chemical Formular <span id="ChemicalFormular">'+items.basic.chemicalFormular+'</span></li><li>SMILES <span id="Smiles">'+items.basic.smiles+'</span></li><li>3D structure file <span id="3D structure file"><a href="./static/jsmol/data/mol2/PIS'+items.id+".mol2"+'">'+items.id+".mol2"+'</a></span></li><li>Alog P <span id="AlogP">'+items.basic.alogP+'</span></li><li>MW <span id="MV">'+items.basic.molecularWeight+'</span></li>'+chemitem+'<li>PubChem <span id="PubChem"><a target="_blank" href="https://pubchem.ncbi.nlm.nih.gov/#query='+smileslink+'">'+pubchem+'</a></span></li></ul>');

            });

            $(b[index]).find("li:nth-child(2)").click(function(){
                var myfunction =items.supporting.function.charAt(0).toUpperCase() + items.supporting.function.slice(1).toLowerCase();
                var mymechanism =items.supporting.mechanism.charAt(0).toUpperCase() + items.supporting.mechanism.slice(1).toLowerCase();
                var myphenotype =items.supporting.phenotype.charAt(0).toUpperCase() + items.supporting.phenotype.slice(1).toLowerCase();
                $(b[index]).parent().next().empty().append('<ul><li>Function <span id="Function">'+myfunction+'</span></li><li>Mechanism <span id="Mechanism">'+mymechanism+'</span></li><li>Phenotype <span id="Phenotype">'+myphenotype+'</span></li></ul>');
            });
            /*$(b[index]).find("li:nth-child(3)").click(function(){
                var smileslink = items.basic.smiles;
                var chemspider;
                var pubchem;
                if (items.compoundDataSource.chemSpider == undefined){
                    chemspider = "Query in ChemSpider";
                } else {
                    chemspider = "ChemSpider:"+items.compoundDataSource.chemSpider;
                }
                if (items.compoundDataSource.pubChem == undefined){
                    pubchem = "Query in PubChem";
                } else {
                    pubchem = "PubChem:"+items.compoundDataSource.pubChem;
                }
                var string = '<ul>';
                string += '<li>ChemSpider <span id="chemspider"><a target="_blank" href="http://www.chemspider.com/Search.aspx?q='+smileslink+'">'+chemspider+'</a></span></li>';
                string += '<li>PubChem <span id="pubchem"><a target="_blank" href="https://pubchem.ncbi.nlm.nih.gov/#query='+smileslink+'">'+pubchem+'</a></span></li>';
                string += '</ul>';
                $(b[index]).parent().next().empty().append(string);
            });*/
            $(".item3").find("span").mouseover(function(){
                this.title = this.innerHTML;
            })

        });
    }
    others(data);

}

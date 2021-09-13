function page_c(data_obj,data) {
    // 获取操作的对象
    var obj_box=(data_obj.obj_box!== undefined)?data_obj.obj_box:function () {
        return;
    };


    $(obj_box).children('.pageContent').empty().html();

    function page_even() {

        /*加载数据*/
        function change_content(){

            var pageContent='<ul id="contentList">';
            for(var i = 0;i< data.length ;i++)//每页显示的页数。如何调整每页显示的内容刚好对应相应的俄页面
            {
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
                pageContent+='<div class="item item2"><p><a href="cdetail?id='+data[i].id+'"id="Chemical-name">'+data[i].basic.chemicalNames+'</a></p><div class="chemical-member"><ul><li><p id="basic">Basic Information</p></li><li><p id="pathway">Targeting Pathway</p></li><li><p id="target">Targeting Protein</p></li><li><p id="related">Related Molecules</p></li><li><p id="supporting">Supporting Information</p></li></ul></div></div>';
                pageContent+='<div class="item item3"><ul><li>IUPAC Name <span id="IUPAC-name">'+data[i].basic.iUPAC_Name+'</span></li><li>Chemical Formula <span id="Chemical-Formula">'+data[i].basic.chemicalFormular+'</span></li><li>SMILES <span id="SMILES">'+data[i].basic.smiles+'</span></li><li>3D structure file <span id="3D structure file"><a href="./static/jsmol/data/mol2/'+data[i].basic.threeD_Structure+'">'+data[i].basic.threeD_Structure.substr(3)+'</a></span></li><li>Alog P <span id="ClogP">'+data[i].basic.alogP+'</span></li><li>MW <span id="MW">'+data[i].basic.molecularWeight+'</span></li>'+chemitem+'<li>PubChem <span id="PubChem"><a target="_blank" href="https://pubchem.ncbi.nlm.nih.gov/#query='+smileslink+'">'+pubchem+'</a></span></li></ul></div>';

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
                for (var i = 0; i < chemarr.length; i++){
                    chemitem += '<a target="_blank" href="http://www.chemspider.com/Search.aspx?q='+smileslink+'">'+chemarr[i]+'</a>&nbsp;&nbsp;'
                }
                chemitem+='</span></li>'
                if (items.compoundDataSource.pubChem == undefined){
                    pubchem = "Query in PubChem";
                } else {
                    pubchem = items.compoundDataSource.pubChem;
                }
                $(b[index]).parent().next().empty().append('<ul><li>IUPAC Name <span id="IUPAC_Name">'+items.basic.iUPAC_Name+'</span></li><li>Chemical Formular <span id="ChemicalFormular">'+items.basic.chemicalFormular+'</span></li><li>SMILES <span id="Smiles">'+items.basic.smiles+'</span></li><li>3D structure file <span id="3D structure file"><a href="./static/jsmol/data/mol2/'+items.basic.threeD_Structure+'">'+items.basic.threeD_Structure.substr(3)+'</a></span></li><li>Alog P <span id="AlogP">'+items.basic.alogP+'</span></li><li>MW <span id="MV">'+items.basic.molecularWeight+'</span></li>'+chemitem+'<li>PubChem <span id="PubChem"><a target="_blank" href="https://pubchem.ncbi.nlm.nih.gov/#query='+smileslink+'">'+pubchem+'</a></span></li></ul>');

            });
            $(b[index]).find("li:nth-child(2)").click(function(){
                var a = "<ul>";
                for(var i = 0; i < items.pathways.pathwaysList.length; i++){
                    a+='<li><a href="pdetail?id='+items.pathways.pathwaysList[i].pathwayID+'">'+items.pathways.pathwaysList[i].pathwayID+'&nbsp;&nbsp;'+items.pathways.pathwaysList[i].pathwayName+'</a></li>'
                }
                a+="</ul>";
                $(b[index]).parent().next().empty().append(a);
            });
            $(b[index]).find("li:nth-child(3)").click(function(){
                var a = "<ul>";
                for(var i = 0; i < items.compoundTargets.targetsList.length; i++){
                    a+='<li><a href="tdetail?id='+items.compoundTargets.targetsList[i].targetID+'">'+items.compoundTargets.targetsList[i].targetID+'&nbsp;&nbsp;'+items.compoundTargets.targetsList[i].proteinName+'</a></li>'
                }
                a+="</ul>";
                $(b[index]).parent().next().empty().append(a);
            });
            $(b[index]).find("li:nth-child(4)").click(function(){
                var a = "<ul>";
                for(var i = 0;i<items.relatedCompounds.compoundsList.length;i++)
                {
                    a+='<li><a href="cdetail?id='+items.relatedCompounds.compoundsList[i].pismid+'">'+items.relatedCompounds.compoundsList[i].pismid+'&nbsp;&nbsp;'+items.relatedCompounds.compoundsList[i].chemicalnames+'</a></li>'
                }
                a+="</ul>";
                $(b[index]).parent().next().empty().append(a);
            });
            $(b[index]).find("li:nth-child(5)").click(function(){
                var string = '<ul>';
                if (items.supporting.function != undefined){
                    var myfunction =items.supporting.function.charAt(0).toUpperCase() + items.supporting.function.slice(1).toLowerCase();
                    string += '<li>Function <span id="Function">'+myfunction+'</span></li>';
                }
                if (items.supporting.mechanism != undefined){
                    var mymechanism =items.supporting.mechanism.charAt(0).toUpperCase() + items.supporting.mechanism.slice(1).toLowerCase();
                    string += '<li>Mechanism <span id="Mechanism">'+mymechanism+'</span></li>';
                }
                if (items.supporting.phenotype != undefined){
                    var myphenotype =items.supporting.phenotype.charAt(0).toUpperCase() + items.supporting.phenotype.slice(1).toLowerCase();
                    string += '<li>Phenotype <span id="Phenotype">'+myphenotype+'</span></li>';
                }
                string += '</ul>';
                $(b[index]).parent().next().empty().append(string);
            });
            /*$(b[index]).find("li:nth-child(6)").click(function(){
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
            });
            $(".item3").find("span").mouseover(function(){
                this.title = this.innerHTML;
            })*/
            $(".item3").find("span").mouseover(function(){
                this.title = this.innerHTML;
            })

        });
    }
    others(data);

}

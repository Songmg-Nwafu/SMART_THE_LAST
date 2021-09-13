function exeData(num, type) {
    loadData(num);
    loadpage();
}
function loadpage() {
    var myPageCount = parseInt($("#PageCount").val());
    var myPageSize = parseInt($("#PageSize").val());
    var countindex = myPageCount % myPageSize > 0 ? (myPageCount / myPageSize) + 1 : (myPageCount / myPageSize);
    $("#countindex").val(countindex);

    $("#pagination").jqPaginator({
        totalPages: parseInt($("#countindex").val()),
        visiblePages: parseInt($("#visiblePages").val()),
        currentPage: 1,
        first: '<li class="first"><a href="javascript:;"><i class="fa fa-angle-double-left fa-lg" style="font-weight:bolder;"></i></a></li>',
        prev: '<li class="prev"><a href="javascript:;"><i class="fa fa-angle-left fa-lg" style="font-weight:bolder"></i></a></li>',
        next: '<li class="next"><a href="javascript:;"><i class="fa fa-angle-right fa-lg" style="font-weight:bolder"></i></a></li>',
        last: '<li class="last"><a href="javascript:;"><i class="fa fa-angle-double-right fa-lg" style="font-weight:bolder"></i></a></li>',
        page: '<li class="page"><a href="javascript:;" style="font-weight:bolder">{{page}}</a></li>',
        onPageChange: function (num, type) {
            if (type == "change") {
                exeData(num, type);
            }
        }
    });
}
$(function () {
    loadData(1);
    loadpage();
});
/*
* 实现方式，当点击当前页面时显示当前条中的后面的数据
*当有无数条数据时对于所有的数据都适合这个操作
**/
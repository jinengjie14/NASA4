$(document).ready(function () {
    $("#onlick").click(function () {
        $("#onlick").hide();
        $("#block").removeClass("display");
    });
    $("#none").click(function () {
        $("#block").addClass("display");
        $("#onlick").show();
    });
    $("#dispaly_a a").click(function(){
        $('html,body').animate({scrollTop:0},'slow');
    });
    $("#reply").click(function(){
         
    });
});
$(window).scroll(function () {
    if ($(window).scrollTop() > 50) {
        $("#display").removeClass("display");
    } else {
        $("#display").addClass("display");
    }
});
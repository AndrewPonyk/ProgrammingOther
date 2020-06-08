console.log(window);

if($){
    var arrData = [];

$(document).keydown(function(e){
  if( e.which === 89 && e.ctrlKey ){
     alert('control + y');
	 
  }
  else if( e.which === 90 && e.ctrlKey ){
	//z
     DoSaveForm(window.location.href, 'z_stake', 'isStake=1');
  }          
});

$("input[name='sums']").val("900");

if(localStorage['sum']){
 $("input[name='sums']").val(localStorage['sum']);
}

arrData.push("isStake=1");
    var fild = $("#z_stake").find("input, select, textarea");
    fild.each(function (index, element){
        if ($(this).attr('type') != 'button') {
            if 	(($(this).attr('type')=="radio"||$(this).attr('type')=="checkbox")&& !$(this).attr('checked')){
            }else
            {
                var link_name = $(this).attr('name');
                var link_val = $(this).val();
                arrData.push(link_name + "=" + encodeURIComponent(link_val));
            }
        }
    });
    window.strData = arrData.join("&");
   console.log(window.strData);
}

function DoSaveForm(FUrl, FName, FParam, FCallback, ShowWait) {
	console.log(FUrl);
    $.ajax({
        type: 'POST',
        url: FUrl,
        data: window.strData,      
        success: function (data) {
            $("#z_stake").html(data);
            if(typeof(FCallback) === "function"){
                FCallback();
            }
        }
    });
}

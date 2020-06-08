console.log("THis is 'run on every page extension2'" + window);

document.body.onmousedown = function (e) {
    console.log("mouse clicked" + e);
    var coef = parseFloat(document.querySelector(".bss-NormalBetItem_OddsContainer ").innerText);
    console.log(coef)
    console.log('try to be');
    var element = document.querySelector(".bss-StakeBox_StakeValueInput");
    console.log(element);
    element.dispatchEvent(new KeyboardEvent('keypress', {'key': '1'}));
    element.dispatchEvent(new KeyboardEvent('keydown', {'key': '1'}));
    element.dispatchEvent(new KeyboardEvent('keyup', {'key': '1'}));
    console.log(element);
}

document.body.onkeydown = function (e) {
    console.log(e.key + e.target);
    if (e.key === "Control") {
        var coef = parseFloat(document.querySelector(".bss-NormalBetItem_OddsContainer ").innerText);
        console.log(coef)
        if (true) {
            console.log('try to be');
            var element = document.querySelector(".bss-StakeBox_StakeValue ");
            console.log(element);

            //element.dispatchEvent(new KeyboardEvent('keypress',{'key':'1'}));
            element.dispatchEvent(new KeyboardEvent('keydown', {'key': '1'}));
            element.dispatchEvent(new KeyboardEvent('keyup', {'key': '1'}));
        }
    }

};


if (window.$) {
    var arrData = [];


    $(document).keydown(function (e) {
        if (e.which === 89 && e.ctrlKey) {
            debugger;
            alert('control + y');
        }

            // if( e.which === 89 && e.ctrlKey ){
            //     alert('control + y');
        // }


        else if (e.which === 90 && e.ctrlKey) {
            //z
            DoSaveForm(window.location.href, 'z_stake', 'isStake=1');
        }
    });

    $("input[name='sums']").val("900");

    if (localStorage['sum']) {
        $("input[name='sums']").val(localStorage['sum']);
    }

    arrData.push("isStake=1");
    var fild = $("#z_stake").find("input, select, textarea");
    fild.each(function (index, element) {
        if ($(this).attr('type') != 'button') {
            if (($(this).attr('type') == "radio" || $(this).attr('type') == "checkbox") && !$(this).attr('checked')) {
            } else {
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
            if (typeof (FCallback) === "function") {
                FCallback();
            }
        }
    });
}

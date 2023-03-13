console.log("THis is 'run on every page extension22'" + window);
console.log("football in local storage" + localStorage["football"]);
console.log("table tennis in local storage" + localStorage["table-tennis"]);

var footballHttp = "https://jsonbox.io/box_a689710efb7c6c3a93bb/5fcd5a6a8abbd4001773c598";
var tennisHttp = "https://jsonbox.io/box_a689710efb7c6c3a93bb/5fcfe96bbdbb73001796890c";

var footballHttpV2 = "https://jsonstorage.net/api/items/bed7ca77-a12d-403c-b5c8-09ca3a04d4e6";
var tennisHttpV2 = "https://jsonstorage.net/api/items/15d42a34-4677-4cfb-8a9b-c433669d0ddc";

if (location.href.indexOf("B92") < 0) {
    if (localStorage["football"] && diffSeconds(new Date(), new Date(localStorage["football"])) < 0) {
        console.log("football storage:" + localStorage["football"] + "IS UP TO DATE CONTINUE MONITORING");
    } else {
        if (location.href.indexOf("bet365") > 0) {
            console.log("footbal storage is empty or outdated start monitoring");
            setTimeout(function () {
                localStorage["football"] = null;
                if (location.href.indexOf("bet365") > 0) {
                    location.href = "https://www.bet365.com/#/IP/B1";
                }
            }, 3000000);

            setInterval(function () {
                sendFootball();
            }, 4000);
        }
    }
}

if (location.href.indexOf("B92") > 0) {
    if (localStorage["table-tennis"] && diffSeconds(new Date(), new Date(localStorage["table-tennis"])) < 0) {
        console.log("table-tennis storage:" + localStorage["table-tennis"] + " continue-monitoring");
    } else {
        console.log("table-tennis storage is empty or outdated - start monitoring")
        if (location.href.indexOf("bet365") > 0) {
            setTimeout(function () {
                localStorage["table-tennis"] = null;
                if (location.href.indexOf("B92") > 0) {
                    location.href = "https://www.bet365.com/#/IP/B92";
                }
            }, 3000000);
            setInterval(function () {
                sendTableTennis();
            }, 4000);
        }
    }
}

//TODO: fix
function sendTableTennis() {
    var resultArray = [];
    console.log("Start:::: sending table")
    var titles = document.querySelectorAll(".rcl-ParticipantFixtureDetails.gl-Market_General-cn1");
    var coefs1 = document.querySelectorAll(".gl-MarketGroupContainer > div:nth-child(2) .sgl-ParticipantOddsOnly80");
    var coefs2 = document.querySelectorAll(".gl-MarketGroupContainer > div:nth-child(3) .sgl-ParticipantOddsOnly80");

    for (var i = 0; i < titles.length; i++) {
        var titleElems = titles[i].querySelectorAll(".rcl-ParticipantFixtureDetails_TeamNames");
        var scoreElem = titles[i].querySelectorAll(".pi-ScoreVariantInColumnsWithSets");
        var score = scoreElem.length == 1 ? scoreElem[0].innerText.replaceAll("\n", " ") : "";
        if (titleElems.length == 1) {
            var title = titleElems[0].innerText.replaceAll("\n", " - ");
            var coef1 = !isNaN(coefs1[i].innerText) ? coefs1[i].innerText : "";
            var coef2 = !isNaN(coefs2[i].innerText) ? coefs2[i].innerText : "";
            var competition = findAncestor(titleElems[0], "src-CompetitionMarketGroup")
                .querySelector(".rcl-CompetitionMarketGroupButton_Title").innerText.replaceAll("\n", "");
            //console.log(title + ";;" + score + ";;" + coef1 + ";;" + coef2);
            resultArray.push(title + ";;" + score + ";;" + coef1 + ";;" + coef2 + ";;" + competition);
        }
    }

    console.log("resultArray" + resultArray.length);
    if (resultArray.length == 0) {
        console.log("table tennis result array is empty");
    } else {
        sendJson("{\"value\": \"" + resultArray + "\"}", tennisHttpV2);
        localStorage["table-tennis"] = new Date().toUTCString();
    }
}

function sendFootball() {
    var resultArray = [];
    var arr = document.querySelectorAll("div.ovm-Fixture_Container");
    for (var i = 0; i < arr.length; i++) {
        var elem = arr[i];
        var titleElem = elem.querySelectorAll(".ovm-FixtureDetailsTwoWay_TeamsWrapper")[0];
        var scoreElem = elem.querySelectorAll(".ovm-StandardScores_ScoresWrapper");
        if (titleElem.innerText.toLowerCase().indexOf("esports") < 0) {
            continue;
        }

        var title = titleElem.innerText.toLowerCase().replaceAll("\n", " - ").replaceAll("esports", "");
        ;
        if (scoreElem.length > 0) {
            title = title + scoreElem[0].innerText.replaceAll("\n", "");
        }
        var timeline = elem.querySelectorAll(".ovm-FixtureDetailsTwoWay_Timer.ovm-InPlayTimer ")[0].innerText;

        var coef1 = 0.0;
        var coef2 = 0.0;

        var oddsElements = elem.querySelectorAll(".ovm-ParticipantOddsOnly_Odds");
        if (oddsElements.length == 3) {
            coef1 = elem.querySelectorAll(".ovm-ParticipantOddsOnly_Odds")[0].innerText;
            coef2 = elem.querySelectorAll(".ovm-ParticipantOddsOnly_Odds")[2].innerText;
        }
        var competition = findAncestor(arr[i], "ovm-Competition").querySelector(".ovm-CompetitionHeader_Name").innerText.replaceAll("\n", "");
        var resultString = title + ";;" + timeline + ";;" + coef1 + ";;" + coef2 + ";;" + competition;
        resultArray.push(resultString);
        // console.log(resultString);
    }

    console.log("" + resultArray);
    if (resultArray.length == 0) {
        console.log("football result array is empty");
    } else {
        console.log("sending football");
        sendJson("{\"value\": \"" + resultArray + "\"}", footballHttpV2);
        localStorage["football"] = new Date().toUTCString();
    }
}

function sendJson(value, url) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {/*nothing here*/
    };

    xmlhttp.open("PUT", url, true);

    xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xmlhttp.send(value);
}

document.body.onmousedown = function (e) {
    console.log("mouse clicked" + e);
}

document.body.onkeydown = function (e) {
    console.log(e.key + e.target);
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

function findAncestor(el, cls) {
    while ((el = el.parentElement) && !el.classList.contains(cls)) ;
    return el;
}

function diffSeconds(date1, date2) {
    var diff = Math.abs(date2 - date1);
    return Math.ceil(diff / 1000);
}
// content scripts can be applied directly to page and page dom
// but there are a lot a limitations

console.log("This is content script");

chrome.runtime.onMessage.addListener(function (msg, sender, sendResponse) {
    /* If the received message has the expected format... */
    if(!msg.text){
        return;
    }
    if (msg.text == "make_bet_selection") {
        var links = document.querySelectorAll("a i.blank");
        selectBets(links);
    } else if (msg.text == "confirm_bet"){
        set3UAHAndFocusConfirmButton();
    }
});

function selectBets(betLinks) {
    for (var i = 0; i < betLinks.length; i++) {
        var betRowText = betLinks[i].closest("div").previousElementSibling.innerText;

        if (betLinks[i].innerText <= "1.04" && betRowText.indexOf("Статистика") < 0 ) {
            betLinks[i].click();
        }
    }
    //alert('Bets marked');
    document.querySelector(".btn_orange").click();
}

function set3UAHAndFocusConfirmButton(){
    document.querySelector("input[name=sums]").value = 3;
    document.querySelector("input[type=radio][onclick='enableSingle();']").click();
    document.querySelector("#do_stake").focus();
}

function checkBet(){
    //TODO:
    // make conditions
}
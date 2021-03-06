// content scripts can be applied directly to page and page dom, but there are a lot a limitations
//1-1(6-4,5-7,2-5) 0:0 tennis
// 0-1(2-6,0-2) 0:0 - wrong score, why?

console.log("This is content script from vnc))");
var betAmount = 3;


var betInterval = setTimeout(function () {
//alert(1);
    var curBalanceElement = document.querySelector("#ownerInfo tr td:nth-child(7)");
    var betHistory = localStorage.getItem("betHistory") || "";

    if (!curBalanceElement){
        return;
    }

    var betText = document.querySelector("#wb").innerText;
    var currBalance = curBalanceElement.innerText ? parseFloat(curBalanceElement.innerText) : 0;
    var validBets = true;
    if(betText.indexOf("1.05") > 0 || betText.indexOf("1.06") > 0 || betText.indexOf("1.07") > 0
        || betText.indexOf("1.08") > 0 || betText.indexOf("1.08") > 0 || betText.indexOf("1.09") > 0 || betText.indexOf("1.1") > 0 ){
        // bet can change coef, so it became invalid
        validBets = false;
    }

    if (currBalance < 10){
	//if we have zero on balance -> skip 8 iterations ))
        localStorage.afterZeroBalanceCounter = 8;
    }

    if (currBalance >= 10 && validBets) {
        if (document.querySelector("#stakeNo[style='color:red']")) {
            window.close();
        }

        if(document.querySelector("input[name=sums]")){
            document.querySelector("input[name=sums]").value = "10";
        }

        var expressBetElement = document.querySelector("#r1");

        if (expressBetElement) {
            expressBetElement.click();
            var expressBetCoef = document.querySelector("label[for=r1]").innerText.replace(/[^\d\\.]/g,'').replace(/^\.+/g,'');
            if (expressBetCoef >= 1.15){
                window.close();
            }

            // it is available balance
            //document.querySelector("input[name=sums]").value = parseFloat(document.querySelector("#ownerInfo tr td:nth-child(7)").innerText);
        }

        var betsTableRows = document.querySelectorAll("#wb tr[id]");
        var currentBetInfo = "",
            errorFlag = true;
        for ( var i = 0; i < betsTableRows.length; i++) {
            if(!betsTableRows[i].querySelector("td:nth-child(4)").innerText.trim()){
                continue;
            }
            // time check
            if(!checkTimeFromEventStart(betsTableRows[i].querySelector("td:nth-child(4)").innerText.trim(),
                    betsTableRows[i].querySelector("td:nth-child(5)").innerText.trim())){
                errorFlag = false;
                console.log("Time: " + new Date() + betsTableRows[i].querySelector("td:nth-child(5)").innerText.trim());
                localStorage.setItem("wrongBetItems",     localStorage.getItem("wrongBetItems") +
                    ";Time:" + betsTableRows[i].querySelector("td:nth-child(5)").innerText.trim() );
                //alert("time check: " + betsTableRows[i].querySelector("td:nth-child(5)").innerText.trim());
                document.title = document.title + "; TIME Exlude " + betsTableRows[i].querySelector("td:nth-child(5)").innerText;
            }

            if(betHistory.indexOf(betsTableRows[i].querySelector("td:nth-child(5)").innerText.trim()) >=0 ){
                console.log("Duplicate: " + new Date() + betsTableRows[i].querySelector("td:nth-child(5)").innerText.trim());
                errorFlag = false;
                localStorage.setItem("wrongBetItems",     localStorage.getItem("wrongBetItems") +
                    "Duplicate: " + betsTableRows[i].querySelector("td:nth-child(5)").innerText.trim() );
                //alert("duplicate check " + betsTableRows[i].querySelector("td:nth-child(5)").innerText.trim());
                document.title = document.title + "; Duplicate Exlude " + betsTableRows[i].querySelector("td:nth-child(5)").innerText;
            }

            var betInfo = betsTableRows[i].querySelector("td:nth-child(4)").innerText.trim() + "__" +
                betsTableRows[i].querySelector("td:nth-child(5)").innerText.trim() + "__" +
                new Date() + ";"
            currentBetInfo += betInfo;
        }

        if(errorFlag){
            betHistory += currentBetInfo;
            betHistory += "     ;";// delimiter:)
            localStorage.setItem("betHistory", betHistory);
            //alert("PERFORM BET");
            document.querySelector("#do_stake").click();
        } else {
            //alert(localStorage.getItem("wrongBetItems"));
        }
    }
    setTimeout(function(){window.close()}, 5500);// close window after bet
}, 3600);


// ------------------------------------------------- DOM
chrome.runtime.onMessage.addListener(function (msg, sender, sendResponse) {
    /* If the received message has the expected format... */
    if (!msg.text) {
        return;
    }
    if (msg.text == "make_bet_selection") {
        var links = document.querySelectorAll("tr td:nth-child(3) a i.blank, tr td:nth-child(3) tr a i.down,tr td:nth-child(5) a i.blank, tr td:nth-child(5) tr a i.down");
        selectBets(links);
    } else if (msg.text == "confirm_bet") {
        set3UAHAndFocusConfirmButton();
    } else if (msg.text == "check_balance_and_bet") {
        checkBalanceAndBet();
    }
});

function selectBets(betLinks, maxBets) {
    console.log("Start selecting bets" + new Date().toLocaleString());
    
    if (localStorage.afterZeroBalanceCounter){
        localStorage.afterZeroBalanceCounter = parseInt(localStorage.afterZeroBalanceCounter)-1;
	if(localStorage.afterZeroBalanceCounter>=0){
		console.log("Skip selecting, because Zero money counter = " + localStorage.afterZeroBalanceCounter);
   		return;	
	}
    }

    maxBets = maxBets || 4;
    var counter = 0;
    document.title = "";
    var betTimesAvailable = JSON.parse((localStorage.betTimesAvailable || "[]"));
    var now = new Date();

    for (var i = 0; i < betLinks.length; i++) {
        var betRowCompetitionsLower = betLinks[i].closest("div").previousElementSibling.innerText.toLowerCase();
        if (betLinks[i].innerText <= "1.04" && checkBet(betRowCompetitionsLower)) {
            var betTitle = betLinks[i].closest("tr").querySelector("td:nth-child(2) a").firstChild.nodeValue ?
                betLinks[i].closest("tr").querySelector("td:nth-child(2) a").firstChild.nodeValue.trim()
                :betLinks[i].closest("tr").querySelector("td:nth-child(2) a").querySelector("small").innerText;

            var betScore = betLinks[i].closest("tr").querySelector(".score").innerText;

            if (!isCurrentScoreValid(betRowCompetitionsLower, betScore)){
                continue;
            }

            var betFromPreviousWrong = searchEventByTitle(betTimesAvailable, betTitle);
            if (betFromPreviousWrong && (new Date(betFromPreviousWrong.availableTime) > now)){
                console.log("DOES NOT MARK, because of TIME :" + betTitle);
                continue;
            }

            var previouslyWrongBetItems = localStorage.getItem("wrongBetItems") || "";
            var betHistory = localStorage.getItem("betHistory") || "";

            if(previouslyWrongBetItems.indexOf(betTitle) >= 0){
                document.title = document.title + "; Excluded " + betTitle;
                //alert("exclude " + betTitle);
                continue;
            }

            if(betHistory.indexOf(betTitle) >= 0){
                console.log("DOES NOT MARK DUPLICATE::" + betTitle);
                continue;
            }

            betLinks[i].click();
            counter++;
        }

        if(counter >= maxBets){
            break;
        }
    }

    localStorage.setItem("wrongBetItems", "");
    if(counter > 0){
        document.querySelector(".btn_orange").click();
    } else {
     console.log("Counter is < 1 :" + counter );
    }
}

function set3UAHAndFocusConfirmButton() {
    document.querySelector("input[name=sums]").value = 3;
    document.querySelector("input[type=radio][onclick='enableSingle();']").click();
    document.querySelector("#do_stake").focus();
}

function checkBalanceAndBet() {
    var links = document.querySelectorAll("tr td:nth-child(3) a i.blank, tr td:nth-child(3) tr a i.down,tr td:nth-child(5) a i.blank, tr td:nth-child(5) tr a i.down");
    selectBets(links);
}

// ---------------------------------------------------- Utils
function checkBet(betRowText) {
    //TODO:
    // make conditions
    if (betRowText.toLowerCase().indexOf("статистика") >= 0) {
        return false;
    }
console.log(betRowText);

    if (betRowText.indexOf("u-13") >= 0 || betRowText.indexOf("u-14") >= 0 || betRowText.indexOf("u-15") >= 0 || betRowText.indexOf("u-16") >= 0 || betRowText.indexOf("u-17") >= 0
        || betRowText.indexOf("u-18") >= 0 || betRowText.indexOf("u-19") >= 0) {
        return false;
    }

    if (betRowText.toLowerCase().indexOf("мол.") >= 0 || betRowText.toLowerCase().indexOf("молод") >= 0
        || betRowText.toLowerCase().indexOf("юнош") >= 0) {
        return false;
    }

    // skip friendly games
    if (betRowText.toLowerCase().indexOf("товари") >= 0) {
        return false;
    }

    // skip regional league
    if (betRowText.toLowerCase().indexOf("регионал") >= 0) {
        return false;
    }

    if (betRowText.toLowerCase().indexOf("микст") >= 0) {
        return false;
    }

    if (betRowText.toLowerCase().indexOf("дартс") >= 0) {
        return false;
    }

    if (betRowText.toLowerCase().indexOf("бильярд") >= 0) {
        return false;
    }

    if (betRowText.toLowerCase().indexOf("на траве") >= 0) {
        return false;
    }

    //??? I dont know, should I add
    if (betRowText.toLowerCase().indexOf("киберспорт") >= 0) {
        return false;
    }

    if (betRowText.toLowerCase().indexOf("регби") >= 0) {
        return false;
    }

   if (betRowText.toLowerCase().indexOf("сквош") >= 0) {
        return false;
    }

    if (betRowText.toLowerCase().indexOf("поло") >= 0) {
        return false;
    }

    if (betRowText.toLowerCase().indexOf("формула 1") >= 0) {
        return false;
    }

    if (betRowText.toLowerCase().indexOf("хоккейбол") >= 0) {
        return false;
    }

    if (betRowText.toLowerCase().indexOf("шары") >= 0) {
        return false;
    }
   
	// disable footbal until app can parse 
    if ( betRowText.toLowerCase().indexOf("футбол") >=0 ){
         return false;
    }

	// disable footbal until app can parse 
    if ( betRowText.toLowerCase().indexOf("бадминт") >=0 ){
         return false;
    }


    if (betRowText.toLowerCase().indexOf("футбол") >= 0 &&
        (
            betRowText.toLowerCase().indexOf("швеция") >= 0 || betRowText.toLowerCase().indexOf("кариока") >= 0 ||
            (betRowText.toLowerCase().indexOf("россия") >= 0 && betRowText.toLowerCase().indexOf("дивизион") >= 0)
        )

    ) {
        console.log("WRONG football");
        return false;
    } 

    if (betRowText.toLowerCase().indexOf("бейсбол") >= 0) {
        return false;
    }
// to do
    if (betRowText.toLowerCase().indexOf("настоль") >= 0) {
        return false;
    }

    if (betRowText.toLowerCase().indexOf("хоккей") >= 0) {
        return false;
    }

    return true;
}

function checkTimeFromEventStart(datetime, event) {
    var now = new Date();
    var before = new Date();

    var time = datetime.substr(datetime.length - 5).split(":");
    before.setHours(time[0]);
    before.setMinutes(time[1]);

    var diffMinutes = (now - before) / 60000;
    var betTitle = event.toLowerCase();
    console.log(betTitle + "----->" + diffMinutes);
    var betTimesAvailable = JSON.parse((localStorage.betTimesAvailable || "[]"));

    if ( betTitle.indexOf("теннис") >= 0 && betTitle.indexOf("настоль") < 0 && diffMinutes < 48) {
        var obj = {};
        obj.betTitle = betTitle;
        obj.availableTime = new Date(before.setMinutes(before.getMinutes() + 48));
        betTimesAvailable.push(obj);
        localStorage.betTimesAvailable = JSON.stringify(betTimesAvailable);
        return false;
    }

    if (betTitle.indexOf("волейб") >= 0 && diffMinutes < 24) {
        var obj = {};
        obj.betTitle = betTitle;
        obj.availableTime = new Date(before.setMinutes(before.getMinutes() + 29));
        betTimesAvailable.push(obj);
        localStorage.betTimesAvailable = JSON.stringify(betTimesAvailable);
        return false;
    }

    if (betTitle.indexOf("бадминтон") >= 0) {
        return diffMinutes > 45;
    }

    if (betTitle.indexOf("настоль") >= 0) {
        return diffMinutes > 34;
    }

    if (betTitle.indexOf("футбол") >= 0) {
        return diffMinutes > 73;
    }

    if (betTitle.indexOf("баскетб") >= 0 && diffMinutes < 36) {
        var obj = {};
        obj.betTitle = betTitle;
        obj.availableTime = new Date(before.setMinutes(before.getMinutes() + 29));
        betTimesAvailable.push(obj);
        localStorage.betTimesAvailable = JSON.stringify(betTimesAvailable);
        return false;
    }

    if (betTitle.indexOf("гандбол") >= 0) {
        return diffMinutes > 27;
    }

    if (betTitle.indexOf("футзал") >= 0) {
        return diffMinutes > 32;
    }

    return true;
}

function isCurrentScoreValid(competition, currentScore) {
    if (!competition || !currentScore) {
        return false;
    }

    if (competition.indexOf("баскетб") >= 0) {
        var scoreCheckResult = checkBasketBallScore(currentScore);
        if(!scoreCheckResult){
            console.log("Wrong score in basketball : " + currentScore);
        }
        return scoreCheckResult;
    }

    if (competition.indexOf("волейб") >= 0) {
        var scoreCheckResult = checkVoleyBallScore(currentScore);
        if(!scoreCheckResult){
            console.log("Wrong score in voleyball : " + currentScore);
        }
        return scoreCheckResult;
    }

    if (competition.indexOf("теннис") >= 0 && competition.indexOf("настоль") < 0) {
        var scoreCheckResult = checkTennisScore(currentScore);
        if(!scoreCheckResult){
            console.log("Wrong score in Tennis : " + currentScore );
        }
        return scoreCheckResult;

    }

    if (competition.indexOf("гандбол") >= 0) {
        var scoreCheckResult = checkHandBallScore(currentScore);
        
        if(!scoreCheckResult){
            console.log("Wrong score in HandBall : " + currentScore );
        }

        return scoreCheckResult;
    }


    return true;
}

// winner in 1 and 2 quarters should be the same, in 3 quarter(if started) should be <5
function checkBasketBallScore(currentScore){
    var result = false;

    var bracketsPositions = [currentScore.indexOf("("), currentScore.indexOf(")")];
    if (bracketsPositions[0] < 0 || bracketsPositions[1] < 0){
        return false;
    }

    var currentScoreArray = currentScore.substring(0, bracketsPositions[0]).split("-");
    if(parseInt(currentScoreArray[0]) + parseInt(currentScoreArray[1]) < 55){
        return false;
    }

    var quarters = currentScore.substring(bracketsPositions[0] + 1, bracketsPositions[1]).split(",");
    if (quarters.length < 2) {
        return false;
    }

    var firstQuarterWinner = parseInt(quarters[0].split("-")[0]) > parseInt(quarters[0].split("-")[1]) ? 0 : 1;
    var secondQuarterWinner = parseInt(quarters[1].split("-")[0]) > parseInt(quarters[1].split("-")[1]) ? 0 : 1;
    var looser = firstQuarterWinner == 0 ? 1 : 0;

    if (firstQuarterWinner == secondQuarterWinner &&
        (parseInt(quarters[1].split("-")[0]) + parseInt(quarters[1].split("-")[1])) > 20) {
        result = true;

        if (quarters.length > 2) {
            result = result && (Math.abs(parseInt(quarters[2].split("-")[0]) - parseInt(quarters[2].split("-")[1])) < 3 ||
                parseInt(quarters[2].split("-")[firstQuarterWinner]) > parseInt(quarters[2].split("-")[looser]));
        }
    }

    return result;
}

function checkVoleyBallScore(currentScore){
    var result = false;

    var bracketsPositions = [currentScore.indexOf("("), currentScore.indexOf(")")];
    if (bracketsPositions[0] < 0 || bracketsPositions[1] < 0){
        return false;
    }

    var quarters = currentScore.substring(bracketsPositions[0] + 1, bracketsPositions[1]).split(",");
    if (quarters.length < 2) {
        return false;
    }


    // first set differnce > 2
    //TODO second set sum > 19 and difference > 2

    var firstQuarterWinner = parseInt(quarters[0].split("-")[0]) > parseInt(quarters[0].split("-")[1]) ? 0 : 1;
    var secondQuarterWinner = parseInt(quarters[1].split("-")[0]) > parseInt(quarters[1].split("-")[1]) ? 0 : 1;
    var looser = firstQuarterWinner == 0 ? 1 : 0;

    if (firstQuarterWinner == secondQuarterWinner){
        result = true;
    }


    if (quarters[0].split("-")[0] > 25 || quarters[0].split("-")[1] > 25 ||
        quarters[1].split("-")[0] > 25 || quarters[1].split("-")[1] > 25) {
        return false;
    }

    var currentSet = quarters.length;
    var currentSetScoreSum  = parseInt(quarters[currentSet-1].split("-")[0]) + parseInt(quarters[currentSet-1].split("-")[1]);
    if( currentSetScoreSum > 16 ){
        result = parseInt(quarters[currentSet-1].split("-")[firstQuarterWinner]) -
            parseInt(quarters[currentSet-1].split("-")[looser]) > 2;
    }

     if(currentSetScoreSum > 5 && currentSetScoreSum <= 16){
         result = parseInt(quarters[currentSet-1].split("-")[firstQuarterWinner]) -
             parseInt(quarters[currentSet-1].split("-")[looser]) > 3;
     }

     if (currentSetScoreSum <= 5){
         return false;
     }

    return result;
}

//checkTennisScore('1-1(2-6,6-2,3-4) 15:30')
//checkTennisScore('1-1(2-6,0-2) 15:15') - true
//checkTennisScore('1-1(2-6,0-2) 15:0') - false
function checkTennisScore(currentScore){
    currentScore = currentScore.replace(/ /g, '');
    currentScore = currentScore.toLowerCase();
    currentScore = currentScore.replace(/adv/g, '50');

    var bracketsPositions = [currentScore.indexOf("("), currentScore.indexOf(")")];
    if (bracketsPositions[0] < 0 || bracketsPositions[1] < 0){
        return false;
    }

    var sets = currentScore.substring(bracketsPositions[0] + 1, bracketsPositions[1]).split(",");
    if (sets.length < 2) {
        return false;
    }


    if (sets.length == 2 ){
        var firstSetVinner = parseInt(sets[0].split("-")[0]) > parseInt(sets[0].split("-")[1]) ? 0 : 1;
        var firstSetLooser = firstSetVinner == 0 ? 1 : 0;

        if (parseInt(sets[1].split("-")[firstSetLooser]) >= parseInt(sets[1].split("-")[firstSetVinner])){
            return false;
        }
    }


    var currentSet = sets.length-1;
    var currentPoints = currentScore.substring(bracketsPositions[1]+1).split(":");
    var currentSetLeader = parseInt(sets[currentSet].split('-')[0]) > parseInt(sets[currentSet].split('-')[1]) ? 0 : 1;
    var currentSetLooser = currentSetLeader == 0 ? 1 : 0;

    if(sets[currentSet][0] == sets[currentSet][1]){
        return false;
    }

    if (parseInt(sets[currentSet].split("-")[currentSetLeader]) - parseInt(sets[currentSet].split("-")[currentSetLooser]) == 2 &&
        parseInt(currentPoints[currentSetLeader]) > parseInt(currentPoints[currentSetLooser])  && parseInt(currentPoints[currentSetLeader]) > 15) {
        return true;
    }

    if (parseInt(sets[currentSet].split("-")[currentSetLeader]) - parseInt(sets[currentSet].split("-")[currentSetLooser]) > 2){
        return true;
    }

    if ( (parseInt(sets[currentSet].split("-")[0]) +
             parseInt(sets[currentSet].split("-")[1])) < 6 ){
          return false;
    }

    if (parseInt(currentPoints[currentSetLeader]) - parseInt(currentPoints[currentSetLooser]) > 15) {
        return true;
    }


    return false;
}

function checkHandBallScore(currentScore){
	var bracketPosition = currentScore.indexOf("(");
	var parsedScoreArr = currentScore.substring(0, bracketPosition).split("-");
	if (parsedScoreArr.length < 1){
		return false;
	}
	
	//TODO Add: if scoreSum > 19 and difference > 4 return true
	return (parseInt(parsedScoreArr[0]) + parseInt(parsedScoreArr[1])) > 39 &&
	Math.abs(parseInt(parsedScoreArr[0]) - parseInt(parsedScoreArr[1])) > 3;
}

function searchEventByTitle(arr, title){
    var resultArray = arr.filter(function(a){ return a.betTitle && a.betTitle.toLowerCase().
        indexOf(title.toLowerCase())>0 });

    if(resultArray.length > 0){
        return resultArray[0];
    }
    return null;
}

function cashOut(){

   //TODO add cashout if it is less than 0.70
   
}

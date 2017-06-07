console.log("This is browser action"); // to see this console.log click 'Отладка страниц: фоновая страница (неактивно)' in chrome extensions page

var checkBetsTimeout = null;

function openParimatchLive() {
    chrome.tabs.create({
        "url": "https://www.parimatch.com/live.html"
    }, function (tab) {
        console.log("Tab " + tab.url); // to see tab.url you need add "permissions": ["tabs"] in manifest.json
    });
}

// listener for commands from manifest.json
chrome.commands.onCommand.addListener(function (command) {
    if (command === "make_bet") {
        sendCommandToTab("make_bet_selection");
    } else if (command === "confirm_bet") {
        sendCommandToTab("confirm_bet");
    } else if (command == "open_parimatch_live") {
        openParimatchLive();
    } else if (command == "start_bot") {
        if (checkBetsTimeout) {
            clearInterval(checkBetsTimeout);
            checkBetsTimeout = null;
            alert("stop");
        } else {
            checkBetsTimeout = setInterval(function () {
                sendCommandToTab("check_balance_and_bet")
            }, 9000);
            alert("start")
        }
    }
});

function sendCommandToTab(command) {
    chrome.tabs.query({active: true, currentWindow: true}, function (tabs) {
        chrome.tabs.sendMessage(tabs[0].id, {text: command},
            function () {/*ignore response*/
            }
        );
    });
    // i had a problem: I wanted to pass 'document' to callback function, but was not able to do it in background.js, so I perform all
    // DOM stuff in content_script.js
}


// this is not used in app, but I leave here for education purpose
chrome.tabs.onActivated.addListener(function (tabId) {
    var url;
    var tab_id = tabId.tabId;
    chrome.tabs.get(tab_id, function (tab) {
        url = tab.url;
//        alert(url);
    });
});
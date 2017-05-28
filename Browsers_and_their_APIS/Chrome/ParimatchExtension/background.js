console.log("This is browser action"); // to see this console.log click 'Отладка страниц: фоновая страница (неактивно)' in chrome extensions page

function handleButton1Click() {
    alert("1");
}

function openParimatchLive() {
    chrome.tabs.create({
        "url": "https://www.parimatch.com/live.html"
    }, function (tab) {
        console.log("Tab " + tab.url); // to see tab.url you need add "permissions": ["tabs"] in manifest.json
    });
}


chrome.commands.onCommand.addListener(function (command) {
    if (command === "make_bet") {
        sendCommandToTab("make_bet_selection");
    } else if (command === "confirm_bet") {
        sendCommandToTab("confirm_bet");
    } else if (command == "open_parimatch_live"){
        openParimatchLive();
    }
});

function sendCommandToTab(command) {
    chrome.tabs.query({active: true, currentWindow: true}, function (tabs) {
        chrome.tabs.sendMessage(tabs[0].id, {text: command},
            function () {/*ignore response*/
            }
        );
    });
    // i had a problem: I wanted to pass 'document' to callback function, but was not able to do it, so I perform all 
    // DOM stuff in content_script.js
}

console.log("This is browser action"); // to see this console.log click 'Отладка страниц: фоновая страница (неактивно)' in chrome extensions page

function handleButton1Click() {
    alert("1");
}

function openNewTab(){
    chrome.tabs.create({
        "url": "https://google.com"
    }, function(tab){
        console.log("Tab " + tab.url); // to see tab.url you need add "permissions": ["tabs"] in manifest.json
    });
}
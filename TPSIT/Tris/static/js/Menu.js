let buttons = document.querySelectorAll("#menu .menuButton");
let menu = document.querySelector("#menu");
let matchMenu = document.querySelector("#matchMenu");
let createMenu = document.querySelector("#createMenu");
let joinMenu = document.querySelector("#joinMenu");
let body = document.body;


buttons.forEach(button => {
    button.addEventListener("click", () => {
        modeMenu(button)
    })
})

function modeMenu(button) {
    let choice = parseInt(button.dataset.choice);
    switch (choice) {
        case 0:
            body.classList.remove("darken");
            menu.classList.add("hidden");
            localPvp();
            break;
        case 1:
            body.classList.remove("darken");
            menu.classList.add("hidden");
            localPve();
            break;
        case 2:
            menu.classList.add("hidden");
            matchMenu.classList.remove("hidden");
            let matchButtons = document.querySelectorAll("#matchMenu .menuButton");
            matchButtons.forEach(matchButton => {
                matchButton.addEventListener("click", () => {
                    matchMenuFunc(matchButton);
                })
            })
            break;
    }
}

function matchMenuFunc(button) {
    let choice = parseInt(button.dataset.choice);
    switch (choice) {
        case 0:
            matchMenu.classList.add("hidden");
            createMenu.classList.remove("hidden");
            let createButton = document.querySelector("#createMenu .menuButton");
            createButton.addEventListener("click", async () => { // Call Pvp() with true here
                let input = document.querySelector("#pl1Username");
                if (input.value !== "") {
                    let createResponse = await CreateMatch(input.value);
                    let guid = createResponse.guid;
                    createMenu.innerHTML = "<h3>Waiting for P2</h3>" +
                        `<span>Guid: ${guid}</span>`
                    // TODO wait for P2, see Pvp.js on how to do a loop
                    await waitLoop(guid, input.value);
                }
            })
            break;
        case 1:
            matchMenu.classList.add("hidden");
            joinMenu.classList.remove("hidden");
            let joinButton = document.querySelector("#joinMenu .menuButton");
            joinButton.addEventListener("click", async () => { // Call Pvp() with false here
                let guidInput = document.querySelector("#guid");
                let pl2Username = document.querySelector("#pl2Username");
                if (guidInput.value !== "" && pl2Username.value !== "") {
                    let connectP2request = await ConnectP2(guidInput.value, pl2Username.value);
                    let pl1Username = connectP2request.username;
                    body.classList.remove("darken");
                    joinMenu.classList.add("hidden");
                    await Pvp(pl1Username, pl2Username.value, guidInput.value, false);
                }
            })
            break;
    }
}

async function waitLoop(guid, pl1Username) {
    let waiting = await asyncLoop(guid);
    if (waiting === true) {
        // This makes sure the function ends before executing it again,
        // then calls it again every second
        setTimeout(waitLoop, 1000, guid, pl1Username)
    } else {
        createMenu.classList.add("hidden");
        body.classList.remove("darken");
        await Pvp(pl1Username, waiting, guid, true);
    }
}

async function asyncLoop(guid) {
    let checkP2Response = await CheckP2Connection(guid);
    if (checkP2Response === "P2 not connected") {
        return true;
    }
    return checkP2Response.username;
}

let buttons = document.querySelectorAll(".menuButton");
let menu = document.querySelector("#menu");
let body = document.body;

buttons.forEach(button => {
    button.addEventListener("click", modeMenu)
})

function modeMenu() {
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
            //pvpMenu();
            break;
    }
}

function pvpMenu() {
    menu.innerHTML =
        `<h3>Multiplayer</h3>
    <button data-choice="0" class="menuButton">Create match</button>
    <button data-choice="1" class="menuButton">Join match</button>`
    buttons = document.querySelectorAll(".menuButton");
    buttons.forEach(button => {
        button.removeEventListener("click", modeMenu);
        button.addEventListener("click", );
    })
}
/*
function createMatch() {
    menu.innerHTML = `
    <h3>Multiplayer</h3>
    <input type="text" class="username">
    <button data-choice="1" class="menuButton">Join match</button>
    `
    buttons = document.querySelectorAll(".menuButton");
    let input = document.querySelector(".username");
    buttons.addEventListener("click", )
}*/
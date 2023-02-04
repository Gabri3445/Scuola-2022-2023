let buttons = document.querySelectorAll(".menuButton");
let menu = document.querySelector("#menu");
let body = document.body;

buttons.forEach(button => {
    button.addEventListener("click", event => {
        let choice = parseInt(button.dataset.choice);
        switch (choice) {
            case 0:
                body.classList.remove("darken");
                menu.classList.add("hidden");
                localPvp();
                break;
            case 1:
                alert("WIP");
                break;
            case 2:
                pvp();
                break;
        }
    })
})
let rowList = document.querySelectorAll(".row > div");
for (let i = 0; i < 9; i++) {
    rowList[i].classList.add(i.toString());
}

let cellStatus = new Array(9);
/*
 * 0 = No X or O
 * 1 = X
 * 2 = O
 */
cellStatus.forEach(cell => {
    cell = 0;
})
let player = 0;

rowList.forEach(row => {
    row.addEventListener("click", event => {
        console.log(event.target.classList.item(0));
        if (player === 0) {
            cellStatus[parseInt(event.target.classList.item(0))] = 1
            console.log(cellStatus[parseInt(event.target.classList.item(0))])
            event.target.children[0].innerHTML = "X";
            player = 1;
        } else {
            cellStatus[parseInt(event.target.classList.item(0))] = 2
            console.log(cellStatus[parseInt(event.target.classList.item(0))])
            event.target.children[0].innerHTML = "O"
            player = 0;
        }
    });
})

document.querySelector(".resetButton").addEventListener("click", event => {
    cellStatus.forEach(cells => {
        cells = 0;
    })
    player = 0;
    rowList.forEach(row => {
        row.children[0].innerHTML = "";
    })
})
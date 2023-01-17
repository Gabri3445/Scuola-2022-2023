let cellList = document.querySelectorAll(".row > div");
for (let i = 0; i < 9; i++) {
    cellList[i].classList.add(i.toString());
    cellList[i].classList.add("pointer");
}

let cellStatus = new Array(9); //reset
/*
 * 0 = No X or O
 * 1 = X
 * 2 = O
 */
for (let i = 0; i < 9; i++) {
    cellStatus[i] = 0;
}
let player = 0; //reset

let drawCounter = 0; //reset

let gameState = 0; //reset
/*
 * 0 = ongoing
 * 1 = X wins
 * 2 = O wins
 * 3 = Draw
 */

const score = {
    X: 0, O: 0
}

cellList.forEach(row => {
    row.addEventListener("click", event => {
        console.log(event.target.classList.item(0));
        if (gameState === 0) {
            if (player === 0) {
                if (cellStatus[parseInt(event.target.classList.item(0))] === 0) {
                    cellStatus[parseInt(event.target.classList.item(0))] = 1
                    console.log(cellStatus[parseInt(event.target.classList.item(0))])
                    event.target.children[0].innerHTML = "X";
                    event.target.classList.remove("pointer");
                    player = 1;
                    drawCounter++
                    if (checkForVictory(cellStatus) === 1) {
                        console.log("barillo X")
                        score.X++;
                        gameState = 1;
                        document.querySelector("#score").innerHTML = `${score.X}-${score.O}`
                    }
                }
            } else {
                if (cellStatus[parseInt(event.target.classList.item(0))] === 0) {
                    cellStatus[parseInt(event.target.classList.item(0))] = 2
                    console.log(cellStatus[parseInt(event.target.classList.item(0))])
                    event.target.children[0].innerHTML = "O";
                    event.target.classList.remove("pointer");
                    player = 0;
                    drawCounter++;
                    if (checkForVictory(cellStatus) === 2) {
                        console.log("barillo O")
                        score.O++;
                        gameState = 2;
                        document.querySelector("#score").innerHTML = `${score.X}-${score.O}`
                    }
                }
            }
            if (drawCounter === 9) {
                gameState = 3;
                console.log("barilraw")
            }
        }
    })
})

function checkForVictory(board) {
    for (let i = 0; i < 9; i += 3) {
        if (board[i] === board[i + 1] && board[i + 1] === board[i + 2]) {
            return board[i];
        }
    }

    for (let i = 0; i < 3; i++) {
        if (board[i] === board[i + 3] && board[i + 3] === board[i + 6]) {
            return board[i];
        }
    }

    if (board[0] === board[4] && board[4] === board[8]) {
        return board[0];
    }
    if (board[2] === board[4] && board[4] === board[6]) {
        return board[2];
    }

    return 0;
}

document.querySelector(".resetButton").addEventListener("click", () => {
    for (let i = 0; i < 9; i++) {
        cellStatus[i] = 0;
    }
    gameState = 0;
    drawCounter = 0;
    player = 0;
    cellList.forEach(row => {
        row.children[0].innerHTML = "";
    })
    for (let i = 0; i < 9; i++) {
        cellList[i].classList.add("pointer");
    }
})
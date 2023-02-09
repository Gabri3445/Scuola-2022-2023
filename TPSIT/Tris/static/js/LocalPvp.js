function localPvp() {
    let cellList = document.querySelectorAll(".row > div");
    for (let i = 0; i < 9; i++) {
        cellList[i].classList.add(i.toString());
        cellList[i].classList.add("pointer");
    }

    /*
     * 0 = No X or O
     * 1 = X
     * 2 = O
     * Arrays represent the rows, the elements each cell
     */
    let cellStatus = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]; // reset


    let player = 0; // reset

    let drawCounter = 0; // reset

    let gameState = 0; // reset
    /*
     * 0 = ongoing
     * 1 = X wins
     * 2 = O wins
     * 3 = Draw
     */

    const currentPlayer = {
        X: document.querySelector("#X"),
        O: document.querySelector("#O")
    }

    const score = {
        X: 0, O: 0
    }

    cellList.forEach(row => {
        row.addEventListener("click", event => {
            if (gameState === 0) {
                if (player === 0) {
                    let cell = event.target.classList.item(0);
                    if (cellStatus[Math.floor(cell / 3)][cell % 3] === 0) {
                        currentPlayer.X.classList.remove("underline");
                        currentPlayer.O.classList.add("underline");
                        let cell = event.target.classList.item(0)
                        cellStatus[Math.floor(cell / 3)][cell % 3] = 1
                        console.log(cellStatus[parseInt(event.target.classList.item(0))])
                        event.target.children[0].innerHTML = "X";
                        event.target.classList.remove("pointer");
                        player = 1;
                        drawCounter++
                        if (checkForVictory(cellStatus) === 1) {
                            currentPlayer.X.innerHTML = "X WON"
                            console.log("barillo X")
                            score.X++;
                            gameState = 1;
                            document.querySelector("#score").innerHTML = `${score.X} - ${score.O}`
                        }
                    }
                } else {
                    let cell = event.target.classList.item(0);
                    if (cellStatus[Math.floor(cell / 3)][cell % 3] === 0) {
                        let cell = event.target.classList.item(0)
                        currentPlayer.X.classList.add("underline");
                        currentPlayer.O.classList.remove("underline");
                        cellStatus[Math.floor(cell / 3)][cell % 3] = 2
                        console.log(cellStatus[parseInt(event.target.classList.item(0))])
                        event.target.children[0].innerHTML = "O";
                        event.target.classList.remove("pointer");
                        player = 0;
                        drawCounter++;
                        if (checkForVictory(cellStatus) === 2) {
                            currentPlayer.O.innerHTML = "O WON"
                            console.log("barillo O")
                            score.O++;
                            gameState = 2;
                            document.querySelector("#score").innerHTML = `${score.X} - ${score.O}`
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

    /*
    function checkForVictory(board) {
        // check rows
        for (let i = 0; i < 3; i++) {
            if (board[i][0] === board[i][1] && board[i][1] === board[i][2]) {
                return board[i][0];
            }
        }
        // check columns
        for (let i = 0; i < 3; i++) {
            if (board[0][i] === board[1][i] && board[1][i] === board[2][i]) {
                return board[0][i];
            }
        }
        // check diagonals
        if (board[0][0] === board[1][1] && board[1][1] === board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] === board[1][1] && board[1][1] === board[2][0]) {
            return board[0][2];
        }
        return 0;
    }
    */

    function checkForVictory(board) {
        // check rows
        for (let i = 0; i < 3; i++) {
            if (board[i][0] === board[i][1] && board[i][1] === board[i][2] && board[i][0] !== 0) {
                return board[i][0];
            }
        }
        // check columns
        for (let i = 0; i < 3; i++) {
            if (board[0][i] === board[1][i] && board[1][i] === board[2][i] && board[0][i] !== 0) {
                return board[0][i];
            }
        }
        // check diagonals
        if (board[0][0] === board[1][1] && board[1][1] === board[2][2] && board[0][0] !== 0) {
            return board[0][0];
        }
        if (board[0][2] === board[1][1] && board[1][1] === board[2][0] && board[0][2] !== 0) {
            return board[0][2];
        }
        // no winner
        return 0;
    }



    document.querySelector(".resetButton").addEventListener("click", () => {
        currentPlayer.O.innerHTML = "O"
        currentPlayer.X.innerHTML = "X"
        currentPlayer.O.classList.remove("underline");
        currentPlayer.X.classList.remove("underline");
        cellStatus = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
        gameState = 0;
        drawCounter = 0;
        player = 0;
        cellList.forEach(row => {
            row.children[0].innerHTML = "";
        })
        for (let i = 0; i < 9; i++) {
            cellList[i].classList.add("pointer");
        }
        currentPlayer.X.classList.add("underline");
        currentPlayer.O.classList.remove("underline");
    })
}
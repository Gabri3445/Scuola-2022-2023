function localPve() {
    let cellList = document.querySelectorAll(".row > div");
    for (let i = 0; i < 9; i++) {
        cellList[i].classList.add(i.toString());
        cellList[i].dataset.index = i;
        cellList[i].classList.add("pointer");
    }

    /*
     * 0 = No X or O
     * 1 = X
     * 2 = O
     * * Arrays represent the rows, the elements each cell
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
                        event.target.children[0].innerHTML = "X";
                        event.target.classList.remove("pointer");
                        player = 1;
                        drawCounter++
                        if (checkForWin(cellStatus) === 1) {
                            currentPlayer.X.innerHTML = "X WON"
                            console.log("barillo X")
                            score.X++;
                            gameState = 1;
                            document.querySelector("#score").innerHTML = `${score.X} - ${score.O}`
                        }
                        else {
                            let bestMove = minimax(cellStatus, 0, true).move;
                            console.log(bestMove)
                            let row = Math.floor(bestMove / 3);
                            let col = bestMove % 3;
                            cellStatus[row][col] = 2;
                            function getCell(index) {
                                for (let i = 0; i < cellList.length; i++) {
                                    if (parseInt(cellList[i].dataset.index) === index) {
                                        return cellList[i];
                                    }
                                }
                                return null;
                            }
                            let targetCell = getCell(bestMove);
                            targetCell.children[0].innerHTML = "O";
                            targetCell.classList.remove("pointer");
                            currentPlayer.O.classList.remove("underline");
                            currentPlayer.X.classList.add("underline");
                            player = 0;
                            drawCounter++;
                            if (checkForWin(cellStatus) === 2) {
                                currentPlayer.O.innerHTML = "O WON"
                                console.log("barillo O")
                                score.O++;
                                gameState = 2;
                                document.querySelector("#score").innerHTML = `${score.X} - ${score.O}`
                            }
                        }
                    }
                }
                if (drawCounter === 9) {
                    gameState = 3;
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

    function minimax(board, depth, isMaximizing) {
        let result = checkForWin(board);
        let bestMove = -1;
        if (result !== 0) {
            if (result === 1) {
                return {
                    score: 10 - depth,
                    move: -1
                };
            } else if (result === 2) {
                return {
                    score: depth - 10,
                    move: -1
                };
            } else {
                return {
                    score: 0,
                    move: -1
                };
            }
        }

        if (isMaximizing) {
            let bestScore = -Infinity;
            for (let i = 0; i < board.length; i++) {
                for (let j = 0; j < board[i].length; j++) {
                    if (board[i][j] === 0) {
                        board[i][j] = 1;
                        let score = minimax(board, depth + 1, false);
                        board[i][j] = 0;
                        if (score.score > bestScore) {
                            bestScore = score.score;
                            bestMove = i * 3 + j;
                        }
                    }
                }
            }
            return {
                score: bestScore,
                move: bestMove
            };
        } else {
            let bestScore = Infinity;
            for (let i = 0; i < board.length; i++) {
                for (let j = 0; j < board[i].length; j++) {
                    if (board[i][j] === 0) {
                        board[i][j] = 2;
                        let score = minimax(board, depth + 1, true);
                        board[i][j] = 0;
                        if (score.score < bestScore) {
                            bestScore = score.score;
                            bestMove = i * 3 + j;
                        }
                    }
                }
            }
            return {
                score: bestScore,
                move: bestMove
            };
        }
    }



    function checkForWin(board) {
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
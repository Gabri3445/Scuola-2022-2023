async function Pvp(pl1Username, pl2Username, guid, isPlayerOne) {
    let cellList = document.querySelectorAll(".row > div");
    for (let i = 0; i < 9; i++) {
        cellList[i].classList.add(i.toString());
        cellList[i].classList.add("pointer");
    }

    let playerSymbol = "";
    let playerNumber = null;

    if (isPlayerOne){
        playerNumber = 1;
        playerSymbol = "X"
    }
    else {
        playerNumber = 2;
        playerSymbol = "O"
    }

    /*
     * 0 = No X or O
     * 1 = X
     * 2 = O
     * Arrays represent the rows, the elements each cell
     */
    let cellStatus = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]; // reset


    //let player = 0; // reset

    //let drawCounter = 0; // reset

    //let gameState = 0; // reset
    /*
     * 0 = ongoing
     * 1 = X wins
     * 2 = O wins
     * 3 = Draw
     */

    const currentPlayer = {
        X: document.querySelector("#X"), O: document.querySelector("#O")
    }

    currentPlayer.X.innerHTML = pl1Username;
    currentPlayer.O.innerHTML = pl2Username;

    const score = {
        X: 0, O: 0
    }

    // Temporary, see TODO on Reset function on the server
    // Only allow reset if the game has ended
    let allowReset = false;

    cellList.forEach(row => {
        row.addEventListener("click", async event => {
            const statusResponse = await GetStatus(guid);
            const matchStatus = statusResponse.matchStatus;

            if (matchStatus === 3) {
                const playerResponse = await GetPlayer(guid);
                const player = playerResponse.player;

                if (player === 1 && isPlayerOne || player === 2 && !isPlayerOne) {
                    let cell = event.target.classList.item(0);
                    if (cellStatus[Math.floor(cell / 3)][cell % 3] === 0) {
                        if (isPlayerOne) {
                            currentPlayer.X.classList.remove("underline");
                            currentPlayer.O.classList.add("underline");
                        } else {
                            currentPlayer.O.classList.remove("underline");
                            currentPlayer.X.classList.add("underline");
                        }



                        let cell = event.target.classList.item(0)
                        cellStatus[Math.floor(cell / 3)][cell % 3] = playerNumber
                        console.log(cellStatus[parseInt(event.target.classList.item(0))])

                        event.target.children[0].innerHTML = playerSymbol;
                        event.target.classList.remove("pointer");
                        
                        let data = {
                            guid: guid,
                            player: isPlayerOne ? 1 : 2,
                            location: {
                                x: cell % 3,
                                y : Math.floor(cell / 3)
                            }
                        }
                        await MakeMove(data.guid, data.player, data.location);

                        const statusResponse = await GetStatus(guid);
                        const matchStatus = statusResponse.matchStatus;
                        // Check for win
                        if (matchStatus === 5 && isPlayerOne || matchStatus === 6 && !isPlayerOne) {
                            if (isPlayerOne) {
                                currentPlayer.X.innerHTML = pl1Username + " WON"
                                score.X++;
                            } else {
                                currentPlayer.O.innerHTML = pl2Username + " WON"
                                score.O++;
                            }
                            allowReset = true;
                            document.querySelector("#score").innerHTML = `${score.X} - ${score.O}`
                        }
                    }
                }

            }
            if (matchStatus === 4) {
                currentPlayer.X.innerHTML = "DRAW"
                currentPlayer.O.innerHTML = "DRAW"
                currentPlayer.O.classList.remove("underline");
                currentPlayer.X.classList.remove("underline");
                allowReset = true;
            }
        })
    })

    //TODO add a infinite loop checking for the player 2 move and if player 2 won

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

    // Won't need this anymore
    /*function checkForVictory(board) {
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
    }*/


    document.querySelector(".resetButton").addEventListener("click", () => {
        if (allowReset) {
            currentPlayer.O.classList.remove("underline");
            currentPlayer.X.classList.remove("underline");
            cellStatus = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
            cellList.forEach(row => {
                row.children[0].innerHTML = "";
            })
            for (let i = 0; i < 9; i++) {
                cellList[i].classList.add("pointer");
            }
            currentPlayer.X.classList.add("underline");
            currentPlayer.O.classList.remove("underline");
            Reset(guid);
        }
    })

}
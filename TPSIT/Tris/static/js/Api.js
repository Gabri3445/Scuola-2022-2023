let url = "http://localhost:5258/api/TicTacToe/"

// let url = "http://gabri3445.ddns.net/api/TicTacToe/

async function postData(url = '', data = {}) {
    // Default options are marked with *
    return await fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        }, redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    }); // parses JSON response into native JavaScript objects
}

async function putData(url = '', data = {}) {
    // Default options are marked with *
    return await fetch(url, {
        method: 'PUT', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        }, redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    }); // parses JSON response into native JavaScript objects
}

/*
Response:
{
  "guid": "string"
}
 */
async function CreateMatch(username) {
    if (username === "") {
        return "No username"
    }
    let createUrl = url + "Create"
    let data = {
        username: username
    };
    let response = await postData(createUrl, data);
    let statusCode = response.status;
    if (statusCode === 200) {
        return response.json();
    }
    if (statusCode === 400) {
        return "Invalid";
    }
}

/*
Response:
{
  "username": "string"
}
 */
async function CheckP2Connection(guid) {
    let checkUrl = url + "CheckP2Connection?guid=" + guid;
    let response = await fetch(checkUrl);
    let statusCode = response.status;
    if (statusCode === 200) {
        return response.json();
    }
    if (statusCode === 400) {
        // TODO Consider replacing these with an object
        return "Invalid UUID"
    }
    if (statusCode === 404) {
        return "Not found"
    }
    if (statusCode === 406) {
        return "P2 not connected"
    }
}

/*
Response:
{
  "username": "string"
}
 */
async function ConnectP2(guid, username) {
    if (username === "") {
        return "Username Empty";
    }
    let connectUrl = url + "ConnectP2"
    let data = {
        guid: guid, username: username
    }
    let response = await putData(connectUrl, data);
    let statusCode = response.status;
    if (statusCode === 200) {
        return response.json();
    }
    if (statusCode === 400) {
        return "Invalid UUID"
    }
    if (statusCode === 404) {
        return "Not found"
    }
}

/*
Response:
{
  "rows": 3,
  "columns": 3,
  "flatBoard": [
    0,
    0,
    0,
    0,
    0,
    0,
    0,
    0,
    0,
  ]
}
 */
async function GetBoardStatus(guid) {
    let getBoardUrl = url + "GetBoardStatus?guid=" + guid;
    let response = await fetch(getBoardUrl);
    let statusCode = response.status;
    if (statusCode === 200) {
        let apiResponse = await response.json();
        const rows = apiResponse.rows;
        const columns = apiResponse.columns;
        const board = apiResponse.flatBoard;

        const twoDimensionalArray = [];

        for (let i = 0; i < rows; i++) {
            const startIndex = i * columns;
            const row = board.slice(startIndex, startIndex + columns);
            twoDimensionalArray.push(row);
        }
        return twoDimensionalArray;
    }
    if (statusCode === 400) {
        return "Invalid UUID"
    }
    if (statusCode === 404) {
        return "Match not found"
    }
}

/*

 */
async function MakeMove(guid, player, location) {
    let moveUrl = url + "MakeMove"
    let data = {
        guid: guid, player: player, location: {
            x: location.x, y: location.y
        }
    }
    let response = await putData(moveUrl, data);
    let statusCode = response.status;
    if (statusCode === 200) {
        return response.json();
    }
    if (statusCode === 400) {
        return "Invalid UUID or Invalid Request"
    }
    if (statusCode === 404) {
        return "Match not found"
    }
}

/*
Response:
{
  "player": 0
}
1 is X
2 is O
 */
async function GetPlayer(guid) {
    let getUrl = url + "GetPlayer?guid=" + guid;
    let response = await fetch(getUrl)
    let statusCode = response.status;
    if (statusCode === 200) {
        return response.json();
    }
    if (statusCode === 400) {
        return "Invalid UUID"
    }
    if (statusCode === 404) {
        return "Match not found"
    }
    if (statusCode === 406) {
        return "Match over"
    }
}

/*
Response:
{
  "matchStatus": 0
}
1 is X
2 is O
3 is Ongoing
4 is Draw
5 is O won
6 is X Won
 */
async function GetStatus(guid) {
    let checkUrl = url + "CheckWin?guid=" + guid;
    let response = await fetch(checkUrl);
    let statusCode = response.status;
    if (statusCode === 200) {
        return response.json();
    }
    if (statusCode === 400) {
        return "Invalid UUID"
    }
    if (statusCode === 404) {
        return "Match not found"
    }
}

// Returns 200 if the server is online
async function Ping() {
    let pingUrl = url + "Ping"
    let response = await fetch(pingUrl).then((response) => response.status);
    if (response !== 200) {
        return "Not online"
    }
    return "Online"
}

async function Reset(guid) {
    let resetUrl = url + "Reset"
    let data = {
        guid: guid
    }
    let response = await putData(resetUrl, data);
    let statusCode = response.status;
    if (statusCode === 200) {
        return response.json();
    }
    if (statusCode === 400) {
        return "Invalid UUID or Invalid Request"
    }
    if (statusCode === 404) {
        return "Match not found"
    }
}
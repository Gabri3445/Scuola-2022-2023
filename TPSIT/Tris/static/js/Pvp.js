function pvp() {
    let url = "http://127.0.0.1:5258/api/TicTacToe/"
    // let url = "http://gabri3445.ddns.net/api/TicTacToe/
    fetch(url).then((response) => response.json()
        .then((data) => console.log(data)));

    async function postData(url = '', data = {}) {
        // Default options are marked with *
        const response = await fetch(url, {
            method: 'POST', // *GET, POST, PUT, DELETE, etc.
            mode: 'cors', // no-cors, *cors, same-origin
            cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
            credentials: 'same-origin', // include, *same-origin, omit
            headers: {
                'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            redirect: 'follow', // manual, *follow, error
            referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
            body: JSON.stringify(data) // body data type must match "Content-Type" header
        });
        return response.json(); // parses JSON response into native JavaScript objects
    }

    async function putData(url = '', data = {}) {
        // Default options are marked with *
        const response = await fetch(url, {
            method: 'PUT', // *GET, POST, PUT, DELETE, etc.
            mode: 'cors', // no-cors, *cors, same-origin
            cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
            credentials: 'same-origin', // include, *same-origin, omit
            headers: {
                'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            redirect: 'follow', // manual, *follow, error
            referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
            body: JSON.stringify(data) // body data type must match "Content-Type" header
        });
        return response.json(); // parses JSON response into native JavaScript objects
    }

    async function CreateMatch(username) {
        let createUrl = url + "Create"
        let data = {
            username: username
        };
        return await postData(createUrl, data);
    }

    async function CheckP2Connection(guid) {
        let checkUrl = url + "CheckP2Connection?guid=" + guid;
        return await fetch(checkUrl).then((response) => response.json())
    }

    async function ConnectP2(guid, username) {
        let connectUrl = url + "ConnectP2"
        let data = {
            guid: guid,
            username: username
        }
        return await putData(connectUrl, data);
    }

    async function GetBoardStatus(guid) {
        let getBoardUrl = url + "GetBoardStatus?guid=" + guid;
        return await fetch(getBoardUrl).then((response) => response.json());
    }

    async function MakeMove(guid, player, location) {
        let moveUrl = url + "MakeMove"
        let data = {
            guid: guid,
            player: player,
            location: {
                x: location.x,
                y: location.y
            }
        }
        return await putData(moveUrl, data);
    }

    async function GetPlayer(guid) {
        let getUrl = url + "GetPlayer?guid=" + guid;
        return await fetch(getUrl).then((response) => response.json())
    }

    async function CheckWin(guid) {
        let checkUrl = url + "CheckWin?guid=" + guid;
        return await fetch(checkUrl).then((response) => response.json());
    }
}
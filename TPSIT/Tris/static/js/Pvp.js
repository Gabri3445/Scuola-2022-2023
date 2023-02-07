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
        return response; // parses JSON response into native JavaScript objects
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
        return response; // parses JSON response into native JavaScript objects
    }

    async function CreateMatch(username) {
        if (username === "") {
            return "No username"
        }
        let createUrl = url + "Create"
        let data = {
            username: username
        };
        return await postData(createUrl, data);
    }

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

    async function ConnectP2(guid, username) {
        if (username === "") {
            return "Username Empty";
        }
        let connectUrl = url + "ConnectP2"
        let data = {
            guid: guid,
            username: username
        }
        let response =  await putData(connectUrl, data);
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

    async function GetBoardStatus(guid) {
        let getBoardUrl = url + "GetBoardStatus?guid=" + guid;
        let response = await fetch(getBoardUrl);
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
        let response = await putData(moveUrl, data);
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
        if (statusCode === )
        if (statusCode === 406) {
            return "Match over"
        }
    }

    async function CheckWin(guid) {
        let checkUrl = url + "CheckWin?guid=" + guid;
        return await fetch(checkUrl).then((response) => response.json());
    }

    async function Ping() {
        let pingUrl = url +"Ping"
        let response = await fetch(pingUrl).then((response) => response.status);
        if (response !== 200) {
            return "Not online"
        }
    }
}
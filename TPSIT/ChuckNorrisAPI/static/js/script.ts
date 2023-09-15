let jokeSpan = document.querySelector(".joke") as HTMLSpanElement;
let jokeLink = document.querySelector(".link") as HTMLLinkElement;
let jokeCat = document.querySelector(".categorySelect") as HTMLSelectElement;
let jokeBtn = document.querySelector("#loadJoke") as HTMLButtonElement;
let copyBtn = document.querySelector(".copyButton") as HTMLButtonElement;

let jokeCategories = ["random", "animal","career","celebrity","dev","explicit","fashion","food","history","money","movie","music","political","religion","science","sport","travel"]

interface ApiResponse {
    icon_url: string;
    id: string;
    url: string;
    value: string;
}

jokeBtn.addEventListener("click", async () => {
    if (jokeCat.value === "") {
        let res = await fetch("https://api.chucknorris.io/jokes/random");
        let data = await res.json();
        jokeSpan.innerHTML = data.value;
        jokeLink.href = data.url;
        jokeLink.classList.remove("hidden");
    }
    else {
        let res = await fetch(`https://api.chucknorris.io/jokes/random?category=${jokeCat.value}`);
        let data = await res.json();
        jokeSpan.innerHTML = data.value;
        jokeLink.href = data.url;
        jokeLink.classList.remove("hidden");
    }
});

copyBtn.addEventListener("click", async () => {
    await navigator.clipboard.writeText(jokeSpan.innerHTML);
});
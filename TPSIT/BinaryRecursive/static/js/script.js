let numberElement = document.getElementById("number")
let resultElement = document.getElementById("result")
document.getElementById("enter").addEventListener("click", e => {
    e.preventDefault();
    let number = parseInt(numberElement.value);
    resultElement.innerHTML = recursiveBinary(number);
})

function recursiveBinary(n) {
    if (n === 0) {
        return ""
    }
    else {
        let r = n % 2;
        return recursiveBinary((n-r)/2) + r.toString()
    }
}
const formElement = document.querySelector("#form") as HTMLFormElement;
const usersElement = document.querySelector("#users") as HTMLDivElement;

class User {
    name: string;
    lastName: string;
    age: number;
    gender: string;

    constructor(name: string, lastName: string, age: number, gender: string) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }
}

const users: Array<User> = [];

formElement.addEventListener("submit", (e: SubmitEvent) => {
    e.preventDefault();
    const name = formElement.elements.namedItem("name") as HTMLInputElement;
    const lastName = formElement.elements.namedItem("lastname") as HTMLInputElement;
    const age = formElement.elements.namedItem("age") as HTMLInputElement;
    const gender = formElement.elements.namedItem("gender") as HTMLSelectElement;
    const user = new User(name.value, lastName.value, Number(age.value), gender.value);
    users.push(user);
    usersElement.innerHTML = "";
    usersElement.innerHTML += `<p> ${printUsers(users)} </p>`;
})

function printUsers(users: Array<User>): string {
    let result = "";
    users.forEach(user => {
        result += `Name: ${user.name} Last Name: ${user.lastName} Age: ${user.age} Gender: ${user.gender} <br>`;
    })
    return result;
}
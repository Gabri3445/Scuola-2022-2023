"use strict";
const formElement = document.querySelector("#form");
const usersElement = document.querySelector("#users");
class User {
    constructor(name, lastName, age, gender) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }
}
const users = [];
formElement.addEventListener("submit", (e) => {
    e.preventDefault();
    const name = formElement.elements.namedItem("name");
    const lastName = formElement.elements.namedItem("lastname");
    const age = formElement.elements.namedItem("age");
    const gender = formElement.elements.namedItem("gender");
    const user = new User(name.value, lastName.value, Number(age.value), gender.value);
    users.push(user);
    usersElement.innerHTML = "";
    usersElement.innerHTML += `<p> ${printUsers(users)} </p>`;
});
function printUsers(users) {
    let result = "";
    users.forEach(user => {
        result += `Name: ${user.name} Last Name: ${user.lastName} Age: ${user.age} Gender: ${user.gender} <br>`;
    });
    return result;
}

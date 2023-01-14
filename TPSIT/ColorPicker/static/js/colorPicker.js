const buttons = document.querySelectorAll('#buttons button');
buttons.forEach(button => {
    button.addEventListener('click', event => {
        const color = event.target.getAttribute('data-color');
        buttons.forEach(b => b.style.borderColor = "white")
        event.target.style.borderColor = "black";
        event.target.style.borderRadius = "15px";
        document.body.style.backgroundColor = color;
    });
});
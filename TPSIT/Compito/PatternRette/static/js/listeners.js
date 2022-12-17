let amplitudeInput = document.querySelector("[name=spacing]");

amplitudeInput.addEventListener("input", function (e) {
    space = parseInt(amplitudeInput.value);
    reset();
})
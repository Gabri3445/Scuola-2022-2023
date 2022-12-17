let amplitudeInput = document.querySelector("[name=spacing]");

amplitudeInput.addEventListener("input", function (e) {
    lW = parseInt(amplitudeInput.value);
    reset();
})
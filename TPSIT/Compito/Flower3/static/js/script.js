let w = 600;
let h = 300;
let diameter = 110;
let radius = diameter / 2;

function setup() {
    createCanvas(w, h)
    background("green");
}

function draw() {
    drawFlower(0, h/2, diameter);
    drawFlower(w / 2, h/2, diameter);
    drawFlower(w, h/2, diameter);
}

function drawFlower(x, y, d) {
    strokeWeight(4);
    stroke("black");
    fill("orange");
    circle(x - radius, y + radius, d)

    strokeWeight(4);
    stroke("black");
    fill("orange");
    circle(x + radius, y + radius, d)

    strokeWeight(4);
    stroke("black");
    fill("orange");
    circle(x - radius, y - radius, d)

    strokeWeight(4);
    stroke("black");
    fill("orange");
    circle(x + radius, y - radius, d)

    strokeWeight(4);
    stroke("black");
    fill("red");
    circle(x, h / 2, diameter)
}
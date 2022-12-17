let space = 10, x = 0, y = 0, w = 400, h = 400;


function setup() {
    createCanvas(w, h);
    background(220)
}

function draw() {
    stroke("white")
    if (random() < 0.5) {
        drawSquare(x, y, space, 0);
    } else {
        drawSquare(x, y, space, 1);
    }

    x += space
    if (x >= w) {
        x = 0;
        y += space;
    }
}

function drawSquare(x, y, l, type) {
    noStroke();
    if (type === 0) {
        fill("black");
    } else {
        fill("white");
    }
    square(x, y, l)
    if (type === 0) {
        fill("white");
    } else {
        fill("black");
    }

    circle(x + (l / 2) , y + (l / 2) , l /2);
}

function reset() {
    background("whites");
    x = 0;
    y = 0;
}
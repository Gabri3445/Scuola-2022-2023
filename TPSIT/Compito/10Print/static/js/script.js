let space = 10, x = 0, y = 0, w = 400, h = 400;


function setup() {
    createCanvas(w, h);
    background("black")
}

function draw() {
    stroke("white")
    if (random() < 0.5) {
        line(x, y, x + space, y + space)
    } else {
        line(x, y + space, x + space, y)
    }

    x += space
    if (x >= w) {
        x = 0;
        y += space;
    }
}

function reset() {
    background("black");
    x = 0;
    y = 0;
}
let w = 400, h = 400;
let xT = 0, yT = 0;
let lW = 20;

function reset() {
    xT = 0;
    yT = 0;
    background(220);
}

function setup() {
    createCanvas(w, h);
    background(220)
}

function draw() {
    let rand = random();
    if (rand < 0.25) {
        drawTile(xT, yT, lW, 0, 0);
    } else if (rand >= 0.25 && rand <= 0.50) {
        drawTile(xT, yT, lW, 0, 1);
    } else if (rand > 0.50 && rand <= 0.75) {
        drawTile(xT, yT, lW, 1, 0);
    } else {
        drawTile(xT, yT, lW, 1, 1);
    }
    xT += lW;
    if (xT >= w) {
        xT = 0;
        yT += lW;
    }


    /*stroke("white")
    if (random() < 0.5) {
        line(x, y, x + space, y + space)
    } else {
        line(x, y + space, x + space, y)
    }

    x += space
    if (x >= w) {
        x = 0;
        y += space;
    }*/
}

function drawTile(x, y, l, type, color) {
    noStroke();
    if (type === 0) {
        if (color === 0) {
            fill("white");
        } else if (color === 1) {
            fill("black");
        }
        triangle(x, y, x + l, y, x, y + l);
        if (color === 0) {
            fill("black");
        } else if (color === 1) {
            fill("white");
        }
        triangle(x, y + l, x + l, y + l, x + l, y);
    } else {
        if (color === 0) {
            fill("black");
        } else if (color === 1) {
            fill("white");
        }
        triangle(x, y, x + l, y, x + l, y + l);
        if (color === 0) {
            fill("white");
        } else if (color === 1) {
            fill("black");
        }
        triangle(x, y, x, y + l, x + l, y + l);
    }

}
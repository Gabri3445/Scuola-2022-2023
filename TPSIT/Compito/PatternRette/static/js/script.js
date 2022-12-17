let space = 10, x = 0, y = 0, w = 400, h = 400;


function setup() {
    createCanvas(w, h);
    background("black")
}

function draw() {
    stroke("white")
    let rand = random(-space, space);
    if (rand < 0.5) {
        line(x , y , x + space, y + rand)
    } else {
        line(x, y, x + space, y + rand)
    }

    x += space * 2
    if (x >= w) {
        x = 0;
        y += space * 2;
    }
}

function reset() {
    background("black");
    x = 0;
    y = 0;
}
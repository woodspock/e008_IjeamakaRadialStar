int n_sticks = 78;
Stick[] sticks = new Stick[n_sticks];
float rmin = 100;
float ang_rot = 0;

void setup() {
    size(800, 800);
    // for (float i = 0; i < TWO_PI; i += TWO_PI/sticks.length) {
        
    float ang = 0;
    for (int i = 0; i < sticks.length; i++) {
        sticks[i] = new Stick(new PVector(100*cos(ang), rmin* sin(ang)),ang, 200);
        sticks[i] = new Stick(new PVector(100*cos(ang), rmin* sin(ang)),ang, 200);
        ang += TWO_PI/sticks.length;
    }
    
    // reduce alternate stick length
    for(int i = 0; i < floor(sticks.length/2); i ++) {
        sticks[2*i].length = 100;
    }
}

void draw() {
    background(#75926f);
    translate(width / 2, height / 2);
    rotate(ang_rot);
    for (int i = 0; i < sticks.length; i++) {
        sticks[i].update();
        sticks[i].display();
    }
    ang_rot += 0.002;
}

class Stick {
    PVector origin;
    float angle;
    float length;    

    // Constructor
    Stick(PVector loc, float ang, float len) {
        origin = loc.copy();
        angle = ang;
        length = len;
    }

    void display() {
        stroke(255);
        fill(255);
        line(origin.x, origin.y, origin.x + length*cos(angle), origin.y + length* sin(angle));
        ellipse(origin.x + length*cos(angle), origin.y + length* sin(angle), 3, 3);

    }

    void update() {
        length = constrain(length + 2*(noise(millis()/500 + angle)-0.5), rmin, width/2);
    }
}
import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class e008_IjeamakaRadialStar extends PApplet {

int n_sticks = 78;
Stick[] sticks = new Stick[n_sticks];
float rmin = 100;
float ang_rot = 0;

public void setup() {
    
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

public void draw() {
    background(0xff75926f);
    translate(width / 2, height / 2);
    rotate(ang_rot);
    for (int i = 0; i < sticks.length; i++) {
        sticks[i].update();
        sticks[i].display();
    }
    ang_rot += 0.002f;
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

    public void display() {
        stroke(255);
        fill(255);
        line(origin.x, origin.y, origin.x + length*cos(angle), origin.y + length* sin(angle));
        ellipse(origin.x + length*cos(angle), origin.y + length* sin(angle), 3, 3);

    }

    public void update() {
        length = constrain(length + 2*(noise(millis()/500 + angle)-0.5f), rmin, width/2);
    }
}
  public void settings() {  size(800, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "e008_IjeamakaRadialStar" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

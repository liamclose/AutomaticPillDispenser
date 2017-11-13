#include <Stepper.h>

const int stepsPerRevolution = 256;  // 2048 divided into 8 sections equally
int inputPin = 6;

// initialize the stepper library on pins 8 through 11:
Stepper myStepper(stepsPerRevolution, 8, 9, 10, 11);

void setup() {
  // set the speed at 60 rpm:
  myStepper.setSpeed(60);
  // initialize the serial port:
  Serial.begin(9600);
}

void loop() {
  //read input pin for high signal and turn motor if true
  while(digitalRead(inputPin)==HIGH){
      Serial.println("clockwise");
      myStepper.step(stepsPerRevolution);
      delay(500);
  }  
}

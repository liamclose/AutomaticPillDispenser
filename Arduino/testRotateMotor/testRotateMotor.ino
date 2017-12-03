#include <Stepper.h>

const int stepsPerRevolution = 256;  // 2048 divided into 8 sections equally
int inputPin = 6; // set pin 6 to be input pin

//testing variables: Connect digital pins 4(output) and 6(input) with wire to work
boolean flag = true; //for testing input to motor, set to true to test or false to run normal
int numOfTurns = 2; //set number of turns you want the output signal to be high (testing purposes only)
int count = 0; //tracks the amount of times the motor has turned (testing purposes only)
int outputPin = 4; // set pin 4 to be output pin

// initialize the stepper library on pins 8 through 11:
Stepper myStepper(stepsPerRevolution, 8, 9, 10, 11);

void setup() {
  // testing setup
  pinMode(inputPin, INPUT); //set pin 6 as input pin
  pinMode(outputPin, OUTPUT); //set pin 4 as output pin

  // set the speed at 60 rpm:
  myStepper.setSpeed(60);
  // initialize the serial port:
  Serial.begin(9600);

}

void loop() {
  if(flag){    
   toggleMotor(); //give the motor a signal
  }
  while(digitalRead(inputPin)==HIGH){
      Serial.println("clockwise");
      myStepper.step(stepsPerRevolution);
      delay(500);
      count++;
      if(count>=numOfTurns){
        toggleMotor(); //turn signal off now that expected number of turns has occured
        flag=false;
      }
  }  
}

//method for testing purposes only. Sends signal to input pin to turn motor on/off
void toggleMotor(){
  if(digitalRead(outputPin)==HIGH){
    digitalWrite(outputPin, LOW);
  }
  else{
    digitalWrite(outputPin, HIGH);
  }
}

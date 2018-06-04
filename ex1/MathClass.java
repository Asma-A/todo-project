package com.mytestapp;

public class MathClass {
	
    int a, b;
	
    MathClass(int a, int b) {
        this.a = a;
        this.b = b;
    }
	
    public int mod() {
        return a % b;
    }
}
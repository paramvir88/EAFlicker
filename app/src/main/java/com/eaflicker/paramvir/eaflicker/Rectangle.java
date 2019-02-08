package com.eaflicker.paramvir.eaflicker;

public class Rectangle {

    private int length;
    private int breadth;

   public Rectangle(int l, int b){
        length = l;
        breadth = b;
    }

    public int area(){
       return  length * breadth;
    }
}

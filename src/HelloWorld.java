/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kingtin
 */
public class HelloWorld {
    private String name;
    private int count;
    private float test;
    
    public HelloWorld() {
    		name = "King";
    		count = 10;
                System.out.println(count);
    }
    
    public void printSomething() {
    		name = "Testing";
                count = 2;
    }

    public int computeSomething(){
        return 1;
    }
    
}

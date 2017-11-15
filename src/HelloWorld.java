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
    private int another;
    private int anotherInt;
    private float test1;
    
    public HelloWorld() {
    		name = "King";
    		count = 10;
                System.out.println(count);
    }
    
    public void today() {
    		System.out.println("Today is Thursday.");
    		return;
    }
    
        
    public void tomorrow() {
        System.out.println("Tomw is Saturday.");
    	return;
    }
    
    public int computeSomething(){
        return 1;
    }
    
}

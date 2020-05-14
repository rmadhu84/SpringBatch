package com.madhu.spring.lambda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.madhu.spring.lambda.service.StringLength;
import com.madhu.spring.lambda.service.GreetingService;
import com.madhu.spring.lambda.service.impl.HelloWorldGreetingImpl;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
public class LambdaFeaturesApplication implements CommandLineRunner{

	public void greet(GreetingService service) {
		System.out.println("Simple: Hello World!!");
		service.perform();
		
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(LambdaFeaturesApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		greet(new HelloWorldGreetingImpl());
		
		GreetingService myLambdaFuntion = () -> System.out.println("Lambda says Hello !!");
		
		//myLambdaFuntion.perform();
		greet(myLambdaFuntion);
		
		//Anonymous inner class
		GreetingService innerClassGreeting = new GreetingService() {
			public void perform() {
				System.out.println("Anonymous Inner Class says Hello !!");
			}
		};
		//innerClassGreeting.perform();
		greet(innerClassGreeting);
		
		///////////////////////////////////////////////////////////////////
		//StringLength s = (String input) -> input.length();
		StringLength s = input -> input.length();
		System.out.println(s.getLength("Hello World"));
		
		printLambda(str -> str.length());
		
		/**
		 * Even though the Thread and Runnables were available from older java versions,
		 * you can still use the new lambda expressions with them, due to backward compatibility.
		 * The only criteria, is that the interface must contain only one method.
		 * The Runnable has only one method run().
		 */
		Thread oldWayThread = new Thread(new Runnable() {
			public void run() {
				System.out.println("Printing from thread, the old way");
			}
		});
		
		oldWayThread.run();
		
		Thread myThread =  new Thread(()->System.out.println("Printed from Lambda runnable"));
		myThread.run();
	}

	public static void printLambda(StringLength l) {
		log.info(String.valueOf(l.getLength("printLambda says Hello")));
	}
}


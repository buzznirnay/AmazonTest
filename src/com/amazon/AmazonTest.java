package com.amazon;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AmazonTest {
	
	public static WebDriver driver = null;
	public static Connection connection = null;

	public static void launchchrome() {
		 System.setProperty("webdriver.chrome.driver","C:\\Users\\buzzn\\Downloads\\chromedriver.exe");    
		    driver =new ChromeDriver();
		    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // Implicit wait for browser
		    driver.manage().window().maximize();	//Maximize the window
	}
	
	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub		
		
		  	try{
		  		connection = Sql_Connector.getConnection();
		  		launchchrome();
			    driver.get("https://www.amazon.in/"); 	//Load the web page
			    driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys("mobiles");
			    driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]")).click();;
			    Thread.sleep(2000);		   
			    By ProductName=By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[5]/div/span/div/div/div[2]/div[2]/div/div/div[1]/h2/a/span");
			    By Pprice=By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[7]/div/span/div/div/div[2]/div[2]/div/div/div[3]/div[1]/div/div[1]/div[2]/a/span[1]/span[2]/span[2]");
			    String prod1= driver.findElement(ProductName).getText();
			    String pr1= driver.findElement(Pprice).getText();
			    pr1 = pr1.replace(",", "");
			    System.out.println("First Product Name: " + prod1); // print first product name
			    
			   System.out.println("First Product price: " + pr1); // print first product name
			   
			   Statement stmt = connection.createStatement();
			   String sql = String.format("INSERT INTO amazonlist VALUES (NULL, '%s', '%s')", prod1, pr1);
			   int count= stmt.executeUpdate(sql);
			   if (count > 0) {
		            System.out.println("Insertion Successful");
		        } else {
		            System.out.println("Failure");
		        }
		  	}
		  	catch(Exception e) {
		  		e.printStackTrace();
		  	}
		  	finally {
		  		connection.close();
		  		//driver.quit();
		  	}
		  	
	}

}

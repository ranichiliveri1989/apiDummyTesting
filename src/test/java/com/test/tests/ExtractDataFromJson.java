package com.test.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;


public class ExtractDataFromJson {
	
	@Test
	public void readingData() throws FileNotFoundException
	{
	 String curDir = System.getProperty("user.dir");
	 
     File file = new File(curDir+"/src/test/resources/tekarch.json");
     
     JsonPath data=new JsonPath(file);
   
     //(1)find value of username  
     
     ArrayList<String> s12=data.get("username");
     System.out.println("value of all username:   "+s12);
    // Assert.assertEquals(s12,"156");
     
     //(2)find values of all studentid

     ArrayList<String> s11=data.get("students.id");
     System.out.println("values of all studentid:  "+s11);
     
     //(3)find  last value of studentid
     
     int size2= data.get("[1].students[1].id");
     System.out.println("last value of studentid:   "+size2);

     //(4)find username of 1st university

     String s=data.get("[0].username");
     System.out.println("username of 1st university:   "+s);
     
     //(5)find name of 2nd university
     String s1=data.get("[1].name");
     System.out.println("name of 2nd university:  "+s1);
     
     //(6)find number of universites in the list
     int size= data.get("size()");
     System.out.println("number of universites in the list:  "+size);
     
     //(7)find all marks of second student of 1st university
     ArrayList<String> s15=data.get("[0].students[1].marks");
     System.out.println("all marks of second student of 1st university:  "+s15);
     
     
     //(8)find the second state(in the address) value of first student of 1st university

     String s2=data.get("[0].students[1].adresss[1].state");
     System.out.println("second state(in the address) value of first student of 1st university:  "+s2);
     
     //(9)find the second contact value of second student of 2nd university
     
     String s3=data.get("[1].students[1].contact[1]");
     System.out.println("the second contact value of second student of 2nd university:  "+s3);
     
     //(10)find all cities of second student of 2nd university

     ArrayList<String> s10=data.get("[1].students[1].adresss.city");
     System.out.println("all cities of second student of 2nd university:   "+s10);
     
     //(11)find names of all students
        
     ArrayList<String> s17=data.get("students.name");
     System.out.println("names of all students:  "+s17);
     

     //(12)find contacts of all students
     
     ArrayList<String> s18=data.get("students.contact");
     System.out.println("contacts of all students:  "+s18);
  
    //(13)find adresses of first student of 1st university

     ArrayList<String> s16=data.get("[0].students[0].adresss");
     System.out.println("adresses of first student of 1st university:  "+s16);
       
     
    // (14)find number of adresses of second student of 2nd university
     
     int  s4=data.get("[1].students[1].adresss.size()");
     System.out.println("number of adresses of second student of 2nd university:   "+s4);
     
     
     //(15)find the number of student records of 2nd university
     int s5=data.get("[1].students.size()");
     System.out.println("number of student records of 2nd university:  "+s5);
     
     //(16)find the number of contacts of second student of 1st university
     int s6=data.get("[0].students[1].contact.size()");
     System.out.println("number of contacts of second student of 1st university:   "+s6);
     
	}
	
}

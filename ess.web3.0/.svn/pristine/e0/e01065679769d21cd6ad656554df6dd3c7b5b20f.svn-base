package com.test;
import com.google.gson.Gson;

class Person{
	Person(){
		
	}
	
	Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	String name;
	int age;
}

public class App {
	private static Gson gson = new Gson();
	public static void main(String[] args){
		Person person = new Person("John",30);
		String personToJson = gson.toJson(person);
		System.out.println(personToJson);
		person = null;
		person = gson.fromJson(personToJson, Person.class);
		System.out.println("this person.name = "+person.name+" & this person.age = "+person.age);
	}
}
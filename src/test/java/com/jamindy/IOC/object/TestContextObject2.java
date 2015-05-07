package com.jamindy.IOC.object;

public class TestContextObject2 {

	private String name;
	
	private int age;
	
	private TestContextObject1 object1;

	public TestContextObject2(String name, int age,
                              TestContextObject1 object1) {
		this.name = name;
		this.age = age;
		this.object1 = object1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public TestContextObject1 getObject1() {
		return object1;
	}

	public void setObject1(TestContextObject1 object1) {
		this.object1 = object1;
	}
	

}

package com.Model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

// определяем корневой элемент
@XmlRootElement(name = "Person")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"id", "name", "age"})
public class Person implements Serializable {

	private Integer id;
	//@JsonProperty("name")
	private String name;
	//@JsonProperty("age")
	private Integer age;

	public Person(Integer id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Person() {

	}

	// указываем, что id должно быть атрибутом
	@XmlAttribute
	public Integer getId() {
		return id;
	}

	// указываем, что поле name должно быть представлено в XML как name
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	// указываем, что поле age должно быть представлено в XML как age
	@XmlElement(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}

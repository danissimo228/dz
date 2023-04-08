package ru.nishpal.practic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class PracticApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticApplication.class, args);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


		FirstBean firstBean = context.getBean(FirstBean.class);
		System.out.println(firstBean);

		SecondBean secondBean = new SecondBean();
		System.out.println(secondBean);

		ThirdBean thirdBean = new ThirdBean();
		System.out.println(thirdBean);
	}

}

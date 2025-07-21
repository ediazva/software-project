package org.unsa.softwareproject;

import org.springframework.boot.SpringApplication;
import org.unsa.model.SoftwareProjectApplication;

public class TestSoftwareProjectApplication {

	public static void main(String[] args) {
		SpringApplication.from(SoftwareProjectApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

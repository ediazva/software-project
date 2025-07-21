package org.unsa.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class SoftwareProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(org.unsa.model.SoftwareProjectApplication.class, args);
	}
}
@Controller
@RequestMapping("")
class SoftwareProjectApplicationController {
	public static void main(String[] args) {
		SpringApplication.run(SoftwareProjectApplication.class, args);
	}
}
package be.iccbxl.poo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import be.iccbxl.poo.data.Data;
import be.iccbxl.poo.data.IData;
import be.iccbxl.poo.logic.ILogic;
import be.iccbxl.poo.logic.Logic;
import be.iccbxl.poo.presentation.IPresentation;
import be.iccbxl.poo.presentation.Presentation;

@Configuration
public class AppConfiguration {
	
	@Bean
	public IPresentation getIprensentation() {
		return new Presentation();
	}
	
	@Bean
	public ILogic getILogic() {
		return new Logic();
	}
	
	@Bean 
	public IData getIData() {
		return new Data("data\\save.xml");
	}
}

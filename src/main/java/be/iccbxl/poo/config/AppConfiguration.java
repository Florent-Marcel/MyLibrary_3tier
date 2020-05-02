package be.iccbxl.poo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import be.iccbxl.poo.data.DataCSV;
import be.iccbxl.poo.data.DataXML;
import be.iccbxl.poo.data.IData;
import be.iccbxl.poo.logic.ILogic;
import be.iccbxl.poo.logic.Logic;
import be.iccbxl.poo.presentation.IPresentation;
import be.iccbxl.poo.presentation.Presentation;

@Configuration
public class AppConfiguration {
	
	@Bean 
	public IData getIData() {
		//return new DataXML("data\\save.xml");
		return new DataCSV("data");
	}
	
	@Bean
	public ILogic getILogic() {
		Logic l = new Logic();
		l.setData(getIData());
		return l;
	}
	
	@Bean
	public IPresentation getIpresentation() {
		Presentation p = new Presentation();
		p.setLogic(getILogic());
		return p;
	}

}

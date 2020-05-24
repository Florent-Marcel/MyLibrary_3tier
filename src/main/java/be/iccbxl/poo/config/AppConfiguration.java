package be.iccbxl.poo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.iccbxl.poo.data.DataCSV;
import be.iccbxl.poo.data.DataXML;
import be.iccbxl.poo.data.IData;
import be.iccbxl.poo.logic.ILogic;
import be.iccbxl.poo.logic.Logic;
import be.iccbxl.poo.presentation.IPresentation;
import be.iccbxl.poo.presentation.Presentation;

/**
 * Configuration Spring via Annotations. Pour la configuration XML, voir fichier AppXMLConfiguration.xml.
 * @author Marcel F.
 */
@Configuration
public class AppConfiguration {
	
	/**
	 * Renvoie une instance de IData. commentez/décommentez pour changer entre csv et xml.
	 * @return une instance de IData.
	 */
	@Bean 
	public IData getIData() {
		return new DataXML("data\\save.xml");
		//return new DataCSV("data");
	}
	
	/**
	 * Retourne une instance de ILogic.
	 * @return Une instance de ILogic.
	 */
	@Bean
	public ILogic getILogic() {
		Logic l = new Logic();
		l.setData(getIData());
		return l;
	}
	
	/**
	 * Returne une instance de IPresentation.
	 * @return Une instance de Ipresentation.
	 */
	@Bean
	public IPresentation getIpresentation() {
		Presentation p = new Presentation();
		p.setLogic(getILogic());
		return p;
	}

}

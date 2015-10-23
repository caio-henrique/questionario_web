package br.com.softbox.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.beanutils.ConversionException;

import br.com.softbox.model.Opcao;
import br.com.softbox.rn.OpcaoRN;

public class OpcaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if( value != null && value.trim().length() > 0 ){
			
			Long identificador = Long.valueOf( value );
			
			try {
				
				OpcaoRN opcaoRN = new OpcaoRN();
				return opcaoRN.carregar(identificador);
			}
			
			catch ( Exception erro ) {
				
				throw new ConversionException("Não foi possível encontrar a opção de código " +  value + ". " + erro.getMessage() );
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if( value != null ){
			
			Opcao opcao = (Opcao) value;
			return Long.toString( opcao.getId());
		}
		
		return null;
	}
}

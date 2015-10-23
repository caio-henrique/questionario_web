package br.com.softbox.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.beanutils.ConversionException;

import br.com.softbox.model.Questionario;
import br.com.softbox.rn.QuestionarioRN;

@FacesConverter( forClass = Questionario.class )
public class QuestionarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if( value != null && value.trim().length() > 0 ){
			
			Long identificador = Long.valueOf( value );
			
			try {
				
				QuestionarioRN questionarioRN = new QuestionarioRN();
				return questionarioRN.carregar(identificador);
			}
			
			catch ( Exception erro ) {
				
				throw new ConversionException("Não foi possível encontrar o questionario de código " +  value + ". " + erro.getMessage() );
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if( value != null ){
			
			Questionario questionario = (Questionario) value;
			return Long.toString( questionario.getId());
		}
		
		return null;
	}
}
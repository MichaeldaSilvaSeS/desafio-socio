package br.com.api.socio.platform.mapper.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import br.com.api.socio.exception.ConversaoDeDTO;
import br.com.api.socio.platform.mapper.config.ConverterConfigurerSupport;

@Component
public class StringToDateConverter extends ConverterConfigurerSupport<String,Date>{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	protected Converter<String, Date> converter() {
		return new Converter<String, Date>() {
			@Override
			public Date convert(MappingContext<String, Date> context) {						
				try {
					return sdf.parse(context.getSource());
				} catch (ParseException e) {
					throw new ConversaoDeDTO(e);
				}
			}			
		};
	}
}

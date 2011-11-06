package org.polishcalendar.ds;

import java.util.*;

import org.polishcalendar.translators.Builder;
import org.polishcalendar.translators.ClassDescriptor;

import com.gwtent.reflection.client.Reflectable;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

@Reflectable
public class DataSourceBuilder<T> implements Builder<T, GwtRpcDataSource>{
	private ClassDescriptor<T> _descriptor;

	public DataSourceBuilder(ClassDescriptor<T> descriptor) {
		_descriptor = descriptor;
	}
	
	public void build(GwtRpcDataSource out) {
		List<String> fieldNames = _descriptor.getGettableFields();
		DataSourceField[] fields = new DataSourceField[fieldNames.size()];
		int index = 0;
		for(String fieldName : fieldNames){
			DataSourceField field;
			if(fieldName.equals("Id")){
				field = new DataSourceIntegerField(fieldName);
				field.setPrimaryKey(true);
				//field.setHidden(true);
			}
			else if(fieldName.contains("Date")){
				field = new DataSourceDateField(fieldName);
			}
			else if(fieldName.contains("Attendees") || fieldName.contains("Number")){
				field = new DataSourceIntegerField(fieldName);
			}
			else{
				field  = new DataSourceTextField(fieldName);
				field.setRequired(true);
			}
			fields[index] = field;
			index++;
		}
		out.setFields(fields);
	}
}

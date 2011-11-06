package org.polishcalendar.translators;

import java.util.List;

import com.smartgwt.client.data.Record;

public class ServerToRecordTranslator<T> implements Translator<T, Record> {

	private ClassDescriptor<T> _descriptor;

	public ServerToRecordTranslator(ClassDescriptor<T> descriptor) {
		_descriptor = descriptor;
	}
	
	@Override
	public Record translate(T source) {
		Record res = new Record();
		List<String> fieldNames = _descriptor.getGettableFields();
		for(String fieldName : fieldNames){
			Object param = _descriptor.invoke(source, "get" + fieldName, new Object[0]);
			res.setAttribute(fieldName.toLowerCase(), param);
		}
		return res;
	}

	@Override
	public T translateBack(Record source) {
		List<String> fieldNames = _descriptor.getSettableFields();
		T ret = _descriptor.newInstance();
		for(String fieldName : fieldNames){
			Object param = source.getAttributeAsObject(fieldName.toLowerCase());
			if(param != null)
				_descriptor.invoke(ret, "set" + fieldName, 	param);
		}
	return ret;	
	}

}

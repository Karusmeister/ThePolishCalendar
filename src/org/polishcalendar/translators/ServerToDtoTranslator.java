package org.polishcalendar.translators;

import java.util.*;

public class ServerToDtoTranslator<T1, T2> implements Translator<T1, T2> {
	
	
	private ClassDescriptor<? extends T1> _source;
	private ClassDescriptor<? extends T2> _target;

	public ServerToDtoTranslator(ClassDescriptor<? extends T1> from, ClassDescriptor<? extends T2> to) {
		_source = from;
		_target = to;
	}
	
	@Override
	public T2 translate(T1 source) {
		List<String> gettables = _source.getGettableFields();
		List<String> settables = _target.getSettableFields();
		List<String> common = new LinkedList<String>();
		for(String gettable : gettables){
			if(settables.contains(gettable))
				common.add(gettable);
		}
		T2 ret = _target.newInstance();
		for(String fieldName : common){
			Object param = _source.invoke(source, "get" + fieldName, new Object[0]);
			if(param != null)
				_target.invoke(ret, "set" + fieldName, 	param);
		}
		return ret;
	}

	@Override
	public T1 translateBack(T2 source) {
		List<String> gettables = _target.getGettableFields();
		List<String> settables = _source.getSettableFields();
		List<String> common = new LinkedList<String>();
		for(String gettable : gettables){
			if(settables.contains(gettable))
				common.add(gettable);
		}
		T1 ret = _source.newInstance();
		for(String fieldName : common){
			Object param = _target.invoke(source, "get" + fieldName, new Object[0]);
			if(param != null)
				_source.invoke(ret, "set" + fieldName, 	param);
		}
		return ret;
	}

}

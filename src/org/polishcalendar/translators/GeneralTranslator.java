package org.polishcalendar.translators;

import java.util.*;

public class GeneralTranslator<T1, T2> implements Translator<T1, T2> {
	
	
	private ClassDescriptor<? extends T1> _from;
	private ClassDescriptor<? extends T2> _to;

	public GeneralTranslator(ClassDescriptor<? extends T1> from, ClassDescriptor<? extends T2> to) {
		_from = from;
		_to = to;
	}
	
	@Override
	public T2 translate(T1 source) {
		List<String> gettables = _from.getGettableFields();
		List<String> settables = _to.getSettableFields();
		List<String> common = new LinkedList<String>();
		for(String gettable : gettables){
			if(settables.contains(gettable))
				common.add(gettable);
		}
		T2 ret = _to.newInstance();
		for(String fieldName : common){
			Object param = _from.invoke(source, "get" + fieldName, new Object[0]);
			if(param != null)
				_to.invoke(ret, "set" + fieldName, 	param);
		}
		return ret;
	}

}

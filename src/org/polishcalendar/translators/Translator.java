package org.polishcalendar.translators;

public interface Translator<T1, T2> {
	
	public T2 translate(T1 source);

}

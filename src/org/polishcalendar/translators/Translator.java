package org.polishcalendar.translators;

public interface Translator<Source, Target> {
	
	public Target translate(Source source);

	public Source translateBack(Target source);
}

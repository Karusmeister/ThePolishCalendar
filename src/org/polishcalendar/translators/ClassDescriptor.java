package org.polishcalendar.translators;

import java.util.*;

import com.gwtent.reflection.client.*;

@Reflectable
public class ClassDescriptor<T1> {
	private ClassType<T1> _classType;
	private Method[] _methods;

	private List<String> _getters;
	private List<String> _setters;
	private List<String> _gettables;
	private List<String> _settables;

	public ClassDescriptor(Class<T1> classType) {
		_classType = TypeOracle.Instance.getClassType(classType);
		_methods = _classType.getMethods();
	}

	public List<String> getGettableFields() {
		if (_gettables == null) {
			_gettables = new LinkedList<String>();
			for (String methodName : getPublicGetters()) {
				String fieldName = methodName.substring(3);
				_gettables.add(fieldName);
			}
		}
		return _gettables;
	}
	
	public List<String> getSettableFields() {
		if (_settables == null) {
			_settables = new LinkedList<String>();
			for (String methodName : getPublicSetters()) {
				String fieldName = methodName.substring(3);
				_settables.add(fieldName);
			}
		}
		return _settables;
	}

	public List<String> getPublicGetters() {
		if (_getters == null)
			_getters = GetMethods("get", "Get");
		return _getters;
	}

	public List<String> getPublicSetters() {
		if (_setters == null)
			_setters = GetMethods("set", "Set");
		return _setters;
	}

	private List<String> GetMethods(String... prefixes) {
		List<String> res = new LinkedList<String>();
		for (Method method : _methods) {
			if (Modifier.isPublic(method.getModifiers())) {
				String methodName = method.getName();
				for (String prefix : prefixes) {
					if (methodName.startsWith(prefix))
						res.add(methodName);
				}
			}
		}
		return res;
	}
	
	public T1 newInstance(){
		return _classType.findConstructor().newInstance();
	}
	
	public Object invoke(Object instance, String methodName, Object... params){
		return this._classType.invoke(instance, methodName, params);
	}

	// ClassHelper<?> helper = ClassHelper.AsClass(this.getClass());
	// ParameterizedType parameterizedType = (ParameterizedType)
	// helper.getGenericSuperclass();
	// ClassType<T1> c = (ClassType<T1>)
	// parameterizedType.getActualTypeArguments()[0];
}

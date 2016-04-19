package org.quartz.ui.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbUtil {

	@SuppressWarnings("unchecked")
	public static <T> T unmarshal(InputStream is, Class<T> cls) throws JAXBException {
		Object result = null;
		JAXBContext jaxbContext = JAXBContext.newInstance(cls);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		result = jaxbUnmarshaller.unmarshal(is);
		return (T) result;
	}

	public static <T> T unmarshal(String content, Class<T> cls) throws UnsupportedEncodingException, JAXBException {
		return unmarshal(new ByteArrayInputStream(content.getBytes("utf-8")), cls);
	}

	public static String marshal(Object root, Class<?> className) throws JAXBException, UnsupportedEncodingException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(className);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(root, out);
		String result = new String(out.toByteArray(), "utf-8");
		return result;
	}
}

package com.htrj.core.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import freemarker.cache.TemplateLoader;

public class StringTemplateLoader implements TemplateLoader {
	
	private String template;
	public StringTemplateLoader(String template) {
		this.template = template;
		if (template == null)
			this.template = "";
	}
	@Override
	public void closeTemplateSource(Object arg0) throws IOException {
		// TODO Auto-generated method stub
		((StringReader) arg0).close();

	}

	@Override
	public Object findTemplateSource(String arg0) throws IOException {
		// TODO Auto-generated method stub
		return new StringReader(this.template);
	
	}

	@Override
	public long getLastModified(Object arg0) {
		// TODO Auto-generated method stub
		return 0L;
	}

	@Override
	public Reader getReader(Object arg0, String arg1) throws IOException {
		// TODO Auto-generated method stub
		return (Reader) arg0;
	}

}

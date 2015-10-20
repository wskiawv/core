package com.htrj.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import freemarker.cache.TemplateLoader;

public class InputStreamTemplateLoader implements TemplateLoader {
	
	private InputStream inputStream;
	
	public InputStreamTemplateLoader(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void closeTemplateSource(Object arg0) throws IOException {
		// TODO Auto-generated method stub
		((InputStreamReader) arg0).close();

	}

	public Object findTemplateSource(String arg0) throws IOException {
		// TODO Auto-generated method stub
		return new InputStreamReader(this.inputStream, "UTF-8");
	}

	public long getLastModified(Object arg0) {
		// TODO Auto-generated method stub
		return 0L;
	}

	public Reader getReader(Object arg0, String arg1) throws IOException {
		// TODO Auto-generated method stub
		return (InputStreamReader) arg0;
	}

}

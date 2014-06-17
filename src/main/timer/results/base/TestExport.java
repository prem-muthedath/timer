package timer.results.base;

import timer.reporting.base.Format;

import java.util.Map;
import java.util.EnumMap;

class TestExport {
	private Field[] fields;
	private enum Schema {ROW, COLUMN, CONTENT}

	TestExport(Field key, Field subkey, Field timing)  {
		fields=new Field[]{key, subkey, timing};
	}

	void dumpTo(Format format) {
		Map<Schema, String> dump=new EnumMap<Schema, String>(Schema.class);
		for(int i=0; i < Schema.values().length; i++)
			dump.put(Schema.values()[i], fields[i].format(format));
		format.add(dump.get(Schema.COLUMN), dump.get(Schema.ROW), dump.get(Schema.CONTENT));
	}
}

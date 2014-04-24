package timer.output.base;

import java.util.Map;
import java.util.LinkedHashMap;

public abstract class Format {
	private Row columns=new Row();
	private Map<String, Row> rows=new LinkedHashMap<String, Row>();

	public abstract void addBySize(int size, String method, double timing);
	public abstract void addByMethod(int size, String method, double timing);
	
	protected void add(String column, String row, String content)  {
		columns.addUnique(new Header(column));
		row(row).add(new Content(content));
	}

	private Row row(String row) {
		if(!rows.containsKey(row))
			rows.put(row, new Row());
		return rows.get(row);
	}

	public void print() {
		printRow("", columns);
		for (String each : rows.keySet()) 
			printRow(each, rows.get(each));
	}

	private void printRow(String header, Row row)  {
		row.print(header, this);	
		printContent(rowSeperator());
	}

	protected abstract void printHeader(String header);
	protected abstract void printContent(String content);	
	protected abstract String rowSeperator();	
}


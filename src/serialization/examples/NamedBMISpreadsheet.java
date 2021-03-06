package serialization.examples;

import java.io.Externalizable;

public interface NamedBMISpreadsheet extends BMISpreadsheet, Externalizable {
	String getName();
	void setName(String newValue);
	boolean isOverWeight();
}

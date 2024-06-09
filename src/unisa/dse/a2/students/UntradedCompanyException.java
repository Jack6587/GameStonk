package unisa.dse.a2.students;

public class UntradedCompanyException extends Exception
{
	public UntradedCompanyException(String companyCode) { // accepts a company code parameter
		super(companyCode + " is not a listed company on this exchange."); // calls constructor of Exception (superclass) with an error message
	}
}

import java.io.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.*;
import org.apache.jena.datatypes.*;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.listeners.*;
import org.apache.jena.reasoner.*;
import org.apache.jena.shared.*;
import org.apache.jena.vocabulary.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.query.*;
import org.apache.jena.atlas.io.IndentedWriter;
import java.util.Scanner; 

public class Main {
	
	public static void main(String args[]){
		//dhmiourgoume to model
		Model model= ModelFactory.createDefaultModel();
		
		//fortwnoume ta periexomena tou rdf sto model
		InputStream in = FileManager.get().open("hey.rdf");
		model.read(in, "");
		
		//dhmiourgoume to query pou 8a epistrepsei ta dia8esima tmhmata
		String queryString="PREFIX plut: <http://mydomain.org/plut#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ?name WHERE { ?xi rdf:type plut:Department . ?xi plut:dep_name ?name}";
		Query query= QueryFactory.create(queryString) ;
		
	    final String[] data = new String[200];
		
		//ektupwnoume ta tmhmata ws ari8mhmenes epiloges
		try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
		    ResultSet results = qexec.execSelect() ;
	    	int i=1;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      RDFNode x = soln.get("name") ; 
		      data[i] = x.toString();
		      System.out.println("option" + i + ":" + x.toString());
		      i++;
		    }
		}
		    //pairnoume thn epilogh tou xrhsth
	        System.out.println("Give me the option number: (1, 2, 3 etc.)");
	        Scanner reader = new Scanner(System.in);  // Reading from System.in
	        String input ;
	        input = reader.next( );
	        int foo = Integer.parseInt(input);
	        	//dhmiourgoume to antistoixo query
	    		String queryString2="PREFIX plut: <http://mydomain.org/plut#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ?name ?age ?phone WHERE { ?x rdf:type plut:Professor . ?x plut:has_name ?name . ?x plut:has_age ?age . ?x plut:has_phone ?phone . ?x plut:member_of ?dep . ?dep plut:dep_name '"+data[foo]+"'}";
	    		Query query2= QueryFactory.create(queryString2) ;
	    		
	    		try (QueryExecution qexec = QueryExecutionFactory.create(query2, model)) {
	    		    ResultSet results2 = qexec.execSelect() ;
	    		    for ( ; results2.hasNext() ; )
	    		    {
	    		      QuerySolution soln = results2.nextSolution() ;
	    		      RDFNode xi = soln.get("name") ; 
	    		      RDFNode y = soln.get("age") ; 
	    		      RDFNode z = soln.get("phone") ; 
	    		      // ektupwnoume ta apotelesmata sth morfh pou zhteitai
	    		      System.out.format("%32s%32s%32s\n", xi.toString(), y.toString(), z.toString());
	    		    }
	    		}
	    		
	        reader.close();
	}

}

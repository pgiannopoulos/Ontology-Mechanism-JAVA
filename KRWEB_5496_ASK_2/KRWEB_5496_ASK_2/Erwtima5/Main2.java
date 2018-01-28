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

public class Main2 {
	
	public static void main(String args[]){
		//dhmiourgoume to model
		Model model= ModelFactory.createDefaultModel();
		
		//fortwnoume ta periexomena tou rdf sto model
		InputStream in = FileManager.get().open("hey.rdf");
		model.read(in, "");
		//model.write(System.out);
		
	
		    
	        System.out.println("Give me the minimun age: ");
	        Scanner reader = new Scanner(System.in); 
	        String input ;
	        input = reader.next( );
	        int minage = Integer.parseInt(input);
	        
	        
	        System.out.println("Give me the maximum age: ");
	        Scanner reader2 = new Scanner(System.in);  
	        String input2 ;
	        input2 = reader2.next( );
	        int maxage = Integer.parseInt(input2);
	        
	        
	      //dhmiourgoume to query pou fernei ola ta dia8esima departments
			String queryString="PREFIX plut: <http://mydomain.org/plut#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT DISTINCT ?city WHERE { ?xi rdf:type plut:Department . ?xi plut:dep_city ?city}";
			Query query= QueryFactory.create(queryString) ;
			
		    final String[] data = new String[200];
			
			try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
			    ResultSet results = qexec.execSelect() ;
		    	int i=1;
			    for ( ; results.hasNext() ; )
			    {
			      QuerySolution soln = results.nextSolution() ;
			      RDFNode x = soln.get("city") ; 
			      data[i] = x.toString();
			      System.out.println("option" + i + ":" + x.toString());
			      i++;
			    }
			}
			    
		        System.out.println("Give me the Department city option number: (1, 2, 3 etc.)");
		        Scanner reader3 = new Scanner(System.in);  
		        String input3 ;
		        input3 = reader3.next( );
		        int depcitynum = Integer.parseInt(input3);       
		        
		      //dhmiourgoume to query pou 8a epistrepsei to sunduasmo twn 3 apaithsewn tou xrhsth
		        String queryString2="PREFIX plut: <http://mydomain.org/plut#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ?name ?age ?phone WHERE { ?x plut:has_name ?name . ?x plut:has_age ?age . ?x plut:has_phone ?phone . ?x plut:member_of ?dep . ?dep plut:dep_city '"+data[depcitynum]+"'. FILTER (?age<'"+maxage+"' && ?age>'"+minage+"')}" ;
		        Query query2= QueryFactory.create(queryString2) ;
					
				try (QueryExecution qexec = QueryExecutionFactory.create(query2, model)) {
				    ResultSet results = qexec.execSelect() ;
				    for ( ; results.hasNext() ; )
				    {
				      QuerySolution soln = results.nextSolution() ;
				      RDFNode x = soln.get("name") ; 
				      RDFNode y = soln.get("age") ; 
				      RDFNode z = soln.get("phone") ; 
	    		      System.out.format("%32s%32s%32s\n", x.toString(), y.toString(), z.toString());
				    }
				}
		        		  
		        reader.close();
		        reader2.close();
		        reader3.close();
       
	}

}

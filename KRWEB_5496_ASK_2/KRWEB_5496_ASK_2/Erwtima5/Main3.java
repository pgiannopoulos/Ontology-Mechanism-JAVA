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

public class Main3 {
	
	public static void main(String args[]) throws IOException {
		//dhmiourgoume to model
		Model model= ModelFactory.createDefaultModel();
		
		//fortwnoume ta periexomena tou rdf sto model
		InputStream in = FileManager.get().open("hey.rdf");
		model.read(in, "");
		//model.write(System.out);
		
    	String plut_uri="http://mydomain.org/plut#";

		    
	        System.out.println("Give me the option number:  (1--> Add Professor, 2--> Add Student, 3--> Add Department, 4--> Add Lesson)");
	        Scanner reader = new Scanner(System.in);  // Reading from System.in
	        String input ;
	        input = reader.next( );
	        int foo = Integer.parseInt(input);
	        
	        //Pros8hkh Professor
	        if (foo == 1) {
	        	
	        	System.out.println("Give me the professor's name (name first then surname): ");
		        Scanner reader2 = new Scanner(System.in);  // Reading from System.in
		        String input2 ;
			    input2 = reader2.nextLine( );	
			    //kanoume tokenize to onoma tou professor gia na ftia3oume to URI tou
			    String[] splitted = input2.split(" ");			    
		            
			    System.out.println("Give me the professor's phone: ");
		        Scanner reader3 = new Scanner(System.in);  // Reading from System.in
		        String input3 ;
			    input3 = reader3.nextLine( );	
			    
			    System.out.println("Give me the professor's age: ");
		        Scanner reader4 = new Scanner(System.in);  // Reading from System.in
		        String input4 ;
			    input4 = reader4.nextLine( );	
			    
			    System.out.println("Give me the lesson the professor's teaching: ");
		        Scanner reader5 = new Scanner(System.in);  // Reading from System.in
		        String input5 ;
			    input5 = reader5.nextLine( );	
			    
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
			        System.out.println("Give me the option number for the Department of the professor: (1, 2, 3 etc.)");
			        Scanner reader6 = new Scanner(System.in);  // Reading from System.in
			        String input6 ;
			        input6 = reader6.next( );
			        int foo2 = Integer.parseInt(input6);
			        
			    //kanoume replace ta kena me '_' gia na tairiazei to URI tou department me auta tou rdf mas
			        data[foo2] = data[foo2].replace(' ', '_');
			        
		        // ftiaxnoume to URI ws p.x. plut:Chris_Makris (onoma_epi8eto)
	        	Resource prof= model.createResource(plut_uri+splitted[0]+"_"+splitted[1]);
	        	Property p_name =model.createProperty(plut_uri,"has_name");
	        	Property p_phone =model.createProperty(plut_uri,"has_phone");
	        	Property p_age =model.createProperty(plut_uri,"has_age");
	        	Property p_teaches =model.createProperty(plut_uri,"teaches");
	        	Property p_member =model.createProperty(plut_uri,"member_of");
	        	prof.addProperty(RDF.type, "http://www.mydomain.org/plut#Professor");
	        	prof.addProperty(p_name,input2);
	        	prof.addProperty(p_phone,input3);
	        	prof.addProperty(p_age,input4);
	        	prof.addProperty(p_teaches,input5);
	        	prof.addProperty(p_member,"http://mydomain.org/plut#"+data[foo2]);

	        }
	        
	        //pros8hkh Student
	        if (foo == 2) {
	        	System.out.println("Give me the Student's name (name first then surname): ");
		        Scanner reader2 = new Scanner(System.in);  // Reading from System.in
		        String input2 ;
			    input2 = reader2.nextLine( );	
			    //kanoume tokenize to onoma tou student gia na ftia3oume to URI tou
			    String[] splitted = input2.split(" ");			    
		            
			    System.out.println("Give me the Student's phone: ");
		        Scanner reader3 = new Scanner(System.in);  // Reading from System.in
		        String input3 ;
			    input3 = reader3.nextLine( );	
			    
			    System.out.println("Give me the Student's age: ");
		        Scanner reader4 = new Scanner(System.in);  // Reading from System.in
		        String input4 ;
			    input4 = reader4.nextLine( );	
			    
			    
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
			        System.out.println("Give me the option number for the Department of the Student: (1, 2, 3 etc.)");
			        Scanner reader6 = new Scanner(System.in);  // Reading from System.in
			        String input6 ;
			        input6 = reader6.next( );
			        int foo2 = Integer.parseInt(input6);
			        
			    //kanoume replace ta kena me '_' gia na tairiazei to URI tou department me auta tou rdf mas
			        data[foo2] = data[foo2].replace(' ', '_');
			        
		        // ftiaxnoume to URI ws p.x. plut:Chris_Makris (onoma_epi8eto)
	        	Resource stud= model.createResource(plut_uri+splitted[0]+"_"+splitted[1]);
	        	Property s_name =model.createProperty(plut_uri,"has_name");
	        	Property s_phone =model.createProperty(plut_uri,"has_phone");
	        	Property s_age =model.createProperty(plut_uri,"has_age");
	        	Property s_member =model.createProperty(plut_uri,"member_of");
	        	stud.addProperty(RDF.type, "http://www.mydomain.org/plut#Student");
	        	stud.addProperty(s_name,input2);
	        	stud.addProperty(s_phone,input3);
	        	stud.addProperty(s_age,input4);
	        	stud.addProperty(s_member,"http://mydomain.org/plut#"+data[foo2]);
  
	    	}
	        
	        //Pros8hkh Department
	        if (foo == 3) {
	        	System.out.println("Give me the Department's name : ");
		        Scanner reader2 = new Scanner(System.in);  // Reading from System.in
		        String input2 ;
			    input2 = reader2.nextLine( );	
		            
			    System.out.println("Give me the Department's city: ");
		        Scanner reader3 = new Scanner(System.in);  // Reading from System.in
		        String input3 ;
			    input3 = reader3.nextLine( );	
			   
			        
			    //kanoume replace ta kena me '_' gia na tairiazei to URI tou department me auta tou rdf mas
			        input2 = input2.replace(' ', '_');
			        
		        // ftiaxnoume to URI 
	        	Resource dep= model.createResource(plut_uri+input2);
	        	Property d_name =model.createProperty(plut_uri,"dep_name");
	        	Property d_city =model.createProperty(plut_uri,"dep_city");
	        	dep.addProperty(RDF.type, "http://www.mydomain.org/plut#Department");
	        	dep.addProperty(d_name,input2);
	        	dep.addProperty(d_city,input3);
	    	}
	        
	        //Pros8hkh Lesson
	        if (foo == 4) {
	        	System.out.println("Give me the Lesson's name : ");
		        Scanner reader2 = new Scanner(System.in);  // Reading from System.in
		        String input2 ;
			    input2 = reader2.nextLine( );	
		            
			    System.out.println("Give me the Lesson's teacher/professor: ");
		        Scanner reader3 = new Scanner(System.in);  // Reading from System.in
		        String input3 ;
			    input3 = reader3.nextLine( );	
			    
			    //kratame sthn temp to onoma tou ma8hmatos gt argotera 8a to kanoume tokenize
			   String temp = input2;
			        
			    //kanoume replace ta kena me '_' gia na tairiazei to URI tou department me auta tou rdf mas
			        input2 = input2.replace(' ', '_');
			        
			        //kanoume replace ta kena me '_' gia na tairiazei to URI tou professor me auta tou rdf mas
			        input3 = input3.replace(' ', '_');
			        
		        // ftiaxnoume to URI 
	        	Resource les= model.createResource(plut_uri+input2);
	        	Property l_name =model.createProperty(plut_uri,"les_name");
	        	Property l_taught =model.createProperty(plut_uri,"taught_by");
	        	les.addProperty(RDF.type, "http://www.mydomain.org/plut#Lesson");
	        	les.addProperty(l_name,temp);
	        	les.addProperty(l_taught,"http://mydomain.org/plut#"+input3);
	        }
	        
	        //ektupwnoume ena neo arxeio rdf me thn pros8hkh tou neou porou mas
	        String fileName = "C:/Users/panos/workspace/jena-app/src/hey8.rdf";
	        FileWriter out = new FileWriter( fileName );
	        try {
	            model.write( out, "RDF/XML-ABBREV" );
	        }
	        finally {
	           try {
	               out.close();
	           }
	           catch (IOException closeException) {
	           }
	        }

	        
	   }

}

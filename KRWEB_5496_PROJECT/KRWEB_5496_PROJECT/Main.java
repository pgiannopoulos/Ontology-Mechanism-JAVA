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
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.query.*;
import org.apache.jena.atlas.io.IndentedWriter;
import java.util.Scanner; 
import java.util.Iterator;

public class Main {
	
	public static void main(String args[]){
		//dhmiourgoume to model
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_DL_MEM_RULE_INF);		
		model.read("C:/Users/panos/workspace/jena-app/src/SnowboardingProj.owl", null);
		String NS = "http://www.owl-ontologies.com/Ontology1484492708.owl#";
		
		//dhmiourgia inference montelou
		InfModel inf = ModelFactory.createRDFSModel(model); 
		// dhlwsh ths vasikhs arxikhs mas klashs gia emfanish olwn twn upoklasewn
		OntClass snow = model.getOntClass( NS + "Snowboarding" );
		
		//ektupwsh tou menu ths efarmoghs mas
		System.out.println( "Menu of actions. Make your choice by typing 1 or 2 or .. etc." );
		System.out.println( "1-->Retrieve the instances of a class of your choice" );
		System.out.println( "2-->Add new instances to a class of your choice" );
		System.out.println( "3-->Run a SPARQL query of your choice" );
		System.out.println( "4-->Show all the relevant information of an instance of your choice" );

		//pairnoume thn epilogh tou xrhsth gia na ginei h katallhlh energeia
		Scanner reader4 = new Scanner(System.in); 
        String input4 ;
        input4 = reader4.next( );
        int choice = Integer.parseInt(input4);
        
		if (choice==1){
			System.out.println( "gia poia klash na emfanistoun ta instances? (plhktrologhste opws thn vlepete)" );
	
			//retrieve twn klasewn
			for (Iterator<OntClass> i = snow.listSubClasses(); i.hasNext(); ) {
			  OntClass c = i.next();
			  if (c.getURI() != null)
			  System.out.println( c.getURI().substring(53) );
			}
			//diavasma epiloghs xrhsth
			Scanner reader = new Scanner(System.in); 
	        String input ;
	        input = reader.next( );
	        
	        String klash = input;
			OntClass newclass = model.getOntClass( NS + klash );
			
			//retrieve twn instances ths klashs pou epele3e o xrhsths
		      ExtendedIterator instances = newclass.listInstances();
		      while (instances.hasNext()){
		        Individual thisInstance = (Individual) instances.next();
		        System.out.println("  Found instance: " + thisInstance.toString());
		      }
		}
	      
	      
		if (choice==2){

	      	System.out.println( "gia poia klash na pros8esw to instance? (plhktrologhste opws thn vlepete)" );

			//retrieve twn klasewn
			for (Iterator<OntClass> i = snow.listSubClasses(); i.hasNext(); ) {
			  OntClass c = i.next();
			  if (c.getURI() != null)
			  System.out.println( c.getURI().substring(53) );
			}
			
			//diavasma epiloghs xrhsth
			Scanner reader2 = new Scanner(System.in); 
	        String input2 ;
	        input2 = reader2.next( );  
	        
	        String klash2 = input2;
			OntClass newclass2 = model.getOntClass( NS + klash2 );
			
			System.out.println( "pws onomazetai to instance?" );
			//diavasma epiloghs xrhsth
			Scanner reader3 = new Scanner(System.in); 
	        String input3 ;
	        input3 = reader3.next( );
			
			//dhmiourgia enos instance
			Individual ind1 = newclass2.createIndividual( NS + input3 );
	      
		}
	      
	      
		
		if (choice==3){
			
			//dhmiourgoume to antistoixo query
			String queryString2="PREFIX plut: <http://www.owl-ontologies.com/Ontology1484492708.owl#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ?name_boots ?name_socks  WHERE { ?name_boots plut:cover ?name_socks }";
			Query query2= QueryFactory.create(queryString2) ;
			
			try (QueryExecution qexec = QueryExecutionFactory.create(query2, model)) {
			    ResultSet results2 = qexec.execSelect() ;
			    for ( ; results2.hasNext() ; )
			    {
			      QuerySolution soln = results2.nextSolution() ;
			      RDFNode xi = soln.get("name_boots") ; 
			      RDFNode y = soln.get("name_socks") ; 
			      // ektupwnoume ta apotelesmata sth morfh pou zhteitai
			      System.out.format("%90s%90s\n", xi.toString(), y.toString());
			    }
			}
		}
		
		if (choice==4){
			System.out.println( "gia poia klash na emfanisw ta instances? (plhktrologhste opws thn vlepete)" );
			
			//retrieve twn klasewn
			for (Iterator<OntClass> i = snow.listSubClasses(); i.hasNext(); ) {
			  OntClass c = i.next();
			  if (c.getURI() != null)
			  System.out.println( c.getURI().substring(53) );
			}
			//diavasma epiloghs xrhsth
			Scanner reader5 = new Scanner(System.in); 
	        String input5 ;
	        input5 = reader5.next( );
	        
	        String klash = input5;
			OntClass newclass = model.getOntClass( NS + klash );
			
			
			System.out.println( "gia poio instance na emfanisw tis plhrofories? (plhktrologhste opws to vlepete)" );

			//retrieve twn instances ths klashs pou epele3e o xrhsths
		    ExtendedIterator instances = newclass.listInstances();
		    int i=1;
		    while (instances.hasNext()){
		    	Individual thisInstance = (Individual) instances.next();
		        System.out.println(thisInstance.toString().substring(53));
		        i++;
		    }
		    
		    //diavasma epiloghs xrhsth
			Scanner reader6 = new Scanner(System.in); 
	        String input6 ;
	        input6 = reader6.next( );
	        
			Individual ind = model.getIndividual( NS + input6);
	        System.out.println(""+ind);
	        StmtIterator it = ind.listProperties();

	        while ( it.hasNext()) {
	        	System.out.println(it.next());
	        }	            
		}
	      
		
		
		
	}

}

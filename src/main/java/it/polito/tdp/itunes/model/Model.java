package it.polito.tdp.itunes.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	ItunesDAO dao;

	private Graph<Album,DefaultEdge> grafo;
	public Model() {
		super();
		this.dao = new ItunesDAO();
	}
	
	public void creaGrafo(double durata) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		
		Graphs.addAllVertices(grafo, dao.getAllAlbumDurata(durata));
		
		//Creiamo una mappa per raccogliere i vertici:
		Map<Integer,Album> idMap = new HashMap<>();
		for(Album a : this.grafo.vertexSet()) {
			idMap.put(a.getAlbumId(), a);
		}
		
		//Iniziamo a sistemare gli archi:
		List<Pair<Integer,Integer>> archi = dao.getCollegamentoCorretto();
		
		for(Pair<Integer,Integer> arco : archi) {
			if(idMap.containsKey(arco.getFirst())&& idMap.containsKey(arco.getSecond()) && arco.getFirst()!=arco.getSecond()) {
				grafo.addEdge(idMap.get(arco.getFirst()),idMap.get(arco.getSecond()));
			}
		}
		System.out.println("Il grafo creato presenta: "+grafo.vertexSet().size()+ " vertici");
		System.out.println("Il grafo creato presenta: "+grafo.edgeSet().size()+ " cammini");

	}
	public List<Album> getAlbum(){
		return (List<Album>) grafo.vertexSet();
	}
	
	public Set<Album> getComponente(Album a1) {
		ConnectivityInspector<Album, DefaultEdge> ci =
				new ConnectivityInspector<>(this.grafo) ;
		return ci.connectedSetOf(a1) ;
	}
}

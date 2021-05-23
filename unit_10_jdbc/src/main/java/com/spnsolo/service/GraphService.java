package com.spnsolo.service;

import com.spnsolo.data.Location;
import com.spnsolo.data.Route;
import com.spnsolo.data.Solution;
import com.spnsolo.exception.NotFoundException;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;

public class GraphService {
    private SimpleWeightedGraph<String, DefaultWeightedEdge> graph;

    public void initializeGraph(List<Location> locations, List<Route> routes) throws NotFoundException {
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        for (Location location : locations) {
            graph.addVertex(location.getName());
        }

        DefaultWeightedEdge edge;
        for(Route route: routes){
            Location from = findLocationById(locations,route.getFrom_id());
            Location to = findLocationById(locations,route.getTo_id());

            edge = graph.addEdge(from.getName(), to.getName());
            graph.setEdgeWeight(edge,route.getCost());
        }

    }
    public Solution shortestWay(Location from, Location to){
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath
                = new DijkstraShortestPath<>(graph);

        double cost = dijkstraShortestPath.getPath(from.getName(),to.getName()).getWeight();
        Solution solution = new Solution();
        solution.setCost((int)cost);
        return solution;
    }

    private Location findLocationById(List<Location> locations, Integer id) throws NotFoundException {
        for(Location location : locations){
            if(location.getId().equals(id)) return location;
        }
        throw new NotFoundException("location");
    }
}

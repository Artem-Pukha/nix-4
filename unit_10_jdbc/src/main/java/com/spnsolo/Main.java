package com.spnsolo;

import com.spnsolo.data.Location;
import com.spnsolo.data.Problem;
import com.spnsolo.data.Route;
import com.spnsolo.data.Solution;
import com.spnsolo.exception.InvalidIdException;
import com.spnsolo.exception.NotFoundException;
import com.spnsolo.service.GraphService;
import com.spnsolo.service.MainService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final MainService service = new MainService();
    private static final GraphService serviceGraph = new GraphService();

    public static void main(String[] args) {
        System.out.println("///////////Unit 10 - JDBC///////////");
        System.out.println();
        try {
            List<Problem> problems = service.getUnsolvedProblems();

            List<Solution> solutionsOfProblems = solveProblems(problems);

            solutionsOfProblems.forEach(solution -> System.out.println(solution.getCost()));

            for(Solution solution : solutionsOfProblems){
                service.createSolution(solution);
            }

        } catch (SQLException | InvalidIdException | NotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("///////////////////////////////////");
    }

    private static List<Solution> solveProblems(List<Problem> problems) throws SQLException, InvalidIdException, NotFoundException {
        List<Location> locationsFrom = new ArrayList<>();
        List<Location> locationsTo = new ArrayList<>();

        for (Problem problem : problems) {
            locationsFrom.add(service.getLocationById(problem.getFrom_id()));
            locationsTo.add(service.getLocationById(problem.getTo_id()));
        }

        List<Location> locations = service.readAllLocations();
        List<Route> routes = service.readAllRouts();
        serviceGraph.initializeGraph(locations,routes);

        List<Solution> solutions = new ArrayList<>();

        for (int i = 0; i < problems.size(); i++) {
            solutions.add(serviceGraph.shortestWay(locationsFrom.get(i),locationsTo.get(i)));
        }

        Solution solution;
        for (int i = 0; i < problems.size(); i++) {
            solution = solutions.get(i);
            solution.setProblem_id(problems.get(i).getId());
        }

        return solutions;
    }

}

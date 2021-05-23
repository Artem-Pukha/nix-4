package com.spnsolo.service;

import com.spnsolo.dao.MainDAO;
import com.spnsolo.data.Location;
import com.spnsolo.data.Problem;
import com.spnsolo.data.Route;
import com.spnsolo.data.Solution;
import com.spnsolo.exception.InvalidIdException;

import java.sql.SQLException;
import java.util.List;

public class MainService {
    private final MainDAO DAO = new MainDAO();
    public List<Problem> getUnsolvedProblems() throws SQLException {
        return DAO.getUnsolvedProblems();
    }

    public Location getLocationById(Integer id) throws SQLException, InvalidIdException {
        if(id <= 0)throw  new InvalidIdException();
        return DAO.getLocationById(id);
    }

    public List<Route> readAllRouts() throws SQLException{
        return DAO.readAllRouts();
    }

    public List<Location> readAllLocations() throws SQLException {
        return DAO.readAllLocations();
    }

    public Integer createSolution(Solution solution) throws SQLException {
        return DAO.createSolution(solution);
    }
}

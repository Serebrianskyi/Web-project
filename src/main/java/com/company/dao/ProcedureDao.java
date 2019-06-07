package com.company.dao;

import com.company.entity.Procedure;

import java.util.List;
import java.util.Optional;

public interface ProcedureDao extends GenericDao<Procedure>{
    List<Procedure> findAll();
}

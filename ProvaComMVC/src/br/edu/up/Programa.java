package br.edu.up;

import br.edu.up.DAO.ResumoDAO;

public class Programa {
    public static void main(String[] args){
        ResumoDAO resumoDAO = new ResumoDAO();

        resumoDAO.gravarResumo();
    }
}

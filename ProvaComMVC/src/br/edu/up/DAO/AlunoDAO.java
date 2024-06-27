package br.edu.up.DAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.up.models.Aluno;

public class AlunoDAO {
    private String path = new File("").getAbsolutePath() + "\\src\\br\\edu\\up\\data\\";
    private String localizacao = path + "alunos.csv";
    private List<Aluno> alunos = new ArrayList<>();

    public List<Aluno> getAlunos(){

        try{
            File arquivoLeitura = new File(localizacao);
            Scanner leitor = new Scanner(arquivoLeitura);
            leitor.nextLine();            

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");

                int matricula = Integer.parseInt(dados[0]);
                String nome = dados[1];
                Double nota = Double.parseDouble(dados[2].replace(",", "."));                

                Aluno aluno = new Aluno(matricula, nome, nota);

                alunos.add(aluno);
            }

            leitor.close();
        }catch(Exception e){
            System.out.println("Erro ao encontrar o caminho do arquivo aluno.csv");
        }

        return alunos;
    }
}
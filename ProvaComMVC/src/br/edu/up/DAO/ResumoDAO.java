package br.edu.up.DAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import br.edu.up.models.Aluno;

public class ResumoDAO {
    private String path = new File("").getAbsolutePath() + "\\src\\br\\edu\\up\\data\\";
    private String resumoCsv = path + "resumo.csv";
    private String header = "quantAlunos;quantAprovados;quantReprovados;menorNota;maiorNota;mediaGeral";
    private AlunoDAO alunoDAO = new AlunoDAO();

    public void gravarResumo(){
        List<Aluno> alunos = alunoDAO.getAlunos();
        
        try {
            FileWriter arquivoGravar = new FileWriter(resumoCsv);
            PrintWriter gravador = new PrintWriter(arquivoGravar);
            gravador.println(header);

            int quantAlunos = alunos.size();
            int quantAprovados = 0;
            int quantReprovados = 0;
            Double menorNota = 0.0;
            Double maiorNota = 0.0;
            Double mediaGeral = 0.0;

            for (Aluno aluno : alunos) {
                if(aluno.getNota() >= 6){
                    quantAprovados ++;
                }else{
                    quantReprovados ++;
                }

                if(aluno.getNota() < menorNota){
                    menorNota = aluno.getNota();
                }

                if(aluno.getNota() > maiorNota){
                    maiorNota = aluno.getNota();
                }

                mediaGeral += aluno.getNota();
            }

            mediaGeral /= quantAlunos;
            header = "quantAlunos;quantAprovados;quantReprovados;menorNota;maiorNota;mediaGeral";

            gravador.println(quantAlunos + ";" + quantAprovados + ";" + quantReprovados + ";" + menorNota + ";" + maiorNota + ";" + mediaGeral);
            gravador.close();
        } catch (IOException e) {
            System.out.println("Não foi possível gravar o arquivo!");
        }
    }
}
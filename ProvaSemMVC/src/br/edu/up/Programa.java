package br.edu.up;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.up.models.Aluno;

public class Programa {
    public static void main(String[] args){
        
        List<Aluno> alunos = getAlunos();

        gravarResumo(alunos);
    }

    public static List<Aluno> getAlunos(){
        List<Aluno> alunos = new ArrayList<>();

        try{
            String path = new File("").getAbsolutePath() + "\\src\\br\\edu\\up\\data\\";

            String localizacao = path + "alunos.csv";

            File arquivoLeitura = new File(localizacao);
            Scanner leitor = new Scanner(arquivoLeitura);

            String header = leitor.nextLine();            

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");

                int matricula = Integer.parseInt(dados[0]);
                String nome = dados[1];
                Double nota = Double.parseDouble(dados[2].replace(",", "."));                

                Aluno aluno = new Aluno(matricula, nome, nota);

                alunos.add(aluno);
            }

        }catch(Exception e){
            System.out.println("Erro ao encontrar o caminho do arquivo aluno.csv");
        }

        return alunos;
    }


    public static void gravarResumo(List<Aluno> alunos){
        String path = new File("").getAbsolutePath() + "\\src\\br\\edu\\up\\data\\";

        String resumoCsv = path + "resumo.csv";

        String header = "quantAlunos;quantAprovados;quantReprovados;menorNota;maiorNota;mediaGeral";

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

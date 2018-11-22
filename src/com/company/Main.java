package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main{

   public static void lerArquivo(ListaBlocosLivres blocosLivres, ListaBlocosOcupados blocosOcupados, ListaPendentes blocosPendentes) {

        Scanner ler = new Scanner(System.in);

        System.out.printf("Informe o nome de arquivo texto:\n");
        String nome = ler.nextLine();

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine(); // lê se modo é fixo ou aleatorio - neste caso, eh sempre fixo

            linha = lerArq.readLine(); // lê inicio da memoria mi
            int mi = Integer.parseInt(linha);

            linha = lerArq.readLine(); // lê fim da memoria mf
            int mf = Integer.parseInt(linha);

            blocosLivres.insereBloco(mi,mf);//chama metodo inserir bloco
            linha = lerArq.readLine(); // lê da segunda até a última linha do arquivo

            while (linha != null) {
                System.out.println("------------------");
                blocosLivres.imprimeBlocos();//imprime blocos livres
                System.out.println("------------------");
                blocosOcupados.imprimeBlocos();//imprime blocos ocupados
                System.out.println("------------------");

                switch (linha.charAt(0)) {
                    case 'S'://se S (solicitacao), executa rotina abaixo
                        int tamanho = Integer.parseInt(linha.substring(2)); //le o que está apos 'S'
                        System.out.println("Solicitou tamanho " + tamanho);
                        //se existir blocos livres, se torna ocupado
                        if (blocosLivres.existeBlocoLivre(tamanho)) {
                            BlocoOcupado novoB = blocosLivres.retornaBloco(tamanho);
                            blocosOcupados.insereBloco(novoB);//insere novo bloco ocupado na lista de ocupados
                        } else if (blocosLivres.tamanhoTotal() > tamanho) {
                            System.out.println("Houve fragmentacao!");
                            blocosPendentes.insereBloco(tamanho);//insere bloco que nao pôde ser atendido na lista de pendentes
                        }
                        else {
                        
                            System.out.println("Nao há memória disponivel");
                            blocosPendentes.insereBloco(tamanho);//insere bloco que nao pôde ser atendido na lista de pendentes
                        }

                        break;
                    //se L (liberar), executa rotina abaixo
                    case 'L':
                        int idBloco = Integer.parseInt(linha.substring(2)); //le o que está apos 'L'
                        
                        BlocoLivre bloco = blocosOcupados.liberaBloco(idBloco);//chama método para libera bloco
                        
                        if (bloco == null)
                            System.err.println("Não encontrou o bloco numero "+idBloco);
                        else {
                            blocosLivres.insereBloco(bloco);
                            System.out.println("Bloco " + idBloco + " liberado.");
                        }
                        blocosLivres.imprimeBlocos();
                        blocosPendentes.verificaBlocosLivres(blocosLivres, blocosOcupados);
                        
                        break;
                }
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        ListaBlocosLivres blocosLivres = new ListaBlocosLivres();
        ListaBlocosOcupados blocosOcupados = new ListaBlocosOcupados();
        ListaPendentes blocosPendentes = new ListaPendentes();
        lerArquivo(blocosLivres, blocosOcupados, blocosPendentes);
    }
}

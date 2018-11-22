package com.company;

public class BlocoOcupado {

        public int endInicial, endFinal;
        public int id;
        private static int contId = 1;//implementa contador para id de blocos ocupados

        public BlocoOcupado(int i, int f) {
            endInicial = i;
            endFinal = f;
            id = contId++;
        }
    }

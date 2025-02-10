

import models.Funcionario;
import models.Pessoa;
import services.GerenciarFuncionarios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        GerenciarFuncionarios gerenciador = new GerenciarFuncionarios();

        gerenciador.adicionarFuncionario(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        gerenciador.adicionarFuncionario(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        gerenciador.adicionarFuncionario(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        gerenciador.adicionarFuncionario(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        gerenciador.adicionarFuncionario(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        gerenciador.adicionarFuncionario(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        gerenciador.adicionarFuncionario(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        gerenciador.adicionarFuncionario(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        gerenciador.adicionarFuncionario(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        gerenciador.adicionarFuncionario(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        gerenciador.removerFuncionario("João");

        System.out.println("Lista de funcionários:");
        gerenciador.imprimirFuncionarios();

        gerenciador.aumentarSalario(new BigDecimal("0.1"));
        System.out.println("\nFuncionários com aumento de salário:");
        gerenciador.imprimirFuncionarios();

        System.out.println("\nFuncionários agrupados por função:");
        gerenciador.imprimirFuncionariosPorFuncao();

        gerenciador.imprimirAniversariantesOutubroDezembro();
        gerenciador.imprimirFuncionarioMaisVelho();
        gerenciador.imprimirFuncionariosOrdemAlfabetica();
        gerenciador.imprimirTotalSalarios();
        gerenciador.imprimirSalariosMinimosPorFuncionario();
    }
}



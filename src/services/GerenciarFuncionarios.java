package services;

import models.Funcionario;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class GerenciarFuncionarios {
    private List<Funcionario> funcionarios;

    public GerenciarFuncionarios() {
        this.funcionarios = new ArrayList<>();
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void removerFuncionario(String nome) {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }

    public void imprimirFuncionarios() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        funcionarios.forEach(f -> System.out.printf("Nome: %s, Data Nascimento: %s, Salário: R$ %,.2f, Função: %s%n",
                f.getNome(), f.getDataNascimento().format(formatter), f.getSalario(), f.getFuncao()));
    }

    public void aumentarSalario(BigDecimal percentual) {
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(BigDecimal.ONE.add(percentual))));
    }

    public void imprimirFuncionariosPorFuncao() {
        Map<String, List<Funcionario>> agrupados = new HashMap<>();
        for (Funcionario f : funcionarios) {
            agrupados.computeIfAbsent(f.getFuncao(), k -> new ArrayList<>()).add(f);
        }
        agrupados.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(f -> System.out.println(" - " + f.getNome()));
        });
    }

    public void imprimirAniversariantesOutubroDezembro() {
        System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(" - " + f.getNome()));
    }

    public void imprimirFuncionarioMaisVelho() {
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.printf("\nFuncionário mais velho:\nNome: %s, Idade: %d anos%n", maisVelho.getNome(), idade);
    }

    public void imprimirFuncionariosOrdemAlfabetica() {
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.forEach(f -> System.out.println(" - " + f.getNome()));
    }

    public void imprimirTotalSalarios() {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("\nTotal dos salários: R$%,.2f%n", totalSalarios);
    }

    public void imprimirSalariosMinimosPorFuncionario() {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        funcionarios.forEach(f -> {
            BigDecimal quantidade = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("%s: %.2f salários mínimos%n", f.getNome(), quantidade);
        });
    }
}

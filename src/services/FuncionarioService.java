package services;

import models.Funcionario;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {
    private List<Funcionario> funcionarios;

    public FuncionarioService() {
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
        funcionarios.forEach(f -> System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %,.2f, Função: %s%n",
                f.getNome(), f.getDataNascimento().format(formatter), f.getSalario(), f.getFuncao()));
    }

    public void aumentarSalario(BigDecimal percentual) {
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(BigDecimal.ONE.add(percentual))));
    }

    public void imprimirFuncionariosPorFuncao() {
        Map<String, List<Funcionario>> agrupados = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        agrupados.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(f -> System.out.println(" - " + f.getNome()));
        });
    }

    public void imprimirAniversariantes(int... meses) {
        List<Integer> mesesList = Arrays.stream(meses).boxed().toList();
        funcionarios.stream()
                .filter(f -> mesesList.contains(f.getDataNascimento().getMonthValue()))
                .forEach(f -> System.out.println(f.getNome()));
    }

    public void imprimirFuncionarioMaisVelho() {
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.printf("\nFuncionário mais velho: %s, Idade: %d anos%n", maisVelho.getNome(), idade);
    }

    public void imprimirFuncionariosOrdenados() {
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));
    }

    public void imprimirTotalSalarios() {
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("\nTotal dos salários: %,.2f%n", total);
    }

    public void imprimirSalariosMinimos(BigDecimal salarioMinimo) {
        funcionarios.forEach(f -> {
            BigDecimal qtdSalarios = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("%s: %.2f salários mínimos%n", f.getNome(), qtdSalarios);
        });
    }
}

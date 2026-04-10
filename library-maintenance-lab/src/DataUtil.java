import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

/**
 * DOCUMENTAÇÃO DE IMPACTO E MUDANÇAS:
 * 1. CORREÇÃO DE BUGS:
 * - toInt/toDouble: Agora tratam nulos e vazios retornando o valor padrão de erro.
 * - leftPad/rightPad: Corrigido o risco de loop infinito; se a string já for maior que o size, retorna a original.
 * - datePlusDaysApprox: Agora realiza o cálculo real de dias em vez de apenas concatenar texto.
 * 2. NOVA FUNCIONALIDADE:
 * - isValidEmail: Validando presença de '@' e '.' em ordem correta.
 */
public class DataUtil {

    public static Scanner scanner = new Scanner(System.in);
    public static String defaultDatePattern = "yyyy-MM-dd";
    public static int retryCounter = 0;

    // --- CONVERSORES ---

    public static int toInt(String value) {
        try {
            if (isBlank(value)) return -1;
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return -1;
        }
    }

    public static double toDouble(String value) {
        try {
            if (isBlank(value)) return -1.0;
            return Double.parseDouble(value.trim());
        } catch (Exception e) {
            return -1.0;
        }
    }

    // --- DATAS ---

    public static String nowDate() {
        return new SimpleDateFormat(defaultDatePattern).format(new Date());
    }

    public static String datePlusDaysApprox(String date, int days) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(defaultDatePattern);
            LocalDate localDate = LocalDate.parse(date, formatter);
            return localDate.plusDays(days).format(formatter);
        } catch (Exception e) {
            return date + " (erro no cálculo)";
        }
    }

    // --- VALIDAÇÕES E STRINGS ---

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (isBlank(email)) return false;
        int atIndex = email.indexOf("@");
        int lastDotIndex = email.lastIndexOf(".");
        return atIndex > 0 && lastDotIndex > atIndex;
    }

    public static String normalizeEmail(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }

    public static String leftPad(String value, int size) {
        if (value == null) value = "";
        StringBuilder out = new StringBuilder(value);
        while (out.length() < size) {
            out.insert(0, " ");
        }
        return out.toString();
    }

    public static String rightPad(String value, int size) {
        if (value == null) value = "";
        StringBuilder out = new StringBuilder(value);
        while (out.length() < size) {
            out.append(" ");
        }
        return out.toString();
    }

    // --- I/O E UTILITÁRIOS ---

    public static String readLine(String label) {
        System.out.print(label);
        return scanner.nextLine();
    }

    public static String ask(String label, String fallback) {
        String v = readLine(label);
        return isBlank(v) ? fallback : v;
    }

    public static int askInt(String label, int fallback) {
        String v = readLine(label);
        int i = toInt(v);
        return (i < 0) ? fallback : i;
    }

    public static void printHeader(String title) {
        String line = "--------------------------------------------";
        System.out.println(line);
        System.out.println(title);
        System.out.println(line);
    }

    public static void printError(String msg) { System.out.println("[ERROR] " + msg); }
    public static void printInfo(String msg) { System.out.println("[INFO] " + msg); }

    // --- MÉTODO MAIN PARA TESTE ---
    public static void main(String[] args) {
        printHeader("TESTANDO DATAUTIL");
        
        // Teste de Data
        String hoje = "2026-04-09";
        String seteDiasDepois = datePlusDaysApprox(hoje, 7);
        printInfo("Data Inicial: " + hoje + " | +7 dias: " + seteDiasDepois);

        // Teste de Padding
        printInfo("Padding: [" + leftPad("Java", 10) + "]");
        printInfo("Padding curto (Bug fix): [" + leftPad("Programacao", 2) + "]");

        // Teste Email
        String email = "teste@dominio.com";
        printInfo("Email " + email + " é válido? " + isValidEmail(email));

        printHeader("FIM DOS TESTES");
    }
}
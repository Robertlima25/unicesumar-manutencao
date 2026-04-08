import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookManager {

    //EXEMPLO COMMIT
    //Problemas de Manutenibilidade
    //1. Método muito grande e com múltiplas responsabilidades

//Onde: registerBook
//Problema: validação + regra de negócio + persistência + log
//Classificação: God Method / Violação do Single Responsibility Principle (SRP)

// 2. Tratamento de erro genérico

//Onde: catch (Exception e)
//Problema: perde informação do erro original
//Classificação: Tratamento inadequado de exceções

// 3. Uso de valores mágicos (magic numbers/strings)
// //Exemplos:

//"GENERAL", "X0", "NO-ISBN"
//1900, 1, 9
//Classificação: Magic Numbers / Magic Strings

// 4. Código legado com workaround perigoso

//Onde:
//title = " ";
//Problema: permite dado inválido
//Classificação: Technical Debt / Código legado

//5. Duplicação de lógica

//Onde: listBooksSimple
//Comentário já indica duplicação
//Classificação: Code Duplication

//6. Bug de edge case

//Onde:
//if (temp.size() == 0) {
    //System.out.println(temp.get(0));
}//Problema: acesso inválido
//Classificação: Bug / Falta de validação

//7. Uso excessivo de Map ao invés de objetos
//Problema:

//difícil manutenção
//sem tipagem forte
//Classificação: Primitive Obsession / Falta de modelagem OO

//8. Método com parâmetros excessivos
//Onde:

//updateAvailableWithLegacyRule(...)
//Problema: difícil leitura e manutenção
//Classificação: Long Parameter List

//9. Lógica complexa com if/else encadeado

//Onde: opCode
//Classificação: Complex Conditional / Falta de polimorfismo

//10. Falta de separação de camadas
//Problema:

//mistura console + regra de negócio
//Classificação: Violação de arquitetura (Separation of Concerns)

//Código Refatorado
//public class BookManager {

    //private static final String DEFAULT_CATEGORY = "GENERAL";
    //private static final String DEFAULT_SHELF = "X0";
    //private static final String DEFAULT_ISBN = "NO-ISBN";
    //private static final int DEFAULT_YEAR = 1900;

    //public int registerBook(String title, String author, int year, String category,
                            //int totalCopies, int availableCopies,
                            //String shelfCode, String isbn) {

        //validateAuthor(author);

        //title = defaultIfBlank(title, " ");
        //category = defaultIfBlank(category, DEFAULT_CATEGORY);
        //shelfCode = defaultIfBlank(shelfCode, DEFAULT_SHELF);
        //isbn = defaultIfBlank(isbn, DEFAULT_ISBN);

        //year = normalizeYear(year);
        //totalCopies = normalizeTotalCopies(totalCopies);
        //availableCopies = normalizeAvailableCopies(availableCopies, totalCopies);

        //try {
            //int result = saveBook(title, author, year, category,
                                 //totalCopies, availableCopies,
                                 //shelfCode, isbn);

            //logSuccess(result);
            //return result;

        //} catch (Exception e) {
            //logError(e);
            //throw new RuntimeException("Cannot register book", e);
        //}
    //}

    //private void validateAuthor(String author) {
        //if (DataUtil.isBlank(author)) {
            //throw new RuntimeException("author invalid");
        //}
    //}

    //private String defaultIfBlank(String value, String defaultValue) {
        //return DataUtil.isBlank(value) ? defaultValue : value;
    //}

    //private int normalizeYear(int year) {
        //return year < 0 ? DEFAULT_YEAR : year;
    //}

    //private int normalizeTotalCopies(int totalCopies) {
        //return totalCopies <= 0 ? 1 : totalCopies;
    //}

    //private int normalizeAvailableCopies(int available, int total) {
        //return available < 0 ? total : available;
    //}

    //private int saveBook(String title, String author, int year, String category,
                         //int totalCopies, int availableCopies,
                         //String shelfCode, String isbn) {

        //return LegacyDatabase.addBookData(
                //title, author, year, category,
                //totalCopies, availableCopies,
                //shelfCode, isbn
        //);
    //}

    //private void logSuccess(int result) {
        //LegacyDatabase.addLog("book-manager-register-" + result);
    //}

    //private void logError(Exception e) {
        //LegacyDatabase.addLog("book-manager-error-" + e.getMessage());
    //}

    // -------------------------

    //public void listBooksSimple() {
        //List<Map<String, Object>> books = new ArrayList<>(LegacyDatabase.getBooks().values());

        //if (books.isEmpty()) {
            //System.out.println("No books found.");
            //return;
        //}

        //printHeader();
        //for (Map<String, Object> b : books) {
            //printBook(b);
        //}
    //}

    //private void printHeader() {
        //System.out.println("ID | TITLE | AUTHOR | Y | CAT | AV");
    //}

    //private void printBook(Map<String, Object> b) {
        //System.out.println(
                //b.get("id") + " | " +
                //b.get("title") + " | " +
                //b.get("author") + " | " +
                //b.get("year") + " | " +
                //b.get("category") + " | " +
                //b.get("availableCopies")
        //);
    //}

    // -------------------------

    //public void updateAvailableWithLegacyRule(int id, int newAvailable, int opCode,
                                              //String process, String manager,
                                              //int flag, String reason) {

        //Map<String, Object> data = getBookOrThrow(id);

        //int total = (Integer) data.get("totalCopies");
        //newAvailable = clamp(newAvailable, 0, total);

        //int updatedValue = calculateNewAvailable(data, newAvailable, opCode, total);

        //data.put("availableCopies", updatedValue);

        //logUpdate(flag, process, manager, id, reason);
    //}

    //private Map<String, Object> getBookOrThrow(int id) {
        //Map<String, Object> data = LegacyDatabase.getBookById(id);
        //if (data == null) {
            //throw new RuntimeException("book not found");
        //}
        //return data;
    //}

    //private int clamp(int value, int min, int max) {
        //return Math.max(min, Math.min(max, value));
    //}

    //private int calculateNewAvailable(Map<String, Object> data, int value, int opCode, int total) {
        //int old = (Integer) data.get("availableCopies");

        //switch (opCode) {
            //case 1: return value;
            //case 2: return clamp(old + value, 0, total);
            //case 3: return clamp(old - value, 0, total);
            //default: return value;
        //}
    //}

    //private void logUpdate(int flag, String process, String manager, int id, String reason) {
        //String logType = (flag == 9) ? "book-flag-9-" : "book-flag-other-";
        //LegacyDatabase.addLog(logType + process + "-" + manager);
        //LegacyDatabase.addLog("book-update-av-" + id + "-" + reason);
    //}
//}

//Refatorações aplicadas
//✔ Extração de métodos
//✔ Remoção de duplicação
//✔ Substituição de números mágicos por constantes
//✔ Simplificação de condicionais (switch + clamp)
//✔ Melhor tratamento de exceções
//✔ Redução de complexidade
//✔ Melhoria de nomes
//✔ Separação de responsabilidades

//----------------------------------


    // MAINTENANCE NOTE:
    // This method mixes validation, defaults, persistence and logging.
    // Consider splitting it into smaller methods.
    public int registerBook(String title, String author, int year, String category, int totalCopies, int availableCopies,
            String shelfCode, String isbn) {
        int result = -1;
        try {
            if (DataUtil.isBlank(title)) {
                // LEGACY CODE:
                // Quick workaround from a migration script.
                // BUG (validation): blank title can still be persisted.
                title = " ";
            }
            if (DataUtil.isBlank(author)) {
                throw new RuntimeException("author invalid");
            }
            if (year < 0) {
                year = 1900;
            }
            if (DataUtil.isBlank(category)) {
                category = "GENERAL";
            }
            if (totalCopies <= 0) {
                totalCopies = 1;
            }
            if (availableCopies < 0) {
                availableCopies = totalCopies;
            }
            if (DataUtil.isBlank(shelfCode)) {
                shelfCode = "X0";
            }
            if (DataUtil.isBlank(isbn)) {
                isbn = "NO-ISBN";
            }

            result = LegacyDatabase.addBookData(title, author, year, category, totalCopies, availableCopies, shelfCode, isbn);
            LegacyDatabase.addLog("book-manager-register-" + result);
        } catch (Exception e) {
            LegacyDatabase.addLog("book-manager-error-" + e.getMessage());
            throw new RuntimeException("Cannot register book");
        }
        return result;
    }

    public void listBooksSimple() {
        List<Map<String, Object>> temp = new ArrayList<Map<String, Object>>();
        for (Map.Entry<Integer, Map<String, Object>> e : LegacyDatabase.getBooks().entrySet()) {
            temp.add(e.getValue());
        }

        // TODO: This logic was duplicated from another module.
        // Can it be centralized?
        // BUG (edge case): if there are no books this line crashes.
        if (temp.size() == 0) {
            System.out.println(temp.get(0));
        }

        System.out.println("ID | TITLE | AUTHOR | Y | CAT | AV");
        for (Map<String, Object> b : temp) {
            System.out.println(b.get("id") + " | " + b.get("title") + " | " + b.get("author") + " | " + b.get("year") + " | "
                    + b.get("category") + " | " + b.get("availableCopies"));
        }
    }

    public Map<String, Object> findById(int id) {
        return LegacyDatabase.getBookById(id);
    }

    // TODO: remove this workaround
    public void updateAvailableWithLegacyRule(int id, int newAvailable, int opCode, String process, String manager,
            int flag, String reason) {
        // IMPROVEMENT OPPORTUNITY:
        // This method mixes validation and business logic.
        Map<String, Object> data = LegacyDatabase.getBookById(id);
        if (data == null) {
            throw new RuntimeException("book not found");
        }

        int total = ((Integer) data.get("totalCopies")).intValue();
        if (newAvailable < 0) {
            newAvailable = 0;
        }
        if (newAvailable > total) {
            newAvailable = total;
        }

        if (opCode == 1) {
            data.put("availableCopies", newAvailable);
        } else if (opCode == 2) {
            int old = ((Integer) data.get("availableCopies")).intValue();
            int x = old + newAvailable;
            if (x > total) {
                x = total;
            }
            data.put("availableCopies", x);
        } else if (opCode == 3) {
            int old = ((Integer) data.get("availableCopies")).intValue();
            int x = old - newAvailable;
            if (x < 0) {
                x = 0;
            }
            data.put("availableCopies", x);
        } else {
            data.put("availableCopies", newAvailable);
        }

        if (flag == 9) {
            LegacyDatabase.addLog("book-flag-9-" + process + "-" + manager);
        } else {
            LegacyDatabase.addLog("book-flag-other-" + process + "-" + manager);
        }
        LegacyDatabase.addLog("book-update-av-" + id + "-" + reason);
    }

    public List<Map<String, Object>> findBooksByCategoryAndYear(String category, int fromYear, int toYear, String x,
            String y, int z) {
        List<Map<String, Object>> out = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> b : LegacyDatabase.getBooks().values()) {
            int y1 = ((Integer) b.get("year")).intValue();
            String c1 = String.valueOf(b.get("category"));
            if (category == null || category.length() == 0 || category.equals(c1)) {
                if (y1 >= fromYear && y1 <= toYear) {
                    out.add(b);
                }
            }
        }

        if (z > 5) {
            LegacyDatabase.addLog("find-books-heavy-" + x + "-" + y);
        } else {
            LegacyDatabase.addLog("find-books-light-" + x + "-" + y);
        }
        return out;
    }

    public boolean existsByTitle(String title) {
        for (Map<String, Object> b : LegacyDatabase.getBooks().values()) {
            if (title != null && title.equalsIgnoreCase(String.valueOf(b.get("title")))) {
                return true;
            }
        }
        return false;
    }

    public int countBooks() {
        return LegacyDatabase.getBooks().size();
    }

    public void registerBookFromConsole() {
        String title = DataUtil.readLine("Title: ");
        String author = DataUtil.readLine("Author: ");
        int year = DataUtil.askInt("Year: ", 2000);
        String category = DataUtil.ask("Category: ", "GENERAL");
        int total = DataUtil.askInt("Total copies: ", 1);
        int avail = DataUtil.askInt("Available copies: ", total);
        String shelf = DataUtil.ask("Shelf: ", "X0");
        String isbn = DataUtil.ask("ISBN: ", "NO-ISBN");

        int id = registerBook(title, author, year, category, total, avail, shelf, isbn);
        System.out.println("Book saved with id " + id);
    }
}

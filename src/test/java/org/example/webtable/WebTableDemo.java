package org.example.webtable;


//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WebTableDemo {

    public static void main(String[] args) {
        //WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Global setup
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // 1) Navigate to a sample table page (static HTML tables)
            driver.get("https://the-internet.herokuapp.com/tables");

            // ====== LOCATORS ======
            // Page has two tables: #table1 and #table2
            By tableLocator = By.cssSelector("#table1");              // choose table1
            By headerCells = By.cssSelector("#table1 thead th");      // header cells
            By bodyRows = By.cssSelector("#table1 tbody tr");         // body rows
            By bodyCells = By.cssSelector("td");                      // cells inside a row

            // 2) Wait until table is visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));

            WebElement table = driver.findElement(tableLocator);

            // 3) Read headers
            List<String> headers = table.findElements(headerCells)
                    .stream()
                    .map(WebElement::getText)
                    .map(String::trim)
                    .collect(Collectors.toList());

            System.out.println("Headers: " + headers);

            // 4) Read all rows and print as List<Map<Header, Value>>
            List<Map<String, String>> tableData = new ArrayList<>();

            List<WebElement> rows = table.findElements(bodyRows);
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(bodyCells);
                Map<String, String> rowMap = new LinkedHashMap<>();
                for (int c = 0; c < cells.size(); c++) {
                    String header = headers.get(c);
                    String value = cells.get(c).getText().trim();
                    rowMap.put(header, value);
                }
                tableData.add(rowMap);
            }

            System.out.println("Row count: " + tableData.size());
            printTable(tableData);

            // 5) Access a specific cell: e.g., Row 2, Column "Email"
            String colName = "Email";
            int rowIndex = 2; // 1-based semantic for human reading
            String emailValue = getCell(tableData, headers, rowIndex, colName);
            System.out.println("Row " + rowIndex + " - " + colName + ": " + emailValue);

            // 6) Find row by cell text (e.g., Last Name = "Smith") and click an action in that row
            String targetLastName = "Smith";
            Optional<WebElement> targetRow = findRowByCell(driver, tableLocator, headerCells, bodyRows, bodyCells, "Last Name", targetLastName);
            if (targetRow.isPresent()) {
                WebElement rowEl = targetRow.get();
                // The table has action links in the last column; try clicking "edit"
                WebElement editLink = rowEl.findElement(By.cssSelector("td:last-child a[href*='edit']"));
                System.out.println("Clicking Edit for row with Last Name = " + targetLastName);
                editLink.click();

                // Go back for continued demo
                driver.navigate().back();
                wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
            } else {
                System.out.println("Row with Last Name = " + targetLastName + " not found.");
            }

            // 7) Verify sorting by clicking a header (e.g., "First Name")
            WebElement firstNameHeader = table.findElements(headerCells).stream()
                    .filter(h -> h.getText().contains("First Name"))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("First Name header not found"));

            // Click to sort (page supports clicking headers to sort)
            firstNameHeader.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator)); // refresh

            // Re-read table after sort
            List<Map<String, String>> sortedData = readTable(driver.findElement(tableLocator), headerCells, bodyRows, bodyCells);
            boolean isSortedAsc = isColumnSorted(sortedData, "First Name", true);
            System.out.println("Is 'First Name' sorted ascending? " + isSortedAsc);

            // Click again to toggle sort
            firstNameHeader.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
            List<Map<String, String>> sortedDataDesc = readTable(driver.findElement(tableLocator), headerCells, bodyRows, bodyCells);
            boolean isSortedDesc = isColumnSorted(sortedDataDesc, "First Name", false);
            System.out.println("Is 'First Name' sorted descending? " + isSortedDesc);

            // 8) Example: Dynamic XPath for cell by header name and row index
            // (Works when table DOM is predictable; better to read into memory then click)
            String dynamicCellXPath = buildCellXPathByHeaderIndex("#table1", "Email", 3, driver, headerCells);
            WebElement dynamicCell = driver.findElement(By.xpath(dynamicCellXPath));
            System.out.println("Dynamic XPath found cell text: " + dynamicCell.getText());

            // 9) Pagination handling pattern (generic, if your table has pagination)
            // This page doesn't paginate, but here's a reusable approach:
            // paginateAndCollect(driver, By.cssSelector(".pagination .next"), tableLocator, headerCells, bodyRows, bodyCells);

            // 10) Safety: Try/catch around stale element while re-reading table during dynamic operations
            try {
                List<Map<String, String>> freshData = readTable(driver.findElement(tableLocator), headerCells, bodyRows, bodyCells);
                System.out.println("Fresh read succeeded, rows: " + freshData.size());
            } catch (StaleElementReferenceException sere) {
                System.out.println("Stale element detected, re-locating table...");
                WebElement tableAgain = wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
                List<Map<String, String>> freshData = readTable(tableAgain, headerCells, bodyRows, bodyCells);
                System.out.println("Fresh read after re-locate, rows: " + freshData.size());
            }

            System.out.println("\nDemo complete.");

        } catch (Exception e) {
            System.err.println("Error during table handling: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the browser
           // driver.quit();
        }
    }

    // ===================== Helper Methods =====================

    /** Read table into List<Map<Header, Value>> */
    private static List<Map<String, String>> readTable(WebElement table,
                                                       By headerCells,
                                                       By bodyRows,
                                                       By bodyCells) {
        List<String> headers = table.findElements(headerCells)
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());

        List<Map<String, String>> data = new ArrayList<>();
        for (WebElement row : table.findElements(bodyRows)) {
            List<WebElement> cells = row.findElements(bodyCells);
            Map<String, String> map = new LinkedHashMap<>();
            for (int i = 0; i < cells.size() && i < headers.size(); i++) {
                map.put(headers.get(i), cells.get(i).getText().trim());
            }
            data.add(map);
        }
        return data;
    }

    /** Pretty print the table data */
    private static void printTable(List<Map<String, String>> tableData) {
        if (tableData.isEmpty()) {
            System.out.println("Table is empty.");
            return;
        }
        // Print headers
        Set<String> headersSet = tableData.get(0).keySet();
        System.out.println(String.join(" | ", headersSet));
        System.out.println("----------------------------------------------");
        // Print rows
        for (Map<String, String> row : tableData) {
            String line = row.values().stream().collect(Collectors.joining(" | "));
            System.out.println(line);
        }
        System.out.println("----------------------------------------------");
    }

    /** Get value by row index (1-based) and header name */
    private static String getCell(List<Map<String, String>> tableData,
                                  List<String> headers,
                                  int rowIndexOneBased,
                                  String columnName) {
        if (rowIndexOneBased < 1 || rowIndexOneBased > tableData.size()) {
            throw new IllegalArgumentException("Row index out of bounds: " + rowIndexOneBased);
        }
        Map<String, String> row = tableData.get(rowIndexOneBased - 1);
        if (!row.containsKey(columnName)) {
            throw new IllegalArgumentException("Column not found: " + columnName + " in headers " + headers);
        }
        return row.get(columnName);
    }

    /** Find row element by column value (using DOM directly) */
    private static Optional<WebElement> findRowByCell(WebDriver driver,
                                                      By tableLocator,
                                                      By headerCells,
                                                      By bodyRows,
                                                      By bodyCells,
                                                      String headerName,
                                                      String cellText) {
        WebElement table = driver.findElement(tableLocator);
        List<WebElement> headers = table.findElements(headerCells);

        int colIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(headerName)) {
                colIndex = i;
                break;
            }
        }
        if (colIndex == -1) {
            throw new NoSuchElementException("Header not found: " + headerName);
        }

        for (WebElement row : table.findElements(bodyRows)) {
            List<WebElement> cells = row.findElements(bodyCells);
            if (colIndex < cells.size()) {
                String txt = cells.get(colIndex).getText().trim();
                if (txt.equalsIgnoreCase(cellText)) {
                    return Optional.of(row);
                }
            }
        }
        return Optional.empty();
    }

    /** Verify if column is sorted (ascending or descending) */
    private static boolean isColumnSorted(List<Map<String, String>> data,
                                          String colName,
                                          boolean ascending) {
        List<String> values = data.stream().map(r -> r.getOrDefault(colName, "")).collect(Collectors.toList());
        List<String> sorted = new ArrayList<>(values);

        // Case-insensitive natural sort
        sorted.sort(String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            Collections.reverse(sorted);
        }
        return values.equals(sorted);
    }

    /**
     * Build dynamic XPath to a cell by header name and 1-based row index.
     * Example result: //*[@id='table1']//tbody/tr[3]/td[<headerIndex>]
     */
    private static String buildCellXPathByHeaderIndex(String tableCssIdOrSelector,
                                                      String headerName,
                                                      int rowIndexOneBased,
                                                      WebDriver driver,
                                                      By headerCells) {
        WebElement table = driver.findElement(By.cssSelector(tableCssIdOrSelector));
        List<WebElement> headers = table.findElements(headerCells);

        int headerIndexOneBased = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(headerName)) {
                headerIndexOneBased = i + 1;
                break;
            }
        }
        if (headerIndexOneBased == -1) {
            throw new NoSuchElementException("Header not found: " + headerName);
        }

        return "//*[@id='table1']//tbody/tr[" + rowIndexOneBased + "]/td[" + headerIndexOneBased + "]";
    }

    /**
     * Generic pagination collector pattern:
     * Click next until disabled or no change, collect table on each page.
     * Adjust selectors based on your actual app's pagination.
     */
    private static List<Map<String, String>> paginateAndCollect(WebDriver driver,
                                                                By nextButton,
                                                                By tableLocator,
                                                                By headerCells,
                                                                By bodyRows,
                                                                By bodyCells) {
        List<Map<String, String>> all = new ArrayList<>();
        Set<String> seenPagesSnapshot = new HashSet<>();

        while (true) {
            WebElement table = driver.findElement(tableLocator);
            List<Map<String, String>> pageData = readTable(table, headerCells, bodyRows, bodyCells);
            String snapshot = pageData.toString();
            if (!seenPagesSnapshot.add(snapshot)) {
                // No change -> stop
                break;
            }
            all.addAll(pageData);

            // Try to click next
            try {
                WebElement next = driver.findElement(nextButton);
                if (!next.isDisplayed() || !next.isEnabled()) {
                    break;
                }
                next.click();
                // Optional: wait for table to refresh
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.stalenessOf(table));
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
            } catch (NoSuchElementException e) {
                // No next button -> stop
                break;
            }
        }
        return all;
    }
}


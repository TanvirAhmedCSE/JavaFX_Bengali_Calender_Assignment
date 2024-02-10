package TanvirAhmedBengalicalender;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import static javafx.application.Application.launch;

public class BengaliCalendar extends Application {
    private static final int NUM_ROWS = 7;
    private static final int NUM_COLUMNS = 7;

    @Override
    public void start(Stage primaryStage) {
    Scanner scanner = new Scanner(System.in);

    int year = 0;
    int month = 0;
    while (year <= 0) {
        System.out.print("Enter a Year: ");
        year = scanner.nextInt();
        if (year <= 0) {
            System.out.println("Wrong entry, please enter a valid Year.");
        }
    }
    while (month <= 0 || month > 12) {
        System.out.print("Enter a Month: ");
        month = scanner.nextInt();
        if (month <= 0 || month > 12) {
            System.out.println("Wrong entry, please enter a valid Month.");
        }
    }
        GridPane calendarPane = new GridPane();
        calendarPane.setAlignment(Pos.CENTER);
        calendarPane.setHgap(10);
        calendarPane.setVgap(10);
        calendarPane.setPadding(new Insets(10));
        calendarPane.setStyle("-fx-background-color: #FFFFFF;");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);

        String[] weekDays = {
                "\u09B0\u09AC\u09BF",
                "\u09B8\u09CB\u09AE",
                "\u09AE\u0999\u09CD\u0997\u09B2",
                "\u09AC\u09C1\u09A7",
                "\u09AC\u09C3\u09B9\u0983",
                "\u09B6\u09C1\u0995\u09CD\u09B0",
                "\u09B6\u09A8\u09BF"
        };
        
        for (int i = 1; i <= NUM_COLUMNS; i++) {
            Label weekDayLabel = new Label(weekDays[i - 1]);
            weekDayLabel.setFont(Font.font("Nikosh", 16));
            calendarPane.add(weekDayLabel, i - 1, 0);
            GridPane.setHalignment(weekDayLabel, HPos.CENTER);
        }
        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int row = 1;
        int column = startDay - 1;
        for (int day = 1; day <= numberOfDays; day++) {
            Label dateLabel = new Label(getBengaliNumber(day));
            dateLabel.setFont(Font.font("Nikosh", 16));
            calendarPane.add(dateLabel, column, row);
            GridPane.setHalignment(dateLabel, HPos.CENTER);
            column++;
            if (column == NUM_COLUMNS) {
                column = 0;
                row++;
            }
        }
        
        String[] monthNames = {
                "\u09AC\u09C8\u09B6\u09BE\u0996",
                "\u099C\u09CD\u09AF\u09C8\u09B7\u09CD\u09A0",
                "\u0986\u09B7\u09BE\u09A2\u09BC",
                "\u09B6\u09CD\u09B0\u09BE\u09AC\u09A3",
                "\u09AD\u09BE\u09A6\u09CD\u09B0",
                "\u0986\u09B6\u09CD\u09AC\u09BF\u09A8",
                "\u0995\u09BE\u09B0\u09CD\u09A4\u09BF\u0995",
                "\u09AB\u09BE\u09B2\u09CD\u0997\u09C1\u09A8",
                "\u099A\u09C8\u09A4\u09CD\u09B0",
                "\u09B8\u09CD\u09AA\u09BE\u0995\u09CD\u09A6",
                "\u09AB\u09BE\u09B2\u09CD\u0997\u09C1\u09A8",
                "\u099A\u09C8\u09A4\u09CD\u09B0"
        };
        
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(calendarPane);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle(monthNames[month - 1]);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getBengaliNumber(int number) {
        StringBuilder bengaliNumber = new StringBuilder();
        String numberString = String.valueOf(number);
        for(int i = 0;i < numberString.length(); i++) {
            char digit = numberString.charAt(i);
            char bengaliDigit = (char)(digit + '\u09E6' - '0');
            bengaliNumber.append(bengaliDigit);
        }
        return bengaliNumber.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

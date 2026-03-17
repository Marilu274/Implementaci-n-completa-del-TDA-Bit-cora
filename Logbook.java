import java.util.GregorianCalendar;

public class Logbook {

    private int logMonth;
    private int logYear;
    private int[] entry = new int[32];
    private GregorianCalendar logCalendar;

    public Logbook(int month, int year) {
        this.logMonth    = month;
        this.logYear     = year;
        this.logCalendar = new GregorianCalendar(year, month - 1, 1);
        for (int i = 0; i < entry.length; i++) entry[i] = 0;
    }

    public void putEntry(int day, int value) {
        if (day >= 1 && day <= daysInMonth())
            entry[day] = value;
        else
            System.out.println("  [!] Día inválido: " + day);
    }

    public int getEntry(int day) {
        if (day >= 1 && day <= daysInMonth()) return entry[day];
        return -1;
    }

    public int month()  { return logMonth; }
    public int year()   { return logYear;  }

    public int daysInMonth() {
        int[] days = { 0,31,leapYear()?29:28,31,30,31,30,31,31,30,31,30,31 };
        return days[logMonth];
    }

    private boolean leapYear() {
        return logCalendar.isLeapYear(logYear);
    }

    public int firstDayOfWeek() {
        return logCalendar.get(GregorianCalendar.DAY_OF_WEEK);
    }

    public int dayOfWeek(int day) {
        GregorianCalendar cal = new GregorianCalendar(logYear, logMonth - 1, day);
        return cal.get(GregorianCalendar.DAY_OF_WEEK);
    }

    public void printCalendar() {
        String[] mNames = { "","Enero","Febrero","Marzo","Abril","Mayo",
            "Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre" };
        String[] dNames = { "Dom","Lun","Mar","Mié","Jue","Vie","Sáb" };

        System.out.println();
        System.out.println("  +----------------------------------+");
        System.out.printf ("  |  %-31s|%n", mNames[logMonth] + " " + logYear);
        System.out.println("  +----------------------------------+");
        System.out.print  ("  | ");
        for (String d : dNames) System.out.printf(" %3s", d);
        System.out.println("  |");
        System.out.println("  +----------------------------------+");

        int startDay = firstDayOfWeek() - 1;
        System.out.print("  | ");
        for (int i = 0; i < startDay; i++) System.out.print("    ");

        int total = daysInMonth();
        for (int day = 1; day <= total; day++) {
            String mark = (entry[day] != 0) ? "*" : " ";
            System.out.printf(" %2d%s", day, mark);
            int col = (startDay + day - 1) % 7;
            if (col == 6 && day < total) {
                System.out.println("  |");
                System.out.print("  | ");
            }
        }
        System.out.println();
        System.out.println("  +----------------------------------+");
        System.out.println("  * = dia con entrada registrada");
        System.out.println();
    }

    @Override
    public String toString() {
        String[] m = { "","Enero","Febrero","Marzo","Abril","Mayo","Junio",
            "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre" };
        return "Logbook[" + m[logMonth] + " " + logYear + "] - "
            + daysInMonth() + " dias" + (leapYear() ? " (bisiesto)" : "");
    }
}
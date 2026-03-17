import java.util.GregorianCalendar;

public class LogbookTest {

    static void banner(int num, String title) {
        System.out.println();
        System.out.println("==================================================");
        System.out.printf ("  PRUEBA %d: %s%n", num, title);
        System.out.println("==================================================");
    }

    static void ok(String msg)   { System.out.println("  OK  " + msg); }
    static void info(String msg) { System.out.println("  --> " + msg); }
    static void warn(String msg) { System.out.println("  !!  " + msg); }

    // PRUEBA 1 - Dia 1 y dia 15
    static void prueba1() {
        banner(1, "Dia 1 y dia 15 del mes");
        Logbook lb = new Logbook(3, 2026);
        info("Logbook creado: " + lb);

        lb.putEntry(1, 100);
        ok("putEntry(1, 100) guardado");
        ok("getEntry(1) = " + lb.getEntry(1));

        lb.putEntry(15, 200);
        ok("putEntry(15, 200) guardado");
        ok("getEntry(15) = " + lb.getEntry(15));

        lb.printCalendar();
    }

    // PRUEBA 2 - Primer y ultimo dia del mes
    static void prueba2() {
        banner(2, "Primer y ultimo dia del mes");
        Logbook lb = new Logbook(2, 2026);
        info("Logbook creado: " + lb);
        info("Total dias: " + lb.daysInMonth());

        int ultimo = lb.daysInMonth();
        lb.putEntry(1, 50);
        ok("putEntry(1, 50) guardado  --> getEntry(1) = " + lb.getEntry(1));

        lb.putEntry(ultimo, 99);
        ok("putEntry(" + ultimo + ", 99) guardado  --> getEntry(" + ultimo + ") = " + lb.getEntry(ultimo));

        warn("Intentando dia invalido 32:");
        lb.putEntry(32, 1);
        ok("getEntry(32) = " + lb.getEntry(32) + "  (debe ser -1)");

        lb.printCalendar();
    }

    // PRUEBA 3 - Todos los viernes
    static void prueba3() {
        banner(3, "Todos los viernes del mes");
        Logbook lb = new Logbook(3, 2026);
        info("Logbook creado: " + lb);

        int viernes = GregorianCalendar.FRIDAY;
        int contador = 0;
        for (int day = 1; day <= lb.daysInMonth(); day++) {
            if (lb.dayOfWeek(day) == viernes) {
                lb.putEntry(day, 77);
                ok("Viernes: dia " + day + " --> putEntry(" + day + ", 77)");
                contador++;
            }
        }
        info("Total viernes encontrados: " + contador);
        lb.printCalendar();
    }

    // PRUEBA 4 - Actualizar el primer dia
    static void prueba4() {
        banner(4, "Actualizar entrada del primer dia");
        Logbook lb = new Logbook(3, 2026);
        info("Logbook creado: " + lb);

        lb.putEntry(1, 10);
        ok("Registro inicial  --> getEntry(1) = " + lb.getEntry(1));

        lb.putEntry(1, 55);
        ok("Primera actualiz  --> getEntry(1) = " + lb.getEntry(1));

        lb.putEntry(1, 0);
        ok("Reset a cero      --> getEntry(1) = " + lb.getEntry(1));

        lb.putEntry(1, 999);
        ok("Valor final       --> getEntry(1) = " + lb.getEntry(1));

        lb.printCalendar();
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println("**************************************************");
        System.out.println("   SUITE DE PRUEBAS - CLASE Logbook | Anio 2026");
        System.out.println("**************************************************");

        prueba1();
        prueba2();
        prueba3();
        prueba4();

        System.out.println();
        System.out.println("**************************************************");
        System.out.println("   TODAS LAS PRUEBAS COMPLETADAS");
        System.out.println("**************************************************");
    }
}

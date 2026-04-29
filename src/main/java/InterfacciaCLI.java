import model.*;

public class InterfacciaCLI{
    Pilota c = new Pilota("EmyServi", "password1", "Emy Servillo", "SRVMLR06R08A024O",
            "+393917021117", "P934", 6767.67);
    Pilota c1 = new Pilota("alekg44", "password2", "Alessio Riccio",
            "ALKGGG44R04A023F", "+393917021112", "P134", 5000.00);
    Hostess h1 = new Hostess("AlexPizzi", "passwordgay", "Alessandro Pizzi", "ALALALALLLLALLAAL",
            "+393918691020", "H563");
    Hostess h2 = new Hostess("ErDeltarune", "Monster", "Emanuele toledo", "DDG315DKLG51A95V",
            "+393849583940", "H654");
    Aereo A = new Aereo("778834", "Piselling", 9000);
    Cliente C = new Cliente("Alessandro", "pass", "Alessandro qualcosa", "AJDLGJ395LF",
            "C3533322");
    Volo v = new Volo("V43452", "MonteRuscello", 176, c, c1, h1, h2, A);
    Prenotazione p = new Prenotazione("P1512", C, v, "14F");
    
}
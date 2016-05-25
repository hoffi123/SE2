package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import java.util.AbstractQueue;
import java.util.concurrent.ArrayBlockingQueue;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Mit Hilfe von Vormerkkarten werden Medien für die nächsten bis zu drei Kunden
 * vorgemerkt.
 * 
 * Medien können unabhängig von ihrem entleihstatus vorgemerkt werden. Zu jedem Medium 
 * kann es maximal eine Vormerkkarte geben.
 * 
 * @author Niklas, Thomas
 * @version SoSe 2016
 */

public class Vormerkkarte {
    
    private AbstractQueue<Kunde> _vormerker;
    
    private Medium _medium;
    
    
    /**
     * Initialisiert eine neue Vormerkkarte mit den gegebenen Daten. 
     * 
     * @param kunde Der Kunde, der das Medium vormerken möchte
     * @param medium Das Medium, das vorgemerkt werden soll.
     * 
     * TODO: Vor- und Nachbedingungen...
     * @require kunde != null
     * @require medium != null
     */
    public Vormerkkarte(Kunde kunde, Medium medium)
    {
        assert kunde != null : "Vorbedingung verletzt: kunde != null";
        assert medium != null : "Vorbedingung verletzt: medium != null";
        _vormerker = new ArrayBlockingQueue<Kunde>(3);
        _vormerker.add(kunde);
        
        _medium = medium;
    }
    
    /**
     * Gibt die vormerkenden Kunden für das Medium aus.
     * 
     * @return Die vormerkenden Kunden
     */
    public AbstractQueue<Kunde> getVormerker()
    {
        return _vormerker;
    }
    
    /**
     * Prüft, ob die Vormerkliste voll ist. Falls ja, kann das 
     * Medium von keinem weiteren Kunden vorgemerkt werden.
     * 
     * @return true, wenn die Liste voll besetzt ist, false sonst.
     */
    public boolean istVormerklisteVoll()
    {
        return (_vormerker.size() == 3);
    }
    
    /**
     * Fügt den übergebenen Kunden der Vormerkliste hinzu.
     * 
     * @param kunde Der hinzuzufügende Kunde
     * 
     * @require kunde != null
     * 
     * @ensure _vormerker.contains(kunde) == true
     */
    public void fügeVormerkerHinzu(Kunde kunde)
    {
        assert kunde != null : "Vorbedingung verletzt: kunde != null";
        if (_vormerker.size() <= 2)
        {
            _vormerker.add(kunde);
        }
    }
    
    /**
     * Entfernt den ersten vormerkenden Kunden. Die nachfolgenden
     * Vormerker rücken automatisch auf.
     * 
     * @param kunde Der zu entfernende Kunde
     * 
     * @require kunde != null
     * 
     * @ensure _vormerker.contains(kunde) == false
     */
    public void entferneVormerker(Kunde kunde)
    {
        assert kunde != null : "Vorbedingung verletzt: kunde != null";
        _vormerker.remove(kunde);
    }
    
    /**
     * Gibt den ersten vormerkenden Kunden aus der Vormerkerqueue zurück.
     * 
     * @return Der erste vormerkende Kunde
     */
    public Kunde gibErsterVormerker()
    {
        return _vormerker.peek();
    }

}
